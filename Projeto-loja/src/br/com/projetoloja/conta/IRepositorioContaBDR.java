package br.com.projetoloja.conta;

import java.sql.SQLException;

import br.com.projetoloja.execoes.CampoObritarorioException;
import br.com.projetoloja.execoes.JaCadastradoException;
import br.com.projetoloja.execoes.NaoEncontradoException;
import br.com.projetoloja.execoes.NaoFoiPossivelCadastrarException;
import br.com.projetoloja.execoes.NaoFoiPossivelDeletarException;

public interface IRepositorioContaBDR {

	public void cadastrar(Conta c) throws JaCadastradoException, CampoObritarorioException, NaoFoiPossivelCadastrarException,SQLException ,Exception;
    public Conta historicoConta(int idCliente) throws NaoEncontradoException, CampoObritarorioException, SQLException, Exception;
	public void remover(int id) throws NaoFoiPossivelDeletarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception;
	public void compraConta(Conta c) throws Exception;
    public void gerarCaner(Conta c) throws Exception;
	public void pagamento(int idConta) throws Exception;
	public void pagamentoLivre(Conta conta) throws Exception;
	public boolean existeId(Conta c) throws JaCadastradoException,SQLException, Exception;
	public Conta recuperarConta(int idConta) throws JaCadastradoException, SQLException, Exception;
}
