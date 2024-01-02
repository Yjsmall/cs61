import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.apache.commons.lang3.ArrayUtils.addFirst;

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

}
