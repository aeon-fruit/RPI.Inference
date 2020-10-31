package rpi.inference.logic;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Clause is an expression in the clausal form that is either a conjunction or a disjunction of literals.
 */
public abstract class Clause implements Expression {

    private List<Literal> literals;

    /**
     * Construct an empty clause.
     */
    public Clause() {
        this(Collections.emptyList());
    }

    /**
     * Construct a clause from a list of literals.
     *
     * @param literals List of literals that could be null or empty.
     */
    public Clause(List<Literal> literals) {
        super();
        setLiterals(literals);
    }

    /**
     * Construct a clause from an array of literals.
     *
     * @param literals Array of literals that could be null or empty.
     */
    public Clause(Literal[] literals) {
        super();
        setLiterals(literals);
    }

    /**
     * Get the list of literals composing this clause.
     *
     * @return List of literals composing this clause.
     */
    public List<Literal> getLiterals() {
        return literals;
    }

    /**
     * Set the list of literals composing this clause.
     *
     * @param literals List of literals that could be null or empty.
     */
    public void setLiterals(List<Literal> literals) {
        if (literals == null) {
            this.literals = Collections.emptyList();
        } else {
            this.literals = literals.stream()
                    .filter(Objects::nonNull)
                    .filter(e -> !e.isEmpty())
                    .collect(Collectors.toList());
        }
    }

    /**
     * Set the list of literals composing this clause from an array.
     *
     * @param literals Array of literals that could be null or empty.
     */
    public void setLiterals(Literal[] literals) {
        if (literals == null || literals.length == 0) {
            this.literals = Collections.emptyList();
        } else {
            this.literals = Arrays.stream(literals)
                    .filter(Objects::nonNull)
                    .filter(e -> !e.isEmpty())
                    .collect(Collectors.toList());
        }
    }

    @Override
    public boolean isEmpty() {
        return literals.isEmpty();
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
        return true;
    }

    @Override
    public boolean isNormalForm() {
        return false;
    }

    @Override
    public boolean isGeneralForm() {
        return false;
    }

}
