package org.purple.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.purple.bean.Page;
import org.purple.bean.User;
import org.purple.bean.Values;
import org.purple.constant.Bdd;
import org.purple.model.DaoUsers;
import org.purple.model.DaoValues;

/**
 * Servlet implementation class ValuesController
 */
@WebServlet("/ValuesController")
public class ValuesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValuesController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		Page p = new Page();
		// On ajout le css
		p.setCss("marks.css");
		p.setJs("marks.js");
		p.setContent("/mark/values_level.jsp");
		request.setAttribute("pages", p);
		
		
		DaoValues v = new DaoValues(Bdd.getCo());
		//Afficher les values
		Values[] value= v.selectAllValues();
		request.setAttribute("valeur", value);
		
		
			this.getServletContext().getRequestDispatcher("/template.jsp")
					.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	DaoValues v = new DaoValues(Bdd.getCo());
		
		//Modifier une value
	if(request.getParameter("modify").equals("1")){
		String nombre=request.getParameter("int");
		Values[] valu=new Values[Integer.parseInt(nombre)+1];
		
		for(int i=0; i<=Integer.parseInt(nombre); i++){
		
		String title = request.getParameter("title"+i);
		String points = request.getParameter("points"+i);
		String id = request.getParameter("id"+i);
				
		Values val = new Values();
		val.setId(Integer.parseInt(id));
		val.setTitle(title);
		val.setPoints(Integer.parseInt(points));
		valu[i] = val;
			
		}		
					
		v.updateValues(valu);
		}
	
				
		//Ajouter une value
		if(request.getParameter("modify").equals("2")){
			String title = request.getParameter("newtitle");
			String points = request.getParameter("newpoints");
			String nombre=request.getParameter("number");
			Values val = new Values();
			val.setTitle(title);
			val.setPoints(Integer.parseInt(points));
			val.setId(Integer.parseInt(nombre));
			
			v.create(val);
		}
	
		//Appel à la page affichage value
		Page p = new Page();
		// On ajout le css
		p.setCss("marks.css");
		p.setJs("marks.js");
		p.setContent("/mark/values_level.jsp");
		request.setAttribute("pages", p);
			
	
		//Afficher les values
		Values[] value= v.selectAllValues();
		request.setAttribute("valeur", value);
		
			this.getServletContext().getRequestDispatcher("/template.jsp")
					.forward(request, response);
		
		
	}

}
