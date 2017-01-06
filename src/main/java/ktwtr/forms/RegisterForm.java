package ktwtr.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;

import com.avaje.ebean.Ebean;

import ktwtr.models.Member;

public final class RegisterForm {
    private static final String CHAMP_EMAIL  = "email";
    private static final String CHAMP_PASS   = "password";
    private static final String CHAMP_CONF   = "confirmation";
    private static final String CHAMP_LOGIN    = "login";

    private String result;
    private Map<String, String> errors = new HashMap<String, String>();

    public String getResult() {
        return result;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
    
    public Member registerMember( HttpServletRequest request ) {
    	String login = getFieldValue( request, CHAMP_LOGIN );
        String email = getFieldValue( request, CHAMP_EMAIL );
        String password = getFieldValue( request, CHAMP_PASS );
        String confirmation = getFieldValue( request, CHAMP_CONF );

        Member member = new Member();

        try {
            emailValidation( email );
        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }
        
        member.setEmail( email );

        try {
            passwordValidation( password, confirmation );
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
            setErreur( CHAMP_CONF, null );
        }
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        member.setPassword( hashedPassword );

        try {
            loginValidation( login );
        } catch ( Exception e ) {
            setErreur( CHAMP_LOGIN, e.getMessage() );
        }
        member.setLogin( login );

        if ( errors.isEmpty() ) {
            result = "Succès de l'inscription.";
            Ebean.save(member);
        } else {
        	result = "Échec de l'inscription.";
        }

        return member;
    }

    private void emailValidation( String email ) throws Exception {
        if ( email != null ) {
            if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
                throw new Exception( "Merci de saisir une adresse mail valide." );
            }
        } else {
            throw new Exception( "Merci de saisir une adresse mail." );
        }
    }

    private void passwordValidation( String password, String confirmation ) throws Exception {
        if ( password != null && confirmation != null ) {
            if ( !password.equals( confirmation ) ) {
                throw new Exception( "Les mots de passe entrés sont différents, merci de les saisir à nouveau." );
            } else if ( password.length() < 5 ) {
                throw new Exception( "Les mots de passe doivent contenir au moins 3 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir et confirmer votre mot de passe." );
        }
    }

    private void loginValidation( String login ) throws Exception {
        if ( login != null && login.length() < 3 ) {
            throw new Exception( "Le login d'utilisateur doit contenir au moins 3 caractères." );
        }
    }

    private void setErreur( String field, String message ) {
        errors.put( field, message );
    }

    private static String getFieldValue( HttpServletRequest request, String fieldName ) {
        String value = request.getParameter( fieldName );
        if ( value == null || value.trim().length() == 0 ) {
            return null;
        } else {
            return value.trim();
        }
    }
}