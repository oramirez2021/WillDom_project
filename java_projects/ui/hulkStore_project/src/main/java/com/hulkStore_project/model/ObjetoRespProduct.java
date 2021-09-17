package com.hulkStore_project.model;

import java.util.ArrayList;

public class ObjetoRespProduct {

	public ArrayList<Integer> product_id = new ArrayList<Integer>();
	public ArrayList<String> product_name = new ArrayList<String>();
	public ArrayList<Integer> category_id = new ArrayList<Integer>();
	public ArrayList<Integer> stock = new ArrayList<Integer>();

	public ObjetoRespProduct(ArrayList<Integer> prduct_id,ArrayList<String> product_name,ArrayList<Integer> category_id,ArrayList<Integer> stock) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_id = category_id;
		this.stock = stock;
	}
	public ObjetoRespProduct() {
		
	}
}
