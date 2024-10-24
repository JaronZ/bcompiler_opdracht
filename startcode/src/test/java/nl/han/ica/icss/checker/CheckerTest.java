package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.AST;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckerTest {
    private Checker sut;

    @BeforeEach
    void setup() {
        sut = new Checker();
    }

    @Test
    void testLevel0() {
        AST exp = Fixtures.checkedLevel0();
        AST act = Fixtures.uncheckedLevel0();
        sut.check(act);
        assertEquals(exp, act);
        assertEquals(act.getErrors(), List.of());
    }

    @Test
    void testLevel1() {
        AST exp = Fixtures.checkedLevel1();
        AST act = Fixtures.uncheckedLevel1();
        sut.check(act);
        assertEquals(exp, act);
        assertEquals(act.getErrors(), List.of());
    }

    @Test
    void testLevel2() {
        AST exp = Fixtures.checkedLevel2();
        AST act = Fixtures.uncheckedLevel2();
        sut.check(act);
        assertEquals(exp, act);
        assertEquals(act.getErrors(), List.of());
    }

    @Test
    void testLevel3() {
        AST exp = Fixtures.checkedLevel3();
        AST act = Fixtures.uncheckedLevel3();
        sut.check(act);
        assertEquals(exp, act);
        assertEquals(act.getErrors(), List.of());
    }

    @Test
    void testScopes() {
        AST exp = Fixtures.checkedScopes();
        AST act = Fixtures.uncheckedScopes();
        sut.check(act);
        assertEquals(exp, act);
        assertEquals(act.getErrors(), List.of());
    }

    @Test
    void testExpressions() {
        AST exp = Fixtures.checkedExpressions();
        AST act = Fixtures.uncheckedExpressions();
        sut.check(act);
        assertEquals(exp, act);
        assertEquals(act.getErrors(), List.of());
    }
}
