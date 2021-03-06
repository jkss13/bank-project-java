
package com.jbank.banco;

public class ContaEspecial extends Conta {
    
    private double bonus;
    
    public ContaEspecial(String numero, double saldo, double bonus) {
		super(numero, saldo);
		this.bonus = bonus;

	}
    
    public ContaEspecial(String numero, double valor) {
    	this(numero,valor,0.0);
    }
    public ContaEspecial(String numero) {
    	this(numero,0.0,0.0);
    }

    public void creditar(double valor) {
        super.creditar(valor);
        bonus = bonus + (valor * 0.01);
    }

    public void renderBonus() {
        super.creditar(this.bonus);
        bonus = 0;
    }

    public double getBonus() {
        return this.bonus;
    }
}
