package Cliente;

import Tipografias.Fuentes;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Cliente_Ventana extends javax.swing.JFrame {

    static String editar;
//    private Conexion_Base_De_Datos conexion = new Conexion_Base_De_Datos();
//    private Connection con = conexion.Conexion_Mysql();
    private Fuentes tipoFuente = new Fuentes();
    private Controlador_BD persona = new Controlador_BD();
    private Utilidades utilidades = new Utilidades();
    private ListSelectionListener seleccionar;
    private Persona p;

    public Cliente_Ventana() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setSize(720, 900);
        iniciarComponentes();
        tabla.setBounds(0, 650, 830, 300);
        tabla.getTableHeader().setFont(new Font("Constantia", Font.BOLD, 22));
        tabla.getTableHeader().setOpaque(true);
        tabla.getTableHeader().setBackground(new Color(32, 136, 203));
        tabla.getTableHeader().setForeground(new Color(255, 255, 255));
        tabla.setRowHeight(25);
        tabla.setBackground(new Color(204, 255, 204));
        persona.numeros(textCedula);
        persona.numeros(textTelefono);
        persona.letras(textNombre);
        persona.letras(textApellido);
    }

    private void iniciarComponentes() {
        colocarTabla();
        camposVacios();
        botonDesabilitadosInicio();
        //seleccionarPersona();
    }

    private void seleccionarPersona() {
        seleccionar = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (lse.getValueIsAdjusting()) {
                    int fila = tabla.getSelectedRow();
                    textCedula.setText(tabla.getValueAt(fila, 1).toString());
                    textNombre.setText(tabla.getValueAt(fila, 2).toString());
                    textApellido.setText(tabla.getValueAt(fila, 3).toString());
                    textDireccion.setText(tabla.getValueAt(fila, 4).toString());
                    textCorreo.setText(tabla.getValueAt(fila, 5).toString());
                    textTelefono.setText(tabla.getValueAt(fila, 6).toString());
                }
            }
        };

        tabla.getSelectionModel().addListSelectionListener(seleccionar);
    }

    private void camposVacios() {
        textCedula.setText("");
        textNombre.setText("");
        textApellido.setText("");
        textDireccion.setText("");
        textCorreo.setText("");
        textTelefono.setText("");
    }

    private void botonDesabilitadosInicio() {
        botonEditar.setEnabled(false);
        botonEliminar.setEnabled(false);
    }

    private void botonDesabilitadosFin() {
        botonEditar.setEnabled(false);
        botonEliminar.setEnabled(true);
        botonAgregar.setEnabled(false);
        botonTraer.setEnabled(false);
    }

    void colocarTabla() {
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("idPersona");
        modeloTabla.addColumn("Cédula");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("Dirección");
        modeloTabla.addColumn("Correo");
        modeloTabla.addColumn("Teléfono");
        tabla.setModel(persona.mostrarPersonas(modeloTabla));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panel1 = new javax.swing.JPanel();
        etiquetaRegistro = new javax.swing.JLabel();
        panel2 = new javax.swing.JPanel();
        etiquetaCedula = new javax.swing.JLabel();
        etiquetaNombre = new javax.swing.JLabel();
        etiquetaApellido = new javax.swing.JLabel();
        etiquetaDireccion = new javax.swing.JLabel();
        etiquetaCorreo = new javax.swing.JLabel();
        etiquetTelefono = new javax.swing.JLabel();
        panel3 = new javax.swing.JPanel();
        textCedula = new javax.swing.JTextField();
        textNombre = new javax.swing.JTextField();
        textApellido = new javax.swing.JTextField();
        textDireccion = new javax.swing.JTextField();
        textCorreo = new javax.swing.JTextField();
        textTelefono = new javax.swing.JTextField();
        botonBuscar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        botonAgregar = new javax.swing.JButton();
        botonEditar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonTraer = new javax.swing.JButton();
        panel4 = new javax.swing.JPanel();
        desplazamiento = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        etiquetaRegistro.setFont(tipoFuente.fuente(tipoFuente.Skaters, 1, 30)
        );
        etiquetaRegistro.setText("REGISTRO DE CLIENTES");
        panel1.add(etiquetaRegistro);

        panel2.setLayout(new java.awt.GridBagLayout());

        etiquetaCedula.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25));
        etiquetaCedula.setText("Cédula:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(40, 0, 0, 0);
        panel2.add(etiquetaCedula, gridBagConstraints);

        etiquetaNombre.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25));
        etiquetaNombre.setText("Nombre:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(25, 0, 0, 0);
        panel2.add(etiquetaNombre, gridBagConstraints);

        etiquetaApellido.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25));
        etiquetaApellido.setText("Apellido:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(25, 0, 0, 0);
        panel2.add(etiquetaApellido, gridBagConstraints);

        etiquetaDireccion.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25));
        etiquetaDireccion.setText("Dirección:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(25, 0, 0, 0);
        panel2.add(etiquetaDireccion, gridBagConstraints);

        etiquetaCorreo.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25));
        etiquetaCorreo.setText("Correo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(25, 0, 0, 0);
        panel2.add(etiquetaCorreo, gridBagConstraints);

        etiquetTelefono.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25));
        etiquetTelefono.setText("Teléfono:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(25, 0, 0, 0);
        panel2.add(etiquetTelefono, gridBagConstraints);

        panel3.setLayout(new java.awt.GridBagLayout());

        textCedula.setBackground(new java.awt.Color(255, 255, 255));
        textCedula.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25));
        textCedula.setForeground(new java.awt.Color(0, 0, 0));
        textCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCedulaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 260;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 0, 0);
        panel3.add(textCedula, gridBagConstraints);

        textNombre.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25)
        );
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 260;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        panel3.add(textNombre, gridBagConstraints);

        textApellido.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25)
        );
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 260;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        panel3.add(textApellido, gridBagConstraints);

        textDireccion.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25)
        );
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 260;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        panel3.add(textDireccion, gridBagConstraints);

        textCorreo.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25)
        );
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 260;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        panel3.add(textCorreo, gridBagConstraints);

        textTelefono.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25)
        );
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 260;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        panel3.add(textTelefono, gridBagConstraints);

        botonBuscar.setFont(tipoFuente.fuente(tipoFuente.FightThis, 2, 30));
        botonBuscar.setText("Buscar");
        botonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 0, 0);
        panel3.add(botonBuscar, gridBagConstraints);

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 60, 5));

        botonAgregar.setFont(tipoFuente.fuente(tipoFuente.FightThis, 2, 30)
        );
        botonAgregar.setText("Agregar");
        botonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(botonAgregar);

        botonEditar.setFont(tipoFuente.fuente(tipoFuente.FightThis, 2, 30)
        );
        botonEditar.setText("Editar");
        botonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEditarActionPerformed(evt);
            }
        });
        jPanel1.add(botonEditar);

        botonEliminar.setFont(tipoFuente.fuente(tipoFuente.FightThis, 2, 30)
        );
        botonEliminar.setText("Eliminar");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(botonEliminar);

        botonTraer.setFont(tipoFuente.fuente(tipoFuente.FightThis, 2, 30));
        botonTraer.setText("Traer");
        jPanel1.add(botonTraer);

        tabla.setFont(new java.awt.Font("Constantia", 2, 18)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla.setRowMargin(0);
        tabla.setShowVerticalLines(false);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        desplazamiento.setViewportView(tabla);

        javax.swing.GroupLayout panel4Layout = new javax.swing.GroupLayout(panel4);
        panel4.setLayout(panel4Layout);
        panel4Layout.setHorizontalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desplazamiento, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panel4Layout.setVerticalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(desplazamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCedulaActionPerformed

    private boolean datosCliente() {
        if (textCedula.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "En campo de cédula está vacio", "ERROR", JOptionPane.ERROR_MESSAGE);
            textCedula.requestFocus();
            return false;
        }

        if (!utilidades.validadorDeCedula(textCedula.getText())) {
            JOptionPane.showMessageDialog(rootPane, "la cédula ingresada no es valida", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (textNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "El campo nombres no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            textNombre.requestFocus();
            return false;
        }
        if (textApellido.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "El campo apellidos no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            textApellido.requestFocus();
            return false;
        }
        if (textDireccion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "El campo dirección no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            textDireccion.requestFocus();
            return false;
        }
        if (textTelefono.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "El campo telefono no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            textTelefono.requestFocus();
            return false;
        }
        if (!utilidades.validarNumeros(textTelefono.getText())) {
            JOptionPane.showMessageDialog(rootPane, "Los datos ingresados en el telefono no son validos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            textTelefono.requestFocus();
            return false;
        }
        if (textCorreo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "El campo correo no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            textCorreo.requestFocus();
            return false;
        }

        if (!utilidades.validarCorreo(textCorreo.getText())) {
            JOptionPane.showMessageDialog(rootPane, "Los datos del correo son incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            textCorreo.requestFocus();
            return false;
        }
        return true;
    }
    private void botonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarActionPerformed

        if (datosCliente()) {
            Persona cliente = new Persona(textCedula.getText(), textNombre.getText(), textApellido.getText(), textDireccion.getText(), textCorreo.getText(), textTelefono.getText());
            persona.ingresarPersona(cliente);
            colocarTabla();
            camposVacios();
        }

    }//GEN-LAST:event_botonAgregarActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        camposVacios();
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            String valor = tabla.getValueAt(fila, 0).toString();
            persona.eliminarCliente(valor);
            botonEliminar.setEnabled(false);
            botonAgregar.setEnabled(true);
            colocarTabla();
        }

    }//GEN-LAST:event_botonEliminarActionPerformed

    private void botonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarActionPerformed
  
        if (datosCliente()) {
            p = new Persona(p.getIdPersona(), textCedula.getText(), textNombre.getText(), textApellido.getText(), textDireccion.getText(), textCorreo.getText(), textTelefono.getText());
            persona.editarCliente(p);
            colocarTabla();
            camposVacios();
            botonEditar.setEnabled(false);
            botonAgregar.setEnabled(true);
        }

//        } else {
//            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
//        }

    }//GEN-LAST:event_botonEditarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        int fila = tabla.getSelectedRow();

        if (fila >= 0) {
            botonDesabilitadosFin();
        }
        /*int fila = tabla.getSelectedRow();
        textCedula.setText(tabla.getValueAt(fila, 1).toString());
        textNombre.setText(tabla.getValueAt(fila, 2).toString());
        textApellido.setText(tabla.getValueAt(fila, 3).toString());
        textDireccion.setText(tabla.getValueAt(fila, 4).toString());
        textCorreo.setText(tabla.getValueAt(fila, 5).toString());
        textTelefono.setText(tabla.getValueAt(fila, 6).toString());*/
    }//GEN-LAST:event_tablaMouseClicked

    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarActionPerformed
        if (textNombre.getText().isEmpty()) {
            System.out.println("No hay ningún dato a buscar");
        } else if (persona.obtenerPersona(textNombre.getText()) != null) {
            p = new Persona();
            p = persona.obtenerPersona(textNombre.getText());
            textCedula.setText(p.getCedula());
            textNombre.setText(p.getNombre());
            textApellido.setText(p.getApellido());
            textDireccion.setText(p.getDireccion());
            textCorreo.setText(p.getCorreo());
            textTelefono.setText(p.getTelefono());
            botonEditar.setEnabled(true);
            botonAgregar.setEnabled(false);
        } else {
            System.out.println("La persona buscada no se encuentra en la base de datos");
        }

    }//GEN-LAST:event_botonBuscarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cliente_Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAgregar;
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonEditar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonTraer;
    private javax.swing.JScrollPane desplazamiento;
    private javax.swing.JLabel etiquetTelefono;
    private javax.swing.JLabel etiquetaApellido;
    private javax.swing.JLabel etiquetaCedula;
    private javax.swing.JLabel etiquetaCorreo;
    private javax.swing.JLabel etiquetaDireccion;
    private javax.swing.JLabel etiquetaNombre;
    private javax.swing.JLabel etiquetaRegistro;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
    private javax.swing.JPanel panel4;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField textApellido;
    private javax.swing.JTextField textCedula;
    private javax.swing.JTextField textCorreo;
    private javax.swing.JTextField textDireccion;
    private javax.swing.JTextField textNombre;
    private javax.swing.JTextField textTelefono;
    // End of variables declaration//GEN-END:variables
}
