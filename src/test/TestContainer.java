package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TestContainer {
	Collection<String> testList = new ArrayList<String>(
			Arrays.asList("i love you".split(" ")));
	Map<Integer, String> testMap = new HashMap<Integer, String>();

	TestContainer() {
		testMap.put(1, "I");
		testMap.put(2, "love");
		testMap.put(3, "you");

		print(testMap);
		print(testList);

		print("Using Iterable Interface");
		for (String str : testList) {
			print(str);
		}

		print("Geting the values collection of a Map");
		Collection<String> mapValues = testMap.values();
		for (String str : mapValues) {
			print(str);
		}

		print("Geting the Iterator of a collection");
		Iterator i = mapValues.iterator();
		while (i.hasNext()) {
			print(i.next());
		}
	}

	private void print(Object next) {
		// TODO Auto-generated method stub
		
	}

	private void print(String string) {
		// TODO Auto-generated method stub
		
	}

	private void print(Map<Integer, String> testMap2) {
		// TODO Auto-generated method stub
		
	}
}