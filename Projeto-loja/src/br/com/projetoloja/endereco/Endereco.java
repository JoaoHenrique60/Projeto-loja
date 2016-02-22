package br.com.projetoloja.endereco;

public class Endereco {

        private int cep;
	private String cidade;
	private String bairro;
	private String rua;
	private int numero;
        private String complemento;

	public Endereco(int cep, String cidade, String bairro, String rua, int numero, String complemento){
                this.cep = cep;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
                this.complemento = complemento; 
	}

        public int getCep() {
        return cep;
        }

        public void setCep(int cep) {
        this.cep = cep;
        }
                
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

        public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

    @Override
    public String toString() {
        return "Endereco{" + "cep=" + cep + ", cidade=" + cidade + ", bairro=" + bairro + ", rua=" + rua + ", numero=" + numero + ", complemento=" + complemento + '}';
    }
}
