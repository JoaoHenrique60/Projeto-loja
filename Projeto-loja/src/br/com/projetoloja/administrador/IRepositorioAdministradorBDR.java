package br.com.projetoloja.administrador;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.projetoloja.execoes.CampoObritarorioException;
import br.com.projetoloja.execoes.JaCadastradoException;
import br.com.projetoloja.execoes.NaoEncontradoException;
import br.com.projetoloja.execoes.NaoFoiPossivelAlterarException;
import br.com.projetoloja.execoes.NaoFoiPossivelAtivarException;
import br.com.projetoloja.execoes.NaoFoiPossivelCadastrarException;
import br.com.projetoloja.execoes.NaoFoiPossivelDeletarException;
import br.com.projetoloja.execoes.NaoFoiPossivelInativarException;
import br.com.projetoloja.execoes.UsuarioOuSenhaErradaException;

public interface IRepositorioAdministradorBDR {

	public void cadastrar(Administrador adm) throws JaCadastradoException, CampoObritarorioException, NaoFoiPossivelCadastrarException,SQLException ,Exception;
	public ArrayList<Administrador>listar(String complemento) throws NaoEncontradoException,SQLException, Exception;
	public ArrayList<Administrador>listarTodos() throws NaoEncontradoException,SQLException,Exception;
	public ArrayList<Administrador>pesquisarPorNome(String nome)throws NaoEncontradoException,CampoObritarorioException,SQLException,Exception;
	public Administrador pesquisarPorId(int id)throws NaoEncontradoException,CampoObritarorioException,SQLException,Exception;
	public void remover(int id) throws NaoFoiPossivelDeletarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception;
	public void ativar(int id) throws NaoFoiPossivelAtivarException,NaoFoiPossivelInativarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception;
	public void alterar(Administrador adm) throws NaoFoiPossivelAlterarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception;
	public boolean existeId(Administrador adm)throws JaCadastradoException,SQLException, Exception;
	public Administrador login(String usuario, String senha)throws UsuarioOuSenhaErradaException, CampoObritarorioException,SQLException,Exception;
}
