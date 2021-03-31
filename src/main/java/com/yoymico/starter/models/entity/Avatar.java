package com.yoymico.starter.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Avatar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer pelo;

    private Integer cejas;

    private Integer ojos;

    private Integer nariz;

    private Integer boca;

    private Integer cara;

    private Integer torso;





    private static final long serialVersionUID = 1L;

    public Avatar(){};

    public Avatar(Integer pelo, Integer cejas, Integer ojos, Integer nariz, Integer boca, Integer cara, Integer torso) {
        this.pelo=pelo;
        this.cejas=cejas;
        this.ojos=ojos;
        this.nariz=nariz;
        this.boca=boca;
        this.cara=cara;
        this.torso=torso;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPelo() {
        return pelo;
    }

    public void setPelo(Integer pelo) {
        this.pelo = pelo;
    }

    public Integer getCejas() {
        return cejas;
    }

    public void setCejas(Integer cejas) {
        this.cejas = cejas;
    }

    public Integer getOjos() {
        return ojos;
    }

    public void setOjos(Integer ojos) {
        this.ojos = ojos;
    }

    public Integer getNariz() {
        return nariz;
    }

    public void setNariz(Integer nariz) {
        this.nariz = nariz;
    }

    public Integer getBoca() {
        return boca;
    }

    public void setBoca(Integer boca) {
        this.boca = boca;
    }

    public Integer getCara() {
        return cara;
    }

    public void setCara(Integer cara) {
        this.cara = cara;
    }

    public Integer getTorso() {
        return torso;
    }

    public void setTorso(Integer torso) {
        this.torso = torso;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
