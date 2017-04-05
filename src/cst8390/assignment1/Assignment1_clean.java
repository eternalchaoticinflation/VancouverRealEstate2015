package cst8390.assignment1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Assignment1_clean extends Application {
	
	//#1
	private List<Double> landValueList = new ArrayList<>(Arrays.asList());
	private double landValueMean=0.0;
	private double landValueSD=0.0;
	/////
	//#2
	private List<Double> houseAgeList = new ArrayList<>(Arrays.asList());
	private double houseAgeMean=0.0;
	private double houseAgeSD=0.0;
	////
	//#3
	private List<Double> prvlandValueList = new ArrayList<>(Arrays.asList());
	private double prvlandValueMean=0.0;
	///
	
	//#4
	///
	private double multiFamilyDwelling=0;
	private double oneFamilyDwelling=0;
	private double commercial=0;
	////	
	public class MyData{
		private String cat;
		private double mean;
		private double dev;
		private int mfdw;
		private int ofdw;
		private int commer;
		
		public MyData(String n, double m, double sd, int mfd, int ofd, int comm)
		{
			cat = n;
			mean = m;	
			dev = sd;
			mfdw=mfd;
			ofdw=ofd;
			commer=comm;
		}		
		public String getCat() 	{ return cat; }		public Double getMean() 	{ return mean; }
		public Double getDev() 	{ return dev; }
		public Integer getMfdw() 	{ return mfdw; }
		public Integer getOfdw() 	{ return ofdw; }
		public Integer getCommer() 	{ return commer; }
		
	}
		public static void main(String[] args) {
		launch(args);
	}		
	@Override
	public void start(Stage primaryStage) throws Exception {
		Button newButton = new Button("Go!");		
		//Create the table, and 3 columns: 
		TableView<MyData> table = new TableView<>();
		TableColumn<MyData, String> nameCol = new TableColumn<>("Category");
		 nameCol.setCellValueFactory(
		    	    new PropertyValueFactory<>("cat") //Will call "getName()" from objects in the list
		    	);
		    
	    TableColumn<MyData, Double> meanCol = new TableColumn<>("mean");
	    meanCol.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	            if (event.getClickCount() > 1) {
	                System.out.println("coldouble clicked!");
	                openOtherStage();
	               
	            }
	        }
	    });
	    meanCol.setCellValueFactory(
	    	    new PropertyValueFactory<>("mean")  //Will call "getAge()" from objects in the list
	    	);
	    
	  
	    
	    TableColumn<MyData, Double> priceSDCol = new TableColumn<>("SD/priceDifference");
	    priceSDCol.setCellValueFactory(
	    	    new PropertyValueFactory<MyData, Double>("dev") //Will call "getSalary()" from objects in the list
	    	);
	
	    TableColumn<MyData, Integer> mfdCol = new TableColumn<>("# of MultiFamilyDwellings");
	    mfdCol.setCellValueFactory(
	    	    new PropertyValueFactory<MyData, Integer>("mfdw") //Will call "getSalary()" from objects in the list
	    	);
	    TableColumn<MyData,  Integer> ofdCol = new TableColumn<>("# of OneFamilyDwellings");
	    ofdCol.setCellValueFactory(
	    	    new PropertyValueFactory<MyData, Integer>("ofdw") //Will call "getSalary()" from objects in the list
	    	);
	    TableColumn<MyData,  Integer> commCol = new TableColumn<>("# of Commercial buildings");
	    commCol.setCellValueFactory(
	    	    new PropertyValueFactory<MyData, Integer>("commer") //Will call "getSalary()" from objects in the list
	    	);
	       
	    //Tell the columns what getter function to call for their data:
	   
	 
	    table.getColumns().addAll(nameCol, meanCol, priceSDCol,  mfdCol, ofdCol, commCol);
	    table.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	            if (event.getClickCount() > 1) {
	                System.out.println("double clicked!");
	                
	             
	               openOtherStage();
	               
	            }
	        }
	    });
	    
	  
	    
	    
	    //Create a list of data:
	    final ObservableList<MyData> data = FXCollections.observableArrayList();
	    LineChartgreat hist= new LineChartgreat();
	    
	    
	    readcleanfinalCSVFile("C:/School/Sem3/BusinessInt/property_tax_report_csv2015/cleanmixedall.csv");	//5
		//readcleanlandvalueCSVFile("C:/School/Sem3/BusinessInt/property_tax_report_csv2015/cleanmixedall.csv");//3
		//readcleanpreviousvalueCSVFile("C:/School/Sem3/BusinessInt/property_tax_report_csv2015/cleanmixedall.csv");//4
		//readcleanzonecatCSVFile("C:/School/Sem3/BusinessInt/property_tax_report_csv2015/cleanmixedall.csv");//0
	   // readcleanstreetCSVFile("C:/School/Sem3/BusinessInt/property_tax_report_csv2015/cleansortedZAstreetname.csv");
	    
	    Stage newStage = new Stage();
	    hist.setLineChart(newStage, "C:/School/Sem3/BusinessInt/property_tax_report_csv2015/cleanmixedall.csv");
	    testPrinter();
		
	    
	    
	    //add elements one at a time:
	    data.add(new MyData("LandValue", landValueMean,  (double)landValueSD, (int)0, (int)0, (int) 0  ));
	    data.add(new MyData("HouseAge",  houseAgeMean, houseAgeSD, (int)0, (int)0, (int) 0 ));
	    data.add(new MyData("PreivouslandV", prvlandValueMean, (landValueMean-prvlandValueMean)*203494, 0,0,0 )); 
	    data.add(new MyData("Type of property", 0, 0, (int)multiFamilyDwelling, (int)oneFamilyDwelling,(int)commercial ));	    
	    //Finally give the data to the table for rendering:
	     table.setItems(data);	 
	     
	     VBox firstBig = new VBox();
	     firstBig.getChildren().addAll(new VBox(table), hist.getLineChart());
	     
	    
	    
	    	Scene scene = new Scene(firstBig, 1024, 768);
	    	

	       // stage.setScene(scene);
		primaryStage.setScene(scene);		
		//when clicking the button, call openOtherStage();
		newButton.setOnMouseClicked(clickEvent -> openOtherStage() );		
		primaryStage.show();
	}	
	protected void openOtherStage()
	{	VBox root = new VBox();		
		//Create a second stage object
		Stage newStage = new Stage();		
		//Create a scene object to show objects
		Scene newScene = new Scene(root, 500, 500);		
		//Create a ListView object
		ListView<String> list = new ListView<>();	
		list.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {
	            if (event.getClickCount() > 1) {
	                System.out.println("double clicked!");
	                
	             
	               openOtherStagestreet();
	               
	            }
	        }
	    });
		
		AssignStreet datagetter=new AssignStreet();
		datagetter.readcleanzonecatCSVFile("C:/School/Sem3/BusinessInt/property_tax_report_csv2015/cleansortedZAstreetname.csv");
		AssignAddress addressgetter=new AssignAddress();
		addressgetter.readcleanzonecatCSVFile("C:/School/Sem3/BusinessInt/property_tax_report_csv2015/cleansortedZApostcode.csv");
		for (int s=0;s<5;s++){		
			System.out.println("-----------------------");
			System.out.println("-----------------------");
		 System.out.println("streetgrouplist first10 "+ datagetter.streetnamegrouplist.get(s));
		 System.out.println("numhouseperstreet first10 "+ datagetter.numOfhouseperstreet.get(s));
		 System.out.println("meanperstreet first10 "+ datagetter.currentmeanperstreet.get(s));
		 System.out.println("sDperstreet first10 "+ datagetter.currentSDperstreet.get(s));
		 System.out.println("housemeanperstreet.gethouseAgeMean() first10 "+ datagetter.houseAgeperstreet.get(s));
		 System.out.println("housesDperstreet.gethouseAgeSD() "+ datagetter.houseAgeSDperstreet.get(s));
		 System.out.println("pmeanperstreet "+  datagetter.plvmeanperstreet.get(s));
		 System.out.println("diff is (meanperstreet-pmeanperstreet)*numhouseperstreet "+  datagetter.numOfhouseperstreet.get(s)*(datagetter.currentmeanperstreet.get(s)-datagetter.plvmeanperstreet.get(s)));
			System.out.println("-----.....------------------");
			System.out.println("-----.....------------------");
		 System.out.println("addressgrouplist first10 "+ addressgetter.addressnamegrouplist.get(s));
		 System.out.println("numhouseperaddress first10 "+ addressgetter.numOfhouseperaddress.get(s));
		 System.out.println("meanperaddress first10 "+ addressgetter.currentmeanperaddress.get(s));
		 System.out.println("sDperaddress first10 "+ addressgetter.currentSDperaddress.get(s));
		 System.out.println("housemeanperaddress.gethouseAgeMean() first10 "+ addressgetter.houseAgeperaddress.get(s));
		 System.out.println("housesDperaddress.gethouseAgeSD() "+ addressgetter.houseAgeSDperaddress.get(s));
		 System.out.println("pmeanperaddress "+  addressgetter.plvmeanperaddress.get(s));
		 System.out.println("diff is (meanperaddress-pmeanperaddress)*numhouseperaddress"+  addressgetter.numOfhouseperaddress.get(s)*(addressgetter.currentmeanperaddress.get(s)-addressgetter.plvmeanperaddress.get(s)));
		
			
			
			
			
		 System.out.println("-----------------------");
		}
		ObservableList<String> items = FXCollections.observableArrayList();	
		items.add("Postal Code  | "+"meanperaddress | "+"SDperaddress |"+"LastYearmeanperaddress | "+ "Number of properties |"
				+ "Age of Property |" + "AgeSDproperties |"
				);
		for (int s=0;s<addressgetter.addressnamegrouplist.size();s++){	
		items.add(addressgetter.addressnamegrouplist.get(s)
				+"  cmpa: "+addressgetter.currentmeanperaddress.get(s)
				+"  SD:"+addressgetter.currentSDperaddress.get(s)
				+"  LYpa: "+addressgetter.plvmeanperaddress.get(s)
		+"  Changetotal: "+ addressgetter.numOfhouseperaddress.get(s)*(addressgetter.currentmeanperaddress.get(s)-addressgetter.plvmeanperaddress.get(s))
				+"  numOfprop: " + addressgetter.numOfhouseperaddress.get(s)
				+" Age/postcode: "+addressgetter.houseAgeperaddress.get(s)
				+" SDAge/postcode: "+addressgetter.houseAgeSDperaddress.get(s)
				);
		}
		//Give the listView a list of numbers:
		list.getItems().addAll(items);
		root.getChildren().add(list);		
		//Tell the stage which scene to display
		newStage.setScene(newScene);		
		//make the stage visible
		newStage.show();
	}	
	
	protected void openOtherStagestreet(){
		VBox root = new VBox();		
		//Create a second stage object
		Stage newStage = new Stage();		
		//Create a scene object to show objects
		Scene newScene = new Scene(root, 500, 500);		
		//Create a ListView object
		ListView<String> list = new ListView<>();	
		
		
		AssignStreet datagetter=new AssignStreet();
		datagetter.readcleanzonecatCSVFile("C:/School/Sem3/BusinessInt/property_tax_report_csv2015/cleansortedZAstreetname.csv");
		
		for (int s=0;s<5;s++){		
			System.out.println("-----------------------");
			System.out.println("-----------------------");
		 System.out.println("streetgrouplist first10 "+ datagetter.streetnamegrouplist.get(s));
		 System.out.println("numhouseperstreet first10 "+ datagetter.numOfhouseperstreet.get(s));
		 System.out.println("meanperstreet first10 "+ datagetter.currentmeanperstreet.get(s));
		 System.out.println("sDperstreet first10 "+ datagetter.currentSDperstreet.get(s));
		 System.out.println("housemeanperstreet.gethouseAgeMean() first10 "+ datagetter.houseAgeperstreet.get(s));
		 System.out.println("housesDperstreet.gethouseAgeSD() "+ datagetter.houseAgeSDperstreet.get(s));
		 System.out.println("pmeanperstreet "+  datagetter.plvmeanperstreet.get(s));
		 System.out.println("diff is (meanperstreet-pmeanperstreet)*numhouseperstreet "+  datagetter.numOfhouseperstreet.get(s)*(datagetter.currentmeanperstreet.get(s)-datagetter.plvmeanperstreet.get(s)));
					
		 System.out.println("-----------------------");
		}
		ObservableList<String> items = FXCollections.observableArrayList();	
		items.add("StreetName  | "+"meanperStreet | "+"SDperStreet |"+"LastYearmeanperStreet | "+ "Number of properties |"
				+ "Age of Property |" + "AgeSDproperties |"
				);
		for (int s=0;s<datagetter.streetnamegrouplist.size();s++){	
		items.add(datagetter.streetnamegrouplist.get(s)
				+"  cmpa: "+datagetter.currentmeanperstreet.get(s)
				+"  SD:"+datagetter.currentSDperstreet.get(s)
				+"  LYpa: "+datagetter.plvmeanperstreet.get(s)
		+"  Changetotal: "+ datagetter.numOfhouseperstreet.get(s)*(datagetter.currentmeanperstreet.get(s)-datagetter.plvmeanperstreet.get(s))
				+"  numOfprop: " + datagetter.numOfhouseperstreet.get(s)
				+" Age/street: "+datagetter.houseAgeperstreet.get(s)
				+" SDAge/street: "+datagetter.houseAgeSDperstreet.get(s)
				);
		}
		//Give the listView a list of numbers:
		list.getItems().addAll(items);
		root.getChildren().add(list);		
		//Tell the stage which scene to display
		newStage.setScene(newScene);		
		//make the stage visible
		newStage.show();
		
	}
	
	/**  This is the basic parts of reading a CSV file.
	 * 
	 * @param filename The string representing a filename to open.
	 */
	public void readcleanfinalCSVFile(String filename)
	{
		
		try(BufferedReader reader = Files.newBufferedReader(Paths.get(filename)))
		{
			String line = "";
			int lineTracker=0;
			//#1
			List<Double> lValueList = new ArrayList<>(Arrays.asList());//list to manipulate for house price	
			Double landValue;
			//#2
			List<Double> lhouseAgeList = new ArrayList<>(Arrays.asList());//list to manipulate for house price	
			Double houseA;
			//#3
			List<Double> plValueList = new ArrayList<>(Arrays.asList());//list to manipulate for house price	
			Double plandValue;
			//#4
			int mfd=0;	int ofd=0;	int comm=0;
			List<String> catList = new ArrayList<>(Arrays.asList());	
						
			while(line != null){
				String [] partsOfLine;
				//get a line of text from the file
				line = reader.readLine();				
				//Split the line by commas
				try {		
				 partsOfLine = line.split(",");					
							 
				if (lineTracker>0){					
					try{houseA=Double.parseDouble(partsOfLine[5]);
					landValue=Double.parseDouble(partsOfLine[3]);
					plandValue=Double.parseDouble(partsOfLine[4]);
					//catList.add(partsOfLine[0]);
					if (partsOfLine[0].equals("Multiple Family Dwelling")){
						mfd++;	}
					if (partsOfLine[0].equals("One Family Dwelling")){
						ofd++;	}
					if (partsOfLine[0].equals("Commercial")){
						comm++;	}					
					}
					catch(NumberFormatException nfe){		
					houseA=null;landValue=null;	plandValue=null;catList.add(null);
					}
					lhouseAgeList.add(houseA);	
					lValueList.add(landValue);
					plValueList.add(plandValue);
					}
				lineTracker++;									
			
				}	catch(NullPointerException np){	
					lineTracker++;	
					continue;
				}
				
		}//<=== while(line != null)
			//2
			sethouseAgeList(lhouseAgeList);//cluttered list, sets houseAgeList
			sethouseAgeMean(gethouseAgeList());///houseAgeList is a clean list
			sethouseAgeSD(gethouseAgeList(), gethouseAgeMean());
			//1
			setlandValueList(lValueList);//lValueList local variable of landValueList clutered list			
			setlandValueMean(getlandValueList());			
			setlandValueSD(landValueList, landValueMean);//DList mean
			//3
			setplandValueList(plValueList);//lValueList local variable of landValueList clutered list			
			setplandValueMean(getplandValueList());			
					
			 System.out.println("lhouseAgeList.size() "+ lhouseAgeList.size());
			 //System.out.println("gethouseAgeMean() "+ gethouseAgeMean());
			// System.out.println("gethouseAgeSD() "+ gethouseAgeSD());
			//#4 
			multiFamilyDwelling=mfd; 
			oneFamilyDwelling=ofd; 
			commercial=comm;
					
		}	//try
		catch(IOException ioe)
		{
			System.out.println("Problem reading csv: " + ioe.getMessage());
		}	
		
	}
	///////#4
