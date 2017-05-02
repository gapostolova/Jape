package com.example.model;

public class Category {
	
	private long categoryID;
	private String categoryName;
	
	public Category(long categoryID, String categoryName) {
		this.categoryID = categoryID;
		if(categoryName != null && !categoryName.isEmpty()){
			this.categoryName = categoryName.toUpperCase();
		}
	}
	
	public long getCategoryID() {
		return categoryID;
	}
	
	public String getCategoryName() {
		return categoryName;
	}

	@Override
	public String toString() {
		return "Category [categoryID=" + categoryID + ", categoryName=" + categoryName + "]";
	}
}
