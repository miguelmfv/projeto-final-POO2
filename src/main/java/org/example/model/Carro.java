package org.example.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Carro {
    public Carro() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String marca;
    private String modelo;
    private String cor;
    private String placa;
    private Float preco;
    private int ano;
    private Boolean alugado;

    public Long getKilometragem() {
        return kilometragem;
    }

    public void setKilometragem(Long kilometragem) {
        this.kilometragem = kilometragem;
    }

    private Long kilometragem;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Boolean getAlugado() {
        return alugado;
    }

    public void setAlugado(Boolean alugado) {
        this.alugado = alugado;
    }

    @OneToMany(mappedBy = "carro", cascade = CascadeType.ALL)
    private List<Aluguel> listAluguel = new ArrayList<>();

    public Carro(String marca, String modelo, String cor, String placa, int ano, Boolean alugado, Float preco, Long kilometragem) {
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.placa = placa;
        this.ano = ano;
        this.alugado = alugado;
        this.preco = preco;
        this.kilometragem = kilometragem;
    }
}
