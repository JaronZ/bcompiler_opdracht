package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.AST;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EvaluatorTest {
    private Evaluator sut;

    @BeforeEach
    void setup() {
        sut = new Evaluator();
    }

    @Test
    void testLevel0() {
        AST exp = Fixtures.transformedLevel0();
        AST act = Fixtures.checkedLevel0();
        sut.apply(act);
        assertEquals(exp, act);
    }

    @Test
    void testLevel1() {
        AST exp = Fixtures.transformedLevel1();
        AST act = Fixtures.checkedLevel1();
        sut.apply(act);
        assertEquals(exp, act);
    }

    @Test
    void testLevel2() {
        AST exp = Fixtures.transformedLevel2();
        AST act = Fixtures.checkedLevel2();
        sut.apply(act);
        assertEquals(exp, act);
    }

    @Test
    void testLevel3() {
        AST exp = Fixtures.transformedLevel3();
        AST act = Fixtures.checkedLevel3();
        sut.apply(act);
        assertEquals(exp, act);
    }

    @Test
    void testScopes() {
        AST exp = Fixtures.transformedScopes();
        AST act = Fixtures.checkedScopes();
        sut.apply(act);
        assertEquals(exp, act);
    }

    @Test
    void testExpressions() {
        AST exp = Fixtures.transformedExpressions();
        AST act = Fixtures.checkedExpressions();
        sut.apply(act);
        assertEquals(exp, act);
    }

    @Test
    void testSelectors() {
        AST exp = Fixtures.transformedSelectors();
        AST act = Fixtures.checkedSelectors();
        sut.apply(act);
        assertEquals(exp, act);
    }
}
