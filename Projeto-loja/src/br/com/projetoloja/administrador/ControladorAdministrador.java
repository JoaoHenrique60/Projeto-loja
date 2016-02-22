package br.com.projetoloja.administrador;

import br.com.projetoloja.execoes.CampoObritarorioException;
import br.com.projetoloja.execoes.JaCadastradoException;
import br.com.projetoloja.execoes.NaoEncontradoException;
import br.com.projetoloja.execoes.NaoFoiPossivelAlterarException;
import br.com.projetoloja.execoes.NaoFoiPossivelAtivarException;
import br.com.projetoloja.execoes.NaoFoiPossivelCadastrarException;
import br.com.projetoloja.execoes.NaoFoiPossivelDeletarException;
import br.com.projetoloja.execoes.NaoFoiPossivelInativarException;
import br.com.projetoloja.execoes.UsuarioOuSenhaErradaException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControladorAdministrador {

  private IRepositorioAdministradorBDR repositorio;

    public ControladorAdministrador() throws Exception{
        this.repositorio = new RepositorioAdministradorBDR();
    }
  
    public void cadastrar(Administrador adm) throws JaCadastradoException, CampoObritarorioException, NaoFoiPossivelCadastrarException,SQLException ,Exception{
        System.out.println("controlador cadastrar ok");
        repositorio.cadastrar(adm);
    }

    public ArrayList<Administrador>listarTodos() throws NaoEncontradoException,SQLException,Exception{
        System.out.println("controlador listar todos ok");
        return repositorio.listarTodos();
    }
    public ArrayList<Administrador>pesquisarPorNome(String nome)throws NaoEncontradoException,CampoObritarorioException,SQLException,Exception{
        System.out.println("controlador pesquisar por nome ok");
        return repositorio.pesquisarPorNome(nome);
    }
    public Administrador pesquisarPorId(int id)throws NaoEncontradoException,CampoObritarorioException,SQLException,Exception{
        System.out.println("controlador pesquisar por id ok");
        return repositorio.pesquisarPorId(id);
    }
    public void remover(int id) throws NaoFoiPossivelDeletarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception{
        System.out.println("controlador remover ok");
        repositorio.remover(id);
    }
    public void ativar(int id) throws NaoFoiPossivelAtivarException,NaoFoiPossivelInativarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception{
        System.out.println("controlador ativar ok");
        repositorio.ativar(id);
    }
    public void alterar(Administrador adm) throws NaoFoiPossivelAlterarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception{
        System.out.println("controlador alterar ok");
        repositorio.alterar(adm);
    }
    
    public Administrador login(String usuario, String senha)throws UsuarioOuSenhaErradaException, CampoObritarorioException,SQLException,Exception{
        System.out.println("controlador login ok");
        return repositorio.login(usuario, senha);
    }
  
    
}