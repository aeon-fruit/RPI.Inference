package rpi.inference.logic;

import java.util.List;

/**
 * Conjunction is an expression in the conjunctive clausal form.
 */
public class Conjunction extends Clause implements ConjunctiveExpression {

    /**
     * Construct an empty conjunction.
     */
    public Conjunction() {
        super();
    }

    /**
     * Construct a conjunction from a list of literals.
     *
     * @param literals List of literals that could be null or empty.
     */
    public Conjunction(List<Literal> literals) {
        super(literals);
    }

    /**
     * Construct a conjunction from an array of literals.
     *
     * @param literals Array of literals that could be null or empty.
     */
    public Conjunction(Literal... literals) {
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
        return "AND " + this.getLiterals();
    }

}
