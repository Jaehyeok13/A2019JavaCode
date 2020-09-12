package com.automationanywhere.commandsdk.botcommand.jh.A2019Datepackage.controller;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayofWeeks {
	Scanner sc = new Scanner(System.in);
	
	public String inputday(String input) {

		System.out.println(input);
		//           (\\W) 정규표현식 \W(특수문자를 제외한 모든 문자를 포함한다. _ 언더코어제외)
		//정규식표현식 이용 하여 년월일 확인    년           월(1~2 글자)01월 1월 구분자만 제대로 넣어주면 문제 없음 
		Pattern p = Pattern.compile("(\\d{1,4})(\\W)(\\d{1,2})(\\W)(\\d){1,2}");
		Matcher m = p.matcher(input);

		if (!m.find()) {
			return "YYYY-MM-DD or YYYY-M-DD or YYYY-M-D 형식 입력 값을 다시 확인 해주세요.";
		}

		char dayOfWeek = 0;
		int year = 0, month = 0, day = 0;

		String[] sDate = input.split("\\W");
		int count = 0;

		for (String ymd : sDate) {
//			System.out.println(ymd);
			if (count == 0) {
				year = Integer.parseInt(ymd);
			} else if (count == 1) {
				month = Integer.parseInt(ymd);
			} else {
				day = Integer.parseInt(ymd);
			}
			count++;
		}

		dayOfWeek = DayofDate(year, month, day);

		return dayOfWeek+"";
	}
	
	public  int MonthDays(int year, int month) {
        int dayOfMonth = 0;
        //윤년일 조건
        Boolean isLeapYear = ((year % 4 == 0) && (year % 100 !=0)) || year % 400 == 0;
        //31일 일 조건
        Boolean is31 = (month == 1) || (month == 3) || (month == 5) || (month == 7)
                || (month == 8) || (month == 10) || (month == 12);

        if (isLeapYear && month == 2) {
            dayOfMonth = 29;
        } else if (!isLeapYear && month == 2) {
            dayOfMonth = 28;
        } else if (is31) {
            dayOfMonth = 31;
        } else {
            dayOfMonth = 30;
        }
        return dayOfMonth;
    }

    public char DayofDate(int year, int month, int day){
        char dayOfWeek = 0;
        int totalDay = 0;
        for(int i = 1900; i <= year; i++) {
            //입력 년도 이전 까지는 12월 까지 다 더함
            if (i < year) {
                for(int j = 1; j <= 12; j++) {
                    totalDay += MonthDays(i, j);
                }
                //입력 년도는 입력 월 이전까지 더함
            } else {
                for(int j = 1; j < month; j++) {
                    totalDay += MonthDays(i, j);
                }
            }
        }
        //나머지 일 수를 구해진 전체 일수에 더함
        totalDay += day;
        //기준일인 1900년 1월 1일이 월요일이므로
        switch (totalDay % 7) {
            case 0:
                dayOfWeek = '일';
                break;
            case 1:
                dayOfWeek = '월';
                break;
            case 2:
                dayOfWeek = '화';
                break;
            case 3:
                dayOfWeek = '수';
                break;
            case 4:
                dayOfWeek = '목';
                break;
            case 5:
                dayOfWeek = '금';
                break;
            case 6:
                dayOfWeek = '토';
                break;
        }

        return dayOfWeek;
    }

	
}
