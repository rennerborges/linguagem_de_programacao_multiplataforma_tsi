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
public class Item {
    public static final int INALTERADO = 0;
    public static final int ALTERADO = 1;
    public static final int EXCLUIDO = 2;
    
    private int pk;
    private int fk;
    private Produto produto;
    private int qtd;
    private float valorUnitario;
    
    private int status = INALTERADO;

    public Item() {
    }

    public Item(int fk, Produto produto, int qtd, float valorUnitario) {
        this.fk = fk;
        this.produto = produto;
        this.qtd = qtd;
        this.valorUnitario = valorUnitario;
    }

    public Item(int pk, int fk, Produto produto, int qtd, float valorUnitario) {
        this.pk = pk;
        this.fk = fk;
        this.produto = produto;
        this.qtd = qtd;
        this.valorUnitario = valorUnitario;
    }

    public Item(Produto produto, int qtd, float valorUnitario) {
        this.produto = produto;
        this.qtd = qtd;
        this.valorUnitario = valorUnitario;
    }
    
    
    
    public int getStatus() {
        return this.status;
    }
    
    public void markAsDeleted(){
        this.status = EXCLUIDO;
    }
    
    public void resetStatus(){
        this.status = INALTERADO;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        if(this.pk == 0){
            this.pk = pk;
        }
    }

    public int getFk() {
        return fk;
    }

    public void setFk(int fk) {
        this.status = ALTERADO;
        this.fk = fk;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.status = ALTERADO;
        this.produto = produto;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.status = ALTERADO;
        this.qtd = qtd;
    }

    public float getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(float valorUnitario) {
        this.status = ALTERADO;
        this.valorUnitario = valorUnitario;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.pk;
        hash = 83 * hash + this.fk;
        hash = 83 * hash + Objects.hashCode(this.produto);
        hash = 83 * hash + this.qtd;
        hash = 83 * hash + Float.floatToIntBits(this.valorUnitario);
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
        final Item other = (Item) obj;
        if (this.pk != other.pk) {
            return false;
        }
        if (this.fk != other.fk) {
            return false;
        }
        if (this.qtd != other.qtd) {
            return false;
        }
        if (Float.floatToIntBits(this.valorUnitario) != Float.floatToIntBits(other.valorUnitario)) {
            return false;
        }
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CompraItem{" + "pk=" + pk + ", fk=" + fk + ", produto=" + produto + ", qtd=" + qtd + ", valorUnitario=" + valorUnitario + '}';
    }
    
}
