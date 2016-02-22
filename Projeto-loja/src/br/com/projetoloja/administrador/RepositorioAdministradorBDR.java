package br.com.projetoloja.administrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.projetoloja.conexao.Conexao;
import br.com.projetoloja.conexao.Database;
import br.com.projetoloja.execoes.CampoObritarorioException;
import br.com.projetoloja.execoes.JaCadastradoException;
import br.com.projetoloja.execoes.NaoCadastradoException;
import br.com.projetoloja.execoes.NaoEncontradoException;
import br.com.projetoloja.execoes.NaoFoiPossivelAlterarException;
import br.com.projetoloja.execoes.NaoFoiPossivelAtivarException;
import br.com.projetoloja.execoes.NaoFoiPossivelCadastrarException;
import br.com.projetoloja.execoes.NaoFoiPossivelDeletarException;
import br.com.projetoloja.execoes.NaoFoiPossivelInativarException;
import br.com.projetoloja.execoes.UsuarioOuSenhaErradaException;

public class RepositorioAdministradorBDR implements IRepositorioAdministradorBDR {

	private static RepositorioAdministradorBDR instance;
	private static final String NOME_TABELA = "administrador";
	private Connection connection;
	private int dataBase = Database.MYSQL;

	public static RepositorioAdministradorBDR getInstance() throws Exception {
		if (instance == null) {
			instance = new RepositorioAdministradorBDR();
		}
		return instance;
	}

	public RepositorioAdministradorBDR() throws Exception {
		this.connection = Conexao.getConnection(dataBase);
	}

