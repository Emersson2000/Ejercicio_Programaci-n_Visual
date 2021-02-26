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
public class Controlador_BD_Proveedor {

    private Conexion_Base_De_Datos conexion = new Conexion_Base_De_Datos();
    private Connection con = conexion.Conexion_Mysql();
    Proveedor persona = new Proveedor();
    private String columnas[] = {"idProveedor", "RUC", "Razón-Social", "Actividad", "Nombre", "Apellido", "Teléfono", "Correo"};

    public DefaultTableModel mostrarProveedorPorDato(String nombre, String buscar) {
        DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas);
        String lista[] = new String[8];
        String sql = "SELECT * FROM proveedor WHERE " + nombre + " LIKE '%" + buscar + "%'";
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
                modeloTabla.addRow(lista);
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        return modeloTabla;
    }

    public boolean ingresarProveedor(Proveedor proveedor) {
        boolean reguistrar = false;
        try {
            CallableStatement sql = con.prepareCall("{call cliente.ingresarProveedor('" + proveedor.getRuc() + "', '" + proveedor.getRazon_Social() + "', '" + proveedor.getTipo_Actividad() + "', '" + proveedor.getNom_Representante_Legal() + "', '" + proveedor.getApell_Representante_Legal() + "', '" + proveedor.getTelefono() + "', '" + proveedor.getCorreo() + "')}");
            sql.execute();
            reguistrar = true;
            sql.close();
        } catch (Exception e) {
            System.out.println("Error : " + e);
            e.printStackTrace();
        }
        return reguistrar;
    }

    public boolean eliminarCliente(String id) {
        boolean eliminar = false;
        PreparedStatement pps;
        try {
            pps = con.prepareStatement("DELETE FROM proveedor WHERE idProveedor = '" + id + "'");
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Dato eliminado");
            eliminar = true;
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        return eliminar;
    }
    
        public boolean editarProveedor(Proveedor proveedor) {
        boolean eliminar = false;

        try {
            CallableStatement sql = con.prepareCall("{call cliente.actualizarProveedor('" + proveedor.getRuc() + "', '" + proveedor.getRazon_Social() + "', '" + proveedor.getTipo_Actividad() + "', '" + proveedor.getNom_Representante_Legal() + "', '" + proveedor.getApell_Representante_Legal() + "', '" + proveedor.getTelefono() + "', '" + proveedor.getCorreo() + "', '" + String.valueOf(proveedor.getIdProveedor()) + "')}");
            sql.execute();
            JOptionPane.showMessageDialog(null, "Persona Editada");
            eliminar = true;
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        return eliminar;
    }

    public void numeros(JTextField texto) {
        texto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                if (texto.getText().length() >= 13) {
                    ke.consume();
                }

                char c = ke.getKeyChar();
                if (!Character.isDigit(c)) {
                    ke.consume();
                }
            }
        });

    }

}
