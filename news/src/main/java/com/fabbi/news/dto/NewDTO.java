package com.fabbi.news.dto;

import javax.validation.constraints.NotBlank;

public class NewDTO extends AbstractDTO<NewDTO> {

	@NotBlank(message = "Title is mandatory")
	private String title;
	
	@NotBlank(message = "Content is mandatory")
	private String content;
	
	@NotBlank(message = "Short Description is mandatory")
	private String shortDescription;
	
	@NotBlank(message = "Category is mandatory")
	private String categoryCode;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
}
