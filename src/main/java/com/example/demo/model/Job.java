package com.example.demo.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
//@Data
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String description;

	//	@Lob
	//	private byte[] image;
	private String imagepath;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	@Column(name = "bookmark_flag")
	private Boolean bookmarkFlag;

	// 使わないと複数登録ができないから
	// エンティティを持つコレクションの要素を外部テーブルにマッピングするアノテーション
	@ElementCollection
	private List<String> categories;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getImagePath() {
		return imagepath;
	}

	public void setImagePath(String imagePath) {
		this.imagepath = imagePath;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", title=" + title + ", description=" + description + ", image" + imagepath
				+ ", date=" + date + ", categories=" + categories + "]";
	}

	public Boolean getBookmarkFlag() {
		return bookmarkFlag;
	}

	public void setBookmarkFlag(Boolean bookmarkFlag) {
		this.bookmarkFlag = bookmarkFlag;
	}

}
