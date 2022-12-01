package com.recursos.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

@Entity
public class TareaDelParteDeHora {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tareaDelParteDeHoraId;

    private Long parteDeHoraId;

    private String proyectoId;
    private String tareaId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaDeTareaACargar;

    private int cantidadDeHorasTrabajadas;

    private String estado;

    private Tipo tipoDeParteDeHoras;

    public String descripcion;

    public TareaDelParteDeHora()  {
    }



    // GETTERS
    public Long getTareaDelParteDeHoraId() { return tareaDelParteDeHoraId; }
    public Long getParteDeHoraId() { return parteDeHoraId; }
    public String getTareaId() { return tareaId; }
    public String getProyectoId() { return proyectoId; }

    public Date getFechaDeLaTareaACargar() { return fechaDeTareaACargar; }
    public int getCantidadDeHorasTrabajadas() { return cantidadDeHorasTrabajadas; }
    public Tipo getTipoDeParteDeHoras() { return tipoDeParteDeHoras; }
    public String getEstado() { return estado; }
    public String getDescripcion() { return descripcion; }

    // SETTERS
    public void setParteDeHoraId(Long parteDeHoraId) { this.parteDeHoraId = parteDeHoraId; }
    public void setTareaId(String tareaId) { this.tareaId = tareaId; }
    public void setProyectoId(String proyectoId) { this.proyectoId = proyectoId; }
    public void setFechaDeLaTareaACargar(Date fechaDeTareaACargar) { this.fechaDeTareaACargar = fechaDeTareaACargar; }
    public void setTipoDeParteDeHoras(Tipo tipoDeParteDeHoras) { this.tipoDeParteDeHoras = tipoDeParteDeHoras; }
    public void setCantidadDeHorasTrabajadas(int cantidadDeHorasTrabajadas) { this.cantidadDeHorasTrabajadas = cantidadDeHorasTrabajadas; }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
