package com.recursos.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ParteDeHoras {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long parteDeHorasID;

    private Date fecha;

    public ParteDeHoras() {
    }

    public Long getParteDeHorasID() {return parteDeHorasID; }

    public Long getFecha() {return fecha.getTime(); }

    public void setParteDeHorasID(Long parteDeHorasID) { this.parteDeHorasID = parteDeHorasID; }

    public void setFecha(Date fecha) { this.fecha = fecha; }
}
