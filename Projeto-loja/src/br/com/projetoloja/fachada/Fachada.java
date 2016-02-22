package br.com.projetoloja.fachada;

import br.com.projetoloja.administrador.Administrador;
import br.com.projetoloja.administrador.ControladorAdministrador;
import br.com.projetoloja.cliente.Cliente;
import br.com.projetoloja.cliente.ControladorCliente;
import br.com.projetoloja.conta.Conta;
import br.com.projetoloja.conta.ControladorConta;
import br.com.projetoloja.conta.RepositorioContaBDR;
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
import br.com.projetoloja.execoes.UsuarioOuSenhaErradaException;
import br.com.projetoloja.produto.ControladorProduto;
import br.com.projetoloja.produto.Produto;
import java.sql.SQLException;
import java.util.ArrayList;

public class Fachada {
    
private static Fachada instance;
    
private ControladorAdministrador controladorAdm;
private ControladorCliente controladorCliente;
private ControladorConta controladorConta;
private ControladorProduto controladorProduto;

public Fachada() throws Exception{
    this.controladorAdm = new ControladorAdministrador();
    this.controladorCliente = new ControladorCliente();
    this.controladorConta = new ControladorConta();
    this.controladorProduto = new ControladorProduto();
}

public static Fachada getInstance() throws Exception {
    if (Fachada.instance == null) {
    Fachada.instance = new Fachada();
}
    return Fachada.instance;
}
        
        
//metodos adm
 public void administradorCadastrar(Administrador adm) throws JaCadastradoException, CampoObritarorioException, NaoFoiPossivelCadastrarException,SQLException ,Exception{
        System.out.println("fachada cadastrar ok");
        controladorAdm.cadastrar(adm);
    }

    public ArrayList<Administrador> administradorListarTodos() throws NaoEncontradoException,SQLException,Exception{
        System.out.println("Fachada listar todos ok");
        return controladorAdm.listarTodos();
    }
    public ArrayList<Administrador>administradorPesquisarPorNome(String nome)throws NaoEncontradoException,CampoObritarorioException,SQLException,Exception{
        System.out.println("Fachada pesquisar por nome ok");
        return controladorAdm.pesquisarPorNome(nome);
    }
    public Administrador administradorPesquisarPorId(int id)throws NaoEncontradoException,CampoObritarorioException,SQLException,Exception{
        System.out.println("Fachada pesquisar por id ok");
        return controladorAdm.pesquisarPorId(id);
    }
    public void administradorRemover(int id) throws NaoFoiPossivelDeletarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception{
        System.out.println("Fachada remover ok");
        controladorAdm.remover(id);
    }
    public void administradorAtivar(int id) throws NaoFoiPossivelAtivarException,NaoFoiPossivelInativarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception{
        System.out.println("Fachada ativar ok");
        controladorAdm.ativar(id);
    }
    public void administradorAlterar(Administrador adm) throws NaoFoiPossivelAlterarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception{
        System.out.println("Fachada alterar ok");
        controladorAdm.alterar(adm);
    }
    
    public Administrador administradorLogin(String usuario, String senha)throws UsuarioOuSenhaErradaException, CampoObritarorioException,SQLException,Exception{
        System.out.println("Fachada login ok");
        return controladorAdm.login(usuario, senha);
    }
    
