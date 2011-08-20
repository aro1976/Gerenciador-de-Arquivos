package br.com.dynamicflow.fileshare.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.document.mongodb.mapping.Document;

@Document
public class FileMetadata {

	public enum Type {
		MBL, HBL, BL, MAWB, HAWB, AWB, INVOICE, PACKING_LIST
	}

	@Id
	private String id;

	private String fileName;

	private Date uploadDate;

	private Date accessDate;
	
	private Long accessCount;
	
	private List<String> processList;

	private Type documentType;

	private String document;

	private Long size;

	private String contentType;

	public FileMetadata() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date date) {
		this.uploadDate = date;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<String> getProcess() {
		return processList;
	}

	public void setProcess(List<String> process) {
		this.processList = process;
	}

	public Type getDocumentType() {
		return documentType;
	}

	public void setDocumentType(Type type) {
		this.documentType = type;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Date getAccessDate() {
		return accessDate;
	}

	public void setAccessDate(Date accessDate) {
		this.accessDate = accessDate;
	}

	public Long getAccessCount() {
		return accessCount != null ? accessCount : 0L;
	}

	public void setAccessCount(Long count) {
		this.accessCount = count;
	}

	@Override
	public String toString() {
		return "FileMetadata [id=" + id + ", contentType=" + contentType
				+ ", fileName=" + fileName + ", size=" + size + ", uploadDate="
				+ uploadDate + ", accessDate=" + accessDate + ", count="
				+ accessCount + ", processList=" + processList + ", documentType="
				+ documentType + ", document=" + document + "]";
	}

}
