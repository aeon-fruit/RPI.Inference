package rpi.inference.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Literal is an expression of a single positive or negative atomic expression with an arity >= 0.
 */
public abstract class Literal implements Expression {

    protected static final String POSITIVE = "posi_";
    protected static final String NEGATIVE = "nega_";

    protected String symbol = null;
    protected List<String> terms;

    /**
     * Construct an empty literal without a symbol.
     */
    public Literal() {
        super();
        this.terms = new ArrayList<>();
    }

    /**
     * Construct a literal with a symbol and a list of terms.
     *
     * @param symbol Symbol of the literal that could be null or empty.
     * @param terms  List of terms of the predicate that could be null or empty.
     */
    public Literal(String symbol, List<String> terms) {
        super();
        this.symbol = symbol;
        setTerms(terms);
    }

    /**
     * Construct a literal with a symbol and an array of terms.
     *
     * @param symbol Symbol of the literal that could be null or empty.
     * @param terms  Array of terms of the predicate that could be null or empty.
     */
    public Literal(String symbol, String[] terms) {
        super();
        this.symbol = symbol;
        setTerms(terms);
    }

    /**
     * Get the negation of this literal, which is a new atom having the same symbol and terms,
     * but negative if this literal is positive, and positive otherwise.
     *
     * @return Negation of this literal.
     */
    public abstract Literal getAntiLiteral();

    /**
     * Get the symbol of this literal.
     *
     * @return Symbol of this literal.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Set the symbol of this literal.
     *
     * @param symbol Atom symbol that could be null or empty.
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Get the terms of the predicate of this literal.
     *
     * @return Terms of the predicate of this literal.
     */
    public List<String> getTerms() {
        return terms;
    }

    /**
     * Set the list of terms of the predicate of this literal.
     *
     * @param terms List of terms that could be null or empty.
     */
    public void setTerms(List<String> terms) {
        if (terms != null) {
            this.terms = terms;
        } else {
            this.terms = new ArrayList<>();
        }
    }

    /**
     * Set the list of terms of the predicate of this literal from an array.
     *
     * @param terms Array of terms that could be null or empty.
     */
    public void setTerms(String[] terms) {
        this.terms = new ArrayList<>();
        if (terms != null) {
            Collections.addAll(this.terms, terms);
        }
    }

    @Override
    public boolean isLiteral() {
        return true;
    }

    @Override
    public boolean isClause() {
        return false;
    }

    @Override
    public boolean isNormalForm() {
        return false;
    }

    @Override
    public boolean isGeneralForm() {
        return false;
    }

    @Override
    public boolean isDisjunction() {
        return true;
    }

    @Override
    public boolean isConjunction() {
        return true;
    }

    @Override
    public Expression getHiddenExpression() {
        return this;
    }

    @Override
    public String toString() {
        if (this.symbol != null) {
            String prefix = isPositive() ? POSITIVE : NEGATIVE;
            return prefix + this.string();
        }
        return "_";
    }

    private String string() {
        if (this.terms.isEmpty()) {
            return symbol;
        }
        String termsStr = terms.toString();
        return symbol + "(" + termsStr.substring(1, termsStr.length() - 1) + ")";
    }

}
