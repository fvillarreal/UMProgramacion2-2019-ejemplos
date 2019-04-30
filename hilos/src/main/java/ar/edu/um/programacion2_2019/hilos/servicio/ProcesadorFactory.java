package ar.edu.um.programacion2_2019.hilos.servicio;

import ar.edu.um.programacion2_2019.hilos.entidades.Venta;

public class ProcesadorFactory {
    public static IProcesador getProcesador(Venta venta, ILogeador log) {
        if(venta.getTarjeta().getTipo().equals("VISA")) {
            return new VisaProcesador(venta, log);
        }
        if(venta.getTarjeta().getTipo().equals("MC")) {
            return new MasterCardProcesador(venta, log);
        }
    return null;
    }
}
