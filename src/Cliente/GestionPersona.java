package Cliente;

import com.toedter.calendar.JDateChooser;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * @author Emersson
 */
public class GestionPersona {

    private JTextField textDNI;
    private JTextField textNombre;
    private JTextField textApellido;
    private JTextField textDireccion;
    private JTextField textCorreo;
    private JTextField textTelefono;
    private JComboBox comboBoxGenero;
    private JComboBox comboBoxBuscar;
    private JFrame Cliente_Ventana;
    private JDateChooser dateNacimiento;
    private JTable tabla;
    private Utilidades utilidades = new Utilidades();
    private Persona p;
    private boolean lista;
    private Controlador_BD_Cliente persona = new Controlador_BD_Cliente();

    public GestionPersona(JTextField textDNI, JTextField textNombre, JTextField textApellido, JTextField textDireccion, JTextField textCorreo, JTextField textTelefono, JComboBox comboBoxGenero, JComboBox comboBoxBuscar, JFrame Cliente_Ventana, JDateChooser dateNacimiento, JTable tabla) {
        this.textDNI = textDNI;
        this.textNombre = textNombre;
        this.textApellido = textApellido;
        this.textDireccion = textDireccion;
        this.textCorreo = textCorreo;
        this.textTelefono = textTelefono;
        this.comboBoxGenero = comboBoxGenero;
        this.comboBoxBuscar = comboBoxBuscar;
        this.Cliente_Ventana = Cliente_Ventana;
        this.dateNacimiento = dateNacimiento;
        this.tabla = tabla;
    }

    public JTextField getTextDNI() {
        return textDNI;
    }

    public void setTextDNI(JTextField textDNI) {
        this.textDNI = textDNI;
    }

    public JTextField getTextNombre() {
        return textNombre;
    }

    public void setTextNombre(JTextField textNombre) {
        this.textNombre = textNombre;
    }

    public JTextField getTextApellido() {
        return textApellido;
    }

    public void setTextApellido(JTextField textApellido) {
        this.textApellido = textApellido;
    }

    public JTextField getTextDireccion() {
        return textDireccion;
    }

    public void setTextDireccion(JTextField textDireccion) {
        this.textDireccion = textDireccion;
    }

    public JTextField getTextCorreo() {
        return textCorreo;
    }

    public void setTextCorreo(JTextField textCorreo) {
        this.textCorreo = textCorreo;
    }

    public JTextField getTextTelefono() {
        return textTelefono;
    }

    public void setTextTelefono(JTextField textTelefono) {
        this.textTelefono = textTelefono;
    }

    public JComboBox getComboBoxGenero() {
        return comboBoxGenero;
    }

    public void setComboBoxGenero(JComboBox comboBoxGenero) {
        this.comboBoxGenero = comboBoxGenero;
    }

    public JComboBox getComboBoxBuscar() {
        return comboBoxBuscar;
    }

    public void setComboBoxBuscar(JComboBox comboBoxBuscar) {
        this.comboBoxBuscar = comboBoxBuscar;
    }

    public JFrame getCliente_Ventana() {
        return Cliente_Ventana;
    }

    public void setCliente_Ventana(JFrame Cliente_Ventana) {
        this.Cliente_Ventana = Cliente_Ventana;
    }

    public JDateChooser getDateNacimiento() {
        return dateNacimiento;
    }

    public void setDateNacimiento(JDateChooser dateNacimiento) {
        this.dateNacimiento = dateNacimiento;
    }

    public JTable getTabla() {
        return tabla;
    }

    public void setTabla(JTable tabla) {
        this.tabla = tabla;
    }

