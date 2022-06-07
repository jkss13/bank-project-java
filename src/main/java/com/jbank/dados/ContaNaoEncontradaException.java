
package com.jbank.dados;

public class ContaNaoEncontradaException extends Exception {

    public ContaNaoEncontradaException() {
        super("Conta nao encontrada");
    }
}
