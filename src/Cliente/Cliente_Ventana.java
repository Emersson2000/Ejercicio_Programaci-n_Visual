package Cliente;

import Tipografias.Fuentes;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeListener;
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
    private Controlador_BD_NotaVenta notaVenta = new Controlador_BD_NotaVenta();
    private Utilidades utilidades = new Utilidades();
    private ListSelectionListener seleccionar;
    private Persona p;
    private Inventario inve = new Inventario();
    private Inventario inveNotaVenta = new Inventario();
    private ModeloTabla model;
    private DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
    private DefaultComboBoxModel modeloComboGenero = new DefaultComboBoxModel();
    private DefaultComboBoxModel modeloComboProveedor = new DefaultComboBoxModel();
    private DefaultComboBoxModel modeloComboInventario = new DefaultComboBoxModel();
    private DefaultComboBoxModel modeloComboNotaVenta = new DefaultComboBoxModel();
    private DefaultTableModel modeloNotaVenta = new DefaultTableModel();
    private GestionPersona gestionPersona;
    private boolean lista = true;
    private boolean inveBoolean;
    private boolean proveedorBoolean;
    private int x;
    private int y;
    private boolean movi = true;
    private int variableX;
    private int variableY;
    private double subtotal = 0;
    private double iva = 0;

    public Cliente_Ventana() {
        model = new ModeloTabla(persona.mostrarPersonasV());
        initComponents();
        this.setLocationRelativeTo(null);
        this.setSize(990, 1025);
        iniciarComponentes();
        leerArchivo();
        panel9.setLayout(null);
        persona.numeros(textDNI);
        persona.numeros(textTelefono);
//        persona.letras(textDNI);
        persona.letras(textApellido);
        proveedor.numeros(textRucProveedor);
        inventario.soloNumeros(textCodigoInventario);
        radioBotonCedula.setSelected(true);
        gestionPersona = new GestionPersona(textDNI, textNombre, textApellido, textDireccion, textCorreo, textTelefono, comboBoxGenero, comboBoxBuscar, this, dateNacimiento, tabla);

    }

    private void iniciarComponentes() {
        colocarTabla();
        colocarComboBox();
        colocarComboBoxGenero();
        colocarComboBoxProveedor();
        colocarComboBoxInventario();
        colocarComboBoxNotaVenta();
        colocarTablaNotaVenta();
        radioBotones();
        verificarCedulaOPasaporte();
    }

    public void radioBotones() {
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(radioBotonCedula);
        grupo.add(radioBotonPasaporte);

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
        textCantidadInventario.setText("");
        textPrecioSinIvaInventario.setText("");
        textPrecioConIvaIventario.setText("");
        textPrecioMayoristaInventario.setText("");
        textPrecioFigoInventario.setText("");
        textPrecioNormalInventario.setText("");
        dateFechaCaducidad.setDate(null);
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

    void colocarComboBoxGenero() {
        modeloComboGenero.addElement("No definido");
        modeloComboGenero.addElement("Hombre");
        modeloComboGenero.addElement("Mujer");

    }

    void verificarCedulaOPasaporte() {

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

    void colocarComboBoxNotaVenta() {
        modeloComboNotaVenta.addElement("Efectivo");
        modeloComboNotaVenta.addElement("Tárjeta de Crédito");
    }

    void colocarTablaNotaVenta() {
        modeloNotaVenta.addColumn("Cantidad");
        modeloNotaVenta.addColumn("Descripción");
        modeloNotaVenta.addColumn("SubTotal");
        modeloNotaVenta.addColumn("Total");
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
        panelVenta = new javax.swing.JTabbedPane();
        panelRegistro = new javax.swing.JPanel();
        panel4 = new javax.swing.JPanel();
        botonAgregar = new javax.swing.JButton();
        botonEditar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonTraer = new javax.swing.JButton();
        panel3 = new javax.swing.JPanel();
        textDNI = new javax.swing.JTextField();
        textApellido = new javax.swing.JTextField();
        textDireccion = new javax.swing.JTextField();
        textCorreo = new javax.swing.JTextField();
        textTelefono = new javax.swing.JTextField();
        radioBotonPasaporte = new javax.swing.JRadioButton();
        radioBotonCedula = new javax.swing.JRadioButton();
        textNombre = new javax.swing.JTextField();
        comboBoxGenero = new javax.swing.JComboBox<>();
        dateNacimiento = new com.toedter.calendar.JDateChooser();
        panel2 = new javax.swing.JPanel();
        etiquetaCedula = new javax.swing.JLabel();
        etiquetaNombre = new javax.swing.JLabel();
        etiquetaApellido = new javax.swing.JLabel();
        etiquetaDireccion = new javax.swing.JLabel();
        etiquetaCorreo = new javax.swing.JLabel();
        etiquetTelefono1 = new javax.swing.JLabel();
        etiquetTelefono2 = new javax.swing.JLabel();
        etiquetTelefono3 = new javax.swing.JLabel();
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
        etiquetaDireccion2 = new javax.swing.JLabel();
        etiquetaDireccion3 = new javax.swing.JLabel();
        etiquetaDireccion4 = new javax.swing.JLabel();
        etiquetaDireccion5 = new javax.swing.JLabel();
        panel8 = new javax.swing.JPanel();
        textCodigoInventario = new javax.swing.JTextField();
        textCantidadInventario = new javax.swing.JTextField();
        textPrecioSinIvaInventario = new javax.swing.JTextField();
        textDescripcionInventario = new javax.swing.JTextField();
        textPrecioConIvaIventario = new javax.swing.JTextField();
        textPrecioMayoristaInventario = new javax.swing.JTextField();
        textPrecioFigoInventario = new javax.swing.JTextField();
        textPrecioNormalInventario = new javax.swing.JTextField();
        dateFechaCaducidad = new com.toedter.calendar.JDateChooser();
        panel9 = new javax.swing.JPanel();
        botonAgregarInventario = new javax.swing.JButton();
        botonEditarInventario = new javax.swing.JButton();
        botonEliminarInventario = new javax.swing.JButton();
        botonTraerInventario = new javax.swing.JButton();
        textInventario2 = new javax.swing.JTextField();
        panelBuscar2 = new javax.swing.JPanel();
        etiquetaBuscar2 = new javax.swing.JLabel();
        comboBoxBuscarInventario = new javax.swing.JComboBox<>();
        panelBuscarText2 = new javax.swing.JPanel();
        textBuscarInventario = new javax.swing.JTextField();
        botonBuscarInventario = new javax.swing.JButton();
        desplazamiento2 = new javax.swing.JScrollPane();
        tablaInventario = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        etiquetaNombre2 = new javax.swing.JLabel();
        etiquetaNombre3 = new javax.swing.JLabel();
        etiquetaNombre4 = new javax.swing.JLabel();
        etiquetaNombre5 = new javax.swing.JLabel();
        etiquetaNombre7 = new javax.swing.JLabel();
        etiquetaNombre8 = new javax.swing.JLabel();
        textNumero_NotaVenta = new javax.swing.JTextField();
        textTelefono_NotaVenta = new javax.swing.JTextField();
        textNombreCliente_NotaVenta = new javax.swing.JTextField();
        textCedulaNotaVenta = new javax.swing.JTextField();
        textDireccion_NotaVenta = new javax.swing.JTextField();
        textFechaVenta_NotaVenta = new javax.swing.JTextField();
        etiquetaNombre6 = new javax.swing.JLabel();
        textIdProducto_NotaVenta = new javax.swing.JTextField();
        etiquetaNombre9 = new javax.swing.JLabel();
        textCantidad_NotaVenta = new javax.swing.JTextField();
        botonAgregar_NotaVenta = new javax.swing.JButton();
        botonBusquedadAvanzada_NotaVenta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaNotaVenta = new javax.swing.JTable();
        etiquetaNombre10 = new javax.swing.JLabel();
        comboPagoNotaVenta = new javax.swing.JComboBox<>();
        etiquetaNombre11 = new javax.swing.JLabel();
        etiquetaNombre12 = new javax.swing.JLabel();
        etiquetaNombre13 = new javax.swing.JLabel();
        textSubTotal_NotaVenta = new javax.swing.JTextField();
        textTotal_NotaVenta = new javax.swing.JTextField();
        textIva_NotaVenta = new javax.swing.JTextField();
        menuBar = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        menuFile = new javax.swing.JMenu();
        menuAcercaDe = new javax.swing.JMenu();

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
        botonAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disco-flexible.png"))); // NOI18N
        botonAgregar.setText("Agregar");
        botonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarActionPerformed(evt);
            }
        });
        panel4.add(botonAgregar);

        botonEditar.setFont(tipoFuente.fuente(tipoFuente.FightThis, 2, 30)
        );
        botonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar.png"))); // NOI18N
        botonEditar.setText("Editar");
        botonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEditarActionPerformed(evt);
            }
        });
        panel4.add(botonEditar);

        botonEliminar.setFont(tipoFuente.fuente(tipoFuente.FightThis, 2, 30)
        );
        botonEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/basura.png"))); // NOI18N
        botonEliminar.setText("Eliminar");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });
        panel4.add(botonEliminar);

        botonTraer.setFont(tipoFuente.fuente(tipoFuente.FightThis, 2, 30));
        botonTraer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/frente.png"))); // NOI18N
        botonTraer.setText("Traer");
        botonTraer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonTraerActionPerformed(evt);
            }
        });
        panel4.add(botonTraer);

        panel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textDNI.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25)
        );
        textDNI.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textDNIFocusLost(evt);
            }
        });
        panel3.add(textDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 450, -1));

        textApellido.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25)
        );
        panel3.add(textApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 641, -1));

        textDireccion.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25)
        );
        panel3.add(textDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 641, -1));

        textCorreo.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25)
        );
        panel3.add(textCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 641, -1));

        textTelefono.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25)
        );
        panel3.add(textTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 641, -1));

        radioBotonPasaporte.setText("Pasaporte");
        radioBotonPasaporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioBotonPasaporteMouseClicked(evt);
            }
        });
        panel3.add(radioBotonPasaporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, -1, -1));

        radioBotonCedula.setText("Cédula");
        panel3.add(radioBotonCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, -1, -1));

        textNombre.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25)
        );
        panel3.add(textNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 641, -1));

        comboBoxGenero.setFont(tipoFuente.fuente(tipoFuente.Emberly, 1, 24)
        );
        comboBoxGenero.setModel(modeloComboGenero);
        panel3.add(comboBoxGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 150, -1));

        dateNacimiento.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25));
        panel3.add(dateNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 290, 40));

        panel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        etiquetaCedula.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25));
        etiquetaCedula.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        etiquetaCedula.setText("DNI:");
        panel2.add(etiquetaCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 130, -1));

        etiquetaNombre.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25));
        etiquetaNombre.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        etiquetaNombre.setText("Nombre:");
        panel2.add(etiquetaNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 130, -1));

        etiquetaApellido.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25));
        etiquetaApellido.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        etiquetaApellido.setText("Apellido:");
        panel2.add(etiquetaApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 130, -1));

        etiquetaDireccion.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25));
        etiquetaDireccion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        etiquetaDireccion.setText("Dirección:");
        panel2.add(etiquetaDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 130, -1));

        etiquetaCorreo.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25));
        etiquetaCorreo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        etiquetaCorreo.setText("Correo:");
        panel2.add(etiquetaCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 224, 130, -1));

        etiquetTelefono1.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25));
        etiquetTelefono1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        etiquetTelefono1.setText("Teléfono:");
        panel2.add(etiquetTelefono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 130, -1));

        etiquetTelefono2.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25));
        etiquetTelefono2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        etiquetTelefono2.setText("Nacimiento:");
        panel2.add(etiquetTelefono2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 150, -1));

        etiquetTelefono3.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 25));
        etiquetTelefono3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        etiquetTelefono3.setText("Género:");
        panel2.add(etiquetTelefono3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 130, -1));

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
        tabla.getTableHeader().setFont(new Font("Constantia", Font.BOLD, 18));
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
            .addGroup(panel5Layout.createSequentialGroup()
                .addComponent(desplazamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 58, Short.MAX_VALUE))
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
                        .addComponent(panelBuscarText, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE))
                    .addGroup(panelRegistroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelRegistroLayout.createSequentialGroup()
                                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegistroLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panelRegistroLayout.setVerticalGroup(
            panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRegistroLayout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addGroup(panelRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                    .addContainerGap(848, Short.MAX_VALUE)))
        );

        panelVenta.addTab("Registro", panelRegistro);

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
                    .addComponent(textActividadProveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
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

        panelVenta.addTab("Proveedor", jPanel2);

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

        panel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        etiquetaCedula1.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        etiquetaCedula1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaCedula1.setText("Código:");
        panel7.add(etiquetaCedula1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, -1));

        etiquetaNombre1.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        etiquetaNombre1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaNombre1.setText("Descripción:");
        panel7.add(etiquetaNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 230, -1));

        etiquetaApellido1.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        etiquetaApellido1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaApellido1.setText("Precio Compra sin Iva:");
        panel7.add(etiquetaApellido1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 230, -1));

        etiquetaDireccion1.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        etiquetaDireccion1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaDireccion1.setText("Fecha Caducidad:");
        panel7.add(etiquetaDireccion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 230, -1));

        etiquetaCorreo1.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        etiquetaCorreo1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaCorreo1.setText("Cantidad Productos:");
        panel7.add(etiquetaCorreo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 230, -1));

        etiquetaDireccion2.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        etiquetaDireccion2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaDireccion2.setText("Precio Cliente Fijo:");
        panel7.add(etiquetaDireccion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 230, -1));

        etiquetaDireccion3.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        etiquetaDireccion3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaDireccion3.setText("Precio Compra con Iva:");
        panel7.add(etiquetaDireccion3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 230, -1));

        etiquetaDireccion4.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        etiquetaDireccion4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaDireccion4.setText(" Precio Mayorista:");
        panel7.add(etiquetaDireccion4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 230, -1));

        etiquetaDireccion5.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        etiquetaDireccion5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaDireccion5.setText("Precio Cliente Normal:");
        panel7.add(etiquetaDireccion5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 230, -1));

        panel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textCodigoInventario.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        panel8.add(textCodigoInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 592, -1));

        textCantidadInventario.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        panel8.add(textCantidadInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 592, -1));

        textPrecioSinIvaInventario.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        utilidades.Letras(textPrecioSinIvaInventario);
        textPrecioSinIvaInventario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textPrecioSinIvaInventarioKeyReleased(evt);
            }
        });
        panel8.add(textPrecioSinIvaInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 592, -1));

        textDescripcionInventario.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        panel8.add(textDescripcionInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 592, -1));

        textPrecioConIvaIventario.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        utilidades.noDejaEscribir(textPrecioConIvaIventario);

        panel8.add(textPrecioConIvaIventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 592, -1));

        textPrecioMayoristaInventario.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        utilidades.noDejaEscribir(textPrecioMayoristaInventario);

        panel8.add(textPrecioMayoristaInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 592, -1));

        textPrecioFigoInventario.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        utilidades.noDejaEscribir(textPrecioFigoInventario);
        panel8.add(textPrecioFigoInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 592, -1));

        textPrecioNormalInventario.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        utilidades.noDejaEscribir(textPrecioNormalInventario);
        panel8.add(textPrecioNormalInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 592, -1));

        dateFechaCaducidad.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        panel8.add(dateFechaCaducidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 590, 30));

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

        textInventario2.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        textInventario2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textInventario2ActionPerformed(evt);
            }
        });
        panel9.add(textInventario2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, -50, 592, -1));

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
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
        panelBuscarText2.add(botonBuscarInventario, gridBagConstraints);

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
            .addGroup(panelInventarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInventarioLayout.createSequentialGroup()
                        .addComponent(panel7, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel8, javax.swing.GroupLayout.PREFERRED_SIZE, 655, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelInventarioLayout.createSequentialGroup()
                        .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelInventarioLayout.createSequentialGroup()
                                .addComponent(panelBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelBuscarText2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addComponent(desplazamiento2)
                    .addComponent(panel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panelInventarioLayout.setVerticalGroup(
            panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInventarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel8, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addComponent(panel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel9, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelBuscarText2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(desplazamiento2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        panelVenta.addTab("Inventario", panelInventario);

        jLabel3.setFont(tipoFuente.fuente(tipoFuente.Skaters, 1, 30)
        );
        jLabel3.setText("NOTA VENTA");

        etiquetaNombre2.setFont(tipoFuente.fuente(tipoFuente.Theano, 1, 12));
        etiquetaNombre2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaNombre2.setText("NÚMERO NOTA VENTA:");

        etiquetaNombre3.setFont(tipoFuente.fuente(tipoFuente.Theano, 1, 12));
        etiquetaNombre3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaNombre3.setText("CÉDULA O RUC CLIENTE:");

        etiquetaNombre4.setFont(tipoFuente.fuente(tipoFuente.Theano, 1, 12));
        etiquetaNombre4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaNombre4.setText("NOMBRE CLIENTE:");

        etiquetaNombre5.setFont(tipoFuente.fuente(tipoFuente.Theano, 1, 12));
        etiquetaNombre5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaNombre5.setText("TELÉFONO");

        etiquetaNombre7.setFont(tipoFuente.fuente(tipoFuente.Theano, 1, 12));
        etiquetaNombre7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        etiquetaNombre7.setText("DIRECCIÓN:");

        etiquetaNombre8.setFont(tipoFuente.fuente(tipoFuente.Theano, 1, 12));
        etiquetaNombre8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        etiquetaNombre8.setText("FECHA VENTA:");

        textNumero_NotaVenta.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));

        textTelefono_NotaVenta.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        utilidades.noDejaEscribir(textTelefono_NotaVenta);

        textNombreCliente_NotaVenta.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        utilidades.noDejaEscribir(textNombreCliente_NotaVenta);

        textCedulaNotaVenta.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        textCedulaNotaVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textCedulaNotaVentaKeyReleased(evt);
            }
        });

        textDireccion_NotaVenta.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        utilidades.noDejaEscribir(textDireccion_NotaVenta);

        textFechaVenta_NotaVenta.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        utilidades.noDejaEscribir(textFechaVenta_NotaVenta);

        etiquetaNombre6.setFont(tipoFuente.fuente(tipoFuente.Theano, 1, 12));
        etiquetaNombre6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaNombre6.setText("ID PRODUCTO:");

        textIdProducto_NotaVenta.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        textIdProducto_NotaVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textIdProducto_NotaVentaKeyReleased(evt);
            }
        });

        etiquetaNombre9.setFont(tipoFuente.fuente(tipoFuente.Theano, 1, 12));
        etiquetaNombre9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        etiquetaNombre9.setText("CANTIDAD:");

        textCantidad_NotaVenta.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        utilidades.soloNumero(textCantidad_NotaVenta);
        textCantidad_NotaVenta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textCantidad_NotaVentaFocusLost(evt);
            }
        });

        botonAgregar_NotaVenta.setFont(tipoFuente.fuente(tipoFuente.FightThis, 2, 18)
        );
        botonAgregar_NotaVenta.setText("Agregar");
        botonAgregar_NotaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregar_NotaVentaActionPerformed(evt);
            }
        });

        botonBusquedadAvanzada_NotaVenta.setFont(tipoFuente.fuente(tipoFuente.FightThis, 2, 18)
        );
        botonBusquedadAvanzada_NotaVenta.setText("BUSQUEDA AVANZADA");

        tablaNotaVenta.setFont(new java.awt.Font("Constantia", 2, 18)); // NOI18N
        tablaNotaVenta.setModel(modeloNotaVenta);
        tablaNotaVenta.setBounds(0, 650, 830, 300);
        tablaNotaVenta.getTableHeader().setFont(new Font("Constantia", Font.BOLD, 22));
        tablaNotaVenta.getTableHeader().setOpaque(true);
        tablaNotaVenta.getTableHeader().setBackground(new Color(32, 136, 203));
        tablaNotaVenta.getTableHeader().setForeground(new Color(255, 255, 255));
        tablaNotaVenta.setRowHeight(25);
        tablaNotaVenta.setBackground(new Color(204, 255, 204));
        jScrollPane1.setViewportView(tablaNotaVenta);

        etiquetaNombre10.setFont(tipoFuente.fuente(tipoFuente.Theano, 1, 12));
        etiquetaNombre10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaNombre10.setText("TIPO PAGO:");

        comboPagoNotaVenta.setFont(tipoFuente.fuente(tipoFuente.Emberly, 1, 24));
        comboPagoNotaVenta.setModel(modeloComboNotaVenta);

        etiquetaNombre11.setFont(tipoFuente.fuente(tipoFuente.Theano, 1, 12));
        etiquetaNombre11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaNombre11.setText("TOTA:");

        etiquetaNombre12.setFont(tipoFuente.fuente(tipoFuente.Theano, 1, 12));
        etiquetaNombre12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaNombre12.setText("IVA:");

        etiquetaNombre13.setFont(tipoFuente.fuente(tipoFuente.Theano, 1, 12));
        etiquetaNombre13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaNombre13.setText("SUB TOTAL:");

        textSubTotal_NotaVenta.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));

        textTotal_NotaVenta.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));

        textIva_NotaVenta.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(etiquetaNombre2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(etiquetaNombre3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                                .addComponent(etiquetaNombre5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(etiquetaNombre4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(etiquetaNombre6, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(textIdProducto_NotaVenta, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(textCedulaNotaVenta)
                            .addComponent(textNombreCliente_NotaVenta)
                            .addComponent(textTelefono_NotaVenta)
                            .addComponent(textNumero_NotaVenta))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiquetaNombre7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiquetaNombre8, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiquetaNombre9, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(textCantidad_NotaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(botonAgregar_NotaVenta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botonBusquedadAvanzada_NotaVenta))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textDireccion_NotaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textFechaVenta_NotaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiquetaNombre10, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboPagoNotaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 287, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaNombre13, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiquetaNombre11, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiquetaNombre12, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textIva_NotaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textTotal_NotaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textSubTotal_NotaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaNombre2)
                    .addComponent(textNumero_NotaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaNombre3)
                    .addComponent(textCedulaNotaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaNombre4)
                    .addComponent(textNombreCliente_NotaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiquetaNombre7)
                    .addComponent(textDireccion_NotaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaNombre5)
                    .addComponent(textTelefono_NotaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiquetaNombre8)
                    .addComponent(textFechaVenta_NotaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaNombre9)
                    .addComponent(etiquetaNombre6)
                    .addComponent(textIdProducto_NotaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textCantidad_NotaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonAgregar_NotaVenta)
                    .addComponent(botonBusquedadAvanzada_NotaVenta))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaNombre10)
                    .addComponent(comboPagoNotaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiquetaNombre13)
                    .addComponent(textSubTotal_NotaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaNombre12)
                    .addComponent(textIva_NotaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetaNombre11)
                    .addComponent(textTotal_NotaVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(144, Short.MAX_VALUE))
        );

        panelVenta.addTab("Venta", jPanel6);

        menuArchivo.setText("File");
        menuBar.add(menuArchivo);

        menuFile.setText("Edit");
        menuBar.add(menuFile);

        menuAcercaDe.setText("Acerca de");
        menuAcercaDe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuAcercaDeMouseClicked(evt);
            }
        });
        menuBar.add(menuAcercaDe);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelVenta)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelVenta)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public Inventario obtenerDatosInventario() {
        inve.setCodigo_Pro(textCodigoInventario.getText());
        inve.setDescripcion(textDescripcionInventario.getText());
        inve.setCan_Productos(Integer.parseInt(textCantidadInventario.getText()));
        inve.setPrecio_CompraSinIva(Double.parseDouble(textPrecioSinIvaInventario.getText()));
        inve.setPrecio_CompraConIva(Double.parseDouble(textPrecioConIvaIventario.getText()));
        inve.setPrecio_Mayorista(Double.parseDouble(textPrecioMayoristaInventario.getText()));
        inve.setPrecio_ClienteFijo(Double.parseDouble(textPrecioFigoInventario.getText()));
        inve.setPrecio_ClienteNormal(Double.parseDouble(textPrecioNormalInventario.getText()));
        if (dateFechaCaducidad.getDate() != null) {
            inve.setFecha_Caducidad(dateFechaCaducidad.getDate());
        } else {
            inve.setFecha_Caducidad(null);
        }
        return inve;
    }

    private void menuAcercaDeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuAcercaDeMouseClicked
        AcercaDe dialog = new AcercaDe(this, true);
        dialog.setVisible(true);
    }//GEN-LAST:event_menuAcercaDeMouseClicked

    private void textCedulaNotaVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textCedulaNotaVentaKeyReleased
        if (textCedulaNotaVenta.getText().length() == 10) {

            Persona per = persona.obtenerPersona(textCedulaNotaVenta.getText());
            if (per == null) {
                System.out.println("Nose encontro la persona con esa cédula");
            } else {
                textNombreCliente_NotaVenta.setText(per.getNombre() + " " + per.getApellido());
                textTelefono_NotaVenta.setText(per.getTelefono());
                textDireccion_NotaVenta.setText(per.getDireccion());
                textFechaVenta_NotaVenta.setText(utilidades.fechaRegistro(new Date()));
            }

        }
    }//GEN-LAST:event_textCedulaNotaVentaKeyReleased

    private void tablaInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaInventarioMouseClicked
        int fila = tablaInventario.getSelectedRow();
        if (fila >= 0) {
            String id = tablaInventario.getValueAt(fila, 0).toString();
            inve = inventario.obtenerInventario(id);
            textCodigoInventario.setText(inve.getCodigo_Pro());
            textDescripcionInventario.setText(inve.getDescripcion());
            textCantidadInventario.setText(String.valueOf(inve.getCan_Productos()));
            textPrecioSinIvaInventario.setText(String.valueOf(inve.getPrecio_CompraSinIva()));
            textPrecioConIvaIventario.setText(String.valueOf(inve.getPrecio_CompraConIva()));
            textPrecioMayoristaInventario.setText(String.valueOf(inve.getPrecio_Mayorista()));
            textPrecioFigoInventario.setText(String.valueOf(inve.getPrecio_ClienteFijo()));
            textPrecioNormalInventario.setText(String.valueOf(inve.getPrecio_ClienteNormal()));
            dateFechaCaducidad.setDate(inve.getFecha_Caducidad());
            inveBoolean = true;

        }
    }//GEN-LAST:event_tablaInventarioMouseClicked

    private void textBuscarInventarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBuscarInventarioKeyReleased
        buscarInventario(textBuscarInventario.getText());
    }//GEN-LAST:event_textBuscarInventarioKeyReleased

    private void textInventario2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textInventario2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textInventario2ActionPerformed

    private void botonEliminarInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarInventarioActionPerformed
        int fila = tablaInventario.getSelectedRow();
        if (fila >= 0) {
            inventario.eliminarInventario(inve.getIdInventario());
            mostrarTabla();
            camposVaciosInventario();
        }
    }//GEN-LAST:event_botonEliminarInventarioActionPerformed

    private void botonEditarInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarInventarioActionPerformed
        if (inveBoolean == true) {
            inve = obtenerDatosInventario();
            inve.setFecha_Actualizacion(new Date());
            inventario.editarInventario(inve);
            JOptionPane.showMessageDialog(null, "Persona Editada");
            mostrarTabla();
            camposVaciosInventario();
            inveBoolean = false;
        }
    }//GEN-LAST:event_botonEditarInventarioActionPerformed

    private void botonAgregarInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarInventarioActionPerformed
        if (datosInventario()) {
            inve = obtenerDatosInventario();
            inve.setFecha_Registro(new Date());
            inventario.ingresarInventario(inve);
            mostrarTabla();
            camposVaciosInventario();
        }
    }//GEN-LAST:event_botonAgregarInventarioActionPerformed

    private void botonAgregarInventarioMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonAgregarInventarioMouseMoved
        if (!movi) {
            movi = true;
        }
    }//GEN-LAST:event_botonAgregarInventarioMouseMoved

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

    private void textPrecioSinIvaInventarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textPrecioSinIvaInventarioKeyReleased
        try {
            if (textPrecioSinIvaInventario.getText().isEmpty()) {
                cargarPrecios(0);
            } else {
                double iva = Double.parseDouble(textPrecioSinIvaInventario.getText());
                iva += (iva * 0.12);
                cargarPrecios(iva);
            }
        } catch (Exception e) {

        }
    }//GEN-LAST:event_textPrecioSinIvaInventarioKeyReleased

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

    private void textBuscarProveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBuscarProveedorKeyReleased
        buscarProveedor(textBuscarProveedor.getText());
    }//GEN-LAST:event_textBuscarProveedorKeyReleased

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

    private void botonAgregarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarProveedorActionPerformed
        if (datosProveedor()) {
            Proveedor pro = new Proveedor(textRucProveedor.getText(), textSocialProveedor.getText(), textActividadProveedor.getText(), textNombreProveedor.getText(), textApellidoProveedor.getText(), textTelefonoProveedor.getText(), textCorreoProveedor.getText());
            proveedor.ingresarProveedor(pro);
            mostrarTabla();
            camposVacionProveedor();
        }
    }//GEN-LAST:event_botonAgregarProveedorActionPerformed

    private void textBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBuscarKeyReleased
        gestionPersona.buscarCliente(textBuscar.getText());
    }//GEN-LAST:event_textBuscarKeyReleased

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        lista = false;
        int fila = tabla.getSelectedRow();
        String id = tabla.getValueAt(fila, 0).toString();
        gestionPersona.setearDatosTabla(id);
    }//GEN-LAST:event_tablaMouseClicked

    private void radioBotonPasaporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioBotonPasaporteMouseClicked

    }//GEN-LAST:event_radioBotonPasaporteMouseClicked

    private void textDNIFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textDNIFocusLost
        if (radioBotonCedula.isSelected()) {
            if (!utilidades.validadorDeCedula(textDNI.getText())) {
                JOptionPane.showMessageDialog(rootPane, "la cédula ingresada no es valida", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else if (radioBotonPasaporte.isSelected()) {

        }
    }//GEN-LAST:event_textDNIFocusLost

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed

        if (lista == false) {
            int filaSeleccionada = tabla.getSelectedRow();
            String valor = tabla.getValueAt(filaSeleccionada, 0).toString();
            gestionPersona.eliminarPersona(valor);
            colocarTabla();
        }
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void botonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarActionPerformed
        if (lista == false) {
            if (gestionPersona.datosCliente()) {
                gestionPersona.editarPersona();
                mostrarTabla();
            }
        }
    }//GEN-LAST:event_botonEditarActionPerformed

    private void botonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarActionPerformed
        if (lista == true) {
            if (gestionPersona.datosCliente()) {
                gestionPersona.registrarPersona();
                mostrarTabla();
                lista = false;
            }
        }
    }//GEN-LAST:event_botonAgregarActionPerformed

    private void botonTraerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonTraerActionPerformed
        gestionPersona.botonTraer();
    }//GEN-LAST:event_botonTraerActionPerformed

    private void textIdProducto_NotaVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textIdProducto_NotaVentaKeyReleased
        inveNotaVenta = inventario.obtenerInventario(textIdProducto_NotaVenta.getText());
        if (textIdProducto_NotaVenta.getText().isEmpty()) {
            textCantidad_NotaVenta.setText("");
        } else if (inveNotaVenta != null) {
            textCantidad_NotaVenta.setText(String.valueOf(inveNotaVenta.getCan_Productos()));
        } else {
            textCantidad_NotaVenta.setText("");
        }
    }//GEN-LAST:event_textIdProducto_NotaVentaKeyReleased

    private void textCantidad_NotaVentaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textCantidad_NotaVentaFocusLost
        verificarCantidadProductos();
    }//GEN-LAST:event_textCantidad_NotaVentaFocusLost

    private void botonAgregar_NotaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregar_NotaVentaActionPerformed
        double sinIva = Double.parseDouble(textCantidad_NotaVenta.getText()) * inveNotaVenta.getPrecio_CompraSinIva();
        double conIva = Double.parseDouble(textCantidad_NotaVenta.getText()) * inveNotaVenta.getPrecio_CompraConIva();
        tablaNotaVenta.setModel(notaVenta.tablaNotaVentas(Integer.parseInt(textCantidad_NotaVenta.getText()), inveNotaVenta.getDescripcion(), sinIva, conIva));
        cargarTotal(sinIva);
        inveNotaVenta.setCan_Productos(inveNotaVenta.getCan_Productos() - Integer.parseInt(textCantidad_NotaVenta.getText()));
        inventario.editarInventario(inveNotaVenta);
        mostrarTabla();
        textCantidad_NotaVenta.setText("");
        textIdProducto_NotaVenta.setText("");

    }//GEN-LAST:event_botonAgregar_NotaVentaActionPerformed

    public void verificarCantidadProductos() {
        if (!textCantidad_NotaVenta.getText().isEmpty()) {
            int cantidad = Integer.parseInt(textCantidad_NotaVenta.getText());
            if (cantidad > inveNotaVenta.getCan_Productos()) {
                textCantidad_NotaVenta.setText(String.valueOf(inveNotaVenta.getCan_Productos()));
            }
        }
    }
