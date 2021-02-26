package Cliente;

/**
 * @author Emersson
 */
public class Proveedor {
    
    private int idProveedor; 
    private String ruc; 
    private String razon_Social; 
    private String tipo_Actividad;
    private String nom_Representante_Legal; 
    private String apell_Representante_Legal;
    private String telefono; 
    private String correo;

    public Proveedor(int idProveedor, String ruc, String razon_Social, String tipo_Actividad, String nom_Representante_Legal, String apell_Representante_Legal, String telefono, String correo) {
        this.idProveedor = idProveedor;
        this.ruc = ruc;
        this.razon_Social = razon_Social;
        this.tipo_Actividad = tipo_Actividad;
        this.nom_Representante_Legal = nom_Representante_Legal;
        this.apell_Representante_Legal = apell_Representante_Legal;
        this.telefono = telefono;
        this.correo = correo;
    }

    public Proveedor(String ruc, String razon_Social, String tipo_Actividad, String nom_Representante_Legal, String apell_Representante_Legal, String telefono, String correo) {
        this.ruc = ruc;
        this.razon_Social = razon_Social;
        this.tipo_Actividad = tipo_Actividad;
        this.nom_Representante_Legal = nom_Representante_Legal;
        this.apell_Representante_Legal = apell_Representante_Legal;
        this.telefono = telefono;
        this.correo = correo;
    }
    

    public Proveedor() {
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazon_Social() {
        return razon_Social;
    }

    public void setRazon_Social(String razon_Social) {
        this.razon_Social = razon_Social;
    }

    public String getTipo_Actividad() {
        return tipo_Actividad;
    }

    public void setTipo_Actividad(String tipo_Actividad) {
        this.tipo_Actividad = tipo_Actividad;
    }

    public String getNom_Representante_Legal() {
        return nom_Representante_Legal;
    }

    public void setNom_Representante_Legal(String nom_Representante_Legal) {
        this.nom_Representante_Legal = nom_Representante_Legal;
    }

    public String getApell_Representante_Legal() {
        return apell_Representante_Legal;
    }

    public void setApell_Representante_Legal(String apell_Representante_Legal) {
        this.apell_Representante_Legal = apell_Representante_Legal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
}
