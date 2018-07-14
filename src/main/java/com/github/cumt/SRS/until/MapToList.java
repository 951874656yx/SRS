package com.github.cumt.SRS.until;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MapToList<T> {
	
	/**
	 * @method 将Map集合转换为List集合
	 * @param map
	 * @return List<T>
	 */
	public List<T> mapToList(HashMap<String, T> map){
		if (map==null) {
			return null;
		}
		ArrayList<T> list = new ArrayList<T>();
		for (T t : map.values()) {
			list.add(t);
		}
		return list;
	}
}
