package Cliente;

import java.util.Date;

/**
 * @author Emersson
 */
public class Inventario {

    private int idInventario;
    private String codigo_Pro;
    private String descripcion;
    private int can_Productos;
    private double precio_CompraSinIva;
    private double precio_CompraConIva;
    private double precio_Mayorista;
    private double precio_ClienteFijo;
    private double precio_ClienteNormal;
    private Date fecha_Caducidad;
    private Date fecha_Registro;
    private Date fecha_Actualizacion;

    public Inventario(int idInventario, String codigo_Pro, String descripcion, int can_Productos, double precio_CompraSinIva, double precio_CompraConIva, double precio_Mayorista, double precio_ClienteFijo, double precio_ClienteNormal, Date fecha_Caducidad) {
        this.idInventario = idInventario;
        this.codigo_Pro = codigo_Pro;
        this.descripcion = descripcion;
        this.can_Productos = can_Productos;
        this.precio_CompraSinIva = precio_CompraSinIva;
        this.precio_CompraConIva = precio_CompraConIva;
        this.precio_Mayorista = precio_Mayorista;
        this.precio_ClienteFijo = precio_ClienteFijo;
        this.precio_ClienteNormal = precio_ClienteNormal;
        this.fecha_Caducidad = fecha_Caducidad;
    }

    public Inventario(String codigo_Pro, String descripcion, int can_Productos, double precio_CompraSinIva, double precio_CompraConIva, double precio_Mayorista, double precio_ClienteFijo, double precio_ClienteNormal, Date fecha_Caducidad) {
        this.codigo_Pro = codigo_Pro;
        this.descripcion = descripcion;
        this.can_Productos = can_Productos;
        this.precio_CompraSinIva = precio_CompraSinIva;
        this.precio_CompraConIva = precio_CompraConIva;
        this.precio_Mayorista = precio_Mayorista;
        this.precio_ClienteFijo = precio_ClienteFijo;
        this.precio_ClienteNormal = precio_ClienteNormal;
        this.fecha_Caducidad = fecha_Caducidad;
    }

    public Inventario(String codigo_Pro, String descripcion, int can_Productos, double precio_CompraSinIva, double precio_CompraConIva, double precio_ClienteFijo, double precio_ClienteNormal) {
        this.codigo_Pro = codigo_Pro;
        this.descripcion = descripcion;
        this.can_Productos = can_Productos;
        this.precio_CompraSinIva = precio_CompraSinIva;
        this.precio_CompraConIva = precio_CompraConIva;
        this.precio_ClienteFijo = precio_ClienteFijo;
        this.precio_ClienteNormal = precio_ClienteNormal;
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

    public int getCan_Productos() {
        return can_Productos;
    }

    public void setCan_Productos(int can_Productos) {
        this.can_Productos = can_Productos;
    }

    public double getPrecio_CompraSinIva() {
        return precio_CompraSinIva;
    }

    public void setPrecio_CompraSinIva(double precio_CompraSinIva) {
        this.precio_CompraSinIva = precio_CompraSinIva;
    }

    public double getPrecio_CompraConIva() {
        return precio_CompraConIva;
    }

    public void setPrecio_CompraConIva(double precio_CompraConIva) {
        this.precio_CompraConIva = precio_CompraConIva;
    }

    public double getPrecio_Mayorista() {
        return precio_Mayorista;
    }

    public void setPrecio_Mayorista(double precio_Mayorista) {
        this.precio_Mayorista = precio_Mayorista;
    }

    public double getPrecio_ClienteFijo() {
        return precio_ClienteFijo;
    }

    public void setPrecio_ClienteFijo(double precio_ClienteFijo) {
        this.precio_ClienteFijo = precio_ClienteFijo;
    }

    public double getPrecio_ClienteNormal() {
        return precio_ClienteNormal;
    }

    public void setPrecio_ClienteNormal(double precio_ClienteNormal) {
        this.precio_ClienteNormal = precio_ClienteNormal;
    }

    public Date getFecha_Caducidad() {
        return fecha_Caducidad;
    }

    public void setFecha_Caducidad(Date fecha_Caducidad) {
        this.fecha_Caducidad = fecha_Caducidad;
    }

    public Date getFecha_Registro() {
        return fecha_Registro;
    }

    public void setFecha_Registro(Date fecha_Registro) {
        this.fecha_Registro = fecha_Registro;
    }

    public Date getFecha_Actualizacion() {
        return fecha_Actualizacion;
    }

    public void setFecha_Actualizacion(Date fecha_Actualizacion) {
        this.fecha_Actualizacion = fecha_Actualizacion;
    }
    
    
    
}
