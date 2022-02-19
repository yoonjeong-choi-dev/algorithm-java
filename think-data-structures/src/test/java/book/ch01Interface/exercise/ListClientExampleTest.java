package book.ch01Interface.exercise;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import java.util.List;

public class ListClientExampleTest {
    @Test
    public void testListClientExample() {
        ListClientExample ex = new ListClientExample();

        List<Integer> list = ex.getList();
        assertThat(list, instanceOf(List.class));
    }
}