//    public void cargarSubTotal(double sub) {
//        subtotal += sub;
//        System.out.println(subtotal);
//    }

    public void cargarTotal(double iva) {
        subtotal += iva;
        textSubTotal_NotaVenta.setText(String.format("%.2f", subtotal));
        textIva_NotaVenta.setText(String.format("%.2f", subtotal * 0.12));
        textTotal_NotaVenta.setText(String.format("%.2f", subtotal + (subtotal * 0.12)));
    }

    public void cargarPrecios(double iva) {
        textPrecioConIvaIventario.setText(String.format("%.2f", iva));
        textPrecioMayoristaInventario.setText(String.format("%.2f", (iva + (iva * 0.10))));
        textPrecioFigoInventario.setText(String.format("%.2f", iva + (iva * 0.12)));
        textPrecioNormalInventario.setText(String.format("%.2f", iva + (iva * 0.15)));
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
        if (textCantidadInventario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "El campo Precio Compra no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            textActividadProveedor.requestFocus();
            return false;
        }
        if (textPrecioSinIvaInventario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "El campo Precio Venta no tiene datos.", "ERROR", JOptionPane.ERROR_MESSAGE);
            textNombreProveedor.requestFocus();
            return false;
        }
        return true;
    }

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
    private javax.swing.JButton botonAgregar_NotaVenta;
    private javax.swing.JButton botonBuscar2;
    private javax.swing.JButton botonBuscarInventario;
    private javax.swing.JButton botonBuscarProveedor;
    private javax.swing.JButton botonBusquedadAvanzada_NotaVenta;
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
    private javax.swing.JComboBox<String> comboBoxGenero;
    private javax.swing.JComboBox<String> comboPagoNotaVenta;
    private com.toedter.calendar.JDateChooser dateFechaCaducidad;
    private com.toedter.calendar.JDateChooser dateNacimiento;
    private javax.swing.JScrollPane desplazamiento;
    private javax.swing.JScrollPane desplazamiento1;
    private javax.swing.JScrollPane desplazamiento2;
    private javax.swing.JLabel etiquetTelefono1;
    private javax.swing.JLabel etiquetTelefono2;
    private javax.swing.JLabel etiquetTelefono3;
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
    private javax.swing.JLabel etiquetaDireccion2;
    private javax.swing.JLabel etiquetaDireccion3;
    private javax.swing.JLabel etiquetaDireccion4;
    private javax.swing.JLabel etiquetaDireccion5;
    private javax.swing.JLabel etiquetaNombre;
    private javax.swing.JLabel etiquetaNombre1;
    private javax.swing.JLabel etiquetaNombre10;
    private javax.swing.JLabel etiquetaNombre11;
    private javax.swing.JLabel etiquetaNombre12;
    private javax.swing.JLabel etiquetaNombre13;
    private javax.swing.JLabel etiquetaNombre2;
    private javax.swing.JLabel etiquetaNombre3;
    private javax.swing.JLabel etiquetaNombre4;
    private javax.swing.JLabel etiquetaNombre5;
    private javax.swing.JLabel etiquetaNombre6;
    private javax.swing.JLabel etiquetaNombre7;
    private javax.swing.JLabel etiquetaNombre8;
    private javax.swing.JLabel etiquetaNombre9;
    private java.awt.Label etiquetaNombreRe1;
    private java.awt.Label etiquetaRazon1;
    private javax.swing.JLabel etiquetaRegistro;
    private java.awt.Label etiquetaRuc1;
    private java.awt.Label etiquetaTelefonoP1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menuAcercaDe;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuFile;
    private javax.swing.JPanel panel1;
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
    private javax.swing.JTabbedPane panelVenta;
    private javax.swing.JRadioButton radioBotonCedula;
    private javax.swing.JRadioButton radioBotonPasaporte;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tablaInventario;
    private javax.swing.JTable tablaNotaVenta;
    private javax.swing.JTable tablaProveedor;
    private javax.swing.JTextField textActividadProveedor;
    private javax.swing.JTextField textApellido;
    private javax.swing.JTextField textApellidoProveedor;
    private javax.swing.JTextField textBuscar;
    private javax.swing.JTextField textBuscarInventario;
    private javax.swing.JTextField textBuscarProveedor;
    private javax.swing.JTextField textCantidadInventario;
    private javax.swing.JTextField textCantidad_NotaVenta;
    private javax.swing.JTextField textCedulaNotaVenta;
    private javax.swing.JTextField textCodigoInventario;
    private javax.swing.JTextField textCorreo;
    private javax.swing.JTextField textCorreoProveedor;
    private javax.swing.JTextField textDNI;
    private javax.swing.JTextField textDescripcionInventario;
    private javax.swing.JTextField textDireccion;
    private javax.swing.JTextField textDireccion_NotaVenta;
    private javax.swing.JTextField textFechaVenta_NotaVenta;
    private javax.swing.JTextField textIdProducto_NotaVenta;
    private javax.swing.JTextField textInventario2;
    private javax.swing.JTextField textIva_NotaVenta;
    private javax.swing.JTextField textNombre;
    private javax.swing.JTextField textNombreCliente_NotaVenta;
    private javax.swing.JTextField textNombreProveedor;
    private javax.swing.JTextField textNumero_NotaVenta;
    private javax.swing.JTextField textPrecioConIvaIventario;
    private javax.swing.JTextField textPrecioFigoInventario;
    private javax.swing.JTextField textPrecioMayoristaInventario;
    private javax.swing.JTextField textPrecioNormalInventario;
    private javax.swing.JTextField textPrecioSinIvaInventario;
    private javax.swing.JTextField textRucProveedor;
    private javax.swing.JTextField textSocialProveedor;
    private javax.swing.JTextField textSubTotal_NotaVenta;
    private javax.swing.JTextField textTelefono;
    private javax.swing.JTextField textTelefonoProveedor;
    private javax.swing.JTextField textTelefono_NotaVenta;
    private javax.swing.JTextField textTotal_NotaVenta;
    // End of variables declaration//GEN-END:variables
}
