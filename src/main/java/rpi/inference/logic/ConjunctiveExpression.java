package rpi.inference.logic;

import java.util.List;

/**
 * ConjunctiveExpression is the representation of a conjunctive logical expression.
 */
public interface ConjunctiveExpression extends Expression {

    /**
     * Get a list of the sub-expressions of this conjunctive expression.
     *
     * @return List of the sub-expressions of this expression.
     */
    List<? extends Expression> getSubExpressions();

    @Override
    default boolean isDisjunction() {
        return false;
    }

    @Override
    default boolean isConjunction() {
        return true;
    }

}
