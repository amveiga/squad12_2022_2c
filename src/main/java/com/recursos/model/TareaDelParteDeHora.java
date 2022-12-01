package com.recursos.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

enum TipoTarea {
    TAREA_PROYECTO,
    INCIDENCIA,
    ADMINISTRATIVA,
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

    private String descripcion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaDeTareaACargar;

    private int cantidadDeHorasTrabajadas;

    private TipoEstado estado;

    private TipoTarea tipoDeTarea;

    public TareaDelParteDeHora()  {
    }

    // GETTERS
    public Long getTareaDelParteDeHoraId() { return tareaDelParteDeHoraId; }
    public Long getParteDeHoraId() { return parteDeHoraId; }
    public String getTareaId() { return tareaId; }
    public String getProyectoId() { return proyectoId; }

    public Date getFechaDeLaTareaACargar() { return fechaDeTareaACargar; }
    public int getCantidadDeHorasTrabajadas() { return cantidadDeHorasTrabajadas; }
    public TipoTarea getTipoDeTarea() { return tipoDeTarea; }
    public TipoEstado getEstado() { return estado; }

    // SETTERS
    public void setParteDeHoraId(Long parteDeHoraId) { this.parteDeHoraId = parteDeHoraId; }
    public void setTareaId(String tareaId) { this.tareaId = tareaId; }
    public void setProyectoId(String proyectoId) { this.proyectoId = proyectoId; }
    public void setFechaDeLaTareaACargar(Date fechaDeTareaACargar) { this.fechaDeTareaACargar = fechaDeTareaACargar; }
    public void setTipoDeTarea(TipoTarea tipoDeParteDeHoras) { this.tipoDeTarea = tipoDeParteDeHoras; }
    public void setCantidadDeHorasTrabajadas(int cantidadDeHorasTrabajadas) { this.cantidadDeHorasTrabajadas = cantidadDeHorasTrabajadas; }
    public void setEstado(TipoEstado estado) {
        this.estado = estado;
    }

}
