package Cliente;

import java.util.Date;

/**
 * @author Emersson
 */
public class Nota_Venta {
    private int idNota_venta;
    private String numero_nota_venta;
    private int persona_id_persona;
    private Date fecha_venta;
    private double subtotal;
    private double iva;
    private double total;
    private int tipo_pago;

    public Nota_Venta() {
    }

    public Nota_Venta(int idNota_venta, String numero_nota_venta, int persona_id_persona, Date fecha_venta, double subtotal, double iva, double total, int tipo_pago) {
        this.idNota_venta = idNota_venta;
        this.numero_nota_venta = numero_nota_venta;
        this.persona_id_persona = persona_id_persona;
        this.fecha_venta = fecha_venta;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.tipo_pago = tipo_pago;
    }

    public int getIdNota_venta() {
        return idNota_venta;
    }

    public void setIdNota_venta(int idNota_venta) {
        this.idNota_venta = idNota_venta;
    }

    public String getNumero_nota_venta() {
        return numero_nota_venta;
    }

    public void setNumero_nota_venta(String numero_nota_venta) {
        this.numero_nota_venta = numero_nota_venta;
    }

    public int getPersona_id_persona() {
        return persona_id_persona;
    }

    public void setPersona_id_persona(int persona_id_persona) {
        this.persona_id_persona = persona_id_persona;
    }

    public Date getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getTipo_pago() {
        return tipo_pago;
    }

    public void setTipo_pago(int tipo_pago) {
        this.tipo_pago = tipo_pago;
    }
    
    
}
