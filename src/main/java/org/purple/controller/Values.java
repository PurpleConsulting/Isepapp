package org.purple.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.purple.bean.Page;
import org.purple.bean.Value;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;
import org.purple.model.Auth;
import org.purple.model.DaoValues;

/**
 * Servlet implementation class Value
 */
@WebServlet("/Values")
public class Values extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Values() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Page p = new Page();
		// On ajout le css
		if(Auth.isRespo(request)){
			p.setCss("values.css");
			p.setJs("values.js");
			p.setContent("/mark/values.jsp");
			p.setTitle("ISEP / APP - Notation");
			
			//les Daos
			Connection bddServletCo = Bdd.getCo();
			DaoValues dv = new DaoValues(bddServletCo);
			
			// Afficher les values
			Value[] value = dv.selectAllValues();
			request.setAttribute("valeur", value);
				try {
					bddServletCo.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}else{
			Isep.bagPackHome(p, request.getSession());
			p.setWarning(true);
			p.setWarningMessage("Cette page n'est pas accessible.");
			p.setTitle("ISEP / APP - Erreur");
		}
		
		request.setAttribute("pages", p);
		this.getServletContext().getRequestDispatcher("/template.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Page p = new Page();
		Connection bddServletCo = Bdd.getCo();
		DaoValues dv = new DaoValues(bddServletCo);
		
		if(Auth.isRespo(request)){
			// Modifier et supprimer une value
			if (request.getParameter("modify").equals("1")) {
				String nombre = request.getParameter("int");
				String[] checkbox = request.getParameterValues("delete");
				Value[] valu = new Value[Integer.parseInt(nombre) + 1];
				int CountTrue = 0;

				if (checkbox != null) {
					for (int i = 0; i < checkbox.length; ++i) {
						dv.deleteId(Integer.parseInt(checkbox[i]));
					}
				}
				for (int i = 0; i <= Integer.parseInt(nombre); i++) {
					String title = request.getParameter("title" + i);
					String points = request.getParameter("points" + i);
					String id = request.getParameter("id" + i);
					char point = points.charAt(0);
					boolean chara = Character.isDigit(point);
					Value val = new Value();
					if (chara == true) {
						CountTrue++;
						val.setPoints(Integer.parseInt(points));
					}
					val.setId(Integer.parseInt(id));
					val.setTitle(title);
	
					valu[i] = val;
				}
	
				if (CountTrue == Integer.parseInt(nombre) + 1) {
					dv.updateValues(valu);
					p.setError(false);
	
				} else {
					p.setError(true);
					p.setErrorMessage("Vous avez rentrer une valeur incorrect.");
				}
	
				// On ajout le css
				p.setCss("values.css");
				p.setJs("values.js");
				p.setContent("/mark/values.jsp");
				p.setTitle("ISEP / APP - Notation");
	
				// Afficher les values
				Value[] value = dv.selectAllValues();
				request.setAttribute("valeur", value);
			
			}else{
				Isep.bagPackHome(p, request.getSession());
				p.setWarning(true);
				p.setWarningMessage("Cette page n'est pas accessible.");
				p.setTitle("ISEP / APP - Erreur");
			}
			this.getServletContext().getRequestDispatcher("/template.jsp")
					.forward(request, response);


		// Ajouter une value
			if (request.getParameter("modify").equals("2")) {
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
	
				String title = request.getParameter("newtitle");
				String points = request.getParameter("newpoints");
				Value val = new Value();
	
				char point = points.charAt(0);
				boolean chara = Character.isDigit(point);
	
				if (chara == true) {
					out.write("0");
					val.setTitle(title);
					val.setPoints(Integer.parseInt(points));
					dv.create(val);
				} else {
					out.write("1");
				}
			}
		}
		request.setAttribute("pages", p);
		try {
			bddServletCo.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
