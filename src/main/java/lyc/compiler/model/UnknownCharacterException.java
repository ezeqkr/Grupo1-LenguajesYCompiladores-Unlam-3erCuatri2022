package lyc.compiler.model;

import java.io.Serial;

public class UnknownCharacterException extends CompilerException {

  @Serial
  private static final long serialVersionUID = -8839023592847976068L;

  public UnknownCharacterException(String unknownInput) {
    super("Unknown character « " + unknownInput + " »");
  }
}
