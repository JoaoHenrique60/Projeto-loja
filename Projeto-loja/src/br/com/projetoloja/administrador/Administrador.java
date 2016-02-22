package br.com.projetoloja.administrador;

import br.com.projetoloja.pessoa.Pessoa;

public class Administrador extends Pessoa {

	private String senha;
	private String usuario;

	public Administrador(int id, String nome, String senha, String usuario,char ativo) {
		super(id, nome, ativo);
		this.senha = senha;
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Administrador [senha=" + senha + ", usuario=" + usuario + "]";
	}

}
