package com.recursos.model;

import javax.persistence.*;

@Entity
public class ParteDeHoras {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long parteDeHorasID;

    public ParteDeHoras() {
    }

    public Long getParteDeHorasID() {return parteDeHorasID; }

    public void setParteDeHorasID(Long parteDeHorasID) { this.parteDeHorasID = parteDeHorasID; }
}
