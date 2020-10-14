package rpi.inference.logic;

import java.util.Collections;
import java.util.List;

/**
 * DisjunctiveGeneralForm is a general disjunction of expressions.
 */
public class DisjunctiveGeneralForm extends GeneralForm implements DisjunctiveExpression {

    /**
     * Construct an empty disjunctive general expression.
     */
    public DisjunctiveGeneralForm() {
        super(Collections.emptyList());
    }

    /**
     * Construct a disjunctive general expression from a list of expressions.
     *
     * @param expressions List of expressions that could be null or empty.
     */
    public DisjunctiveGeneralForm(List<? extends Expression> expressions) {
        super(expressions);
    }

    /**
     * Construct a disjunctive general expression from an array of expressions.
     *
     * @param expressions Array of expressions that could be null or empty.
     */
    public DisjunctiveGeneralForm(Expression... expressions) {
        super(expressions);
    }

    @Override
    public List<? extends Expression> getSubExpressions() {
        return this.getExpressions();
    }

    @Override
    public Expression getHiddenExpression() {
        // TODO - in the next version
        return this;
    }

    @Override
    public String toString() {
        return "OR " + this.getExpressions();
    }

}
