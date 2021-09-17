package com.hulkStore_project.controllers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Button;

public class ProductControllerCRUD extends Controller {
	Button btn_save_product;
@Override
public void doAfterCompose(Component comp) throws Exception {
	// TODO Auto-generated method stub
	super.doAfterCompose(comp);
	btn_save_product.setLabel("Salvar");
}
}
