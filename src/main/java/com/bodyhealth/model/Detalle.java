package com.bodyhealth.model;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Data
@Table(name = "detalle")
public class Detalle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_detalle;

    /*@ManyToOne
    @JoinColumn(name = "id_plan")*/
    private String plan;

    private double precio;
    private int meses;
}
