package com.example.demo;

public class test {
	private static String s = "\\12";

	public static void main(String[] args) {
	    testOne(s);
	}

	private static void testOne(String s){
	    System.out.println(s);
	    System.out.println(s.contains("\\"));
	    System.out.println(s.matches("\\\\?\\d+"));
	}

	
}
