package com.example.crudusuario.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "equipos")
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String ciudad;
    private int fundacion;

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Futbolista> futbolistas;

    public Equipo() {}

    public Equipo(String nombre, String ciudad, int fundacion) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.fundacion = fundacion;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public int getFundacion() {
        return fundacion;
    }

    public List<Futbolista> getFutbolistas() {
        return futbolistas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setFundacion(int fundacion) {
        this.fundacion = fundacion;
    }

    public void setFutbolistas(List<Futbolista> futbolistas) {
        this.futbolistas = futbolistas;
    }
}

