package com.bodyhealth.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "cliente_detalle")
//@IdClass(ClienteDetallePK.class)
public class ClienteDetalle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_factura;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente id_cliente;

    @ManyToOne
    @JoinColumn(name = "id_detalle")
    private Detalle id_detalle;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date fecha_inicio;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date fecha_fin;

    @ManyToOne
    @JoinColumn(name = "id_metodopago")
    private MetodoPago id_metodopago;
}
