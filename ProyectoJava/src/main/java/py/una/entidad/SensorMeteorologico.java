package py.una.entidad;

import java.util.Date;
import java.util.Timer;

public class SensorMeteorologico { //nueva clase completamente creado

    long id_estacion;
    String ciudad;
    int porcentaje_humedad;
    float temperatura;
    int velocidad_viento;
    Date fecha;
    Timer hora;

    public SensorMeteorologico(long id_estacion,String ciudad, int porcentaje_humedad, float temperatura, int velocidad_viento, Date fecha, Timer hora){
        this.id_estacion = id_estacion;
        this.ciudad = ciudad;
        this.porcentaje_humedad = porcentaje_humedad;
        this.temperatura = temperatura;
        this.velocidad_viento = velocidad_viento;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Date getFecha() {
        return fecha;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public int getPorcentaje_humedad() {
        return porcentaje_humedad;
    }

    public int getVelocidad_viento() {
        return velocidad_viento;
    }

    public long getId_estacion() {
        return id_estacion;
    }

    public Timer getHora() {
        return hora;
    }

    public String getCiudad() {
        return ciudad;
    }
}
