package com.recursos.model;

import javax.persistence.*;
import java.util.Date;

enum Tipo {
    TAREA_PROYECTO,
    INCIDENCIA,
    ADMINISTRATIVA_REUNION,
    ADMINISTRATIVA_CAPACITACION,
    ADMINISTRATIVA_CURSO,
    GUARDIA,
    LICENCIA
}

enum Estado {
    BORRADOR,
    VALIDACION_PENDIENTE,
    APROBADO,
    DESAPROBADO
}

enum Ubicacion {
    REMOTO,
    OFICINA
}

@Entity
public class ParteDeHoras {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long parteDeHorasID;
    private Long legajoEmpleado;
    private int cantidadDeHorasTrabajadas;
    private Date fechaDeLaTareaACargar;
    private Tipo tipoDeParteDeHoras;
    private Estado estado;


    public ParteDeHoras() {
    }

    public Long getParteDeHorasID() { return parteDeHorasID; }

    public Long getLegajoEmpleado() { return legajoEmpleado; }

    public Date getFechaDeLaTareaACargar() { return fechaDeLaTareaACargar; }

    public Estado getEstado() { return estado; }

    public Tipo getTipoDeParteDeHoras() { return tipoDeParteDeHoras; }

    public void setParteDeHorasID(Long parteDeHorasID) { this.parteDeHorasID = parteDeHorasID; }

    public void setLegajoEmpleado(Long legajoEmpleado) { this.legajoEmpleado = legajoEmpleado; }

    public void setFechaDeLaTareaACargar(Date fechaDeLaCarga) { this.fechaDeLaTareaACargar = fechaDeLaCarga; }

    public void setEstado(Estado estado) { this.estado = estado; }

    public void setTipoDeParteDeHoras(Tipo tipoDeParteDeHoras) { this.tipoDeParteDeHoras = tipoDeParteDeHoras; }
}
