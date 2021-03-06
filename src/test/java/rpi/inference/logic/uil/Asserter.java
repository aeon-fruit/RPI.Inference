package rpi.inference.logic.uil;

import rpi.inference.logic.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public interface Asserter {

    static void assertAtom(Atom atom) {
        assertLiteral(atom);
        assertTrue(atom.isPositive());
        assertFalse(atom.isNegative());
    }

    static void assertNegativeAtom(NegativeAtom negativeAtom) {
        assertLiteral(negativeAtom);
        assertFalse(negativeAtom.isPositive());
        assertTrue(negativeAtom.isNegative());
    }

    static void assertLiteral(Literal literal) {
        assertNotNull(literal.getTerms());
        String symbol = literal.getSymbol();
        assertEquals(((symbol == null) || symbol.isEmpty()) && literal.getTerms().isEmpty(), literal.isEmpty());
        assertTrue(literal.isLiteral());
        assertFalse(literal.isClause());
        assertFalse(literal.isNormalForm());
        assertFalse(literal.isGeneralForm());
        assertTrue(literal.isDisjunction());
        assertTrue(literal.isConjunction());
    }

    static void assertLiteralString(String expectedSymbol, List<String> expectedTerms, String actual, String prefix) {
        if (expectedSymbol == null) {
            assertEquals("_", actual);
            return;
        }
        int beginIndex;
        int endIndex = prefix.length();
        assertEquals(prefix, actual.substring(0, endIndex));
        beginIndex = endIndex;
        endIndex += expectedSymbol.length();
        assertEquals(expectedSymbol, actual.substring(beginIndex, endIndex));
        beginIndex = endIndex;
        endIndex++;
        if ((expectedTerms == null) || expectedTerms.isEmpty()) {
            assertEquals(beginIndex, actual.length());
            return;
        }
        assertEquals("(", actual.substring(beginIndex, endIndex));
        beginIndex = endIndex;
        endIndex = actual.length() - 1;
        assertEquals(expectedTerms, Arrays.asList(actual.substring(beginIndex, endIndex).split(", ")));
        beginIndex = endIndex;
        assertEquals(")", actual.substring(beginIndex));
    }

    static void assertConjunction(Conjunction conjunction) {
        assertClause(conjunction);
        assertFalse(conjunction.isDisjunction());
        assertTrue(conjunction.isConjunction());
    }

    static void assertDisjunction(Disjunction disjunction) {
        assertClause(disjunction);
        assertTrue(disjunction.isDisjunction());
        assertFalse(disjunction.isConjunction());
    }

    static void assertClause(Clause clause) {
        assertNotNull(clause.getLiterals());
        assertEquals(clause.getLiterals().isEmpty(), clause.isEmpty());
        assertTrue(clause.isPositive());
        assertFalse(clause.isNegative());
        assertFalse(clause.isLiteral());
        assertTrue(clause.isClause());
        assertFalse(clause.isNormalForm());
        assertFalse(clause.isGeneralForm());
    }

    static void assertConjunctiveNormalForm(ConjunctiveNormalForm cnf) {
        assertNormalForm(cnf);
        assertFalse(cnf.isDisjunction());
        assertTrue(cnf.isConjunction());
    }

    static void assertDisjunctiveNormalForm(DisjunctiveNormalForm dnf) {
        assertNormalForm(dnf);
        assertTrue(dnf.isDisjunction());
        assertFalse(dnf.isConjunction());
    }

    static void assertNormalForm(NormalForm normalForm) {
        assertNotNull(normalForm.getClauses());
        assertEquals(normalForm.getClauses().isEmpty(), normalForm.isEmpty());
        assertTrue(normalForm.isPositive());
        assertFalse(normalForm.isNegative());
        assertFalse(normalForm.isLiteral());
        assertFalse(normalForm.isClause());
        assertTrue(normalForm.isNormalForm());
        assertFalse(normalForm.isGeneralForm());
    }

    static void assertConjunctiveGeneralForm(ConjunctiveGeneralForm cgf) {
        assertGeneralForm(cgf);
        assertFalse(cgf.isDisjunction());
        assertTrue(cgf.isConjunction());
    }

    static void assertDisjunctiveGeneralForm(DisjunctiveGeneralForm dgf) {
        assertGeneralForm(dgf);
        assertTrue(dgf.isDisjunction());
        assertFalse(dgf.isConjunction());
    }

    static void assertGeneralForm(GeneralForm generalForm) {
        assertNotNull(generalForm.getExpressions());
        assertEquals(generalForm.getExpressions().isEmpty(), generalForm.isEmpty());
        assertTrue(generalForm.isPositive());
        assertFalse(generalForm.isNegative());
        assertFalse(generalForm.isLiteral());
        assertFalse(generalForm.isClause());
        assertFalse(generalForm.isNormalForm());
        assertTrue(generalForm.isGeneralForm());
    }

}
