package br.com.projetoloja.produto;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.projetoloja.execoes.CampoObritarorioException;
import br.com.projetoloja.execoes.JaCadastradoException;
import br.com.projetoloja.execoes.NaoEncontradoException;
import br.com.projetoloja.execoes.NaoFoiPossivelAlterarException;
import br.com.projetoloja.execoes.NaoFoiPossivelAtivarException;
import br.com.projetoloja.execoes.NaoFoiPossivelCadastrarException;
import br.com.projetoloja.execoes.NaoFoiPossivelInativarException;

public interface IRepositorioProdutoBDR {

	public void cadastrar(Produto p) throws JaCadastradoException, CampoObritarorioException, NaoFoiPossivelCadastrarException,SQLException ,Exception;
	public ArrayList<Produto>listar(String complemento)throws NaoEncontradoException,SQLException, Exception;
	public ArrayList<Produto>listarTodos()throws NaoEncontradoException,SQLException, Exception;
	public ArrayList<Produto> pesquisarPorNome(String nome)throws NaoEncontradoException,CampoObritarorioException,SQLException,Exception;
	public Produto pesquisarPorId(int id)throws NaoEncontradoException,CampoObritarorioException,SQLException,Exception;
    public void remover(int id) throws NaoFoiPossivelAtivarException,NaoFoiPossivelInativarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception;
	public void ativar(int id) throws NaoFoiPossivelAtivarException,NaoFoiPossivelInativarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception;
	public void alterar(Produto p)throws NaoFoiPossivelAlterarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception;
	public void fazerCompra(Produto p) throws NaoFoiPossivelAlterarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception;
	public boolean existeId(Produto p)throws JaCadastradoException,SQLException, Exception;

}
