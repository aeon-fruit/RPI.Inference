package rpi.inference.logic;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static rpi.inference.logic.uil.Asserter.assertDisjunctiveNormalForm;
import static rpi.inference.logic.uil.Randomizer.*;

class DisjunctiveNormalFormTest {

    @Test
    void constructor_Default() {
        DisjunctiveNormalForm dnf = new DisjunctiveNormalForm();

        assertDisjunctiveNormalForm(dnf);

        assertIterableEquals(dnf.getClauses(), dnf.getSubExpressions());
        assertEquals(0, dnf.getSubExpressions().size());
    }

    @Test
    void constructor_ListClauses() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            List<Conjunction> clauses = randomListConjunctions();
            DisjunctiveNormalForm dnf = new DisjunctiveNormalForm(clauses);

            assertDisjunctiveNormalForm(dnf);

            assertEquals(clauses == null ? 0 : clauses.size(), dnf.getClauses().size());
            assertIterableEquals(clauses == null ? Collections.emptyList() : clauses, dnf.getClauses());
            assertIterableEquals(dnf.getClauses(), dnf.getSubExpressions());
        }
    }

    @Test
    void constructor_ArrayClauses() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            Conjunction[] clauses = randomArrayConjunctions();
            DisjunctiveNormalForm dnf = new DisjunctiveNormalForm(clauses);

            assertDisjunctiveNormalForm(dnf);

            assertEquals(clauses == null ? 0 : clauses.length, dnf.getClauses().size());
            assertIterableEquals(clauses == null ? Collections.emptyList() : Arrays.asList(clauses), dnf.getClauses());
            assertIterableEquals(dnf.getClauses(), dnf.getSubExpressions());
        }
    }

    @Test
    void setClauses() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            List<Conjunction> clauses = randomListConjunctions();
            DisjunctiveNormalForm dnf = new DisjunctiveNormalForm(clauses);

            clauses = randomListConjunctions();
            dnf.setClauses(clauses);

            assertDisjunctiveNormalForm(dnf);

            assertEquals(clauses == null ? 0 : clauses.size(), dnf.getClauses().size());
            assertIterableEquals(clauses == null ? Collections.emptyList() : clauses, dnf.getClauses());
            assertIterableEquals(dnf.getClauses(), dnf.getSubExpressions());
        }
    }

    @Test
    void toCNF_Empty() {
        // empty - clauses empty
        DisjunctiveNormalForm dnf = new DisjunctiveNormalForm();

        ConjunctiveNormalForm cnf = dnf.toCNF();

        assertNotNull(cnf);
        assertNotNull(cnf.getClauses());
        DisjunctiveNormalForm reverseDnf = cnf.toDNF();
        assertNotNull(reverseDnf);
        assertNotNull(reverseDnf.getClauses());

        assertEquals(0, cnf.getClauses().size());
        assertEquals(0, reverseDnf.getClauses().size());
    }

    @Test
    void toCNF_OneClauseOneLiteral() {
        // one clause - one literal
        List<Conjunction> clauses = new ArrayList<>();
        clauses.add(new Conjunction(randomLiteral(true)));
        DisjunctiveNormalForm dnf = new DisjunctiveNormalForm(clauses);

        ConjunctiveNormalForm cnf = dnf.toCNF();

        assertNotNull(cnf);
        assertNotNull(cnf.getClauses());
        DisjunctiveNormalForm reverseDnf = cnf.toDNF();
        assertNotNull(reverseDnf);
        assertNotNull(reverseDnf.getClauses());

        assertEquals(1, cnf.getClauses().size());
        assertEquals(1, reverseDnf.getClauses().size());
        assertIterableEquals(dnf.getClauses().get(0).getLiterals(), cnf.getClauses().get(0).getLiterals());
        assertIterableEquals(dnf.getClauses().get(0).getLiterals(), reverseDnf.getClauses().get(0).getLiterals());
    }

    @Test
    void toCNF_OneClauseMultiLiteral() {
        // one clause - multiple literals
        for (int it = 0; it < ITERATIONS_COUNT; ++it) {
            List<Conjunction> clauses = new ArrayList<>();
            clauses.add(new Conjunction(randomListLiterals(true)));
            DisjunctiveNormalForm dnf = new DisjunctiveNormalForm(clauses);

            ConjunctiveNormalForm cnf = dnf.toCNF();

            assertNotNull(cnf);
            assertNotNull(cnf.getClauses());
            DisjunctiveNormalForm reverseDnf = cnf.toDNF();
            assertNotNull(reverseDnf);
            assertNotNull(reverseDnf.getClauses());

            int count = clauses.get(0).getLiterals().size();
            assertEquals(count, cnf.getClauses().size());
            assertEquals(1, reverseDnf.getClauses().size());
            for (int i = 0; i < count; ++i) {
                assertEquals(1, cnf.getClauses().get(i).getLiterals().size());
                assertEquals(clauses.get(0).getLiterals().get(i), cnf.getClauses().get(i).getLiterals().get(0));
            }
            assertIterableEquals(clauses.get(0).getLiterals(), reverseDnf.getClauses().get(0).getLiterals());
        }
    }

    @Test
    void toCNF_MultiClauseOneLiteral() {
        // multiple clauses - one literal
        for (int it = 0; it < ITERATIONS_COUNT; ++it) {
            List<Conjunction> clauses = new ArrayList<>();
            int count = randomCount() + 2;
            for (int i = 0; i < count; ++i) {
                clauses.add(new Conjunction(randomLiteral(true)));
            }
            DisjunctiveNormalForm dnf = new DisjunctiveNormalForm(clauses);

            ConjunctiveNormalForm cnf = dnf.toCNF();

            assertNotNull(cnf);
            assertNotNull(cnf.getClauses());
            DisjunctiveNormalForm reverseDnf = cnf.toDNF();
            assertNotNull(reverseDnf);
            assertNotNull(reverseDnf.getClauses());

            assertEquals(1, cnf.getClauses().size());
            assertEquals(clauses.size(), cnf.getClauses().get(0).getLiterals().size());
            assertEquals(clauses.size(), reverseDnf.getClauses().size());

            for (int i = 0; i < count; ++i) {
                assertEquals(clauses.get(i).getLiterals().get(0), cnf.getClauses().get(0).getLiterals().get(i));
                assertIterableEquals(clauses.get(i).getLiterals(), reverseDnf.getClauses().get(i).getLiterals());
            }
        }
    }

    @Test
    void toCNF_MultiClauseMultiLiteral() {
        // multiple clauses - multiple literals
        for (int it = 0; it < ITERATIONS_COUNT; ++it) {
            List<Conjunction> clauses = new ArrayList<>();
            int count = randomCount() % 2 + 5;
            int size = 1;
            for (int i = 0; i < count; ++i) {
                List<Literal> literals = Optional.ofNullable(randomListLiterals(true)).orElse(Collections.emptyList());
                literals = literals.subList(0, Math.min(literals.size(), 5));
                clauses.add(new Conjunction(literals));
                size *= literals.size();
            }
            DisjunctiveNormalForm dnf = new DisjunctiveNormalForm(clauses);

            ConjunctiveNormalForm cnf = dnf.toCNF();

            assertNotNull(cnf);
            assertNotNull(cnf.getClauses());
            // TODO - uncomment reverseDnf-related lines once the performance is optimized
            //DisjunctiveNormalForm reverseDnf = cnf.toDNF();
            //assertNotNull(reverseDnf);
            //assertNotNull(reverseDnf.getClauses());

            assertEquals(size, cnf.getClauses().size());
            //assertEquals(clauses.size(), reverseDnf.getClauses().size());

            int cnfSize = cnf.getClauses().size();
            for (int i = 0; i < count; ++i) {
                Conjunction clause = clauses.get(i);
                for (int j = 0; j < cnfSize; ++j) {
                    Disjunction cnfClause = cnf.getClauses().get(j);
                    assertEquals(clauses.size(), cnfClause.getLiterals().size());
                    assertTrue(clause.getLiterals().contains(cnfClause.getLiterals().get(i)));
                    //assertIterableEquals(clauses.get(i).getLiterals(), reverseDnf.getClauses().get(i).getLiterals());
                }
            }
        }
    }

    @Test
    void toCNF_MultiClauseMixedCountLiteral() {
        // multiple clauses - mixed-count literals
        for (int it = 0; it < ITERATIONS_COUNT; ++it) {
            List<Conjunction> clauses = new ArrayList<>();
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
                clauses.add(new Conjunction(literals));
                size *= literals.size();
            }
            DisjunctiveNormalForm dnf = new DisjunctiveNormalForm(clauses);

            ConjunctiveNormalForm cnf = dnf.toCNF();

            assertNotNull(cnf);
            assertNotNull(cnf.getClauses());
            // TODO - uncomment reverseDnf-related lines once the performance is optimized
            //DisjunctiveNormalForm reverseDnf = cnf.toDNF();
            //assertNotNull(reverseDnf);
            //assertNotNull(reverseDnf.getClauses());

            assertEquals(size, cnf.getClauses().size());
            //assertEquals(clauses.size(), reverseDnf.getClauses().size());

            int cnfSize = cnf.getClauses().size();
            for (int i = 0; i < count; ++i) {
                Conjunction clause = clauses.get(i);
                for (int j = 0; j < cnfSize; ++j) {
                    Disjunction cnfClause = cnf.getClauses().get(j);
                    assertEquals(clauses.size(), cnfClause.getLiterals().size());
                    assertTrue(clause.getLiterals().contains(cnfClause.getLiterals().get(i)));
                    //assertIterableEquals(clauses.get(i).getLiterals(), reverseDnf.getClauses().get(i).getLiterals());
                }
            }
        }
    }

    @Test
    void toCNF_OneClauseEmpty() {
        // one clause - empty
        List<Conjunction> clauses = new ArrayList<>();
        clauses.add(new Conjunction());
        DisjunctiveNormalForm dnf = new DisjunctiveNormalForm(clauses);

        ConjunctiveNormalForm cnf = dnf.toCNF();

        assertNotNull(cnf);
        assertNotNull(cnf.getClauses());
        DisjunctiveNormalForm reverseDnf = cnf.toDNF();
        assertNotNull(reverseDnf);
        assertNotNull(reverseDnf.getClauses());

        assertEquals(0, cnf.getClauses().size());
        assertEquals(0, reverseDnf.getClauses().size());
    }

    @Test
    void toCNF_MultiClauseEmpty() {
        // multiple clauses - all empty clauses
        for (int it = 0; it < ITERATIONS_COUNT; ++it) {
            List<Conjunction> clauses = new ArrayList<>();
            int count = randomCount();
            for (int i = 0; i < count; ++i) {
                clauses.add(new Conjunction());
            }
            DisjunctiveNormalForm dnf = new DisjunctiveNormalForm(clauses);

            ConjunctiveNormalForm cnf = dnf.toCNF();

            assertNotNull(cnf);
            assertNotNull(cnf.getClauses());

            DisjunctiveNormalForm reverseDnf = cnf.toDNF();
            assertNotNull(reverseDnf);
            assertNotNull(reverseDnf.getClauses());

            assertEquals(0, cnf.getClauses().size());
            assertEquals(0, reverseDnf.getClauses().size());
        }
    }

    @Test
    void toCNF_MultiClauseOneLiteralOrEmpty() {
        // multiple clauses - compare (one literal + empty clauses, same stripped from empty clauses)
        for (int it = 0; it < ITERATIONS_COUNT; ++it) {
            List<Conjunction> clauses = new ArrayList<>();
            int count = randomCount();
            int nonEmptyCount = 0;
            for (int i = 0; i < count; ++i) {
                Literal literal = null;
                if (randomCount() % 2 == 0) {
                    literal = randomLiteral(true);
                    ++nonEmptyCount;
                }
                clauses.add(new Conjunction(literal));
            }
            DisjunctiveNormalForm dnf = new DisjunctiveNormalForm(clauses);

            ConjunctiveNormalForm cnf = dnf.toCNF();

            assertNotNull(cnf);
            assertNotNull(cnf.getClauses());
            DisjunctiveNormalForm reverseDnf = cnf.toDNF();
            assertNotNull(reverseDnf);
            assertNotNull(reverseDnf.getClauses());

            assertEquals(nonEmptyCount > 0 ? 1 : 0, cnf.getClauses().size());
            assertEquals(nonEmptyCount, reverseDnf.getClauses().size());
            if (nonEmptyCount > 0) {
                assertEquals(nonEmptyCount, cnf.getClauses().get(0).getLiterals().size());

                for (int i = 0, idx = 0; i < count; ++i) {
                    Conjunction clause = clauses.get(i);
                    if (clause.isEmpty()) {
                        continue;
                    }
                    assertEquals(clause.getLiterals().get(0), cnf.getClauses().get(0).getLiterals().get(idx));
                    assertIterableEquals(clause.getLiterals(), reverseDnf.getClauses().get(idx).getLiterals());
                    ++idx;
                }
            }
        }
    }

    @Test
    void toCNF_MultiClauseMultiLiteralOrEmpty() {
        // multiple clauses - compare (multiple literal + empty clauses, same stripped from empty clauses)
        for (int it = 0; it < ITERATIONS_COUNT; ++it) {
            List<Conjunction> clauses = new ArrayList<>();
            int count = randomCount() % 2 + 5;
            int size = 1;
            boolean allEmpty = true;
            for (int i = 0; i < count; ++i) {
                List<Literal> literals = Optional.ofNullable(randomListLiterals()).orElse(Collections.emptyList());
                literals = literals.subList(0, Math.min(literals.size(), 5));
                Conjunction conjunction = new Conjunction(literals);
                clauses.add(conjunction);
                allEmpty = allEmpty && conjunction.isEmpty();
                size *= Math.max(1, conjunction.getLiterals().size());
            }
            DisjunctiveNormalForm dnf = new DisjunctiveNormalForm(clauses);

            ConjunctiveNormalForm cnf = dnf.toCNF();

            assertNotNull(cnf);
            assertNotNull(cnf.getClauses());
            // TODO - uncomment reverseDnf-related lines once the performance is optimized
            //DisjunctiveNormalForm reverseDnf = cnf.toDNF();
            //assertNotNull(reverseDnf);
            //assertNotNull(reverseDnf.getClauses());

            assertEquals(allEmpty ? 0 : size, cnf.getClauses().size());
            //assertEquals(clauses.size(), reverseDnf.getClauses().size());

            int cnfSize = cnf.getClauses().size();
            long nonEmptyCount = clauses.size() - clauses.stream().filter(Clause::isEmpty).count();
            for (int i = 0, idx = 0; i < count; ++i) {
                Conjunction clause = clauses.get(i);
                if (clause.isEmpty()) {
                    continue;
                }
                for (int j = 0; j < cnfSize; ++j) {
                    Disjunction cnfClause = cnf.getClauses().get(j);
                    assertEquals(nonEmptyCount, cnfClause.getLiterals().size());
                    assertTrue(clause.getLiterals().contains(cnfClause.getLiterals().get(idx)));
                    //assertIterableEquals(clauses.get(i).getLiterals(), reverseDnf.getClauses().get(i).getLiterals());
                }
                ++idx;
            }
        }
    }

    @Test
    void toCNF_MultiClauseMixedCountLiteralOrEmpty() {
        // multiple clauses - compare (mixed-count literal + empty clauses, same stripped from empty clauses)
        for (int it = 0; it < ITERATIONS_COUNT; ++it) {
            List<Conjunction> clauses = new ArrayList<>();
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
                clauses.add(new Conjunction(literals));
                size *= Math.max(1, literals.size());
            }
            DisjunctiveNormalForm dnf = new DisjunctiveNormalForm(clauses);

            ConjunctiveNormalForm cnf = dnf.toCNF();

            assertNotNull(cnf);
            assertNotNull(cnf.getClauses());
            // TODO - uncomment reverseDnf-related lines once the performance is optimized
            //DisjunctiveNormalForm reverseDnf = cnf.toDNF();
            //assertNotNull(reverseDnf);
            //assertNotNull(reverseDnf.getClauses());

            assertEquals(size, cnf.getClauses().size());
            //assertEquals(clauses.size(), reverseDnf.getClauses().size());

            int cnfSize = cnf.getClauses().size();
            long nonEmptyCount = clauses.size() - clauses.stream().filter(Clause::isEmpty).count();
            for (int i = 0, idx = 0; i < count; ++i) {
                Conjunction clause = clauses.get(i);
                if (clause.isEmpty()) {
                    continue;
                }
                for (int j = 0; j < cnfSize; ++j) {
                    Disjunction cnfClause = cnf.getClauses().get(j);
                    assertEquals(nonEmptyCount, cnfClause.getLiterals().size());
                    assertTrue(clause.getLiterals().contains(cnfClause.getLiterals().get(idx)));
                    //assertIterableEquals(clauses.get(i).getLiterals(), reverseDnf.getClauses().get(i).getLiterals());
                }
                ++idx;
            }
        }
    }

    @Test
    void toString_test() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            List<Conjunction> clauses = randomListConjunctions();
            DisjunctiveNormalForm dnf = new DisjunctiveNormalForm(clauses);

            assertDisjunctiveNormalForm(dnf);

            String str = dnf.toString();

            assertEquals("OR " + dnf.getClauses(), str);
        }
    }

}