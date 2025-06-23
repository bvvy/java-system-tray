package bvvy.system_tray;

public class JSystemTrayException extends RuntimeException {
    public JSystemTrayException(String message) {
        super(message);
    }

    public JSystemTrayException(String message, Throwable cause) {
        super(message, cause);
    }

    public JSystemTrayException(Throwable cause) {
        super(cause);
    }
}
