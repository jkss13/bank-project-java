package com.jbank.dados;

import java.io.*;
import java.util.Scanner;

import com.jbank.banco.Conta;
import com.jbank.banco.ContaAbstrata;
import com.jbank.banco.ContaEspecial;
import com.jbank.banco.ContaImposto;
import com.jbank.banco.Poupanca;

import iterator.IteratorContaAbstrata;

public class RepositorioContasArquivoTxt implements RepositorioContas {
	private RepositorioContasArray contas;
	
	private final String ARQUIVO = "C:\\BancoDoBanco\\Banco.txt";
	private File fBanco;
	
	public RepositorioContasArquivoTxt() throws RepositorioException{
		try {
			contas = new RepositorioContasArray();
			fBanco = new File(this.ARQUIVO);
			fBanco.createNewFile();
			this.lerArquivo();
		}catch(IOException e) {
			throw new RepositorioException(e);
		}
	}
	
	private void lerArquivo() throws RepositorioException {
		Scanner inBanco = null;
		ContaAbstrata conta = null;
		int tipoConta;
		String numero;
		double saldo;
		double bonus;
		try {
		    inBanco = new Scanner(fBanco);
			while(inBanco.hasNext()){
				tipoConta = inBanco.nextInt();
				numero = inBanco.next();
				saldo = Double.parseDouble(inBanco.next());
				switch(tipoConta) {
					case 0:	
						conta = new Conta(numero,saldo); // uma Conta
						break;
					case 1: 
						conta = new Poupanca(numero,saldo); // uma Poupana
						break;
					case 2: 
						conta = new ContaImposto(numero,saldo); // uma ContaImposto
						break;
					case 3: 
						bonus = Double.parseDouble(inBanco.next());
						conta = new ContaEspecial(numero,saldo,bonus); // uma ContaEspecial
						break;
					default:
						throw new RepositorioException(new Exception("Erro interno"));
				}
				this.contas.inserir(conta);
			}
		} catch (FileNotFoundException e) {
			throw new RepositorioException(e);
		} finally {
			inBanco.close();
		}
	}
	
	private void gravarArquivo() throws RepositorioException {
		BufferedWriter bwBanco = null;
		FileWriter fwBanco = null;
		try {
			fwBanco = new FileWriter(fBanco);
			bwBanco = new BufferedWriter(fwBanco);
			IteratorContaAbstrata it = contas.getIterator();
			while(it.hasNext()) {
				ContaAbstrata c = it.next();
				System.out.println(c);
				if(c instanceof Poupanca) {
					bwBanco.write("1 "+c.getNumero()+" "+c.getSaldo());
				} else if(c instanceof ContaImposto) {
					bwBanco.write("2 "+c.getNumero()+" "+c.getSaldo());
				} else if(c instanceof ContaEspecial) {
					bwBanco.write("3 "+c.getNumero()+" "+c.getSaldo()+" "+((ContaEspecial) c).getBonus());
				} else if(c instanceof Conta) {	
					bwBanco.write("0 "+c.getNumero()+" "+c.getSaldo());
				} else {
					throw new RepositorioException(new Exception("Erro interno!"));
				}
				bwBanco.newLine();
			} 
		} catch (IOException e) {
			throw new RepositorioException(e);
		} finally {
			try {
				bwBanco.close();
				fwBanco.close();
			} catch (IOException e) {
				throw new RepositorioException(e);
			}
		}
	}

	public void inserir(ContaAbstrata conta) throws RepositorioException{
		contas.inserir(conta);
		this.gravarArquivo();
	}

	public ContaAbstrata procurar(String numero) throws ContaNaoEncontradaException {
		return contas.procurar(numero);
	}

	public void remover(String numero) throws RepositorioException, ContaNaoEncontradaException{
		contas.remover(numero);
		this.gravarArquivo();
	}

	public void atualizar(ContaAbstrata conta) throws RepositorioException, ContaNaoEncontradaException{
		contas.atualizar(conta);
		this.gravarArquivo();
	}

	public boolean existe(String numero) {
		return contas.existe(numero);
	}

	public IteratorContaAbstrata getIterator() throws RepositorioException {
		return contas.getIterator();
	}
}
