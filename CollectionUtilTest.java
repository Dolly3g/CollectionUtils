import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

interface ListMapper<E> {
	E mapCallback(E ele, int index, List<E> list);
}

class MyClass implements ListMapper<Integer> {
	public Integer mapCallback (Integer ele, int index, List<Integer> list){
		return ele.intValue()*ele.intValue();
	}
}

public class CollectionUtilTest {
	@Test
	public void map_returns_a_list_after_performing_specified_operation_on_the_given_list(){
		ListMapper listMapper = new MyClass();
		List<Integer> numbers = new ArrayList<Integer>();
		List<Integer> expected = new ArrayList<Integer>();
		
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);

		expected.add(1);
		expected.add(4);
		expected.add(9);

		List<Integer> result = CollectionUtil.map(numbers,listMapper);
		assertEquals(expected.get(0),result.get(0));
		assertEquals(expected.get(1),result.get(1));
	}
}