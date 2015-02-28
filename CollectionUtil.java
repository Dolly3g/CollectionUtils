import java.util.*;

interface ListMapper<E,K> {
	K callback(E ele, int index, List<E> list);
}

interface ListFilter<E> {
	boolean predicate(E ele, int index, List<E> list);
}

interface ListReducer<E,K> {
	K reducer(K pv, E cv, int index, List<E> list);
}

public class CollectionUtil {
	public static<E,K> List<K> map(List<E> list, ListMapper<E,K> listMapper) {
		List<K> result = new ArrayList<K>();
		int index = -1;
		for(E ele : list) {
			K i = listMapper.callback(ele,++index,list);
			result.add(i);
		}
		return result;
	}

	public static<E> List<E> filter(List<E> list, ListFilter<E> listFilter) {
		List<E> result = new ArrayList<E>();
		int index = -1;
		for(E ele : list) {
			boolean flag = listFilter.predicate(ele,++index,list);
			if(flag)
				result.add(ele);
		}
		return result;
	}

	public static<E,K> K reduce(List<E> list, ListReducer<E,K> listReducer, K pv) {
		int index = -1;
		for(E ele : list) {
			pv = listReducer.reducer(pv,ele,++index,list);
		}
		return pv;
	}

	
}
