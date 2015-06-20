package org.purple.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;
import org.purple.bean.Deadline;
import org.purple.bean.Page;
import org.purple.bean.SubSkill;
import org.purple.bean.User;
import org.purple.constant.Bdd;
import org.purple.constant.Isep;
import org.purple.model.Auth;
import org.purple.model.DaoDeadline;
import org.purple.model.DaoGroups;

/**
 * Servlet implementation class FileHandler
 */
@WebServlet("/FileHandler")
public class FileHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String subjectFile = "subject_file";
    private static final String promoFile = "promo_file";
    private static final String backupFile = "backup_file";
    private static final String deliveryFile = "delivery_file";
    private String slash = "/";
    
    private void setSlash(String root){
    	if(root.indexOf("/") == -1){
    		this.slash = "\\";
    	} else if(root.indexOf("\\") == -1){
    		this.slash = "/";
    	}
    }
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileHandler() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doPivot(String folder, String fileName){
    	int abc = fileName.length();
    	String name = fileName.substring(0, abc - 4);
    	String ext = fileName.substring(abc - 4);
    	File del_file = new File(folder +this.slash+ name + "_OLDER" + ext);
    	File old_file = new File(folder +this.slash+ fileName );
    	del_file.delete();
    	old_file.renameTo(new File(folder +this.slash+ name + "_OLDER" + ext));
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Page p = new Page();
		
		/**
		 * POSSIBLE PARAMS
		 */
		// -- file request
		String deadLineId = request.getParameter("deadline-number");
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if(Auth.isConnect(request)){
			
			Connection bddServletCo = Bdd.getCo();
			DaoDeadline ddl = new DaoDeadline(bddServletCo);
			DaoGroups dg = new DaoGroups(bddServletCo);
			
			if(isMultipart){
				User user = (User) request.getSession().getAttribute("user"); 
				p.setSuccess(true);
				FileItemFactory factory = new DiskFileItemFactory();
		        ServletFileUpload upload = new ServletFileUpload(factory);
				
			    try {
			    	List items = upload.parseRequest(request);
			        Iterator iterator = items.iterator();
			        while (iterator.hasNext()) {
			            FileItem item = (FileItem) iterator.next();

			            if (!item.isFormField()) {
			                String fileName = item.getName();
			                String field = item.getFieldName();
			                

			                if(field.equals(this.subjectFile)){
			                	fileName = Isep.FILE_SUBJECT;
			                	p.setSuccessMessage("<strong>et voilà, </strong>le chargement du fichier a bien été effectué."
			                			+ " Retrouvez le nouveau sujet sur la page <em><a href=\"Subject\">sujet</a><em>.");
			                } else if (field.equals(this.promoFile)) {
			                	fileName = Isep.FILE_PROMO;
			                	p.setSuccessMessage("<strong>et voilà, </strong>les groupes du nouveau semestre "
			                			+ "ont été chargés avec succès. Retrouvez dès maintenant la demi-promo sur "
			                			+ "la page <em><a href=\"Promo\">Classes</a><em>.");
			                } else if (field.equals(this.backupFile)) {

			                	
                            }
				                
				                String root = getServletContext().getRealPath("/");
				                this.setSlash(root);
				                File path = new File(root +this.slash+ Isep.ROOT_FILE_SYS);
				                if (!path.exists()) {
				                    boolean status = path.mkdirs();
				                }
				                this.doPivot(root +this.slash+ Isep.ROOT_FILE_SYS, fileName);
				                File uploadedFile = new File(path + this.slash + fileName);
				                System.out.print(path + this.slash + fileName);
				                item.write(uploadedFile);
				                
				                if (field.equals(this.promoFile)) {
				                	boolean parsingStatus = true;
				                	parsingStatus = Isep.csvParser(root +this.slash+ Isep.ROOT_FILE_SYS , Isep.FILE_PROMO, ";");
				                	p.setSuccess(parsingStatus);
				                } 
				                
			                } else if (Auth.isStudent(request)){
			                	
			                	if(field.equals(this.deliveryFile) && !Isep.nullOrEmpty(deadLineId)){
			                		boolean businessFlag = true;
			                		Deadline deadl = ddl.select(deadLineId);
			                		
			                		if(deadl.getId() == 0 || !deadl.getStatus()) businessFlag = false;
			                		
			                		if(businessFlag){
			                			String root = getServletContext().getRealPath("/");
						                this.setSlash(root);
						                File path = new File(root +this.slash+ Isep.ROOT_FILE_SYS_DEPOSIT);
						                if (!path.exists()) {
						                    boolean status = path.mkdirs();
						                }
						                File uploadedFile = new File(path + this.slash + fileName);
						                System.out.print(path + this.slash + fileName);
						                boolean sqlFlag = ddl.submitDeposit(deadl , Isep.ROOT_FILE_SYS_DEPOSIT + fileName);
						                if(sqlFlag){
						                	item.write(uploadedFile);
						                }
						                
			                		}
			                		
			                	}
			                }
			                
			            }
			        }
			        
			    } catch (FileUploadException e) {
			        e.printStackTrace();
			        p.setSuccess(false);
			        p.setSuccessMessage("une erreur est survenue lors du chargement du fichier.");
			    } catch (Exception e) {
			        e.printStackTrace();
			        p.setSuccess(false);
			        p.setSuccessMessage("une erreur est survenue lors du chargement du fichier.");
			    }
				
				JSONObject business = new JSONObject();
				business.put("success", p.getSuccess());
				business.put("message", p.getSuccessMessage());
				
				response.setHeader("content-type", "application/json");
				response.getWriter().write(business.toString());
				
			} else {
				
			}
			
			try {
				bddServletCo.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			p.setSuccess(false);
			p.setSuccessMessage("<strong>attention</strong>, une erreur est arrivée lors de l'envoi du fichier. "
					+ "Vérifiez que vous êtes bien connecté à l'application.");
			JSONObject business = new JSONObject();
			business.put("err", true);
			business.put("success", p.getSuccess());
			business.put("message", p.getSuccessMessage());
			
			response.setHeader("content-type", "application/json");
			response.getWriter().write(business.toString());
		}
	}

}
