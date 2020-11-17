package com.yj.t_2020_11;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Character.isDigit;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class StreamTest {
		public static void main(String[] args) {
				List<String> collected = Stream
								.of("a", "b", "c")
								.collect(toList());
				System.out.println(collected.toString());
				List<String> collected2 = Stream
								.of("a", "b", "hello")
								.map(string -> string.toUpperCase())
								.collect(toList());
				System.out.println(collected2.toString());
				List<String> beginningWithNumbers = Stream
								.of("a", "1abc", "abc1")
								.filter(value -> isDigit(value.charAt(0)))
								.collect(toList());
				System.out.println(beginningWithNumbers.toString());
				List<Integer> together = Stream
								.of(asList(1, 2), asList(3, 4))
								.flatMap(numbers -> numbers.stream())
								.collect(toList());
				System.out.println(together.toString());
				List<Integer> list = Arrays.asList(3, 5, 2, 9, 1);
				int maxInt = list
								.stream()
								.max(Integer::compareTo)
								.get();
				int minInt = list
								.stream()
								.min(Integer::compareTo)
								.get();
				System.out.println("maxInt: " + maxInt + ",minInt: " + minInt);
				int result = Stream
								.of(1, 2, 3, 4)
								.reduce(0, (acc, element) -> acc + element);
				System.out.println(result);
				int result2 = Stream
								.of(1, 2, 3, 4)
								.reduce(1, (acc, element) -> acc * element);
				System.out.println(result2);
		}
}
