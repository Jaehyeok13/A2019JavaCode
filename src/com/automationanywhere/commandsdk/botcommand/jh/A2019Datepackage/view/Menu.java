package com.automationanywhere.commandsdk.botcommand.jh.A2019Datepackage.view;

import java.util.Scanner;

import com.automationanywhere.commandsdk.botcommand.jh.A2019Datepackage.controller.DayofWeeks;

public class Menu {
	DayofWeeks df = new DayofWeeks();
	private Scanner sc = new Scanner(System.in);
	
	public void method() {
		String outday = df.inputday("2020년9일13");
		System.out.println(outday);
	}
}
