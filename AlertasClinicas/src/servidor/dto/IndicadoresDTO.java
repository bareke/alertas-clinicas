package servidor.dto;

import java.io.Serializable;

/**
 *
 * @authors Alejandro Muñoz - Cristian Collazos
 */
public class IndicadoresDTO implements Serializable {

    //<editor-fold defaultstate="collapsed" desc="Atributos">
    private int frecuenciaCardiaca;
    private int presionArterial;
    private int frecuenciaRespiratoria;
    private float temperatura;
    private int oxigeno;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    public IndicadoresDTO() {
    }

    public IndicadoresDTO(int frecuenciaCardiaca, int presionSistolica, int presionDiastolica, int frecuenciaRespiratoria, float temperatura, int oxigeno) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.presionArterial = presionSistolica;
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
        this.temperatura = temperatura;
        this.oxigeno = oxigeno;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters">
    public int getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public int getPresionArterial() {
        return presionArterial;
    }

    public int getFrecuenciaRespiratoria() {
        return frecuenciaRespiratoria;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public int getOxigeno() {
        return oxigeno;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setters">
    public void setFrecuenciaCardiaca(int frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public void setPresionArterial(int presionArterial) {
        this.presionArterial = presionArterial;
    }

    public void setFrecuenciaRespiratoria(int frecuenciaRespiratoria) {
        this.frecuenciaRespiratoria = frecuenciaRespiratoria;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public void setOxigeno(int oxigeno) {
        this.oxigeno = oxigeno;
    }
    //</editor-fold>
}
