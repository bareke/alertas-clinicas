/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.utilidades;

import com.google.gson.Gson;

/**
 *
 * @authors Alejandro Muñoz - Cristian Collazos
 */
public class ConversorJSON {

    public static String obtenerLectura(String msg) {

        Gson gson = new Gson();
        String log = gson.fromJson(msg, msg.getClass());
        System.out.println("log recibido: " + log);
        return log;

    }

    public static String convertirJson(String err) {

        Gson gson = new Gson();
        String JSON = gson.toJson(err);
        System.out.println("log enviado: " + JSON);
        return JSON;

    }

}
