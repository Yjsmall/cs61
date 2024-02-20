import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {

    @Test
    @DisplayName("ArrayDeque has no fields besides backing array and primitives")
    void noNonTrivialFields() {
        List<Field> badFields = Reflection.getFields(ArrayDeque.class)
                .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                .toList();

        assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
    }

    @Test
    void addTest() {
        Deque<String> l = new ArrayDeque<>();
        l.addLast("a");
        l.addLast("b");
        l.addFirst("c");
        assertThat(l.toList()).containsExactly("c", "a", "b").inOrder();
        l.addLast("d");
        l.addLast("e");
        l.addFirst("f");
        l.addLast("g");
        l.addLast("h");
        var list = l.toList();
        assertThat(list).containsExactly("f", "c", "a", "b", "d", "e", "g", "h").inOrder();
    }

    @Test
    void addOverTest() {
        Deque<String> l = new ArrayDeque<>();
        l.addLast("a");
        l.addLast("b");
        l.addFirst("c");
        assertThat(l.toList()).containsExactly("c", "a", "b").inOrder();
        l.addLast("d");
        l.addLast("e");
        l.addFirst("f");
        l.addLast("g");
        l.addLast("h");
        var list = l.toList();
        assertThat(list).containsExactly("f", "c", "a", "b", "d", "e", "g", "h").inOrder();
        l.addLast("z");
        assertThat(l.toList()).containsExactly("f", "c", "a", "b", "d", "e", "g", "h", "z").inOrder();
    }
    @Test
    void addRemoveTest() {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addFirst(0);
        assertThat(deque.toList()).containsExactly(0, 1, 2).inOrder();

        assertThat(deque.removeFirst()).isEqualTo(0);
        assertThat(deque.removeLast()).isEqualTo(2);

        var l = deque.toList();
        assertThat(deque.toList()).containsExactly(1);
    }

    @Test
    void addRemoveMultipleTest() {
        Deque<Character> deque = new ArrayDeque<>();
        deque.addLast('a');
        deque.addLast('b');
        deque.addFirst('c');
        assertThat(deque.toList()).containsExactly('c', 'a', 'b').inOrder();

        deque.removeFirst();
        deque.addLast('d');
        deque.addLast('e');
        deque.addFirst('f');
        assertThat(deque.toList()).containsExactly('f', 'a', 'b', 'd', 'e').inOrder();

        assertThat(deque.removeLast()).isEqualTo('e');
        assertThat(deque.removeFirst()).isEqualTo('f');

        assertThat(deque.toList()).containsExactly('a', 'b', 'd').inOrder();
    }

    @Test
    void edgeCasesTest() {
        Deque<String> deque = new ArrayDeque<>();
        assertThat(deque.isEmpty()).isTrue();

        deque.addFirst("a");
        assertThat(deque.isEmpty()).isFalse();
        assertThat(deque.size()).isEqualTo(1);

        assertThat(deque.removeFirst()).isEqualTo("a");
        assertThat(deque.isEmpty()).isTrue();
        assertThat(deque.size()).isEqualTo(0);
    }
    @Test
    void edgeLargeTest() {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 1000; i > 0; i--) {
            deque.addFirst(i);
        }

        for (int i = 0; i < 750; i++) {
            var val = deque.removeLast();
            assertThat(val).isEqualTo(1000 - i);
        }
    }

}
