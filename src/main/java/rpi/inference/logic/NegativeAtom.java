package rpi.inference.logic;

import java.util.List;

/**
 * NegativeAtom is an expression of a single negative atomic expression with an arity >= 0.
 */
public class NegativeAtom extends Literal {

    /**
     * Construct an empty negative atom without a symbol.
     */
    public NegativeAtom() {
        super();
    }

    /**
     * Construct a negative atom with a symbol and a list of terms.
     *
     * @param symbol Symbol of the negative atom that could be null or empty.
     * @param terms  List of terms of the predicate that could be null or empty.
     */
    public NegativeAtom(String symbol, List<String> terms) {
        super(symbol, terms);
    }

    /**
     * Construct a negative atom with a symbol and an array of terms.
     *
     * @param symbol Symbol of the negative atom that could be null or empty.
     * @param terms  Array of terms of the predicate that could be null or empty.
     */
    public NegativeAtom(String symbol, String[] terms) {
        super(symbol, terms);
    }

    @Override
    public boolean isPositive() {
        return false;
    }

    @Override
    public Literal getAntiLiteral() {
        return new Atom(this.symbol, this.terms);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
