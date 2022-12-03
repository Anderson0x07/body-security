package com.bodyhealth.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Data
@Table(name = "entrenador")
public class Entrenador implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_entrenador;

    private int documentoE;

    private String tipo_documento;

    private String nombre;

    private String apellido;

    private String telefono;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date fechaN;

    private String email;

    private String password;

    private String foto;

    private String experiencia;

    private String hoja_vida;

    private String titulo_academico;
    private String jornada;

    private boolean estado;

    @ManyToOne
    @JoinColumn(name="id_admin")
    private Administrador id_admin;

    @ManyToOne
    @JoinColumn(name="id_rol")
    private Rol id_rol;


}