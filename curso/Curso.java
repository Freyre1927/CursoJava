package cursoSE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CursoSE {

   public String solicitarPais(){
      Scanner sc = new Scanner(System.in);
      System.out.println("Ingrese nombre del pais:");
      String pais = sc.next();
      return pais;
   }

   public Integer solicitarCodePais(){
      Scanner sc = new Scanner(System.in);
      System.out.println("Ingrese Codigo del pais:");
      Integer code = sc.nextInt();
      return code;
   }
   public void registrarBD(Integer code, String pais){
      //JDBC driver nombre y base de datos URL - oracle.jdbc.driver.OracleDriver
      final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
      final String DB_URL = "jdbc:oracle:thin:@//129.153.214.148/pdbeedev1.eesubnet.eevcn.oraclevcn.com";
      //credenciales base de datos
      final String USER = "CVCIMPDEV";
      final String PASS = "CVCIMPDEV";

      try (Connection conexion = DriverManager.getConnection(DB_URL,USER, PASS) ){
         Class.forName(JDBC_DRIVER);

         PreparedStatement st = conexion.prepareStatement("INSERT INTO REGIONES VALUES(?,?)");
         st.setInt(1, code);
         st.setString(2, pais);

         st.executeUpdate();
         st.close();
      }catch (Exception e){
         e.getMessage();
      }
   }

   public List<Regiones> listar(){
      List<Regiones> lista = null;
      //JDBC driver nombre y base de datos URL - oracle.jdbc.driver.OracleDriver
      final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
      final String DB_URL = "jdbc:oracle:thin:@//129.153.214.148/pdbeedev1.eesubnet.eevcn.oraclevcn.com";
      //credenciales base de datos
      final String USER = "CVCIMPDEV";
      final String PASS = "CVCIMPDEV";

      try (Connection conexion = DriverManager.getConnection(DB_URL,USER, PASS) ){
         Class.forName(JDBC_DRIVER);

         PreparedStatement st = conexion.prepareStatement("SELECT * FROM REGIONES");

         lista = new ArrayList<>();
         ResultSet rs = st.executeQuery();
         while(rs.next()){
            Regiones reg = new Regiones();
            reg.setRegion_id(rs.getInt("REGION_ID"));
            reg.setRegion_name(rs.getString("REGION_NAME"));
            lista.add(reg);
         }
         rs.close();
         st.close();//en teoria cuando se cierra el stament automaticamente se cierra el rg
      }catch (Exception e){
         e.getMessage();
      }
      return lista;
   }

   public static void main(String[] args)  {
      CursoSE curso = new CursoSE();
      Integer code = curso.solicitarCodePais();
      String pais = curso.solicitarPais();
      if (pais != null && code != null){
         System.out.println("Antes de registrar BD");
         curso.registrarBD(code, pais);
         System.out.println("Después de registrar BD");
      }
      //List<cursoSE.Regiones> listaBD = curso.listar(); se declara en el for mejorado
      for (Regiones reg : curso.listar()){
         System.out.println(reg.getRegion_id() + " - " + reg.getRegion_name());
      }
   }


}
