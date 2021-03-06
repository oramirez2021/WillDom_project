package com.hulkStore_project.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

import com.hulkStore_project.model.ObjetoRespProduct;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hulkStore_project.model.*;
public class ProductControllerCRUD extends Controller {
	Button btn_save_product;
	Button btn_update_product;
	Textbox txt_product_name;
	Textbox txt_category_id;
	Textbox txt_stock;
	String product_name;
	int stock,category_id,product_id;
	private HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
	Gson gson = new Gson();
	int trans_status;
@Override
public void doAfterCompose(Component comp) throws Exception {
	// TODO Auto-generated method stub
	super.doAfterCompose(comp);
	try {
	btn_save_product.addEventListener("onClick", new guardarListener());
	}catch (Exception e) {
		// TODO: handle exception
	}
	try {
	btn_update_product.addEventListener("onClick", new actualizarListener());
	//System.out.println(session.getAttribute("id_producto"));
	txt_product_name.setValue(""+session.getAttribute("nombre_producto"));
	txt_category_id.setValue(""+session.getAttribute("categoria_producto"));
	txt_stock.setValue(""+session.getAttribute("stock_producto"));
	}catch (Exception e) {
		// TODO: handle exception
	}
}


public class guardarListener implements EventListener {
    public void onEvent(Event event) {
    	product_name = txt_product_name.getValue();
		category_id = Integer.parseInt(txt_category_id.getValue());
		stock = Integer.parseInt(txt_stock.getValue());
		insertarProducto(product_name, category_id, stock);
    }
}

public class actualizarListener implements EventListener {
    public void onEvent(Event event) {
    	product_id = Integer.parseInt(session.getAttribute("id_producto").toString());
    	product_name = txt_product_name.getValue();
		category_id = Integer.parseInt(txt_category_id.getValue());
		stock = Integer.parseInt(txt_stock.getValue());
		actualizarProducto(product_name, category_id, stock,product_id);
    }
}

private void insertarProducto(String product_name, int category_id, int stock) {
	//Se inserta producto en la tabla PRODUCTS
	final HttpRequest requestPost_prod = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8888/hulkStore/InsertProduct?product_name="+product_name+"&category_id="+category_id+"&stock="+stock)).build();
	try {
		Listitem item,item1;
		Listcell cell;
		final HttpResponse<String> response_prod = httpClient.send(requestPost_prod, HttpResponse.BodyHandlers.ofString());
		System.out.println("omar");
		System.out.println(response_prod.statusCode());
		System.out.println(response_prod.body());
		System.out.println(response_prod.headers());
		trans_status = response_prod.statusCode();
		if (trans_status == 200) {
			product_id = Integer.parseInt(response_prod.body());
		}
		//Se inserta movimiento en KARDEX de producto
		final HttpRequest requestPost_k_prod = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8888/hulkStore/InsertKardex?product_id="+product_id+"&ope_type=E&cant="+stock)).build();
		try {
			final HttpResponse<String> response_kardex_prod = httpClient.send(requestPost_k_prod, HttpResponse.BodyHandlers.ofString());
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (IOException | InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//Se inserta movimiento en KARDEX de producto
	/*final HttpRequest requestPost_k_prod = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8888/hulkStore/InsertKardex?product_id="+""+"&ope_type=E&cant="+stock)).build();
	try {
		Listitem item,item1;
		Listcell cell;
		final HttpResponse<String> response = httpClient.send(requestPost_k_prod, HttpResponse.BodyHandlers.ofString());
		
	} catch (IOException | InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
}

private void actualizarProducto(String product_name, int category_id, int stock, int product_id) {
	final HttpRequest requestPost = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8888/hulkStore/UpdateProduct?product_name="+product_name+"&category_id="+category_id+"&stock="+stock+"&product_id="+product_id)).build();
	try {
		Listitem item,item1;
		Listcell cell;
		final HttpResponse<String> response = httpClient.send(requestPost, HttpResponse.BodyHandlers.ofString());
		
	} catch (IOException | InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
