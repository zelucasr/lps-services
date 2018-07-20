package com.ufrn.social.chart;

import java.util.ArrayList;

import org.primefaces.model.chart.ChartModel;

import com.ufrn.social.model.ChartHelper;

public abstract class ChartCreator {
	
	private String chartType;
	
	public ChartCreator(String chartType){
		this.chartType = chartType;
	}
	
	public abstract ChartModel mountGraph(ArrayList<ChartHelper> labels, String title, String legendPosition, boolean showTip, boolean showLabels, String dataFormat, ArrayList<String> colors); //método para montar o gráfico

	public String getChartType() {
		return chartType;
	}

	public void setChartType(String chartType) {
		this.chartType = chartType;
	}
}
