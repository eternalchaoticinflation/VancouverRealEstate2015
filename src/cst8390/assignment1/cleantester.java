package cst8390.assignment1;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

public class cleantester extends Application{
		
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
		
		///
		
		
		//#4
		///
		private double multiFamilyDwelling=0;
		private double oneFamilyDwelling=0;
		private double commercial=0;
		////
		
		
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
				Double srtlandValue;
				String streetnameread="";
				String streetnamegroup="YUKON ST";
				int numofProbySrt=0;
				
				while(line != null){
					String [] partsOfLine;
					//get a line of text from the file
					line = reader.readLine();				
					//Split the line by commas
					try {		
					 partsOfLine = line.split(",");					
								 
					if (lineTracker>0){					
						try{//String streetnameread="";
							// String streetnamegroup="";
				//ZONE_CATEGORY,STREET_NAME,PROPERTY_POSTAL_CODE,CURRENT_LAND_VALUE,PREVIOUS_LAND_VALUE,YEAR_BUILT

							streetnameread=partsOfLine[1];//read it
							srtlandValue=Double.parseDouble(partsOfLine[3]);
							if (lineTracker<20){ 
								System.out.println("streetnameread "+streetnameread);
								System.out.println("streetnamegroup "+streetnamegroup);
							}
							
							if (streetnamegroup.equalsIgnoreCase(streetnameread)){//if equal adds counter
								numofProbySrt++;
								landvaluebystreetlist.add(srtlandValue);//adds values to the group list
								
							}
							else{//group is done
								//new group
								//add list add number of houses
								//streetnamegroup.equalsIgnoreCase(streetnameread) false
								streetgrouplist.add(streetnamegroup);//adds the previous name thats no longer equal
								//^(Yukon St, James St, ...)
								numhouseperstreet.add(numofProbySrt);
								//^(10, 550, 44, ...)
								//get SD, for group, get mean for group, add SD and mean for group
								//clears landvaluestreetlist
								landvaluebystreetlist.clear();//($400 000, $500 000, ...)
								((ArrayList<Double>) landvaluebystreetlist).trimToSize();
								//clears 
								//numofProbySrt clears
								numofProbySrt=0;
								streetnamegroup=partsOfLine[1];//update streetgroup use to be YorkSt now JamesStr
								
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
						srtlandValue=null;	
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
				System.out.println("streetgrouplist.size "+ streetgrouplist.size());
					
				for (int s=0;s<10;s++){		
				 System.out.println("streetgrouplist first10 "+ streetgrouplist.get(s));
				 System.out.println("numhouseperstreet first10 "+ numhouseperstreet.get(s));
				 
				}
				 //System.out.println("gethouseAgeMean() "+ gethouseAgeMean());
				// System.out.println("gethouseAgeSD() "+ gethouseAgeSD());
				//#4 
			
			}	//try
			catch(IOException ioe)
			{
				System.out.println("Problem reading csv: " + ioe.getMessage());
			}	
			
		}
			
		
		
		
		
		}


