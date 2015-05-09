package org.purple.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.purple.bean.Page;
import org.purple.bean.Value;
import org.purple.constant.Bdd;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Page p = new Page();
		// On ajout le css
		p.setCss("marks.css");
		p.setJs("marks.js");
		p.setContent("/mark/values.jsp");
		request.setAttribute("pages", p);
		
		
		DaoValues v = new DaoValues(Bdd.getCo());
		//Afficher les values
		Value[] value= v.selectAllValues();
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
		
		//Modifier et supprimer une value
	if(request.getParameter("modify").equals("1")){
		String nombre=request.getParameter("int");
		Value[] valu=new Value[Integer.parseInt(nombre)+1];
		
		String[] checkbox = request.getParameterValues("delete");
		if(checkbox!=null){
		for (int i = 0; i < checkbox.length; ++i){ 
			v.deleteId(Integer.parseInt(checkbox[i])); 
			} 
		}
		for(int i=0; i<=Integer.parseInt(nombre); i++){
		
			String title = request.getParameter("title"+i);
			String points = request.getParameter("points"+i);
			String id = request.getParameter("id"+i);
			Value val = new Value();
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
			Value val = new Value();
			val.setId(Integer.parseInt(nombre));
			val.setTitle(title);
			val.setPoints(Integer.parseInt(points));
			
			v.create(val);
			
		}
	
		
		//Appel � la page affichage value
		Page p = new Page();
		// On ajout le css
		p.setCss("marks.css");
		p.setJs("marks.js");
		p.setContent("/mark/values.jsp");
		request.setAttribute("pages", p);
			
		//Afficher les values
		Value[] value= v.selectAllValues();
		request.setAttribute("valeur", value);
		
			this.getServletContext().getRequestDispatcher("/template.jsp")
					.forward(request, response);
		
		
	
	}

}
