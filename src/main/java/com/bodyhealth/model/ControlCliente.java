package com.bodyhealth.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "control_cliente")
public class ControlCliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_controlcliente;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente id_cliente;

    private double peso;

    private double estatura;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date fecha;

}
