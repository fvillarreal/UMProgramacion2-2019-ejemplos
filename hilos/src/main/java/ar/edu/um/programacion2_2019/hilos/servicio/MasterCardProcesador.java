package ar.edu.um.programacion2_2019.hilos.servicio;

import ar.edu.um.programacion2_2019.hilos.entidades.Venta;

import java.util.UUID;

public class MasterCardProcesador implements IProcesador{
    protected Venta venta;
    protected ILogeador log;
    protected UUID sesion;

    public MasterCardProcesador() {}

    public MasterCardProcesador(Venta venta, ILogeador log) {
        this.venta = venta;
        this.log = log;
        this.sesion = log.crearSesion();
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public boolean verificarTarjeta() {
        boolean retorno = true;
        this.log.bloqueInfo(this.sesion, "Arrancando el proceso de verificar la tarjeta de credito MC");
        this.log.bloqueInfo(this.sesion, "Verificando tarjeta: "+this.venta.getTarjeta().getCCEnmascarada());
        int numError = (int)(Math.random()*10);
        if(numError==1) {
            this.log.bloqueError(this.sesion, "Hubo un error en la verificación de la tarjeta MC");
            this.log.bloqueInfo(this.sesion, "Finalizando proceso de venta");
            this.log.publicarSesion(this.sesion);
            retorno = false;
        }
        this.log.bloqueInfo(this.sesion, "Finalizada la verificación de la tarjeta MC");
        return retorno;
    }

    public boolean autorizar() {
        boolean retorno = true;
        this.log.bloqueInfo(this.sesion, "Arrancando el proceso de autorizar la transacción MC");
        this.log.bloqueInfo(this.sesion, "Verificando monto: "+this.venta.getMonto());
        int numError = (int)(Math.random()*10);
        if(numError==1) {
            this.log.bloqueError(this.sesion, "Hubo un error en la autorización de la tarjeta MC");
            this.log.bloqueInfo(this.sesion, "Finalizando proceso de venta");
            this.log.publicarSesion(this.sesion);
            retorno = false;
        }
        this.log.bloqueInfo(this.sesion, "Finalizado el proceso de autorización de la tarjeta MC");
        return retorno;
    }

    public boolean capturar() {
        this.log.bloqueInfo(this.sesion, "Procesada la captura de la tarjeta MC");
        return true;
    }

    public boolean finalizar() {
        this.log.bloqueInfo(this.sesion, "Finalizado el proceso de venta de la tarjeta MC");
        this.log.publicarSesion(this.sesion);
        return true;
    }
}
