package com.gm.hxOracle.jdbc;

import java.sql.*;
import java.util.*;
import javax.sql.*;
import org.apache.commons.dbcp2.BasicDataSource;






public class ResourceManager
{
    private static String JDBC_DRIVER ;
    private static String JDBC_URL ;

    private static String JDBC_USER  ;
    private static String JDBC_PASSWORD ;

    private static Driver driver = null;
    private static String JDBC_FILE_NAME ="jdbc";
    
    
    public static Properties loadProperties(String file){
        Properties prop = new Properties();
        ResourceBundle bundle = ResourceBundle.getBundle(file);
        Enumeration e = bundle.getKeys();
        String key = null;
        while (e.hasMoreElements()) {
            key = (String)e.nextElement();
            prop.put(key,bundle.getObject(key));
        }
        JDBC_DRIVER = prop.getProperty("driver");
        JDBC_URL = prop.getProperty("url");
        JDBC_USER = prop.getProperty("user");
        JDBC_PASSWORD = prop.getProperty("pass");
        return prop;
    }

   public static DataSource getDataSource(){
       BasicDataSource ds = new BasicDataSource();
       ds.setDriverClassName(JDBC_DRIVER);
       System.out.println(JDBC_DRIVER);
       ds.setUrl(JDBC_URL);
       ds.setUsername(JDBC_USER);
       ds.setPassword(JDBC_PASSWORD);
       ds.setInitialSize(5);
       return (DataSource) ds;
       
   }
    public static synchronized Connection getConnection() throws SQLException{
        
        if (driver ==null) {
            try {
                //cargamos las propiedades de conexion a la bd
                loadProperties(JDBC_FILE_NAME);
                
                // SE REGISTRA EL DRIVER
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);
            } catch (Exception e) {
                System.out.println("fallo al cargar el driver JDBC");
                e.printStackTrace();
            }
            
            
        }
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

	public static void close(Connection conn)
	{
		try {
			if (conn != null) conn.close();
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
	}

	public static void close(PreparedStatement stmt)
	{
		try {
			if (stmt != null) stmt.close();
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
	}

	public static void close(ResultSet rs)
	{
		try {
			if (rs != null) rs.close();
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}

	}

}
