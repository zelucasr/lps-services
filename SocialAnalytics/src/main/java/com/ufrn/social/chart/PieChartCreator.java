package com.ufrn.social.chart;

import java.util.ArrayList;

import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.PieChartModel;

import com.ufrn.social.model.ChartHelper;

public class PieChartCreator extends ChartCreator {

	public PieChartCreator(String chartType) {
		super(chartType);
	}

	@Override
	public ChartModel mountGraph(ArrayList<ChartHelper> labels, String title, String legendPosition, boolean showTip, boolean showLabels, String dataFormat, ArrayList<String> colors) {
		
		PieChartModel pieChart = new PieChartModel();
		
		for(ChartHelper label : labels){
			pieChart.set(label.getName(), label.getQuantity());
		}
		pieChart.setTitle(title);
		pieChart.setLegendPosition(legendPosition);
		pieChart.setShowDatatip(showTip);
		pieChart.setShowDataLabels(showLabels);
		pieChart.setDataFormat(dataFormat);
		
		String seriesColors= "";
		for(int i = 0; i<colors.size() ;i++){
			seriesColors += colors.get(i);
			if(i+1 < colors.size()){
				seriesColors += ",";
			}
		}
		pieChart.setSeriesColors(seriesColors);
		
		
		//labels list / title / legendposition / showlabels / dataformat / colors list
				
				
		        //show labels inside pie chart
				pieChart.setShowDataLabels(true);
		        //show label text  as 'value' (numeric) , others are 'label', 'percent' (default). Only one can be used.
				pieChart.setDataFormat("value");
				pieChart.setSeriesColors("afa,faa,ddd");
		
		return new PieChartModel();
	}

}
