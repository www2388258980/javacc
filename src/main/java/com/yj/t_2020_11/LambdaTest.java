package com.yj.t_2020_11;

import java.awt.event.ActionListener;
import java.util.function.BinaryOperator;

public class LambdaTest {
		public static void main(String[] args) {
				Runnable noArguments = () -> System.out.println("Hello World");
				new Thread(noArguments).start();
				ActionListener oneArgument = event -> System.out.println("button clicked");
				Runnable multiStatement = () -> {
						System.out.print("Hello");
						System.out.println(" World");
				};
				BinaryOperator<Long> add = (x, y) -> x + y;
				BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;
		}
}
