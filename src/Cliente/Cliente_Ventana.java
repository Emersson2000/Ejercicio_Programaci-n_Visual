package Cliente;

import Tipografias.Fuentes;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    private Controlador_BD_Cliente persona = new Controlador_BD_Cliente();
    private Controlador_BD_Proveedor proveedor = new Controlador_BD_Proveedor();
    private Controlador_BD_Inventario inventario = new Controlador_BD_Inventario();
    private Utilidades utilidades = new Utilidades();
    private ListSelectionListener seleccionar;
    private Persona p;
    private Inventario inve;
    private ModeloTabla model;
    private DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
    private DefaultComboBoxModel modeloComboProveedor = new DefaultComboBoxModel();
    private DefaultComboBoxModel modeloComboInventario = new DefaultComboBoxModel();
    private boolean lista;
    private boolean inveBoolean;
    private int x;
    private int y;
    private boolean movi = true;
    private int variableX;
    private int variableY;

    public Cliente_Ventana() {
        model = new ModeloTabla(persona.mostrarPersonasV());
        initComponents();
        this.setLocationRelativeTo(null);
        this.setSize(937, 1025);
        iniciarComponentes();
        leerArchivo();
        panel9.setLayout(null);
        persona.numeros(textCedula);
        persona.numeros(textTelefono);
        persona.letras(textNombre);
        persona.letras(textApellido);
        proveedor.numeros(textRucProveedor);
        inventario.soloNumeros(textCodigoInventario);
    }

    private void iniciarComponentes() {
        colocarTabla();
        camposVacios();
        botonDesabilitadosInicio();
        colocarComboBox();
        colocarComboBoxProveedor();
        colocarComboBoxInventario();
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

    private void camposVacionProveedor() {
        textRucProveedor.setText("");
        textSocialProveedor.setText("");
        textActividadProveedor.setText("");
        textNombreProveedor.setText("");
        textApellidoProveedor.setText("");
        textCorreoProveedor.setText("");
        textTelefonoProveedor.setText("");
    }

    private void camposVaciosInventario() {
        textCodigoInventario.setText("");
        textDescripcionInventario.setText("");
        textP_CompraInventario.setText("");
        textP_VentaInventario.setText("");
        textProductosInventario.setText("");
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
        tablaProveedor.setModel(proveedor.mostrarProveedorPorDato("idProveedor", ""));
        tablaInventario.setModel(inventario.mostrarInventarioPorDato("idInventario", ""));
    }

    void colocarComboBox() {

        modeloCombo.addElement("Cédula");
        modeloCombo.addElement("Nombre");
        modeloCombo.addElement("Apellido");
        modeloCombo.addElement("Dirección");
        modeloCombo.addElement("Correo");
        modeloCombo.addElement("Teléfono");

    }

    void colocarComboBoxProveedor() {
        modeloComboProveedor.addElement("RUC");
        modeloComboProveedor.addElement("Social");
        modeloComboProveedor.addElement("Actividad");
        modeloComboProveedor.addElement("Nombre");
        modeloComboProveedor.addElement("Apellido");
        modeloComboProveedor.addElement("Teléfono");
        modeloComboProveedor.addElement("Correo");
    }

    void colocarComboBoxInventario() {
        modeloComboInventario.addElement("Código");
        modeloComboInventario.addElement("Descripción");
        modeloComboInventario.addElement("P.Compra");
        modeloComboInventario.addElement("P.Venta");
        modeloComboInventario.addElement("Productos");
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

    void buscarProveedor(String buscar) {
        if (comboBoxBuscarProveedor.getSelectedIndex() == 0) {
            tablaProveedor.setModel(proveedor.mostrarProveedorPorDato("ruc", buscar));
        } else if (comboBoxBuscarProveedor.getSelectedIndex() == 1) {
            tablaProveedor.setModel(proveedor.mostrarProveedorPorDato("razon_Social", buscar));
        } else if (comboBoxBuscarProveedor.getSelectedIndex() == 2) {
            tablaProveedor.setModel(proveedor.mostrarProveedorPorDato("tipo_Actividad", buscar));
        } else if (comboBoxBuscarProveedor.getSelectedIndex() == 3) {
            tablaProveedor.setModel(proveedor.mostrarProveedorPorDato("nom_Representante_Legal", buscar));
        } else if (comboBoxBuscarProveedor.getSelectedIndex() == 4) {
            tablaProveedor.setModel(proveedor.mostrarProveedorPorDato("apell_Representante_Legal", buscar));
        } else if (comboBoxBuscarProveedor.getSelectedIndex() == 5) {
            tablaProveedor.setModel(proveedor.mostrarProveedorPorDato("telefono", buscar));
        } else if (comboBoxBuscarProveedor.getSelectedIndex() == 6) {
            tablaProveedor.setModel(proveedor.mostrarProveedorPorDato("correo", buscar));
        }
    }

    void buscarInventario(String buscar) {
        if (comboBoxBuscarInventario.getSelectedIndex() == 0) {
            tablaInventario.setModel(inventario.mostrarInventarioPorDato("codigo_Pro", buscar));
        } else if (comboBoxBuscarInventario.getSelectedIndex() == 1) {
            tablaInventario.setModel(inventario.mostrarInventarioPorDato("descripción", buscar));
        } else if (comboBoxBuscarInventario.getSelectedIndex() == 2) {
            tablaInventario.setModel(inventario.mostrarInventarioPorDato("precio_Compra", buscar));
        } else if (comboBoxBuscarInventario.getSelectedIndex() == 3) {
            tablaInventario.setModel(inventario.mostrarInventarioPorDato("precio_Venta", buscar));
        } else if (comboBoxBuscarInventario.getSelectedIndex() == 4) {
            tablaInventario.setModel(inventario.mostrarInventarioPorDato("can_Productos", buscar));
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
        panelProveedor = new javax.swing.JPanel();
        textRucProveedor = new javax.swing.JTextField();
        textActividadProveedor = new javax.swing.JTextField();
        textSocialProveedor = new javax.swing.JTextField();
        textApellidoProveedor = new javax.swing.JTextField();
        textNombreProveedor = new javax.swing.JTextField();
        textCorreoProveedor = new javax.swing.JTextField();
        textTelefonoProveedor = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        etiquetaRuc1 = new java.awt.Label();
        etiquetaRazon1 = new java.awt.Label();
        etiquetaActividad1 = new java.awt.Label();
        etiquetaNombreRe1 = new java.awt.Label();
        etiquetaApellidoRe1 = new java.awt.Label();
        etiquetaTelefonoP1 = new java.awt.Label();
        etiquetaCorreoP1 = new java.awt.Label();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panel6 = new javax.swing.JPanel();
        botonAgregarProveedor = new javax.swing.JButton();
        botonEditarProveedor = new javax.swing.JButton();
        botonEliminarProveedor = new javax.swing.JButton();
        botonTraerProveedor = new javax.swing.JButton();
        panelBuscar1 = new javax.swing.JPanel();
        etiquetaBuscar1 = new javax.swing.JLabel();
        comboBoxBuscarProveedor = new javax.swing.JComboBox<>();
        panelBuscarText1 = new javax.swing.JPanel();
        textBuscarProveedor = new javax.swing.JTextField();
        botonBuscarProveedor = new javax.swing.JButton();
        panelTablaProveedor = new javax.swing.JPanel();
        desplazamiento1 = new javax.swing.JScrollPane();
        tablaProveedor = new javax.swing.JTable();
        panelInventario = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        panel7 = new javax.swing.JPanel();
        etiquetaCedula1 = new javax.swing.JLabel();
        etiquetaNombre1 = new javax.swing.JLabel();
        etiquetaApellido1 = new javax.swing.JLabel();
        etiquetaDireccion1 = new javax.swing.JLabel();
        etiquetaCorreo1 = new javax.swing.JLabel();
        panel8 = new javax.swing.JPanel();
        textCodigoInventario = new javax.swing.JTextField();
        textDescripcionInventario = new javax.swing.JTextField();
        textP_CompraInventario = new javax.swing.JTextField();
        textP_VentaInventario = new javax.swing.JTextField();
        textProductosInventario = new javax.swing.JTextField();
        botonBuscarInventario1 = new javax.swing.JButton();
        panel9 = new javax.swing.JPanel();
        botonAgregarInventario = new javax.swing.JButton();
        botonEditarInventario = new javax.swing.JButton();
        botonEliminarInventario = new javax.swing.JButton();
        botonTraerInventario = new javax.swing.JButton();
        panelBuscar2 = new javax.swing.JPanel();
        etiquetaBuscar2 = new javax.swing.JLabel();
        comboBoxBuscarInventario = new javax.swing.JComboBox<>();
        panelBuscarText2 = new javax.swing.JPanel();
        textBuscarInventario = new javax.swing.JTextField();
        botonBuscarInventario = new javax.swing.JButton();
        panel10 = new javax.swing.JPanel();
        desplazamiento2 = new javax.swing.JScrollPane();
        tablaInventario = new javax.swing.JTable();

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
                        .addComponent(panelBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelBuscarText, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE))
                    .addGroup(panelRegistroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addContainerGap(481, Short.MAX_VALUE)
                .addComponent(panel4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
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
                    .addContainerGap(548, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Registro", panelRegistro);

        panelProveedor.setBorder(javax.swing.BorderFactory.createTitledBorder("Proveedor"));

        textRucProveedor.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20)
        );

        textActividadProveedor.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20)
        );

        textSocialProveedor.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20)
        );

        textApellidoProveedor.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20)
        );

        textNombreProveedor.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20)
        );

        textCorreoProveedor.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20)
        );

        textTelefonoProveedor.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20)
        );

        etiquetaRuc1.setAlignment(java.awt.Label.RIGHT);
        etiquetaRuc1.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        etiquetaRuc1.setPreferredSize(new java.awt.Dimension(40, 20));
        etiquetaRuc1.setText("RUC:");

        etiquetaRazon1.setAlignment(java.awt.Label.RIGHT);
        etiquetaRazon1.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        etiquetaRazon1.setMinimumSize(new java.awt.Dimension(79, 20));
        etiquetaRazon1.setPreferredSize(new java.awt.Dimension(80, 20));
        etiquetaRazon1.setText("Razón Social:");

        etiquetaActividad1.setAlignment(java.awt.Label.RIGHT);
        etiquetaActividad1.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        etiquetaActividad1.setPreferredSize(new java.awt.Dimension(99, 20));
        etiquetaActividad1.setText("Tipo de Actividad:");

        etiquetaNombreRe1.setAlignment(java.awt.Label.RIGHT);
        etiquetaNombreRe1.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        etiquetaNombreRe1.setText("Nombre:");

        etiquetaApellidoRe1.setAlignment(java.awt.Label.RIGHT);
        etiquetaApellidoRe1.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        etiquetaApellidoRe1.setText("Apellido:");

        etiquetaTelefonoP1.setAlignment(java.awt.Label.RIGHT);
        etiquetaTelefonoP1.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        etiquetaTelefonoP1.setText("Teléfono:");

        etiquetaCorreoP1.setAlignment(java.awt.Label.RIGHT);
        etiquetaCorreoP1.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        etiquetaCorreoP1.setText("Correo:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(etiquetaCorreoP1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etiquetaRazon1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etiquetaActividad1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(etiquetaRuc1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etiquetaApellidoRe1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etiquetaNombreRe1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etiquetaTelefonoP1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiquetaRuc1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(etiquetaRazon1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(etiquetaActividad1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(etiquetaNombreRe1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(etiquetaApellidoRe1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(etiquetaTelefonoP1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(etiquetaCorreoP1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelProveedorLayout = new javax.swing.GroupLayout(panelProveedor);
        panelProveedor.setLayout(panelProveedorLayout);
        panelProveedorLayout.setHorizontalGroup(
            panelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProveedorLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(panelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textActividadProveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                    .addComponent(textSocialProveedor, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textRucProveedor, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textCorreoProveedor, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textNombreProveedor)
                    .addComponent(textApellidoProveedor)
                    .addComponent(textTelefonoProveedor))
                .addGap(14, 14, 14))
        );
        panelProveedorLayout.setVerticalGroup(
            panelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProveedorLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(textRucProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(textSocialProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(textActividadProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(textNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(textApellidoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(textTelefonoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(textCorreoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelProveedorLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(tipoFuente.fuente(tipoFuente.Skaters, 1, 30)
        );
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REGISTRO PROVEEDOR");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );

        panel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 60, 5));

        botonAgregarProveedor.setFont(tipoFuente.fuente(tipoFuente.FightThis, 2, 25)
        );
        botonAgregarProveedor.setText("Agregar");
        botonAgregarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarProveedorActionPerformed(evt);
            }
        });
        panel6.add(botonAgregarProveedor);

        botonEditarProveedor.setFont(tipoFuente.fuente(tipoFuente.FightThis, 2, 25)
        );
        botonEditarProveedor.setText("Editar");
        botonEditarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEditarProveedorActionPerformed(evt);
            }
        });
        panel6.add(botonEditarProveedor);

        botonEliminarProveedor.setFont(tipoFuente.fuente(tipoFuente.FightThis, 2, 25)
        );
        botonEliminarProveedor.setText("Eliminar");
        botonEliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarProveedorActionPerformed(evt);
            }
        });
        panel6.add(botonEliminarProveedor);

        botonTraerProveedor.setFont(tipoFuente.fuente(tipoFuente.FightThis, 2, 25)
        );
        botonTraerProveedor.setText("Traer");
        panel6.add(botonTraerProveedor);

        panelBuscar1.setLayout(new java.awt.GridBagLayout());

        etiquetaBuscar1.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 22));
        etiquetaBuscar1.setText("Buscar Cliente:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
        panelBuscar1.add(etiquetaBuscar1, gridBagConstraints);

        comboBoxBuscarProveedor.setFont(tipoFuente.fuente(tipoFuente.Emberly, 1, 24)
        );
        comboBoxBuscarProveedor.setModel(modeloComboProveedor);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 5, 20, 0);
        panelBuscar1.add(comboBoxBuscarProveedor, gridBagConstraints);

        panelBuscarText1.setLayout(new java.awt.GridBagLayout());

        textBuscarProveedor.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        textBuscarProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textBuscarProveedorKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 260;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
        panelBuscarText1.add(textBuscarProveedor, gridBagConstraints);

        botonBuscarProveedor.setFont(tipoFuente.fuente(tipoFuente.FightThis, 2, 20));
        botonBuscarProveedor.setText("Buscar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
        panelBuscarText1.add(botonBuscarProveedor, gridBagConstraints);

        tablaProveedor.setFont(new java.awt.Font("Constantia", 2, 18)); // NOI18N
        tablaProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaProveedor.setRowMargin(0);
        tablaProveedor.setShowVerticalLines(false);
        tablaProveedor.setBounds(0, 650, 830, 300);
        tablaProveedor.getTableHeader().setFont(new Font("Constantia", Font.BOLD, 22));
        tablaProveedor.getTableHeader().setOpaque(true);
        tablaProveedor.getTableHeader().setBackground(new Color(32, 136, 203));
        tablaProveedor.getTableHeader().setForeground(new Color(255, 255, 255));
        tablaProveedor.setRowHeight(25);
        tablaProveedor.setBackground(new Color(204, 255, 204));
        tablaProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProveedorMouseClicked(evt);
            }
        });
        desplazamiento1.setViewportView(tablaProveedor);

        javax.swing.GroupLayout panelTablaProveedorLayout = new javax.swing.GroupLayout(panelTablaProveedor);
        panelTablaProveedor.setLayout(panelTablaProveedorLayout);
        panelTablaProveedorLayout.setHorizontalGroup(
            panelTablaProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desplazamiento1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panelTablaProveedorLayout.setVerticalGroup(
            panelTablaProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablaProveedorLayout.createSequentialGroup()
                .addComponent(desplazamiento1, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTablaProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(panelBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelBuscarText1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBuscar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBuscarText1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelTablaProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Proveedor", jPanel2);

        jLabel2.setFont(tipoFuente.fuente(tipoFuente.Skaters, 1, 30)
        );
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Registro de Inventario");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );

        panel7.setLayout(new java.awt.GridBagLayout());

        etiquetaCedula1.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25));
        etiquetaCedula1.setText("Código:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(47, 0, 0, 0);
        panel7.add(etiquetaCedula1, gridBagConstraints);

        etiquetaNombre1.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25));
        etiquetaNombre1.setText("Descripción:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(25, 0, 0, 0);
        panel7.add(etiquetaNombre1, gridBagConstraints);

        etiquetaApellido1.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25));
        etiquetaApellido1.setText("Precio Compra:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(25, 0, 0, 0);
        panel7.add(etiquetaApellido1, gridBagConstraints);

        etiquetaDireccion1.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25));
        etiquetaDireccion1.setText("Precio Venta:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(25, 0, 0, 0);
        panel7.add(etiquetaDireccion1, gridBagConstraints);

        etiquetaCorreo1.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25));
        etiquetaCorreo1.setText("Can Productos:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(25, 0, 0, 0);
        panel7.add(etiquetaCorreo1, gridBagConstraints);

        panel8.setLayout(new java.awt.GridBagLayout());

        textCodigoInventario.setBackground(new java.awt.Color(255, 255, 255));
        textCodigoInventario.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25));
        textCodigoInventario.setForeground(new java.awt.Color(0, 0, 0));
        textCodigoInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCodigoInventarioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 260;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 0, 0);
        panel8.add(textCodigoInventario, gridBagConstraints);

        textDescripcionInventario.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25)
        );
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 260;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        panel8.add(textDescripcionInventario, gridBagConstraints);

        textP_CompraInventario.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25)
        );
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 260;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        panel8.add(textP_CompraInventario, gridBagConstraints);

        textP_VentaInventario.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25)
        );
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 260;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        panel8.add(textP_VentaInventario, gridBagConstraints);

        textProductosInventario.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25)
        );
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 260;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        panel8.add(textProductosInventario, gridBagConstraints);

        botonBuscarInventario1.setFont(tipoFuente.fuente(tipoFuente.FightThis, 2, 30));
        botonBuscarInventario1.setText("Buscar");
        botonBuscarInventario1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarInventario1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.insets = new java.awt.Insets(50, 0, 0, 0);
        panel8.add(botonBuscarInventario1, gridBagConstraints);

        panel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        botonAgregarInventario.setFont(tipoFuente.fuente(tipoFuente.FightThis, 2, 30)
        );
        botonAgregarInventario.setText("Agregar");
        botonAgregarInventario.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                botonAgregarInventarioMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botonAgregarInventarioMouseMoved(evt);
            }
        });
        botonAgregarInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarInventarioActionPerformed(evt);
            }
        });
        panel9.add(botonAgregarInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 5, -1, -1));

        botonEditarInventario.setFont(tipoFuente.fuente(tipoFuente.FightThis, 2, 30)
        );
        botonEditarInventario.setText("Editar");
        botonEditarInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEditarInventarioActionPerformed(evt);
            }
        });
        panel9.add(botonEditarInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(338, 5, -1, -1));

        botonEliminarInventario.setFont(tipoFuente.fuente(tipoFuente.FightThis, 2, 30)
        );
        botonEliminarInventario.setText("Eliminar");
        botonEliminarInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarInventarioActionPerformed(evt);
            }
        });
        panel9.add(botonEliminarInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 5, -1, -1));

        botonTraerInventario.setFont(tipoFuente.fuente(tipoFuente.FightThis, 2, 30));
        botonTraerInventario.setText("Traer");
        panel9.add(botonTraerInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(597, 5, -1, -1));

        panelBuscar2.setLayout(new java.awt.GridBagLayout());

        etiquetaBuscar2.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 24));
        etiquetaBuscar2.setText("Buscar Cliente:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
        panelBuscar2.add(etiquetaBuscar2, gridBagConstraints);

        comboBoxBuscarInventario.setFont(tipoFuente.fuente(tipoFuente.Emberly, 1, 24)
        );
        comboBoxBuscarInventario.setModel(modeloComboInventario);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 5, 20, 0);
        panelBuscar2.add(comboBoxBuscarInventario, gridBagConstraints);

        panelBuscarText2.setLayout(new java.awt.GridBagLayout());

        textBuscarInventario.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25));
        textBuscarInventario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textBuscarInventarioKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 260;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
        panelBuscarText2.add(textBuscarInventario, gridBagConstraints);

        botonBuscarInventario.setFont(tipoFuente.fuente(tipoFuente.FightThis, 2, 24));
        botonBuscarInventario.setText("Buscar");
        botonBuscarInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarInventarioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
        panelBuscarText2.add(botonBuscarInventario, gridBagConstraints);

        javax.swing.GroupLayout panel10Layout = new javax.swing.GroupLayout(panel10);
        panel10.setLayout(panel10Layout);
        panel10Layout.setHorizontalGroup(
            panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 848, Short.MAX_VALUE)
        );
        panel10Layout.setVerticalGroup(
            panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 233, Short.MAX_VALUE)
        );

        tablaInventario.setFont(new java.awt.Font("Constantia", 2, 18)); // NOI18N
        tablaInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaInventario.setRowMargin(0);
        tablaInventario.setShowVerticalLines(false);
        tablaInventario.setBounds(0, 650, 830, 300);
        tablaInventario.getTableHeader().setFont(new Font("Constantia", Font.BOLD, 22));
        tablaInventario.getTableHeader().setOpaque(true);
        tablaInventario.getTableHeader().setBackground(new Color(32, 136, 203));
        tablaInventario.getTableHeader().setForeground(new Color(255, 255, 255));
        tablaInventario.setRowHeight(25);
        tablaInventario.setBackground(new Color(204, 255, 204));
        tablaInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaInventarioMouseClicked(evt);
            }
        });
        desplazamiento2.setViewportView(tablaInventario);

        javax.swing.GroupLayout panelInventarioLayout = new javax.swing.GroupLayout(panelInventario);
        panelInventario.setLayout(panelInventarioLayout);
        panelInventarioLayout.setHorizontalGroup(
            panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel9, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
            .addGroup(panelInventarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInventarioLayout.createSequentialGroup()
                        .addComponent(panel7, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelInventarioLayout.createSequentialGroup()
                        .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelInventarioLayout.createSequentialGroup()
                                .addComponent(panelBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelBuscarText2, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addComponent(desplazamiento2)))
            .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelInventarioLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelInventarioLayout.setVerticalGroup(
            panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInventarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel7, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    .addComponent(panel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(panel9, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBuscar2, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                    .addComponent(panelBuscarText2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(desplazamiento2, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
            .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelInventarioLayout.createSequentialGroup()
                    .addGap(336, 336, 336)
                    .addComponent(panel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(336, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Inventario", panelInventario);

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

    private boolean datosProveedor() {
        if (textRucProveedor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "En campo del Ruc está vacio", "ERROR", JOptionPane.ERROR_MESSAGE);
            textRucProveedor.requestFocus();
            return false;
        }

        if (textSocialProveedor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "El campo Razón Social no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            textSocialProveedor.requestFocus();
            return false;
        }
        if (textActividadProveedor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "El campo Actividad no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            textActividadProveedor.requestFocus();
            return false;
        }
        if (textNombreProveedor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "El campo Nombre no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            textNombreProveedor.requestFocus();
            return false;
        }
        if (textApellidoProveedor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "El campo Apellido no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            textApellidoProveedor.requestFocus();
            return false;
        }
        if (!utilidades.validarNumeros(textTelefonoProveedor.getText())) {
            JOptionPane.showMessageDialog(rootPane, "Los datos ingresados en el teléfono no son validos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            textTelefonoProveedor.requestFocus();
            return false;
        }
        if (textCorreoProveedor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "El campo correo no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            textCorreoProveedor.requestFocus();
            return false;
        }

        if (!utilidades.validarCorreo(textCorreoProveedor.getText())) {
            JOptionPane.showMessageDialog(rootPane, "Los datos del correo son incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            textCorreoProveedor.requestFocus();
            return false;
        }
        return true;
    }

    private boolean datosInventario() {
        if (textCodigoInventario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "En campo del Cógio está vacio", "ERROR", JOptionPane.ERROR_MESSAGE);
            textRucProveedor.requestFocus();
            return false;
        }

        if (textDescripcionInventario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "El campo Dirección no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            textSocialProveedor.requestFocus();
            return false;
        }
        if (textP_CompraInventario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "El campo Precio Compra no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            textActividadProveedor.requestFocus();
            return false;
        }
        if (textP_VentaInventario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "El campo Precio Venta no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            textNombreProveedor.requestFocus();
            return false;
        }
        if (textProductosInventario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "El campo Productos no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            textApellidoProveedor.requestFocus();
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

    private void tablaProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProveedorMouseClicked
        int fila = tablaProveedor.getSelectedRow();
        textRucProveedor.setText(tablaProveedor.getValueAt(fila, 1).toString());
        textSocialProveedor.setText(tablaProveedor.getValueAt(fila, 2).toString());
        textActividadProveedor.setText(tablaProveedor.getValueAt(fila, 3).toString());
        textNombreProveedor.setText(tablaProveedor.getValueAt(fila, 4).toString());
        textApellidoProveedor.setText(tablaProveedor.getValueAt(fila, 5).toString());
        textTelefonoProveedor.setText(tablaProveedor.getValueAt(fila, 6).toString());
        textCorreoProveedor.setText(tablaProveedor.getValueAt(fila, 7).toString());
    }//GEN-LAST:event_tablaProveedorMouseClicked

    private void botonAgregarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarProveedorActionPerformed
        if (datosProveedor()) {
            Proveedor pro = new Proveedor(textRucProveedor.getText(), textSocialProveedor.getText(), textActividadProveedor.getText(), textNombreProveedor.getText(), textApellidoProveedor.getText(), textTelefonoProveedor.getText(), textCorreoProveedor.getText());
            proveedor.ingresarProveedor(pro);
            mostrarTabla();
            camposVacionProveedor();
        }

    }//GEN-LAST:event_botonAgregarProveedorActionPerformed

    private void botonEliminarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarProveedorActionPerformed
        int filaSeleccionada = tablaProveedor.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String valor = tablaProveedor.getValueAt(filaSeleccionada, 0).toString();
            proveedor.eliminarCliente(valor);
            mostrarTabla();
            camposVacionProveedor();
        }
    }//GEN-LAST:event_botonEliminarProveedorActionPerformed

    private void botonEditarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarProveedorActionPerformed
        int fila = tablaProveedor.getSelectedRow();
        if (datosProveedor()) {
            String valor = tablaProveedor.getValueAt(fila, 0).toString();
            Proveedor pro = new Proveedor(Integer.parseInt(valor), textRucProveedor.getText(), textSocialProveedor.getText(), textActividadProveedor.getText(), textNombreProveedor.getText(), textApellidoProveedor.getText(), textTelefonoProveedor.getText(), textCorreoProveedor.getText());
            proveedor.editarProveedor(pro);
            mostrarTabla();
            camposVacionProveedor();
        }

    }//GEN-LAST:event_botonEditarProveedorActionPerformed

    private void textBuscarProveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBuscarProveedorKeyReleased
        buscarProveedor(textBuscarProveedor.getText());
    }//GEN-LAST:event_textBuscarProveedorKeyReleased

    private void textCodigoInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCodigoInventarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCodigoInventarioActionPerformed

    private void botonBuscarInventario1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarInventario1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonBuscarInventario1ActionPerformed

    private void botonAgregarInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarInventarioActionPerformed
        if (datosInventario()) {
            Inventario in = new Inventario(textCodigoInventario.getText(), textDescripcionInventario.getText(), textP_CompraInventario.getText(), textP_VentaInventario.getText(), textProductosInventario.getText());
            inventario.ingresarInventario(in);
            mostrarTabla();
            camposVaciosInventario();
        }
    }//GEN-LAST:event_botonAgregarInventarioActionPerformed

    private void botonEditarInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarInventarioActionPerformed
        if (inveBoolean == true) {
            Inventario in = new Inventario(inve.getIdInventario(), textCodigoInventario.getText(), textDescripcionInventario.getText(), textP_CompraInventario.getText(), textP_VentaInventario.getText(), textProductosInventario.getText());
            inventario.editarInventario(in);
            mostrarTabla();
            camposVaciosInventario();
        }


    }//GEN-LAST:event_botonEditarInventarioActionPerformed

    private void botonEliminarInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarInventarioActionPerformed
        int fila = tablaInventario.getSelectedRow();
        if (fila >= 0) {
            inventario.eliminarInventario(inve.getIdInventario());
            mostrarTabla();
            camposVaciosInventario();
        }
    }//GEN-LAST:event_botonEliminarInventarioActionPerformed

    private void textBuscarInventarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBuscarInventarioKeyReleased
        buscarInventario(textBuscarInventario.getText());
    }//GEN-LAST:event_textBuscarInventarioKeyReleased

    private void botonBuscarInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarInventarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonBuscarInventarioActionPerformed

    private void tablaInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaInventarioMouseClicked
        int fila = tablaInventario.getSelectedRow();
        if (fila >= 0) {
            String id = tablaInventario.getValueAt(fila, 0).toString();
            String codigo = tablaInventario.getValueAt(fila, 1).toString();
            String descri = tablaInventario.getValueAt(fila, 2).toString();
            String compra = tablaInventario.getValueAt(fila, 3).toString();
            String venta = tablaInventario.getValueAt(fila, 4).toString();
            String producto = tablaInventario.getValueAt(fila, 5).toString();
            textCodigoInventario.setText(codigo);
            textDescripcionInventario.setText(descri);
            textP_CompraInventario.setText(compra);
            textP_VentaInventario.setText(venta);
            textProductosInventario.setText(producto);
            inve = new Inventario(Integer.parseInt(id), codigo, descri, compra, venta, producto);
            inveBoolean = true;
        }
    }//GEN-LAST:event_tablaInventarioMouseClicked

    private void botonAgregarInventarioMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonAgregarInventarioMouseDragged
        if (movi) {
            x = evt.getX();
            y = evt.getY();
            movi = false;
        }

        botonAgregarInventario.setLocation(botonAgregarInventario.getX() + evt.getX() - x, botonAgregarInventario.getY() + evt.getY() - y);
        variableX = botonAgregarInventario.getX() + evt.getX() - x;
        variableY = botonAgregarInventario.getY() + evt.getY() - y;
        
        crearArchivo();
    }//GEN-LAST:event_botonAgregarInventarioMouseDragged

    private void botonAgregarInventarioMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonAgregarInventarioMouseMoved
        if (!movi) {
            movi = true;
        }
    }//GEN-LAST:event_botonAgregarInventarioMouseMoved

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

     public void crearArchivo() {
        File archivo = new File("posicion.txt");
        try {
            FileWriter escribir = new FileWriter(archivo);
            escribir.write(variableX + "_" + variableY);
            escribir.close();
            leerArchivo();
        } catch (IOException ex) {
            System.err.println("Error: " + ex);
        }
    }

    public void leerArchivo() {
        String cadena, dato[];
        try {
            FileReader lector = new FileReader("posicion.txt");
            BufferedReader leer = new BufferedReader(lector);
            cadena = leer.readLine();
            System.out.println(cadena);
            dato = cadena.split("_");
            variableX = Integer.parseInt(dato[0]);
            variableY = Integer.parseInt(dato[1]);
            botonAgregarInventario.setBounds(variableX, variableY, 110, 50);
        } catch (FileNotFoundException ex) {
            System.err.println("Error: " + ex);
        } catch (IOException ex) {
            System.err.println("Error: " + ex);
        }
    }
    public static void main(String args[]) {
        
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
    private javax.swing.JButton botonAgregarInventario;
    private javax.swing.JButton botonAgregarProveedor;
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonBuscar2;
    private javax.swing.JButton botonBuscarInventario;
    private javax.swing.JButton botonBuscarInventario1;
    private javax.swing.JButton botonBuscarProveedor;
    private javax.swing.JButton botonEditar;
    private javax.swing.JButton botonEditarInventario;
    private javax.swing.JButton botonEditarProveedor;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonEliminarInventario;
    private javax.swing.JButton botonEliminarProveedor;
    private javax.swing.JButton botonTraer;
    private javax.swing.JButton botonTraerInventario;
    private javax.swing.JButton botonTraerProveedor;
    private javax.swing.JComboBox<String> comboBoxBuscar;
    private javax.swing.JComboBox<String> comboBoxBuscarInventario;
    private javax.swing.JComboBox<String> comboBoxBuscarProveedor;
    private javax.swing.JScrollPane desplazamiento;
    private javax.swing.JScrollPane desplazamiento1;
    private javax.swing.JScrollPane desplazamiento2;
    private javax.swing.JLabel etiquetTelefono;
    private java.awt.Label etiquetaActividad1;
    private javax.swing.JLabel etiquetaApellido;
    private javax.swing.JLabel etiquetaApellido1;
    private java.awt.Label etiquetaApellidoRe1;
    private javax.swing.JLabel etiquetaBuscar;
    private javax.swing.JLabel etiquetaBuscar1;
    private javax.swing.JLabel etiquetaBuscar2;
    private javax.swing.JLabel etiquetaCedula;
    private javax.swing.JLabel etiquetaCedula1;
    private javax.swing.JLabel etiquetaCorreo;
    private javax.swing.JLabel etiquetaCorreo1;
    private java.awt.Label etiquetaCorreoP1;
    private javax.swing.JLabel etiquetaDireccion;
    private javax.swing.JLabel etiquetaDireccion1;
    private javax.swing.JLabel etiquetaNombre;
    private javax.swing.JLabel etiquetaNombre1;
    private java.awt.Label etiquetaNombreRe1;
    private java.awt.Label etiquetaRazon1;
    private javax.swing.JLabel etiquetaRegistro;
    private java.awt.Label etiquetaRuc1;
    private java.awt.Label etiquetaTelefonoP1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel10;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
    private javax.swing.JPanel panel4;
    private javax.swing.JPanel panel5;
    private javax.swing.JPanel panel6;
    private javax.swing.JPanel panel7;
    private javax.swing.JPanel panel8;
    private javax.swing.JPanel panel9;
    private javax.swing.JPanel panelBuscar;
    private javax.swing.JPanel panelBuscar1;
    private javax.swing.JPanel panelBuscar2;
    private javax.swing.JPanel panelBuscarText;
    private javax.swing.JPanel panelBuscarText1;
    private javax.swing.JPanel panelBuscarText2;
    private javax.swing.JPanel panelInventario;
    private javax.swing.JPanel panelProveedor;
    private javax.swing.JPanel panelRegistro;
    private javax.swing.JPanel panelTablaProveedor;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tablaInventario;
    private javax.swing.JTable tablaProveedor;
    private javax.swing.JTextField textActividadProveedor;
    private javax.swing.JTextField textApellido;
    private javax.swing.JTextField textApellidoProveedor;
    private javax.swing.JTextField textBuscar;
    private javax.swing.JTextField textBuscarInventario;
    private javax.swing.JTextField textBuscarProveedor;
    private javax.swing.JTextField textCedula;
    private javax.swing.JTextField textCodigoInventario;
    private javax.swing.JTextField textCorreo;
    private javax.swing.JTextField textCorreoProveedor;
    private javax.swing.JTextField textDescripcionInventario;
    private javax.swing.JTextField textDireccion;
    private javax.swing.JTextField textNombre;
    private javax.swing.JTextField textNombreProveedor;
    private javax.swing.JTextField textP_CompraInventario;
    private javax.swing.JTextField textP_VentaInventario;
    private javax.swing.JTextField textProductosInventario;
    private javax.swing.JTextField textRucProveedor;
    private javax.swing.JTextField textSocialProveedor;
    private javax.swing.JTextField textTelefono;
    private javax.swing.JTextField textTelefonoProveedor;
    // End of variables declaration//GEN-END:variables
}
