package rpi.inference.logic;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * NormalForm is an expression in a "super-clausal" form of a disjunction of conjunctive clauses or the opposite.
 */
public abstract class NormalForm implements Expression {

    private List<? extends Clause> clauses;

    /**
     * Construct an empty normal form.
     */
    public NormalForm() {
        this(Collections.emptyList());
    }

    /**
     * Construct a normal form from a list of clauses.
     *
     * @param clauses List of clauses that could be null or empty.
     */
    public NormalForm(List<? extends Clause> clauses) {
        super();
        setClauses(clauses);
    }

    /**
     * Get the list of clauses composing this normal form.
     *
     * @return List of clauses composing this normal form.
     */
    public List<? extends Clause> getClauses() {
        return clauses;
    }

    /**
     * Set the list of clauses composing this normal form.
     *
     * @param clauses List of clauses that could be null or empty.
     */
    public void setClauses(List<? extends Clause> clauses) {
        if (clauses == null) {
            this.clauses = Collections.emptyList();
        } else {
            this.clauses = clauses.stream()
                    .filter(Objects::nonNull)
                    .filter(e -> !e.isEmpty())
                    .collect(Collectors.toList());
        }
    }

    @Override
    public boolean isEmpty() {
        return clauses.isEmpty();
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
        return true;
    }

    @Override
    public boolean isGeneralForm() {
        return false;
    }

}
