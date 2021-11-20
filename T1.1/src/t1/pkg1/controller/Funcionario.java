/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t1.pkg1.controller;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author renner
 */
public class Funcionario {
    private String nome;
    private String cpf;
    private ArrayList<Endereco> enderecos;
    private Cargo cargo;
    private int pk;

    public Funcionario() {
    }

    public Funcionario(String nome, String cpf, ArrayList<Endereco> enderecos, Cargo cargo) {
        this.nome = nome;
        this.cpf = cpf;
        this.enderecos = enderecos;
        this.cargo = cargo;
    }

    public Funcionario(String nome, String cpf, ArrayList<Endereco> enderecos, Cargo cargo, int pk) {
        this.nome = nome;
        this.cpf = cpf;
        this.enderecos = enderecos;
        this.cargo = cargo;
        this.pk = pk;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ArrayList<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(ArrayList<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        if(this.pk == 0){
            this.pk = pk;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.nome);
        hash = 29 * hash + Objects.hashCode(this.cpf);
        hash = 29 * hash + Objects.hashCode(this.enderecos);
        hash = 29 * hash + Objects.hashCode(this.cargo);
        hash = 29 * hash + this.pk;
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
        final Funcionario other = (Funcionario) obj;
        if (this.pk != other.pk) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.enderecos, other.enderecos)) {
            return false;
        }
        if (!Objects.equals(this.cargo, other.cargo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "nome=" + nome + ", cpf=" + cpf + ", enderecos=" + enderecos + ", cargo=" + cargo + ", pk=" + pk + '}';
    }
    
}
