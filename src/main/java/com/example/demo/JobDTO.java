package com.example.demo;

public class JobDTO {
	private String title;
	private String description;
	private Boolean bookmarkFlag;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getBookmarkFlag() {
		return bookmarkFlag;
	}

	public void setBookmarkFlag(Boolean bookmarkFlag) {
		this.bookmarkFlag = bookmarkFlag;
	}

}