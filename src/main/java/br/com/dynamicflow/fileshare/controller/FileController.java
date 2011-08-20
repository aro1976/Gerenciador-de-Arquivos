package br.com.dynamicflow.fileshare.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.dynamicflow.fileshare.model.FileItem;
import br.com.dynamicflow.fileshare.service.FileMetadata;
import br.com.dynamicflow.fileshare.service.FileMetadataConfig;
import br.com.dynamicflow.fileshare.service.FileMetadataRepository;

@Controller
@RequestMapping(value = "/file")
public class FileController {
	
	private static final Logger log = Logger.getLogger(FileController.class);
	
	private static final SimpleDateFormat httpDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
	 
	static{
		httpDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	}
	
	public synchronized static String getHttpDate(Date date){
		return httpDateFormat.format(date);
	}
	
	@Autowired
	FileMetadataRepository fileRepository;
	
	@Autowired
	FileMetadataConfig fileConfig;
	
	@RequestMapping(value="all", method = RequestMethod.GET)
	public ModelAndView listAll(Model model) throws Exception {
		log.info("listAll");
		
		ModelAndView modelAndView = new ModelAndView("file/list");
		model.addAttribute("fileList", fileRepository.findAll());
		return modelAndView;
	}
	
	@RequestMapping(value="query", method = RequestMethod.GET)
	public ModelAndView query(FileItem query) throws Exception {
		log.info("query");
		
		ModelAndView model = new ModelAndView("file/list");
		if (query.getProcess() != null && query.getProcess().length() > 0) {
			model.addObject("fileList", fileRepository.findByProcess(query.getProcess()));
			return model;
		}
		
		if (query.getDocument() != null && query.getDocument().length() > 0) {
			model.addObject("fileList", fileRepository.findByDocument(query.getDocumentType().toString(),query.getDocument()));
			return model;
		}
		
		return new ModelAndView("file/query");
	}
	
	@RequestMapping(value="new", method = RequestMethod.GET)
	public String newFile(Model model) {
		log.info("newFile");
		
		model.addAttribute(new FileItem());
		return "file/new";
	}

	@RequestMapping(value="download/{id}", method=RequestMethod.GET)
	public void view(@PathVariable String id, HttpServletResponse response) throws IOException {
		log.info("view "+id);
		
		File file = digestToFile(id);
		if (!file.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		
		FileMetadata metadata = fileRepository.findById(id);
		fileRepository.incrementAccess(id);
		
		response.addHeader("Last-Modified", getHttpDate(metadata.getUploadDate()));
		response.setContentType(metadata.getContentType());
		response.setContentLength(metadata.getSize().intValue());
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ metadata.getFileName() + "\"");
		FileInputStream fileInputStream = new FileInputStream(file);
		FileCopyUtils.copy(fileInputStream, response.getOutputStream());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String uploadFile(FileItem uploadItem, BindingResult result) {
		log.info("uploadFile "+uploadItem.getFileData().getOriginalFilename());
		
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.err.println("Error: " + error.getCode() + " - "
						+ error.getDefaultMessage());
			}
			return "file/new";
		}

		String digest = null;
		try {
			digest = calculateDigest(uploadItem);
			storeFile(digest,uploadItem);
			
			FileMetadata fileMetadata = new br.com.dynamicflow.fileshare.service.FileMetadata();
			fileMetadata.setUploadDate(new Date());
			fileMetadata.setDocument(uploadItem.getDocument());
			fileMetadata.setFileName(uploadItem.getFileData().getOriginalFilename());
			fileMetadata.setId(digest);
			fileMetadata.setContentType(uploadItem.getFileData().getContentType());
			fileMetadata.setSize(uploadItem.getFileData().getSize());
			String[] process = uploadItem.getProcess().split(",");
			fileMetadata.setProcess(Arrays.asList(process));
			fileMetadata.setDocumentType(uploadItem.getDocumentType());
			fileRepository.save(fileMetadata);
	
			return "redirect:/app/file/query";
		} catch (IOException e) {
			log.error(e);
			return "file/new";
		}
	}

	private String calculateDigest(FileItem uploadItem) throws IOException {
		log.debug("calculateDigest "+uploadItem.getFileData().getOriginalFilename());
		
		String digest = DigestUtils.shaHex((uploadItem.getFileData().getInputStream()));
		return digest;
	}
	
	private File storeFile(String digest, FileItem uploadItem) throws IOException {
		log.debug("storeFile "+digest+ " for "+uploadItem.getFileData().getOriginalFilename());
		
		File dataDir = digestToDir(digest);
		dataDir.mkdirs();

		File file = digestToFile(digest);
		uploadItem.getFileData().transferTo(file);
		return file;
	}
	
	public File digestToDir(String digest) {
		return new File(fileConfig.getDataPathDir(),digest.substring(0, 3) + "/"+ digest.substring(3, 6));
	}
	
	public File digestToFile(String digest) {
		return new File(digestToDir(digest),digest.substring(6));
	}
}
