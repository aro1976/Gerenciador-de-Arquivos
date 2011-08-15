package br.com.dynamicflow.fileshare.service;

import java.util.Date;
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

	private Date date;

	private String process;

	private Type documentType;

	private String document;

	private Long size;

	private String contentType;

	public FileMetadata() {
	}

	public FileMetadata(String fileName, String process, Type type,
			String document) {
		super();
		this.date = new Date();
		this.fileName = fileName;
		this.process = process;
		this.documentType = type;
		this.document = document;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	@Override
	public String toString() {
		return "FileMetadata [id=" + id + ", contentType=" + contentType
				+ ", size=" + size + ", date=" + date + ", fileName="
				+ fileName + ", process=" + process + ", documentType="
				+ documentType + ", document=" + document + "]";
	}

}
