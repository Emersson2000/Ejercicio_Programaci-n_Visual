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
    private String columnas[] = {"idInventario", "Cógigo_Pro", "Descripción", "Can Productos", "Sin Iva", "Con Iva", "Mayorista", "Fijo", "Normal"};
    private Utilidades utilidades;

    public Controlador_BD_Inventario() {
        utilidades = new Utilidades();
    }

    public DefaultTableModel mostrarInventarioPorDato(String nombre, String buscar) {
        DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas);
        String lista[] = new String[9];
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
                lista[6] = res.getString(7);
                lista[7] = res.getString(8);
                lista[8] = res.getString(9);
                modeloTabla.addRow(lista);
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        return modeloTabla;
    }

    public boolean ingresarInventario(Inventario inventario) {
        boolean reguistrar = false;
        Connection con = null;
        Statement stm = null;
        String sql;
        if (inventario.getFecha_Caducidad() == null) {
            sql = "INSERT INTO `cliente`.`inventario` ( `codigo_Pro`, `descripcion`, `can_Productos`, `precio_Compra_Sin_Iva`, `precio_Compra_Con_Iva`, `precio_Mayorista`, `precio_Cliente_Fijo`, `precio_Cliente_Normal`, `fecha_Registro`) VALUES ('" + inventario.getCodigo_Pro() + "', '" + inventario.getDescripcion() + "', '" + inventario.getCan_Productos() + "', '" + inventario.getPrecio_CompraSinIva() + "', '" + inventario.getPrecio_CompraConIva() + "', '" + inventario.getPrecio_Mayorista() + "', '" + inventario.getPrecio_ClienteFijo() + "', '" + inventario.getPrecio_ClienteNormal() + "', '" + utilidades.fechaRegistro(inventario.getFecha_Registro()) + "')";
        } else {
            sql = "INSERT INTO `cliente`.`inventario` (`codigo_Pro`, `descripcion`, `can_Productos`, `precio_Compra_Sin_Iva`, `precio_Compra_Con_Iva`, `precio_Mayorista`, `precio_Cliente_Fijo`, `precio_Cliente_Normal`, `fecha_Caducidad`, `fecha_Registro`) VALUES ('" + inventario.getCodigo_Pro() + "', '" + inventario.getDescripcion() + "', '" + inventario.getCan_Productos() + "', '" + inventario.getPrecio_CompraSinIva() + "', '" + inventario.getPrecio_CompraConIva() + "', '" + inventario.getPrecio_Mayorista() + "', '" + inventario.getPrecio_ClienteFijo() + "', '" + inventario.getPrecio_ClienteNormal() + "', '" + utilidades.fechaRegistro(inventario.getFecha_Caducidad()) + "', '" + utilidades.fechaRegistro(inventario.getFecha_Registro()) + "');";

        }
        try {
            con = conexion.Conexion_Mysql();
            stm = con.createStatement();
            stm.execute(sql);
            reguistrar = true;
            stm.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error : " + e);
            e.printStackTrace();
        }
        return reguistrar;
    }

    public Inventario obtenerInventario(String nombre) {
        Inventario obtenerInventario = null;

        String sql = "SELECT * FROM inventario WHERE idInventario = '" + nombre + "'";

        Statement datos;
        try {
            datos = con.createStatement();
            ResultSet res = datos.executeQuery(sql);

            while (res.next()) {
                obtenerInventario = new Inventario();
                obtenerInventario.setIdInventario(res.getInt(1));
                obtenerInventario.setCodigo_Pro(res.getString(2));
                obtenerInventario.setDescripcion(res.getString(3));
                obtenerInventario.setCan_Productos(res.getInt(4));
                obtenerInventario.setPrecio_CompraSinIva(res.getDouble(5));
                obtenerInventario.setPrecio_CompraConIva(res.getDouble(6));
                obtenerInventario.setPrecio_Mayorista(res.getDouble(7));
                obtenerInventario.setPrecio_ClienteFijo(res.getDouble(8));
                obtenerInventario.setPrecio_ClienteNormal(res.getDouble(9));
                obtenerInventario.setFecha_Caducidad(res.getDate(10));
                obtenerInventario.setFecha_Registro(res.getDate(11));
                obtenerInventario.setFecha_Actualizacion(res.getDate(12));

            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        return obtenerInventario;

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
        PreparedStatement pps;
        try {
            if (inventario.getFecha_Caducidad() != null) {
                pps = con.prepareStatement("UPDATE `cliente`.`inventario` SET `codigo_Pro` = '" + inventario.getCodigo_Pro() + "', `descripcion` = '" + inventario.getDescripcion() + "', `can_Productos` = '" + inventario.getCan_Productos() + "', `precio_Compra_Sin_Iva` = '" + inventario.getPrecio_CompraSinIva() + "', `precio_Compra_Con_Iva` = '" + inventario.getPrecio_CompraConIva() + "', `precio_Mayorista` = '" + inventario.getPrecio_Mayorista() + "', `precio_Cliente_Fijo` = '" + inventario.getPrecio_ClienteFijo() + "', `precio_Cliente_Normal` = '" + inventario.getPrecio_ClienteNormal() + "', `fecha_Caducidad` = '" + utilidades.fechaRegistro(inventario.getFecha_Caducidad()) + "', `fecha_Actualizacion` = '" + utilidades.fechaRegistro(inventario.getFecha_Actualizacion()) + "' WHERE (`idInventario` = '" + inventario.getIdInventario() + "')");

            } else {
                pps = con.prepareStatement("UPDATE `cliente`.`inventario` SET `codigo_Pro` = '" + inventario.getCodigo_Pro() + "', `descripcion` = '" + inventario.getDescripcion() + "', `can_Productos` = '" + inventario.getCan_Productos() + "', `precio_Compra_Sin_Iva` = '" + inventario.getPrecio_CompraSinIva() + "', `precio_Compra_Con_Iva` = '" + inventario.getPrecio_CompraConIva() + "', `precio_Mayorista` = '" + inventario.getPrecio_Mayorista() + "', `precio_Cliente_Fijo` = '" + inventario.getPrecio_ClienteFijo() + "', `precio_Cliente_Normal` = '" + inventario.getPrecio_ClienteNormal() + "', `fecha_Actualizacion` = '" + utilidades.fechaRegistro(inventario.getFecha_Actualizacion()) + "' WHERE (`idInventario` = '" + inventario.getIdInventario() + "')");
            }
            pps.executeUpdate();
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
