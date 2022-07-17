/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.dto;

import java.io.Serializable;

/**
 *
 * @author Alejandro Muñoz -
 */
public class IndicadoresDTO implements Serializable{
    private int FrecuenciaCardiaca;
    private int PresionSistolica;
    private int PresionDiastolica;
    private int FrecuenciaRespiratoria;
    private float Temperatura;
    private int Oxigeno;
    
    public IndicadoresDTO(){}

    public IndicadoresDTO(int FrecuenciaCardiaca, int PresionSistolica, int PresionDiastolica, int FrecuenciaRespiratoria, float Temperatura, int Oxigeno) {
        this.FrecuenciaCardiaca = FrecuenciaCardiaca;
        this.PresionSistolica = PresionSistolica;
        this.PresionDiastolica = PresionDiastolica;
        this.FrecuenciaRespiratoria = FrecuenciaRespiratoria;
        this.Temperatura = Temperatura;
        this.Oxigeno = Oxigeno;
    }

    public int getFrecuenciaCardiaca() {
        return FrecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(int FrecuenciaCardiaca) {
        this.FrecuenciaCardiaca = FrecuenciaCardiaca;
    }

    public int getPresionSistolica() {
        return PresionSistolica;
    }

    public void setPresionSistolica(int PresionSistolica) {
        this.PresionSistolica = PresionSistolica;
    }

    public int getPresionDiastolica() {
        return PresionDiastolica;
    }

    public void setPresionDiastolica(int PresionDiastolica) {
        this.PresionDiastolica = PresionDiastolica;
    }

    public int getFrecuenciaRespiratoria() {
        return FrecuenciaRespiratoria;
    }

    public void setFrecuenciaRespiratoria(int FrecuenciaRespiratoria) {
        this.FrecuenciaRespiratoria = FrecuenciaRespiratoria;
    }

    public float getTemperatura() {
        return Temperatura;
    }

    public void setTemperatura(float Temperatura) {
        this.Temperatura = Temperatura;
    }

    public int getOxigeno() {
        return Oxigeno;
    }

    public void setOxigeno(int Oxigeno) {
        this.Oxigeno = Oxigeno;
    }
    
    
    
}
