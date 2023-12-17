package com.api.downupload.model;

import javax.persistence.*;

@Entity
@Table(name = "Documento")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDocumento;

    @Lob
    @Column(name = "datosDocumento")
    private byte[] datosDocumento;

    @Column(name = "nombreDocumento")
    private String nombreDocumento;

    @Column(name = "tipoDocumento")
    private String tipoDocumento;

    // Getters y setters

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public byte[] getDatosDocumento() {
        return datosDocumento;
    }

    public void setDatosDocumento(byte[] datosDocumento) {
        this.datosDocumento = datosDocumento;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
}
