package br.com.projetoloja.conta;

import br.com.projetoloja.conexao.Conexao;
import br.com.projetoloja.conexao.Database;
import br.com.projetoloja.execoes.CampoObritarorioException;
import br.com.projetoloja.execoes.JaCadastradoException;
import br.com.projetoloja.execoes.NaoCadastradoException;
import br.com.projetoloja.execoes.NaoEncontradoException;
import br.com.projetoloja.execoes.NaoFoiPossivelAlterarException;
import br.com.projetoloja.execoes.NaoFoiPossivelCadastrarException;
import br.com.projetoloja.execoes.NaoFoiPossivelDeletarException;
import br.com.projetoloja.produto.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RepositorioContaBDR implements IRepositorioContaBDR{
    
    	private static RepositorioContaBDR instance;
	private static final String NOME_TABELA = "conta";
	private Connection connection;
	private int dataBase = Database.MYSQL;

	public static RepositorioContaBDR getInstance() throws Exception {
		if (instance == null) {
			instance = new RepositorioContaBDR();
		}
		return instance;
	}

	public RepositorioContaBDR() throws Exception {
		this.connection = Conexao.getConnection(dataBase);
	}

    @Override
    public void cadastrar(Conta c) throws JaCadastradoException, CampoObritarorioException, NaoFoiPossivelCadastrarException, SQLException, Exception {
     		System.out.println("repositrio cadastrar ok");
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
                System.out.println("1");
		if (existeId(c) == false) {
			sql = "insert into " + NOME_TABELA + "(idCliente, valor_Total, valor_Parcela, quantidade_Parcelas) values (?,?,?,?);";
                System.out.println("2");
			if (this.dataBase == Database.ORACLE) {
				ps = this.connection.prepareStatement(sql, new String[] { "id" });
			} else {
				ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			}

			ps.setInt(1, c.getIdCliente());
			ps.setDouble(2, c.getValorTotal());
			ps.setDouble(3, c.getValorParcela());
			ps.setInt(4, c.getQuantidadeParcelas());
			ps.execute();
			rs = ps.getGeneratedKeys();
			int id = 0;
			if (rs != null) {
				while (rs.next()) {
					id = rs.getInt(1);
				}
				c.setId(id);
				System.out.println(c.toString());
				System.out.println("concluido");
			} else {
				throw new NaoFoiPossivelCadastrarException(
						"Ops possivel erro no sistema...\ntentar novamente caso o erro persista contatar o resposavel pelo sistema. ");
			}
		}else{
			throw new JaCadastradoException("ja esta cadastrado...\n tente novamente com outro administrador.");
		}
    }

    @Override
    public Conta historicoConta(int idCliente) throws NaoEncontradoException, CampoObritarorioException, SQLException, Exception {
		ArrayList<Produto> lista = new ArrayList<>();
		Conta c = new Conta(0,idCliente,lista,0,0,0);
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		System.out.println("1");
		sql = "select *from view_historico_compra";
		sql += " where id_cliente = " + idCliente+";";
		ps = this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
		System.out.println("2");
		if(rs !=null){
			System.out.println("3");
			while(rs.next()){
				System.out.println("4");
				Produto p = new Produto(rs.getInt("id_Cliente"), rs.getString("nome_produto"),0,rs.getDouble("valor_Venda"),'A',0);
				p.setData(rs.getString("dataCompra"));
				lista.add(p);
			}
			System.out.println("consulta completada com sucesso...");
		}else{
			throw new NaoEncontradoException("Cliente nao encontrado");
		}
		ps.close();
		rs.close();
		return c;
    }
    @Override
    public void remover(int id) throws NaoFoiPossivelDeletarException, CampoObritarorioException, NaoEncontradoException, SQLException, Exception {
//    System.out.println("Chegando ao repositorio");
//		Conta conta = new Conta(id, 0, null, 0, 0, 0);
//		if (existeId(conta) == false){
//			PreparedStatement ps = null;
//			String sql = "";
//			sql = "UPDATE " + NOME_TABELA + " SET ativo=? WHERE id=?;";
//			ps = this.connection.prepareStatement(sql);
//			ps.setString(1, String.valueOf(conta.getAtivo()));
//			ps.setInt(2, conta.getId());
//			Integer resultado = ps.executeUpdate();
//			if (resultado == 0) throw new NaoFoiPossivelAlterarException("Não foi possivel fazer alteracao");
//			ps.close();
//			System.out.println("- removido com sucesso -");
//		}else{
//			throw new NaoCadastradoException("Este administrador nao esta cadastrado no sistema");
//		}
    }
    
    @Override
    public void gerarCaner(Conta c) throws Exception {
		if (existeId(c) == false){
			PreparedStatement ps = null;
			String sql = "";
			sql = "UPDATE " + NOME_TABELA + " SET valor_Total=?,valor_Parcela=?, quantidade_Parcelas=?,parcela_Atual=? WHERE idConta=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setDouble(1, c.getValorTotal());
			ps.setDouble(2, c.getValorParcela());
			ps.setInt(3, c.getQuantidadeParcelas());
			ps.setInt(4, c.getParcelaAtual());
			ps.setInt(5, c.getId());
			Integer resultado = ps.executeUpdate();
			if (resultado == 0) throw new NaoFoiPossivelAlterarException("Não foi possivel fazer alteracao");
			ps.close();
		}else{
			throw new NaoCadastradoException("Este administrador nao esta cadastrado no sistema");
		}
    }
    
    @Override
    public void compraConta(Conta c) throws Exception {
		if (existeId(c) == false){
			PreparedStatement ps = null;
			String sql = "";
			sql = "UPDATE " + NOME_TABELA + " SET valor_Total=?,valor_Parcela=?, quantidade_Parcelas=? WHERE idConta=?;";
			ps = this.connection.prepareStatement(sql);
			ps.setDouble(1, c.getValorTotal());
			ps.setDouble(2, c.getValorParcela());
			ps.setInt(3, c.getQuantidadeParcelas());
			ps.setInt(4, c.getId());
			for(Produto p : c.getHistoricoConta()){
				for(int i = 0; i< p.getQuantidade();i++){
				insertHistorico(c.getId(), p);
				}
			}
			Integer resultado = ps.executeUpdate();
			if (resultado == 0) throw new NaoFoiPossivelAlterarException("Não foi possivel fazer alteracao");
			ps.close();
		}else{
			throw new NaoCadastradoException("Este administrador nao esta cadastrado no sistema");
		}
    }
    
    private void insertHistorico(int idCliente,Produto p) throws JaCadastradoException, CampoObritarorioException, NaoFoiPossivelCadastrarException, SQLException, Exception {
 		System.out.println("repositrio cadastrar ok");
	PreparedStatement ps = null;
	ResultSet rs = null;
	String sql = "";
		sql = "insert into historicoconta (idCliente, idProduto,dataCompra) values (?,?,?);";
            System.out.println("2");
		if (this.dataBase == Database.ORACLE) {
			ps = this.connection.prepareStatement(sql, new String[] { "id" });
		} else {
			ps = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		}
		ps.setInt(1, idCliente);
		ps.setInt(2, p.getId());
		ps.setString(3,p.data());
		ps.execute();
		rs = ps.getGeneratedKeys();
}

    @Override
    public void pagamento(int id) throws Exception {
		Conta conta = new Conta(id, 0, null, 0, 0, 0);
		System.out.println("id : "+id);
		if (existeId(conta) == false){
			PreparedStatement ps = null;
			String sql = "";
			sql = "UPDATE " + NOME_TABELA +
			" SET parcela_Atual = parcela_Atual +1,valor_Total = valor_Total - valor_Parcela WHERE idConta="+id+";";
			ps = this.connection.prepareStatement(sql);
			Integer resultado = ps.executeUpdate();
			if (resultado == 0) throw new NaoFoiPossivelAlterarException("Não foi possivel fazer alteracao");
			ps.close();
		}else{
			throw new NaoCadastradoException("Este administrador nao esta cadastrado no sistema");
		}
    }

	@Override
	public void pagamentoLivre(Conta conta) throws Exception {
		if (existeId(conta) == false){
			PreparedStatement ps = null;
			String sql = "";
			sql = "UPDATE " + NOME_TABELA +
					" SET valor_Total = valor_Total - "+ conta.getValorParcela()+ "WHERE idConta="+conta.getId()+";";
			ps = this.connection.prepareStatement(sql);
			Integer resultado = ps.executeUpdate();
			if (resultado == 0) throw new NaoFoiPossivelAlterarException("Não foi possivel fazer alteracao");
			ps.close();
		}else{
			throw new NaoCadastradoException("Este administrador nao esta cadastrado no sistema");
		}
	}
	
	
    @Override
    public boolean existeId(Conta c) throws JaCadastradoException, SQLException, Exception {
        	System.out.println("Chegando ao repositorio");
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + NOME_TABELA + " WHERE idConta=?";
		boolean resposta = true;		
		ps = connection.prepareStatement(sql);
		ps.setInt(1, c.getId());
		rs = ps.executeQuery();
                System.out.println("aqui 1");
		if(rs != null){
			resposta = false;
                        System.out.println("aqui");
		}
		ps.close();
		rs.close();
		System.out.println("- consulta completada com sucesso -");
		return resposta;
    }

	@Override
	public Conta recuperarConta(int idConta) throws JaCadastradoException, SQLException, Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Conta conta = null;
		String sql = "SELECT * FROM " + NOME_TABELA + " WHERE idConta=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, idConta);
		rs = ps.executeQuery();
		System.out.println("aqui 1");
		if(rs != null){
			while(rs.next()) {
				if (rs.getInt("idConta") == idConta) {
					conta = new Conta(rs.getDouble("valor_Parcela"), rs.getInt("parcela_Atual"));
					conta.setQuantidadeParcelas(rs.getInt("quantidade_Parcelas"));
				}
			}
		}
		ps.close();
		rs.close();
		System.out.println("- consulta completada com sucesso -");
		return conta;
	}

}
