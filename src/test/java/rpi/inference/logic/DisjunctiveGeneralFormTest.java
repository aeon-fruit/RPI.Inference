package rpi.inference.logic;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static rpi.inference.logic.uil.Asserter.assertDisjunctiveGeneralForm;
import static rpi.inference.logic.uil.Randomizer.*;

class DisjunctiveGeneralFormTest {

    @Test
    void constructor_Default() {
        DisjunctiveGeneralForm dgf = new DisjunctiveGeneralForm();

        assertDisjunctiveGeneralForm(dgf);

        assertIterableEquals(dgf.getExpressions(), dgf.getSubExpressions());
        assertEquals(0, dgf.getSubExpressions().size());
    }

    @Test
    void constructor_ListTerms() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            List<Expression> expressions = randomListExpressions();
            DisjunctiveGeneralForm dgf = new DisjunctiveGeneralForm(expressions);

            assertDisjunctiveGeneralForm(dgf);

            List<Expression> nonEmpty = Optional.ofNullable(expressions).orElse(Collections.emptyList()).stream()
                    .filter(Objects::nonNull)
                    .filter(expression -> !expression.isEmpty())
                    .collect(Collectors.toList());
            assertEquals(expressions == null ? 0 : nonEmpty.size(), dgf.getExpressions().size());
            assertIterableEquals(expressions == null ? Collections.emptyList() : nonEmpty, dgf.getExpressions());
            assertIterableEquals(dgf.getExpressions(), dgf.getSubExpressions());
        }
    }

    @Test
    void constructor_ArrayTerms() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            Expression[] expressions = randomArrayExpressions();
            DisjunctiveGeneralForm dgf = new DisjunctiveGeneralForm(expressions);

            assertDisjunctiveGeneralForm(dgf);

            List<Expression> nonEmpty = Arrays.stream(Optional.ofNullable(expressions).orElse(new Expression[0]))
                    .filter(Objects::nonNull)
                    .filter(expression -> !expression.isEmpty())
                    .collect(Collectors.toList());
            assertEquals(expressions == null ? 0 : nonEmpty.size(), dgf.getExpressions().size());
            assertIterableEquals(expressions == null ? Collections.emptyList() : nonEmpty, dgf.getExpressions());
            assertIterableEquals(dgf.getExpressions(), dgf.getSubExpressions());
        }
    }

    @Test
    void setExpressions() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            List<Expression> expressions = randomListExpressions();
            DisjunctiveGeneralForm dgf = new DisjunctiveGeneralForm(expressions);

            expressions = randomListExpressions();
            dgf.setExpressions(expressions);

            assertDisjunctiveGeneralForm(dgf);

            List<Expression> nonEmpty = Optional.ofNullable(expressions).orElse(Collections.emptyList()).stream()
                    .filter(Objects::nonNull)
                    .filter(expression -> !expression.isEmpty())
                    .collect(Collectors.toList());
            assertEquals(expressions == null ? 0 : nonEmpty.size(), dgf.getExpressions().size());
            assertIterableEquals(expressions == null ? Collections.emptyList() : nonEmpty, dgf.getExpressions());
            assertIterableEquals(dgf.getExpressions(), dgf.getSubExpressions());
        }
    }

    @Test
    void getHiddenExpression() {
        // TODO - test after a proper implementation
    }

    @Test
    void testToString() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            List<Expression> expressions = randomListExpressions();
            DisjunctiveGeneralForm dgf = new DisjunctiveGeneralForm(expressions);

            assertDisjunctiveGeneralForm(dgf);

            String str = dgf.toString();

            assertEquals("OR " + dgf.getExpressions(), str);
        }
    }

}