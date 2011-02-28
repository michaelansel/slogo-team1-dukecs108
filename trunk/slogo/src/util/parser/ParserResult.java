package util.parser;

import java.util.ArrayList;
import java.util.List;

public class ParserResult {
	private List<Object> myObjects;

	public ParserResult() {
		myObjects = new ArrayList<Object>();
	}

	public ParserResult(List<Object> objects) {
		myObjects = objects;
	}

	public ParserResult(Object object) {
		this();
		add(object);
	}

	public void add(Object token) {
		myObjects.add(token);
	}

	protected void clearList() {
		myObjects.clear();
	}

	public List<Object> getList() {
		return new ArrayList<Object>(myObjects);
	}

	public void merge(ParserResult result) {
		myObjects.addAll(result.getList());
	}

	@Override
	public String toString() {
		return String.format("ParserResult(%s)", myObjects.toString());
	}
}
