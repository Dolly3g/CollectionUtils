import java.util.*;

public class CollectionUtil {
	public static List<Integer> map(List<Integer> list, ListMapper listMapper) {

		List<Integer> result = new ArrayList();
		System.out.println("Map");
		for(Integer ele : list) {
			Integer i = (Integer)listMapper.mapCallback(ele,0,list);
			result.add(i);
		}
		return result;
	}
}