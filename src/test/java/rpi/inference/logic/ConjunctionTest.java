package rpi.inference.logic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static rpi.inference.logic.uil.Asserter.assertConjunction;
import static rpi.inference.logic.uil.Randomizer.*;

class ConjunctionTest {

    @Test
    void constructor_Default() {
        Conjunction conjunction = new Conjunction();

        assertConjunction(conjunction);

        assertIterableEquals(conjunction.getLiterals(), conjunction.getSubExpressions());
        assertEquals(0, conjunction.getSubExpressions().size());
    }

    @Test
    void constructor_ListLiterals() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            List<Literal> literals = randomListLiterals();
            Conjunction conjunction = new Conjunction(literals);

            assertConjunction(conjunction);

            assertEquals(literals == null ? 0 : literals.size(), conjunction.getLiterals().size());
            assertIterableEquals(literals == null ? Collections.emptyList() : literals, conjunction.getLiterals());
            assertIterableEquals(conjunction.getLiterals(), conjunction.getSubExpressions());
        }
    }

    @Test
    void constructor_ArrayLiterals() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            Literal[] literals = randomArrayLiterals();
            Conjunction conjunction = new Conjunction(literals);

            assertConjunction(conjunction);

            assertEquals(literals == null ? 0 : literals.length, conjunction.getLiterals().size());
            assertIterableEquals(literals == null ? Collections.emptyList() : Arrays.asList(literals), conjunction.getLiterals());
            assertIterableEquals(conjunction.getLiterals(), conjunction.getSubExpressions());
        }
    }

    @Test
    void setLiterals_List() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            List<Literal> literals = randomListLiterals();
            Conjunction conjunction = new Conjunction(literals);

            literals = randomListLiterals();
            conjunction.setLiterals(literals);

            assertConjunction(conjunction);

            assertEquals(literals == null ? 0 : literals.size(), conjunction.getLiterals().size());
            assertIterableEquals(literals == null ? Collections.emptyList() : literals, conjunction.getLiterals());
            assertIterableEquals(conjunction.getLiterals(), conjunction.getSubExpressions());
        }
    }

    @Test
    void setLiterals_Array() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            Literal[] literals = randomArrayLiterals();
            Conjunction conjunction = new Conjunction(literals);

            literals = randomArrayLiterals();
            conjunction.setLiterals(literals);

            assertConjunction(conjunction);

            assertEquals(literals == null ? 0 : literals.length, conjunction.getLiterals().size());
            assertIterableEquals(literals == null ? Collections.emptyList() : Arrays.asList(literals), conjunction.getLiterals());
            assertIterableEquals(conjunction.getLiterals(), conjunction.getSubExpressions());
        }
    }

    @Test
    void toString_test() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            Literal[] literals = randomArrayLiterals();
            Conjunction conjunction = new Conjunction(literals);

            assertConjunction(conjunction);

            String str = conjunction.toString();

            assertEquals("AND " + conjunction.getLiterals(), str);
        }
    }

}