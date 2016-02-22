package br.com.projetoloja.produto;

import java.sql.Connection;
import java.sql.SQLException;
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
import br.com.projetoloja.execoes.NaoFoiPossivelInativarException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RepositorioProdutoBDR implements IRepositorioProdutoBDR {

	private static RepositorioProdutoBDR instance;
	private static final String NOME_TABELA = "produto";
	private Connection connection;
	private int dataBase = Database.MYSQL;

	public static RepositorioProdutoBDR getInstance() throws Exception {
		if (instance == null) {
			instance = new RepositorioProdutoBDR();
		}
		return instance;
	}

	public RepositorioProdutoBDR() throws Exception {
		this.connection = Conexao.getConnection(dataBase);
	}

	@Override
	public void cadastrar(Produto p) throws JaCadastradoException, CampoObritarorioException,
			NaoFoiPossivelCadastrarException, SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		if (existeId(p) == false) {
			System.out.println("2");
			sql = "insert into " + NOME_TABELA
					+ " (nome, quantidade, quantidade_Minima, valor_Compra, valor_Venda, ativo)"
					+ " values (?,?,?,?,?,?);";
			if (this.dataBase == Database.ORACLE) {
				ps = this.connection.prepareStatement(sql, new String[] { "id" });
			} else {
				ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}
			ps.setString(1, p.getNome());
			ps.setInt(2, p.getQuantidade());
			ps.setInt(3, p.getQuantidadeMinima());
			ps.setDouble(4, p.getValorCompra());
			ps.setDouble(5, p.getValorVenda());
			ps.setString(6, String.valueOf(p.getAtivo()));
			ps.execute();
			rs = ps.getGeneratedKeys();
			int id = 0;
			if (rs != null) {
				while (rs.next()) {
					id = rs.getInt(1);
				}
				p.setId(id);
				System.out.println(p.toString());
			} else {
				throw new NaoFoiPossivelCadastrarException(
						"Ops possivel erro no sistema...\ntente novamente, caso o erro persista contatar o resposavel pelo sistema. ");
			}
		} else {
			throw new JaCadastradoException(
					p.getNome() + " ja esta cadastrado...\n tente novamente com outro administrador.");
		}

	}

	@Override
	public ArrayList<Produto> listar(String complemento) throws NaoEncontradoException, SQLException, Exception {
		ArrayList<Produto> lista = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";

		sql = "select *from " + NOME_TABELA + " ";
		sql += "where id is not null ";
		sql += complemento;
		sql += " order by nome;";
		ps = this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
		if (rs != null) {
			while (rs.next()) {
				Produto p = new Produto(rs.getInt("id"), rs.getString("nome"), rs.getInt("quantidade"),
						rs.getInt("quantidade_Minima"), rs.getDouble("valor_Venda"), rs.getDouble("valor_Compra"),
						rs.getString("ativo").charAt(0));
				lista.add(p);
			}
		} else {
			throw new NaoEncontradoException("Administrador nao encontrado");
		}
		ps.close();
		rs.close();
		return lista;
	}

	@Override
	public ArrayList<Produto> listarTodos() throws NaoEncontradoException, SQLException, Exception {
		return listar("");
	}

	@Override
	public ArrayList<Produto> pesquisarPorNome(String nome)
			throws NaoEncontradoException, CampoObritarorioException, SQLException, Exception {
		return listar("AND nome LIKE '%" + nome + "%'");
	}

	@Override
	public Produto pesquisarPorId(int id)
			throws NaoEncontradoException, CampoObritarorioException, SQLException, Exception {
		return listar("AND id=" + id).get(0);
	}

	@Override
	public void remover(int id) throws NaoFoiPossivelAtivarException, NaoFoiPossivelInativarException,
			CampoObritarorioException, NaoEncontradoException, SQLException, Exception {
		Produto p = new Produto(id, "", 0, 0, 0, 0, 'N');
		if (existeId(p) == false) {
			PreparedStatement ps = null;
			String sql = "";
			sql = "UPDATE " + NOME_TABELA + " SET ativo=? WHERE id=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, String.valueOf(p.getAtivo()));
			ps.setInt(2, p.getId());
			Integer resultado = ps.executeUpdate();
			if (resultado == 0)
				throw new NaoFoiPossivelAlterarException("");
			ps.close();
		} else {
			throw new NaoCadastradoException("");
		}
	}

	@Override
	public void ativar(int id) throws NaoFoiPossivelAtivarException, NaoFoiPossivelInativarException,
			CampoObritarorioException, NaoEncontradoException, SQLException, Exception {
		Produto p = new Produto(id, "", 0, 0, 0, 0, 'A');
		if (existeId(p) == false) {
			System.out.println("teste"+id);
			PreparedStatement ps = null;
			String sql = "";
			sql = "UPDATE " + NOME_TABELA + " SET ativo=? WHERE id=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, String.valueOf(p.getAtivo()));
			ps.setInt(2, p.getId());
			Integer resultado = ps.executeUpdate();
			if (resultado == 0)
				throw new NaoFoiPossivelAlterarException("");
			ps.close();
		} else {
			throw new NaoCadastradoException("");
		}
	}

	@Override
	public void alterar(Produto p) throws NaoFoiPossivelAlterarException, CampoObritarorioException,
			NaoEncontradoException, SQLException, Exception {
		System.out.println(p.toString());
		if (existeId(p) == false) {
			PreparedStatement ps = null;
			String sql = "";
			sql = "UPDATE " + NOME_TABELA + " SET nome=?, quantidade=?, quantidade_Minima=?, "
					+ "valor_Compra=?, valor_Venda=? WHERE id=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, p.getNome());
			ps.setInt(2, p.getQuantidade());
			ps.setInt(3, p.getQuantidadeMinima());
			ps.setDouble(4, p.getValorCompra());
			ps.setDouble(5, p.getValorVenda());
			ps.setInt(6, p.getId());
			Integer resultado = ps.executeUpdate();
			System.out.println("" + resultado);
			if (resultado == 0)
				throw new NaoFoiPossivelAlterarException("Infelizmente nao foi possivel alterar o registro.");
			ps.close();
			System.out.println("- consulta completada com sucesso -");
		} else {
			throw new NaoCadastradoException("Este administrado nao esta cadastrado em nosso banco de dados");

		}
	}

	@Override
	public void fazerCompra(Produto p) throws NaoFoiPossivelAlterarException, CampoObritarorioException,
			NaoEncontradoException, SQLException, Exception {
		if (existeId(p) == false) {
			PreparedStatement ps = null;
			String sql = "";
			sql = "UPDATE " + NOME_TABELA + " SET quantidade = quantidade - 1 WHERE id=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setInt(1, p.getId());
			Integer resultado = ps.executeUpdate();
			if (resultado == 0)
				throw new NaoFoiPossivelAlterarException("Infelizmente nao foi possivel alterar o registro.");
			ps.close();
		} else {
			throw new NaoCadastradoException("Este administrado nao esta cadastrado em nosso banco de dados");
		}
	}

	@Override
	public boolean existeId(Produto p) throws JaCadastradoException, SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + NOME_TABELA + " WHERE id=?";
		boolean resposta = true;
		ps = connection.prepareStatement(sql);
		ps.setInt(1, p.getId());
		rs = ps.executeQuery();
		if (rs != null) {
			resposta = false;
			System.out.println("foi");
		}
		ps.close();
		rs.close();
		return resposta;
	}
}
