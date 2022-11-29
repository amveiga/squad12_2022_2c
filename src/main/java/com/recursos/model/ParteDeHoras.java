package com.recursos.model;
import javax.persistence.*;

@Entity
public class ParteDeHoras {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long parteDeHorasID;
    private Long legajoEmpleado;

    public ParteDeHoras(Long legajo) {
        legajoEmpleado = legajo;
    }

    public ParteDeHoras() {

    }

    // GETTERS
    public Long getParteDeHorasID() { return parteDeHorasID; }
    public Long getLegajoEmpleado() { return legajoEmpleado; }


    // SETTERS
    public void setParteDeHorasID(Long parteDeHorasID) { this.parteDeHorasID = parteDeHorasID; }
    public void setLegajoEmpleado(Long legajoEmpleado) { this.legajoEmpleado = legajoEmpleado; }




}
