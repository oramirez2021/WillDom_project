package com.hulkStore_ms.controller;

import java.security.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;

//MVC
import com.hulkStore_ms.model.*;
import com.hulkStore_ms.ws.ObjetoService;
import com.google.gson.Gson;

@CrossOrigin
@RestController
//@RequestMapping("/BusinessAgnosticService/ServicePackage")
@RequestMapping("/hulkStore")
public class ObjetoController {
	private static final Logger log = LoggerFactory.getLogger(ObjetoService.class);
	//@RequestMapping(method = RequestMethod.POST, path = "/Query", consumes = "application/json", produces = "application/json")
	@RequestMapping(method = RequestMethod.GET, path = "/GetProducts", produces = "application/json")
	public @ResponseBody String getObjeto() {
		ObjetoService objService=null;
		//System.out.println(inputJson);
		try {
			//log.info(inputJson);
			//ObjetoReq mp = new Gson().fromJson(inputJson, ObjetoReq.class);
			objService = new ObjetoService();
			objService.getProducts();
		} catch (Exception ex) {
			log.info(ex.toString());
		}
		return objService.getObjeto();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/InsertProduct", produces = "application/json")
	public @ResponseBody String insertProduct(String product_name, int category_id, int stock) {
		ObjetoService objService=null;
		//System.out.println(inputJson);
		try {
			//log.info(inputJson);
			//ObjetoReq mp = new Gson().fromJson(inputJson, ObjetoReq.class);
			objService = new ObjetoService();
			objService.insertProduct(product_name, category_id, stock);
		} catch (Exception ex) {
			log.info(ex.toString());
		}
		return objService.getObjeto();
	}
	@RequestMapping(method = RequestMethod.GET, path = "/DeleteProduct", produces = "application/json")
	public @ResponseBody String deleteProduct(int product_id) {
		ObjetoService objService=null;
		//System.out.println(inputJson);
		try {
			//log.info(inputJson);
			//ObjetoReq mp = new Gson().fromJson(inputJson, ObjetoReq.class);
			objService = new ObjetoService();
			objService.deleteProduct(product_id);
		} catch (Exception ex) {
			log.info(ex.toString());
		}
		return objService.getObjeto();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/UpdateProduct", produces = "application/json")
	public @ResponseBody String updateProduct(String product_name, int category_id, int stock, int product_id) {
		ObjetoService objService=null;
		//System.out.println(inputJson);
		try {
			objService = new ObjetoService();
			objService.updateProduct(product_name, category_id, stock, product_id);
		} catch (Exception ex) {
			log.info(ex.toString());
		}
		return objService.getObjeto();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/InsertKardex", produces = "application/json")
	public @ResponseBody String insertKardex(int product_id, Date transaction_date, Timestamp transaction_time,String ope_type, int cant ) {
		ObjetoService objService=null;
		//System.out.println(inputJson);
		try {
			objService = new ObjetoService();
			objService.insertKardex(product_id, transaction_date, transaction_time, ope_type, cant);
		} catch (Exception ex) {
			log.info(ex.toString());
		}
		return objService.getObjeto();
	}
}
