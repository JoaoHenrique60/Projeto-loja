package br.com.projetoloja.produto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Produto {

	private int id;
	private String nome;
	private int quantidade;
    private int quantidadeMinima;
	private double valorVenda;
	private double valorCompra;
	private double valorTotal;
	private String data;
	private char ativo;

	public Produto(int id, String nome, int quantidade, double valorVenda,char ativo,double valorTotal) {
		this.id = id;
		this.nome = nome;
		this.quantidade = quantidade;
		this.valorVenda = valorVenda;
		this.ativo = ativo;
		this.valorTotal = valorTotal;
	}

	public Produto(int id, String nome, int quantidade,int quantidadeMinima, double valorVenda, double valorCompra,
			char ativo) {
		this.id = id;
		this.nome = nome;
		this.quantidade = quantidade;
        this.quantidadeMinima = quantidadeMinima;
		this.valorVenda = valorVenda;
		this.valorCompra = valorCompra;
		this.ativo = ativo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
        
	public int getQuantidadeMinima() {
		return quantidadeMinima;
	}

	public void setQuantidadeMinima(int quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(double precoDeVenda) {
		this.valorVenda = precoDeVenda;
	}

	public double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(double precoDeCompra) {
		this.valorCompra = precoDeCompra;
	}

	public double getValorTotal(){
		return valorTotal;
	}
	public void setValorTotal(double valorTotal){
		this.valorTotal = valorTotal;
	}

	public String getData(){
		return data;
	}

	public void setData(String data){
		this.data = data;
	}

	public String data() {
		DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
		Date data = new Date();
		return dateFormat.format(data);
	}

	public char getAtivo() {
		return ativo;
	}

	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", nome=" + nome + ", quantidade=" + quantidade + 
               ", quantidadeMinima=" + quantidadeMinima + ", valorVenda=" + valorVenda +
               ", valorCompra=" + valorCompra + ", ativo=" + ativo + '}';
    }
}
