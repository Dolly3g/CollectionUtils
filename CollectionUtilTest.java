import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

interface ListMapper<E> {
	E callback(E ele, int index, List<E> list);
}

interface ListFilter<E> {
	boolean predicate(E ele, int index, List<E> list);
}

class SquareMapper implements ListMapper<Integer> {
	public Integer callback (Integer ele, int index, List<Integer> list){
		return ele.intValue()*ele.intValue();
	}
}

class LowerCaseMapper implements ListMapper<String> {
	public String callback (String ele, int index, List<String> list){
		return ele.toLowerCase();
	}
}

class EvenFilter implements ListFilter<Integer> {
	public boolean predicate (Integer ele, int index, List<Integer> list){
		return (ele.intValue() % 2 == 0);
	}
}


public class CollectionUtilTest {
	@Test
	public void map_returns_an_Integer_List_after_Sqauring_on_the_given_list(){
		ListMapper listMapper = new SquareMapper();
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
		ListMapper listMapper = new LowerCaseMapper();
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

	@Test
	public void filter_returns_an_Integer_List_after_filtering_evens_from_the_given_list(){
		ListFilter listFilter = new EvenFilter();
		List<Integer> names = new ArrayList<Integer>();
		List<Integer> expected = new ArrayList<Integer>();
		
		names.add(1);
		names.add(2);
		names.add(4);
		names.add(9);

		expected.add(2);
		expected.add(4);

		List<Integer> result = CollectionUtil.<Integer>filter(names,listFilter);
		assertEquals(expected.get(0),result.get(0));
		assertEquals(expected.get(1),result.get(1));
	}
}