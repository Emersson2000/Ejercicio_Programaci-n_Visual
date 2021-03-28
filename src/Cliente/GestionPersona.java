package Cliente;

import com.toedter.calendar.JDateChooser;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
    private JFrame Cliente_Ventana;
    private JDateChooser dateNacimiento;
    private Utilidades utilidades = new Utilidades();
    private boolean lista;

    public GestionPersona(JTextField textDNI, JTextField textNombre, JTextField textApellido, JTextField textDireccion, JTextField textCorreo, JTextField textTelefono, JComboBox comboBoxGenero, JFrame Cliente_Ventana, JDateChooser dateNacimiento) {
        this.textDNI = textDNI;
        this.textNombre = textNombre;
        this.textApellido = textApellido;
        this.textDireccion = textDireccion;
        this.textCorreo = textCorreo;
        this.textTelefono = textTelefono;
        this.comboBoxGenero = comboBoxGenero;
        this.Cliente_Ventana = Cliente_Ventana;
        this.dateNacimiento = dateNacimiento;
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

  private boolean datosCliente() {

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
    
}
