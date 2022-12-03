package com.bodyhealth.model;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

@Data
public class ControlEntrenadorPK implements Serializable {
    @Column(name = "documentoE")
    private int documentoE;
    //private Date fecha;
}
