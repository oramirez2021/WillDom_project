package com.hulkStore_ms.model;

import java.util.ArrayList;

public class ObjetoRespProduct {

	public ArrayList<Integer> product_id = new ArrayList<Integer>();
	public ArrayList<String> product_name = new ArrayList<String>();
	public ArrayList<Integer> category_id = new ArrayList<Integer>();
	public ArrayList<Integer> stock = new ArrayList<Integer>();
	public ArrayList<String> image_path = new ArrayList<String>();

	public ObjetoRespProduct(ArrayList<Integer> prduct_id,ArrayList<String> product_name,ArrayList<Integer> category_id,ArrayList<Integer> stock, ArrayList<String> image_path) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_id = category_id;
		this.stock = stock;
		this.image_path = image_path;
	}
	public ObjetoRespProduct() {
		
	}
}
