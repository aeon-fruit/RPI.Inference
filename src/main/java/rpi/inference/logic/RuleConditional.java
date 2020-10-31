package rpi.inference.logic;

/**
 * RuleConditional is a rule in the form of (literal|conjunctive-expr) => literal.
 */
public class RuleConditional {

    private Expression antecedent = null;
    private Literal consequent = null;

    /**
     * Construct an empty rule.
     */
    public RuleConditional() {
        super();
    }

    /**
     * Construct a rule from 2 literals.
     *
     * @param antecedent Antecedent literal that could be null.
     * @param consequent Consequent literal that could be null.
     */
    public RuleConditional(Literal antecedent, Literal consequent) {
        super();
        this.antecedent = antecedent;
        this.consequent = consequent;
    }

    /**
     * Construct a rule from a conjunctive expression and a literal.
     *
     * @param antecedent Antecedent conjunctive expression that could be null.
     * @param consequent Consequent literal that could be null.
     */
    public RuleConditional(ConjunctiveExpression antecedent, Literal consequent) {
        super();
        this.antecedent = antecedent;
        this.consequent = consequent;
    }

    /**
     * Get the antecedent from this rule.
     *
     * @return Antecedent.
     */
    public Expression getAntecedent() {
        return antecedent;
    }

    /**
     * Set a literal as the antecedent in this rule.
     *
     * @param antecedent Antecedent literal that could be null.
     */
    public void setAntecedent(Literal antecedent) {
        this.antecedent = antecedent;
    }

    /**
     * Set a conjunctive expression as the antecedent in this rule.
     *
     * @param antecedent Antecedent conjunctive expression that could be null.
     */
    public void setAntecedent(ConjunctiveExpression antecedent) {
        this.antecedent = antecedent;
    }

    /**
     * Get the consequent from the rule.
     *
     * @return Consequent.
     */
    public Literal getConsequent() {
        return consequent;
    }

    /**
     * Set the consequent in this rule.
     *
     * @param consequent Consequent that could be null.
     */
    public void setConsequent(Literal consequent) {
        this.consequent = consequent;
    }

    /**
     * Check if both the antecedent and the consequent in this rule are non-null and non-empty.
     *
     * @return true if the antecedent and the consequent in this rule are non-null and non-empty, false otherwise.
     */
    public boolean isValid() {
        return antecedent != null && !antecedent.isEmpty() && consequent != null && !consequent.isEmpty();
    }

    @Override
    public String toString() {
        String str = "";
        if ((antecedent == null) || antecedent.isEmpty()) {
            str += "INVALID";
        } else {
            str += antecedent;
        }
        str += " => ";
        if ((consequent == null) || consequent.isEmpty()) {
            str += "INVALID";
        } else {
            str += consequent;
        }
        return str;
    }

}
