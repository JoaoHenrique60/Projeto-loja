package br.com.projetoloja.cliente;

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
import java.sql.SQLException;
import java.util.ArrayList;

public class ControladorCliente {

    private IRepositorioClienteBDR repositorio;

    public ControladorCliente() throws Exception{
        this.repositorio = new RepositorioClienteBDR();
    }
    
    public void cadastrar(Cliente c)throws JaCadastradoException, CampoObritarorioException,CPFInvalidoException, FoneInvalidoException, NaoFoiPossivelCadastrarException,SQLException ,Exception{
        System.out.println("controlador cadastrar ok");
        repositorio.cadastrar(c);
    }
    public ArrayList<Cliente>listarTodos()throws NaoEncontradoException,SQLException,Exception{
        System.out.println("controlador listrar todos ok");
        return repositorio.listarTodos();
    }
    public ArrayList<Cliente>pesquisarPorNome(String nome)throws NaoEncontradoException,CampoObritarorioException,SQLException,Exception{
        System.out.println("controlador pesquisar por nome ok");
        return repositorio.pesquisarPorNome(nome);
    }
    public Cliente pesquisarPorId(int id)throws NaoEncontradoException,CampoObritarorioException,SQLException,Exception{
        System.out.println("controlador pesquisar por id ok");
        return repositorio.pesquisarPorId(id);
    }
    public Cliente pesquisarPorCpf(String cpf)throws NaoEncontradoException,CPFInvalidoException,CampoObritarorioException,SQLException,Exception{
        System.out.println("controlador pesquisar por cpf ok");
        return repositorio.pesquisarPorCpf(cpf);
    }
    public void remover(int id) throws NaoFoiPossivelDeletarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception{
        System.out.println("controlador remover ok");
        repositorio.remover(id);
    }
    public void ativar(int id) throws NaoFoiPossivelAtivarException,NaoFoiPossivelInativarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception{
        System.out.println("controlador ativar ok");
        repositorio.ativar(id);
    }
    public void alterar(Cliente c) throws NaoFoiPossivelAlterarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception{
        System.out.println("controlador alterar ok");
        repositorio.alterar(c);
    }    
}
