package org.example.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    private String marca;
    private String modelo;
    private String cor;
    private String placa;
    private int ano;
    private List<Carro> listCarros = new ArrayList<>();

    @OneToMany(mappedBy = "carro", cascade = CascadeType.ALL)
    private ArrayList<Aluguel> listAlugueis= new ArrayList<>();


    public Carro(String marca, String modelo, String cor, String placa, int ano) {
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.placa = placa;
        this.ano = ano;
    }

    public List<Carro> listCarros() {return listCarros;}
}
