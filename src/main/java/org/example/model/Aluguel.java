package org.example.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Aluguel {
    public Aluguel() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getKm_inicial() {
        return km_inicial;
    }

    public void setKm_inicial(long km_inicial) {
        this.km_inicial = km_inicial;
    }

    public long getKm_final() {
        return km_final;
    }

    public void setKm_final(long km_final) {
        this.km_final = km_final;
    }

    public Date getDt_inicio() {
        return dt_inicio;
    }

    public void setDt_inicio(Date dt_inicio) {
        this.dt_inicio = dt_inicio;
    }

    public Date getDt_fim() {
        return dt_fim;
    }

    public void setDt_fim(Date dt_fim) {
        this.dt_fim = dt_fim;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public long getId() {
        return id;
    }

    private Long km_inicial;
    private Long km_final;
    private Date dt_inicio;
    private Date dt_fim;

    @ManyToOne
    @JoinColumn
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "carro_id", nullable = false)
    private Carro carro;

    public Aluguel(Carro carro, Usuario usuario, Long km_inicial, Long km_final, Date dt_inicio, Date dt_fim) {
        this.carro = carro;
        this.usuario = usuario;
        this.km_inicial = km_inicial;
        this.km_final = km_final;
        this.dt_inicio = dt_inicio;
        this.dt_fim = dt_fim;
    }
}
