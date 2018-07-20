package com.ufrn.social.chart;

import java.util.ArrayList;

import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.LineChartModel;

import com.ufrn.social.model.ChartHelper;

public class LineChartCreator extends ChartCreator {

	public LineChartCreator(String chartType) {
		super(chartType);
	}

	@Override
	public ChartModel mountGraph(ArrayList<ChartHelper> labels, String title, String legendPosition, boolean showTip,
			boolean showLabels, String dataFormat, ArrayList<String> colors) {
		//Montar gráfico de linha
		return new LineChartModel();
	}

}
