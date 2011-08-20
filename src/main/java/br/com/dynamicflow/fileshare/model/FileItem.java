package br.com.dynamicflow.fileshare.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import br.com.dynamicflow.fileshare.service.FileMetadata;
import br.com.dynamicflow.fileshare.service.FileMetadata.Type;

/**
 */
public class FileItem {
	private String name;
	private String document;
	private String process;
	private Type documentType;
	private CommonsMultipartFile fileData;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public Type getDocumentType() {
		return documentType;
	}
	public void setDocumentType(Type type) {
		this.documentType = type;
	}
	public CommonsMultipartFile getFileData() {
		return fileData;
	}
	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}	
}
