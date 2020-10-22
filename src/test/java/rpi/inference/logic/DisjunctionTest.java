package rpi.inference.logic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static rpi.inference.logic.uil.Asserter.assertDisjunction;
import static rpi.inference.logic.uil.Randomizer.*;

class DisjunctionTest {

    @Test
    void constructor_Default() {
        Disjunction disjunction = new Disjunction();

        assertDisjunction(disjunction);

        assertIterableEquals(disjunction.getLiterals(), disjunction.getSubExpressions());
        assertEquals(0, disjunction.getSubExpressions().size());
    }

    @Test
    void constructor_ListLiterals() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            List<Literal> literals = randomListLiterals();
            Disjunction disjunction = new Disjunction(literals);

            assertDisjunction(disjunction);

            assertEquals(literals == null ? 0 : literals.size(), disjunction.getLiterals().size());
            assertIterableEquals(literals == null ? Collections.emptyList() : literals, disjunction.getLiterals());
            assertIterableEquals(disjunction.getLiterals(), disjunction.getSubExpressions());
        }
    }

    @Test
    void constructor_ArrayLiterals() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            Literal[] literals = randomArrayLiterals();
            Disjunction disjunction = new Disjunction(literals);

            assertDisjunction(disjunction);

            assertEquals(literals == null ? 0 : literals.length, disjunction.getLiterals().size());
            assertIterableEquals(literals == null ? Collections.emptyList() : Arrays.asList(literals), disjunction.getLiterals());
            assertIterableEquals(disjunction.getLiterals(), disjunction.getSubExpressions());
        }
    }

    @Test
    void setLiterals_List() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            List<Literal> literals = randomListLiterals();
            Disjunction disjunction = new Disjunction(literals);

            literals = randomListLiterals();
            disjunction.setLiterals(literals);

            assertDisjunction(disjunction);

            assertEquals(literals == null ? 0 : literals.size(), disjunction.getLiterals().size());
            assertIterableEquals(literals == null ? Collections.emptyList() : literals, disjunction.getLiterals());
            assertIterableEquals(disjunction.getLiterals(), disjunction.getSubExpressions());
        }
    }

    @Test
    void setLiterals_Array() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            Literal[] literals = randomArrayLiterals();
            Disjunction disjunction = new Disjunction(literals);

            literals = randomArrayLiterals();
            disjunction.setLiterals(literals);

            assertDisjunction(disjunction);

            assertEquals(literals == null ? 0 : literals.length, disjunction.getLiterals().size());
            assertIterableEquals(literals == null ? Collections.emptyList() : Arrays.asList(literals), disjunction.getLiterals());
            assertIterableEquals(disjunction.getLiterals(), disjunction.getSubExpressions());
        }
    }

    @Test
    void toString_test() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            Literal[] literals = randomArrayLiterals();
            Disjunction disjunction = new Disjunction(literals);

            assertDisjunction(disjunction);

            String str = disjunction.toString();

            assertEquals("OR " + disjunction.getLiterals(), str);
        }
    }

}