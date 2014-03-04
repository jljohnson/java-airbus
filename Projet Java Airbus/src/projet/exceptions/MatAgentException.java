package projet.exceptions;

public class MatAgentException extends Exception{
	public MatAgentException(String id) {
		super("Matricule agent non trouvé : " + id);
	}
}
