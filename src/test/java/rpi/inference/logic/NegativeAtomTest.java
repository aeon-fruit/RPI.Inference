package rpi.inference.logic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class NegativeAtomTest {

    private static final int ITERATIONS_COUNT = 50000;
    private static final String NEGATIVE = "nega_";

    private final Random random = new Random();

    @Test
    void new_Default() {
        NegativeAtom negativeAtom = new NegativeAtom();

        assertNegativeAtom(negativeAtom);

        assertNull(negativeAtom.getSymbol());
        assertEquals(0, negativeAtom.getTerms().size());
    }

    @Test
    void new_ListTerms() {
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
    void new_ArrayTerms() {
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
            String symbol = randomSymbol();
            String[] terms = randomArrayTerms();
            NegativeAtom negativeAtom = new NegativeAtom(symbol, terms);

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
    void setters_test() {
        for (int i = 0; i < ITERATIONS_COUNT; ++i) {
            String symbol = randomSymbol();
            String[] terms = randomArrayTerms();
            NegativeAtom negativeAtom = new NegativeAtom(symbol, terms);

            symbol = randomSymbol();
            terms = randomArrayTerms();
            negativeAtom.setSymbol(symbol);
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

            if (symbol == null) {
                assertEquals("_", str);
                continue;
            }
            int beginIndex;
            int endIndex = NEGATIVE.length();
            assertEquals(NEGATIVE, str.substring(0, endIndex));
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

    private void assertNegativeAtom(NegativeAtom negativeAtom) {
        assertLiteral(negativeAtom);
        assertFalse(negativeAtom.isPositive());
        assertTrue(negativeAtom.isNegative());
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