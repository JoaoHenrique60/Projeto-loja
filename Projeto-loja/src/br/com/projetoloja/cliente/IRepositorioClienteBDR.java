package br.com.projetoloja.cliente;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.projetoloja.execoes.CPFInvalidoException;
import br.com.projetoloja.execoes.CampoObritarorioException;
import br.com.projetoloja.execoes.FoneInvalidoException;
import br.com.projetoloja.execoes.JaCadastradoException;
import br.com.projetoloja.execoes.NaoEncontradoException;
import br.com.projetoloja.execoes.NaoFoiPossivelAlterarException;
import br.com.projetoloja.execoes.NaoFoiPossivelAtivarException;
import br.com.projetoloja.execoes.NaoFoiPossivelCadastrarException;
import br.com.projetoloja.execoes.NaoFoiPossivelDeletarException;
import br.com.projetoloja.execoes.NaoFoiPossivelInativarException;

public interface IRepositorioClienteBDR {
	
	public void cadastrar(Cliente c)throws JaCadastradoException, CampoObritarorioException,CPFInvalidoException, FoneInvalidoException, NaoFoiPossivelCadastrarException,SQLException ,Exception;
	public ArrayList<Cliente>listar(String complemento) throws NaoEncontradoException,SQLException, Exception ;
	public ArrayList<Cliente>listarTodos()throws NaoEncontradoException,SQLException,Exception;
	public ArrayList<Cliente>pesquisarPorNome(String nome)throws NaoEncontradoException,CampoObritarorioException,SQLException,Exception;
	public Cliente pesquisarPorId(int id)throws NaoEncontradoException,CampoObritarorioException,SQLException,Exception;
	public Cliente pesquisarPorCpf(String cpf)throws NaoEncontradoException,CPFInvalidoException,CampoObritarorioException,SQLException,Exception;
	public void remover(int id) throws NaoFoiPossivelDeletarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception;
	public void ativar(int id) throws NaoFoiPossivelAtivarException,NaoFoiPossivelInativarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception;
	public void alterar(Cliente c) throws NaoFoiPossivelAlterarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception;
	public boolean existeId(Cliente c)throws JaCadastradoException,SQLException, Exception;

}
