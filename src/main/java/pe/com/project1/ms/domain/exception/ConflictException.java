package pe.com.project1.ms.domain.exception;

public class ConflictException extends RuntimeException {

    private static final long serialVersionUID = 4589236076816462966L;
    private static final String DESCRIPTION = "Conflict Exception";

    public ConflictException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
