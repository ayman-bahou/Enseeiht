package Interface_graphique;

public class CaseInvalideException extends RuntimeException {
    public CaseInvalideException() {
        super("Case invalide !");
    }

    public CaseInvalideException(String message) {
        super(message);
    }
}