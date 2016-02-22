package br.com.projetoloja.produto;

import br.com.projetoloja.execoes.CampoObritarorioException;
import br.com.projetoloja.execoes.JaCadastradoException;
import br.com.projetoloja.execoes.NaoEncontradoException;
import br.com.projetoloja.execoes.NaoFoiPossivelAlterarException;
import br.com.projetoloja.execoes.NaoFoiPossivelAtivarException;
import br.com.projetoloja.execoes.NaoFoiPossivelCadastrarException;
import br.com.projetoloja.execoes.NaoFoiPossivelInativarException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ControladorProduto {

    private IRepositorioProdutoBDR repositorio;

    public ControladorProduto() throws Exception{
        this.repositorio = new RepositorioProdutoBDR();
    }
    
    public void cadastrar(Produto p) throws JaCadastradoException, CampoObritarorioException, NaoFoiPossivelCadastrarException,SQLException ,Exception{
        System.out.println("controlador cadastrar ok");
        repositorio.cadastrar(p);
    }
    public ArrayList<Produto>listarTodos()throws NaoEncontradoException,SQLException, Exception{
        System.out.println("controlador listar todos ok");
        return  repositorio.listarTodos();
    }
    public ArrayList<Produto> pesquisarPorNome(String nome)throws NaoEncontradoException,CampoObritarorioException,SQLException,Exception{
        System.out.println("controlador pesquisar por nome ok");
        return repositorio.pesquisarPorNome(nome);
    }
    public Produto pesquisarPorId(int id)throws NaoEncontradoException,CampoObritarorioException,SQLException,Exception{
        System.out.println("controlador pesquisar por id ok");
        return repositorio.pesquisarPorId(id);
    }
    public void remover(int id) throws NaoFoiPossivelAtivarException,NaoFoiPossivelInativarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception{
        System.out.println("controlador remover ok");
        repositorio.remover(id);
    }
    public void ativar(int id) throws NaoFoiPossivelAtivarException,NaoFoiPossivelInativarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception{
        System.out.println("controlador ativar ok");
        repositorio.ativar(id);
    }
    public void alterar(Produto p)throws NaoFoiPossivelAlterarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception{
        repositorio.alterar(p);
    }
    public void fazerCompra(Produto p) throws NaoFoiPossivelAlterarException, CampoObritarorioException, NaoEncontradoException, SQLException, Exception {
        repositorio.fazerCompra(p);
    }

    }
