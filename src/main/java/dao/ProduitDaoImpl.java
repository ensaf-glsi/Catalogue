package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import exceptions.TechnicalException;
import metier.entities.Produit;
import utils.SqlUtils;

public class ProduitDaoImpl implements IProduitDao{
	static final String QUERY_INSERT = "INSERT INTO PRODUITS(DESIGNATION,PRIX,QUANTITE) VALUES (?,?,?)";
	static final String TABLE_NAME = "PRODUITS";
	static final String FIND_BY_KW = "SELECT * FROM PRODUITS WHERE DESIGNATION LIKE ?";

	@Override
	public Produit save(Produit p) {
//		Connection connection = SingletonConnection.getConnection();
//		try {
//			PreparedStatement ps = connection.prepareStatement(
//					"INSERT INTO PRODUITS(DESIGNATION,PRIX,QUANTITE) VALUES (?,?,?)", 
//					Statement.RETURN_GENERATED_KEYS);
//			ps.setString(1, p.getDesignation());
//			ps.setDouble(2, p.getPrix());
//			ps.setInt(3, p.getQuantite());
//			ps.executeUpdate();
//			ResultSet rs = ps.getGeneratedKeys();
//			if (rs.next()) {
//				Long id = rs.getLong(1);
//				p.setId(id);
//			}
//			ps.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return p;
		try {
			// regles de gestion a ajouter ici
			Long id = SqlUtils.insert(QUERY_INSERT,
					new Object[] {p.getDesignation(), p.getPrix(), p.getQuantite()}
			);
			p.setId(id);
			return p;
		} catch (SQLException e) {
			System.err.println("Erreur d'ajout du produit : " + e.getStackTrace());
			throw new TechnicalException("Ereur d'ajout du produit, merci de contacter l'admin du système", e);
		}
	}

	@Override
	public List<Produit> produitsParMC(String mc) {
		return SqlUtils.findList(FIND_BY_KW, this::convert, mc);
	}
	
	private Produit convert(ResultSet rs) {
		Produit p = new Produit();
		try {
			p.setId(rs.getLong("ID"));
			p.setDesignation(rs.getString("DESIGNATION"));
			p.setPrix(rs.getDouble("PRIX"));
			p.setQuantite(rs.getInt("QUANTITE"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;	
	}

	@Override
	public Produit findById(Long id) {
		try {
			return SqlUtils.findOne(TABLE_NAME, id, this::convert);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Override
	public Produit update(Produit p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProduit(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	static void fct(int... t) {
		for (int i = 0 ; i < t.length; i++) {
			System.out.println(t[i]);
		}
	}
	
	public static void main(String[] args) {
		fct(23, 3, 100, 240);
	}
	

}
