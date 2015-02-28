import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

interface ListMapper<E> {
	E mapCallback(E ele, int index, List<E> list);
}

class IntegerMapper implements ListMapper<Integer> {
	public Integer mapCallback (Integer ele, int index, List<Integer> list){
		return ele.intValue()*ele.intValue();
	}
}

class StringMapper implements ListMapper<String> {
	public String mapCallback (String ele, int index, List<String> list){
		return ele.toLowerCase();
	}
}


public class CollectionUtilTest {
	@Test
	public void map_returns_an_Integer_List_after_Sqauring_on_the_given_list(){
		ListMapper listMapper = new IntegerMapper();
		List<Integer> numbers = new ArrayList<Integer>();
		List<Integer> expected = new ArrayList<Integer>();
		
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);

		expected.add(1);
		expected.add(4);
		expected.add(9);

		List<Integer> result = CollectionUtil.<Integer>map(numbers,listMapper);
		assertEquals(expected.get(0),result.get(0));
		assertEquals(expected.get(1),result.get(1));
	}

	@Test
	public void map_returns_a_String_List_after_lowerCasing_on_the_given_list(){
		ListMapper listMapper = new StringMapper();
		List<String> names = new ArrayList<String>();
		List<String> expected = new ArrayList<String>();
		
		names.add("BOB");
		names.add("AlicE");

		expected.add("bob");
		expected.add("alice");

		List<String> result = CollectionUtil.<String>map(names,listMapper);
		assertEquals(expected.get(0),result.get(0));
		assertEquals(expected.get(1),result.get(1));
	}
}