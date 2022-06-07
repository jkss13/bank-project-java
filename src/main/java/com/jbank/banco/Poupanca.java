
package com.jbank.banco;

public class Poupanca extends Conta {
    
	public Poupanca(String numero, double valor) {
    	super(numero,valor);
    }
	
    public Poupanca(String numero) {
        super(numero);
    }
    
    public void renderJuros(double taxa) {
        double juros = this.getSaldo() * taxa;
        this.creditar(juros);
    }
}
