package com.hulkStore_project.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hulkStore_project.controllers.ProductControllerCRUD.guardarListener;
import com.hulkStore_project.model.ObjetoRespProduct;
import com.hulkStore_project.model.Product;

public class ProductControler extends SelectorComposer<Component> {
    private ListModel<Product> productsModel;
    @Wire
    private Window win;
   	//Consumo rest
	private HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
	ObjetoRespProduct ObjRespProd=null;
	Gson gson = new GsonBuilder().serializeNulls().create();
	String selectedProductId, selectedProductName = "";
	String product_name = "";
	int category_id,stock,product_id;
	Session session = Sessions.getCurrent();
	public ProductControler() {
		cargarProductos();
	}
	
	private void showNotify(String msg,Component ref){
        Clients.showNotification(msg,"info",ref,"top_right",2000);
    }
	
	public ListModel<Product> getProductsModel() {
        return productsModel;
    }
	
	@Listen("onSelect = listbox")
	public void actualizarMensaje() {
        System.out.println("entro");
    	Set<Product> selectedProduct = ((ListModelList<Product>)productsModel).getSelection();
        int size = selectedProduct.size();
        System.out.println("tamano "+size);
        System.out.println();
        selectedProductName = selectedProduct.iterator().next().getproduct_name();
        selectedProductId = ""+selectedProduct.iterator().next().getProduct_id();
        showNotify(size > 0 ? size + " product selected: " + selectedProductName : "no product selected", win);
        session.setAttribute("id_producto", "1522");
        System.out.println(session.getAttribute("id_producto"));
    }
	
	@Listen("onClick = #btn_del_product")
	public void onClickDel() {
		id_producto = selectedProductId;
		System.out.println("entro eliminar");
		final HttpRequest requestPost = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8888/hulkStore/DeleteProduct?product_id="+id_producto)).build();
		Executions.sendRedirect("");
		try {
			final HttpResponse<String> response = httpClient.send(requestPost, HttpResponse.BodyHandlers.ofString());	
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	/*@Listen("onClick = #btn_upd_product")
	public void onClickUpd() {
		String product_id = selectedProductId;
		System.out.println("entro eliminar");
		final HttpRequest requestPost = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8888/hulkStore/UpdateProduct?product_name="+product_name+"&category_id="+category_id+"&stock="+stock+"&product_id="+product_id)).build();
		Executions.sendRedirect("");
		try {
			final HttpResponse<String> response = httpClient.send(requestPost, HttpResponse.BodyHandlers.ofString());	
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }*/
	
	private void cargarProductos() {
		final HttpRequest requestPost = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8888/hulkStore/GetProducts")).build();
		try {
			Listitem item,item1;
			Listcell cell;
			List<Product> list_product = new ArrayList<Product>();
			Product producto;
			final HttpResponse<String> response = httpClient.send(requestPost, HttpResponse.BodyHandlers.ofString());
			System.out.println(response.body());
			ObjRespProd=gson.fromJson(response.body(), ObjetoRespProduct.class);
			System.out.println(ObjRespProd.product_name.get(0));
			System.out.println(ObjRespProd.product_name.get(1));
			int c=0;
			for (int product_id : ObjRespProd.product_id) {
				item = new Listitem();
				item.setValue(null);
				cell = new Listcell();
				cell.setParent(item);
				cell.setLabel(Integer.toString(product_id));
				System.out.println(product_id);
				cell = new Listcell();
				cell.setParent(item);
				cell.setLabel(ObjRespProd.product_name.get(c));
				cell = new Listcell();
				cell.setParent(item);
				cell.setLabel(Integer.toString(ObjRespProd.category_id.get(c)));
				cell = new Listcell();
				cell.setParent(item);
				cell.setLabel(Integer.toString(ObjRespProd.stock.get(c)));			
				producto = new Product(product_id,ObjRespProd.product_name.get(c),ObjRespProd.category_id.get(c),ObjRespProd.stock.get(c));
				list_product.add(producto);
				c++;
			}				
			
			productsModel = new ListModelList<Product>(list_product);			
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
