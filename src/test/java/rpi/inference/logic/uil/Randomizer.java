package rpi.inference.logic.uil;

import rpi.inference.logic.*;

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

    static int randomCount() {
        return RandomUtil.random.nextInt(30) + 1;
    }

    static String randomSymbol() {
        return randomSymbol(false);
    }

    static String randomSymbol(boolean nonNull) {
        if (!nonNull && RandomUtil.random.nextInt() % 5 == 0) {
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
        return randomListTerms(false);
    }

    static List<String> randomListTerms(boolean nonNull) {
        if (!nonNull && RandomUtil.random.nextInt() % 5 == 0) {
            return null;
        }
        int length = RandomUtil.random.nextInt(30) + 1;
        return IntStream.range(0, length).boxed().map(value -> "t" + value).collect(Collectors.toList());
    }

    static Atom randomAtom() {
        return randomAtom(false);
    }

    static Atom randomAtom(boolean nonNull) {
        String symbol = randomSymbol(nonNull);
        List<String> terms = randomListTerms(nonNull);
        return new Atom(symbol, terms);
    }

    static NegativeAtom randomNegativeAtom() {
        return randomNegativeAtom(false);
    }

    static NegativeAtom randomNegativeAtom(boolean nonNull) {
        String symbol = randomSymbol(nonNull);
        List<String> terms = randomListTerms(nonNull);
        return new NegativeAtom(symbol, terms);
    }

    static Literal randomLiteral() {
        return randomLiteral(false);
    }

    static Literal randomLiteral(boolean nonNull) {
        return RandomUtil.random.nextBoolean() ? randomAtom(nonNull) : randomNegativeAtom(nonNull);
    }

    static Literal[] randomArrayLiterals() {
        List<Literal> list = randomListLiterals();
        return list == null ? null : list.toArray(new Literal[0]);
    }

    static List<Literal> randomListLiterals() {
        return randomListLiterals(false);
    }

    static List<Literal> randomListLiterals(boolean nonNull) {
        if (!nonNull && RandomUtil.random.nextInt() % 5 == 0) {
            return null;
        }
        int length = RandomUtil.random.nextInt(30) + 1;
        List<Literal> list = new ArrayList<>(length);
        for (int i = 0; i < length; ++i) {
            list.add(randomLiteral(nonNull));
        }
        return list;
    }

    static Disjunction[] randomArrayDisjunctions() {
        List<Disjunction> list = randomListDisjunctions();
        return list == null ? null : list.toArray(new Disjunction[0]);
    }

    static List<Disjunction> randomListDisjunctions() {
        return randomListDisjunctions(false);
    }

    static List<Disjunction> randomListDisjunctions(boolean nonNull) {
        if (!nonNull && RandomUtil.random.nextInt() % 5 == 0) {
            return null;
        }
        int length = RandomUtil.random.nextInt(30) + 1;
        List<Disjunction> list = new ArrayList<>(length);
        for (int i = 0; i < length; ++i) {
            list.add(new Disjunction(randomListLiterals(nonNull)));
        }
        return list;
    }

    static Conjunction[] randomArrayConjunctions() {
        List<Conjunction> list = randomListConjunctions();
        return list == null ? null : list.toArray(new Conjunction[0]);
    }

    static List<Conjunction> randomListConjunctions() {
        return randomListConjunctions(false);
    }

    static List<Conjunction> randomListConjunctions(boolean nonNUll) {
        if (!nonNUll && RandomUtil.random.nextInt() % 5 == 0) {
            return null;
        }
        int length = RandomUtil.random.nextInt(30) + 1;
        List<Conjunction> list = new ArrayList<>(length);
        for (int i = 0; i < length; ++i) {
            list.add(new Conjunction(randomListLiterals(nonNUll)));
        }
        return list;
    }

    static ConjunctiveNormalForm randomConjunctiveNormalForm() {
        return randomConjunctiveNormalForm(false);
    }

    static ConjunctiveNormalForm randomConjunctiveNormalForm(boolean nonNUll) {
        if (!nonNUll && RandomUtil.random.nextInt() % 5 == 0) {
            return null;
        }
        return new ConjunctiveNormalForm(randomListDisjunctions(nonNUll));
    }

    static DisjunctiveGeneralForm randomDisjunctiveNormalForm() {
        return randomDisjunctiveNormalForm(false);
    }

    static DisjunctiveGeneralForm randomDisjunctiveNormalForm(boolean nonNUll) {
        if (!nonNUll && RandomUtil.random.nextInt() % 5 == 0) {
            return null;
        }
        return new DisjunctiveGeneralForm(randomListConjunctions(nonNUll));
    }

    static Expression[] randomArrayExpressions() {
        List<Conjunction> list = randomListConjunctions();
        return list == null ? null : list.toArray(new Conjunction[0]);
    }

    static List<Expression> randomListExpressions() {
        return randomListExpressions(false, 0);
    }

    static List<Expression> randomListExpressions(boolean nonNUll, int height) {
        if (!nonNUll && RandomUtil.random.nextInt() % 5 == 0) {
            return null;
        }
        int length = RandomUtil.random.nextInt(3) + 1;
        List<Expression> list = new ArrayList<>(length);
        for (int i = 0; i < length; ++i) {
            Expression expression;
            switch (RandomUtil.random.nextInt(50) % (8 + height * 3)) {
                case 0: {
                    expression = new Conjunction(randomListLiterals(nonNUll));
                    break;
                }
                case 1: {
                    expression = new Disjunction(randomListLiterals(nonNUll));
                    break;
                }
                case 2: {
                    expression = randomConjunctiveNormalForm(nonNUll);
                    break;
                }
                case 3: {
                    expression = randomDisjunctiveNormalForm(nonNUll);
                    break;
                }
                case 4: {
                    expression = new ConjunctiveGeneralForm(randomListExpressions(nonNUll, height + 1));
                    break;
                }
                case 5: {
                    expression = new DisjunctiveGeneralForm(randomListExpressions(nonNUll, height + 1));
                    break;
                }
                default: {
                    expression = randomLiteral(nonNUll);
                }
            }
            list.add(expression);
        }
        return list;
    }

}
