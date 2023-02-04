package lyc.compiler.factories;

import lyc.compiler.Lexer;

import java.io.Reader;
import java.io.StringReader;

public final class LexerFactory {

    private LexerFactory() {}

    public static Lexer create(String input) {
        Reader reader = new StringReader(input);
        return create(reader);
    }

    public static Lexer create(Reader reader) {
        return new Lexer(reader);
    }

}
