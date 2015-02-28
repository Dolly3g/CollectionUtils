import java.util.*;

public class CollectionUtil {
	public static<E> List<E> map(List<E> list, ListMapper listMapper) {
		List<E> result = new ArrayList();
		int index = -1;
		for(E ele : list) {
			E i = (E)listMapper.callback(ele,++index,list);
			result.add(i);
		}
		return result;
	}

	public static<E> List<E> filter(List<E> list, ListFilter listFilter) {
		List<E> result = new ArrayList();
		int index = -1;
		for(E ele : list) {
			boolean flag = listFilter.predicate(ele,++index,list);
			if(flag)
				result.add(ele);
		}
		return result;
	}
}