package cst8390.assignment1;
	import java.io.BufferedReader;
	import java.io.IOException;
	import java.nio.file.Files;
	import java.nio.file.Paths;
	import java.util.ArrayList;
	import java.util.Arrays;
	import java.util.List;

import cst8390.assignment1.cleantester.MyData;
import javafx.application.Application;
	import javafx.collections.FXCollections;
	import javafx.collections.ObservableList;
	import javafx.scene.Scene;
	import javafx.scene.control.Button;
	import javafx.scene.control.ListView;
	import javafx.scene.control.TableColumn;
	import javafx.scene.control.TableView;
	import javafx.scene.control.cell.PropertyValueFactory;
	import javafx.scene.layout.VBox;
	import javafx.stage.Stage;

public class AssignAddress extends Application {

			
			//#1
			private List<Double> srtlandValueList = new ArrayList<>(Arrays.asList());
			private double srtlandValueMean=0.0;
			private double srtlandValueSD=0.0;
			/////
			//#2
			private List<Double> srthouseAgeList = new ArrayList<>(Arrays.asList());
			private double srthouseAgeMean=0.0;
			private double srthouseAgeSD=0.0;
			////#3
			private List<Double> prsrtvlandValueList = new ArrayList<>(Arrays.asList());
			private double prsrtvlandValueMean=0.0;
			///
			
			
			//#4
			///
			private double srtmultiFamilyDwelling=0;
			private double srtoneFamilyDwelling=0;
			private double srtcommercial=0;
			////
			//#6
			public List<String> addressnamegrouplist = new ArrayList<>(Arrays.asList());
			public List<Integer> numOfhouseperaddress = new ArrayList<>(Arrays.asList());
			public List<Double> currentmeanperaddress = new ArrayList<>(Arrays.asList());
			public List<Double> currentSDperaddress = new ArrayList<>(Arrays.asList());
			public List<Double>  houseAgeperaddress = new ArrayList<>(Arrays.asList());
			public List<Double>  houseAgeSDperaddress = new ArrayList<>(Arrays.asList());
			public List<Double>  plvmeanperaddress = new ArrayList<>(Arrays.asList());
		
			 
		
			
			 
			
			
			
			/////
			public class MyData{		public MyData(String n, int a, int sal)
			{myName = n;myAge = a;	annualSalary = sal;
			}		
			public String getName() 	{ return myName; }		public Integer getAge() 	{ return myAge; }
			public Integer getSalary() 	{ return annualSalary; }
			private String myName;	private int myAge;	private int annualSalary;
		}
			public static void main(String[] args) {
			launch(args);
		}		
		@Override
		public void start(Stage primaryStage) throws Exception {
			Button newButton = new Button("Go!");		
			//Create the table, and 3 columns: 
			TableView<MyData> table = new TableView<>();
			TableColumn<MyData, String> nameCol = new TableColumn<>("Name");	
		    TableColumn<MyData, String> ageCol = new TableColumn<>("Age");
		    TableColumn<MyData, String> salCol = new TableColumn<>("Salary");
		    table.getColumns().addAll(nameCol, ageCol, salCol);	    
		    //Tell the columns what getter function to call for their data:
		    nameCol.setCellValueFactory(
		    	    new PropertyValueFactory<MyData,String>("Name") //Will call "getName()" from objects in the list
		    	);
		    
		    ageCol.setCellValueFactory(
		    	    new PropertyValueFactory<MyData,String>("Age")  //Will call "getAge()" from objects in the list
		    	);
		    
		    salCol.setCellValueFactory(
		    	    new PropertyValueFactory<MyData,String>("Salary") //Will call "getSalary()" from objects in the list
		    	);	    
		    //Create a list of data:
		    final ObservableList<MyData> data = FXCollections.observableArrayList();
		    //add elements one at a time:
		    data.add(new MyData("Eric", 20, 12345));
		    data.add(new MyData("Cire", 40, 54321));
		    data.add(new MyData("Rice", 60, 123321));	    
		    //Finally give the data to the table for rendering:
		     table.setItems(data);	     
			primaryStage.setScene(new Scene(new VBox(newButton, table), 640, 480));		
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
			ListView<Number> list = new ListView<>();		
			//readCSVFile
			readcleanzonecatCSVFile("C:/School/Sem3/BusinessInt/property_tax_report_csv2015/cleansortedZApostcode.csv");
		
						//Create a list of data numbers
			ObservableList<Number> items = FXCollections.observableArrayList ( 1, 2, 3, 4, 5.5 );		
			//Give the listView a list of numbers:
			list.setItems(items);		
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
		public void readcleanzonecatCSVFile(String filename)
		{
			
			
			
			try(BufferedReader reader = Files.newBufferedReader(Paths.get(filename)))
			{
				String line = "";	int lineTracker=0;
				//#1
				List<Double> landvaluebystreetlist = new ArrayList<>(Arrays.asList());//list to manipulate for house price	
				List<String> streetgrouplist = new ArrayList<>(Arrays.asList());
				List<Integer> numhouseperstreet = new ArrayList<>(Arrays.asList());
				List<Double> meanperstreet = new ArrayList<>(Arrays.asList());
				List<Double> sDperstreet = new ArrayList<>(Arrays.asList());
				Double srtlandValue;String streetnameread="";String streetnamegroup="Z16";	int numofProbySrt=0;
				///////////
				//#2
				List<Double> lhouseAgeList = new ArrayList<>(Arrays.asList());//list to manipulate for house price	
				Double houseA;
				List<Double> housemeanperstreet = new ArrayList<>(Arrays.asList());
				List<Double> housesDperstreet = new ArrayList<>(Arrays.asList());
				
				///#3
				List<Double> plValueList = new ArrayList<>(Arrays.asList());//list to manipulate for house price	
				Double plandValue;
				List<Double> pmeanperstreet = new ArrayList<>(Arrays.asList());
				
				
				while(line != null){
					String [] partsOfLine;	//get a line of text from the file
					line = reader.readLine();	//Split the line by commas
					try { partsOfLine = line.split(",");								 
					if (lineTracker>0){					
						try{//String streetnameread="";// String streetnamegroup="";
				//ZONE_CATEGORY,STREET_NAME,PROPERTY_POSTAL_CODE,CURRENT_LAND_VALUE,PREVIOUS_LAND_VALUE,YEAR_BUILT
							streetnameread=partsOfLine[2];//read it
							srtlandValue=Double.parseDouble(partsOfLine[3]);
							houseA=Double.parseDouble(partsOfLine[5]);
							plandValue=Double.parseDouble(partsOfLine[4]);
							
							//if (lineTracker<20){ 
							//	System.out.println("streetnameread "+streetnameread);
							//	System.out.println("streetnamegroup "+streetnamegroup);		}
							if (streetnamegroup.equalsIgnoreCase(streetnameread)){//if equal adds counter
								numofProbySrt++;
								landvaluebystreetlist.add(srtlandValue);//adds values to the group list		
								lhouseAgeList.add(houseA);	
								plValueList.add(plandValue);
							}
							else{//group is done
								//new group
								//add list add number of houses
								//streetnamegroup.equalsIgnoreCase(streetnameread) false
								streetgrouplist.add(streetnamegroup);//adds the previous name thats no longer equal
								//^(Yukon St, James St, ...)
								numhouseperstreet.add(numofProbySrt);
								//^(10, 550, 44, ...)
								
								//#1---------
								setlandValueList(landvaluebystreetlist);//lValueList local variable of landValueList clutered list			
								setlandValueMean(getlandValueList());			
								setlandValueSD(srtlandValueList, srtlandValueMean);//DList mean
								///all SET
								meanperstreet.add(this.getlandValueMean());
								sDperstreet.add(this.getlandValueSD());
								//get SD, for group, get mean for group, add SD and mean for group
								//#2----------
								sethouseAgeList(lhouseAgeList);//cluttered list, sets houseAgeList
								sethouseAgeMean(gethouseAgeList());///houseAgeList is a clean list
								sethouseAgeSD(gethouseAgeList(), gethouseAgeMean());
								//set
								housemeanperstreet.add(this.gethouseAgeMean());
								housesDperstreet.add(this.gethouseAgeSD());
								//#3
								setplandValueList(plValueList);//lValueList local variable of landValueList clutered list			
								setplandValueMean(getplandValueList());	
								pmeanperstreet.add(this.getplandValueMean());
								//clears landvaluestreetlist
								//	private List<Double> srtlandValueList = new ArrayList<>(Arrays.asList());
								//private double srtlandValueMean=0.0;
								//private double srtlandValueSD=0.0;
								landvaluebystreetlist.clear();//($400 000, $500 000, ...)
								((ArrayList<Double>) landvaluebystreetlist).trimToSize();
								//clears 
								lhouseAgeList.clear();
								((ArrayList<Double>) lhouseAgeList).trimToSize();
								//clears3
								plValueList.clear();
								((ArrayList<Double>) plValueList).trimToSize();
								
								//numofProbySrt clears
								numofProbySrt=0;
								streetnamegroup=partsOfLine[2];//update streetgroup use to be YorkSt now JamesStr
								
							}
							//houseA=Double.parseDouble(partsOfLine[5]);
						//reads the property value
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
						srtlandValue=null;	houseA=null;plandValue=null;
						//plandValue=null;
						//catList.add(null);
						}
						//lhouseAgeList.add(houseA);	
						
						//plValueList.add(plandValue);
						}
					lineTracker++;									
				
					}	catch(NullPointerException np){	
						lineTracker++;	
						continue;
					}
					
			}//<=== while(line != null)
				System.out.println("addressgrouplist.size "+ streetgrouplist.size());
					
			//	for (int s=0;s<10;s++){		
				//	System.out.println("-----------------------");
			//		System.out.println("-----------------------");
			//	 System.out.println("addressgrouplist first10 "+ streetgrouplist.get(s));
			//	 System.out.println("numhouseperaddress first10 "+ numhouseperstreet.get(s));
			//	 System.out.println("meanperaddress first10 "+ meanperstreet.get(s));
			//	 System.out.println("sDperaddress first10 "+ sDperstreet.get(s));
			//	 System.out.println("housemeanperaddress.gethouseAgeMean() first10 "+ housemeanperstreet.get(s));
			//	 System.out.println("housesDperaddress.gethouseAgeSD() "+ housesDperstreet.get(s));
			//	 System.out.println("pmeanperaddress "+  pmeanperstreet.get(s));
			//	 System.out.println("diff is (meanperaddress-pmeanperaddress)*numhouseperaddress "+  numhouseperstreet.get(s)*(meanperstreet.get(s)-pmeanperstreet.get(s)));
				
					
					
					
			//	 System.out.println("-----------------------");
			//	}
				addressnamegrouplist=streetgrouplist;
				numOfhouseperaddress=numhouseperstreet; 
				currentmeanperaddress=meanperstreet;
				currentSDperaddress=sDperstreet; 
				houseAgeperaddress=housemeanperstreet;
				houseAgeSDperaddress=housesDperstreet;
				plvmeanperaddress=pmeanperstreet;
				
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
			//System.out.println(" CLEANlandValueList size is  "+cleanplandValueList.size());
			
			prsrtvlandValueList=cleanplandValueList;
			
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
			//System.out.println(" CLEANlandValueList size is  "+cleanlandValueList.size());
			
			srtlandValueList=cleanlandValueList;
			}
		public List getlandValueList(){
			return srtlandValueList;
		}
		
		public List getplandValueList(){
			return prsrtvlandValueList;
		}
		
		
		public void setlandValueMean(List <Double> lVList){
			//takes a double List and set the value of landValueMean using getDlistAvg.
				
			srtlandValueMean=getDListAvg(lVList);//I just pass it in with the nulls
		}
		
		public void setplandValueMean(List <Double> lVList){
			//takes a double List and set the value of landValueMean using getDlistAvg.
				
			prsrtvlandValueMean=getDListAvg(lVList);//I just pass it in with the nulls
		}
		
		
		public double getlandValueMean(){
			return srtlandValueMean;
		}
		
		public double getplandValueMean(){
			return prsrtvlandValueMean;
		}
		public void setlandValueSD(List lVList, double landValueMean){
			//the list lVList is a <Double>
			srtlandValueSD=getDListSD(lVList, landValueMean);
		}
		public double getlandValueSD(){
			//the list lVList is a <Double>
			return srtlandValueSD;
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
					//System.out.println(" cleanHOUSEList size is  "+cleanHouseAgeList.size());			
					srthouseAgeList=cleanHouseAgeList;		
		}
		public List gethouseAgeList(){		
			return srthouseAgeList;
		}
		
		public void sethouseAgeMean(List cleanhouseAgeList){
			srthouseAgeMean=getDListAvg(cleanhouseAgeList);
			
		}
		
		public double gethouseAgeMean(){
			return srthouseAgeMean;
		}
		
		
		public void sethouseAgeSD(List hAList, double houseAMean){
			//the list lVList is a <Double>
			srthouseAgeSD=getDListSD(hAList, houseAMean);
		}
		public double gethouseAgeSD(){
			//the list lVList is a <Double>
			return srthouseAgeSD;
		}
		
		
		
		public double getDListAvg(List aList){
			int latch=0;
			double summation=0;//sum of all numbers
			//System.out.println("alist size is: "+aList.size());
			while (latch<aList.size()){
				summation=summation+(double)aList.get(latch);
				latch++;
			}
			double listMean=(summation/aList.size()) ;
			//System.out.println("average house is: "+listMean);
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
			//System.out.println(" total dev squared is "+total);		System.out.println(" List size is  "+aList.size());
			//total=deviation^2
			double var=(total/aList.size());//(E(X)^2  - E(X^2) =SD
			double fSD=Math.sqrt(var);		return fSD;		
		}
		
		
		
		
		}


