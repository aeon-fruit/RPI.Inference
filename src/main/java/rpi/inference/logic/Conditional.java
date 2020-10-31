package rpi.inference.logic;

/**
 * Conditional is a general conditional in the form of expr => expr.
 */
public class Conditional {

    private Expression antecedent = null;
    private Expression consequent = null;

    /**
     * Construct an empty conditional.
     */
    public Conditional() {
        super();
    }

    /**
     * Construct a conditional from 2 expressions.
     *
     * @param antecedent Antecedent expression that could be null.
     * @param consequent Consequent expression that could be null.
     */
    public Conditional(Expression antecedent, Expression consequent) {
        super();
        this.antecedent = antecedent;
        this.consequent = consequent;
    }

    /**
     * Get the antecedent from this conditional.
     *
     * @return Antecedent.
     */
    public Expression getAntecedent() {
        return antecedent;
    }

    /**
     * Set the antecedent in this conditional.
     *
     * @param antecedent Antecedent expression that could be null.
     */
    public void setAntecedent(Expression antecedent) {
        this.antecedent = antecedent;
    }

    /**
     * Get the consequent from this conditional.
     *
     * @return Consequent.
     */
    public Expression getConsequent() {
        return consequent;
    }

    /**
     * Set the consequent in this conditional.
     *
     * @param consequent Consequent expression that could be null.
     */
    public void setConsequent(Expression consequent) {
        this.consequent = consequent;
    }

    /**
     * Check if both the antecedent and the consequent in this conditional are non-null and non-empty.
     *
     * @return true if the antecedent and the consequent in this conditional are non-null and non-empty, false otherwise.
     */
    public boolean isValid() {
        return antecedent != null && !antecedent.isEmpty() && consequent != null && !consequent.isEmpty();
    }

    @Override
    public String toString() {
        String str = "";
        if ((antecedent != null) && !antecedent.isEmpty()) {
            str += antecedent;
        } else {
            str += "INVALID";
        }
        str += " => ";
        if ((consequent != null) && !consequent.isEmpty()) {
            str += consequent;
        } else {
            str += "INVALID";
        }
        return str;
    }

}
