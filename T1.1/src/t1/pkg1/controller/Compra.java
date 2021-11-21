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
    private ArrayList<CompraItem> comprasItens;

    public Compra() {
    }

    public Compra(Fornecedor fornecedor, int numero, Date data, ArrayList<CompraItem> comprasItens) {
        this.fornecedor = fornecedor;
        this.numero = numero;
        this.data = data;
        this.comprasItens = comprasItens;
    }

    public Compra(int pk, Fornecedor fornecedor, int numero, Date data, ArrayList<CompraItem> comprasItens) {
        this.pk = pk;
        this.fornecedor = fornecedor;
        this.numero = numero;
        this.data = data;
        this.comprasItens = comprasItens;
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

    public ArrayList<CompraItem> getComprasItens() {
        return comprasItens;
    }

    public void setComprasItens(ArrayList<CompraItem> comprasItens) {
        this.comprasItens = comprasItens;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.pk;
        hash = 89 * hash + Objects.hashCode(this.fornecedor);
        hash = 89 * hash + this.numero;
        hash = 89 * hash + Objects.hashCode(this.data);
        hash = 89 * hash + Objects.hashCode(this.comprasItens);
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
        return true;
    }

    @Override
    public String toString() {
        return "Compra{" + "pk=" + pk + ", fornecedor=" + fornecedor + ", numero=" + numero + ", data=" + data + ", comprasItens=" + comprasItens + '}';
    }
    
}
