package br.com.projetoloja.execoes;

public class CodigoErradoExcepiton extends Exception{

	public CodigoErradoExcepiton() {
		super("Esse codico possivelmente este errado ou "
				+ "esse cliente não esta cadastrado no sistema");
	}
}
