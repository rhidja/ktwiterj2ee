package ktwtr.forms;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.avaje.ebean.Ebean;

import ktwtr.models.Image;
import ktwtr.models.Member;
import ktwtr.models.Post;

public final class PostForm {
    private static final String CHAMP_TITLE = "title";
    private static final String CHAMP_CONTENT = "content";
    private static final String CHAMP_FILE = "file";
    
    private static final int TAILLE_TAMPON = 10240; // 10 ko

    private String result;
    private Map<String, String> errors = new HashMap<String, String>();

    public String getResult() {
        return result;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public Post createPost( HttpServletRequest request, String path ) {

        String title = getFieldValue( request, CHAMP_TITLE );
        String content = getFieldValue( request, CHAMP_CONTENT );
        
        Post post = new Post();
        Image image = new Image();
        
        HttpSession session = request.getSession();
        Member member = (Member)session.getAttribute("memberSession");
    	post.setAuthor(member);
    	
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
        
        post.setPostDate(new Date());
        
        String fileName = null;
        InputStream fileContent = null;
   
        try {
            Part part = request.getPart( CHAMP_FILE );

            fileName = getFileName( part );

            if ( fileName != null && !fileName.isEmpty() ) {

            	fileName = fileName.substring( fileName.lastIndexOf( '/' ) + 1 ).substring( fileName.lastIndexOf( '\\' ) + 1 );
            	fileContent = part.getInputStream();
            }
        } catch ( IllegalStateException e ) {

        	e.printStackTrace();
        	setError( CHAMP_FILE, "Les données envoyées sont trop volumineuses." );
        } catch ( IOException e ) {

            e.printStackTrace();
            setError( CHAMP_FILE, "Erreur de configuration du serveur." );
        } catch ( ServletException e ) {
        	
            e.printStackTrace();
            setError( CHAMP_FILE, "Ce type de requête n'est pas supporté, merci d'utiliser le formulaire prévu pour envoyer votre fichier." );
        }

        try {
            validationFile( fileName, fileContent );
        } catch ( Exception e ) {
            setError( CHAMP_FILE, e.getMessage() );
        }
        
        
        if ( errors.isEmpty() ) {
            try {
            	writeFile( fileContent, fileName, path );
            } catch ( Exception e ) {
                setError( CHAMP_FILE, "Erreur lors de l'écriture du fichier sur le disque." );
            }
        }
        image.setName( fileName );
        Ebean.save(image);
        post.setImage(image);
        
        if ( errors.isEmpty() ) {
            result = "Succes de la connexion.";
            Ebean.save(post);
        } else {
        	result = "Echec de la connexion.";
        }
        
        return post;
    }

    private static String getFileName( Part part ) {
        
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            
            if ( contentDisposition.trim().startsWith( "filename" ) ) {

                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        
        return null;
    }
    
    private void writeFile( InputStream content, String fileName, String path ) throws Exception {
        
        BufferedInputStream input = null;
        BufferedOutputStream output = null;
        try {
            
        	input = new BufferedInputStream( content, TAILLE_TAMPON );
            output = new BufferedOutputStream( new FileOutputStream( new File( path + fileName ) ), TAILLE_TAMPON );

            byte[] tampon = new byte[TAILLE_TAMPON];
            int length = 0;
            while ( ( length = input.read( tampon ) ) > 0 ) {
            	output.write( tampon, 0, length );
            }
        } finally {
            try {
            	output.close();
            } catch ( IOException ignore ) {
            	
            }
            
            try {
            	output.close();
            } catch ( IOException ignore ) {
            	
            }
        }
    }
    
    private void validationTitle( String title ) throws Exception {
        if ( title != null ) {
            if ( title.length() < 3 ) {
                throw new Exception( "Le titre doit contenir au moins 3 caractÃ¨res." );
            }
        }else {
            throw new Exception( "Merci de saisir le titre du post." );
        }
    }

    private void validationContent( String content ) throws Exception {
        if ( content != null ) {
            if ( content.length() < 3 ) {
                throw new Exception( "Le contenu doit contenir au moins 10 caractÃ¨res." );
            }
        } else {
            throw new Exception( "Merci de saisir votre le contenu du post." );
        }
    }

    private void validationFile( String fileName, InputStream fileContent ) throws Exception {
        if ( fileName == null || fileContent == null ) {
            throw new Exception( "Merci de sélectionner un fichier à envoyer." );
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