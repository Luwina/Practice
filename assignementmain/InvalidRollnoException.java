package assignementmain;
public class InvalidRollnoException extends Exception {
	String str;
	public InvalidRollnoException(String str) {
		this.str = str;
	}
	public String getMessage(){
		return this.str;	
	}
}