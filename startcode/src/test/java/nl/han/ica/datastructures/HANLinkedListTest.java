package nl.han.ica.datastructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HANLinkedListTest {
    private HANLinkedList<String> sut;

    @BeforeEach
    void setup() {
        sut = new HANLinkedList<>();
    }

    @Test
    void testAddFirstAddsValueToFrontForEmptyList() {
        // Arrange
        String value = "a string";

        // Act
        sut.addFirst(value);

        // Assert
        assertEquals(value, sut.getFirst());
    }

    @Test
    void testAddFirstAddsValueToFrontForListOfSizeTwo() {
        // Arrange
        sut.addFirst("a");
        sut.addFirst("b");
        String value = "c";

        // Act
        sut.addFirst(value);

        // Assert
        assertEquals(value, sut.getFirst());
    }

    @Test
    void testAddFirstAddsValueToFrontForListWithDuplicateValue() {
        // Arrange
        String value = "duplicate";
        sut.addFirst(value);

        // Act
        sut.addFirst(value);

        // Assert
        assertEquals(value, sut.getFirst());
        assertEquals(value, sut.get(1));
    }

    @Test
    void testClearClearsListWithEmptyList() {
        // Act
        sut.clear();

        // Assert
        assertEquals(0, sut.getSize());
    }

    @Test
    void testClearClearsListWithListOfSizeTwo() {
        // Arrange
        sut.addFirst("a");
        sut.addFirst("b");

        // Act
        sut.clear();

        // Assert
        assertEquals(0, sut.getSize());
    }

    @Test
    void testInsertInsertsItemIntoEmptyList() {
        // Arrange
        String value = "value";

        // Act
        sut.insert(0, value);

        // Assert
        assertEquals(value, sut.getFirst());
    }

    @Test
    void testInsertInsertsItemAtStartIntoListOfSizeTwo() {
        // Arrange
        sut.addFirst("a");
        sut.addFirst("b");
        String value = "c";

        // Act
        sut.insert(0, value);

        // Assert
        assertEquals(value, sut.getFirst());
    }

    @Test
    void testInsertInsertsItemAtEndIntoListOfSizeTwo() {
        // Arrange
        sut.addFirst("a");
        sut.addFirst("b");
        String value = "c";

        // Act
        sut.insert(2, value);

        // Assert
        assertEquals(value, sut.get(2));
    }

    @Test
    void testInsertInsertsItemAtIndexOneIntoListOfSizeTwo() {
        // Arrange
        sut.addFirst("a");
        sut.addFirst("b");
        String value = "c";

        // Act
        sut.insert(1, value);

        // Assert
        assertEquals(value, sut.get(1));
    }

    @Test
    void testInsertThrowsIndexOutOfBoundsExceptionWhenInsertingAtOneWithEmptyList() {
        // Arrange
        String value = "value";

        // Assert
        assertThrows(IndexOutOfBoundsException.class, () -> {
            // Act
            sut.insert(1, value);
        });
    }

    @Test
    void testInsertThrowsIndexOutOfBoundsExceptionWhenInsertingAtThreeWithListOfSizeTwo() {
        // Arrange
        sut.addFirst("a");
        sut.addFirst("b");
        String value = "c";

        // Assert
        assertThrows(IndexOutOfBoundsException.class, () -> {
            // Act
            sut.insert(3, value);
        });
    }

    @Test
    void testDeleteDeletesFirstItemWithListOfSizeThree() {
        // Arrange
        sut.addFirst("a");
        sut.addFirst("b");
        sut.addFirst("c");

        // Act
        sut.delete(0);

        // Assert
        assertEquals(2, sut.getSize());
        assertEquals("b", sut.getFirst());
        assertEquals("a", sut.get(1));
    }

    @Test
    void testDeleteDeletesLastItemWthListOfSizeThree() {
        // Arrange
        sut.addFirst("a");
        sut.addFirst("b");
        sut.addFirst("c");

        // Act
        sut.delete(2);

        // Assert
        assertEquals(2, sut.getSize());
        assertEquals("c", sut.getFirst());
        assertEquals("b", sut.get(1));
    }

    @Test
    void testDeleteDeletesItemAtIndexOneWithListOfSizeThree() {
        // Arrange
        sut.addFirst("a");
        sut.addFirst("b");
        sut.addFirst("c");

        // Act
        sut.delete(1);

        // Assert
        assertEquals(2, sut.getSize());
        assertEquals("c", sut.getFirst());
        assertEquals("a", sut.get(1));
    }

    @Test
    void testDeleteThrowsIndexOutOfBoundsExceptionWithEmptyList() {
        // Assert
        assertThrows(IndexOutOfBoundsException.class, () -> {
            // Act
            sut.delete(0);
        });
    }

    @Test
    void testDeleteThrowsIndexOutOfBoundsExceptionWhenDeletingIndexTwoWithListOfSizeTwo() {
        // Arrange
        sut.addFirst("a");
        sut.addFirst("b");

        // Assert
        assertThrows(IndexOutOfBoundsException.class, () -> {
            // Act
            sut.delete(2);
        });
    }

    @Test
    void testGetReturnsFirstItemWhenGettingIndexZeroWithListOfSizeThree() {
        // Arrange
        String value = "c";
        sut.addFirst("a");
        sut.addFirst("b");
        sut.addFirst(value);

        // Act
        String item = sut.get(0);

        // Assert
        assertEquals(value, item);
    }

    @Test
    void testGetReturnsThirdItemWhenGettingIndexTwoWithListOfSizeThree() {
        // Arrange
        String value = "a";
        sut.addFirst(value);
        sut.addFirst("b");
        sut.addFirst("c");

        // Act
        String item = sut.get(2);

        // Assert
        assertEquals(value, item);
    }

    @Test
    void testGetReturnsSecondItemWhenGettingIndexOneWithListOfSizeThree() {
        // Arrange
        String value = "b";
        sut.addFirst("a");
        sut.addFirst(value);
        sut.addFirst("c");

        // Act
        String item = sut.get(1);

        // Assert
        assertEquals(value, item);
    }

    @Test
    void testGetThrowsIndexOutOfBoundsExceptionWhenGettingIndexZeroWithEmptyList() {
        // Assert
        assertThrows(IndexOutOfBoundsException.class, () -> {
            // Act
            sut.get(0);
        });
    }

    @Test
    void testGetThrowsIndexOutOfBoundsExceptionWhenGettingIndexMinusOneWithEmptyList() {
        // Assert
        assertThrows(IndexOutOfBoundsException.class, () -> {
            // Act
            sut.get(-1);
        });
    }

    @Test
    void testGetThrowsIndexOutOfBoundsExceptionWhenGettingIndexMinusOneWithListOfSizeTwo() {
        // Arrange
        sut.addFirst("a");
        sut.addFirst("b");

        // Assert
        assertThrows(IndexOutOfBoundsException.class, () -> {
            // Act
            sut.get(-1);
        });
    }

    @Test
    void testRemoveFirstRemovesFirstWithListOfSizeOne() {
        // Arrange
        sut.addFirst("a");

        // Act
        sut.removeFirst();

        // Assert
        assertEquals(0, sut.getSize());
    }

    @Test
    void testRemoveFirstRemovesFirstWithListOfSizeTwo() {
        // Arrange
        sut.addFirst("a");
        sut.addFirst("b");

        // Act
        sut.removeFirst();

        // Assert
        assertEquals(1, sut.getSize());
        assertEquals("a", sut.getFirst());
    }

    @Test
    void testRemoveFirstThrowsNoSuchElementExceptionWithEmptyList() {
        // Assert
        assertThrows(NoSuchElementException.class, () -> {
            // Act
            sut.removeFirst();
        });
    }

    @Test
    void testGetFirstReturnsFirstWithListOfSizeOne() {
        // Arrange
        String value = "value";
        sut.addFirst(value);

        // Act
        String first = sut.getFirst();

        // Assert
        assertEquals(value, first);
    }

    @Test
    void testGetFirstReturnsFirstWithListOfSizeTwo() {
        // Arrange
        sut.addFirst("a");
        String value = "b";
        sut.addFirst(value);

        // Act
        String first = sut.getFirst();

        // Assert
        assertEquals(value, first);
    }

    @Test
    void testGetFirstThrowsNoSuchElementExceptionWithEmptyList() {
        // Assert
        assertThrows(NoSuchElementException.class, () -> {
            // Act
            sut.getFirst();
        });
    }

    @Test
    void testGetSizeReturnsZeroWithListOfSizeZero() {
        // Act
        int size = sut.getSize();

        // Assert
        assertEquals(0, size);
    }

    @Test
    void testGetSizeReturnsOneWithListOfSizeOne() {
        // Arrange
        sut.addFirst("a");

        // Act
        int size = sut.getSize();

        // Assert
        assertEquals(1, size);
    }

    @Test
    void testGetSizeReturnsTwoWithListOfSizeTwo() {
        // Arrange
        sut.addFirst("a");
        sut.addFirst("b");

        // Act
        int size = sut.getSize();

        // Assert
        assertEquals(2, size);
    }
}
