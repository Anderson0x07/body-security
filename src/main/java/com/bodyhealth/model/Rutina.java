package com.bodyhealth.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "rutina")
public class Rutina implements Serializable {
    @Id
    private int id_rutina;
    private String nombre_rutina;
    private String descripcion;
}
