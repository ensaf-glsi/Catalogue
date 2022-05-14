package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exceptions.TechnicalException;
import metier.entities.Client;
import metier.entities.Produit;
import utils.SqlUtils;

public class ClientDao {
	static final String QUERY_INSERT = "INSERT INTO CLIENTS(NOM,TEL,EMAIL) VALUES (?,?,?)";

	public Client save(Client c) {
		try {
			// regles de gestion a ajouter ici
			Long id = SqlUtils.insert(QUERY_INSERT,
					new Object[] {c.getNom(), c.getTel(), c.getEmail()}
			);
			c.setId(id);
			return c;
		} catch (SQLException e) {
			System.err.println("Erreur ajout client : " + e.getStackTrace());
			throw new TechnicalException("Ereur d'ajout du client, merci de contacter l'admin du système", e);
		}
	}
}
