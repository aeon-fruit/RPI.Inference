package rpi.inference.logic;

import java.util.List;

/**
 * Atom is an expression of a single positive atomic expression with an arity >= 0.
 */
public class Atom extends Literal {

    /**
     * Construct an empty atom without a symbol.
     */
    public Atom() {
        super();
    }

    /**
     * Construct an atom with a symbol and a list of terms.
     *
     * @param symbol Symbol of the atom that could be null or empty.
     * @param terms  List of terms of the predicate that could be null or empty.
     */
    public Atom(String symbol, List<String> terms) {
        super(symbol, terms);
    }

    /**
     * Construct an atom with a symbol and an array of terms.
     *
     * @param symbol Symbol of the atom that could be null or empty.
     * @param terms  Array of terms of the predicate that could be null or empty.
     */
    public Atom(String symbol, String[] terms) {
        super(symbol, terms);
    }

    @Override
    public boolean isPositive() {
        return true;
    }

    @Override
    public Literal getAntiLiteral() {
        return new NegativeAtom(this.symbol, this.terms);
    }

}
