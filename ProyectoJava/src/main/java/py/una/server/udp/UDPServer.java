package py.una.server.udp;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import py.una.entidad.SensorMeteorologico;
import py.una.entidad.SensorMeteorologicoJSON;

public class UDPServer {
	
	
    public static void main(String[] a){
        
        // Variables
        int puertoServidor = 9876;
        ArrayList<SensorMeteorologico> ciudadesSensorMeteorologico = new ArrayList<SensorMeteorologico>();

        ciudadesSensorMeteorologico.add(new SensorMeteorologico(
                1,"Asuncion",60,32,10,new Date(),new Timer()
        ));
        ciudadesSensorMeteorologico.add(new SensorMeteorologico(
                2,"CDE",60,32,10,new Date(),new Timer()
        ));
        ciudadesSensorMeteorologico.add(new SensorMeteorologico(
                3,"Encarnacion",60,32,10,new Date(),new Timer()
        ));
        ciudadesSensorMeteorologico.add(new SensorMeteorologico(
                4,"PJC",60,32,10,new Date(),new Timer()
        ));
        ciudadesSensorMeteorologico.add(new SensorMeteorologico(
                5,"Luque",60,32,10,new Date(),new Timer()
        ));
        ciudadesSensorMeteorologico.add(new SensorMeteorologico(
                6,"San Lorenzo",60,32,10,new Date(),new Timer()
        ));
        ciudadesSensorMeteorologico.add(new SensorMeteorologico(
                7,"Capiata",60,32,10,new Date(),new Timer()
        ));
        ciudadesSensorMeteorologico.add(new SensorMeteorologico(
                8,"Lambare",60,32,10,new Date(),new Timer()
        ));

        try {
            //1) Creamos el socket Servidor de Datagramas (UDP)
            DatagramSocket serverSocket = new DatagramSocket(puertoServidor);
			System.out.println("Servidor Sistemas Distribuidos - UDP ");
			
            //2) buffer de datos a enviar y recibir
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];

			
            //3) Servidor siempre esperando
            while (true) {

                receiveData = new byte[1024];

                DatagramPacket receivePacket =
                        new DatagramPacket(receiveData, receiveData.length);


                System.out.println("Esperando a algun cliente... ");

                // 4) Receive LLAMADA BLOQUEANTE
                serverSocket.receive(receivePacket);
				
				System.out.println("________________________________________________");
                System.out.println("Aceptamos un paquete");

                // Datos recibidos e Identificamos quien nos envio
                String datoRecibido = new String(receivePacket.getData());
                datoRecibido = datoRecibido.trim();
                System.out.println("DatoRecibido: " + datoRecibido );
                SensorMeteorologico sensor = SensorMeteorologicoJSON.stringObjeto(datoRecibido);

                InetAddress IPAddress = receivePacket.getAddress();

                int port = receivePacket.getPort();

                System.out.println("De : " + IPAddress + ":" + port);
                System.out.println("La temperatura de la ciudad recibida es: "+sensor.getTemperatura());
                



            }

        } catch (Exception ex) {
        	ex.printStackTrace();
            System.exit(1);
        }

    }
}  

