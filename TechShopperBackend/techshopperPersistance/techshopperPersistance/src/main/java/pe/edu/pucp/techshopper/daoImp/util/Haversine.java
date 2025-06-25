/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.techshopper.daoImp.util;

/**
 *
 * @author CRISTHIAN
 */
public class Haversine {
    //direccion: Av. Javier Prado 123, Lima (LAT: -12.09, LON: -77.05)

    //estas constantes son las latitud y longitud del almacen de origen donde sale nuestro producto pal envio
    private static final double lat1 = -12.09;
    private static final double lon1 = -77.05;

//    public Haversine(double lat1) {
//        this.lat1 = lat1;
//    }

    public static double calcularDistanciaKm(double lat2, double lon2) {
//    public static double calcularDistanciaKm(double lat1, double lon1, double lat2, double lon2) {
        double radioTierra = 6371.0; // km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return radioTierra * c;
    }
}
