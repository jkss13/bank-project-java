package com.jbank.banco;

import java.io.Serializable;

public abstract class ContaAbstrata implements Serializable {
    private String numero;
    private double saldo;
    
    public ContaAbstrata(String numero, double valor) {
    	this.numero=numero;
    	this.saldo=valor;
    }
    public ContaAbstrata(String numero) {
        this(numero,0);
    }
    
    public String getNumero() {
        return this.numero;
    }
    
    public double getSaldo() {
        return this.saldo;
    }
    
    public void creditar(double valor) {
        this.saldo = this.saldo + valor;
    }
    
    public abstract void debitar(double valor) throws SaldoInsuficienteException;

    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    public boolean equals(String numero){
        return this.numero.equals(numero);
    }
    
    public boolean equals(ContaAbstrata conta){
        return this.numero.equals(conta.getNumero());
    }
        
}
