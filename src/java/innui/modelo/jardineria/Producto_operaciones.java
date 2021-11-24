/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package innui.modelo.jardineria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author informatica
 */
public class Producto_operaciones {
    public int pagina_tam = 0;
    public Connection connection = null;
    /**
     * Devuelve una lista con las filas de la tabla productos 
     * @param error En la posición 0, mensaje de error, si lo hay. 
     * @return null si hay error, una lista de productos si no hay error
     */
    public List<Producto> listar(String [] error) {
        boolean ret = true;
        List lista = null;
        String sql_comando;
        Producto producto;
        PreparedStatement preparedStatement;
        try {
            sql_comando = "select "
                + Producto.codigo_producto_tex + ", "
                + Producto.nombre_tex + ", "
                + Producto.gama_tex + ", "
                + Producto.dimensiones_tex + ", "
                + Producto.proveedor_tex + ", "
                + Producto.descripcion_tex + ", "
                + Producto.cantidad_en_stock_tex + ", "
                + Producto.precio_venta_tex + ", "
                + Producto.precio_proveedor_tex + " "
                + "from producto";
            preparedStatement = connection.prepareStatement(sql_comando);
            ResultSet resultSet = preparedStatement.executeQuery();
            lista = new LinkedList();
            while (resultSet.next() ) {
                producto = new Producto();
                producto.codigo_producto = resultSet.getString("codigo_producto");
                producto.nombre = resultSet.getString("nombre");
                producto.descripcion = resultSet.getString("descripcion");
                producto.proveedor  = resultSet.getString("proveedor");
                producto.descripcion = resultSet.getString("descripcion");
                producto.cantidad_en_stock = resultSet.getInt("cantidad_en_stock");
                producto.precio_venta = resultSet.getDouble("precio_venta");
                producto.precio_proveedor = resultSet.getDouble("precio_proveedor");
                lista.add(producto);
            }
        } catch (Exception e) {
            error[0] = e.getMessage();
            if (error[0] == null) {
                error[0] = ""; //NOI18N
            }
            error[0] = "Error al listar productos. "
                    + error[0];
        }
        return lista;
    }
    
    public boolean borrar(String codigo_producto, String [] error) {
        boolean ret = true;
        String sql_comando;
        PreparedStatement preparedStatement;
        try {
            sql_comando = "delete from producto where codigo_producto = ?";
            preparedStatement = connection.prepareStatement(sql_comando);
            preparedStatement.setString(1, codigo_producto);
            int row = preparedStatement.executeUpdate();
            if (row != 1) {
                ret = false;
                error[0] = "Error al borrar producto: " + codigo_producto;
            }
        } catch (Exception e) {
            ret = false;
            error[0] = e.getMessage();
            if (error[0] == null) {
                error[0] = ""; //NOI18N
            }
            error[0] = "Error al borrar producto. "
                    + error[0];
        }
        return ret;
    }

    public boolean crear(Producto producto, String [] error) {
        boolean ret = true;
        String sql_comando;
        PreparedStatement preparedStatement;
        try {
            sql_comando = "insert into producto ("
                + Producto.codigo_producto_tex + ", "
                + Producto.nombre_tex + ", "
                + Producto.gama_tex + ", "
                + Producto.dimensiones_tex + ", "
                + Producto.proveedor_tex + ", "
                + Producto.descripcion_tex + ", "
                + Producto.cantidad_en_stock_tex + ", "
                + Producto.precio_venta_tex + ", "
                + Producto.precio_proveedor_tex + " "
                + ") values ("
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "?)";
            preparedStatement = connection.prepareStatement(sql_comando);
            preparedStatement.setString(1, producto.codigo_producto);
            preparedStatement.setString(2, producto.nombre);
            preparedStatement.setString(3, producto.gama);
            preparedStatement.setString(4, producto.dimensiones);
            preparedStatement.setString(5, producto.proveedor);
            preparedStatement.setString(6, producto.descripcion);
            preparedStatement.setInt(7, producto.cantidad_en_stock);
            preparedStatement.setDouble(8, producto.precio_venta);
            preparedStatement.setDouble(9, producto.precio_proveedor);
            int row = preparedStatement.executeUpdate();
            if (row != 1) {
                ret = false;
                error[0] = "Error al crear el producto: " + producto.codigo_producto;
            }
        } catch (Exception e) {
            ret = false;
            error[0] = e.getMessage();
            if (error[0] == null) {
                error[0] = ""; //NOI18N
            }
            error[0] = "Error al borrar producto. "
                    + error[0];
        }
        return ret;
    }

}
