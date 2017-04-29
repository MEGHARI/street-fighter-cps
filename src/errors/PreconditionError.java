package  errors;

public class PreconditionError extends Error {
	private static final long serialVersionUID = 9050050491786738283L;
	public PreconditionError(String message) {
		super(message);
	}
}
