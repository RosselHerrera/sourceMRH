package planeacion.persistencia;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConexionP {
	
	public Connection getConection() throws Exception {	
		Connection conexDB = null;
		Properties props = new Properties();
		String url = null;
		String userID = null;
		String password = null;
		String className = null;
		try {
			InputStream inputStream = getClass().getResourceAsStream("/db.properties"); 
			props.load(inputStream);
			className = props.getProperty("class");
			url = props.getProperty("url");
			userID = props.getProperty("user");
			password = props.getProperty("password");
			/**---------------------------------------- */     
			Class.forName(className);
			conexDB = DriverManager.getConnection(url, userID, password);  
			} catch( Exception e ) {
				e.printStackTrace();
			}
		return conexDB;	
	}
}
