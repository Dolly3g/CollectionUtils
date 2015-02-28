import java.util.*;

public class CollectionUtil {
	public static<E> List<E> map(List<E> list, ListMapper listMapper) {
		List<E> result = new ArrayList();
		for(E ele : list) {
			E i = (E)listMapper.mapCallback(ele,0,list);
			result.add(i);
		}
		return result;
	}
}