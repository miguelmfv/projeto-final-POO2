package org.example.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    public Boolean getAlugado() {
        return alugado;
    }

    public void setAlugado(Boolean alugado) {
        this.alugado = alugado;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private long km_inicial;
    private long km_final;
    private Date dt_inicio;
    private Date dt_fim;
    private Boolean alugado = false;

    @ManyToOne
    @JoinColumn
    private Usuario usuario;

    @ManyToOne
    @JoinColumn
    private Carro carro;

    public Aluguel(long km_inicial, long km_final, Date dt_inicio, Date dt_fim, Boolean alugado) {
        this.km_final = km_final;
        this.km_inicial = km_inicial;
        this.dt_inicio = dt_inicio;
        this.dt_fim = dt_fim;
        this.alugado = alugado;
    }
}
