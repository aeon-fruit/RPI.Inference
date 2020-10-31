package rpi.inference.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static rpi.inference.logic.uil.Randomizer.ITERATIONS_COUNT;
import static rpi.inference.logic.uil.Randomizer.randomExpression;

class ConditionalTest {

    @Test
    void constructor_Default() {
        Conditional conditional = new Conditional();

        assertNull(conditional.getAntecedent());
        assertNull(conditional.getConsequent());
        assertFalse(conditional.isValid());
    }

    @Test
    void constructor_Args() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            Expression antecedent = randomExpression();
            Expression consequent = randomExpression();
            Conditional conditional = new Conditional(antecedent, consequent);

            assertEquals(antecedent, conditional.getAntecedent());
            assertEquals(consequent, conditional.getConsequent());
            assertEquals(antecedent != null && !antecedent.isEmpty() && consequent != null && !consequent.isEmpty(),
                    conditional.isValid());
        }
    }

    @Test
    void setAntecedent() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            Expression antecedent = randomExpression();
            Expression consequent = randomExpression();
            Conditional conditional = new Conditional(antecedent, consequent);

            assertEquals(antecedent, conditional.getAntecedent());
            assertEquals(consequent, conditional.getConsequent());
            assertEquals(antecedent != null && !antecedent.isEmpty() && consequent != null && !consequent.isEmpty(),
                    conditional.isValid());

            antecedent = randomExpression();
            conditional.setAntecedent(antecedent);

            assertEquals(antecedent, conditional.getAntecedent());
            assertEquals(consequent, conditional.getConsequent());
            assertEquals(antecedent != null && !antecedent.isEmpty() && consequent != null && !consequent.isEmpty(),
                    conditional.isValid());
        }
    }

    @Test
    void setConsequent() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            Expression antecedent = randomExpression();
            Expression consequent = randomExpression();
            Conditional conditional = new Conditional(antecedent, consequent);

            assertEquals(antecedent, conditional.getAntecedent());
            assertEquals(consequent, conditional.getConsequent());
            assertEquals(antecedent != null && !antecedent.isEmpty() && consequent != null && !consequent.isEmpty(),
                    conditional.isValid());

            consequent = randomExpression();
            conditional.setConsequent(consequent);

            assertEquals(antecedent, conditional.getAntecedent());
            assertEquals(consequent, conditional.getConsequent());
            assertEquals(antecedent != null && !antecedent.isEmpty() && consequent != null && !consequent.isEmpty(),
                    conditional.isValid());
        }
    }

    @Test
    void testToString() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            Expression antecedent = randomExpression();
            Expression consequent = randomExpression();
            Conditional conditional = new Conditional(antecedent, consequent);

            assertEquals(antecedent, conditional.getAntecedent());
            assertEquals(consequent, conditional.getConsequent());
            assertEquals(antecedent != null && !antecedent.isEmpty() && consequent != null && !consequent.isEmpty(),
                    conditional.isValid());

            String str = conditional.toString();
            String antecedentStr = ((antecedent != null) && !antecedent.isEmpty()) ? antecedent.toString() : "INVALID";
            String consequentStr = ((consequent != null) && !consequent.isEmpty()) ? consequent.toString() : "INVALID";
            assertEquals(antecedentStr + " => " + consequentStr, str);
        }
    }

}