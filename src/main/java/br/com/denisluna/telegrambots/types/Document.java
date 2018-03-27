package br.com.denisluna.telegrambots.types;

public class Document {
	private String fileId;
	private String fileName;
	private String mimeType;
	private int fileSize;

	public Document(String fileId, String fileName, String mimeType, int fileSize) {
		this.fileId = fileId;
		this.fileName = fileName;
		this.mimeType = mimeType;
		this.fileSize = fileSize;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
}