	@Override
	public void cadastrar(Administrador adm) throws JaCadastradoException, CampoObritarorioException,
			NaoFoiPossivelCadastrarException, SQLException, Exception {
		System.out.println("repositrio cadastrar ok");
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";

		if (existeId(adm) == false) {
			sql = "insert into " + NOME_TABELA + " (nome, usuario, senha, ativo) values (?,?,?,?);";

			if (this.dataBase == Database.ORACLE) {
				ps = this.connection.prepareStatement(sql, new String[] { "id" });
			} else {
				ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
                        System.out.println("passou aqui");
			ps.setString(1, adm.getNome());
			ps.setString(2, adm.getUsuario());
			ps.setString(3, adm.getSenha());
			ps.setString(4, String.valueOf(adm.getAtivo()));
			ps.execute();
			rs = ps.getGeneratedKeys();
			int id = 0;
			if (rs != null) {
				while (rs.next()) {
					id = rs.getInt(1);
				}
				adm.setId(id);
				System.out.println(adm.toString());
				System.out.println("concluido");
			} else {
				throw new NaoFoiPossivelCadastrarException(
						"Ops possivel erro no sistema...\ntentar novamente caso o erro persista contatar o resposavel pelo sistema. ");
			}
		}else{
			throw new JaCadastradoException(adm.getNome() + " ja esta cadastrado...\n tente novamente com outro administrador.");
		}
	}

	@Override
	public ArrayList<Administrador> listar(String complemento) throws NaoEncontradoException, SQLException, Exception {
		System.out.println("repositorio listar ok");
		ArrayList<Administrador> lista = new ArrayList<Administrador>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		
		sql = "select *from " + NOME_TABELA + " ";
		sql += "where id is not null  ";
		sql += complemento;
		sql += " order by nome;";
		ps = this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
		if(rs !=null){
			while(rs.next()){
				Administrador adm = new Administrador(rs.getInt("id"), rs.getString("nome"), rs.getString("senha"),
						rs.getString("usuario"), rs.getString("ativo").charAt(0));
				lista.add(adm);
			}
			System.out.println("consulta completada com sucesso...");
		}else{
			throw new NaoEncontradoException("Administrador nao encontrado");
		}
		ps.close();
		rs.close();
		return lista;
	}

	@Override
	public ArrayList<Administrador> listarTodos() throws NaoEncontradoException, SQLException, Exception {
		return listar("");
	}

	@Override
	public ArrayList<Administrador> pesquisarPorNome(String nome)
			throws NaoEncontradoException, CampoObritarorioException, SQLException, Exception {
		return listar("AND nome LIKE '%" + nome + "%'");
	}

	@Override
	public Administrador pesquisarPorId(int id)
			throws NaoEncontradoException, CampoObritarorioException, SQLException, Exception {
		return listar("AND id=" + id).get(0);
	}

	@Override
	public void remover(int id) throws NaoFoiPossivelAlterarException, CampoObritarorioException,
			NaoEncontradoException, SQLException, Exception {
            		System.out.println("Chegando ao repositorio");
                
		Administrador administrador = new Administrador(id, "", "", "",'N');
		if (existeId(administrador) == false){
			PreparedStatement ps = null;
			String sql = "";
			sql = "UPDATE " + NOME_TABELA + " SET ativo=? WHERE id=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, String.valueOf(administrador.getAtivo()));
			ps.setInt(2, administrador.getId());
			Integer resultado = ps.executeUpdate();
			if (resultado == 0) throw new NaoFoiPossivelAlterarException("Não foi possivel fazer alteracao");
			ps.close();
			System.out.println("- removido com sucesso -");
		}else{
			throw new NaoCadastradoException("Este administrador nao esta cadastrado no sistema");
		}
	}

	@Override
	public void ativar(int id) throws NaoFoiPossivelAtivarException, NaoFoiPossivelInativarException,
			CampoObritarorioException, NaoEncontradoException, SQLException, Exception {
            		System.out.println("Chegando ao repositorio");
                
		Administrador administrador = new Administrador(id, "", "", "",'A');
		if (existeId(administrador) == false){
			PreparedStatement ps = null;
			String sql = "";
			sql = "UPDATE " + NOME_TABELA + " SET ativo=? WHERE id=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, String.valueOf(administrador.getAtivo()));
			ps.setInt(2, administrador.getId());
			Integer resultado = ps.executeUpdate();
			if (resultado == 0) throw new NaoFoiPossivelAlterarException("");
			ps.close();
			System.out.println("- Ativado com sucesso -");
		}else{
			throw new NaoCadastradoException("");
		}
	}

	@Override
	public void alterar(Administrador adm) throws NaoFoiPossivelDeletarException, CampoObritarorioException,
			NaoEncontradoException, SQLException, Exception {
            System.out.println("repositorio alterar ok");
            if (existeId(adm) == false){
                PreparedStatement ps = null;
		String sql = "";
                sql = "UPDATE " + NOME_TABELA + " SET nome=?, usuario=?, senha=? WHERE id=?;";
		ps = this.connection.prepareStatement(sql);
		ps.setString(1, adm.getNome());
                ps.setString(2, adm.getUsuario());
                ps.setString(3, adm.getSenha());
		ps.setInt(4, adm.getId());
		Integer resultado = ps.executeUpdate();
		if (resultado == 0) throw new NaoFoiPossivelAlterarException("Infelizmente nao foi possivel alterar o registro.");
		ps.close();
		System.out.println("- consulta completada com sucesso -");
			
		}else{
			throw new NaoCadastradoException("Este administrado nao esta cadastrado em nosso banco de dados");
		}
	}

	@Override
	public boolean existeId(Administrador adm) throws JaCadastradoException, SQLException, Exception {
	System.out.println("Chegando ao repositorio");
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + NOME_TABELA + " WHERE id=?";
		boolean resposta = true;		
		ps = connection.prepareStatement(sql);
		ps.setInt(1, adm.getId());
		rs = ps.executeQuery();
		if(rs != null){
			resposta = false;
		}
		ps.close();
		rs.close();
		System.out.println("- consulta completada com sucesso -");
		return resposta;
	}

	@Override
	public Administrador login(String usuario, String senha)
			throws UsuarioOuSenhaErradaException, CampoObritarorioException, SQLException, Exception {
		ArrayList<Administrador> lista = listarTodos();
		Administrador adm = null;
		for (Administrador administrador : lista) {
			if(administrador.getUsuario().equals(usuario) && administrador.getSenha().equals(senha)){
				adm = administrador;
			}
		}
		return adm;
	}

}