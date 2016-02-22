package br.com.projetoloja.cliente;

import br.com.projetoloja.conta.Conta;
import br.com.projetoloja.endereco.Endereco;
import br.com.projetoloja.pessoa.Pessoa;

public class Cliente extends Pessoa {

	private String cpf;
	private String telefone;
	private String celular;
	private Endereco endereco;
	private Conta conta;

	public Cliente(int id, String nome, String cpf, String telefone, String celular, Endereco endereco, Conta conta,char ativo) {
		super(id, nome, ativo);
		this.cpf = cpf;
		this.telefone = telefone;
		this.celular = celular;
		this.endereco = endereco;
		this.conta = conta;
	}
        
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	@Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", telefone=" + telefone + ", celular=" + celular + ", endereco=" + endereco
				+ ", conta=" + conta + "]";
	}

}
