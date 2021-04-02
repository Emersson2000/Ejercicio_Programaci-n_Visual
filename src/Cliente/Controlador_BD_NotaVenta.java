package Cliente;

import java.sql.Connection;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 * @author Emersson
 */
public class Controlador_BD_NotaVenta {
//    private Nota_Venta nota_venta = new Nota_Venta();

    private String lista[] = {"Cantidad", "Descripción", "SubTotal", "Total"};
    private DefaultTableModel modeloNotaVenta = new DefaultTableModel(null, lista);
    private Conexion_Base_De_Datos conexion = new Conexion_Base_De_Datos();
    
     private Utilidades utilidades;

    public Controlador_BD_NotaVenta() {
        utilidades = new Utilidades();
    }

    public DefaultTableModel tablaNotaVentas(int cantidad, String descripcion, double subTotal, double total) {
        String fila[] = new String[4];
        fila[0] = String.valueOf(cantidad);
        fila[1] = descripcion;
        fila[2] = String.valueOf(subTotal);
        fila[3] = String.valueOf(total);
        modeloNotaVenta.addRow(fila);
        return modeloNotaVenta;
    }
    
   public boolean ingresarInventario(Nota_Venta notaVenta) {
        boolean reguistrar = false;
        Connection con = null;
        Statement stm = null;
        
        String sql = "INSERT INTO `cliente`.`nota_venta` (`numero_nota_venta`, `persona_id_persona`, `fecha_venta`, `subtotal`, `iva`, `total`, `tipo_pago`) VALUES ('" +notaVenta.getIdNota_venta() +"', '" +notaVenta.getPersona_id_persona() +"', '" +utilidades.fechaRegistro(notaVenta.getFecha_venta()) +"', '" +notaVenta.getSubtotal() +"', '" +notaVenta.getIva() +"', '" +notaVenta.getTotal() +"', '" +notaVenta.getTipo_pago() +"')";
  
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

}
