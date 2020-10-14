package rpi.inference.logic;

/**
 * Expression is the representation of a logical expression.
 */
public interface Expression {

    /**
     * Check if this expression is positive.
     *
     * @return true if this expression is positive, false otherwise.
     */
    boolean isPositive();

    /**
     * Check if this expression is negative.
     *
     * @return true if this expression is negative, false otherwise.
     */
    default boolean isNegative() {
        return !isPositive();
    }

    /**
     * Check if this expression is a literal.
     *
     * @return true if this expression is a literal, false otherwise.
     */
    boolean isLiteral();

    /**
     * Check if this expression is a clause.
     *
     * @return true if this expression is a clause, false otherwise.
     */
    boolean isClause();

    /**
     * Check if this expression is in the normal form.
     *
     * @return true if this expression is in the normal form, false otherwise.
     */
    boolean isNormalForm();

    /**
     * Check if this expression is not a literal, a clause, nor in the normal form.
     *
     * @return true if this expression is not a literal, a clause, nor in the normal form, false otherwise.
     */
    boolean isGeneralForm();

    /**
     * Check if this expression is a disjunction of expressions.
     *
     * @return true if this expression is a disjunction of expressions, false otherwise.
     */
    boolean isDisjunction();

    /**
     * Check if this expression is a conjunction of expressions.
     *
     * @return true if this expression is a conjunction of expressions, false otherwise.
     */
    boolean isConjunction();

    /**
     * Get a simplified form of this expression.
     *
     * @return A simplified form of this expression.
     */
    Expression getHiddenExpression();

}
