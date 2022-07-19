/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.utilidades;

import com.google.gson.Gson;

/**
 *
 * @author
 */
public class ConversorJSON {
    
    public static String obtenerLectura(String err){
        
        Gson gson = new Gson();
        String log = gson.fromJson(err, err.getClass());
        return log;
        
    }
    
}
