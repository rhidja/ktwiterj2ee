package ktwtr.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.avaje.ebean.Ebean;

import ktwtr.models.Member;
import ktwtr.models.Post;

public final class PostForm {
    private static final String CHAMP_TITLE  = "title";
    private static final String CHAMP_CONTENT   = "content";

    private String result;
    private Map<String, String> errors = new HashMap<String, String>();

    public String getResult() {
        return result;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public Post createPost( HttpServletRequest request ) {

        String title = getFieldValue( request, CHAMP_TITLE );
        String content = getFieldValue( request, CHAMP_CONTENT );
        
        Post post = new Post();
        
        HttpSession session = request.getSession();
        Member member = (Member)session.getAttribute("memberSession");
    	post.setAutor(member);
    	
        try {
            validationTitle( title );
        } catch ( Exception e ) {
        	setError( CHAMP_TITLE, e.getMessage() );
        }
        post.setTitle( title );

        try {
            validationContent( content );
        } catch ( Exception e ) {
        	setError( CHAMP_CONTENT, e.getMessage() );
        }
        post.setContent( content );
             
        if ( errors.isEmpty() ) {
            result = "Succès de la connexion.";
            Ebean.save(post);
        } else {
        	result = "Échec de la connexion.";
        }
        
        return post;
    }

    private void validationTitle( String title ) throws Exception {
        if ( title != null ) {
            if ( title.length() < 3 ) {
                throw new Exception( "Le titre doit contenir au moins 3 caractères." );
            }
        }else {
            throw new Exception( "Merci de saisir le titre du post." );
        }
    }

    private void validationContent( String content ) throws Exception {
        if ( content != null ) {
            if ( content.length() < 3 ) {
                throw new Exception( "Le contenu doit contenir au moins 10 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir votre le contenu du post." );
        }
    }

    private void setError( String field, String message ) {
        errors.put( field, message );
    }

    private static String getFieldValue( HttpServletRequest request, String fieldName ) {
        String value = request.getParameter( fieldName );
        if ( value == null || value.trim().length() == 0 ) {
            return null;
        } else {
            return value;
        }
    }
}