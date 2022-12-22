package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
   protected Connection conexion;
   //JDBC driver nombre y base de datos URL - oracle.jdbc.driver.OracleDriver
   private final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
   private final String DB_URL = "jdbc:oracle:thin:@//129.153.214.148/pdbeedev1.eesubnet.eevcn.oraclevcn.com";
   //credenciales base de datos
   private final String USER = "CVCIMPDEV";
   private final String PASS = "CVCIMPDEV";

   public void conectar() throws Exception{
      try {
         Connection conexion = DriverManager.getConnection(DB_URL,USER, PASS);
         Class.forName(JDBC_DRIVER);
      }catch (Exception e){
         throw e;
      }
   }

   public void cerrar() throws SQLException{
      if (conexion != null){
         if (!conexion.isClosed()){
            conexion.close();
         }
      }
   }

}
