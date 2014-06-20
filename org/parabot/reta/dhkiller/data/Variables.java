package org.parabot.reta.dhkiller.data;

import org.parabot.environment.scripts.framework.Strategy;

import java.util.ArrayList;

public class Variables {
	private static ArrayList<Strategy> strategies = new ArrayList<>();

	public static void setStrategy(Strategy e) {
		strategies.add(e);
	}

	public static ArrayList<Strategy> getStrategyArray() {
		return strategies;

	}
}
