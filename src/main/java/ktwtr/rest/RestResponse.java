package ktwtr.rest;

public class RestResponse<Type> {

	public Boolean status;
	public String message;
	public Type objet;

	public RestResponse(Boolean status, String message, Type objet) {
		this.status = status;
		this.message = message;
		this.objet = objet;
	}
}