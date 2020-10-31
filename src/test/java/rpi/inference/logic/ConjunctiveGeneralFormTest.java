package rpi.inference.logic;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static rpi.inference.logic.uil.Asserter.assertConjunctiveGeneralForm;
import static rpi.inference.logic.uil.Randomizer.*;

class ConjunctiveGeneralFormTest {

    @Test
    void constructor_Default() {
        ConjunctiveGeneralForm cgf = new ConjunctiveGeneralForm();

        assertConjunctiveGeneralForm(cgf);

        assertIterableEquals(cgf.getExpressions(), cgf.getSubExpressions());
        assertEquals(0, cgf.getSubExpressions().size());
    }

    @Test
    void constructor_ListTerms() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            List<Expression> expressions = randomListExpressions();
            ConjunctiveGeneralForm cgf = new ConjunctiveGeneralForm(expressions);

            assertConjunctiveGeneralForm(cgf);

            List<Expression> nonEmpty = Optional.ofNullable(expressions).orElse(Collections.emptyList()).stream()
                    .filter(Objects::nonNull)
                    .filter(expression -> !expression.isEmpty())
                    .collect(Collectors.toList());
            assertEquals(expressions == null ? 0 : nonEmpty.size(), cgf.getExpressions().size());
            assertIterableEquals(expressions == null ? Collections.emptyList() : nonEmpty, cgf.getExpressions());
            assertIterableEquals(cgf.getExpressions(), cgf.getSubExpressions());
        }
    }

    @Test
    void constructor_ArrayTerms() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            Expression[] expressions = randomArrayExpressions();
            ConjunctiveGeneralForm cgf = new ConjunctiveGeneralForm(expressions);

            assertConjunctiveGeneralForm(cgf);

            List<Expression> nonEmpty = Arrays.stream(Optional.ofNullable(expressions).orElse(new Expression[0]))
                    .filter(Objects::nonNull)
                    .filter(expression -> !expression.isEmpty())
                    .collect(Collectors.toList());
            assertEquals(expressions == null ? 0 : nonEmpty.size(), cgf.getExpressions().size());
            assertIterableEquals(expressions == null ? Collections.emptyList() : nonEmpty, cgf.getExpressions());
            assertIterableEquals(cgf.getExpressions(), cgf.getSubExpressions());
        }
    }

    @Test
    void setExpressions() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            List<Expression> expressions = randomListExpressions();
            ConjunctiveGeneralForm cgf = new ConjunctiveGeneralForm(expressions);

            expressions = randomListExpressions();
            cgf.setExpressions(expressions);

            assertConjunctiveGeneralForm(cgf);

            List<Expression> nonEmpty = Optional.ofNullable(expressions).orElse(Collections.emptyList()).stream()
                    .filter(Objects::nonNull)
                    .filter(expression -> !expression.isEmpty())
                    .collect(Collectors.toList());
            assertEquals(expressions == null ? 0 : nonEmpty.size(), cgf.getExpressions().size());
            assertIterableEquals(expressions == null ? Collections.emptyList() : nonEmpty, cgf.getExpressions());
            assertIterableEquals(cgf.getExpressions(), cgf.getSubExpressions());
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
            ConjunctiveGeneralForm cgf = new ConjunctiveGeneralForm(expressions);

            assertConjunctiveGeneralForm(cgf);

            String str = cgf.toString();

            assertEquals("AND " + cgf.getExpressions(), str);
        }
    }

}