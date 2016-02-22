package br.com.projetoloja.cliente;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.projetoloja.conexao.Conexao;
import br.com.projetoloja.conexao.Database;
import br.com.projetoloja.conta.Conta;
import br.com.projetoloja.endereco.Endereco;
import br.com.projetoloja.execoes.CPFInvalidoException;
import br.com.projetoloja.execoes.CampoObritarorioException;
import br.com.projetoloja.execoes.FoneInvalidoException;
import br.com.projetoloja.execoes.JaCadastradoException;
import br.com.projetoloja.execoes.NaoCadastradoException;
import br.com.projetoloja.execoes.NaoEncontradoException;
import br.com.projetoloja.execoes.NaoFoiPossivelAlterarException;
import br.com.projetoloja.execoes.NaoFoiPossivelAtivarException;
import br.com.projetoloja.execoes.NaoFoiPossivelCadastrarException;
import br.com.projetoloja.execoes.NaoFoiPossivelDeletarException;
import br.com.projetoloja.execoes.NaoFoiPossivelInativarException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RepositorioClienteBDR implements IRepositorioClienteBDR{

	private static RepositorioClienteBDR instance;
	private static final String NOME_TABELA = "cliente";
	private Connection connection;
	private int dataBase = Database.MYSQL;

	public static RepositorioClienteBDR getInstance() throws Exception{
		if(instance == null){
			instance = new RepositorioClienteBDR();
		}
		return instance;
	}

	public RepositorioClienteBDR() throws Exception{
		this.connection = Conexao.getConnection(dataBase);
	}

	@Override
	public void cadastrar(Cliente c) throws JaCadastradoException, CampoObritarorioException, CPFInvalidoException,
			FoneInvalidoException, NaoFoiPossivelCadastrarException, SQLException, Exception {
	System.out.println("repositrio cadastrar ok");
	PreparedStatement ps = null;
	ResultSet rs = null;
	String sql = "";
                            System.out.println("1");
        	if (existeId(c) == false) {
                    System.out.println("2");
			sql = "insert into " + NOME_TABELA + "(cpf, nome, telefone, celular, cep, cidade, bairro, rua, numero, complemento, ativo)"
                                + " values (?,?,?,?,?,?,?,?,?,?,?);";

			if (this.dataBase == Database.ORACLE) {
				ps = this.connection.prepareStatement(sql, new String[] { "id" });
			} else {
				ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                                                    System.out.println("3");
			}
                                            System.out.println("4");
                        ps.setString(1, c.getCpf());
			ps.setString(2, c.getNome());
                        ps.setString(3, c.getTelefone());
                        ps.setString(4, c.getCelular());
                        ps.setInt(5, c.getEndereco().getCep());
                        ps.setString(6, c.getEndereco().getCidade());
                        ps.setString(7, c.getEndereco().getBairro());
                        ps.setString(8, c.getEndereco().getRua());
                        ps.setInt(9, c.getEndereco().getNumero());
                        ps.setString(10, c.getEndereco().getComplemento());
			ps.setString(11, String.valueOf(c.getAtivo()));
			ps.execute();
			rs = ps.getGeneratedKeys();
                        System.out.println("5");
			int id = 0;
			if (rs != null) {
				while (rs.next()) {
					id = rs.getInt(1);
                                        System.out.println("6");
				}
				c.setId(id);
				c.setConta(new Conta(0,id,null,0,0,0));
				System.out.println(c.toString());
				System.out.println("concluido");
			} else {
				throw new NaoFoiPossivelCadastrarException(
						"Ops possivel erro no sistema...\ntentar novamente caso o erro persista contatar o resposavel pelo sistema. ");
			}
		}else{
			throw new JaCadastradoException(c.getNome() + " ja esta cadastrado...\n tente novamente com outro administrador.");
		}
		
	}

	@Override
	public ArrayList<Cliente> listar(String complemento) throws NaoEncontradoException, SQLException, Exception {
		System.out.println("repositorio listar ok");
		ArrayList<Cliente> lista = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		          System.out.println("1");
		sql = "select *from view_cliente_conta";
		sql += " where id is not null ";
		sql += complemento;
		sql += " order by nome;";
		ps = this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
                System.out.println("2");
		if(rs !=null){
                         System.out.println("3");
			while(rs.next()){
                                 System.out.println("4");
				Cliente c = new Cliente(rs.getInt("id"), rs.getString("nome"),rs.getString("cpf"),rs.getString("telefone"),
						rs.getString("celular"),new Endereco(rs.getInt("cep"), rs.getString("cidade"), rs.getString("bairro"),
						rs.getString("rua"),rs.getInt("numero"),rs.getString("complemento")),
						new Conta(rs.getInt("idConta"),	rs.getInt("IdCliente"),null,rs.getDouble("valor_Parcela"),
						rs.getDouble("valor_Total"),rs.getInt("quantidade_Parcelas")),rs.getString("ativo").charAt(0));
				lista.add(c);
			}
			System.out.println("consulta completada com sucesso...");
		}else{
			throw new NaoEncontradoException("Cliente nao encontrado");
		}
		ps.close();
		rs.close();
		return lista;
	}

	@Override
	public ArrayList<Cliente> listarTodos() throws NaoEncontradoException, SQLException, Exception {
		return listar("");
	}

	@Override
	public ArrayList<Cliente> pesquisarPorNome(String nome)
			throws NaoEncontradoException, CampoObritarorioException, SQLException, Exception {
		return listar("AND nome LIKE '%" + nome + "%'");
	}

	@Override
	public Cliente pesquisarPorId(int id)
			throws NaoEncontradoException, CampoObritarorioException, SQLException, Exception {
		return listar("AND id=" + id).get(0);
	}

	@Override
	public Cliente pesquisarPorCpf(String cpf)
			throws NaoEncontradoException, CPFInvalidoException, CampoObritarorioException, SQLException, Exception {
		System.out.println("repositorio listar ok");
                Cliente cliente = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		          System.out.println("1");
		sql = "select *from cliente";
		sql += " where id is not null ";
		sql += "AND cpf='" + cpf + "'";
		sql += " order by nome;";
		ps = this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
                System.out.println("2");
		if(rs !=null){
                         System.out.println("3");
			while(rs.next()){
                                 System.out.println("4");
				Cliente c = new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"), rs.getString("telefone"), 
                                rs.getString("celular"), new Endereco(rs.getInt("cep"), rs.getString("cidade"), rs.getString("bairro"), 
                                rs.getString("rua"), rs.getInt("numero"), rs.getString("complemento")),
                                null, rs.getString("ativo").charAt(0));
				cliente = c;
			}
			System.out.println("consulta completada com sucesso...");
		}else{
			throw new NaoEncontradoException("Cliente nao encontrado");
		}
		ps.close();
		rs.close();	
            return cliente;         
	}

	@Override
	public void remover(int id) throws NaoFoiPossivelDeletarException, CampoObritarorioException,
			NaoEncontradoException, SQLException, Exception {
	            		System.out.println("Chegando ao repositorio");
		Cliente c = new Cliente(id, "", "","","", null, null, 'N');
		if(existeId(c) == false){
			PreparedStatement ps = null;
			String sql = "";
			sql = "UPDATE " + NOME_TABELA + " SET ativo=? WHERE id=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, String.valueOf(c.getAtivo()));
			ps.setInt(2, c.getId());
			Integer resultado = ps.executeUpdate();
			if (resultado == 0) throw new NaoFoiPossivelAlterarException("");
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
		Cliente c = new Cliente(id, "", "","","", null, null, 'A');
		if(existeId(c) == false){
			PreparedStatement ps = null;
			String sql = "";
			sql = "UPDATE " + NOME_TABELA + " SET ativo=? WHERE id=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setString(1, String.valueOf(c.getAtivo()));
			ps.setInt(2, c.getId());
			Integer resultado = ps.executeUpdate();
			if (resultado == 0) throw new NaoFoiPossivelAlterarException("");
			ps.close();
			System.out.println("- ativado com sucesso -");
                }else{
			throw new NaoCadastradoException("Este administrador nao esta cadastrado no sistema");
		}		
	}

	@Override
	public void alterar(Cliente c) throws NaoFoiPossivelAlterarException, CampoObritarorioException,
			NaoEncontradoException, SQLException, Exception {
           System.out.println("repositorio alterar ok");
            if (existeId(c) == false){
                PreparedStatement ps = null;
		String sql = "";
                sql = "UPDATE " + NOME_TABELA + " SET nome=?, telefone=?, celular=?, cep=?, cidade=?, bairro=?, rua=?, numero=?, complemento=? WHERE id=?;";
		ps = this.connection.prepareStatement(sql);
		ps.setString(1, c.getNome());
                ps.setString(2, c.getTelefone());
                ps.setString(3, c.getCelular());
		ps.setInt(4, c.getEndereco().getCep());
                ps.setString(5, c.getEndereco().getCidade());
                ps.setString(6, c.getEndereco().getBairro());
                ps.setString(7, c.getEndereco().getRua());
                ps.setInt(8, c.getEndereco().getNumero());
                ps.setString(9, c.getEndereco().getComplemento());
                ps.setInt(10, c.getId());
                
                
		Integer resultado = ps.executeUpdate();
		if (resultado == 0) throw new NaoFoiPossivelAlterarException("Infelizmente nao foi possivel alterar o registro.");
		ps.close();
		System.out.println("- consulta completada com sucesso -");
			
		}else{
			throw new NaoCadastradoException("Este administrado nao esta cadastrado em nosso banco de dados");
		}		
	}

	@Override
	public boolean existeId(Cliente c) throws JaCadastradoException, SQLException, Exception {
				PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + NOME_TABELA + " WHERE id=?";
		boolean resposta = true;		
		ps = connection.prepareStatement(sql);
		ps.setInt(1, c.getId());
		rs = ps.executeQuery();
		if(rs != null){
			resposta = false;
                        System.out.println("- aqui -");
		}
		ps.close();
		rs.close();
		System.out.println("- consulta completada com sucesso -");
		return resposta;
	}
	
}
