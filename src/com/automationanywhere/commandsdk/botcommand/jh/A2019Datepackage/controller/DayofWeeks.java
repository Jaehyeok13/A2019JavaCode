package com.automationanywhere.commandsdk.botcommand.jh.A2019Datepackage.controller;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayofWeeks {
	Scanner sc = new Scanner(System.in);
	public void outDay() {
		System.out.println("====== 입력 날짜 요일 출력 받기 ========");
	
		System.out.print("날짜입력 : ");
		String date = sc.nextLine();
		
		// 정규식 사용
		Pattern p = Pattern.compile("\\W{2,}");
		Matcher m = p.matcher(date);
		
		
		boolean key = false;
		if(m.find()) {
			key = true;
		}else {
			System.out.println("구분자가 2개가 아니다.");
		}
		
		System.out.println("결과" +key);
		
		
		int year = 0,month = 0,day = 0;
		
		String[] sDate = date.split("\\W");
		int count = 0;
		
		for(String ymd : sDate) {
			if(count == 0) {
				year = Integer.parseInt(ymd);
			}else if(count == 1) {
				month = Integer.parseInt(ymd);
			}else {
				day = Integer.parseInt(ymd);
			}
			count++;
		}
        
        char dayOfWeek = DayofDate(year, month, day);
		
        System.out.println(dayOfWeek + "요일");
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
