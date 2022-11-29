package com.recursos.model;
import javax.persistence.*;

@Entity
public class ParteDeHoras {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long parteDeHorasID;
    private Long tareaID;
    private Long legajoEmpleado;

    public ParteDeHoras(Long legajo) {
        legajoEmpleado = legajo;
    }

    public ParteDeHoras() {

    }

    // GETTERS
    public Long getParteDeHorasID() { return parteDeHorasID; }

    public Long getTareaID() { return tareaID; }

    public Long getLegajoEmpleado() { return legajoEmpleado; }
    public int getCantidadDeHorasTrabajadas() { return cantidadDeHorasTrabajadas; }

    public Date getFechaDeLaTareaACargar() { return fechaDeLaTareaACargar; }

    public String getEstado() { return estado; }

    public Tipo getTipoDeParteDeHoras() { return tipoDeParteDeHoras; }


    // SETTERS
    public void setParteDeHorasID(Long parteDeHorasID) { this.parteDeHorasID = parteDeHorasID; }
    public void setLegajoEmpleado(Long legajoEmpleado) { this.legajoEmpleado = legajoEmpleado; }


    public void setTipoDeParteDeHoras(Tipo tipoDeParteDeHoras) { this.tipoDeParteDeHoras = tipoDeParteDeHoras; }


}
