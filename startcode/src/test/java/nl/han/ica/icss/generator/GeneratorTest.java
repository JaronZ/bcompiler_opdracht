package nl.han.ica.icss.generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneratorTest {
    private Generator sut;

    @BeforeEach
    void setup() {
        sut = new Generator();
    }

    @Test
    void testLevel2() {
        String exp = Fixtures.generatedLevel2();
        String act = sut.generate(Fixtures.transformedLevel2());
        assertEquals(exp, act);
    }

    @Test
    void testLevel3() {
        String exp = Fixtures.generatedLevel3();
        String act = sut.generate(Fixtures.transformedLevel3());
        assertEquals(exp, act);
    }

    @Test
    void testScopes() {
        String exp = Fixtures.generatedScopes();
        String act = sut.generate(Fixtures.transformedScopes());
        assertEquals(exp, act);
    }

    @Test
    void testExpressions() {
        String exp = Fixtures.generatedExpressions();
        String act = sut.generate(Fixtures.transformedExpressions());
        assertEquals(exp, act);
    }

    @Test
    void testSelectors() {
        String exp = Fixtures.generatedSelectors();
        String act = sut.generate(Fixtures.transformedSelectors());
        assertEquals(exp, act);
    }
}
