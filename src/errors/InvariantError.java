package errors;

public class InvariantError extends Error {
	private static final long serialVersionUID = 9050050491786738283L;
	public InvariantError(String message) {
		super(message);
	}
}
