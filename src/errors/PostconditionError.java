package errors;

public class PostconditionError extends Error {
	private static final long serialVersionUID = 9050050491786738283L;
	public PostconditionError(String message) {
		super(message);
	}
}
