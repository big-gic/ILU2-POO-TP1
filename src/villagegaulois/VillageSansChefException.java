package villagegaulois;

public class VillageSansChefException extends Exception{
	//pour éviter un warning
	private static final long serialVersionUID = 1L;
	
	public VillageSansChefException(String message) {
		super(message);
	}
}
