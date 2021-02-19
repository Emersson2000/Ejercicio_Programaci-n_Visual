package Cliente;

import Tipografias.Fuentes;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
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
    private ModeloTabla model;
    private DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
    private boolean lista;

    public Cliente_Ventana() {
        model = new ModeloTabla(persona.mostrarPersonasV());
        initComponents();
        this.setLocationRelativeTo(null);
        this.setSize(910, 1000);
        iniciarComponentes();
        persona.numeros(textCedula);
        persona.numeros(textTelefono);
        persona.letras(textNombre);
        persona.letras(textApellido);

    }

    private void iniciarComponentes() {
        colocarTabla();
        camposVacios();
        botonDesabilitadosInicio();
        colocarComboBox();
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
        mostrarTabla();
    }

    void mostrarTabla() {
        tabla.setModel(persona.mostrarPersonasPorDato("idpersona", ""));
    }

    void colocarComboBox() {

        modeloCombo.addElement("Cédula");
        modeloCombo.addElement("Nombre");
        modeloCombo.addElement("Apellido");
        modeloCombo.addElement("Dirección");
        modeloCombo.addElement("Correo");
        modeloCombo.addElement("Teléfono");

    }

    void buscarCliente(String buscar) {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelRegistro = new javax.swing.JPanel();
        panel4 = new javax.swing.JPanel();
        botonAgregar = new javax.swing.JButton();
        botonEditar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonTraer = new javax.swing.JButton();
        panel3 = new javax.swing.JPanel();
        textCedula = new javax.swing.JTextField();
        textNombre = new javax.swing.JTextField();
        textApellido = new javax.swing.JTextField();
        textDireccion = new javax.swing.JTextField();
        textCorreo = new javax.swing.JTextField();
        textTelefono = new javax.swing.JTextField();
        botonBuscar = new javax.swing.JButton();
        panel2 = new javax.swing.JPanel();
        etiquetaCedula = new javax.swing.JLabel();
        etiquetaNombre = new javax.swing.JLabel();
        etiquetaApellido = new javax.swing.JLabel();
        etiquetaDireccion = new javax.swing.JLabel();
        etiquetaCorreo = new javax.swing.JLabel();
        etiquetTelefono = new javax.swing.JLabel();
        panel1 = new javax.swing.JPanel();
        etiquetaRegistro = new javax.swing.JLabel();
        panel5 = new javax.swing.JPanel();
        desplazamiento = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        panelBuscar = new javax.swing.JPanel();
        etiquetaBuscar = new javax.swing.JLabel();
        comboBoxBuscar = new javax.swing.JComboBox<>();
        panelBuscarText = new javax.swing.JPanel();
        textBuscar = new javax.swing.JTextField();
        botonBuscar2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelRegistro.setPreferredSize(new java.awt.Dimension(732, 900));

        panel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 60, 5));

        botonAgregar.setFont(tipoFuente.fuente(tipoFuente.FightThis, 2, 30)
        );
        botonAgregar.setText("Agregar");
        botonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarActionPerformed(evt);
            }
        });
        panel4.add(botonAgregar);

        botonEditar.setFont(tipoFuente.fuente(tipoFuente.FightThis, 2, 30)
        );
        botonEditar.setText("Editar");
        botonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEditarActionPerformed(evt);
            }
        });
        panel4.add(botonEditar);

        botonEliminar.setFont(tipoFuente.fuente(tipoFuente.FightThis, 2, 30)
        );
        botonEliminar.setText("Eliminar");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });
        panel4.add(botonEliminar);

        botonTraer.setFont(tipoFuente.fuente(tipoFuente.FightThis, 2, 30));
        botonTraer.setText("Traer");
        panel4.add(botonTraer);

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
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 0, 0);
        panel3.add(botonBuscar, gridBagConstraints);

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

        etiquetaRegistro.setFont(tipoFuente.fuente(tipoFuente.Skaters, 1, 30)
        );
        etiquetaRegistro.setText("REGISTRO DE CLIENTES");
        panel1.add(etiquetaRegistro);

        tabla.setFont(new java.awt.Font("Constantia", 2, 18)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla.setRowMargin(0);
        tabla.setShowVerticalLines(false);
        tabla.setBounds(0, 650, 830, 300);
        tabla.getTableHeader().setFont(new Font("Constantia", Font.BOLD, 22));
        tabla.getTableHeader().setOpaque(true);
        tabla.getTableHeader().setBackground(new Color(32, 136, 203));
        tabla.getTableHeader().setForeground(new Color(255, 255, 255));
        tabla.setRowHeight(25);
        tabla.setBackground(new Color(204, 255, 204));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        desplazamiento.setViewportView(tabla);

        javax.swing.GroupLayout panel5Layout = new javax.swing.GroupLayout(panel5);
        panel5.setLayout(panel5Layout);
        panel5Layout.setHorizontalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desplazamiento, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panel5Layout.setVerticalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desplazamiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
        );

        panelBuscar.setLayout(new java.awt.GridBagLayout());

        etiquetaBuscar.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 24));
        etiquetaBuscar.setText("Buscar Cliente:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
        panelBuscar.add(etiquetaBuscar, gridBagConstraints);

        comboBoxBuscar.setFont(tipoFuente.fuente(tipoFuente.Emberly, 1, 24)
        );
        comboBoxBuscar.setModel(modeloCombo);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 5, 20, 0);
        panelBuscar.add(comboBoxBuscar, gridBagConstraints);

        panelBuscarText.setLayout(new java.awt.GridBagLayout());

        textBuscar.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25));
        textBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textBuscarKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 260;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
        panelBuscarText.add(textBuscar, gridBagConstraints);

        botonBuscar2.setFont(tipoFuente.fuente(tipoFuente.FightThis, 2, 24));
        botonBuscar2.setText("Buscar");
        botonBuscar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscar2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
        panelBuscarText.add(botonBuscar2, gridBagConstraints);

        javax.swing.GroupLayout panelRegistroLayout = new javax.swing.GroupLayout(panelRegistro);
        panelRegistro.setLayout(panelRegistroLayout);
        panelRegistroLayout.setHorizontalGroup(
            panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRegistroLayout.createSequentialGroup()
                .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRegistroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelRegistroLayout.createSequentialGroup()
                        .addComponent(panelBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelBuscarText, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegistroLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(panel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRegistroLayout.createSequentialGroup()
                            .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        panelRegistroLayout.setVerticalGroup(
            panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegistroLayout.createSequentialGroup()
                .addContainerGap(568, Short.MAX_VALUE)
                .addComponent(panel4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBuscarText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addComponent(panel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRegistroLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(603, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Registro", panelRegistro);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 732, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 968, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Mantenimiento", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
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
            JOptionPane.showMessageDialog(rootPane, "Los datos ingresados en el teléfono no son validos.", "ERROR", JOptionPane.ERROR_MESSAGE);
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
            mostrarTabla();
            camposVacios();
        }

    }//GEN-LAST:event_botonAgregarActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        camposVacios();
        if (lista == false) {
            int filaSeleccionada = tabla.getSelectedRow();
            if (filaSeleccionada >= 0) {
                String valor = tabla.getValueAt(filaSeleccionada, 0).toString();
                persona.eliminarCliente(valor);
                botonEliminar.setEnabled(false);
                botonAgregar.setEnabled(true);
                colocarTabla();
            }

        } else if (lista == true) {
            if (persona.eliminarCliente(p.getIdPersona())) {
                System.out.println("Persona eliminada");
                colocarTabla();
            }
        }

    }//GEN-LAST:event_botonEliminarActionPerformed

    private void botonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarActionPerformed

        if (datosCliente()) {
            p = new Persona(p.getIdPersona(), textCedula.getText(), textNombre.getText(), textApellido.getText(), textDireccion.getText(), textCorreo.getText(), textTelefono.getText());
            persona.editarCliente(p);
            mostrarTabla();
            camposVacios();
            botonEditar.setEnabled(false);
            botonAgregar.setEnabled(true);
        }

//        } else {
//            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
//        }

    }//GEN-LAST:event_botonEditarActionPerformed

    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarActionPerformed
        lista = true;

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
            botonEliminar.setEnabled(true);
        } else {
            System.out.println("La persona buscada no se encuentra en la base de datos");
        }

    }//GEN-LAST:event_botonBuscarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        lista = false;

        int fila = tabla.getSelectedRow();
        textCedula.setText(tabla.getValueAt(fila, 1).toString());
        textNombre.setText(tabla.getValueAt(fila, 2).toString());
        textApellido.setText(tabla.getValueAt(fila, 3).toString());
        textDireccion.setText(tabla.getValueAt(fila, 4).toString());
        textCorreo.setText(tabla.getValueAt(fila, 5).toString());
        textTelefono.setText(tabla.getValueAt(fila, 6).toString());
        botonEliminar.setEnabled(true);

    }//GEN-LAST:event_tablaMouseClicked

    private void botonBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscar2ActionPerformed

    }//GEN-LAST:event_botonBuscar2ActionPerformed

    private void textBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBuscarKeyReleased
        buscarCliente(textBuscar.getText());
    }//GEN-LAST:event_textBuscarKeyReleased

    public static void main(String args[]) {
//        
//        try {
//            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Cliente_Ventana.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            Logger.getLogger(Cliente_Ventana.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(Cliente_Ventana.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(Cliente_Ventana.class.getName()).log(Level.SEVERE, null, ex);
//        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cliente_Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAgregar;
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonBuscar2;
    private javax.swing.JButton botonEditar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonTraer;
    private javax.swing.JComboBox<String> comboBoxBuscar;
    private javax.swing.JScrollPane desplazamiento;
    private javax.swing.JLabel etiquetTelefono;
    private javax.swing.JLabel etiquetaApellido;
    private javax.swing.JLabel etiquetaBuscar;
    private javax.swing.JLabel etiquetaCedula;
    private javax.swing.JLabel etiquetaCorreo;
    private javax.swing.JLabel etiquetaDireccion;
    private javax.swing.JLabel etiquetaNombre;
    private javax.swing.JLabel etiquetaRegistro;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
    private javax.swing.JPanel panel4;
    private javax.swing.JPanel panel5;
    private javax.swing.JPanel panelBuscar;
    private javax.swing.JPanel panelBuscarText;
    private javax.swing.JPanel panelRegistro;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField textApellido;
    private javax.swing.JTextField textBuscar;
    private javax.swing.JTextField textCedula;
    private javax.swing.JTextField textCorreo;
    private javax.swing.JTextField textDireccion;
    private javax.swing.JTextField textNombre;
    private javax.swing.JTextField textTelefono;
    // End of variables declaration//GEN-END:variables
}
