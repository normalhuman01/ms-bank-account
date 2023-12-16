package pe.com.project1.ms.domain.exception;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = -402019425453699807L;
    private static final String DESCRIPTION = "Not Found Exception";

    public NotFoundException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
