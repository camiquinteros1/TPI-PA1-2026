package empleado.exception;

public class SalarioInferiorAlPisoException extends RuntimeException {
    public SalarioInferiorAlPisoException(String message) {
        super(message);
    }
}
