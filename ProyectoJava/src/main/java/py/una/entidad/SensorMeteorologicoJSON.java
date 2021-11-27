package py.una.entidad;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Date;
import java.util.Iterator;
import java.util.Timer;

public class SensorMeteorologicoJSON {

    public static String objetoString(SensorMeteorologico p) {

        JSONObject obj = new JSONObject();
        obj.put("id_estacion", p.getId_estacion());
        obj.put("ciudad", p.getCiudad());
        obj.put("fecha", p.getFecha());
        obj.put("hora", p.getHora());
        obj.put("porcentaje_humedad", p.getPorcentaje_humedad());
        obj.put("temperatura", p.getTemperatura());
        obj.put("velocidad_viento", p.getVelocidad_viento());




        return obj.toJSONString();
    }


    public static SensorMeteorologico stringObjeto(String str) throws Exception {
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(str.trim());
        JSONObject jsonObject = (JSONObject) obj;


        SensorMeteorologico p = new SensorMeteorologico(
                (long) jsonObject.get("id_estacion"),
                (String) jsonObject.get("ciudad"),
                (int) jsonObject.get("porcentaje_humedad"),
                (float) jsonObject.get("temperatura"),
                (int) jsonObject.get("velocidad_viento"),
                (Date) jsonObject.get("fecha"),
                (Timer) jsonObject.get("hora")
        );

        return p;
    }
}