    //metodos Cliente
    public void clienteCadastrar(Cliente c)throws JaCadastradoException, CampoObritarorioException,CPFInvalidoException, FoneInvalidoException, NaoFoiPossivelCadastrarException,SQLException ,Exception{
        System.out.println("Fachda cadastrar ok");
        controladorCliente.cadastrar(c);
        controladorConta.cadastrar(c.getConta());
    }
    public ArrayList<Cliente> clienteListarTodos()throws NaoEncontradoException,SQLException,Exception{
        System.out.println("Fachada listrar todos ok");
        return controladorCliente.listarTodos();
    }
    public ArrayList<Cliente> clientePesquisarPorNome(String nome)throws NaoEncontradoException,CampoObritarorioException,SQLException,Exception{
        System.out.println("Fachada pesquisar por nome ok");
        return controladorCliente.pesquisarPorNome(nome);
    }
    public Cliente clientePesquisarPorId(int id)throws NaoEncontradoException,CampoObritarorioException,SQLException,Exception{
        System.out.println("Fachada pesquisar por id ok");
        return controladorCliente.pesquisarPorId(id);
    }
    public Cliente clientePesquisarPorCpf(String cpf)throws NaoEncontradoException,CPFInvalidoException,CampoObritarorioException,SQLException,Exception{
        System.out.println("Fachada pesquisar por cpf ok");
        return controladorCliente.pesquisarPorCpf(cpf);
    }
    public void clienteRemover(int id) throws NaoFoiPossivelDeletarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception{
        System.out.println("Fachada remover ok");
        controladorCliente.remover(id);
    }
    public void clienteAtivar(int id) throws NaoFoiPossivelAtivarException,NaoFoiPossivelInativarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception{
        System.out.println("Fachada ativar ok");
        controladorCliente.ativar(id);
    }
    public void clienteAlterar(Cliente c) throws NaoFoiPossivelAlterarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception{
        System.out.println("Fachada alterar ok");
        controladorCliente.alterar(c);
    }
    
    //metodos produto
    public void produtoCadastrar(Produto p) throws JaCadastradoException, CampoObritarorioException, NaoFoiPossivelCadastrarException,SQLException ,Exception{
        System.out.println("Fachada cadastrar ok");
        controladorProduto.cadastrar(p);
    }
    public ArrayList<Produto> produtoListarTodos()throws NaoEncontradoException,SQLException, Exception{
        System.out.println("Fachada listar todos ok");
        return  controladorProduto.listarTodos();
    }
    public ArrayList<Produto> produtoPesquisarPorNome(String nome)throws NaoEncontradoException,CampoObritarorioException,SQLException,Exception{
        System.out.println("Fachada pesquisar por nome ok");
        return controladorProduto.pesquisarPorNome(nome);
    }
    public Produto produtoPesquisarPorId(int id)throws NaoEncontradoException,CampoObritarorioException,SQLException,Exception{
        System.out.println("Fachada pesquisar por id ok");
        return controladorProduto.pesquisarPorId(id);
    }
    public void produtoRemover(int id) throws NaoFoiPossivelAtivarException,NaoFoiPossivelInativarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception{
        System.out.println("Fachada remover ok");
        controladorProduto.remover(id);
    }
    public void produtoAtivar(int id) throws NaoFoiPossivelAtivarException,NaoFoiPossivelInativarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception{
        System.out.println("Fachada ativar ok");
        controladorProduto.ativar(id);
    }
    public void produtoAlterar(Produto p)throws NaoFoiPossivelAlterarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception{
        System.out.println("Fachada alterar ok");
        controladorProduto.alterar(p);
    }

    public void produtoFazerCompra(Produto p) throws NaoFoiPossivelAlterarException, CampoObritarorioException, NaoEncontradoException, SQLException, Exception {
        controladorProduto.fazerCompra(p);
    }

    //metodos
    public void contaCadastrar(Conta c) throws JaCadastradoException, CampoObritarorioException, NaoFoiPossivelCadastrarException,SQLException ,Exception{
    System.out.println("Fachada cadastrar ok");
    controladorConta.cadastrar(c);
    }
    
    public Conta historiorConta(int idCliente) throws NaoEncontradoException, CampoObritarorioException, SQLException, Exception{
        System.out.println("Fachada recuperar conta ok");
        return controladorConta.historicoConta(idCliente);
    }

    public Conta recuperarConta(int idConta) throws NaoEncontradoException, CampoObritarorioException, SQLException, Exception{
        return controladorConta.recuperarConta(idConta);
    }
    public void contaRemover(int id) throws NaoFoiPossivelDeletarException, CampoObritarorioException, NaoEncontradoException,SQLException, Exception{
        System.out.println("Fachada remover ok");
        controladorConta.remover(id);
    }
    
    public void gerarCaner(Conta c) throws Exception {
    	controladorConta.gerarCaner(c);
    }
    public void compraConta(Conta c) throws Exception {
    	controladorConta.compraConta(c);
    }
    public void pagamento(int idConta) throws Exception {
        System.out.println("Fachada pagamento ok");
        controladorConta.pagamento(idConta);
    }
    public void pagamentoLivre(Conta conta) throws Exception {
        controladorConta.pagamentoLivre(conta);
    }
}
