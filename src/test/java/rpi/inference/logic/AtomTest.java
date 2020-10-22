package rpi.inference.logic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static rpi.inference.logic.uil.Asserter.*;
import static rpi.inference.logic.uil.Randomizer.*;

class AtomTest {

    private static final String POSITIVE = "posi_";

    @Test
    void constructor_Default() {
        Atom atom = new Atom();

        assertAtom(atom);

        assertNull(atom.getSymbol());
        assertEquals(0, atom.getTerms().size());
    }

    @Test
    void constructor_ListTerms() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            String symbol = randomSymbol();
            List<String> terms = randomListTerms();
            Atom atom = new Atom(symbol, terms);

            assertAtom(atom);

            assertEquals(symbol, atom.getSymbol());
            assertEquals(terms == null ? 0 : terms.size(), atom.getTerms().size());
            assertIterableEquals(terms == null ? Collections.emptyList() : terms, atom.getTerms());
        }
    }

    @Test
    void constructor_ArrayTerms() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            String symbol = randomSymbol();
            String[] terms = randomArrayTerms();
            Atom atom = new Atom(symbol, terms);

            assertAtom(atom);

            assertEquals(symbol, atom.getSymbol());
            assertEquals(terms == null ? 0 : terms.length, atom.getTerms().size());
            assertIterableEquals(terms == null ? Collections.emptyList() : Arrays.asList(terms), atom.getTerms());
        }
    }

    @Test
    void getAntiLiteral_test() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            Atom atom = randomAtom();

            Literal anti = atom.getAntiLiteral();

            assertEquals(NegativeAtom.class, anti.getClass());
            assertLiteral(anti);
            assertFalse(anti.isPositive());
            assertTrue(anti.isNegative());

            assertEquals(atom.getSymbol(), anti.getSymbol());
            assertEquals(atom.getTerms().size(), anti.getTerms().size());
            assertIterableEquals(atom.getTerms(), anti.getTerms());
        }
    }

    @Test
    void setSymbol_test() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            String symbol = randomSymbol();
            String[] terms = randomArrayTerms();
            Atom atom = new Atom(symbol, terms);

            symbol = randomSymbol();
            atom.setSymbol(symbol);

            assertAtom(atom);

            assertEquals(symbol, atom.getSymbol());
            assertEquals(terms == null ? 0 : terms.length, atom.getTerms().size());
            assertIterableEquals(terms == null ? Collections.emptyList() : Arrays.asList(terms), atom.getTerms());
        }
    }

    @Test
    void setTerms_List() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            String symbol = randomSymbol();
            List<String> terms = randomListTerms();
            Atom atom = new Atom(symbol, terms);

            terms = randomListTerms();
            atom.setTerms(terms);

            assertAtom(atom);

            assertEquals(symbol, atom.getSymbol());
            assertEquals(terms == null ? 0 : terms.size(), atom.getTerms().size());
            assertIterableEquals(terms == null ? Collections.emptyList() : terms, atom.getTerms());
        }
    }

    @Test
    void setTerms_Array() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            String symbol = randomSymbol();
            String[] terms = randomArrayTerms();
            Atom atom = new Atom(symbol, terms);

            terms = randomArrayTerms();
            atom.setTerms(terms);

            assertAtom(atom);

            assertEquals(symbol, atom.getSymbol());
            assertEquals(terms == null ? 0 : terms.length, atom.getTerms().size());
            assertIterableEquals(terms == null ? Collections.emptyList() : Arrays.asList(terms), atom.getTerms());
        }
    }

    @Test
    void toString_test() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            String symbol = randomSymbol();
            List<String> terms = randomListTerms();
            Atom atom = new Atom(symbol, terms);

            assertAtom(atom);

            assertEquals(symbol, atom.getSymbol());
            assertEquals(terms == null ? 0 : terms.size(), atom.getTerms().size());
            assertIterableEquals(terms == null ? Collections.emptyList() : terms, atom.getTerms());

            String str = atom.toString();

            assertLiteralString(symbol, terms, str, POSITIVE);
        }
    }

}