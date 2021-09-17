package com.hulkStore_project.controllers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;

public class IndexController extends Controller{
	private Label menu_1;
	private Button btn_ver_productos;
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		menu_1.setValue("Hola Omar");
	}
	
	public void onClickBtn_ver_productos(Event eve) {
		menu_1.setValue("Ver Productos");
		
		btn_ver_productos.setStyle("color:blue");
	}
}
