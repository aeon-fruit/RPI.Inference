package rpi.inference.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DisjunctiveNormalForm is an expression of a disjunction of conjunctive clauses.
 */
public class DisjunctiveNormalForm extends NormalForm implements DisjunctiveExpression {

    /**
     * Construct an empty disjunctive normal form.
     */
    public DisjunctiveNormalForm() {
        super(Collections.emptyList());
    }

    /**
     * Construct a disjunctive normal form from a list of conjunctive clauses.
     *
     * @param clauses List of conjunctive clauses that could be null or empty.
     */
    public DisjunctiveNormalForm(List<Conjunction> clauses) {
        super(clauses);
    }

    /**
     * Construct a disjunctive normal form from an array of conjunctive clauses.
     *
     * @param clauses Array of conjunctive clauses that could be null or empty.
     */
    public DisjunctiveNormalForm(Conjunction... clauses) {
        super();
        if (clauses == null || clauses.length == 0) {
            setClauses(Collections.emptyList());
        } else {
            List<Conjunction> tmpClauses = new ArrayList<>();
            Collections.addAll(tmpClauses, clauses);
            setClauses(tmpClauses);
        }
    }

    /**
     * Transform this DNF to a CNF.
     *
     * @return Conjunctive normal form of this expression.
     */
    public ConjunctiveNormalForm toCNF() {
        return this.toCNFLame();
    }

    /**
     * Transform this DNF to a CNF by simple distribution of disjunctions over conjunctions.
     *
     * @return Sub-optimized conjunctive normal form of this expression.
     */
    public ConjunctiveNormalForm toCNFLame() {
        List<? extends Clause> clauses = this.getClauses();
        if (clauses.size() == 1) {
            // transform the single conjunctive clause to a conjunction of one-literal disjunctive clauses
            return new ConjunctiveNormalForm(
                    clauses.get(0).getLiterals().stream()
                            .map(Disjunction::new)
                            .collect(Collectors.toList()));
        }

        Iterator<? extends Clause> iterator = clauses.iterator();
        Conjunction firstConjunction = (Conjunction) iterator.next();
        while (firstConjunction.getLiterals().isEmpty() && iterator.hasNext()) {
            // skip empty conjunctions
            firstConjunction = (Conjunction) iterator.next();
        }
        if (!iterator.hasNext()) {
            // empty expression
            return new ConjunctiveNormalForm();
        }
        Conjunction secondConjunction = (Conjunction) iterator.next();
        while (secondConjunction.getLiterals().isEmpty() && iterator.hasNext()) {
            // skip empty conjunctions
            secondConjunction = (Conjunction) iterator.next();
        }
        if (!iterator.hasNext()) {
            // single conjunctive clause
            return new ConjunctiveNormalForm(
                    firstConjunction.getLiterals().stream()
                            .map(Disjunction::new)
                            .collect(Collectors.toList()));
        }

        List<Disjunction> disjunctiveClauses = new ArrayList<>();
        // distribute first over second
        for (Literal literalFirst : firstConjunction.getLiterals()) {
            for (Literal literalSecond : secondConjunction.getLiterals()) {
                disjunctiveClauses.add(new Disjunction(literalFirst, literalSecond));
            }
        }
        // distribute next conjunction over the result
        while (iterator.hasNext()) {
            Conjunction laterConjunction = (Conjunction) iterator.next();
            if (!laterConjunction.getLiterals().isEmpty()) {
                disjunctiveClauses = dist(disjunctiveClauses, laterConjunction);
            }
        }

        return new ConjunctiveNormalForm(disjunctiveClauses);
    }

    private List<Disjunction> dist(List<Disjunction> clauses, Conjunction conjunction) {
        List<Disjunction> distribution = new ArrayList<>();
        List<Literal> literals;
        for (Disjunction disjunction : clauses) {
            for (Literal conjunctionLiteral : conjunction.getLiterals()) {
                literals = new ArrayList<>(disjunction.getLiterals());
                literals.add(conjunctionLiteral);
                distribution.add(new Disjunction(literals));
            }
        }
        return distribution;
    }

    @Override
    public List<? extends Expression> getSubExpressions() {
        return this.getClauses();
    }

    @Override
    public Expression getHiddenExpression() {
        // TODO - in the next version
        return this;
    }

    @Override
    public String toString() {
        return "OR " + this.getClauses();
    }

}
