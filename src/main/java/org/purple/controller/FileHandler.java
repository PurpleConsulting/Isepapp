package org.purple.controller;

import java.io.File;
import java.io.IOException;
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
import org.purple.bean.Page;
import org.purple.bean.SubSkill;
import org.purple.constant.Isep;
import org.purple.model.Auth;

/**
 * Servlet implementation class FileHandler
 */
@WebServlet("/FileHandler")
public class FileHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String subjectFile = "subject_file";
    private static final String promoFile = "promo_file";
    private static final String backupFile = "backup_file";
   
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
    	File del_file = new File(folder +"/"+ name + "_OLDER" + ext);
    	File old_file = new File(folder +"/"+ fileName );
    	del_file.delete();
    	old_file.renameTo(new File(folder +"/"+ name + "_OLDER" + ext));
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.print("OKOK: GET");
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
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if(Auth.isRespo(request)){
			
			if(isMultipart){
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
			                	p.setSuccessMessage("<strong>Et voilà, </strong>le chargement du fichié à bien été effectué."
			                			+ " retrouvez le nouveau sujet sur la page <em><a href=\"Subject\">sujet</a><em>.");
			                } else if (field.equals(this.promoFile)) {
			                	fileName = Isep.FILE_PROMO;
			                	p.setSuccessMessage("<strong>Et voilà, </strong>les groupes du nouveau semestre "
			                			+ "ont été chargés avec success. Retrouvez dès maintenant la demi-promo sur "
			                			+ "la page <em><a href=\"Promo\">Classes</a><em>.");
			                } else if (field.equals(this.backupFile)) {
			                	
			                }
			                
			                String root = getServletContext().getRealPath("/");
			                File path = new File(root +"/"+ Isep.ROOT_FILE_SYS);
			                if (!path.exists()) {
			                    boolean status = path.mkdirs();
			                }
			                this.doPivot(root +"/"+ Isep.ROOT_FILE_SYS, fileName);
			                File uploadedFile = new File(path + "/" + fileName);
			                System.out.print(path + "/" + fileName);
			                item.write(uploadedFile);
			                
			                if (field.equals(this.promoFile)) {
			                	boolean parsingStatus = true;
			                	parsingStatus = Isep.csvParser(root +"/"+ Isep.ROOT_FILE_SYS , Isep.FILE_PROMO, ";");
			                	p.setSuccess(parsingStatus);
			                }
			                
			                
			            }
			        }
			    } catch (FileUploadException e) {
			        e.printStackTrace();
			        p.setSuccess(false);
			        p.setSuccessMessage("Une erreur est survenue lors du chargement du fichier.");
			    } catch (Exception e) {
			        e.printStackTrace();
			        p.setSuccess(false);
			        p.setSuccessMessage("Une erreur est survenue lors du chargement du fichier.");
			    }
				
				JSONObject business = new JSONObject();
				business.put("success", p.getSuccess());
				business.put("message", p.getSuccessMessage());
				
				response.setHeader("content-type", "application/json");
				response.getWriter().write(business.toString());
				
			} else {
				
			}
		} else {
			p.setSuccess(false);
			p.setSuccessMessage("<strong>Attention</strong>, une erreur est arrivée lors de l'envoie du fichier. "
					+ "Vérifiez que soyé bien conecté à l'application.");
			JSONObject business = new JSONObject();
			business.put("err", true);
			business.put("success", p.getSuccess());
			business.put("message", p.getSuccessMessage());
			
			response.setHeader("content-type", "application/json");
			response.getWriter().write(business.toString());
		}
	}

}
