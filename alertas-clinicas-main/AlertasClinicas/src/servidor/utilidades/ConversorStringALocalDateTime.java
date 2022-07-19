/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.utilidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Sebastian_Arenas
 */
public class ConversorStringALocalDateTime {
    
    public static LocalDateTime deserialize(String ldtString){
        return LocalDateTime.parse(ldtString,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
    
}
