package Cliente;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import Tipografias.Fuentes;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
/**
 * @author Emersson
 */
public class Busqueda_Avanzada extends javax.swing.JDialog {

    private Fuentes tipoFuente = new Fuentes();
    private DefaultComboBoxModel modeloComboBusquedaAvanzada = new DefaultComboBoxModel(); 
    private Controlador_BD_Inventario inventario = new Controlador_BD_Inventario();
    private JTextField textId_Nota_Venta;
    private JTextField textCantidad_Nota_Venta;
    
    public Busqueda_Avanzada(java.awt.Frame parent, boolean modal, DefaultTableModel modeloTablaBusqueda, JTextField textId_Nota_Venta, JTextField textCantidad_Nota_Venta) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setSize(990, 350);
        tablaBusquedaAvanzada.setModel(modeloTablaBusqueda);
        this.textId_Nota_Venta = textId_Nota_Venta;
        this.textCantidad_Nota_Venta = textCantidad_Nota_Venta;
        colocarComboBox();
    }

    public void colocarComboBox() {
        modeloComboBusquedaAvanzada.addElement("Código");
        modeloComboBusquedaAvanzada.addElement("Descripción");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new fondo();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaBusquedaAvanzada = new javax.swing.JTable();
        etiquetaBuscar1 = new javax.swing.JLabel();
        comboBoxBusquedaAvanzada = new javax.swing.JComboBox<>();
        textBusquedaAvanzada = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(990, 500));

        tablaBusquedaAvanzada.setFont(new java.awt.Font("Constantia", 2, 18)); // NOI18N
        tablaBusquedaAvanzada.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaBusquedaAvanzada.setBounds(0, 650, 830, 300);
        tablaBusquedaAvanzada.getTableHeader().setFont(new Font("Constantia", Font.BOLD, 18));
        tablaBusquedaAvanzada.getTableHeader().setOpaque(true);
        tablaBusquedaAvanzada.getTableHeader().setBackground(new Color(32, 136, 203));
        tablaBusquedaAvanzada.getTableHeader().setForeground(new Color(255, 255, 255));
        tablaBusquedaAvanzada.setRowHeight(25);
        tablaBusquedaAvanzada.setBackground(new Color(204, 255, 204));
        tablaBusquedaAvanzada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaBusquedaAvanzadaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaBusquedaAvanzada);

        etiquetaBuscar1.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 22));
        etiquetaBuscar1.setText("Buscar Cliente:");

        comboBoxBusquedaAvanzada.setFont(tipoFuente.fuente(tipoFuente.Emberly, 1, 24)
        );
        comboBoxBusquedaAvanzada.setModel(modeloComboBusquedaAvanzada );

        textBusquedaAvanzada.setFont(tipoFuente.fuente(tipoFuente.Theano, 2, 20));
        textBusquedaAvanzada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textBusquedaAvanzadaKeyReleased(evt);
            }
        });

        jLabel1.setFont(tipoFuente.fuente(tipoFuente.Skaters, 1, 30)
        );
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("BUSQUEDA AVANZADA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(etiquetaBuscar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboBoxBusquedaAvanzada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textBusquedaAvanzada)
                        .addGap(103, 103, 103))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textBusquedaAvanzada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiquetaBuscar1)
                    .addComponent(comboBoxBusquedaAvanzada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void mostarInventario(String buscar) {
        if (comboBoxBusquedaAvanzada.getSelectedIndex() == 0) {
           tablaBusquedaAvanzada.setModel(inventario.mostrarInventarioPorDato("codigo_Pro", buscar));
        } else {
            tablaBusquedaAvanzada.setModel(inventario.mostrarInventarioPorDato("descripcion", buscar));
        }
    }
    private void textBusquedaAvanzadaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBusquedaAvanzadaKeyReleased
       mostarInventario(textBusquedaAvanzada.getText());
    }//GEN-LAST:event_textBusquedaAvanzadaKeyReleased

    
    private void tablaBusquedaAvanzadaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaBusquedaAvanzadaMouseClicked

        int fila = tablaBusquedaAvanzada.getSelectedRow();
        String codigo = (String) tablaBusquedaAvanzada.getValueAt(fila, 1);
        String cantidad = (String) tablaBusquedaAvanzada.getValueAt(fila, 3);
        textId_Nota_Venta.setText(codigo);
        textCantidad_Nota_Venta.setText(cantidad);
    }//GEN-LAST:event_tablaBusquedaAvanzadaMouseClicked


    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBoxBusquedaAvanzada;
    private javax.swing.JLabel etiquetaBuscar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaBusquedaAvanzada;
    private javax.swing.JTextField textBusquedaAvanzada;
    // End of variables declaration//GEN-END:variables

      class fondo extends JPanel {

        private Image ima;

        @Override
        public void paint(Graphics g) {
            ima = new ImageIcon(getClass().getResource("/Imagenes/chever.jpg")).getImage();
            g.drawImage(ima, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }

    }
}
