package Cliente;

/**
 * @author Emersson
 */
public class Inventario {
    private int idInventario; 
    private String codigo_Pro; 
    private String descripcion; 
    private String precio_Compra; 
    private String precio_Venta; 
    private String can_Productos;

    public Inventario(int idInventario, String codigo_Pro, String descripcion, String precio_Compra, String precio_Venta, String can_Productos) {
        this.idInventario = idInventario;
        this.codigo_Pro = codigo_Pro;
        this.descripcion = descripcion;
        this.precio_Compra = precio_Compra;
        this.precio_Venta = precio_Venta;
        this.can_Productos = can_Productos;
    }

    public Inventario(String codigo_Pro, String descripcion, String precio_Compra, String precio_Venta, String can_Productos) {
        this.codigo_Pro = codigo_Pro;
        this.descripcion = descripcion;
        this.precio_Compra = precio_Compra;
        this.precio_Venta = precio_Venta;
        this.can_Productos = can_Productos;
    }

    public Inventario() {
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public String getCodigo_Pro() {
        return codigo_Pro;
    }

    public void setCodigo_Pro(String codigo_Pro) {
        this.codigo_Pro = codigo_Pro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio_Compra() {
        return precio_Compra;
    }

    public void setPrecio_Compra(String precio_Compra) {
        this.precio_Compra = precio_Compra;
    }

    public String getPrecio_Venta() {
        return precio_Venta;
    }

    public void setPrecio_Venta(String precio_Venta) {
        this.precio_Venta = precio_Venta;
    }

    public String getCan_Productos() {
        return can_Productos;
    }

    public void setCan_Productos(String can_Productos) {
        this.can_Productos = can_Productos;
    }
    
    
}
