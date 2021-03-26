package Cliente;

import java.util.Date;

public class Persona {

    private String idPersona;
    private String cedula;
    private String nombre;
    private String apellido;
    private String direccion;
    private String correo;
    private String telefono;
    private Date fechaRegistro;
    private int genero;
    private Date fechaActualización;
    private Date fechaNacimiento;

    public Persona(String idPersona, String cedula, String nombre, String apellido, String direccion, String correo, String telefono) {
        this.idPersona = idPersona;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
    }

    public Persona(String cedula, String nombre, String apellido, String direccion, String correo, String telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
    }

    public Persona(String idPersona, String cedula, String nombre, String apellido, String direccion, String correo, String telefono, Date fechaRegistro) {
        this.idPersona = idPersona;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
    }

    public Persona(String idPersona, String cedula, String nombre, String apellido, String direccion, String correo, String telefono, Date fechaRegistro, int genero, Date fechaActualización, Date fechaNacimiento) {
        this.idPersona = idPersona;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
        this.genero = genero;
        this.fechaActualización = fechaActualización;
        this.fechaNacimiento = fechaNacimiento;
    }




    public Persona() {

    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public Date getFechaActualización() {
        return fechaActualización;
    }

    public void setFechaActualización(Date fechaActualización) {
        this.fechaActualización = fechaActualización;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Cédula: " + cedula + "\nNombre: " + nombre + "\nApellidos: " + apellido
                + "\nDirección: " + direccion + "\nCorreo: " + correo + "\nTeléfono: " + telefono
                + "\nFecha de Registro: " + fechaRegistro + "\nGénero: " + genero + "\n";
    }
}
