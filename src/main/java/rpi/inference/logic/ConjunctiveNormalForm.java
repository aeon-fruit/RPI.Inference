package rpi.inference.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ConjunctiveNormalForm is an expression of a conjunction of disjunctive clauses.
 */
public class ConjunctiveNormalForm extends NormalForm implements ConjunctiveExpression {

    /**
     * Construct an empty conjunctive normal form.
     */
    public ConjunctiveNormalForm() {
        super(Collections.emptyList());
    }

    /**
     * Construct a conjunctive normal form from a list of disjunctive clauses.
     *
     * @param clauses List of disjunctive clauses that could be null or empty.
     */
    public ConjunctiveNormalForm(List<Disjunction> clauses) {
        super(clauses);
    }

    /**
     * Construct a conjunctive normal form from an array of disjunctive clauses.
     *
     * @param clauses Array of disjunctive clauses that could be null or empty.
     */
    public ConjunctiveNormalForm(Disjunction... clauses) {
        super();
        if (clauses == null || clauses.length == 0) {
            setClauses(Collections.emptyList());
        } else {
            List<Disjunction> tmpClauses = new ArrayList<>();
            Collections.addAll(tmpClauses, clauses);
            setClauses(tmpClauses);
        }
    }

    /**
     * Transform this CNF to a DNF.
     *
     * @return Disjunctive normal form of this expression.
     */
    public DisjunctiveNormalForm toDNF() {
        return this.toDNFLame();
    }

    /**
     * Transform this CNF to a DNF by simple distribution of conjunctions over disjunctions.
     *
     * @return Sub-optimized disjunctive normal form of this expression.
     */
    public DisjunctiveNormalForm toDNFLame() {
        List<? extends Clause> clauses = this.getClauses();
        if (clauses.size() == 1) {
            // transform the single disjunctive clause to a disjunction of one-literal conjunctive clauses
            return new DisjunctiveNormalForm(
                    clauses.get(0).getLiterals().stream()
                            .map(Conjunction::new)
                            .collect(Collectors.toList()));
        }

        Iterator<? extends Clause> iterator = clauses.iterator();
        Disjunction firstDisjunction = (Disjunction) iterator.next();
        while (firstDisjunction.getLiterals().isEmpty() && iterator.hasNext()) {
            // skip empty disjunctions
            firstDisjunction = (Disjunction) iterator.next();
        }
        if (!iterator.hasNext()) {
            // empty expression
            return new DisjunctiveNormalForm();
        }
        Disjunction secondDisjunction = (Disjunction) iterator.next();
        while (secondDisjunction.getLiterals().isEmpty() && iterator.hasNext()) {
            // skip empty disjunctions
            secondDisjunction = (Disjunction) iterator.next();
        }
        if (!iterator.hasNext()) {
            // single conjunctive clause
            return new DisjunctiveNormalForm(
                    firstDisjunction.getLiterals().stream()
                            .map(Conjunction::new)
                            .collect(Collectors.toList()));
        }

        List<Conjunction> conjunctiveClauses = new ArrayList<>();
        // distribute first over second
        for (Literal literalFirst : firstDisjunction.getLiterals()) {
            for (Literal literalSecond : secondDisjunction.getLiterals()) {
                conjunctiveClauses.add(new Conjunction(literalFirst, literalSecond));
            }
        }
        // distribute next disjunction over the result
        while (iterator.hasNext()) {
            Disjunction laterDisjunction = (Disjunction) iterator.next();
            if (!laterDisjunction.getLiterals().isEmpty()) {
                conjunctiveClauses = dist(conjunctiveClauses, laterDisjunction);
            }
        }

        return new DisjunctiveNormalForm(conjunctiveClauses);
    }

    private List<Conjunction> dist(List<Conjunction> clauses, Disjunction disjunction) {
        List<Conjunction> distribution = new ArrayList<>();
        List<Literal> literals;
        for (Conjunction conjunction : clauses) {
            for (Literal disjunctionLiteral : disjunction.getLiterals()) {
                literals = new ArrayList<>(conjunction.getLiterals());
                literals.add(disjunctionLiteral);
                distribution.add(new Conjunction(literals));
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
        return "AND " + this.getClauses();
    }

}
