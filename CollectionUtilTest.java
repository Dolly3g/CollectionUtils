import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

class SquareMapper implements ListMapper<Integer,Integer> {
	public Integer callback (Integer ele, int index, List<Integer> list){
		return ele.intValue()*ele.intValue();
	}
}

class SpellingMapper implements ListMapper<Integer,String> {
	public String callback (Integer ele, int index, List<Integer> list){
		switch(ele){
			case 1: return "One";
			case 2: return "Two";
			case 3: return "Three";
			default: return "";
		}
	}
}

class LowerCaseMapper implements ListMapper<String,String> {
	public String callback (String ele, int index, List<String> list){
		return ele.toLowerCase();
	}
}

class EvenFilter implements ListFilter<Integer> {
	public boolean predicate (Integer ele, int index, List<Integer> list){
		return (ele.intValue() % 2 == 0);
	}
}

class SumReducer implements ListReducer<Integer,Integer> {
	public Integer reducer (Integer pv, Integer cv , int index, List<Integer> list) {
		return pv.intValue()+cv.intValue();
	}
}

public class CollectionUtilTest {
	@Test
	public void map_returns_an_Integer_List_after_Sqauring_on_the_given_list(){
		ListMapper<Integer,Integer> listMapper = new SquareMapper();
		List<Integer> numbers = new ArrayList<Integer>();
		List<Integer> expected = new ArrayList<Integer>();
		
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);

		expected.add(1);
		expected.add(4);
		expected.add(9);

		List<Integer> result = CollectionUtil.<Integer,Integer>map(numbers,listMapper);
		assertEquals(expected.get(0),result.get(0));
		assertEquals(expected.get(1),result.get(1));
	}

	@Test
	public void map_returns_a_String_List_after_lowerCasing_on_the_given_list(){
		ListMapper<String,String> listMapper = new LowerCaseMapper();
		List<String> names = new ArrayList<String>();
		List<String> expected = new ArrayList<String>();
		
		names.add("BOB");
		names.add("AlicE");

		expected.add("bob");
		expected.add("alice");

		List<String> result = CollectionUtil.<String,String>map(names,listMapper);
		assertEquals(expected.get(0),result.get(0));
		assertEquals(expected.get(1),result.get(1));
	}

	@Test
	public void map_returns_a_list_of_spellings_of_given_numbers(){
		ListMapper<Integer,String> listMapper = new SpellingMapper();
		List<Integer> numbers = new ArrayList<Integer>();
		List<String> expected = new ArrayList<String>();
		
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);

		expected.add("One");
		expected.add("Two");
		expected.add("Three");

		List<String> result = CollectionUtil.<Integer,String>map(numbers,listMapper);
		assertEquals(expected.get(0),result.get(0));
		assertEquals(expected.get(1),result.get(1));
	}

	@Test
	public void filter_returns_an_Integer_List_after_filtering_evens_from_the_given_list(){
		ListFilter<Integer> listFilter = new EvenFilter();
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
	
	@Test
	public void reduce_returns_the_sum_of_all_numbers (){
		ListReducer<Integer,Integer> listReducer = new SumReducer();
		List<Integer> numbers = new ArrayList<Integer>();
		Integer initial = new Integer(0);
		Integer expected = new Integer(6);
		
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);

		Integer sum = CollectionUtil.<Integer,Integer>reduce(numbers,listReducer,initial);		
		assertEquals(expected,sum);
	}
}