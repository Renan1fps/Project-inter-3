package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Provider;
import database.DbException;
import model.dao.UserDao;
import model.entities.User;

public class UserDaoJDBC implements UserDao {

	private final Connection conn;

	public UserDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(User obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO tb_user " + "(name, email, cpf, phone, nacionalidade, password) " + "VALUES " + "(?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setString(3, obj.getCpf());
			st.setString(4, obj.getPhone());
			st.setString(5, obj.getNacionalidade());
			st.setString(6, obj.getPassowrd());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				Provider.closeResultSet(rs);
			} else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Provider.closeStatement(st);
		}
	}

	@Override
	public void update(User obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE  tb_user " + "SET name = ?, email = ?, cpf = ? " + "WHERE id = ?");

			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setString(3, obj.getCpf());

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Provider.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM tb_user WHERE id = ?");

			st.setInt(1, id);

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Provider.closeStatement(st);
		}
	}

	@Override
	public User findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT tb_user.*" + "WHERE tb_user.id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {

				User obj = instantiateUser(rs);
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Provider.closeStatement(st);
			Provider.closeResultSet(rs);
		}
	}

	private User instantiateUser(ResultSet rs) throws SQLException {
		User obj = new User();
		obj.setId(rs.getInt("id_user"));
		obj.setName(rs.getString("name"));
		obj.setEmail(rs.getString("email"));
		obj.setCpf(rs.getString("cpf"));
		obj.setPhone(rs.getString("phone"));
		obj.setNacionalidade(rs.getString("nacionalidade"));
		obj.setIs_adm(rs.getBoolean("is_admin"));
		obj.setPassowrd(rs.getString("password"));
		return obj;
	}

	@Override
	public List<User> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT tb_user.*" + "FROM tb_user ");

			rs = st.executeQuery();

			List<User> list = new ArrayList<>();

			while (rs.next()) {
				User obj = instantiateUser(rs);
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Provider.closeStatement(st);
			Provider.closeResultSet(rs);
		}
	}
	
	@Override
	public User findByEmail(String email) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM tb_user WHERE email = ?");

			st.setString(1, email);
			rs = st.executeQuery();
			if (rs.next()) {
				return instantiateUser(rs);
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			Provider.closeStatement(st);
			Provider.closeResultSet(rs);
		}
	}

}
