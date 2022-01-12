package com.hulkStore_ms.model;

import java.util.ArrayList;

public class ObjetoRespCategory {

	public ArrayList<Integer> category_id = new ArrayList<Integer>();
	public ArrayList<String> category_name = new ArrayList<String>();
	public ArrayList<String> image_path = new ArrayList<String>();

	public ObjetoRespCategory(ArrayList<Integer> category_id,ArrayList<String> category_name, ArrayList<String> image_path) {
		super();
		this.category_id = category_id;
		this.category_name = category_name;
		this.image_path = image_path;
	}
	public ObjetoRespCategory() {
		
	}
}
