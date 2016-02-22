package br.com.projetoloja.execoes;

public class ForaDeEstoqueException extends Exception{

	public ForaDeEstoqueException() {
		super("Infelizmente essa mercadoria acabou no estoque");
	}
}
