package com.hulkStore_project.model;

public class Category {
int category_id;
String category_name;
String image_path;

public Category(int category_id, String category_name, String image_path) {
	super();
	this.category_id = category_id;
	this.category_name = category_name;
	this.image_path = image_path;
}

public int getCategory_id() {
	return category_id;
}
public void setCategory_id(int category_id) {
	this.category_id = category_id;
}
public String getCategory_name() {
	return category_name;
}
public void setCategory_name(String category_name) {
	this.category_name = category_name;
}
public String getImage_path() {
	return image_path;
}
public void setImage_path(String image_path) {
	this.image_path = image_path;
}


}
