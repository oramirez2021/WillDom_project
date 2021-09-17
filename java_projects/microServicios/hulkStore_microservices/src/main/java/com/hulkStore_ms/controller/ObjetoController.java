package com.hulkStore_ms.controller;

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
}
