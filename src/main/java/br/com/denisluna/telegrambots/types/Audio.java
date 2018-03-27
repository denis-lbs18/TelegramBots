package br.com.denisluna.telegrambots.types;

public class Audio {
	private String fileID;
	private int duration;
	private String fileName;
	private String mimeType;
	private int fileSize;

	public Audio(String fileID, int duration, String fileName, String mimeType, int fileSize) {
		this.fileID = fileID;
		this.duration = duration;
		this.fileName = fileName;
		this.mimeType = mimeType;
		this.fileSize = fileSize;
	}

	public String getFileID() {
		return fileID;
	}

	public void setFileID(String fileID) {
		this.fileID = fileID;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
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
