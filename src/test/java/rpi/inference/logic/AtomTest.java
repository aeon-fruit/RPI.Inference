package rpi.inference.logic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class AtomTest {

    private static final int ITERATIONS_COUNT = 50000;
    private static final String POSITIVE = "posi_";

    private final Random random = new Random();

    @Test
    void newAtom_Default() {
        Atom atom = new Atom();

        assertAtom(atom);

        assertNull(atom.getSymbol());
        assertEquals(0, atom.getTerms().size());
    }

    @Test
    void newAtom_ListTerms() {
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
    void new_ArrayTerms() {
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
            String symbol = randomSymbol();
            String[] terms = randomArrayTerms();
            Atom atom = new Atom(symbol, terms);

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
    void setters_test() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            String symbol = randomSymbol();
            String[] terms = randomArrayTerms();
            Atom atom = new Atom(symbol, terms);

            symbol = randomSymbol();
            terms = randomArrayTerms();
            atom.setSymbol(symbol);
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

            if (symbol == null) {
                assertEquals("_", str);
                continue;
            }
            int beginIndex;
            int endIndex = POSITIVE.length();
            assertEquals(POSITIVE, str.substring(0, endIndex));
            beginIndex = endIndex;
            endIndex += symbol.length();
            assertEquals(symbol, str.substring(beginIndex, endIndex));
            beginIndex = endIndex;
            endIndex++;
            if ((terms == null) || terms.isEmpty()) {
                assertEquals(beginIndex, str.length());
                continue;
            }
            assertEquals("(", str.substring(beginIndex, endIndex));
            beginIndex = endIndex;
            endIndex = str.length() - 1;
            assertEquals(terms, Arrays.asList(str.substring(beginIndex, endIndex).split(", ")));
            beginIndex = endIndex;
            assertEquals(")", str.substring(beginIndex));
        }
    }

    private void assertAtom(Atom atom) {
        assertLiteral(atom);
        assertTrue(atom.isPositive());
        assertFalse(atom.isNegative());
    }

    private void assertLiteral(Literal literal) {
        assertNotNull(literal.getTerms());
        assertTrue(literal.isLiteral());
        assertFalse(literal.isClause());
        assertFalse(literal.isNormalForm());
        assertFalse(literal.isGeneralForm());
        assertTrue(literal.isDisjunction());
        assertTrue(literal.isConjunction());
    }

    private String[] randomArrayTerms() {
        List<String> list = randomListTerms();
        return list == null ? null : list.toArray(new String[0]);
    }

    private List<String> randomListTerms() {
        if (random.nextInt() % 5 == 0) {
            return null;
        }
        int length = random.nextInt(30) + 1;
        return IntStream.range(0, length).boxed().map(value -> "t" + value).collect(Collectors.toList());
    }

    private String randomSymbol() {
        if (random.nextInt() % 5 == 0) {
            return null;
        }
        String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
                "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        return alphabet[random.nextInt(alphabet.length)];
    }

}