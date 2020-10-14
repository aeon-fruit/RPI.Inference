package rpi.inference.logic;

import java.util.List;

/**
 * DisjunctiveExpression is the representation of a disjunctive logical expression.
 */
public interface DisjunctiveExpression extends Expression {

    /**
     * Get a list of the sub-expressions of this disjunctive expression.
     *
     * @return List of the sub-expressions of this expression.
     */
    List<? extends Expression> getSubExpressions();

    @Override
    default boolean isDisjunction() {
        return true;
    }

    @Override
    default boolean isConjunction() {
        return false;
    }

}
