package Cliente;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Controlador_BD_Cliente {

    private Conexion_Base_De_Datos conexion = new Conexion_Base_De_Datos();
    private Connection con = conexion.Conexion_Mysql();
    Persona persona = new Persona();
    private String columnas[] = {"idCliente", "Cédula", "Nombre", "Apellido", "Dirección", "Correo", "Teléfono"};
    Utilidades utilidades;

    public Controlador_BD_Cliente() {
        utilidades = new Utilidades();
    }

    public DefaultTableModel mostrarPersonasPorDato(String nombre, String id) {
        DefaultTableModel modeloTabla = new DefaultTableModel(null, columnas);
        String lista[] = new String[7];
        String sql = "SELECT * FROM persona WHERE " + nombre + " LIKE '%" + id + "%'";
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
//                lista[7] = res.getString(8);
//                lista[8] = res.getString(9);
                modeloTabla.addRow(lista);
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        return modeloTabla;
    }

    public ArrayList<Persona> mostrarPersonasV() {

        String lista[] = new String[7];
        String sql = "SELECT * FROM persona";
        ArrayList<Persona> per = new ArrayList<Persona>();
        Persona p;
        Statement datos;
        try {
            datos = con.createStatement();
            ResultSet res = datos.executeQuery(sql);

            while (res.next()) {
                p = new Persona();
                String id = res.getString(1);
                String cedula = res.getString(2);
                String nombre = res.getString(3);
                String apellido = res.getString(4);
                String direccion = res.getString(5);
                String correo = res.getString(6);
                String telefono = res.getString(7);
                Date registro = res.getDate(8);
                int genero = res.getInt(9);
                Date actualizacion = res.getDate(10);
                Date nacimiento = res.getDate(11);
                p = new Persona(id, cedula, nombre, apellido, direccion, correo, telefono, registro, genero, actualizacion, nacimiento);
                per.add(p);
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        return per;
    }

    public boolean ingresarPersona(Persona cliente) {
        boolean reguistrar = false;
        Connection con = null;
        Statement stm = null;
        String sql;
        if (cliente.getFechaNacimiento() == null) {
            sql = "INSERT INTO `cliente`.`persona` (`cedulo`, `nombre`, `apellido`, `direccion`, `correo`, `telefono`, `fecha_Registro`, `genero`) VALUES ('" + cliente.getCedula() + "', '" + cliente.getNombre() + "', '" + cliente.getApellido() + "', '" + cliente.getDireccion() + "', '" + cliente.getCorreo() + "', '" + cliente.getTelefono() + "', '" + utilidades.fechaRegistro(cliente.getFechaRegistro()) + "', '" + cliente.getGenero() + "')";
        } else {
            sql = "INSERT INTO `cliente`.`persona` (`cedulo`, `nombre`, `apellido`, `direccion`, `correo`, `telefono`, `fecha_Registro`, `genero`, `fecha_Nacimiento`) VALUES ('" + cliente.getCedula() + "', '" + cliente.getNombre() + "', '" + cliente.getApellido() + "', '" + cliente.getDireccion() + "', '" + cliente.getCorreo() + "', '" + cliente.getTelefono() + "', '" + utilidades.fechaRegistro(cliente.getFechaRegistro()) + "', '" + cliente.getGenero() + "', '" + utilidades.fechaRegistro(cliente.getFechaNacimiento()) + "')";
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

    public boolean eliminarCliente(String id) {
        boolean eliminar = false;
        PreparedStatement pps;
        try {
            pps = con.prepareStatement("DELETE FROM persona WHERE idpersona = '" + id + "'");
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Dato eliminado");
            eliminar = true;
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        return eliminar;
    }

    public boolean editarCliente(Persona cliente) {
        boolean eliminar = false;
        PreparedStatement pps;
        try {
            if (cliente.getFechaNacimiento() == null) {
                pps = con.prepareStatement("UPDATE persona SET cedulo = '" + cliente.getCedula() + "', nombre = '" + cliente.getNombre() + "', apellido = '" + cliente.getApellido() + "', direccion = '" + cliente.getDireccion() + "', correo = '" + cliente.getCorreo() + "', telefono = '" + cliente.getTelefono() + "', fecha_Registro = '" + cliente.getFechaRegistro() + "', genero = '" + cliente.getGenero() + "', fecha_Actualizacion = '" + utilidades.fechaRegistro(cliente.getFechaActualización()) + "' WHERE idpersona = '" + cliente.getIdPersona() + "'");
            } else {
                pps = con.prepareStatement("UPDATE persona SET cedulo = '" + cliente.getCedula() + "', nombre = '" + cliente.getNombre() + "', apellido = '" + cliente.getApellido() + "', direccion = '" + cliente.getDireccion() + "', correo = '" + cliente.getCorreo() + "', telefono = '" + cliente.getTelefono() + "', fecha_Registro = '" + cliente.getFechaRegistro() + "', genero = '" + cliente.getGenero() + "', fecha_Actualizacion = '" + utilidades.fechaRegistro(cliente.getFechaActualización()) + "', fecha_Nacimiento = '" + utilidades.fechaRegistro(cliente.getFechaNacimiento()) + "' WHERE idpersona = '" + cliente.getIdPersona() + "'");
            }
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Persona Editada");
            eliminar = true;
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        return eliminar;
    }

    public Persona obtenerPersona(String nombre) {
        Persona obtenerPersona = null;

        String sql = "SELECT * FROM persona WHERE idpersona = '" + nombre + "' OR cedulo = '" + nombre + "'";

        Statement datos;
        try {
            datos = con.createStatement();
            ResultSet res = datos.executeQuery(sql);

            while (res.next()) {
                obtenerPersona = new Persona();
                obtenerPersona.setIdPersona(res.getString(1));
                obtenerPersona.setCedula(res.getString(2));
                obtenerPersona.setNombre(res.getString(3));
                obtenerPersona.setApellido(res.getString(4));
                obtenerPersona.setDireccion(res.getString(5));
                obtenerPersona.setCorreo(res.getString(6));
                obtenerPersona.setTelefono(res.getString(7));
                obtenerPersona.setFechaRegistro(res.getDate(8));
                obtenerPersona.setGenero(res.getInt(9));
                obtenerPersona.setFechaActualización(res.getDate(10));
                obtenerPersona.setFechaNacimiento(res.getDate(11));
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex);
        }
        return obtenerPersona;

    }

    public void numeros(JTextField texto) {
        texto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                if (texto.getText().length() >= 10) {
                    ke.consume();
                }

                char c = ke.getKeyChar();
                if (!Character.isDigit(c)) {
                    ke.consume();
                }
            }
        });
    }

    public void letras(JTextField texto) {
        texto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                char c = ke.getKeyChar();
                if (Character.isDigit(c)) {
                    ke.consume();
                }
            }
        });

    }

}
