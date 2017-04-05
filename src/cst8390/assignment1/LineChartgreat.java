package cst8390.assignment1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
 
public class LineChartgreat extends Application {
	final NumberAxis xAxis = new NumberAxis();
    final NumberAxis yAxis = new NumberAxis(); 
	final LineChart<Number,Number> lineChart = 
             new LineChart<Number,Number>(xAxis,yAxis);//it makes a line chart here.
	 
 
    @Override public void start(Stage stage) {
    	
        setLineChart(stage, "Property Prices in Vancouver 2015");
        Scene scene  = new Scene(getLineChart(),800,600);
        stage.setScene(scene);
        stage.show();
   	 
    }
 public void setLineChart(Stage stage, String s){
	 stage.setTitle(s);
	 
     //defining the axes
    
     xAxis.setLabel("Price in $25000CDN");
     //creating the chart
    
             
     lineChart.setTitle("Property Prices 2015");
     //defining a series
     XYChart.Series series = new XYChart.Series();
     series.setName("Property Bins");
     Assignment1_clean dataAdder=new Assignment1_clean();
     List<Integer> chartList1 = new ArrayList<>(Arrays.asList());
     dataAdder.readcleanfinalCSVFile(s);
     chartList1=dataAdder.getchartlist();
     int m=0;
     while (m<338){m++;
    	 series.getData().add(new XYChart.Data(m, chartList1.get(m)));
     } 
     //populating the series with data    
     lineChart.getData().add(series);    
 }
 public LineChart getLineChart(){
	 return lineChart;
 }
 
 
    public static void main(String[] args) {
        launch(args);
    }
}