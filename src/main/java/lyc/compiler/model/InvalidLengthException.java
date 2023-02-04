package lyc.compiler.model;

import java.io.Serial;

public class InvalidLengthException extends CompilerException {

    @Serial
    private static final long serialVersionUID = -6649278000190971816L;

    public InvalidLengthException(String message) {
        super(message);
    }

}