//readcleanstreetCSVFile
	public void readcleanstreetCSVFile(String filename)
	{
		
	
	try(BufferedReader reader = Files.newBufferedReader(Paths.get(filename)))
	{
		String line = "";
		int lineTracker=0;
		//#1
		List<Double> landvaluebystreetlist = new ArrayList<>(Arrays.asList());//list to manipulate for house price	
		Double landValue;
		
		while(line != null){
			String [] partsOfLine;
			//get a line of text from the file
			line = reader.readLine();				
			//Split the line by commas
			try {		
			 partsOfLine = line.split(",");					
						 
			if (lineTracker>0){					
				try{//houseA=Double.parseDouble(partsOfLine[5]);
				landValue=Double.parseDouble(partsOfLine[3]);
				//plandValue=Double.parseDouble(partsOfLine[4]);
				//catList.add(partsOfLine[0]);
				//if (partsOfLine[0].equals("Multiple Family Dwelling")){
				//	mfd++;	}
				//if (partsOfLine[0].equals("One Family Dwelling")){
				//	ofd++;	}
			//	if (partsOfLine[0].equals("Commercial")){
				//	comm++;	}					
				}
				catch(NumberFormatException nfe){		
				//houseA=null;
				landValue=null;	
				//plandValue=null;
				//catList.add(null);
				}
				//lhouseAgeList.add(houseA);	
				landvaluebystreetlist.add(landValue);
				//plValueList.add(plandValue);
				}
			lineTracker++;									
		
			}	catch(NullPointerException np){	
				lineTracker++;	
				continue;
			}
			
	}//<=== while(line != null)
		
			
				
		 System.out.println("lhouseAgeList.size() ");
		 //System.out.println("gethouseAgeMean() "+ gethouseAgeMean());
		// System.out.println("gethouseAgeSD() "+ gethouseAgeSD());
		//#4 
	
	}	//try
	catch(IOException ioe)
	{
		System.out.println("Problem reading csv: " + ioe.getMessage());
	}	
	
}
	
	
	
	
	
	private void setplandValueList(List<Double> plValueList) {
		// TODO Auto-generated method stub
		List<Double> cleanplandValueList = new ArrayList<>(Arrays.asList());
		int latch=0;
		while (latch<plValueList.size()){//clutered lVList
			
			if(plValueList.get(latch)!=null){
				cleanplandValueList.add(plValueList.get(latch));
			}
			
			latch++;
		}
		System.out.println(" CLEANlandValueList size is  "+cleanplandValueList.size());
		
		prvlandValueList=cleanplandValueList;
		
	}
	public void setlandValueList(List <Double> lVList){
		//make clean list
		List<Double> cleanlandValueList = new ArrayList<>(Arrays.asList());
		int latch=0;
		while (latch<lVList.size()){//clutered lVList
			
			if(lVList.get(latch)!=null){
				cleanlandValueList.add(lVList.get(latch));
			}
			
			latch++;
		}
		System.out.println(" CLEANlandValueList size is  "+cleanlandValueList.size());
		
		landValueList=cleanlandValueList;
		}
	public List getlandValueList(){
		return landValueList;
	}
	
	public List getplandValueList(){
		return prvlandValueList;
	}
	
	
	public void setlandValueMean(List <Double> lVList){
		//takes a double List and set the value of landValueMean using getDlistAvg.
			
		landValueMean=getDListAvg(lVList);//I just pass it in with the nulls
	}
	
	public void setplandValueMean(List <Double> lVList){
		//takes a double List and set the value of landValueMean using getDlistAvg.
			
		prvlandValueMean=getDListAvg(lVList);//I just pass it in with the nulls
	}
	
	
	public double getlandValueMean(){
		return landValueMean;
	}
	
	public double getplandValueMean(){
		return prvlandValueMean;
	}
	public void setlandValueSD(List lVList, double landValueMean){
		//the list lVList is a <Double>
		landValueSD=getDListSD(lVList, landValueMean);
	}
	public double getlandValueSD(){
		//the list lVList is a <Double>
		return landValueSD;
	}
	
	
	//#2
	public void sethouseAgeList(List<Double> lhouseAgeList){
		//make clean list
				List<Double> cleanHouseAgeList = new ArrayList<>(Arrays.asList());
				int latch=0;
				while (latch<lhouseAgeList.size()){//clutered lVList					
					if(lhouseAgeList.get(latch)!=null){						
						//System.out.println(" latch is  "+ lhouseAgeList.get(latch));
						if ((2016-lhouseAgeList.get(latch))<0||(2016-lhouseAgeList.get(latch))>500){
							//System.out.println(" woah  "+ lhouseAgeList.get(latch));
							//if (latch>20){break;}
							latch++;	continue;
						}						
						cleanHouseAgeList.add(2016-lhouseAgeList.get(latch));						
					}					
					latch++;
				}
				System.out.println(" cleanHOUSEList size is  "+cleanHouseAgeList.size());			
				houseAgeList=cleanHouseAgeList;		
	}
	public List gethouseAgeList(){		
		return houseAgeList;
	}
	
	public void sethouseAgeMean(List cleanhouseAgeList){
		houseAgeMean=getDListAvg(cleanhouseAgeList);
		
	}
	
	public double gethouseAgeMean(){
		return houseAgeMean;
	}
	
	
	public void sethouseAgeSD(List hAList, double houseAMean){
		//the list lVList is a <Double>
		houseAgeSD=getDListSD(hAList, houseAMean);
	}
	public double gethouseAgeSD(){
		//the list lVList is a <Double>
		return houseAgeSD;
	}
	
	
	
	public double getDListAvg(List aList){
		int latch=0;
		double summation=0;//sum of all numbers
		System.out.println("alist size is: "+aList.size());
		while (latch<aList.size()){
			summation=summation+(double)aList.get(latch);
			latch++;
		}
		double listMean=(summation/aList.size()) ;
		System.out.println("average house is: "+listMean);
		return listMean;		
	}
	

	
	public double getDListSD(List<Double> aList, double mean){
	//////
				//new latch, nothing to do with for loop before
				//this gets sum, for the list of values generated before 
				//then total is the total deviation from the given mean, squared
				//total/number of samples = variance
				//root(variance)=Standard Deviation = Root mean square of deivation
		int mlatch=0;
		double total=0;//total=deviation^2
	
		while (mlatch<aList.size()){			
			double diff=(aList.get(mlatch)-mean);
		total=(total+ (aList.get(mlatch)-mean)*(aList.get(mlatch)-mean) );
		mlatch++;
		}
		System.out.println(" total dev squared is "+total);		System.out.println(" List size is  "+aList.size());
		//total=deviation^2
		double var=(total/aList.size());//(E(X)^2  - E(X^2) =SD
		double fSD=Math.sqrt(var);		return fSD;		
	}
	
	public void testPrinter(){
		System.out.println("----------------");
		System.out.println("----------------");
		//
		System.out.println("The getplandValueMean() is "+ getplandValueMean());
		System.out.println("The getplandValueList().size() is "+getplandValueList().size());
		///
		System.out.println("----------------");
		///
		System.out.println("The current getlandValueMean() is "+ getlandValueMean());
		System.out.println("The current getlandValueSD() is "+ getlandValueSD());
		System.out.println("The current getlandValueList().size() is "+getlandValueList().size());
		///
		System.out.println("----------------");
		///
		System.out.println("The gethouseAgeMean() is "+ gethouseAgeMean());
		System.out.println("The gethouseAgeSD() is "+ gethouseAgeSD());
		System.out.println("The gethouseAgeList().size() is "+gethouseAgeList().size());
		////
		System.out.println("----------------");
		System.out.println("The (getlandValueMean()-getplandValueMean())*getlandValueList().size() is "
		+ (getlandValueMean()-getplandValueMean())*getlandValueList().size());
		System.out.println("Or just $"
				+ (( (getlandValueMean()-getplandValueMean())*getlandValueList().size() )/1000000000)+" Billion"   );
		
		System.out.println("----------------");
		////
	
		System.out.println("the # of multiFamilyDwellings are "+multiFamilyDwelling);
		System.out.println("the # of oneFamilyDwellings are "+oneFamilyDwelling);
		System.out.println("# of Commercial buildings are  "+commercial);
					
		
		
	}
	
	public List<Integer> getchartlist(){
		 List<Double> landValuelatch = new ArrayList<>(Arrays.asList());
		 List<Integer> chartList = new ArrayList<>(Arrays.asList());
		 //makes land value latch equals cleand land value list
		 landValuelatch=getlandValueList(); //done
		 Collections.sort(landValuelatch);
		 int i=0;
		 double intervals=25000.0;//should be 125 000
		 int binkeeper=0;
		 int bintracker=0;
		 while (i<landValuelatch.size()){ 
		 if (landValuelatch.get(i)<=intervals){//first 25000
			 //bin grows
			 binkeeper++;
			 
		 }	
		 else {
			 intervals=intervals+25000.0;//house value greater by 25000
			 //fix using if interval>=100000
			 if (intervals>=100000){
			 chartList.add(binkeeper);
				 }
			 bintracker++;
			 binkeeper=0;//reset binkeeper
			 
		 }
		 i++;
		 }
		 i=i-1;
		 //System.out.println("landValuelatch sorted at  "+landValuelatch.get(i)+" "+i);	
		 System.out.println("bintracker is  "+bintracker);
		 bintracker=0;//reset
		 for (int k:chartList){bintracker++;
		 if (k==0){
			 System.out.println("first zero bin at  "+bintracker);
			 
			 break;
		 }
				
			}
		 return chartList;
			
	}
	
	
	
	
	
	
	}



