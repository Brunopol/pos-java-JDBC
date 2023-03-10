package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaoJdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class UserPosDAO {

	private Connection connection;

	public UserPosDAO() {

		connection = SingleConnection.getConnection();

	}

	public void salvar(Userposjava userposjava) {

		try {
			String sql = "insert into userposjava (nome, email) values (?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, userposjava.getNome());
			insert.setString(2, userposjava.getEmail());
			insert.execute();
			connection.commit();

		} catch (Exception e) {

			try {
				connection.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}

			e.printStackTrace();
		}

	}

	public void atualizar(Userposjava userposjava) {

		try {

			String sql = "update userposjava set nome = ? where id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userposjava.getNome());
			statement.setLong(2, userposjava.getId());
			statement.execute();
			connection.commit();

		} catch (Exception e) {

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();

		}

	}

	public void deletar(long id) {

		try {

			String sql = "delete from userposjava where id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			statement.execute();
			connection.commit();

		} catch (Exception e) {

			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

			e.printStackTrace();

		}

	}

	public List<Userposjava> consultarRetornaLista() {

		List<Userposjava> list = new ArrayList<Userposjava>();
		try {
			String sql = "select * from userposjava";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {

				Userposjava userposjava = new Userposjava();
				userposjava.setId(resultado.getLong("id"));
				userposjava.setNome(resultado.getString("nome"));
				userposjava.setEmail(resultado.getString("email"));

				list.add(userposjava);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return list;

	}

	public List<Userposjava> buscar(long id) {

		List<Userposjava> list = new ArrayList<Userposjava>();
		try {
			String sql = "select * from userposjava where id = " + id;
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {

				Userposjava userposjava = new Userposjava();
				userposjava.setId(resultado.getLong("id"));
				userposjava.setNome(resultado.getString("nome"));
				userposjava.setEmail(resultado.getString("email"));
				list.add(userposjava);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return list;

	}

	public List<Userposjava> buscar(String nome) {

		List<Userposjava> list = new ArrayList<Userposjava>();
		try {
			String sql = "select * from userposjava where nome like " + "'%" + nome + "%'";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {

				Userposjava userposjava = new Userposjava();
				userposjava.setId(resultado.getLong("id"));
				userposjava.setNome(resultado.getString("nome"));
				userposjava.setEmail(resultado.getString("email"));
				list.add(userposjava);

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return list;

	}

	// ________________________________________________________________

	public void salvarTelefone(Telefone telefone) {

		try {

			String sql = "INSERT INTO telefoneuser(numero, tipo, usuariopessoa) VALUES (?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, telefone.getNumero());
			statement.setString(2, telefone.getTipo());
			statement.setLong(3, telefone.getUsuariopessoa());
			statement.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<BeanUserFone> retornaUserTelefone(Long id) {

		List<BeanUserFone> list = new ArrayList<BeanUserFone>();

		try {

			String sql = " select nome,email,tipo,numero from telefoneuser as fone ";
			sql += " inner join userposjava as userp ";
			sql += " on fone.usuariopessoa = userp.id ";
			sql += " where userp.id = ? ";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {

				BeanUserFone beanUserFone = new BeanUserFone();

				beanUserFone.setNome(resultado.getString("nome"));
				beanUserFone.setEmail(resultado.getString("email"));
				beanUserFone.setTipo(resultado.getString("tipo"));
				beanUserFone.setNumero(resultado.getString("numero"));

				list.add(beanUserFone);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public void deletarUserComTelefone(Long id) {

		try {

			String sqlDeletarUserTelefone = "delete from telefoneuser where usuariopessoa = " + id;

			String sqlDeletarUser = "delete from userposjava where id = " + id;

			PreparedStatement statement = connection.prepareStatement(sqlDeletarUserTelefone);
			statement.executeUpdate();
			connection.commit();

			statement = connection.prepareStatement(sqlDeletarUser);
			statement.executeUpdate();
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
