package br.com.projetoloja.execoes;

public class CPFInvalidoException extends Exception {
	private String cpf;
	
	public CPFInvalidoException(String cpf) {
		super("CPF '" + cpf + "' Nulo ou Inv�lido");
		this.cpf = cpf;
	}
}
