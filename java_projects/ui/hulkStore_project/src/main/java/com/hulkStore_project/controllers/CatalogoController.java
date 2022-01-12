package com.hulkStore_project.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

import com.hulkStore_project.model.Category;
import com.hulkStore_project.model.ObjetoRespProduct;
import com.hulkStore_project.model.Product;

public class CatalogoController extends SelectorComposer<Component>{
	private ListModel<Category> categoriesModel;
	String selectedCategoryName, selectedCategoryId, selectedImagePath;
	Session session = Sessions.getCurrent();
	public ListModel<Category> getCategoriesModel() {
        return categoriesModel;
    }
	
	@Listen("onSelect = listbox")
	public void actualizarMensaje() {
        System.out.println("entro");
    	Set<Category> selectedCategory = ((ListModelList<Category>)categoriesModel).getSelection();
        int size = selectedCategory.size();
        System.out.println("tamano "+size);
        System.out.println();
        selectedCategoryName = selectedCategory.iterator().next().getCategory_name();
        selectedCategoryId = ""+selectedCategory.iterator().next().getCategory_id();
        selectedImagePath = ""+selectedCategory.iterator().next().getImage_path();
        //se crean variables de session para que almacenen los valores correspondientes al producto seleccionado
        session.setAttribute("id_category", selectedCategoryId);
        session.setAttribute("nombre_categoria", selectedCategoryName);
        session.setAttribute("ruta_imagen", selectedImagePath);
        System.out.println(selectedCategoryName);
        System.out.println("salio");
    }
	private void cargarCategorias() {
		final HttpRequest requestPost = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8888/hulkStore/GetCategories")).build();
		try {
			Listitem item,item1;
			Listcell cell;
			List<Category> list_product = new ArrayList<Category>();
			Category category;
			final HttpResponse<String> response = httpClient.send(requestPost, HttpResponse.BodyHandlers.ofString());
			ObjetoRespCategory ObjRespCate=null;
			//System.out.println(response.body());
			ObjRespCate=gson.fromJson(response.body(), ObjetoRespProduct.class);
			//System.out.println(ObjRespProd.product_name.get(0));
			//System.out.println(ObjRespProd.product_name.get(1));
			int c=0;
			for (int product_id : ObjRespProd.product_id) {
				item = new Listitem();
				item.setValue(null);
				cell = new Listcell();
				cell.setParent(item);
				cell.setLabel(Integer.toString(product_id));
				//System.out.println(product_id);
				cell = new Listcell();
				cell.setParent(item);
				cell.setLabel(ObjRespProd.product_name.get(c));
				cell = new Listcell();
				cell.setParent(item);
				cell.setLabel(Integer.toString(ObjRespProd.category_id.get(c)));
				cell = new Listcell();
				cell.setParent(item);
				cell.setLabel(Integer.toString(ObjRespProd.stock.get(c)));			
				category = new Product(product_id,ObjRespProd.product_name.get(c),ObjRespProd.category_id.get(c),ObjRespProd.stock.get(c));
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
