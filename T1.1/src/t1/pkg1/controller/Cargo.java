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
public class Cargo {
    private String nome;
    private String descricao;
    private int pk_cargo;

    public Cargo() {
    }

    public Cargo(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Cargo(String nome, String descricao, int pk_cargo) {
        this.nome = nome;
        this.descricao = descricao;
        this.pk_cargo = pk_cargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPk_cargo() {
        return pk_cargo;
    }

    public void setPk_cargo(int pk_cargo) {
        this.pk_cargo = pk_cargo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.nome);
        hash = 89 * hash + Objects.hashCode(this.descricao);
        hash = 89 * hash + this.pk_cargo;
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
        final Cargo other = (Cargo) obj;
        if (this.pk_cargo != other.pk_cargo) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cargo{" + "nome=" + nome + ", descricao=" + descricao + ", pk_cargo=" + pk_cargo + '}';
    }
    
    
}
