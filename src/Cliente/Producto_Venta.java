package Cliente;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Emersson
 */
public class Producto_Venta {
    private int cantidad;
    private String descripcion;
    private double subTotal;
    private double total;



    public Producto_Venta(int cantidad, String descripcion, double subTotal, double total) {
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.subTotal = subTotal;
        this.total = total;

    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
//    public void clickProductoVender() {
//        ejecutar = true;
//        //HILO DE PROGRMACIÓN
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (ejecutar) {
//                    try {
//                        //Parar un segundo
//                        Thread.sleep(1000);
//                        contadorClick = 0;
//                        ejecutar = false;
//                        System.out.println("Reinicia CLICK");
//                    } catch (InterruptedException e) {
//                        System.out.println(e.getMessage());
//                    }
//                }
//            }
//        }).start();
//
//        contadorClick++;
//        if (contadorClick == 2) {
//            if (JOptionPane.showConfirmDialog(Cliente_Ventana, "Se eliminará el item, ¿desea continuar?",
//                    "Eliminar Producto", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
////                productosVenta.remove(p);
////                calcularValoresAdicionales();
////                modeloTablaVenta.setProductoVentas(productosVenta);
//            }
//            contadorClick = 0;
//            ejecutar = false;
//        }
//        System.out.println("contadorClick" + contadorClick);
//    }
    
}
