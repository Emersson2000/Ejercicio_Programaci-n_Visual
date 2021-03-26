package Cliente;

import Tipografias.Fuentes;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * @author Emersson
 */
public class AcercaDe extends java.awt.Dialog {

    private Fuentes tipoFuente = new Fuentes();


    public AcercaDe(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        panelAcercaDe = new fondo();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelAcercaDe = new fondo();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelAcercaDe.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/factura (2) (1).png"))); // NOI18N
        jLabel2.setToolTipText("Facturación");
        panelAcercaDe.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 270, 290));

        jLabel1.setFont(tipoFuente.fuente(tipoFuente.Bemio, 2, 25)
        );
        jLabel1.setForeground(new java.awt.Color(204, 204, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sistema de Facturación");
        panelAcercaDe.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 360, -1));

        add(panelAcercaDe, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel panelAcercaDe;
    // End of variables declaration//GEN-END:variables

    class fondo extends JPanel {

        private Image ima;

        @Override
        public void paint(Graphics g) {
            ima = new ImageIcon(getClass().getResource("/Imagenes/negro.jpg")).getImage();
            g.drawImage(ima, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }

    }


}



