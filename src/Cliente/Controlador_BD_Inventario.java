package Cliente;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * @author Emersson
 */
public class Controlador_BD_Inventario {

    private Conexion_Base_De_Datos conexion = new Conexion_Base_De_Datos();
    private Connection con = conexion.Conexion_Mysql();
    Proveedor persona = new Proveedor();
    private String columnas[] = {"idInventario", "Cógigo_Pro", "Descripción", "Precio Compra", "Precio Venta", "Can Productos"};

    public DefaultTableModel mostrarInventarioPorDato(String nombre, String buscar) {
        DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas);
        String lista[] = new String[6];
        String sql = "SELECT * FROM inventario WHERE " + nombre + " LIKE '%" + buscar + "%'";
        // SELECT * FROM persona WHERE id LIKE '%"buscar"%' OR nombre '%"buscar"%'";
        Statement datos;
        try {
            datos = con.createStatement();
            ResultSet res = datos.executeQuery(sql);

            while (res.next()) {
                lista[0] = res.getString(1);
                lista[1] = res.getString(2);
                lista[2] = res.getString(3);
                lista[3] = res.getString(4);
                lista[4] = res.getString(5);
                lista[5] = res.getString(6);
                modeloTabla.addRow(lista);
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        return modeloTabla;
    }

    public boolean ingresarInventario(Inventario inventario) {
        boolean reguistrar = false;
        try {
            CallableStatement sql = con.prepareCall("{call cliente.IngresarInventario('" + inventario.getCodigo_Pro() + "', '" + inventario.getDescripcion() + "', '" + inventario.getPrecio_Compra() + "', '" + inventario.getPrecio_Venta() + "', '" + inventario.getCan_Productos() + "')}");
            sql.execute();
            reguistrar = true;
            sql.close();
        } catch (Exception e) {
            System.out.println("Error : " + e);
            e.printStackTrace();
        }
        return reguistrar;
    }

    public boolean eliminarInventario(int id) {
        boolean eliminar = false;
        PreparedStatement pps;
        try {
            pps = con.prepareStatement("DELETE FROM inventario WHERE idInventario = '" + String.valueOf(id) + "'");
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Dato eliminado");
            eliminar = true;
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        return eliminar;
    }

    public boolean editarInventario(Inventario inventario) {
        boolean eliminar = false;

        try {
            CallableStatement sql = con.prepareCall("{call cliente.actualiazarInventario('" + inventario.getIdInventario() + "', '" + inventario.getCodigo_Pro() + "', '" + inventario.getDescripcion() + "', '" + inventario.getPrecio_Compra() + "', '" + inventario.getPrecio_Venta() + "', '" + inventario.getCan_Productos() + "')}");
            sql.execute();
            JOptionPane.showMessageDialog(null, "Persona Editada");
            eliminar = true;
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        return eliminar;
    }

    public void soloNumeros(JTextField texto) {
        texto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();  // Obtenemos el carácter que ingresa el usuario
                if (!Character.isDigit(c)) {
                    ke.consume();
                }
            }
        });
    }
}
