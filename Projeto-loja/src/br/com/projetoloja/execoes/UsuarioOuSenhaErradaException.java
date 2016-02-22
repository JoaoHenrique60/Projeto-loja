package br.com.projetoloja.execoes;

public class UsuarioOuSenhaErradaException extends Exception{

	public UsuarioOuSenhaErradaException(String usuario,int senha){
		super("O seu usuario ou a sua senha esta incorreta");
	}
}
