package com.bodyhealth.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "maquina")
public class Maquina implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  id_maquina;

    private  String nombre;

    private String estado;

    private String observacion;

    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private Proveedor id_proveedor;
}
