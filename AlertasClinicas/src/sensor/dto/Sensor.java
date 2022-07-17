/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensor.dto;

import java.io.Serializable;
import servidor.dto.IndicadoresDTO;
/**
 *
 * @author Alejandro Muñoz - 
 */
public class Sensor implements Serializable{
    private int id;
    private IndicadoresDTO indicadores;

    public Sensor(){}
    
    public Sensor(int id, IndicadoresDTO indicadores) {
        this.id = id;
        this.indicadores = indicadores;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public IndicadoresDTO getIndicadores() {
        return indicadores;
    }

    public void setIndicadores(IndicadoresDTO indicadores) {
        this.indicadores = indicadores;
    }
    
    

}
