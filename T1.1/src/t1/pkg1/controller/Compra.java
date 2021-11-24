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
public class Compra {
    private int pk;
    private Fornecedor fornecedor;
    private int numero;
    private Date data;
    private ArrayList<Item> comprasItens;
    private ArrayList<Financeiro> pagamentos;
    
    public Compra() {
    }

    public Compra(Fornecedor fornecedor, int numero, Date data, ArrayList<Item> comprasItens) {
        this.fornecedor = fornecedor;
        this.numero = numero;
        this.data = data;
        this.comprasItens = comprasItens;
    }

    public Compra(int pk, Fornecedor fornecedor, int numero, Date data, ArrayList<Item> comprasItens) {
        this.pk = pk;
        this.fornecedor = fornecedor;
        this.numero = numero;
        this.data = data;
        this.comprasItens = comprasItens;
    }

    public Compra(int pk, Fornecedor fornecedor, int numero, Date data, ArrayList<Item> comprasItens, ArrayList<Financeiro> pagamentos) {
        this.pk = pk;
        this.fornecedor = fornecedor;
        this.numero = numero;
        this.data = data;
        this.comprasItens = comprasItens;
        this.pagamentos = pagamentos;
    }

    public Compra(Fornecedor fornecedor, int numero, Date data, ArrayList<Item> comprasItens, ArrayList<Financeiro> pagamentos) {
        this.fornecedor = fornecedor;
        this.numero = numero;
        this.data = data;
        this.comprasItens = comprasItens;
        this.pagamentos = pagamentos;
    }
    
    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        if(this.pk == 0){
            this.pk = pk;
        }
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public ArrayList<Item> getComprasItens() {
        return comprasItens;
    }

    public void setComprasItens(ArrayList<Item> comprasItens) {
        this.comprasItens = comprasItens;
    }

    public ArrayList<Financeiro> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(ArrayList<Financeiro> pagamentos) {
        this.pagamentos = pagamentos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.pk;
        hash = 53 * hash + Objects.hashCode(this.fornecedor);
        hash = 53 * hash + this.numero;
        hash = 53 * hash + Objects.hashCode(this.data);
        hash = 53 * hash + Objects.hashCode(this.comprasItens);
        hash = 53 * hash + Objects.hashCode(this.pagamentos);
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
        final Compra other = (Compra) obj;
        if (this.pk != other.pk) {
            return false;
        }
        if (this.numero != other.numero) {
            return false;
        }
        if (!Objects.equals(this.fornecedor, other.fornecedor)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.comprasItens, other.comprasItens)) {
            return false;
        }
        if (!Objects.equals(this.pagamentos, other.pagamentos)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Compra{" + "pk=" + pk + ", fornecedor=" + fornecedor + ", numero=" + numero + ", data=" + data + ", comprasItens=" + comprasItens + ", pagamentos=" + pagamentos + '}';
    }
    
}
