package com.example.demo;

import java.util.List;

public class JobDTO {
	private String title;
	private String description;
	private Boolean bookmarkFlag;
	private List<String> categories;

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

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

}