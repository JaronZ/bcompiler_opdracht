package nl.han.ica.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

public class HANStackTest {
    private HANStack<String> sut;

    @BeforeEach
    void setup() {
        sut = new HANStack<>();
    }

    @Test
    void testPushAddsOneItemToStack() {
        // Arrange
        String value = "value";

        // Act
        sut.push(value);

        // Assert
        assertEquals(value, sut.peek());
    }

    @Test
    void testPopReturnsLastItemOfStackWithSizeTwo() {
        // Arrange
        sut.push("a");
        String value = "b";
        sut.push(value);

        // Act
        String last = sut.pop();

        // Assert
        assertEquals(value, last);
    }

    @Test
    void testPopRemovesLastItemOfStackWithSizeTwo() {
        // Arrange
        sut.push("a");
        String value = "b";
        sut.push(value);

        // Act
        sut.pop();

        // Assert
        assertNotEquals(value, sut.peek());
    }

    @Test
    void testPopThrowsNoSuchElementExceptionWithEmptyStack() {
        // Assert
        assertThrows(EmptyStackException.class, () -> {
            // Act
            sut.pop();
        });
    }

    @Test
    void testPeekReturnsLastItemOfStackWithSizeOne() {
        // Arrange
        String value = "a";
        sut.push(value);

        // Act
        String last = sut.peek();

        // Assert
        assertEquals(value, last);
    }

    @Test
    void testPeekReturnsLastItemOfStackWithSizeTwo() {
        // Arrange
        sut.push("a");
        String value = "b";
        sut.push(value);

        // Act
        String last = sut.peek();

        // Assert
        assertEquals(value, last);
    }

    @Test
    void testPeekThrowsNoSuchElementExceptionWithEmptyStack() {
        // Assert
        assertThrows(EmptyStackException.class, () -> {
            // Act
            sut.peek();
        });
    }
}
