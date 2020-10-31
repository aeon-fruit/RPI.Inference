package rpi.inference.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static rpi.inference.logic.uil.Randomizer.*;

class RuleConditionalTest {

    @Test
    void constructor_Default() {
        RuleConditional rule = new RuleConditional();

        assertNull(rule.getAntecedent());
        assertNull(rule.getConsequent());
        assertFalse(rule.isValid());
    }

    @Test
    void constructor_ArgsLiteralAntecedent() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            Literal antecedent = randomLiteral();
            Literal consequent = randomLiteral();
            RuleConditional rule = new RuleConditional(antecedent, consequent);

            assertEquals(antecedent, rule.getAntecedent());
            assertEquals(consequent, rule.getConsequent());
            assertEquals(antecedent != null && !antecedent.isEmpty() && consequent != null && !consequent.isEmpty(),
                    rule.isValid());
        }
    }

    @Test
    void constructor_ArgsConjunctiveExpressionAntecedent() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            ConjunctiveExpression antecedent = randomConjunctiveExpression();
            Literal consequent = randomLiteral();
            RuleConditional rule = new RuleConditional(antecedent, consequent);

            assertEquals(antecedent, rule.getAntecedent());
            assertEquals(consequent, rule.getConsequent());
            assertEquals(antecedent != null && !antecedent.isEmpty() && consequent != null && !consequent.isEmpty(),
                    rule.isValid());
        }
    }

    @Test
    void setAntecedent_Literal() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            Literal antecedent = randomLiteral();
            Literal consequent = randomLiteral();
            RuleConditional rule = new RuleConditional(antecedent, consequent);

            assertEquals(antecedent, rule.getAntecedent());
            assertEquals(consequent, rule.getConsequent());
            assertEquals(antecedent != null && !antecedent.isEmpty() && consequent != null && !consequent.isEmpty(),
                    rule.isValid());

            antecedent = randomLiteral();
            rule.setAntecedent(antecedent);

            assertEquals(antecedent, rule.getAntecedent());
            assertEquals(consequent, rule.getConsequent());
            assertEquals(antecedent != null && !antecedent.isEmpty() && consequent != null && !consequent.isEmpty(),
                    rule.isValid());
        }
    }

    @Test
    void setAntecedent_ConjunctiveExpression() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            ConjunctiveExpression antecedent = randomConjunctiveExpression();
            Literal consequent = randomLiteral();
            RuleConditional rule = new RuleConditional(antecedent, consequent);

            assertEquals(antecedent, rule.getAntecedent());
            assertEquals(consequent, rule.getConsequent());
            assertEquals(antecedent != null && !antecedent.isEmpty() && consequent != null && !consequent.isEmpty(),
                    rule.isValid());

            antecedent = randomConjunctiveExpression();
            rule.setAntecedent(antecedent);

            assertEquals(antecedent, rule.getAntecedent());
            assertEquals(consequent, rule.getConsequent());
            assertEquals(antecedent != null && !antecedent.isEmpty() && consequent != null && !consequent.isEmpty(),
                    rule.isValid());
        }
    }

    @Test
    void setConsequent_LiteralAntecedent() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            Literal antecedent = randomLiteral();
            Literal consequent = randomLiteral();
            RuleConditional rule = new RuleConditional(antecedent, consequent);

            assertEquals(antecedent, rule.getAntecedent());
            assertEquals(consequent, rule.getConsequent());
            assertEquals(antecedent != null && !antecedent.isEmpty() && consequent != null && !consequent.isEmpty(),
                    rule.isValid());

            consequent = randomLiteral();
            rule.setConsequent(consequent);

            assertEquals(antecedent, rule.getAntecedent());
            assertEquals(consequent, rule.getConsequent());
            assertEquals(antecedent != null && !antecedent.isEmpty() && consequent != null && !consequent.isEmpty(),
                    rule.isValid());
        }
    }

    @Test
    void setConsequent_ConjunctiveExpressionAntecedent() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            ConjunctiveExpression antecedent = randomConjunctiveExpression();
            Literal consequent = randomLiteral();
            RuleConditional rule = new RuleConditional(antecedent, consequent);

            assertEquals(antecedent, rule.getAntecedent());
            assertEquals(consequent, rule.getConsequent());
            assertEquals(antecedent != null && !antecedent.isEmpty() && consequent != null && !consequent.isEmpty(),
                    rule.isValid());

            consequent = randomLiteral();
            rule.setConsequent(consequent);

            assertEquals(antecedent, rule.getAntecedent());
            assertEquals(consequent, rule.getConsequent());
            assertEquals(antecedent != null && !antecedent.isEmpty() && consequent != null && !consequent.isEmpty(),
                    rule.isValid());
        }
    }

    @Test
    void testToString_LiteralAntecedent() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            Literal antecedent = randomLiteral();
            Literal consequent = randomLiteral();
            RuleConditional rule = new RuleConditional(antecedent, consequent);

            assertEquals(antecedent, rule.getAntecedent());
            assertEquals(consequent, rule.getConsequent());
            assertEquals(antecedent != null && !antecedent.isEmpty() && consequent != null && !consequent.isEmpty(),
                    rule.isValid());

            String str = rule.toString();
            String antecedentStr = ((antecedent != null) && !antecedent.isEmpty()) ? antecedent.toString() : "INVALID";
            String consequentStr = ((consequent != null) && !consequent.isEmpty()) ? consequent.toString() : "INVALID";
            assertEquals(antecedentStr + " => " + consequentStr, str);
        }
    }

    @Test
    void testToString_ConjunctiveExpressionAntecedent() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            ConjunctiveExpression antecedent = randomConjunctiveExpression();
            Literal consequent = randomLiteral();
            RuleConditional rule = new RuleConditional(antecedent, consequent);

            assertEquals(antecedent, rule.getAntecedent());
            assertEquals(consequent, rule.getConsequent());
            assertEquals(antecedent != null && !antecedent.isEmpty() && consequent != null && !consequent.isEmpty(),
                    rule.isValid());

            String str = rule.toString();
            String antecedentStr = ((antecedent != null) && !antecedent.isEmpty()) ? antecedent.toString() : "INVALID";
            String consequentStr = ((consequent != null) && !consequent.isEmpty()) ? consequent.toString() : "INVALID";
            assertEquals(antecedentStr + " => " + consequentStr, str);
        }
    }

}