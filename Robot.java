package com.robot.service;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Robot {

	private int X, Y, _x, _y, north = 0, east = 1, west = 3, south = 2, dir = north;
	String path = "";
	Set<String> visited = new HashSet<>();

	public void setFirstInput(String input) {
		String[] strarr = getInputInArray(input);
		X = Integer.parseInt(strarr[0]);
		Y = Integer.parseInt(strarr[1]);
	}

	public void setSecondInput(String input) {
		String[] strarr = getInputInArray(input);
		_x = Integer.parseInt(strarr[0]);
		_y = Integer.parseInt(strarr[1]);
		dir = getInputDir(strarr[2]);
	}

	public void setThirdInput(String input) {
		path = input;
	}

	public void startProcessing() {
		boolean currMove = false;
		for (int i = 0; i < path.length(); i++) {
			char c = path.charAt(i);
			if (c == 'R' || c == 'L')
				dir = getDir(dir, c);
			if (c == 'M') {
				if (dir == north)
					currMove = isValidMove(_x, _y + 1);
				if (dir == south)
					currMove = isValidMove(_x, _y - 1);
				if (dir == east)
					currMove = isValidMove(_x + 1, _y);
				if (dir == west)
					currMove = isValidMove(_x - 1, _y);
			}
			if(!currMove)
				break;
		}
	}

	private int getDir(int dir2, char c) {

		if (c == 'R')
			dir2 = (dir2 + 1) % 4;
		else
			dir2 = (4 + dir2 - 1) % 4;

		return dir2;
	}

	private int getInputDir(String string) {
		int val = north;
		if (string == "E")
			val = east;
		if (string == "W")
			val = west;
		if (string == "S")
			val = south;
		return val;
	}

	private boolean isVisited(int x, int y) {
		String xy = x + "" + y;
		if (visited.contains(xy)) {
			return true;
		}
		return false;
	}

	private String[] getInputInArray(String str) {
		return str.split(" ");
	}

	private boolean isValidMove(int x, int y) {
		String xy = x + "" + y;
		if (x <= X && y <= Y) {
			if (!isVisited(x, y)) {
				visited.add(xy);
				_x = x;
				_y = y;
				return true;
			}
		}
		return false;
	}
	
	public void printCurrXY() {
		System.out.println("X=" + _x + " : " + "Y=" + _y);
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		String firstinput = scanner.nextLine();
		String secondinput = scanner.nextLine();
		String thirdinput = scanner.nextLine();
		scanner.close();

		Robot robot = new Robot();
		robot.setFirstInput(firstinput);
		robot.setSecondInput(secondinput);
		robot.setThirdInput(thirdinput);
		robot.startProcessing();
		robot.printCurrXY();
	}
}
