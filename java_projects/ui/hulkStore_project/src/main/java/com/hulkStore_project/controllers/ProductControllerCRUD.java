package com.hulkStore_project.controllers;



import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;

public class ProductControllerCRUD extends Controller {
	Button btn_save_product;
	Textbox txt_product_name;
	Textbox txt_category_id;
	Textbox txt_stock;
	String product_name;
	int stock;
	int category_id;
	
@Override
public void doAfterCompose(Component comp) throws Exception {
	// TODO Auto-generated method stub
	super.doAfterCompose(comp);
	btn_save_product.setLabel("Salvar");
	
	btn_save_product.addEventListener("onClick", new guardarListener());
}

public class guardarListener implements EventListener {
    public void onEvent(Event event) {
    	product_name = txt_product_name.getValue();
    	category_id = Integer.parseInt(txt_category_id.getValue());
    	stock = Integer.parseInt(txt_stock.getValue());
    	
    }
}

}
