package rpi.inference.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * GeneralForm is a general expression in the form of a disjunction or a conjunction of expressions.
 */
public abstract class GeneralForm implements Expression {

    private List<? extends Expression> expressions;

    /**
     * Construct an empty general expression.
     */
    public GeneralForm() {
        this(Collections.emptyList());
    }

    /**
     * Construct a general expression from a list of expressions.
     *
     * @param expressions List of expressions that could be null or empty.
     */
    public GeneralForm(List<? extends Expression> expressions) {
        super();
        setExpressions(expressions);
    }

    /**
     * Construct a general expression from an array of expressions.
     *
     * @param expressions Array of expressions that could be null or empty.
     */
    public GeneralForm(Expression[] expressions) {
        super();
        if (expressions == null || expressions.length == 0) {
            setExpressions(Collections.emptyList());
        } else {
            List<Expression> tmpExpressions = new ArrayList<>();
            Collections.addAll(tmpExpressions, expressions);
            setExpressions(tmpExpressions);
        }
    }

    /**
     * Get the list of expressions composing this general expression.
     *
     * @return List of expressions composing this general expression.
     */
    public List<? extends Expression> getExpressions() {
        return expressions;
    }

    /**
     * Set the list of expressions composing this general expression.
     *
     * @param expressions List of clauses that could be null or empty.
     */
    public void setExpressions(List<? extends Expression> expressions) {
        if (expressions == null) {
            this.expressions = Collections.emptyList();
        } else {
            this.expressions = expressions;
        }
    }

    @Override
    public boolean isPositive() {
        return true;
    }

    @Override
    public boolean isLiteral() {
        return false;
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
        return true;
    }

}
