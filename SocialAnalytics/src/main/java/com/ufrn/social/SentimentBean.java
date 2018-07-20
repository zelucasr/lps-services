package com.ufrn.social;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import org.primefaces.model.chart.PieChartModel;

import com.ufrn.social.chart.ChartCreator;
import com.ufrn.social.chart.FactoryChart;
import com.ufrn.social.data.FactorySocialConnector;
import com.ufrn.social.data.SocialConnector;
import com.ufrn.social.model.ChartHelper;
import com.ufrn.social.model.Post;
import com.ufrn.social.request.HttpConnection;

@ManagedBean
public class SentimentBean {
	
	private String searchString;
	private int limit;
	private String user;
	private List<Post> posts;
	private int positivo;
	private int negativo;
	private int neutro;
	private boolean renderChart;
	private Date dateQuerySince;
	private Date dateQueryUntil;
	
	private PieChartModel pieChart;
	
	@PostConstruct
    public void init() {
		searchString = "";
		limit = 0;
		user = "";
		renderChart = false;
		posts = new ArrayList<Post>();
		pieChart = new PieChartModel();
	}
	
	public void mountGraph(){
		
		ArrayList<ChartHelper> labels = new ArrayList<ChartHelper>();
		
		//Labels
		labels.add(new ChartHelper("Positivo", positivo));
		labels.add(new ChartHelper("Negativo", negativo));
		labels.add(new ChartHelper("Neutro", neutro));
		
		//Cores
		ArrayList<String> colors = new ArrayList<String>();
		colors.add("afa");
		colors.add("faa");
		colors.add("ddd");
		
		//Instancia
		pieChart = new PieChartModel();
		FactoryChart factoryChart = new FactoryChart();
		ChartCreator chartCreator = factoryChart.getChart("Pie"); //Fabrica do tipo Pie
		
		//Atributos do gráfico
		String title = "Sentiment Quantitative";
		String legendPosition = "e";
		boolean showTip = true;
		boolean showLabel = true;
		String dataFormat = "value";
		
		//Monta o gráfico
		pieChart = (PieChartModel) chartCreator.mountGraph(labels, title, legendPosition, showTip, showLabel, dataFormat, colors);
		
		this.renderChart = true; //Permite exibição do gráfico
	}
	
	public List<Post> requestPosts(){
		int limiteUtilizado = 10;
		if(this.limit>0){
			limiteUtilizado = limit; //Determina o limite (o padrão é 10)
		}
		
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		boolean hasDate = false;
		String date1 = "";
		String date2 = "";
		
		if(dateQuerySince != null && dateQueryUntil != null){
			date1 = format.format(dateQuerySince);
			date2 = format.format(dateQueryUntil);
			
			hasDate = true;
		}
		
		FactorySocialConnector factory = new FactorySocialConnector();
		SocialConnector connector = factory.getConnector("Twitter");
		
		return connector.findPosts(this.searchString, this.user, limiteUtilizado, date1, date2, hasDate);
	}
	
	public void findTweets(ActionEvent actionEvent) {
		
		posts = requestPosts(); //popula a lista
		
	    HttpConnection con = new HttpConnection();
	    ArrayList<String> sentiments = new ArrayList<String>();
	    if(posts.size() > 0){
	    	sentiments = con.getSentiments(posts);
	    }
		positivo = 0;
		negativo = 0;
		neutro = 0;
		for(int i=0;i<posts.size();i++){
			String sentimentValue = sentiments.get(i);
			String sentiment = "";
			if(sentimentValue.equals("1")){
				sentiment = "Positivo";
				positivo++;
			} else if(sentimentValue.equals("-1")){
				sentiment = "Negativo";
				negativo++;
			} else {
				sentiment = "Neutro";
				neutro++;
			}
			posts.get(i).setSentiment(sentiment);
		}
		
		mountGraph();
	    
	}
	
	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public PieChartModel getPieChart() {
		return pieChart;
	}

	public void setPieChart(PieChartModel pieChart) {
		this.pieChart = pieChart;
	}

	public int getPositivo() {
		return positivo;
	}

	public void setPositivo(int positivo) {
		this.positivo = positivo;
	}

	public int getNegativo() {
		return negativo;
	}

	public void setNegativo(int negativo) {
		this.negativo = negativo;
	}

	public int getNeutro() {
		return neutro;
	}

	public void setNeutro(int neutro) {
		this.neutro = neutro;
	}

	public boolean isRenderChart() {
		return renderChart;
	}

	public void setRenderChart(boolean renderChart) {
		this.renderChart = renderChart;
	}

	public Date getDateQuerySince() {
		return dateQuerySince;
	}

	public void setDateQuerySince(Date dateQuerySince) {
		this.dateQuerySince = dateQuerySince;
	}

	public Date getDateQueryUntil() {
		return dateQueryUntil;
	}

	public void setDateQueryUntil(Date dateQueryUntil) {
		this.dateQueryUntil = dateQueryUntil;
	}

}
