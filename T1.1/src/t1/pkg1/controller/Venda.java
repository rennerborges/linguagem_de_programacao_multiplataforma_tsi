/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t1.pkg1.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author renner
 */
public class Venda {
    private int pk;
    private Cliente cliente;
    private Funcionario vendedor;
    private int numero;
    private Date date;
    private ArrayList<Item> vendasItens;
    private ArrayList<Financeiro> recebimentos;
   
    public Venda() {
    }

    public Venda(Cliente cliente, Funcionario vendedor, int numero, Date date, ArrayList<Item> vendasItens) {
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.numero = numero;
        this.date = date;
        this.vendasItens = vendasItens;
    }

    public Venda(Cliente cliente, Funcionario vendedor, int numero, Date date, ArrayList<Item> vendasItens, ArrayList<Financeiro> recebimentos) {
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.numero = numero;
        this.date = date;
        this.vendasItens = vendasItens;
        this.recebimentos = recebimentos;
    }
    
    public Venda(int pk, Cliente cliente, Funcionario vendedor, int numero, Date date, ArrayList<Item> vendasItens) {
        this.pk = pk;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.numero = numero;
        this.date = date;
        this.vendasItens = vendasItens;
    }

    public Venda(int pk, Cliente cliente, Funcionario vendedor, int numero, Date date, ArrayList<Item> vendasItens, ArrayList<Financeiro> recebimentos) {
        this.pk = pk;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.numero = numero;
        this.date = date;
        this.vendasItens = vendasItens;
        this.recebimentos = recebimentos;
    }
    
    

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        if(this.pk == 0){
            this.pk = pk;
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Funcionario vendedor) {
        this.vendedor = vendedor;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Item> getVendasItens() {
        return vendasItens;
    }

    public void setVendasItens(ArrayList<Item> vendasItens) {
        this.vendasItens = vendasItens;
    }

    public ArrayList<Financeiro> getRecebimentos() {
        return recebimentos;
    }

    public void setRecebimentos(ArrayList<Financeiro> financeiros) {
        this.recebimentos = financeiros;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.pk;
        hash = 47 * hash + Objects.hashCode(this.cliente);
        hash = 47 * hash + Objects.hashCode(this.vendedor);
        hash = 47 * hash + this.numero;
        hash = 47 * hash + Objects.hashCode(this.date);
        hash = 47 * hash + Objects.hashCode(this.vendasItens);
        hash = 47 * hash + Objects.hashCode(this.recebimentos);
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
        final Venda other = (Venda) obj;
        if (this.pk != other.pk) {
            return false;
        }
        if (this.numero != other.numero) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.vendedor, other.vendedor)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.vendasItens, other.vendasItens)) {
            return false;
        }
        if (!Objects.equals(this.recebimentos, other.recebimentos)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Venda{" + "pk=" + pk + ", cliente=" + cliente + ", vendedor=" + vendedor + ", numero=" + numero + ", date=" + date + ", vendasItens=" + vendasItens + ", recebimentos=" + recebimentos + '}';
    }

}
