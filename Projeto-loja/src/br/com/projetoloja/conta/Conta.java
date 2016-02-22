package br.com.projetoloja.conta;

import br.com.projetoloja.produto.Produto;

import java.util.ArrayList;

public class Conta {

	private int id;
	private int idCliente;
	private ArrayList<Produto> historicoConta;
	private double valorParcela;
	private double valorTotal;
	private int quantidadeParcelas;
	private int parcelaAtual;

	public Conta(double valorParcela, int parcelaAtual){
		this.valorParcela = valorParcela;
		this.parcelaAtual = parcelaAtual;
	}
        public Conta(double valorParcela, double valorTotal, int quantidadeParcelas){
            this.valorParcela = valorParcela;
            this.valorTotal = valorTotal;
            this.quantidadeParcelas = quantidadeParcelas;
        }
        
	public Conta(int id,int idCliente, ArrayList<Produto> historicoConta,double valorParcela, double valorTotal,
					int quantidadeParcelas) {
		this.id = id;
		this.idCliente = idCliente;
		this.historicoConta = historicoConta;
		this.valorParcela = valorParcela;
		this.valorTotal = valorTotal;
		this.quantidadeParcelas = quantidadeParcelas;

	}

	public int getId() {
		return id;
	}

	public void setId(int idCliente) {
		this.idCliente = idCliente;
	}
        
        public int getIdCliente() {
	return idCliente;
	}

	public void setIdCliente(int id) {
		this.id = id;
	}

	public ArrayList<Produto> getHistoricoConta() {
		return historicoConta;
	}

	public void setHistoricoConta(ArrayList<Produto> historicoConta) {
		this.historicoConta = historicoConta;
	}

	public double getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(double valorParcela) {
		this.valorParcela = valorParcela;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getQuantidadeParcelas() {
		return quantidadeParcelas;
	}

	public void setQuantidadeParcelas(int quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}

	public int getParcelaAtual() {
		return parcelaAtual;
	}

	public void setParcelaAtual(int parcelaAtual) {
		this.parcelaAtual = parcelaAtual;
	}


    @Override
    public String toString() {
        return "Conta{" + "id=" + id + ", idCliente=" + idCliente + ", historicoConta=" + historicoConta + ", valorParcela=" + valorParcela + ", valorTotal=" + valorTotal + ", quantidadeParcelas=" + quantidadeParcelas + '}';
    }
        
        

}
