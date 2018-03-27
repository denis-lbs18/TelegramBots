package br.com.denisluna.telegrambots.types;

public class Video {
	private String fileId;
	private int width;
	private int heigth;
	private int duration;
	private String mimeType;
	private int fileSize;

	public Video(String fileId, int width, int heigth, int duration, String mimeType, int fileSize) {
		this.fileId = fileId;
		this.width = width;
		this.heigth = heigth;
		this.duration = duration;
		this.mimeType = mimeType;
		this.fileSize = fileSize;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeigth() {
		return heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
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
