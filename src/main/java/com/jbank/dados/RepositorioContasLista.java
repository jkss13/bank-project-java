
package com.jbank.dados;

import com.jbank.banco.ContaAbstrata;

public class RepositorioContasLista implements RepositorioContas {

    private ContaAbstrata conta;
    private RepositorioContasLista prox;

    public RepositorioContasLista() {
        this.conta = null;
        this.prox = null;
    }

    public void inserir(ContaAbstrata conta) {
        if (this.conta == null) {
            this.conta = conta;
            this.prox = new RepositorioContasLista();
        } else {
            this.prox.inserir(conta);
        }
    }

    public ContaAbstrata procurar(String numero)
            throws ContaNaoEncontradaException {
        ContaAbstrata resposta = null;
        if (this.conta != null) {
            if (this.conta.equals(numero)) {
                resposta = this.conta;
            } else {
                resposta = this.prox.procurar(numero);
            }
        } else {
            throw new ContaNaoEncontradaException();
        }
        return resposta;
    }

    public void remover(String numero) throws ContaNaoEncontradaException {
        if (this.conta != null) {
            if (this.conta.equals(numero)) {
                this.conta = this.prox.conta;
                this.prox = this.prox.prox;
            } else {
                this.prox.remover(numero);
            }
        } else {
            throw new ContaNaoEncontradaException();
        }
    }

    public void atualizar(ContaAbstrata conta)
            throws ContaNaoEncontradaException {
        if (this.conta != null) {
            if (this.conta.equals(conta.getNumero())) {
                this.conta = conta;
            } else {
                this.prox.atualizar(conta);
            }
        } else {
            throw new ContaNaoEncontradaException();
        }
    }

    public boolean existe(String numero) {
        boolean resposta = false;
        if (this.conta != null) {
            if (this.conta.equals(numero)) {
                resposta = true;
            } else {
                resposta = this.prox.existe(numero);
            }
        } else {
            resposta = false;
        }
        return resposta;
    }

}
