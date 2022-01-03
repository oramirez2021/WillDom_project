package com.hulkStore_ms.ws;

import java.io.*;
import java.net.*;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import javax.xml.parsers.*;

//import org.jboss.jandex.Main;
import org.slf4j.*;
import org.w3c.dom.*;
import org.xml.sax.*;

//import com.conecel.beans.*;
//MVC
import com.hulkStore_ms.model.*;
import com.hulkStore_ms.DBCon.*;
import com.hulkStore_ms.model.ObjetoRespProduct;
import com.google.gson.*;

//Connection
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

//@Service
public class ObjetoService {
	ObjetoRespProduct ObjRespProd = new ObjetoRespProduct();	
	ResultSet resultado;
	Properties p = null;
	SimpleDateFormat fecha_formato = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat fecha_formato_time = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	Gson gson = new GsonBuilder().serializeNulls().create();
	private static final Logger log = LoggerFactory.getLogger(ObjetoService.class);
	File log_file;
	File path = new File("");
	FileWriter fichero;
	PrintWriter pw;
	int exito = 1;// controla el exito o fracaso de la ejecucion del servicio
	int ctrlSerCont = 0;// controla el exito o fracaso de la ejecucion del servicio
	int ctrlSerMax = 1;// numero de servicios que seran controlados. Valor 1 debido a que se debe
						// ejecutar el servicio tcpGateway
	String current_date;
	HttpURLConnection conexion = null;
	URL url = null;
	StringBuilder output;

	public ObjetoService(ObjetoReq objReq) {
		System.out.println(objReq);
	}

	public ObjetoService() {
		
	}
	public void getProducts() {
		//Product ObjProd = null;
		// Instancias la clase que hemos creado anteriormente
		DBConnection MySql = new DBConnection();
		// Llamas al método que tiene la clase y te devuelve una conexión
		Connection conn = MySql.mySQLConnect();
		// Query que usarás para hacer lo que necesites
		String query = "select product_id,product_name,category_id,stock from PRODUCT";
		// Statement
		try {
            Statement sentencia=conn.createStatement();
            resultado=sentencia.executeQuery(query);
            while (resultado.next()) {
            	ObjRespProd.product_id.add(resultado.getInt("product_id"));
            	ObjRespProd.product_name.add(resultado.getString("product_name"));
            	ObjRespProd.category_id.add(resultado.getInt("category_id"));
            	ObjRespProd.stock.add(resultado.getInt("stock"));
            }
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        }

	}
	
	public void insertProduct(String product_name, int category_id, int stock) {
		//Product ObjProd = null;
		// Instancias la clase que hemos creado anteriormente
		DBConnection MySql = new DBConnection();
		// Llamas al método que tiene la clase y te devuelve una conexión
		Connection conn = MySql.mySQLConnect();
		// Query que usarás para hacer lo que necesites
		String comando = "insert into PRODUCT(product_name,category_id,stock) values (?,?,?)";
		// Statement
		try {
            PreparedStatement sentencia=conn.prepareStatement(comando);
            sentencia.setString(1,product_name);
            sentencia.setInt(2, category_id);
            sentencia.setInt(3, stock);
            sentencia.executeUpdate();
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        }

	}

	public void deleteProduct(int product_id) {
		//Product ObjProd = null;
		// Instancias la clase que hemos creado anteriormente
		DBConnection MySql = new DBConnection();
		// Llamas al método que tiene la clase y te devuelve una conexión
		Connection conn = MySql.mySQLConnect();
		// Query que usarás para hacer lo que necesites
		String comando = "delete from  PRODUCT where product_id = ?";
		// Statement
		try {
            PreparedStatement sentencia=conn.prepareStatement(comando);
            sentencia.setInt(1,product_id);
            sentencia.executeUpdate();
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        }

	}
	
	public void updateProduct(String product_name, int category_id, int stock, int product_id) {
		//Product ObjProd = null;
		// Instancias la clase que hemos creado anteriormente
		DBConnection MySql = new DBConnection();
		// Llamas al método que tiene la clase y te devuelve una conexión
		Connection conn = MySql.mySQLConnect();
		// Query que usarás para hacer lo que necesites
		String comando = "update PRODUCT set product_name = ?, category_id = ?, stock = ? where product_id = ?";
		// Statement
		try {
            PreparedStatement sentencia=conn.prepareStatement(comando);
            sentencia.setString(1,product_name);
            sentencia.setInt(2,category_id);
            sentencia.setInt(3,stock);
            sentencia.setInt(4,product_id);
            sentencia.executeUpdate();
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        }

	}
	
	public void insertKardex(int product_id, String ope_type, int cant) {
		//Product ObjProd = null;
		// Instancias la clase que hemos creado anteriormente
		DBConnection MySql = new DBConnection();
		// Llamas al método que tiene la clase y te devuelve una conexión
		Connection conn = MySql.mySQLConnect();
		// Query que usarás para hacer lo que necesites
		String comando = "insert into KARDEX(product_id,transaction_date_time, ope_type, cant) values (?,now(),?,?)";
		// Statement
		try {
            PreparedStatement sentencia=conn.prepareStatement(comando);
            sentencia.setInt(1,product_id);
            sentencia.setString(2, ope_type);
            sentencia.setInt(3,cant);
            sentencia.executeUpdate();
        } catch (SQLException e) {
            
            e.printStackTrace();
        }

	}
	
	/*
	 * Validaciones basicas del servicio -1 servicio esta vacio, 1 formato invalido,
	 * 0 formato correcto
	 */
	public int valida_servicio(String servicio2) {
		int len = servicio2.length();
		servicio2 = null;
		if (len == 0) {
			return -1;
		}
		if (len != 12) {
			return 1;
		} else {
			return 0;
		}

	}

	// Lo que devuelve el servicio
	/*
	 * public ObjetoRes getObjeto() { return ObjRes; }
	 */
	public String getObjeto() {
		
		String json = gson.toJson(ObjRespProd);
		return json;
		/*return json.replace("null", "\"\"").replace("-1.1", "\"Ilimitado\"").replace("-2.2", "\"Ilimitado\"")
				.replace("-3.3", "\"Ilimitado\"");*/
	}
}
