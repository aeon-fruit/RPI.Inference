package rpi.inference.logic;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static rpi.inference.logic.uil.Asserter.assertConjunctiveNormalForm;
import static rpi.inference.logic.uil.Randomizer.*;

class ConjunctiveNormalFormTest {

    @Test
    void constructor_Default() {
        ConjunctiveNormalForm cnf = new ConjunctiveNormalForm();

        assertConjunctiveNormalForm(cnf);

        assertIterableEquals(cnf.getClauses(), cnf.getSubExpressions());
        assertEquals(0, cnf.getSubExpressions().size());
    }

    @Test
    void constructor_ListClauses() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            List<Disjunction> clauses = randomListDisjunctions();
            ConjunctiveNormalForm cnf = new ConjunctiveNormalForm(clauses);

            assertConjunctiveNormalForm(cnf);

            assertEquals(clauses == null ? 0 : clauses.size(), cnf.getClauses().size());
            assertIterableEquals(clauses == null ? Collections.emptyList() : clauses, cnf.getClauses());
            assertIterableEquals(cnf.getClauses(), cnf.getSubExpressions());
        }
    }

    @Test
    void constructor_ArrayClauses() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            Disjunction[] clauses = randomArrayDisjunctions();
            ConjunctiveNormalForm cnf = new ConjunctiveNormalForm(clauses);

            assertConjunctiveNormalForm(cnf);

            assertEquals(clauses == null ? 0 : clauses.length, cnf.getClauses().size());
            assertIterableEquals(clauses == null ? Collections.emptyList() : Arrays.asList(clauses), cnf.getClauses());
            assertIterableEquals(cnf.getClauses(), cnf.getSubExpressions());
        }
    }

    @Test
    void setClauses() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            List<Disjunction> clauses = randomListDisjunctions();
            ConjunctiveNormalForm cnf = new ConjunctiveNormalForm(clauses);

            clauses = randomListDisjunctions();
            cnf.setClauses(clauses);

            assertConjunctiveNormalForm(cnf);

            assertEquals(clauses == null ? 0 : clauses.size(), cnf.getClauses().size());
            assertIterableEquals(clauses == null ? Collections.emptyList() : clauses, cnf.getClauses());
            assertIterableEquals(cnf.getClauses(), cnf.getSubExpressions());
        }
    }

    @Test
    void toDNF_Empty() {
        // empty - clauses empty
        ConjunctiveNormalForm cnf = new ConjunctiveNormalForm();

        DisjunctiveNormalForm dnf = cnf.toDNF();

        assertNotNull(dnf);
        assertNotNull(dnf.getClauses());
        ConjunctiveNormalForm reverseCnf = dnf.toCNF();
        assertNotNull(reverseCnf);
        assertNotNull(reverseCnf.getClauses());

        assertEquals(0, dnf.getClauses().size());
        assertEquals(0, reverseCnf.getClauses().size());
    }

    @Test
    void toDNF_OneClauseOneLiteral() {
        // one clause - one literal
        List<Disjunction> clauses = new ArrayList<>();
        clauses.add(new Disjunction(randomLiteral(true)));
        ConjunctiveNormalForm cnf = new ConjunctiveNormalForm(clauses);

        DisjunctiveNormalForm dnf = cnf.toDNF();

        assertNotNull(dnf);
        assertNotNull(dnf.getClauses());
        ConjunctiveNormalForm reverseCnf = dnf.toCNF();
        assertNotNull(reverseCnf);
        assertNotNull(reverseCnf.getClauses());

        assertEquals(1, dnf.getClauses().size());
        assertEquals(1, reverseCnf.getClauses().size());
        assertIterableEquals(cnf.getClauses().get(0).getLiterals(), dnf.getClauses().get(0).getLiterals());
        assertIterableEquals(cnf.getClauses().get(0).getLiterals(), reverseCnf.getClauses().get(0).getLiterals());
    }

    @Test
    void toDNF_OneClauseMultiLiteral() {
        // one clause - multiple literals
        for (int it = 0; it < ITERATIONS_COUNT; ++it) {
            List<Disjunction> clauses = new ArrayList<>();
            clauses.add(new Disjunction(randomListLiterals(true)));
            ConjunctiveNormalForm cnf = new ConjunctiveNormalForm(clauses);

            DisjunctiveNormalForm dnf = cnf.toDNF();

            assertNotNull(dnf);
            assertNotNull(dnf.getClauses());
            ConjunctiveNormalForm reverseCnf = dnf.toCNF();
            assertNotNull(reverseCnf);
            assertNotNull(reverseCnf.getClauses());

            int count = clauses.get(0).getLiterals().size();
            assertEquals(count, dnf.getClauses().size());
            assertEquals(1, reverseCnf.getClauses().size());
            for (int i = 0; i < count; ++i) {
                assertEquals(1, dnf.getClauses().get(i).getLiterals().size());
                assertEquals(clauses.get(0).getLiterals().get(i), dnf.getClauses().get(i).getLiterals().get(0));
            }
            assertIterableEquals(clauses.get(0).getLiterals(), reverseCnf.getClauses().get(0).getLiterals());
        }
    }

    @Test
    void toDNF_MultiClauseOneLiteral() {
        // multiple clauses - one literal
        for (int it = 0; it < ITERATIONS_COUNT; ++it) {
            List<Disjunction> clauses = new ArrayList<>();
            int count = randomCount() + 2;
            for (int i = 0; i < count; ++i) {
                clauses.add(new Disjunction(randomLiteral(true)));
            }
            ConjunctiveNormalForm cnf = new ConjunctiveNormalForm(clauses);

            DisjunctiveNormalForm dnf = cnf.toDNF();

            assertNotNull(dnf);
            assertNotNull(dnf.getClauses());
            ConjunctiveNormalForm reverseCnf = dnf.toCNF();
            assertNotNull(reverseCnf);
            assertNotNull(reverseCnf.getClauses());

            assertEquals(1, dnf.getClauses().size());
            assertEquals(clauses.size(), dnf.getClauses().get(0).getLiterals().size());
            assertEquals(clauses.size(), reverseCnf.getClauses().size());

            for (int i = 0; i < count; ++i) {
                assertEquals(clauses.get(i).getLiterals().get(0), dnf.getClauses().get(0).getLiterals().get(i));
                assertIterableEquals(clauses.get(i).getLiterals(), reverseCnf.getClauses().get(i).getLiterals());
            }
        }
    }

    @Test
    void toDNF_MultiClauseMultiLiteral() {
        // multiple clauses - multiple literals
        for (int it = 0; it < ITERATIONS_COUNT; ++it) {
            List<Disjunction> clauses = new ArrayList<>();
            int count = randomCount() % 2 + 5;
            int size = 1;
            for (int i = 0; i < count; ++i) {
                List<Literal> literals = Optional.ofNullable(randomListLiterals(true)).orElse(Collections.emptyList());
                literals = literals.subList(0, Math.min(literals.size(), 5));
                clauses.add(new Disjunction(literals));
                size *= literals.size();
            }
            ConjunctiveNormalForm cnf = new ConjunctiveNormalForm(clauses);

            DisjunctiveNormalForm dnf = cnf.toDNF();

            assertNotNull(dnf);
            assertNotNull(dnf.getClauses());
            // TODO - uncomment reverseCnf-related lines once the performance is optimized
            //ConjunctiveNormalForm reverseCnf = dnf.toCNF();
            //assertNotNull(reverseCnf);
            //assertNotNull(reverseCnf.getClauses());

            assertEquals(size, dnf.getClauses().size());
            //assertEquals(clauses.size(), reverseCnf.getClauses().size());

            int dnfSize = dnf.getClauses().size();
            for (int i = 0; i < count; ++i) {
                Disjunction clause = clauses.get(i);
                for (int j = 0; j < dnfSize; ++j) {
                    Conjunction dnfClause = dnf.getClauses().get(j);
                    assertEquals(clauses.size(), dnfClause.getLiterals().size());
                    assertTrue(clause.getLiterals().contains(dnfClause.getLiterals().get(i)));
                    //assertIterableEquals(clauses.get(i).getLiterals(), reverseCnf.getClauses().get(i).getLiterals());
                }
            }
        }
    }

    @Test
    void toDNF_MultiClauseMixedCountLiteral() {
        // multiple clauses - mixed-count literals
        for (int it = 0; it < ITERATIONS_COUNT; ++it) {
            List<Disjunction> clauses = new ArrayList<>();
            int count = randomCount() % 2 + 5;
            int size = 1;
            for (int i = 0; i < count; ++i) {
                List<Literal> literals;
                if (randomCount() % 2 == 0) {
                    literals = Optional.ofNullable(randomListLiterals(true)).orElse(Collections.emptyList());
                    literals = literals.subList(0, Math.min(literals.size(), 5));
                } else {
                    literals = new ArrayList<>();
                    literals.add(randomLiteral(true));
                }
                clauses.add(new Disjunction(literals));
                size *= literals.size();
            }
            ConjunctiveNormalForm cnf = new ConjunctiveNormalForm(clauses);

            DisjunctiveNormalForm dnf = cnf.toDNF();

            assertNotNull(dnf);
            assertNotNull(dnf.getClauses());
            // TODO - uncomment reverseCnf-related lines once the performance is optimized
            //ConjunctiveNormalForm reverseCnf = dnf.toCNF();
            //assertNotNull(reverseCnf);
            //assertNotNull(reverseCnf.getClauses());

            assertEquals(size, dnf.getClauses().size());
            //assertEquals(clauses.size(), reverseCnf.getClauses().size());

            int dnfSize = dnf.getClauses().size();
            for (int i = 0; i < count; ++i) {
                Disjunction clause = clauses.get(i);
                for (int j = 0; j < dnfSize; ++j) {
                    Conjunction dnfClause = dnf.getClauses().get(j);
                    assertEquals(clauses.size(), dnfClause.getLiterals().size());
                    assertTrue(clause.getLiterals().contains(dnfClause.getLiterals().get(i)));
                    //assertIterableEquals(clauses.get(i).getLiterals(), reverseCnf.getClauses().get(i).getLiterals());
                }
            }
        }
    }

    @Test
    void toDNF_OneClauseEmpty() {
        // one clause - empty
        List<Disjunction> clauses = new ArrayList<>();
        clauses.add(new Disjunction());
        ConjunctiveNormalForm cnf = new ConjunctiveNormalForm(clauses);

        DisjunctiveNormalForm dnf = cnf.toDNF();

        assertNotNull(dnf);
        assertNotNull(dnf.getClauses());
        ConjunctiveNormalForm reverseCnf = dnf.toCNF();
        assertNotNull(reverseCnf);
        assertNotNull(reverseCnf.getClauses());

        assertEquals(0, dnf.getClauses().size());
        assertEquals(0, reverseCnf.getClauses().size());
    }

    @Test
    void toDNF_MultiClauseEmpty() {
        // multiple clauses - all empty clauses
        for (int it = 0; it < ITERATIONS_COUNT; ++it) {
            List<Disjunction> clauses = new ArrayList<>();
            int count = randomCount();
            for (int i = 0; i < count; ++i) {
                clauses.add(new Disjunction());
            }
            ConjunctiveNormalForm cnf = new ConjunctiveNormalForm(clauses);

            DisjunctiveNormalForm dnf = cnf.toDNF();

            assertNotNull(dnf);
            assertNotNull(dnf.getClauses());

            ConjunctiveNormalForm reverseCnf = dnf.toCNF();
            assertNotNull(reverseCnf);
            assertNotNull(reverseCnf.getClauses());

            assertEquals(0, dnf.getClauses().size());
            assertEquals(0, reverseCnf.getClauses().size());
        }
    }

    @Test
    void toDNF_MultiClauseOneLiteralOrEmpty() {
        // multiple clauses - compare (one literal + empty clauses, same stripped from empty clauses)
        for (int it = 0; it < ITERATIONS_COUNT; ++it) {
            List<Disjunction> clauses = new ArrayList<>();
            int count = randomCount();
            int nonEmptyCount = 0;
            for (int i = 0; i < count; ++i) {
                Literal literal = null;
                if (randomCount() % 2 == 0) {
                    literal = randomLiteral(true);
                    ++nonEmptyCount;
                }
                clauses.add(new Disjunction(literal));
            }
            ConjunctiveNormalForm cnf = new ConjunctiveNormalForm(clauses);

            DisjunctiveNormalForm dnf = cnf.toDNF();

            assertNotNull(dnf);
            assertNotNull(dnf.getClauses());
            ConjunctiveNormalForm reverseCnf = dnf.toCNF();
            assertNotNull(reverseCnf);
            assertNotNull(reverseCnf.getClauses());

            assertEquals(nonEmptyCount > 0 ? 1 : 0, dnf.getClauses().size());
            assertEquals(nonEmptyCount, reverseCnf.getClauses().size());
            if (nonEmptyCount > 0) {
                assertEquals(nonEmptyCount, dnf.getClauses().get(0).getLiterals().size());

                for (int i = 0, idx = 0; i < count; ++i) {
                    Disjunction clause = clauses.get(i);
                    if (clause.isEmpty()) {
                        continue;
                    }
                    assertEquals(clause.getLiterals().get(0), dnf.getClauses().get(0).getLiterals().get(idx));
                    assertIterableEquals(clause.getLiterals(), reverseCnf.getClauses().get(idx).getLiterals());
                    ++idx;
                }
            }
        }
    }

    @Test
    void toDNF_MultiClauseMultiLiteralOrEmpty() {
        // multiple clauses - compare (multiple literal + empty clauses, same stripped from empty clauses)
        for (int it = 0; it < ITERATIONS_COUNT; ++it) {
            List<Disjunction> clauses = new ArrayList<>();
            int count = randomCount() % 2 + 5;
            int size = 1;
            boolean allEmpty = true;
            for (int i = 0; i < count; ++i) {
                List<Literal> literals = Optional.ofNullable(randomListLiterals()).orElse(Collections.emptyList());
                literals = literals.subList(0, Math.min(literals.size(), 5));
                Disjunction disjunction = new Disjunction(literals);
                clauses.add(disjunction);
                allEmpty = allEmpty && disjunction.isEmpty();
                size *= Math.max(1, disjunction.getLiterals().size());
            }
            ConjunctiveNormalForm cnf = new ConjunctiveNormalForm(clauses);

            DisjunctiveNormalForm dnf = cnf.toDNF();

            assertNotNull(dnf);
            assertNotNull(dnf.getClauses());
            // TODO - uncomment reverseCnf-related lines once the performance is optimized
            //ConjunctiveNormalForm reverseCnf = dnf.toCNF();
            //assertNotNull(reverseCnf);
            //assertNotNull(reverseCnf.getClauses());

            assertEquals(allEmpty ? 0 : size, dnf.getClauses().size());
            //assertEquals(clauses.size(), reverseCnf.getClauses().size());

            int dnfSize = dnf.getClauses().size();
            long nonEmptyCount = clauses.size() - clauses.stream().filter(Clause::isEmpty).count();
            for (int i = 0, idx = 0; i < count; ++i) {
                Disjunction clause = clauses.get(i);
                if (clause.isEmpty()) {
                    continue;
                }
                for (int j = 0; j < dnfSize; ++j) {
                    Conjunction dnfClause = dnf.getClauses().get(j);
                    assertEquals(nonEmptyCount, dnfClause.getLiterals().size());
                    assertTrue(clause.getLiterals().contains(dnfClause.getLiterals().get(idx)));
                    //assertIterableEquals(clauses.get(i).getLiterals(), reverseCnf.getClauses().get(i).getLiterals());
                }
                ++idx;
            }
        }
    }

    @Test
    void toDNF_MultiClauseMixedCountLiteralOrEmpty() {
        // multiple clauses - compare (mixed-count literal + empty clauses, same stripped from empty clauses)
        for (int it = 0; it < ITERATIONS_COUNT; ++it) {
            List<Disjunction> clauses = new ArrayList<>();
            int count = randomCount() % 2 + 5;
            int size = 1;
            for (int i = 0; i < count; ++i) {
                List<Literal> literals;
                if (randomCount() % 2 == 0) {
                    literals = Optional.ofNullable(randomListLiterals()).orElse(Collections.emptyList());
                    literals = literals.subList(0, Math.min(literals.size(), 5));
                } else {
                    literals = new ArrayList<>();
                    literals.add(randomLiteral(true));
                }
                clauses.add(new Disjunction(literals));
                size *= Math.max(1, literals.size());
            }
            ConjunctiveNormalForm cnf = new ConjunctiveNormalForm(clauses);

            DisjunctiveNormalForm dnf = cnf.toDNF();

            assertNotNull(dnf);
            assertNotNull(dnf.getClauses());
            // TODO - uncomment reverseCnf-related lines once the performance is optimized
            //ConjunctiveNormalForm reverseCnf = dnf.toCNF();
            //assertNotNull(reverseCnf);
            //assertNotNull(reverseCnf.getClauses());

            assertEquals(size, dnf.getClauses().size());
            //assertEquals(clauses.size(), reverseCnf.getClauses().size());

            int dnfSize = dnf.getClauses().size();
            long nonEmptyCount = clauses.size() - clauses.stream().filter(Clause::isEmpty).count();
            for (int i = 0, idx = 0; i < count; ++i) {
                Disjunction clause = clauses.get(i);
                if (clause.isEmpty()) {
                    continue;
                }
                for (int j = 0; j < dnfSize; ++j) {
                    Conjunction dnfClause = dnf.getClauses().get(j);
                    assertEquals(nonEmptyCount, dnfClause.getLiterals().size());
                    assertTrue(clause.getLiterals().contains(dnfClause.getLiterals().get(idx)));
                    //assertIterableEquals(clauses.get(i).getLiterals(), reverseCnf.getClauses().get(i).getLiterals());
                }
                ++idx;
            }
        }
    }

    @Test
    void toString_test() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            List<Disjunction> clauses = randomListDisjunctions();
            ConjunctiveNormalForm cnf = new ConjunctiveNormalForm(clauses);

            assertConjunctiveNormalForm(cnf);

            String str = cnf.toString();

            assertEquals("AND " + cnf.getClauses(), str);
        }
    }

}