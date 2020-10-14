package rpi.inference.logic;

import java.util.Collections;
import java.util.List;

/**
 * ConjunctiveGeneralForm is a general conjunction of expressions.
 */
public class ConjunctiveGeneralForm extends GeneralForm implements ConjunctiveExpression {

    /**
     * Construct an empty conjunctive general expression.
     */
    public ConjunctiveGeneralForm() {
        super(Collections.emptyList());
    }

    /**
     * Construct a conjunctive general expression from a list of expressions.
     *
     * @param expressions List of expressions that could be null or empty.
     */
    public ConjunctiveGeneralForm(List<? extends Expression> expressions) {
        super(expressions);
    }

    /**
     * Construct a conjunctive general expression from an array of expressions.
     *
     * @param expressions Array of expressions that could be null or empty.
     */
    public ConjunctiveGeneralForm(Expression... expressions) {
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
        //		Expression hidden = null;
        //		if (this.expressions.size() == 0) {
        //			hidden = null;
        //		} else if (this.expressions.size() == 1) {
        //			hidden = this.expressions.get(0);
        //			if (hidden != null) {
        //				hidden = hidden.getHiddenExpression();
        //			}
        //		} else {
        //			boolean allLiterals = true;
        //			boolean allClauses = false;
        //			boolean allNFs = false;
        //			boolean allConjunctions = true;
        //			boolean allDisjunctions = true;
        //			List<Expression> subHiddens = new ArrayList<Expression>();
        //			for (Expression expr : this.expressions) {
        //				Expression subHidden = expr.getHiddenExpression();
        //				subHiddens.add(subHidden);
        //				if (allLiterals) {
        //					if (subHidden.isClause()) {
        //						allLiterals = false;
        //						allClauses = true;
        //					} else if (subHidden.isNormalForm()) {
        //						allLiterals = false;
        //						allNFs = true;
        //					} else if (subHidden.isGeneralForm()) {
        //						allLiterals = false;
        //					}
        //				} else if (allClauses) {
        //					if (subHidden.isNormalForm()) {
        //						allClauses = false;
        //						allNFs = true;
        //					} else if (subHidden.isGeneralForm()) {
        //						allClauses = false;
        //					}
        //				} else if (allNFs) {
        //					if (subHidden.isGeneralForm()) {
        //						allNFs = false;
        //					}
        //				}
        //				if (subHidden.isLiteral() == false) {
        //					if (allConjunctions && subHidden.isDisjunction()) {
        //						allConjunctions = false;
        //					}
        //					if (allDisjunctions && subHidden.isConjunction()) {
        //						allDisjunctions = false;
        //					}
        //				}
        //			}
        //			if (allLiterals) {
        //				List<Literal> literals = new ArrayList<Literal>(subHiddens.size());
        //				for (Expression subHidden : subHiddens) {
        //					literals.add((Literal) subHidden);
        //				}
        //				hidden = new Conjunction(literals);
        //			} else if (allClauses) {
        //				if (allConjunctions) {
        //					List<Literal> literals = new ArrayList<Literal>();
        //					Conjunction conjunction = null;
        //					for (Expression subHidden : subHiddens) {
        //						if (subHidden.isLiteral()) {
        //							literals.add((Literal) subHidden);
        //						} else {
        //							conjunction = (Conjunction) subHidden;
        //							for (Literal literal : conjunction.getLiterals()) {
        //								literals.add(literal);
        //							}
        //						}
        //					}
        //					hidden = new Conjunction(literals);
        //				} else if (allDisjunctions) {
        //					List<Disjunction> clauses = new ArrayList<Disjunction>(subHiddens.size());
        //					for (Expression subHidden : subHiddens) {
        //						clauses.add((Disjunction) subHidden);
        //					}
        //					hidden = new ConjunctiveNormalForm(clauses);
        //				} else {
        //					hidden = new ConjunctiveGeneralForm(subHiddens);
        //				}
        //			} else if (allNFs) {
        //				// TBD
        //			} else {
        //				// TBD
        //			}
        //		}
        //		return hidden;
    }

    @Override
    public String toString() {
        return "AND " + this.getExpressions();
    }

}
