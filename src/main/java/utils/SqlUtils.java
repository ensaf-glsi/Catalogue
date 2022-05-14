package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import dao.SingletonConnection;
import exceptions.TechnicalException;

public class SqlUtils {

	// update PRODUITS set designation = 'Clavier filaire', prix = 35, quantite = 30 where id = 1;

	// tableName = PRODUITS
	// id = 1
	// fields = {designation: 'Clavier filaire', prix: 35, quantite: 30 }
	
	// query = update produits set designaion = ?, prix = ?, quantite = ? wher id = ?
	// params = ['Clavier filaire', 35, 30]
	public static int update(String tableName, Object id, Map<String, Object> fields) {
		
//		return update(query, params);
		return 0;
	}
	
	public static int update(String query, Object[] params) {
		try {
			Connection connection = SingletonConnection.getConnection();
			PreparedStatement ps = connection.prepareStatement(query);
			setParams(ps, params);
			int result = ps.executeUpdate();
			ps.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TechnicalException("Erreur interne, merci de contacter l'admin");
		}
	}
	
	
	public static Long insert(String query, Object[] params) throws SQLException {
		Connection connection = SingletonConnection.getConnection();
		PreparedStatement ps = connection.prepareStatement(
				query, 
				Statement.RETURN_GENERATED_KEYS);
		setParams(ps, params);
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
			return rs.getLong(1);
		}
		ps.close();
		return null;
	}
	
	// select * from {tablename} where id = ?
	public static <T> T findOne(String tableName, Object id, Function<ResultSet, T> rowMapper) throws SQLException {
		Connection connection = SingletonConnection.getConnection();
		PreparedStatement ps = connection.prepareStatement(
				new StringBuilder(3).append("select * from ")
				.append(tableName).append(" where id = ?")
				.toString()
		);
		ps.setObject(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return rowMapper.apply(rs);
		}
		return null;
	}
	
	public static void setParams(PreparedStatement ps, Object[] params) throws SQLException {
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
		}
	}
	/**
	 * ex 1 - query : select * from produits ; params : null 
	 * ex 2 - query : select * from produits where designation like ? ; params : ["cl"] 
	 * @param <T>
	 * @param query
	 * @param params
	 * @return
	 */
	public static <T> List<T> findList(String query, Function<ResultSet, T> rowMapper, Object... params) {
		List<T> list = new ArrayList<>();
		Connection connection = SingletonConnection.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			setParams(ps, params);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rowMapper.apply(rs));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new TechnicalException("Erreur interne, merci de contacter l'admin");
		}
	}
}
