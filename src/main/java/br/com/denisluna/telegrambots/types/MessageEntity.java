package br.com.denisluna.telegrambots.types;

public class MessageEntity {
	private String type;
	private int offset;
	private int length;
	private String url;

	public MessageEntity(String type, int offset, int length) {
		this.type = type;
		this.offset = offset;
		this.length = length;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
