package com.hulkStore_project.model;

public class Product {
int product_id;
String prodcut_name;
int category_id;
int stock;

public Product(int product_id, String prodcut_name, int category_id, int stock) {
	super();
	this.product_id = product_id;
	this.prodcut_name = prodcut_name;
	this.category_id = category_id;
	this.stock = stock;
}


public int getProduct_id() {
	return product_id;
}
public void setProduct_id(int product_id) {
	this.product_id = product_id;
}
public String getProdcut_name() {
	return prodcut_name;
}
public void setProdcut_name(String prodcut_name) {
	this.prodcut_name = prodcut_name;
}
public int getCategory_id() {
	return category_id;
}
public void setCategory_id(int category_id) {
	this.category_id = category_id;
}
public int getStock() {
	return stock;
}
public void setStock(int stock) {
	this.stock = stock;
}


}
