/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t1.pkg1.controller;

import java.util.Objects;

/**
 *
 * @author renner
 */
public class Produto {
    private int pk;
    private String nome;
    private int estoqueMinimo;
    private int qtdEstoque;

    public Produto() {
    }

    public Produto(String nome, int estoqueMinimo, int qtdEstoque) {
        this.nome = nome;
        this.estoqueMinimo = estoqueMinimo;
        this.qtdEstoque = qtdEstoque;
    }

    public Produto(int pk, String nome, int estoqueMinimo, int qtdEstoque) {
        this.pk = pk;
        this.nome = nome;
        this.estoqueMinimo = estoqueMinimo;
        this.qtdEstoque = qtdEstoque;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        if(this.pk == 0){
            this.pk = pk;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.pk;
        hash = 71 * hash + Objects.hashCode(this.nome);
        hash = 71 * hash + this.estoqueMinimo;
        hash = 71 * hash + this.qtdEstoque;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (this.pk != other.pk) {
            return false;
        }
        if (this.estoqueMinimo != other.estoqueMinimo) {
            return false;
        }
        if (this.qtdEstoque != other.qtdEstoque) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produto{" + "pk=" + pk + ", nome=" + nome + ", estoqueMinimo=" + estoqueMinimo + ", qtdEstoque=" + qtdEstoque + '}';
    }
   
}
