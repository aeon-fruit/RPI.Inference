package rpi.inference.logic;

import java.util.List;

/**
 * Disjunction is an expression in the disjunctive clausal form.
 */
public class Disjunction extends Clause implements DisjunctiveExpression {

    /**
     * Construct an empty disjunction.
     */
    public Disjunction() {
        super();
    }

    /**
     * Construct a disjunction from a list of literals.
     *
     * @param literals List of literals that could be null or empty.
     */
    public Disjunction(List<Literal> literals) {
        super(literals);
    }

    /**
     * Construct a disjunction from an array of literals.
     *
     * @param literals Array of literals that could be null or empty.
     */
    public Disjunction(Literal... literals) {
        super(literals);
    }

    @Override
    public List<? extends Expression> getSubExpressions() {
        return this.getLiterals();
    }

    @Override
    public Expression getHiddenExpression() {
        // TODO - in the next version
        return this;
    }

    @Override
    public String toString() {
        return "OR " + this.getLiterals();
    }

}
