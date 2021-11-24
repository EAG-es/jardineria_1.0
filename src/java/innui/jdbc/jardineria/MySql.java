/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package innui.jdbc.jardineria;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author informatica
 */
public class MySql {
    public Connection connection = null;
    public static String jdbc_uri = "jdbc:mysql://localhost:3306/jardineria?zeroDateTimeBehavior=CONVERT_TO_NULL";
    public static String usuario = "jardineria";
    public static String clave = "2021jardineria";
    
    public boolean conectar(String uri, String usuario, String clave, String [] error) {
        boolean ret = true;
        try {
            if (connection == null) {
                // Instalar la librería con los drivers:
                // /usr/share/java/mysql-connector-java-8.0.26.jar
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(uri, usuario, clave);
            }
        } catch (Exception e) {
            error[0] = e.getMessage();
            if (error[0] == null) {
                error[0] = ""; //NOI18N
            }
            error[0] = "Error al conectar en MySql. "
                    + error[0];
        }
        return ret;
    }
    
    public boolean desconectar(String [] error) {
        boolean ret = true;
        connection = null;
        return ret;
    }
}
