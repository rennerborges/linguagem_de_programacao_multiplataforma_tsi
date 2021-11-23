/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package t1.pkg1.controller;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author renner
 */
public class Financeiro {
    public static final int INALTERADO = 0;
    public static final int ALTERADO = 1;
    public static final int EXCLUIDO = 2;
    
    private int pk;
    private int fk;
    private Date dataEmissao;
    private Date dataVencimento;
    private Date dataBaixa;
    private float valor;
    private String formaPagamento;

    private int status = INALTERADO;
    
    public Financeiro() {
    }

    public Financeiro(Date dataEmissao, Date dataVencimento, Date dataBaixa, float valor, String formaPagamento) {
        this.dataEmissao = dataEmissao;
        this.dataVencimento = dataVencimento;
        this.dataBaixa = dataBaixa;
        this.valor = valor;
        this.formaPagamento = formaPagamento;
    }

    public Financeiro(Date dataEmissao, Date dataVencimento, float valor) {
        this.dataEmissao = dataEmissao;
        this.dataVencimento = dataVencimento;
        this.valor = valor;
    }
    
    public Financeiro(int pk, int fk, Date dataEmissao, Date dataVencimento, Date dataBaixa, float valor, String formaPagamento) {
        this.pk = pk;
        this.fk = fk;
        this.dataEmissao = dataEmissao;
        this.dataVencimento = dataVencimento;
        this.dataBaixa = dataBaixa;
        this.valor = valor;
        this.formaPagamento = formaPagamento;
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

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.status = ALTERADO;
        this.dataEmissao = dataEmissao;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.status = ALTERADO;
        this.dataVencimento = dataVencimento;
    }

    public Date getDataBaixa() {
        return dataBaixa;
    }

    public void setDataBaixa(Date dataBaixa) {
        this.status = ALTERADO;
        this.dataBaixa = dataBaixa;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.status = ALTERADO;
        this.valor = valor;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.status = ALTERADO;
        this.formaPagamento = formaPagamento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.pk;
        hash = 47 * hash + this.fk;
        hash = 47 * hash + Objects.hashCode(this.dataEmissao);
        hash = 47 * hash + Objects.hashCode(this.dataVencimento);
        hash = 47 * hash + Objects.hashCode(this.dataBaixa);
        hash = 47 * hash + Float.floatToIntBits(this.valor);
        hash = 47 * hash + Objects.hashCode(this.formaPagamento);
        hash = 47 * hash + this.status;
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
        final Financeiro other = (Financeiro) obj;
        if (this.pk != other.pk) {
            return false;
        }
        if (this.fk != other.fk) {
            return false;
        }
        if (Float.floatToIntBits(this.valor) != Float.floatToIntBits(other.valor)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.formaPagamento, other.formaPagamento)) {
            return false;
        }
        if (!Objects.equals(this.dataEmissao, other.dataEmissao)) {
            return false;
        }
        if (!Objects.equals(this.dataVencimento, other.dataVencimento)) {
            return false;
        }
        if (!Objects.equals(this.dataBaixa, other.dataBaixa)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Financeiro{" + "pk=" + pk + ", fk=" + fk + ", dataEmissao=" + dataEmissao + ", dataVencimento=" + dataVencimento + ", dataBaixa=" + dataBaixa + ", valor=" + valor + ", formaPagamento=" + formaPagamento + ", status=" + status + '}';
    }
    
}
