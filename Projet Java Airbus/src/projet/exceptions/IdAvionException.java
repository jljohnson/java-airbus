package projet.exceptions;

public class IdAvionException extends Exception {
	public IdAvionException(String id) {
		super("Id avion non trouv� : " + id);
	}
}
