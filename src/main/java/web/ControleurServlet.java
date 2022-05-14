package web;


import java.io.IOException;
import java.util.List;

//import org.apache.catalina.connector.Response;

import dao.IProduitDao;
import dao.ProduitDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import metier.entities.Produit;

public class ControleurServlet extends HttpServlet {
	private IProduitDao metier;
	@Override
	public void init() throws ServletException {
		metier = new ProduitDaoImpl();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if(path.equals("/index.do")) {
			request.getRequestDispatcher("produits.jsp").forward(request,response);
		}
		else if(path.equals("/chercher.do")) {
			String motCle = request.getParameter("motCle");
			ProduitModel model = new ProduitModel();
			model.setMotCle(motCle);
			List<Produit> produits = metier.produitsParMC("%"+motCle+"%");
			model.setProduits(produits);
			request.setAttribute("model", model);
			request.getRequestDispatcher("produits.jsp").forward(request,response);
		}
		else if(path.equals("/ajouter.do")) {
			request.getRequestDispatcher("SaisieProduit.jsp").forward(request,response);
		}
		else if(path.equals("/SaveProduit.do")&&(request.getMethod().equals("POST"))) {
			String des=request.getParameter("designation");
			double prix = Double.parseDouble(request.getParameter("prix"));
			int quantite=Integer.parseInt(request.getParameter("quantite"));
			Produit p=metier.save(new Produit(des,prix,quantite));
			request.setAttribute("produit", p);
			request.getRequestDispatcher("Confirmation.jsp").forward(request, response);
		}
		else {
//			response.sendError(Response.SC_NOT_FOUND);
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
