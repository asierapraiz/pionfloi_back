package com.yoymico.starter.models.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "tareas" )
public class Tarea implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Enumerated(value= EnumType.STRING)
//    private NombresTareas name;
    private String name;

    @JsonIgnoreProperties(value={"valoraciones", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "valoracion_id",  referencedColumnName = "id")
    private Valoracion valoracion;

    public Tarea(){}


/*
    @JsonIgnoreProperties(value={"reto", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reto_id")
    private Reto reto;*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Valoracion getValoracion() {
        return valoracion;
    }

    public void setValoracion(Valoracion valoracion) {
        this.valoracion = valoracion;
    }
}
