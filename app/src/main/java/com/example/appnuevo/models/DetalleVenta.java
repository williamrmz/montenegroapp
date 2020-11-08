package com.example.appnuevo.models;

public class DetalleVenta {

    private int idproducto;
    private int cantidad;
    private double precio_unitario;
    private String undm;
    private int cundm;
    private String sundm;
    private double precio_compra;

    public int getIdproducto() { return idproducto; }

    public void setIdproducto(int idproducto) { this.idproducto = idproducto; }

    public int getCantidad() { return cantidad; }

    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecio_unitario() { return precio_unitario; }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario; }

    public String getUndm() { return undm;}

    public void setUndm(String undm) { this.undm = undm; }

    public int getCundm() { return cundm; }

    public void setCundm(int cundm) { this.cundm = cundm; }

    public String getSundm() { return sundm; }

    public void setSundm(String sundm) { this.sundm = sundm; }

    public double getPrecio_compra() { return precio_compra; }

    public void setPrecio_compra(double precio_compra) { this.precio_compra = precio_compra; }

    @Override
    public String toString() {
        return "DetalleVenta{" +
                "idproducto=" + idproducto +
                ", cantidad=" + cantidad +
                ", precio_unitario=" + precio_unitario +
                ", undm='" + undm + '\'' +
                ", cundm=" + cundm +
                ", sundm='" + sundm + '\'' +
                ", precio_compra=" + precio_compra +
                '}';
    }
}