    public boolean datosCliente() {

        if (textDNI.getText().isEmpty()) {
            JOptionPane.showMessageDialog(Cliente_Ventana, "El campo nombres no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            textDNI.requestFocus();
            return false;
        }
        if (textApellido.getText().isEmpty()) {
            JOptionPane.showMessageDialog(Cliente_Ventana, "El campo apellidos no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            textApellido.requestFocus();
            return false;
        }
        if (textDireccion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(Cliente_Ventana, "El campo dirección no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            textDireccion.requestFocus();
            return false;
        }
        if (textTelefono.getText().isEmpty()) {
            JOptionPane.showMessageDialog(Cliente_Ventana, "El campo telefono no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            textTelefono.requestFocus();
            return false;
        }
        if (!utilidades.validarNumeros(textTelefono.getText())) {
            JOptionPane.showMessageDialog(Cliente_Ventana, "Los datos ingresados en el teléfono no son validos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            textTelefono.requestFocus();
            return false;
        }
        if (textCorreo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(Cliente_Ventana, "El campo correo no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            textCorreo.requestFocus();
            return false;
        }

        if (!utilidades.validarCorreo(textCorreo.getText())) {
            JOptionPane.showMessageDialog(Cliente_Ventana, "Los datos del correo son incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            textCorreo.requestFocus();
            return false;
        }
        lista = true;
        return true;
    }

    private void camposVacios() {
        textDNI.setText("");
        textNombre.setText("");
        textApellido.setText("");
        textDireccion.setText("");
        textCorreo.setText("");
        textTelefono.setText("");
        comboBoxGenero.setSelectedItem("No definido");
        dateNacimiento.setDate(null);
    }

    public void botonTraer() {
        System.out.println(textNombre.getText());
        System.out.println(textApellido.getText());
    }

    public void registrarPersona() {
        Persona cliente = null;
        if (datosCliente()) {
            utilidades.fechaRegistro(new Date());
            cliente = new Persona(textDNI.getText(), textNombre.getText(), textApellido.getText(), textDireccion.getText(), textCorreo.getText(), textTelefono.getText());
            cliente.setGenero(comboBoxGenero.getSelectedIndex());
            cliente.setFechaRegistro(new Date());
            if (dateNacimiento.getDate() != null) {
                cliente.setFechaNacimiento(dateNacimiento.getDate());
            }
            persona.ingresarPersona(cliente);
            camposVacios();
        }

    }

    public void setearDatosTabla(String id) {
        p = persona.obtenerPersona(id);
        textDNI.setText(p.getCedula());
        textNombre.setText(p.getNombre());
        textApellido.setText(p.getApellido());
        textDireccion.setText(p.getDireccion());
        textCorreo.setText(p.getCorreo());
        textTelefono.setText(p.getTelefono());
        comboBoxGenero.setSelectedIndex(p.getGenero());
        dateNacimiento.setDate(p.getFechaNacimiento());
    }

    public void editarPersona() {
        java.util.Date f = p.getFechaRegistro();
        p = new Persona(p.getIdPersona(), textDNI.getText(), textNombre.getText(), textApellido.getText(), textDireccion.getText(), textCorreo.getText(), textTelefono.getText(), f);
        p.setGenero(comboBoxGenero.getSelectedIndex());
        p.setFechaActualización(new Date());
        if (dateNacimiento.getDate() != null) {
            p.setFechaNacimiento(dateNacimiento.getDate());
        }
        persona.editarCliente(p);
        camposVacios();
    }

    public void eliminarPersona(String id) {
        persona.eliminarCliente(id);
        camposVacios();
    }

    public void buscarCliente(String buscar) {
        if (comboBoxBuscar.getSelectedIndex() == 0) {
            tabla.setModel(persona.mostrarPersonasPorDato("cedulo", buscar));
        } else if (comboBoxBuscar.getSelectedIndex() == 1) {
            tabla.setModel(persona.mostrarPersonasPorDato("nombre", buscar));
        } else if (comboBoxBuscar.getSelectedIndex() == 2) {
            tabla.setModel(persona.mostrarPersonasPorDato("apellido", buscar));
        } else if (comboBoxBuscar.getSelectedIndex() == 3) {
            tabla.setModel(persona.mostrarPersonasPorDato("direccion", buscar));
        } else if (comboBoxBuscar.getSelectedIndex() == 4) {
            tabla.setModel(persona.mostrarPersonasPorDato("correo", buscar));
        } else if (comboBoxBuscar.getSelectedIndex() == 5) {
            tabla.setModel(persona.mostrarPersonasPorDato("telefono", buscar));
        }
    }
}
