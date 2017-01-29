package ktwtr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ktwtr.forms.UploadForm;
import ktwtr.models.Fichier;

/**
*
* @author rhidja
*/
public class Upload extends HttpServlet {
	
	public static final String CHEMIN = "chemin";
	
	public static final String ATT_FICHIER = "fichier";
	public static final String ATT_FORM    = "form";
	
	public static final String VUE = "/upload.jsp";

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
			
		String chemin = this.getServletConfig().getInitParameter( CHEMIN );

        UploadForm form = new UploadForm();

        Fichier fichier = form.enregistrerFichier( request, chemin );

        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_FICHIER, fichier );
	    
	    this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}	
}