package br.com.denisluna.telegrambots.types;

public class Photo {
	private String fileId;
	private int width;
	private int heigth;
	private int fileSize;

	public Photo(String fileId, int width, int heigth, int fileSize) {
		this.fileId = fileId;
		this.width = width;
		this.heigth = heigth;
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

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
}
