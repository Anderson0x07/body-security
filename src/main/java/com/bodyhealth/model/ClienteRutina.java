package com.bodyhealth.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "cliente_rutina")
public class ClienteRutina implements Serializable {
    @Id
    private int id_clienterutina;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente id_cliente;
    @ManyToOne
    @JoinColumn(name = "id_rutina")
    private Rutina id_rutina;
}
