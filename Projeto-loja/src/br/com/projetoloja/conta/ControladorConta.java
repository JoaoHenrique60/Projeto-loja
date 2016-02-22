package br.com.projetoloja.conta;

import br.com.projetoloja.execoes.CampoObritarorioException;
import br.com.projetoloja.execoes.JaCadastradoException;
import br.com.projetoloja.execoes.NaoEncontradoException;
import br.com.projetoloja.execoes.NaoFoiPossivelCadastrarException;
import br.com.projetoloja.execoes.NaoFoiPossivelDeletarException;
import java.sql.SQLException;

public class ControladorConta {
    
    private IRepositorioContaBDR repositorio;

    public ControladorConta() throws Exception{
        this.repositorio =  new RepositorioContaBDR();
    }
    
    public void cadastrar(Conta c) throws JaCadastradoException, CampoObritarorioException, NaoFoiPossivelCadastrarException,SQLException ,Exception{
            repositorio.cadastrar(c);
    }
    
    public Conta historicoConta(int idCliente) throws NaoEncontradoException, CampoObritarorioException, SQLException, Exception{
    return repositorio.historicoConta(idCliente);
    }
    public Conta recuperarConta(int idConta) throws NaoEncontradoException, CampoObritarorioException, SQLException, Exception{
        return repositorio.recuperarConta(idConta);
    }
    public void remover(int id) throws NaoFoiPossivelDeletarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception{
        repositorio.remover(id);
    }
    
    public void gerarCaner(Conta c) throws Exception {
    	repositorio.gerarCaner(c);
    }
    public void compraConta(Conta c) throws Exception {
    	repositorio.compraConta(c);
    }
    public void pagamento(int idConta) throws Exception {
        repositorio.pagamento(idConta);
    }
    public void pagamentoLivre(Conta conta) throws Exception {
        repositorio.pagamentoLivre(conta);
    }
}
