package rpi.inference.logic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static rpi.inference.logic.uil.Asserter.*;
import static rpi.inference.logic.uil.Randomizer.*;

class NegativeAtomTest {

    private static final String NEGATIVE = "nega_";

    @Test
    void constructor_Default() {
        NegativeAtom negativeAtom = new NegativeAtom();

        assertNegativeAtom(negativeAtom);

        assertNull(negativeAtom.getSymbol());
        assertEquals(0, negativeAtom.getTerms().size());
    }

    @Test
    void constructor_ListTerms() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            String symbol = randomSymbol();
            List<String> terms = randomListTerms();
            NegativeAtom negativeAtom = new NegativeAtom(symbol, terms);

            assertNegativeAtom(negativeAtom);

            assertEquals(symbol, negativeAtom.getSymbol());
            assertEquals(terms == null ? 0 : terms.size(), negativeAtom.getTerms().size());
            assertIterableEquals(terms == null ? Collections.emptyList() : terms, negativeAtom.getTerms());
        }
    }

    @Test
    void constructor_ArrayTerms() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            String symbol = randomSymbol();
            String[] terms = randomArrayTerms();
            NegativeAtom negativeAtom = new NegativeAtom(symbol, terms);

            assertNegativeAtom(negativeAtom);

            assertEquals(symbol, negativeAtom.getSymbol());
            assertEquals(terms == null ? 0 : terms.length, negativeAtom.getTerms().size());
            assertIterableEquals(terms == null ? Collections.emptyList() : Arrays.asList(terms), negativeAtom.getTerms());
        }
    }

    @Test
    void getAntiLiteral_test() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            NegativeAtom negativeAtom = randomNegativeAtom();

            Literal anti = negativeAtom.getAntiLiteral();

            assertEquals(Atom.class, anti.getClass());
            assertLiteral(anti);
            assertTrue(anti.isPositive());
            assertFalse(anti.isNegative());

            assertEquals(negativeAtom.getSymbol(), anti.getSymbol());
            assertEquals(negativeAtom.getTerms().size(), anti.getTerms().size());
            assertIterableEquals(negativeAtom.getTerms(), anti.getTerms());
        }
    }

    @Test
    void setSymbol_test() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            String symbol = randomSymbol();
            String[] terms = randomArrayTerms();
            NegativeAtom negativeAtom = new NegativeAtom(symbol, terms);

            symbol = randomSymbol();
            negativeAtom.setSymbol(symbol);

            assertNegativeAtom(negativeAtom);

            assertEquals(symbol, negativeAtom.getSymbol());
            assertEquals(terms == null ? 0 : terms.length, negativeAtom.getTerms().size());
            assertIterableEquals(terms == null ? Collections.emptyList() : Arrays.asList(terms), negativeAtom.getTerms());
        }
    }

    @Test
    void setTerms_List() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            String symbol = randomSymbol();
            List<String> terms = randomListTerms();
            NegativeAtom negativeAtom = new NegativeAtom(symbol, terms);

            terms = randomListTerms();
            negativeAtom.setTerms(terms);

            assertNegativeAtom(negativeAtom);

            assertEquals(symbol, negativeAtom.getSymbol());
            assertEquals(terms == null ? 0 : terms.size(), negativeAtom.getTerms().size());
            assertIterableEquals(terms == null ? Collections.emptyList() : terms, negativeAtom.getTerms());
        }
    }

    @Test
    void setTerms_Array() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            String symbol = randomSymbol();
            String[] terms = randomArrayTerms();
            NegativeAtom negativeAtom = new NegativeAtom(symbol, terms);

            terms = randomArrayTerms();
            negativeAtom.setTerms(terms);

            assertNegativeAtom(negativeAtom);

            assertEquals(symbol, negativeAtom.getSymbol());
            assertEquals(terms == null ? 0 : terms.length, negativeAtom.getTerms().size());
            assertIterableEquals(terms == null ? Collections.emptyList() : Arrays.asList(terms), negativeAtom.getTerms());
        }
    }

    @Test
    void toString_test() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            String symbol = randomSymbol();
            List<String> terms = randomListTerms();
            NegativeAtom negativeAtom = new NegativeAtom(symbol, terms);

            assertNegativeAtom(negativeAtom);

            assertEquals(symbol, negativeAtom.getSymbol());
            assertEquals(terms == null ? 0 : terms.size(), negativeAtom.getTerms().size());
            assertIterableEquals(terms == null ? Collections.emptyList() : terms, negativeAtom.getTerms());

            String str = negativeAtom.toString();

            assertLiteralString(symbol, terms, str, NEGATIVE);
        }
    }

}