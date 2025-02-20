package com.example.crudusuario.model;

import jakarta.persistence.*;

@Entity
@Table(name = "futbolistas")
public class Futbolista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String posicion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipo_id", nullable = false)
    private Equipo equipo;

    public Futbolista() {}

    public Futbolista(String nombre, String posicion, Equipo equipo) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.equipo = equipo;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPosicion() {
        return posicion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
