package com.recursos.model;

import com.recursos.exceptions.EstadoInvalidoException;
import com.recursos.exceptions.NoSePuedeModificarUnParteAprobadoException;

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

/*
enum Estado {
    BORRADOR,
    VALIDACION_PENDIENTE,
    APROBADO,
    DESAPROBADO
}
*/


@Entity
public class ParteDeHoras {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long parteDeHorasID;
    private Long tareaID;
    private Long legajoEmpleado;
    private int cantidadDeHorasTrabajadas;
    private Date fechaDeLaTareaACargar;
    private Tipo tipoDeParteDeHoras;
    private String estado;

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

    public void setTareaID(Long tareaID) { this.tareaID = tareaID; }

    public void setLegajoEmpleado(Long legajoEmpleado) { this.legajoEmpleado = legajoEmpleado; }

    public void setCantidadDeHorasTrabajadas(int cantidadDeHorasTrabajadas) { this.cantidadDeHorasTrabajadas = cantidadDeHorasTrabajadas; }

    public void setFechaDeLaTareaACargar(Date fechaDeLaCarga) { this.fechaDeLaTareaACargar = fechaDeLaCarga; }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTipoDeParteDeHoras(Tipo tipoDeParteDeHoras) { this.tipoDeParteDeHoras = tipoDeParteDeHoras; }


}
