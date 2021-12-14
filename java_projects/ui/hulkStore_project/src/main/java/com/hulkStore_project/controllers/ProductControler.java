package com.hulkStore_project.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.util.Set;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
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
import com.hulkStore_project.model.ObjetoRespProduct;
import com.hulkStore_project.model.Product;


public class ProductControler extends Controller{
	//private Label menu_1;
	private Button btn_ver_productos;
	private Listbox lbl_products;
	Button btn_add_product;
    private ListModel<Product> productModel;
    @Wire
    private Window win;
	//Consumo rest
	private HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
	ObjetoRespProduct ObjRespProd=null;
	Gson gson = new GsonBuilder().serializeNulls().create();
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		//menu_1.setValue("Hola Omar");
		btn_add_product.setLabel("Add");
		cargarProductos();
	}
	private void showNotify(String msg,Component ref){
        Clients.showNotification(msg,"info",ref,"top_right",2000);
    }
	
	public ListModel<Product> getProductModel() {
        return productModel;
    }
	
	@Listen("onSelect = listbox")
	public void actualizarMensaje() {
        Set<Product> selectedProduct = ((ListModelList<Product>)productModel).getSelection();
        int size = selectedProduct.size();
        System.out.println("tamano "+size);
        showNotify(size > 0 ? size + " product selected: " + selectedProduct : "no product selected", win);
    }
	
	private void cargarProductos() {
		final HttpRequest requestPost = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8888/hulkStore/GetProducts")).build();
		try {
			Listitem item,item1;
			Listcell cell;
			final HttpResponse<String> response = httpClient.send(requestPost, HttpResponse.BodyHandlers.ofString());
			System.out.println(response.body());
			
			ObjRespProd=gson.fromJson(response.body(), ObjetoRespProduct.class);
			System.out.println(ObjRespProd.product_name.get(0));
			System.out.println(ObjRespProd.product_name.get(1));
			int c=0;
			for (int product_id : ObjRespProd.product_id) {
				item = new Listitem();
				item.setParent(lbl_products);
				item.setValue(null);
				cell = new Listcell();
				cell.setParent(item);
				cell.setLabel(Integer.toString(product_id));
				System.out.println(product_id);
				cell = new Listcell();
				cell.setParent(item);
				cell.setLabel(ObjRespProd.product_name.get(c));
				//System.out.println(product_id);
				cell = new Listcell();
				cell.setParent(item);
				cell.setLabel(Integer.toString(ObjRespProd.category_id.get(c)));
				cell = new Listcell();
				cell.setParent(item);
				cell.setLabel(Integer.toString(ObjRespProd.stock.get(c)));
				c++;
			}
			
			lbl_products.invalidate();
			
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
