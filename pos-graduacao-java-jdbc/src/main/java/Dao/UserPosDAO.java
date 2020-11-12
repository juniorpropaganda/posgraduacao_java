package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao_jdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.Userposgraduacao;

public class UserPosDAO {
	
	private Connection connection;
	
	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar (Userposgraduacao userposgraduacao) {
		try {
			
		
		String sql = "insert into userposgraduacao (nome, email)values (?,?)";
		PreparedStatement insert = connection.prepareStatement(sql);
		//insert.setLong(1, userposgraduacao.getId());
		insert.setString(1, userposgraduacao.getNome());
		insert.setString(2, userposgraduacao.getEmail());
		insert.execute();
		connection.commit(); // salva no banco
		
		}catch (Exception e) {
			try {
				connection.rollback(); //reverte operação
			} catch (SQLException e1) {
				// quando digita esse roll banck ai ele pede para acrescentar esse codigo automaticamente.
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public ArrayList<Userposgraduacao> listar () throws Exception{
		ArrayList<Userposgraduacao> list = new ArrayList<Userposgraduacao>();
		
		String sql = "select * from userposgraduacao";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) {
			Userposgraduacao userposgraduacao = new Userposgraduacao();
			userposgraduacao.setId(resultado.getLong("id"));
			userposgraduacao.setNome(resultado.getString("nome"));
			userposgraduacao.setEmail(resultado.getString("email"));
			
			
			list.add(userposgraduacao);
		}
		
		return list;
		
	};
	
	
	public Userposgraduacao buscar (Long id) throws Exception{
		Userposgraduacao retorno = new Userposgraduacao();
		
		String sql = "select * from userposgraduacao where id = " + id;
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) { // retorna apenas um ou nenhum
			
			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));
			
			
			
		}
		
		return retorno;
		
	}
	
	
	
	
	
	
	
	public List<BeanUserFone> listaUserFone(Long idUser) {
		
		List<BeanUserFone> beanUserFones = new ArrayList<BeanUserFone>();
		
		String sql = " select nome, numero, email from telefoneuser as fone ";
		sql += " inner join userposgraduacao as userp ";
		sql += " on fone.usuariopessoa = userp.id  ";
		sql += " where userp.id = " + idUser;
		
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				BeanUserFone userFone = new BeanUserFone();
				
				userFone.setEmail(resultSet.getString("email"));
				userFone.setNome(resultSet.getString("nome"));
				userFone.setNumero(resultSet.getString("numero"));
				
				beanUserFones.add(userFone);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		return beanUserFones;
	}
	
	
	
	
	
	
	public void atualizar (Userposgraduacao userposgraduacao) {
		
		try {
			String sql = "update userposgraduacao set nome = ? where id = " + userposgraduacao.getId();
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userposgraduacao.getNome());
			
			statement.execute();
			connection.commit();
		
		}catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		}
		
		
	}
	
	public void deletar(Long id) {
		try {
			String sql = "delete from userposgraduacao where id = " + id;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			connection.commit();
			
		} catch(Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
		
	
	public void salvarTelefone(Telefone telefone) {
		
		try {
			
			String sql = "INSERT INTO telefoneuser(numero, tipo, usuariopessoa) VALUES (?,?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, telefone.getNumero());
			statement.setString(2, telefone.getTipo());
			statement.setLong(3, telefone.getUsuario());
			statement.execute();
			connection.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
		
	}
	
	
	public void deleteFonesPorUser(Long idUser) {
		

		
		try {
			
			String sqlFone = "delete from telefoneuser where usuariopessoa =" + idUser;
			String sqlUser = "delete from userposgraduacao where id =" +idUser;
			
			PreparedStatement preparedStatement = connection.prepareStatement(sqlFone);
			preparedStatement.executeUpdate();
			connection.commit();
			
			preparedStatement = connection.prepareStatement(sqlUser);
			preparedStatement.executeUpdate();
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
			
				e1.printStackTrace();
			}
		}
	}

}
