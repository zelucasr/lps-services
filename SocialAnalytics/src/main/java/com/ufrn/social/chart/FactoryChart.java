package com.ufrn.social.chart;

public class FactoryChart {
	
	public ChartCreator getChart(String type){
		if(type.equals("Pie")){
			return new PieChartCreator(type);
		} else if(type.equals("Line")){
			return new LineChartCreator(type);
		} else {
			return null;
		}
	}

}
