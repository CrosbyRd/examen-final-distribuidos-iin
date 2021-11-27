package py.una.server.udp;


import py.una.entidad.SensorMeteorologico;
import py.una.entidad.SensorMeteorologicoJSON;

import java.io.*;
import java.net.*;



class UDPClient {

    public static void main(String a[]) throws Exception {

        // Datos necesario
        String direccionServidor = "127.0.0.1";

        if (a.length > 0) {
            direccionServidor = a[0];
        }

        int puertoServidor = 9876;
        
        try {

            BufferedReader inFromUser =
                    new BufferedReader(new InputStreamReader(System.in));

            DatagramSocket clientSocket = new DatagramSocket();

            InetAddress IPAddress = InetAddress.getByName(direccionServidor);
            System.out.println("Intentando conectar a = " + IPAddress + ":" + puertoServidor +  " via UDP...");

            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            System.out.print("Ingrese los datos de la ciudad");

            String id_estacion = inFromUser.readLine();
            String ciudad = inFromUser.readLine();
            String porcentaje_humedad = inFromUser.readLine();
            String temperatura = inFromUser.readLine();
            String velocidad_viento = inFromUser.readLine();
            String fecha = inFromUser.readLine();
            String hora = inFromUser.readLine();

            
            SensorMeteorologico p = new SensorMeteorologico(id_estacion,ciudad,porcentaje_humedad,temperatura,velocidad_viento,hora);
            
            String datoPaquete = SensorMeteorologicoJSON.objetoString(p);
            sendData = datoPaquete.getBytes();

            System.out.println("Enviar " + datoPaquete + " al servidor. ("+ sendData.length + " bytes)");
            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, IPAddress, puertoServidor);

            clientSocket.send(sendPacket);

            DatagramPacket receivePacket =
                    new DatagramPacket(receiveData, receiveData.length);

            System.out.println("Esperamos si viene la respuesta.");

            //Vamos a hacer una llamada BLOQUEANTE entonces establecemos un timeout maximo de espera
            clientSocket.setSoTimeout(10000);

            try {
                // ESPERAMOS LA RESPUESTA, BLOQUENTE
                clientSocket.receive(receivePacket);

                String respuesta = new String(receivePacket.getData());
                SensorMeteorologico presp = SensorMeteorologicoJSON.stringObjeto(respuesta.trim());
                
                InetAddress returnIPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();

                System.out.println("Respuesta desde =  " + returnIPAddress + ":" + port);
                System.out.println("Asignaturas: ");

                

            } catch (SocketTimeoutException ste) {

                System.out.println("TimeOut: El paquete udp se asume perdido.");
            }
            clientSocket.close();
        } catch (UnknownHostException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
} 

