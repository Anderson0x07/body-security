package com.bodyhealth.model;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@Table(name = "producto")
public class Producto implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_producto;


    private String nombre;


    private int stock;


    private double precio;
    private  String foto;

    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private Proveedor id_proveedor;
}
