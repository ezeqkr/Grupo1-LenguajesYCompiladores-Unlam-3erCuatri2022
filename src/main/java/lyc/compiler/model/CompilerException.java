package lyc.compiler.model;

import java.io.Serial;

public abstract class CompilerException extends Exception {

    @Serial
    private static final long serialVersionUID = -3138875452688305726L;

    public CompilerException(String message) {
        super(message);
    }
}
