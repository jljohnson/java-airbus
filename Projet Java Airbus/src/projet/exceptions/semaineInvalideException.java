package projet.exceptions;

public class semaineInvalideException extends Exception{
	public semaineInvalideException() {
		super("La semaine ne peut pas être nulle");
	}
}	
