package rpi.inference.logic.uil;

import rpi.inference.logic.Atom;
import rpi.inference.logic.Literal;
import rpi.inference.logic.NegativeAtom;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface Randomizer {

    int ITERATIONS_COUNT = 50000;

    final class RandomUtil {
        private final static Random random = new Random();
    }

    static String randomSymbol() {
        if (RandomUtil.random.nextInt() % 5 == 0) {
            return null;
        }
        return randomLowerCaseLetter();
    }

    static String randomLowerCaseLetter() {
        String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
                "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        return alphabet[RandomUtil.random.nextInt(alphabet.length)];
    }

    static String[] randomArrayTerms() {
        List<String> list = randomListTerms();
        return list == null ? null : list.toArray(new String[0]);
    }

    static List<String> randomListTerms() {
        if (RandomUtil.random.nextInt() % 5 == 0) {
            return null;
        }
        int length = RandomUtil.random.nextInt(30) + 1;
        return IntStream.range(0, length).boxed().map(value -> "t" + value).collect(Collectors.toList());
    }

    static Atom randomAtom() {
        String symbol = randomSymbol();
        List<String> terms = randomListTerms();
        return new Atom(symbol, terms);
    }

    static NegativeAtom randomNegativeAtom() {
        String symbol = randomSymbol();
        List<String> terms = randomListTerms();
        return new NegativeAtom(symbol, terms);
    }

    static Literal randomLiteral() {
        return RandomUtil.random.nextBoolean() ? randomAtom() : randomNegativeAtom();
    }

    static Literal[] randomArrayLiterals() {
        List<Literal> list = randomListLiterals();
        return list == null ? null : list.toArray(new Literal[0]);
    }

    static List<Literal> randomListLiterals() {
        if (RandomUtil.random.nextInt() % 5 == 0) {
            return null;
        }
        int length = RandomUtil.random.nextInt(30) + 1;
        List<Literal> list = new ArrayList<>(length);
        for (int i = 0; i < length; ++i) {
            list.add(randomLiteral());
        }
        return list;
    }

}
