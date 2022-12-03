package com.bodyhealth.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Data
@Table(name = "pedido")
//@IdClass(PedidoPK.class)
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pedido;

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_producto;*/
    @ManyToOne
    @JoinColumn(name = "id_producto"/*,insertable = false,updatable = false*/)
    private Producto producto;

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_compra;*/
    @ManyToOne
    @JoinColumn(name = "id_compra"/*,insertable = false,updatable = false*/)
    private Compra compra;

    private int cantidad;

    //private double subtotal;
}
