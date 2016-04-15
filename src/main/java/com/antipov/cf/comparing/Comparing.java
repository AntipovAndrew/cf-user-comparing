package com.antipov.cf.comparing;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew Antipov
 * Date: 31.03.2016
 * Time: 0:38
 */
public class Comparing {
	private String firstHandle;
	private String secondHandle;

	public String getFirstHandle() {
		return firstHandle;
	}

	public void setFirstHandle(String firstHandle) {
		this.firstHandle = firstHandle;
	}

	public String getSecondHandle() {
		return secondHandle;
	}

	public void setSecondHandle(String secondHandle) {
		this.secondHandle = secondHandle;
	}

	public boolean isValid() {
		return !(isEmpty(firstHandle) || isEmpty(secondHandle) || firstHandle.equals(secondHandle));
	}

	private boolean isEmpty(String s) {
		return s == null || s.isEmpty();
	}
}
