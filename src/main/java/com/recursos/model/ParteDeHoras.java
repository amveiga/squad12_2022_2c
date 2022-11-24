package com.recursos.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ParteDeHoras {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long parteDeHorasID;

    private Long legajoEmpleado;

    private Date fechaDeLaCarga;

    private String estado;


    public ParteDeHoras() {
    }

    public Long getParteDeHorasID() { return parteDeHorasID; }

    public Long getLegajoEmpleado() { return legajoEmpleado; }

    public Date getFechaDeLaCarga() { return fechaDeLaCarga; }

    public String getEstado() { return estado; }

    public void setParteDeHorasID(Long parteDeHorasID) { this.parteDeHorasID = parteDeHorasID; }

    public void setLegajoEmpleado(Long legajoEmpleado) { this.legajoEmpleado = legajoEmpleado; }

    public void setFechaDeLaCarga(Date fechaDeLaCarga) { this.fechaDeLaCarga = fechaDeLaCarga; }

    public void setEstado(String estado) { this.estado = estado; }
}
