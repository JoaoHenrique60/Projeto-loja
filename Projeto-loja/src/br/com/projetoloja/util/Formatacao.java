package br.com.projetoloja.util;

public class Formatacao {
	
	public static String cpf(String cpf) {
		return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
	}
	
	public static String dataHoraTela(String dataHora) {
		return dataHora.substring(8,10) + "/" + dataHora.substring(5,7) + "/" + dataHora.substring(0,4) + " " + dataHora.substring(11,19);
	}

}
