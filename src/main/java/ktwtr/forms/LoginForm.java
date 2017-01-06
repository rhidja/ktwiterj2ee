package ktwtr.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;

import com.avaje.ebean.Ebean;

import ktwtr.models.Member;

public final class LoginForm {
    private static final String CHAMP_EMAIL  = "email";
    private static final String CHAMP_PASS   = "password";

    private String result;
    private Map<String, String> errors = new HashMap<String, String>();

    public String getResult() {
        return result;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public Member loginMember( HttpServletRequest request ) {

        String email = getFieldValue( request, CHAMP_EMAIL );
        String password = getFieldValue( request, CHAMP_PASS );

        try {
            validationEmail( email );
        } catch ( Exception e ) {
        	setError( CHAMP_EMAIL, e.getMessage() );
        }

        try {
            validationPassword( password );
        } catch ( Exception e ) {
        	setError( CHAMP_PASS, e.getMessage() );
        }       
        
        Member member = null;
        
        if ( errors.isEmpty() ) {
        	
            member = Ebean.find(Member.class).where().eq("email", email).findUnique();
            
            // gensalt's log_rounds parameter determines the complexity
            // the work factor is 2**log_rounds, and the default is 10
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));

            if (BCrypt.checkpw(member.getPassword(), hashedPassword))
            	result = "Succès de la connexion.";
            else
            	result = "Échec de la connexion.";
        } else {
        	result = "Échec de la connexion.";
        }

        return member;
    }

    private void validationEmail( String email ) throws Exception {
        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
            throw new Exception( "Merci de saisir une adresse mail valide." );
        }
    }

    private void validationPassword( String password ) throws Exception {
        if ( password != null ) {
            if ( password.length() < 3 ) {
                throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir votre mot de passe." );
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