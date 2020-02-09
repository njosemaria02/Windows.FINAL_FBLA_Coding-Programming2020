import java.awt.*;  
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.text.BadLocationException;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.print.DocPrintJob;


// font is Segoe UI
//Segoe MDL2 Assets
//Segoe Print
//Segoe Script
//Segoe UI
//Segoe UI Black
//Segoe UI Emoji
//Segoe UI Historic
//Segoe UI Light
//Segoe UI Semibold
//Segoe UI Semilight
//Segoe UI Symbol


public class CSA_AwardProgram {	
	public static void setUIFont (javax.swing.plaf.FontUIResource f){
	    java.util.Enumeration keys = UIManager.getDefaults().keys();
	    while (keys.hasMoreElements()) {
	      Object key = keys.nextElement();
	      Object value = UIManager.get (key);
	      if (value instanceof javax.swing.plaf.FontUIResource)
	        UIManager.put (key, f);
	      }
	    } 
	
	
	swing.defaultlaf=com.sun.java.swing.plaf.windows.WindowsLookAndFeel

	
	private static boolean mainActive = false;
	private static boolean needUpdateTable = false;
	
	private static String folderName = ("C:/Users/" + System.getProperty("user.name").toString() + "/Desktop/DesktopCSATest");
	//private static String folderName = ("C:/Users/" + System.getProperty("user.name").toString() + "/OneDrive - Fulton County Schools/Desktop/DesktopCSATest");
	//private static String folderName = ("C:/Users/1100299029/OneDrive - Fulton County Schools");
	
	private static School_CSA alpharetta;
	
	// gitHub test
	private  static String fileName = folderName + "/src/StudentDatabase.txt";
	private static String serviceFileName = folderName + "/src/ServiceDatabase.txt";

	
	// school computer
	//private  static String fileName = "C:/Users/1100299029/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020/src/StudentDatabase.txt";
	//private static String serviceFileName = "C:/Users/1100299029/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020/src/ServiceDatabase.txt";
	
	// personal computer
	//private static String fileName = "C:/Users/SYFGWC19/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020/src/StudentDatabase.txt"; 
	//private static String serviceFileName = "C:/Users/SYFGWC19/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020/src/ServiceDatabase.txt";

	
	public static void setMainActive(boolean decision)
	{
		mainActive = decision;
	}
	
	
	// creates array for application to used based on text file
	private static void createSchoolRoster()
	{	int count = 0;
		//need to recreate array each time program opens since will not remember from last use
		 ArrayList<Student> students = new ArrayList<Student>();
		 
		  try
		  {
			/** register COUNT of how many students in system **/
		    FileInputStream fstream = new FileInputStream(fileName);
		    DataInputStream in = new DataInputStream(fstream);
		    BufferedReader br = new BufferedReader(new InputStreamReader(in));
		    
		    String stringToLookFor = "Alpharetta High School Student", strLine;
		    
		    while ((strLine = br.readLine()) != null)   {
			      int startIndex = strLine.indexOf(stringToLookFor);
			      while (startIndex != -1) {
			        count++;
			        students.add(new Student());
			        startIndex = fileName.indexOf(stringToLookFor, startIndex + stringToLookFor.length());
			      }
			}
		    
		    in.close();
		    
		    //System.out.println(count); // successfully finding number of students
		    //for (int k = 0; k < count; k++)
		    	//System.out.println(students.get(k).toString()+ "\n"); // successfully creating empty students

		    
		    /** getting information and recreating students for database able to use within file**/
		    String temp = "";
		    
		    for (int numStu = 0; numStu < count; numStu++) // going through each student
			{
			    
			    
		    	Student tempStudent = students.get(numStu);
		    	
		    	/**System.out.println("-------------------\nStudent " + numStu + ": \n");**/
		    	
				for (int i = 0; i < 5; i++) // searching by column
				{
					FileInputStream fstream2 = new FileInputStream(fileName);
				    DataInputStream in2 = new DataInputStream(fstream2);
				    BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
					
			    	String strLine2;	
			    	
			    	ArrayList<String> tempList = new ArrayList<String>();

			    	int add = 0;
			    	
			    	if (i == 0)
			    	{
			    		stringToLookFor = "First";
			    		add = 2;
			    	}
			    	else if (i == 1)
			    	{
			    		stringToLookFor = "Last";
			    		add = 2;
			    	}
			    	else if (i == 2)
			    	{
			    		stringToLookFor = "Id:";
			    		add = 1;
			    	}
			    	else if (i == 3)
			    	{
			    		stringToLookFor = "Grade";
			    		add = 2;
			    	}
			    	else
			    	{
			    		stringToLookFor = "Hours:";
			    		add = 1;
			    	}
			    		
			    	
			    	while ((strLine2 = br2.readLine()) != null)   {
			    		String arr[] = strLine2.split(" ");
			    		
			    		for (int num = 0; num < arr.length; num++)
			    			tempList.add(arr[num]);
			    	}//while close
			    	
				    int tempCount = 0;
				    for (int k = 0; k < tempList.size(); k++)
				    {
				    	if (tempList.get(k).equals(stringToLookFor))
				    	{
				    		if (tempCount == numStu)
				    		{
					  			temp = tempList.get(k+add);
					   			k = tempList.size();
				    		}
				    		tempCount++;
				    	}
				    }
			    		
			    	/**System.out.println("Index " + i + " " + stringToLookFor + ": " + temp);**/
			    	
			    	if (i == 0)
			    		tempStudent.setMyFirstName(temp);
			    	else if (i == 1)
			    		tempStudent.setMyLastName(temp);
			    	else if (i == 2)
			    		tempStudent.setMyStudentId(Integer.parseInt(temp));
				    else if (i == 3)
				    	tempStudent.setMyGradeLevel(Integer.parseInt(temp));
				    else
				    	tempStudent.setMyTotalServiceHours(Double.parseDouble(temp));
			    	
	    	 	    		
			    	temp = "";	
			    	in2.close();
				} //column loop close
			} //stuCount loop close
		  } //try close
		  catch (Exception e)
		  {//Catch exception if any
		    System.err.println("Error: " + e.getMessage());
		  }
		  
		  /** code for checking data successfully transferring
		  	System.out.println("\nstudents array loop:\n");
		 	for (int k = 0; k < count; k++)
		    	System.out.println(students.get(k).toString() + "\n");
		 
		 	System.out.println("\n\nstudents array toString:\n" + students.toString());
		  **/
		  
		  System.out.println();
		  alpharetta = new School_CSA(students);
		  
		  System.out.println("Alpharetta roster array successfully built");
		  
		  createServiceDatabase();
			
	}

	
	public static void createServiceDatabase() {
		
		int count = alpharetta.getArrayLength();
    	ArrayList<String> tempList = new ArrayList<String>(); //tempList is an array of the entire ServiceDatabase
		
		/** read through file once and save to array *******************************************/
		try {
			FileInputStream fstream2 = new FileInputStream(serviceFileName);
		    DataInputStream in2 = new DataInputStream(fstream2);
		    BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
			
	    	String strLine2;	
	    	
	    	
			// transferring each individual word to new array for manipulation
	    	while ((strLine2 = br2.readLine()) != null)   {
	    		String arr[] = strLine2.split(" ");
	    		
	    		for (int num = 0; num < arr.length; num++)
	    			tempList.add(arr[num]);
	    	}//while close
	    	in2.close();
		}
		catch (Exception e)
		{
		    System.err.println("Error: " + e.getMessage());
		}
		
		//for (int i = 0; i < tempList.size(); i++)
			//System.out.print("" + tempList.get(i) + "///");
		/***System.out.println("Successfully created tempList array (array of database)");***/
		
		
		/** loop through each student in array ***************************************************/
		for (int numStu = 0; numStu < count; numStu++)
	{
			Student tempStudent = alpharetta.getSpecificStudent(numStu);
		
		/** create individualized student array ***********************/
    	ArrayList<String> indivStuInfo =  new ArrayList<String>();
    	
    	String startString = ""+tempStudent.getStudentId();
    	String endString = "";
    	if (!(numStu == alpharetta.getArrayLength()-1))
    		endString = ""+alpharetta.getSpecificStudent(numStu+1).getStudentId();
    	else
    		endString = "***";
    	
    	int startIndex = tempList.indexOf(startString);
    	int endIndex = tempList.indexOf(endString);
    	
    	while (startIndex < endIndex)
    	{
    		indivStuInfo.add(tempList.get(startIndex));
    		startIndex++;
    	}
    	
    	//System.out.println();
    	//for (int i = 0; i < indivStuInfo.size(); i++)
    		//System.out.print("" + indivStuInfo.get(i) + "///");
    	//System.out.println();
    	
    	/***System.out.println("Successfully created " + tempStudent.getFirstName() + "'s array."); ***/
    	/***************************************************************/
    	
    	/** find number of "Name:"s to determine number of projects to make *****/
    	int numProj = 0;
    	for (int i = 0; i < indivStuInfo.size(); i++)
    		if (indivStuInfo.get(i).contentEquals("Name:"))
    			numProj++;
    	
    	//adds service projects to student object
    	for (int j = 0; j < numProj; j++)
    		tempStudent.addServiceProject();
    	
    	//System.out.println(numProj);
    	//System.out.println(tempStudent.getServiceProjectsArray().size());
    	
		int startIndex1 = 0;
		 /** looping each project ***/ 
		for (int projNum = 0; projNum < numProj; projNum++)
		{

			for (int i = 0; i < 3; i++) // searching by topic (project name, description, hours)
			{
				String stringToLookFor, finalString;
				String temp = "";
				
				int add = 0;
				
				if (i == 0)
				{
					stringToLookFor = "Name:";
					finalString = "Description:";
					add = 1;
				}
				else if (i == 1)
				{
					stringToLookFor = "Description:";
					finalString = "Hours:";
					add = 1;
				}
				else
				{
					stringToLookFor = "Hours:";
					finalString = "}";
					add = 2;
				}
			    
				for (int k = startIndex1; k < indivStuInfo.size(); k++)
				{
					if (indivStuInfo.get(k).equals(stringToLookFor))
					{
						k += add;
						while (!(indivStuInfo.get(k).equals(finalString)))
						{
							temp += indivStuInfo.get(k) + " ";
							k++;
						}
						if (i == 2)
							startIndex1 = k;
						k = tempList.size();
					}
				}
			    			
				
				if (i == 0) {
					tempStudent.getSpecificServiceProject(projNum).setName(temp);
				}
		    	else if (i == 1) {
					tempStudent.getSpecificServiceProject(projNum).setDescription(temp);
		    	}
		    	else 		 // loop through dates and create projects and add
		    	{
		    		ArrayList<String> dates = new ArrayList<String>();
		    		
		    		String temp2 = temp;
		    		String temp3 = "";
		    		
		    		for (int c = 0; c < temp2.length()-1; c++)
		    		{
		    			temp2 = temp2.substring(c);
		    			c = temp2.indexOf("Date:");
		    			int end = temp2.indexOf(",");
		    			temp3 = temp2.substring(c,end); // remember end index exclusive
		    			dates.add(temp3);
		    			c+= end;
		    		}
		    				    		
		    		for (int go = 0; go < dates.size(); go++)
		    			tempStudent.getSpecificServiceProject(projNum).addServiceHours(dates.get(go));
		    	}
		    		
			} //project topic loop close
			
			//System.out.println(tempStudent.getSpecificServiceProject(projNum).toString());
    		/***System.out.println("Successfully created project " + projNum +" and added correct information");***/
		} //project array loop close
		
		//System.out.println(tempStudent.printProjectArray());
		/***System.out.println("Able to transfer " + tempStudent.getFirstName() + "'s information to ServiceDatabase");***/
	}//stuNum loop close

		//System.out.println(alpharetta.printServiceDatabase());
		//alpharetta.updateEntireDatabase(fileName, serviceFileName);
		
		System.out.println("Alpharetta service database successfully integrated into application\n");
	}
	
	public static void buildWelcomeGUI()
	{	
		createSchoolRoster();
				
		CSA_AwardProgram.setUIFont(new javax.swing.plaf.FontUIResource("Segoe UI",Font.PLAIN,14));



		JFrame welcomeFrame = new JFrame("Welcome to CSA_AwardProgram");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		/**************************************************************************************************/
		JPanel welcomePanel = new JPanel();
		welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
		//welcomePanel.setBorder(new EmptyBorder(200, 0, 80, 200));

		
		JLabel FBLAIntro = new JLabel("Future Business Leaders of America "); 
		JLabel CSAIntro = new JLabel("Community Service Awards");
		FBLAIntro.setForeground(new Color(0, 82, 155));
		FBLAIntro.setFont(new javax.swing.plaf.FontUIResource("Segoe UI Semibold",Font.PLAIN,16));
		CSAIntro.setForeground(new Color(0, 82, 155));
		CSAIntro.setFont(new javax.swing.plaf.FontUIResource("Segoe UI Semibold",Font.PLAIN,16));
		
		
		
		/** button panel **********************************************************************************/
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		//buttonPanel.setBorder(new EmptyBorder(0, 200, 0, 100));

		JButton registerNewStudent = new JButton("Register New Student");
		JButton viewRoster = new JButton("View School Roster");
		
		registerNewStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent register)
			{
				CSA_AwardProgram.buildRegisterNewStudentGUI();
				welcomeFrame.dispose();
			}
			
			
		}); 
		
		viewRoster.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent view)
		    { 
		       	CSA_AwardProgram.buildMainPageGUI();
		    	welcomeFrame.dispose();
		    }
     	});
		
		
		
		/** finalizations *****************************************************************************/
		 welcomePanel.add(FBLAIntro);
		 welcomePanel.add(CSAIntro);
		 
		 buttonPanel.add(registerNewStudent);
		 buttonPanel.add(viewRoster);
		 
		 mainPanel.add(welcomePanel);
		 mainPanel.add(buttonPanel);
		 mainPanel.setBackground(Color.white);
		 
		 welcomeFrame.add(mainPanel);		 
		 welcomeFrame.setSize(1000, 700);  
		 welcomeFrame.setLocationRelativeTo(null);  
		 welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		 welcomeFrame.setVisible(true);	  
	}
	
	
	public static void buildMainPageGUI()  
	{
		createSchoolRoster();
		
		mainActive = true;
		needUpdateTable = true;
		
		/*** creates GUI's general frame + panels ********************************************************/  
	    JFrame frame = new JFrame("Main Page - Alpharetta High School Roster");  // makes title in upper left hand corner
	   	JPanel mainPanel = new JPanel();
	   	mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	    
	    JPanel firstPanel = new JPanel();
	    JPanel tablePanel = new JPanel();
	    JPanel directionsPanel = new JPanel();
	    
	    firstPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	   	firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.X_AXIS));

	    tablePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	   	tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
	   	tablePanel.setPreferredSize(new Dimension(1000, 500));
	    directionsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

	    
	    JPanel buttonPanel = new JPanel();
	    buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    
	    /*** Panel 1 + Welcome Label *********************************************************************/
	    firstPanel.setOpaque(true);
	    firstPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	    firstPanel.setMaximumSize(new Dimension(700,20));
	    
	    JLabel welcomeText = new JLabel();
	    welcomeText.setText("Alpharetta High School Community Service Award Management");
	    welcomeText.setFont(new javax.swing.plaf.FontUIResource("Segoe UI Semibold",Font.PLAIN,16));
	    welcomeText.setForeground(new Color(0, 82, 155));
	    
	    JButton needHelp = new JButton("Need help?");
	    needHelp.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e)
	    	{ 
	        	faqGUI();
	    	}
	        
	    });
	    
	    /*** Panel 2 + Table + EditRoster Button *********************************************************/
	    //tablePanel ------------------------------------------------------------------------------------
    		tablePanel.setOpaque(true);
	    	tablePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	    	//tablePanel.setMaximumSize(new Dimension(1000,400)); // remember to switch back to 700 width!!!
	    	
	    //table ------------------------------------------------------------------------------------------
	    	
	    //hardcode data input; will adjust with text file code
	    	String column[]={"STUDENT ID", "FULL NAME","GRADE LEVEL","SERVICE HOURS", "AWARD ELIGIBLE?"};  
	    	// 5 col
	    	
	    	String data[][] = new String[alpharetta.getArrayLength()][5];
	    
	    	//forever loop?			
	    if (needUpdateTable)
	    {
	    	for (int r = 0; r < alpharetta.getArrayLength(); r++)
	    	{
	    		Student student = alpharetta.getSpecificStudent(r);
	    		
	    		for (int c = 0; c < 5; c++)
	    		{
	    			if (c == 0)
	    				data[r][c] = "" + student.getStudentId();
	    			else if (c == 1)
	    				data[r][c] = student.getFullName();
	    			else if (c == 2)
	    				data[r][c] = "" + student.getGradeLevel();
	    			else if (c == 3)
	    				data[r][c] = "" + student.getTotalServiceHours();
	    			else
	    				data[r][c] = "" + (student.isCommunityEligibility() || student.isServiceEligibility() || student.isAchieveEligibility());
	    		}
		    }
	    	needUpdateTable = false;
	    }
	
	    
	    
		// adjusts column sizes
	    JTable jt = new JTable(data, column);
	    JScrollPane sp = new JScrollPane(jt);    
    	
	    	TableColumn changeColumn = null;
	    	for (int ci = 0; ci < jt.getColumnCount(); ci++)
	    	{
	    		changeColumn = jt.getColumnModel().getColumn(ci);
	    		
	    		if (ci == 1)
	    			changeColumn.setPreferredWidth(200);
	    		else  if (ci == 3)
	    				changeColumn.setPreferredWidth(120);
	    		else
	    			changeColumn.setPreferredWidth(150);
	    		
	    	}
	    	
	    jt.setPreferredScrollableViewportSize(jt.getPreferredSize());
	    jt.setFillsViewportHeight(true);
	    
	    jt.addMouseListener(new MouseAdapter() {
	    	public void mousePressed(MouseEvent e) {
	    		if (e.getClickCount() == 1) {
	    			JTable target = (JTable)e.getSource();
	    			
	    			int row= target.rowAtPoint(e.getPoint());

	    			int col= target.columnAtPoint(e.getPoint());
	    			
	    			if (col == 1) {
	    				Student tempStu = alpharetta.getSpecificStudent(row);
	    				buildIndivStudentProfileGUI(tempStu);
	    				frame.dispose();
	    			}
	    		}
	    	}
	    });
	    
	    
	    // addStudentFx button -----------------------------------------------------------------------
	    JButton addStudentFx= new JButton("Add Student");
	    addStudentFx.setBounds(50,100,95,30);
	    
	    //create new popup asking for student information (DONE)
	    //append student information to text file
	    //from text file, print new student row in table
	    
	    /*** Popup Window for Inserting New Student Information *****************************************/
	    addStudentFx.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e)
	    	{ 
	        	CSA_AwardProgram.buildRegisterNewStudentGUI();
	        	mainActive = false;
	        	frame.dispose();
	    	}
	        
	    });
	    
	    /** Delete Student Function *********************************************************************/
	    // deleteStudent button --------------------------------------------------------------------------
	    JButton deleteStudent = new JButton("Delete Student");
	    deleteStudent.setBounds(50,100,95,30);
	    
	    deleteStudent.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		makeDeleteStudentGUI();
	    		
	    		frame.dispose();
	    	}
	    });
	    
	    /** View Service Hours *************************************************************************/
	    JButton viewServiceHours = new JButton("View Service Hours");
	    
	    viewServiceHours.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		buildServiceHourStudentSelectorGUI();
	    		frame.dispose();
	    	}
	    });
	    
	    // prompt to select student name
	    // once selected, find row number and delete row in table in text file

	    
	    /** Directions **/
	    JLabel directions = new JLabel("To view or edit an individual student's information, please click on the student's name in the table.");
	    directions.setFont(new javax.swing.plaf.FontUIResource("Segoe UI Semibold",Font.PLAIN,16));

	    
	    /** End Panel ************************************************************************************/
	    JPanel endPanel = new JPanel();
	    endPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    endPanel.setPreferredSize(new Dimension(1000, 100));
	    
	    
	    /** Print Information Function *******************************************************************/
	    JButton printStudentDatabase = new JButton ("Print Student Hour Report");
	    printStudentDatabase.setBounds(50,100,95,30);
	    
	    printStudentDatabase.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		alpharetta.printStudentHourReport(folderName);
	    		
	    		
	    	}
	    	
	    	
	    });
	    
	    JButton printAwards = new JButton ("Print Award Recipient List");
	    printAwards.setBounds(50,100,95,30);
	    
	    printAwards.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		buildAwardSelection();
	    		frame.dispose();
	    	}
	    	
	    	
	    });
	    
	    JButton restore = new JButton("Restore Previous Information");
	    restore.setBounds(50,100,95,30);
	    
	    restore.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e)
	    	{	    		
	    		restorePreviousInfo();
	    		frame.dispose();
	    	}
	    });
	    
	    
	    /*** Frame Finalizations *************************************************************************/
        //Note: anything you want ordered together with FlowLayout must be in same panel
	    firstPanel.add(welcomeText);
	    firstPanel.add(needHelp);
	    firstPanel.setBackground(Color.white);

	    
	    buttonPanel.add(addStudentFx);
	    buttonPanel.add(deleteStudent);
	    buttonPanel.add(viewServiceHours);
	    
	   
	    endPanel.add(printStudentDatabase);
	    endPanel.add(printAwards);
	    endPanel.add(restore);
	    endPanel.setBackground(new Color(0, 82, 155));
	    
	    directionsPanel.add(directions);
	   	    
	    tablePanel.add(buttonPanel);
	    tablePanel.add(directionsPanel);
	    tablePanel.add(sp);
	    //tablePanel.setSize(tablePanel.getWidth(), jt.getHeight());
	    
	    mainPanel.add(firstPanel);
	    mainPanel.add(tablePanel);
	    mainPanel.add(endPanel);
	    
		 mainPanel.setBackground(Color.white);

	    
	    frame.add(mainPanel);
        
	    frame.setSize(1000, 700);  
	    frame.setLocationRelativeTo(null);  
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	    frame.setVisible(true);	    
	}
	
	
	 public static void buildRegisterNewStudentGUI()
	 {		
     	//popup window appear
     	JFrame popup = new JFrame("New Student Registration");
     	JPanel subMainPanel = new JPanel();
         subMainPanel.setLayout(new BoxLayout(subMainPanel, BoxLayout.Y_AXIS));
         
         JPanel fieldPanel = new JPanel();
         fieldPanel.setLayout(new GridLayout(10,1));
         
     	//enter text fields for information and save to text file
     	JLabel directions = new JLabel();
     	directions.setText("Please enter the following information for your new student:");
	    directions.setFont(new javax.swing.plaf.FontUIResource("Segoe UI Semibold",Font.PLAIN,16));

     	
     	JTextField firstField, lastField, idField, gradeField, hoursField;
     	
     	JLabel firstLabel = new JLabel();
     	firstLabel.setText("First name: ");
     	firstField = new JTextField();  
     	
     	JLabel lastLabel = new JLabel();
     	lastLabel.setText("Last name: ");
     	lastField = new JTextField();  
     	
     	JLabel idLabel = new JLabel();
     	idLabel.setText("ID: ");
     	idField = new JTextField();  

     	JLabel gradeLabel = new JLabel();
     	gradeLabel.setText("Grade level: ");
     	gradeField = new JTextField();  

//     	JLabel hoursLabel = new JLabel();
//     	hoursLabel.setText("Total service hours: ");
//     	hoursField = new JTextField();  
     	
     	
     	JButton enterNewInfo = new JButton("Submit Information");
     	enterNewInfo.setBounds(50,100,95,30);
     	enterNewInfo.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent e)
     		{     			
	     		try {
	     			// gets input from textField and appends to text file
	     			String myFirst = firstField.getText();
	     			String myLast = lastField.getText();
	     			String myID = idField.getText();
	     			String myGrade = gradeField.getText();
	     			//String myHours = hoursField.getText();
	     			
	     			
	     			//alpharetta.addStudent(myFirst, myLast, myID, myGrade, myHours);
	     			alpharetta.addStudent(myFirst, myLast, myID, myGrade);
	     			
	     			//PrintWriter writer = new PrintWriter(fileName);
	     			//writer.print(alpharetta.toString());
	     			//writer.close();
	     			alpharetta.updateEntireDatabase(folderName, fileName, serviceFileName);

	     		}
	     		catch (Exception e1)
	     		{ 
					JOptionPane.showMessageDialog(popup, "There was an error. Please try again.");
	     			System.out.println("Exception Occurred" + e1); 
	     		}
	     		
	     		buildMainPageGUI();
	     		popup.dispose();
	     		
     		}; 
     		} );
     	// end of entire statement
     	
     	JButton returnButton = new JButton("Cancel");
		 returnButton.setBounds(50,100,95,30);
		    
			returnButton.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e)
		    	{
		    		buildMainPageGUI();
		    		popup.dispose();
		    	}
			});
			
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));
     			
     	// update information on table OR call method that does it
     	//		- have method that actively keeps searching for update??
     	
		subPanel.add(enterNewInfo);
		subPanel.add(returnButton);
         
     	fieldPanel.add(firstLabel);
     	fieldPanel.add(firstField);
     	
     	fieldPanel.add(lastLabel);
     	fieldPanel.add(lastField);
     	
     	fieldPanel.add(idLabel);
     	fieldPanel.add(idField);
     	
     	fieldPanel.add(gradeLabel);
     	fieldPanel.add(gradeField);
     	
     	//fieldPanel.add(hoursLabel);       	
     	//fieldPanel.add(hoursField);
     	
     	subMainPanel.add(directions);
     	subMainPanel.add(fieldPanel);
     	subMainPanel.add(subPanel);
		subMainPanel.setBackground(Color.white);

     	
     	popup.add(subMainPanel);
         popup.setSize(600, 400);  
         popup.setLocationRelativeTo(null);  
         popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
         popup.setVisible(true);	   
	    }
	 
	 public static void makeDeleteStudentGUI() {
		 int alphalen = alpharetta.getArrayLength();		 
		 
		 JFrame frame = new JFrame("Student Deletion");
		 JPanel mainPanel = new JPanel();
		 mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		 
		 JPanel newYearPanel = new JPanel();
		 newYearPanel.setLayout(new BoxLayout(newYearPanel, BoxLayout.X_AXIS));
		 
		 /** adjust roster button and function ****************************************************************************/
		 JLabel newYearDirections = new JLabel("New School Year? Click here to adjust grade levels and remove seniors.");
		 
		 JButton adjustRoster = new JButton("Adjust Roster");
		 adjustRoster.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 ArrayList<Student> removedStudents =  new ArrayList<Student>();

				 for (int i = 0; i < alpharetta.getArrayLength(); i++)
				 {
					 Student temp = alpharetta.getSpecificStudent(i);
					 temp.setMyGradeLevel(temp.getGradeLevel() + 1);
					 
					 if (temp.getGradeLevel() > 12)
						 removedStudents.add(temp);
				 }
				 			
				 for (int j = 0; j < removedStudents.size(); j++)
				 {
					 for (int k = 0; k < alpharetta.getArrayLength(); k++)
					 {
						 if (removedStudents.get(j) == alpharetta.getSpecificStudent(k))
						 {
							 alpharetta.removeStudent(k);
							 k--;
							 System.out.println(removedStudents.get(j).getFullName() + " removed");
						 }
					 }
				 }
				 
				 
				 alpharetta.updateEntireDatabase(folderName, fileName, serviceFileName);
				 
				System.out.println("Database altered");
				
				
				String print = "The following students were successfully removed: \n";
				
				 for (int count = 0; count < removedStudents.size(); count++)
					 print+= removedStudents.get(count).getFullName() + "\n";
				
				JOptionPane.showMessageDialog(frame, print);
				 
		     	CSA_AwardProgram.buildMainPageGUI();

		     	frame.dispose();
			 }
		 });

		 
		 
		 JPanel directionsPanel =  new JPanel();		 
		 directionsPanel.setLayout(new BoxLayout(directionsPanel, BoxLayout.X_AXIS));
		 JLabel directions = new JLabel("Please click on which student(s) to delete in the list below.");
		    directions.setFont(new javax.swing.plaf.FontUIResource("Segoe UI Semibold",Font.PLAIN,16));

		 
		 
		 /** nameListPanel and delete single student functionality ***********************************************/
		 JPanel nameListPanel = new JPanel();
		 nameListPanel.setLayout(new BoxLayout(nameListPanel, BoxLayout.X_AXIS));
	
		 JTabbedPane tp = new JTabbedPane();
		 
		 JPanel ninePanel = new JPanel();
		 	for (int i = 0; i < alphalen; i++)
		 	{
		 		if (alpharetta.getSpecificStudent(i).getGradeLevel() == 9) {
		 		 JRadioButton rb = new JRadioButton();
		 		 String name = alpharetta.getSpecificStudent(i).getFullName(); 
		 		 rb.setText(name);
				 rb.setName(name);

		 		 ninePanel.add(rb);
		 		}
		 	}
		 			 
		 JPanel tenPanel = new JPanel();
			 for (int i = 0; i < alphalen; i++)
			 {
			 	if (alpharetta.getSpecificStudent(i).getGradeLevel() == 10) {
			 	 JRadioButton rb = new JRadioButton();
			 	 String name = alpharetta.getSpecificStudent(i).getFullName(); 
			 	 rb.setText(name);
				 rb.setName(name);

			 	 tenPanel.add(rb);
			 	}
			 }
		 	
		 JPanel elevenPanel = new JPanel();
			 for (int i = 0; i < alphalen; i++)
			 {
			 	if (alpharetta.getSpecificStudent(i).getGradeLevel() == 11) {
			 	 JRadioButton rb = new JRadioButton();
			 	 String name = alpharetta.getSpecificStudent(i).getFullName(); 
			 	 rb.setText(name);
			 	 rb.setName(name);
			 	 
			 	 elevenPanel.add(rb);
			 	}
			 }
			 
		 JPanel twelvePanel = new JPanel();
			 for (int i = 0; i < alphalen; i++)
			 {
			 	if (alpharetta.getSpecificStudent(i).getGradeLevel() == 12) {
			 	 JRadioButton rb = new JRadioButton();
			 	 String name = alpharetta.getSpecificStudent(i).getFullName(); 
			 	 rb.setText(name);
			 	 rb.setName(name);
			 	 
			 	 twelvePanel.add(rb);

			 	}
			 }
		 
		 tp.setBounds(50,50, 200,200);
		 tp.add("9th Graders", ninePanel);
		 tp.add("10th Graders", tenPanel);
		 tp.add("11th Graders", elevenPanel);
		 tp.add("12th Graders", twelvePanel);
		 		 
		 ArrayList<String> indivToDelete = new ArrayList<String>();
		 JButton deleteIndivs = new JButton("Delete Selected Students");
		 deleteIndivs.setBounds(50,100,95,30);
		 deleteIndivs.addActionListener(new ActionListener() {
	 			public void actionPerformed(ActionEvent e) {
	 				for (int nine = 0; nine < ninePanel.getComponentCount(); nine++)
	 				{
		 				if (((AbstractButton) ninePanel.getComponent(nine)).isSelected()) {
		 					indivToDelete.add(ninePanel.getComponent(nine).getName());
		 					System.out.println(ninePanel.getComponent(nine).getName());
		 				}
	 				}
	 				for (int ten = 0; ten < tenPanel.getComponentCount(); ten++)
	 				{
		 				if (((AbstractButton) tenPanel.getComponent(ten)).isSelected()) {
		 					indivToDelete.add(tenPanel.getComponent(ten).getName());
		 					System.out.println(tenPanel.getComponent(ten).getName());
		 				}
	 				}
	 				for (int eleven = 0; eleven < elevenPanel.getComponentCount(); eleven++)
	 				{
		 				if (((AbstractButton) elevenPanel.getComponent(eleven)).isSelected()) {
		 					indivToDelete.add(elevenPanel.getComponent(eleven).getName());
		 					System.out.println(elevenPanel.getComponent(eleven).getName());
	 					}
	 				}
	 				for (int twelve = 0; twelve < twelvePanel.getComponentCount(); twelve++)
	 				{
		 				if (((AbstractButton) twelvePanel.getComponent(twelve)).isSelected()) {
		 					indivToDelete.add(twelvePanel.getComponent(twelve).getName());
		 					System.out.println(twelvePanel.getComponent(twelve).getName());
	 					}
	 				}
	 				
	 				 for (int j = 0; j < indivToDelete.size(); j++)
					 {
						 for (int k = 0; k < alpharetta.getArrayLength(); k++)
						 {
							 if (indivToDelete.get(j).equals(alpharetta.getSpecificStudent(k).getFullName()))
							 {
								 alpharetta.removeStudent(k);
								 k--;
								 System.out.println(indivToDelete.get(j) + " removed");
							 }
						 }
					 }
	 				
	 				
	 				alpharetta.updateEntireDatabase(folderName, fileName, serviceFileName);
					 
	 				System.out.println("Database altered");
	 				
	 				
	 				String print = "The following students were successfully removed: \n";
	 				
	 				 for (int count = 0; count < indivToDelete.size(); count++)
	 					 print+= indivToDelete.get(count) + "\n";
	 				
	 				JOptionPane.showMessageDialog(frame, print);
	 				 
	 		     	CSA_AwardProgram.buildMainPageGUI();

	 		     	frame.dispose();
	 			}
	 		});
		 
		 
		 JButton returnButton = new JButton("Back to School Roster");
		 returnButton.setBounds(50,100,95,30);
		    
			returnButton.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e)
		    	{
		    		buildMainPageGUI();
		    		frame.dispose();
		    	}
			});
		 
		 JPanel endPanel = new JPanel();
		 endPanel.setLayout(new BoxLayout(endPanel, BoxLayout.X_AXIS));
		 
		 
		 /** Finalizations ***********************************************************************/		 
		 newYearPanel.add(newYearDirections);
		 newYearPanel.add(adjustRoster);
		 
		 directionsPanel.add(directions);
		 nameListPanel.add(tp);
		 endPanel.add(deleteIndivs);
		 endPanel.add(returnButton);
		 
		 mainPanel.add(newYearPanel);
		 mainPanel.add(directionsPanel);
		 mainPanel.add(nameListPanel);
		 mainPanel.add(endPanel);
		 mainPanel.setBackground(Color.white);

		 
		 frame.add(mainPanel);
		 frame.setSize(600, 400);  
		 frame.setLocationRelativeTo(null);  
		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		 frame.setVisible(true);	
	 }
	 
	 
	 public static void buildIndivStudentProfileGUI(Student stu)
	 {
		 /** Initializations ********************************************************************/
		JFrame indivStuFrame = new JFrame("Individual Student Information");
		JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JPanel studentInfoPanel = new JPanel();
		studentInfoPanel.setLayout(new GridLayout(5,1));
		
		JPanel buttonPanel = new JPanel();
		//buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		
		/** introPanel (generalIntro + returnButton) **************************************************************************/
		JPanel introPanel = new JPanel();
		introPanel.setLayout(new BoxLayout(introPanel, BoxLayout.X_AXIS));
		
		JLabel generalIntro = new JLabel();
		generalIntro.setText("Student Profile: " + stu.getFullName());
	    generalIntro.setFont(new javax.swing.plaf.FontUIResource("Segoe UI Semibold",Font.PLAIN,16));

		
		JButton returnButton = new JButton("Back to School Roster");
		returnButton.setBounds(50,100,95,30);
	    
		returnButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		buildMainPageGUI();
	    		indivStuFrame.dispose();
	    	}
	    });
		
		 
		/** studentInfoPanel ***********************************************************************/		 
		JLabel firstLabel = new JLabel();
		firstLabel.setText("First name: " + stu.getFirstName());
	     	
		JLabel lastLabel = new JLabel();
		lastLabel.setText("Last name: " + stu.getLastName());
	     	
	    JLabel idLabel = new JLabel();
	    idLabel.setText("ID: " + stu.getStudentId());
	     	
	    JLabel gradeLabel = new JLabel();
	    gradeLabel.setText("Grade level: " + stu.getGradeLevel());
	     	
	    JLabel hoursLabel = new JLabel();
	    hoursLabel.setText("Total service hours: " + stu.getTotalServiceHours());
	    
	    /** buttonPanel *********************************************************************************/
	    JButton viewHours = new JButton("View Service Hours and Projects");
	    viewHours.setBounds(50,100,95,30);
	    
	    viewHours.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		buildIndivServiceProfileGUI(stu);
	    		indivStuFrame.dispose();
	    	}
	    });
	    
	    
	    JButton editInformation = new JButton("Edit " + stu.getFirstName() + "'s Information");
	    editInformation.setBounds(50,100,95,30);
	    
	    editInformation.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		buildEditIndivStuGUI(stu);
	    		indivStuFrame.dispose();
	    	}
	    });

		 
	     
	     /** Finalizations ****************************************************************************/
	    introPanel.add(returnButton);
	    introPanel.add(generalIntro); 
	    
	    
	     studentInfoPanel.add(firstLabel);
	     studentInfoPanel.add(lastLabel);
	     studentInfoPanel.add(idLabel);
	     studentInfoPanel.add(gradeLabel);
	     studentInfoPanel.add(hoursLabel);
	     
	     buttonPanel.add(viewHours);
	     buttonPanel.add(editInformation);
	     
		 
		 mainPanel.add(introPanel);
	     mainPanel.add(studentInfoPanel);
	     mainPanel.add(buttonPanel);
		 mainPanel.setBackground(Color.white);

	     
		 indivStuFrame.add(mainPanel);
		 indivStuFrame.setSize(600, 400);  
		 indivStuFrame.setLocationRelativeTo(null);  
		 indivStuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //doesn't cancel complete application
		 indivStuFrame.setVisible(true);	 		 
	 }
	
	
	 public static void buildEditIndivStuGUI(Student stuToEdit)
	 {		
		JFrame frame = new JFrame("Edit Individual Student");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		/** directionsPanel **********************************************************/
		JPanel directionPanel = new JPanel();
		JLabel directions = new JLabel();
		directions.setText("Please confirm " + stuToEdit.getFullName() + "'s information below.");
		directionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		/** fieldPanel **************************************************************/		
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridLayout(10,1));
			
		JTextField firstField, lastField, idField, gradeField, hoursField;
			
		JLabel firstLabel = new JLabel();
     	firstLabel.setText("First name: ");
     	firstField = new JTextField(stuToEdit.getFirstName());  
     	
     	JLabel lastLabel = new JLabel();
     	lastLabel.setText("Last name: ");
     	lastField = new JTextField(stuToEdit.getLastName());  
     	
     	JLabel idLabel = new JLabel();
     	idLabel.setText("ID: ");
     	idField = new JTextField(""+stuToEdit.getStudentId());  

     	JLabel gradeLabel = new JLabel();
     	gradeLabel.setText("Grade level: ");
     	gradeField = new JTextField(""+stuToEdit.getGradeLevel());  

//     	JLabel hoursLabel = new JLabel();
//     	hoursLabel.setText("Total service hours: ");
//     	hoursField = new JTextField(""+stuToEdit.getTotalServiceHours());  
     	
     	/** submitPanel *******************************************************************/
     	JPanel submitPanel = new JPanel();
     	submitPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

     	JButton enterNewInfo = new JButton("Submit Information");
     	enterNewInfo.addActionListener(new ActionListener() {
 	    	public void actionPerformed(ActionEvent e)
 	    	{
 	    		try {
 	    		//replace student information
 	    		stuToEdit.setMyFirstName(firstField.getText());
 	    		stuToEdit.setMyLastName(lastField.getText());
 	    		stuToEdit.setMyStudentId(Integer.parseInt(idField.getText()));
 	    		stuToEdit.setMyGradeLevel(Integer.parseInt(gradeField.getText()));
 	    		//stuToEdit.setMyTotalServiceHours(Double.parseDouble(hoursField.getText()));
 	    		
 	    		//refresh mainGUI	
 	    		alpharetta.updateEntireDatabase(folderName, fileName, serviceFileName);
 	    		buildIndivStudentProfileGUI(stuToEdit);
 	    		frame.dispose();
 	    		}
 	    		catch (Exception e1)
 	    		{
					JOptionPane.showMessageDialog(frame, "There was an error. Please try again.");
					System.out.println("Exception occured: " + e1);
 	    		}
 	    	}
 	    });
	
		/** Finalizations ******************************************************************/ 
     	directionPanel.add(directions);
     	submitPanel.add(enterNewInfo);
     	
     	
     	fieldPanel.add(firstLabel);
		fieldPanel.add(firstField);
		fieldPanel.add(lastLabel);
		fieldPanel.add(lastField);
		fieldPanel.add(idLabel);
		fieldPanel.add(idField);
		fieldPanel.add(gradeLabel);
		fieldPanel.add(gradeField);
//		fieldPanel.add(hoursLabel);
//		fieldPanel.add(hoursField);
     	
		mainPanel.add(directionPanel);
     	mainPanel.add(fieldPanel);
     	mainPanel.add(submitPanel);
		 mainPanel.setBackground(Color.white);

     	
     	frame.add(mainPanel);
		frame.setSize(600, 400);  
		frame.setLocationRelativeTo(null);  
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		frame.setVisible(true);	
	 }
	 
	 public static void buildServiceHourStudentSelectorGUI()
	 {
		 JFrame frame = new JFrame("Student Selection Screen for Viewing Service Hours");
		 JPanel mainPanel = new JPanel();
		 mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		 
		 JLabel intro = new JLabel("Please select a student from the dropdown to view their service projects and hours by project.");
		    intro.setFont(new javax.swing.plaf.FontUIResource("Segoe UI Semibold",Font.PLAIN,16));

		 
		 JButton returnButton = new JButton("Back to School Roster");
			returnButton.setBounds(50,100,95,30);
		    
			returnButton.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e)
		    	{
		    		buildMainPageGUI();
		    		frame.dispose();
		    	}
		    });
		 
		// 	REBUILD SERVICE DATABASE HERE!!!
			
		 /** student selector **********************************************************************/
		 JPanel studentSelectorPanel = new JPanel();
		 //studentSelectorPanel.setLayout(new BoxLayout(studentSelectorPanel, BoxLayout.Y_AXIS));
		 studentSelectorPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		 String[] names = new String[alpharetta.getArrayLength()];
		 for (int i = 0; i < alpharetta.getArrayLength(); i++)
			 names[i] = alpharetta.getSpecificStudent(i).getFullName();

		 JComboBox<String> cb = new JComboBox<String>(names);
		 cb.setBounds(50, 50, 90, 20);
		
		 JLabel label = new JLabel("No student is currently selected.");
		 JLabel stuName = new JLabel();
		 JButton selectButton = new JButton("Select Student");
		 selectButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 label.setText("View " + cb.getItemAt(cb.getSelectedIndex()) + "'s community service information by confirming the student.");
				 stuName.setText(cb.getItemAt(cb.getSelectedIndex()));
			 }
		 });
				
		 
		 JButton confirmStu = new JButton("Confirm Student");
		 confirmStu.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				try {
				 Student tempStu = null;
				 for (int loop = 0; loop < alpharetta.getArrayLength(); loop++)
				 {
					 if (alpharetta.getSpecificStudent(loop).getFullName().equals(stuName.getText()))
						 tempStu = alpharetta.getSpecificStudent(loop);
				 }
				 
				 
				 JOptionPane.showMessageDialog(frame, "Opening " + tempStu.getFullName() + "'s Service Page");
				 buildIndivServiceProfileGUI(tempStu);
				 frame.dispose();
				}
				catch (Exception e1)
 	    		{
					JOptionPane.showMessageDialog(frame, "There was an error. Please try again.");
					System.out.println("Exception occured: " + e1);
 	    		}
			 }
		 });
		 
		 JPanel conclusion = new JPanel();
		 conclusion.setAlignmentX(Component.LEFT_ALIGNMENT);

		 
		 /** Finalizations ********************************************************************************/
		 studentSelectorPanel.add(cb);
		 studentSelectorPanel.add(selectButton);
		 
		 conclusion.add(label);
		 conclusion.add(confirmStu);
		 
		 mainPanel.add(returnButton);
		 mainPanel.add(intro);
		 mainPanel.add(studentSelectorPanel);
		 mainPanel.add(conclusion);
		 mainPanel.setBackground(Color.white);

		 
		 frame.add(mainPanel);
		 frame.setSize(1000, 600);  
		 frame.setLocationRelativeTo(null);  
		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		 frame.setVisible(true);	
	 }
	 
	 public static void buildIndivServiceProfileGUI(Student student) {
		 
		 JFrame frame = new JFrame(student.getFullName() + "'s Community Service Information");
		 JPanel mainPanel = new JPanel();
		 mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		 JPanel topPanel = new JPanel();
		 topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		 
		 /*** top panel ********************************************************************************/
		 //[Student]'s Service Projects
		 JLabel header = new JLabel(student.getFirstName() + "'s Service Projects");
		    header.setFont(new javax.swing.plaf.FontUIResource("Segoe UI Semibold",Font.PLAIN,16));

		 header.setHorizontalAlignment(JLabel.CENTER);
		 header.setVerticalAlignment(JLabel.CENTER);
		 
		 topPanel.setPreferredSize(new Dimension(600,20));
		 
		 
		 // Edit Projects Menu
		 JMenuBar mb = new JMenuBar();
		 
		 JMenu addProj = new JMenu("Add New Project");
		 JMenu editProj = new JMenu("Edit Existing Project");
		 JMenu deleteProj = new JMenu("Delete Project");
		 
		 JMenuItem change = new JMenuItem("Change Name/Description");
		 JMenuItem addHour = new JMenuItem("Add Hour(s)");
		 JMenuItem editHour = new JMenuItem("Edit Hour(s)");
		 JMenuItem deleteHour = new JMenuItem("Delete Hour(s)");
		 
		 editProj.add(change);
		 editProj.add(addHour);
		 editProj.add(editHour);
		 editProj.add(deleteHour);
		 mb.add(addProj); mb.add(editProj); mb.add(deleteProj);
		 
		 addProj.addMouseListener(new MouseAdapter() {
			 @Override
			 public void mouseClicked(MouseEvent e) {
				 addNewProjectGUI(student);
				 frame.dispose();
			 }
		 });
		 
		 deleteProj.addMouseListener(new MouseAdapter() {
			 @Override
			 public void mouseClicked(MouseEvent e) {
				 deleteProjectGUI(student);
				 frame.dispose();
			 }
		 });
		 
		 addHour.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 addNewHourGUI(student);
				 frame.dispose();
			 }
		 });
		 
		 editHour.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 editHoursPopup(student);
				 frame.dispose();
			 }
		 });
		 
		 change.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 editProject(student);
				 frame.dispose();
			 }
		 });
		 
		 deleteHour.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 deleteHoursPopup(student);
				 frame.dispose();
			 }
		 });
		 
		
		 //for loop = print Project Name, Description, Hours (with Dates)
				 
		/** project panel ****************************************************************************/
		 JPanel projectPanel = new JPanel();
		 projectPanel.setLayout(new BoxLayout(projectPanel, BoxLayout.X_AXIS));
			
		 JTabbedPane tp = new JTabbedPane();
		 tp.setBounds(50,50, 200,200);

		 // add tabs to tabbedPanel + dateAndHour buttons
		 for (int numProj = 0; numProj < student.getServiceProjectsArray().size(); numProj++)
		 {
			 JPanel tempProjPanel = new JPanel();
			 tempProjPanel.setLayout(new BoxLayout(tempProjPanel, BoxLayout.Y_AXIS));
		  
			 
			 String projName = ("Project Name: " + student.getSpecificServiceProject(numProj).getName());
			 String descrip = ("Project Description: " + student.getSpecificServiceProject(numProj).getDescription());
			 JLabel serviceLabel = new JLabel("Service Hours: ");
			 
			 String html = "<html><body style = 'width: %1spx'>%1s";
			 
			 JLabel projName2 = new JLabel (String.format(html, 400, projName));
			 JLabel descrip2 = new JLabel(String.format(html, 400, descrip));
			 
			 tempProjPanel.add(projName2);
			 tempProjPanel.add(descrip2);
			 tempProjPanel.add(serviceLabel);
			 
			 	for (int i = 0; i < student.getSpecificServiceProject(numProj).getServiceHourArray().size(); i++)
			 	{
			 		JRadioButton rb = new JRadioButton();
			 		String name = student.getSpecificServiceProject(numProj).getSpecificServiceHour(i).toString(); 
			 		rb.setText(name);
					rb.setName(name);
				 	tempProjPanel.add(rb);
			 	}
		 	
			 	
			 String title = "";
			 if (student.getSpecificServiceProject(numProj).getName().length() > 20)
				 title = student.getSpecificServiceProject(numProj).getName().substring(0, 20);
			 else
				 title = student.getSpecificServiceProject(numProj).getName();
			 
			 tp.add(title, tempProjPanel);
		 }
		
		 
		 /** return *****************************************************************************************/
		 JButton returnButton = new JButton("Back to School Roster");
		 returnButton.setBounds(50,100,95,30);
		    
			returnButton.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e)
		    	{
		    		buildMainPageGUI();
		    		frame.dispose();
		    	}
			});
			
			
			JPanel endPanel = new JPanel();
			endPanel.setBackground(new Color(0, 82, 155));
			endPanel.setMaximumSize(new Dimension(600, 100));
		 
				 
		/** finalizations ****************************************************************************/
		 projectPanel.add(tp);
		 
		 topPanel.add(header);
		 //topPanel.add(editProj);
		 //topPanel.add(addHours);
		 
		 endPanel.add(returnButton);
		    
		 mainPanel.add(topPanel);
		 mainPanel.add(projectPanel);
		 mainPanel.add(endPanel);

		 
		 frame.add(mainPanel);
		 frame.setJMenuBar(mb);
		 frame.setSize(600,400);  
		 frame.setLocationRelativeTo(null);  
		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		 frame.setVisible(true);	
	 }
	 
	 public static void addNewProjectGUI(Student student) {
		 JFrame frame = new JFrame("Adding New Project for " + student.getFullName());
		 JPanel mainPanel = new JPanel();
		 mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		 /** fieldPanel **/
		 JPanel fieldPanel = new JPanel();
			fieldPanel.setLayout(new GridLayout(8,1));
				
			JTextField projNameField, projDescripField, dateField, hourField;
				
			JLabel projNameLabel = new JLabel();
			projNameLabel.setText("Project Name: ");
			projNameField = new JTextField();  
	     	
	     	JLabel projDescripLabel = new JLabel();
	     	projDescripLabel.setText("Project Description: ");
	     	projDescripField = new JTextField();  
	     	
	     	JLabel dateLabel = new JLabel();
	     	dateLabel.setText("Date (formatted ##/##/####): ");
	     	dateField = new JTextField();  

	     	JLabel hourLabel = new JLabel();
	     	hourLabel.setText("Hour: ");
	     	hourField = new JTextField();  
	     	
	     	JButton submit = new JButton("Submit");
	     	submit.addActionListener(new ActionListener() {
	     		public void actionPerformed(ActionEvent e) {
	     			try {
	     			// add to student service projects array	     			
	     			String name = projNameField.getText();
	     			String descrip = projDescripField.getText();
	     			String date = dateField.getText();
	     			double hour = Double.parseDouble(hourField.getText());
	     			
	     			//System.out.println(name);
	    	     	//System.out.println(descrip);
	    	     	//System.out.println(date);
	    	     	//System.out.println(hour);
	    	     	
	    	     	//System.out.println("now adding to student");
	    	     	
	    	     	student.addServiceProject(new ServiceProject(name, descrip, date, hour));
	    	     	//System.out.println(student.printProjectArray());
	    	     	alpharetta.updateEntireDatabase(folderName, fileName, serviceFileName);
	    	     	
					JOptionPane.showMessageDialog(frame, "\"" + name + "\" successfully added to " + student.getFullName() + "'s profile.");
					buildIndivServiceProfileGUI(student);
	    	     	frame.dispose();
	     			}
	     			catch (Exception e1)
	 	    		{
						JOptionPane.showMessageDialog(frame, "There was an error. Please try again.");
						System.out.println("Exception occured: " + e1);
	 	    		}
	     		}
	     	});
	     	
	     	JButton returnButton = new JButton("Cancel");
			 returnButton.setBounds(50,100,95,30);
			    
				returnButton.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent e)
			    	{
			    		buildIndivServiceProfileGUI(student);
			    		frame.dispose();
			    	}
				});
				
	     JPanel end = new JPanel();
	     end.setLayout(new BoxLayout(end, BoxLayout.X_AXIS));

	     	
	     end.add(submit);
	     end.add(returnButton);
	     	
	     fieldPanel.add(projNameLabel);
	     fieldPanel.add(projNameField);
	     fieldPanel.add(projDescripLabel);
	     fieldPanel.add(projDescripField);
	     fieldPanel.add(dateLabel);
	     fieldPanel.add(dateField);
	     fieldPanel.add(hourLabel);
	     fieldPanel.add(hourField);
		 
		 
		 mainPanel.add(fieldPanel);
		 mainPanel.add(end);
		 mainPanel.setBackground(Color.white);

		 
		 frame.add(mainPanel);
		 frame.setSize(600,400);  
		 frame.setLocationRelativeTo(null);  
		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		 frame.setVisible(true);	
	 }
	 
	 public static void addNewHourGUI(Student student) {
		 JFrame frame = new JFrame("Adding Hours to " + student.getFullName() + "'s Service Project");
		 JPanel mainPanel = new JPanel();
		 mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		 // please select service project
		 
		 /** service project selector **********************************************************************/
		 JPanel projectSelectorPanel = new JPanel();
		 //studentSelectorPanel.setLayout(new BoxLayout(studentSelectorPanel, BoxLayout.Y_AXIS));
		 projectSelectorPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		 String[] projNames = new String[student.getServiceProjectsArray().size()];
		 for (int i = 0; i < student.getServiceProjectsArray().size(); i++)
			 projNames[i] = student.getSpecificServiceProject(i).getName();
		 
		 JComboBox<String> cb = new JComboBox<String>(projNames);
		 cb.setBounds(50, 50, 90, 20);
		
		 JLabel label = new JLabel("No project is currently selected.");
		 JLabel selectedProjectName = new JLabel();
		 JButton selectButton = new JButton("Select Project");
		 selectButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 label.setText("Editing \"" + cb.getItemAt(cb.getSelectedIndex()) + "\" service project.");
				 selectedProjectName.setText(cb.getItemAt(cb.getSelectedIndex()));
			 }
		 });
		 
		 
		 /** fieldPanel **************************************************************************************/
		 JPanel fieldPanel = new JPanel();
			fieldPanel.setLayout(new GridLayout(4,1));
				
			JTextField dateField, hourField;
				
	     	
	     	JLabel dateLabel = new JLabel();
	     	dateLabel.setText("Date (formatted ##/##/####): ");
	     	dateField = new JTextField();  

	     	JLabel hourLabel = new JLabel();
	     	hourLabel.setText("Hour: ");
	     	hourField = new JTextField();  
	     	
	     	JButton submit = new JButton("Submit");
	     	submit.addActionListener(new ActionListener() {
	     		public void actionPerformed(ActionEvent e) {
	     			try {
	     		 	ServiceProject project = null;
					 for (int loop = 0; loop < student.getServiceProjectsArray().size(); loop++)
					 {
						 if (student.getSpecificServiceProject(loop).getName().equals(selectedProjectName.getText()))
							 project = student.getSpecificServiceProject(loop);
					 }
					 
	     			// add to student service projects array	     			
	     			String date = dateField.getText();
	     			double hour = Double.parseDouble(hourField.getText());
	     			    				
	     			
	     			// print service project selected
	    	     	//System.out.println(date);
	    	     	//System.out.println(hour);
	    	     	
	    	     	
	    	     	project.addServiceHours(date, hour);
	    	     	//System.out.println(student.printProjectArray());
	    	     	alpharetta.updateEntireDatabase(folderName, fileName, serviceFileName);
	    	     	
	    	     	
	    	     	JOptionPane.showMessageDialog(frame, "Hour(s) successfully added to \"" + project.getName() + "\"");
					buildIndivServiceProfileGUI(student);

	    	     	frame.dispose();
	     			}
	     			catch (Exception e1)
	 	    		{
						JOptionPane.showMessageDialog(frame, "There was an error. Please try again.");
						System.out.println("Exception occured: " + e1);
	 	    		}
	     			
	     		}
	     	});
	     	
	     	 JPanel end = new JPanel();
		     end.setLayout(new BoxLayout(end, BoxLayout.X_AXIS));
		     
		 	JButton returnButton = new JButton("Cancel");
			 returnButton.setBounds(50,100,95,30);
			    
				returnButton.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent e)
			    	{
			    		buildIndivServiceProfileGUI(student);
			    		frame.dispose();
			    	}
				});
		     
	     	
	     
	     /** finalizations ********************************************************************************************/
	    end.add(submit);
	    end.add(returnButton);
				
		projectSelectorPanel.add(cb);
	     projectSelectorPanel.add(selectButton);
	     projectSelectorPanel.add(label);
	     
	     fieldPanel.add(dateLabel);
	     fieldPanel.add(dateField);
	     fieldPanel.add(hourLabel);
	     fieldPanel.add(hourField);
		 
		 mainPanel.add(projectSelectorPanel);
		 mainPanel.add(fieldPanel);
		 mainPanel.add(end);
		 mainPanel.setBackground(Color.white);

		 
		 frame.add(mainPanel);
		 frame.setSize(600,400);  
		 frame.setLocationRelativeTo(null);  
		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		 frame.setVisible(true);	
	 }
	 
	 public static void editHoursPopup(Student student)
	 {		 
		 
		 JFrame frame = new JFrame("Project Selection for Hour Editing");
		 JPanel mainPanel = new JPanel();
		 mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		 
		 JLabel intro = new JLabel("Please select a project from the dropdown to view corresponding dates and hours for editing.");
		    intro.setFont(new javax.swing.plaf.FontUIResource("Segoe UI Semibold",Font.PLAIN,13));

		 
		 /** project selector ****************************************************************************/
		 JPanel projectSelectorPanel = new JPanel();
		 //projectSelectorPanel.setLayout(new BoxLayout(studentSelectorPanel, BoxLayout.Y_AXIS));
		 projectSelectorPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		 String[] names = new String[student.getServiceProjectsArray().size()];
		 for (int i = 0; i < student.getServiceProjectsArray().size(); i++)
			 names[i] = student.getSpecificServiceProject(i).getName();

		 JComboBox<String> cb = new JComboBox<String>(names);
		 cb.setBounds(50, 50, 90, 20);
		
		 JLabel label = new JLabel("No project is currently selected.");
		 JLabel projName = new JLabel();
		 JButton selectButton = new JButton("Select Project");
		 selectButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 label.setText("View Project \"" + cb.getItemAt(cb.getSelectedIndex()) + "\"'s logged dates and hours.");
				 projName.setText(cb.getItemAt(cb.getSelectedIndex()));
			 }
		 });
				
		 
		 JButton confirmProj = new JButton("Confirm Project");
		 confirmProj.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				try {
				 ServiceProject tempProj = null;
				 for (int loop = 0; loop < student.getServiceProjectsArray().size(); loop++)
				 {
					 if (student.getSpecificServiceProject(loop).getName().equals(projName.getText()))
						 tempProj = student.getSpecificServiceProject(loop);
				 }
				 
				 
				 JOptionPane.showMessageDialog(frame, "Opening " + tempProj.getName() + "'s logged dates and hours");
				 editHoursGUI(student, tempProj);
				 frame.dispose();
				}
				catch (Exception e1)
 	    		{
					JOptionPane.showMessageDialog(frame, "There was an error. Please try again.");
					System.out.println("Exception occured: " + e1);
 	    		}
			 }
		 });
		 
		 JPanel conclusion = new JPanel();
		 conclusion.setAlignmentX(Component.LEFT_ALIGNMENT);
		 
		 
		 /** return button **********************************************************************/
		 JButton returnButton = new JButton("Cancel Hour Edit");
		 returnButton.setBounds(50,100,95,30);
		    
			returnButton.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e)
		    	{
		    		buildIndivServiceProfileGUI(student);
		    		frame.dispose();
		    	}
			});
		 
		 
		 /** finalizations ****************************************************************************************/
		projectSelectorPanel.add(cb);
		projectSelectorPanel.add(selectButton);
			 
		conclusion.add(label);
		conclusion.add(confirmProj);
		
		mainPanel.add(returnButton);
		mainPanel.add(intro);
		mainPanel.add(projectSelectorPanel);
		mainPanel.add(conclusion);
		 mainPanel.setBackground(Color.white);

		
		
		 frame.add(mainPanel);
		 frame.setSize(600,400);  
		 frame.setLocationRelativeTo(null);  
		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		 frame.setVisible(true);
	 }
	 
	
	 
	 public static void editHoursGUI(Student student, ServiceProject sp)
	 {
		 JFrame frame = new JFrame("Service Hour Selection");
		 JPanel mainPanel = new JPanel();
		 mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		 // please select service project
		 
		 
		 /** service project selector **********************************************************************/
		 JPanel hourSelectorPanel = new JPanel();
		 hourSelectorPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		 String[] hourDates = new String[sp.getServiceHourArray().size()];
		 for (int i = 0; i < sp.getServiceHourArray().size(); i++)
			 hourDates[i] = sp.getSpecificServiceHour(i).getMyDate();
		 
		 JComboBox<String> cb = new JComboBox<String>(hourDates);
		 cb.setBounds(50, 50, 90, 20);
		
		 JLabel label = new JLabel("No date is currently selected.");
		 JLabel selectedDateName = new JLabel();
		 JButton selectButton = new JButton("Select date");
		 selectButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 label.setText("Editing \"" + cb.getItemAt(cb.getSelectedIndex()) + "\" information.");
				 selectedDateName.setText(cb.getItemAt(cb.getSelectedIndex()));
			 }
		 });
		 
		 ServiceHour hour3 = null;
		 for (int loop = 0; loop < sp.getServiceHourArray().size(); loop++)
		 {
			 if (sp.getSpecificServiceHour(loop).getMyDate().equals(selectedDateName.getText()))
				 hour3 = sp.getSpecificServiceHour(loop);
		 }
		 
		 /** fieldPanel **************************************************************************************/
		 JPanel fieldPanel = new JPanel();
			fieldPanel.setLayout(new GridLayout(4,1));
				
			JTextField dateField, hourField;
			
			/** date ****************************************************/	     	
	     	JLabel dateLabel = new JLabel();
	     	dateLabel.setText("New Date: (rewrite even if not changing)");
	     
	     	dateField = new JTextField();  
	     	if (hour3 != null)
	     		dateField.setText(hour3.getMyDate());
	     	
	     	/** hour ****************************************************/   	
	     	JLabel hourLabel = new JLabel();
	     	hourLabel.setText("New Hour: (rewrite even if not changing)");
	     	hourField = new JTextField();  
	     	if (hour3 != null)
	     		hourField.setText(""+hour3.getMyHour());
	     	
	     	
	     	/** submit *****************************************************/
	     	JButton submit = new JButton("Submit");
	     	submit.addActionListener(new ActionListener() {
	     		public void actionPerformed(ActionEvent e) {
	     			try {
	     		 	// convert to usable data
	     			String date2 = dateField.getText();
	     			String hour2 = hourField.getText();
	     			
	     			if ((date2 == null || date2.isEmpty()) || (hour2 == null || hour2.isEmpty()) )
	     			    throw new RuntimeException("Empty String invalid.");
	     			// print service project selected
	    	     	//System.out.println(date);
	    	     	//System.out.println(hour);
	    	     	
	     			// edit student service projects array
	     			 ServiceHour hour4 = null;
	     			 for (int loop = 0; loop < sp.getServiceHourArray().size(); loop++)
	     			 {
	     				if (sp.getSpecificServiceHour(loop).getMyDate().equals(selectedDateName.getText()))
	   	   				 hour4 = sp.getSpecificServiceHour(loop);
	     			 }
	     			 
	     
	    	     	hour4.setMyDate(date2);
	    	     	hour4.setMyHour(Double.parseDouble(hour2));
	    	     	//System.out.println(student.printProjectArray());
	    	     	alpharetta.updateEntireDatabase(folderName, fileName, serviceFileName);
	    	     	
	    	     	
	    	     	JOptionPane.showMessageDialog(frame, "Date \"" + hour4.getMyDate() + "\" Succesfully Edited");
					buildIndivServiceProfileGUI(student);

	    	     	frame.dispose();
	     			}
	     			catch (Exception e1)
	 	    		{
						JOptionPane.showMessageDialog(frame, "There was an error. Please try again.");
						System.out.println("Exception occured: " + e1);
	 	    		}
	     			
	     		}
	     	});
	     	
	     	 JPanel end = new JPanel();
		     end.setLayout(new BoxLayout(end, BoxLayout.X_AXIS));
		     
		 	JButton returnButton = new JButton("Cancel");
			 returnButton.setBounds(50,100,95,30);
			    
				returnButton.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent e)
			    	{
			    		buildIndivServiceProfileGUI(student);
			    		frame.dispose();
			    	}
				});
		     
	     	
	     
	     /** finalizations ********************************************************************************************/
	    end.add(submit);
	    end.add(returnButton);
				
		hourSelectorPanel.add(cb);
	     hourSelectorPanel.add(selectButton);
	     hourSelectorPanel.add(label);
	     
	     fieldPanel.add(dateLabel);
	     fieldPanel.add(dateField);
	     
	     fieldPanel.add(hourLabel);
	     fieldPanel.add(hourField);
		 
		 mainPanel.add(hourSelectorPanel);
		 mainPanel.add(fieldPanel);
		 mainPanel.add(end);
		 mainPanel.setBackground(Color.white);

		 
		 frame.add(mainPanel);
		 frame.setSize(600,400);  
		 frame.setLocationRelativeTo(null);  
		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		 frame.setVisible(true);
	 }
	 
	 
	 
	 public static void editProject(Student student)
	 {
		 JFrame frame = new JFrame("Editing Service Project");
		 JPanel mainPanel = new JPanel();
		 mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		 // please select service project
		 
		 /** service project selector **********************************************************************/
		 JPanel projectSelectorPanel = new JPanel();
		 //studentSelectorPanel.setLayout(new BoxLayout(studentSelectorPanel, BoxLayout.Y_AXIS));
		 projectSelectorPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		 String[] projNames = new String[student.getServiceProjectsArray().size()];
		 for (int i = 0; i < student.getServiceProjectsArray().size(); i++)
			 projNames[i] = student.getSpecificServiceProject(i).getName();
		 
		 JComboBox<String> cb = new JComboBox<String>(projNames);
		 cb.setBounds(50, 50, 90, 20);
		
		 JLabel label = new JLabel("No project is currently selected.");
		 JLabel selectedProjectName = new JLabel();
		 JButton selectButton = new JButton("Select Project");
		 selectButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 label.setText("Editing \"" + cb.getItemAt(cb.getSelectedIndex()) + "\" service project.");
				 selectedProjectName.setText(cb.getItemAt(cb.getSelectedIndex()));
			 }
		 });
		 
		 ServiceProject project = null;
		 for (int loop = 0; loop < student.getServiceProjectsArray().size(); loop++)
		 {
			 if (student.getSpecificServiceProject(loop).getName().equals(selectedProjectName.getText()))
				 project = student.getSpecificServiceProject(loop);
		 }
		 
		 /** fieldPanel **************************************************************************************/
		 JPanel fieldPanel = new JPanel();
			fieldPanel.setLayout(new GridLayout(4,1));
				
			JTextField nameField, descripField;
				
	     	
	     	JLabel nameLabel = new JLabel();
	     	nameLabel.setText("New Name: ");
	     	nameField = new JTextField();  
	     	if (project != null)
	     		nameField.setText(project.getName());
	     	
	     	JLabel descripLabel = new JLabel();
	     	descripLabel.setText("New Description: ");
	     	descripField = new JTextField();  
	     	if (project != null)
	     		nameField.setText(project.getDescription());
	     	
	     	JButton submit = new JButton("Submit");
	     	submit.addActionListener(new ActionListener() {
	     		public void actionPerformed(ActionEvent e) {
	     			try {
	     		 	// convert to usable data
	     			String name = nameField.getText();
	     			String description = descripField.getText();
	     			
	     			if ((name == null || name.isEmpty()) || (description == null || description.isEmpty()) )
	     			    throw new RuntimeException("Empty String invalid.");
	     			// print service project selected
	    	     	//System.out.println(date);
	    	     	//System.out.println(hour);
	    	     	
	     			// edit student service projects array
	     			 ServiceProject project = null;
	     			 for (int loop = 0; loop < student.getServiceProjectsArray().size(); loop++)
	     			 {
	     				 if (student.getSpecificServiceProject(loop).getName().equals(selectedProjectName.getText()))
	     					 project = student.getSpecificServiceProject(loop);
	     			 }
	     			
	    	     	project.setName(name);
	    	     	project.setDescription(description);
	    	     	//System.out.println(student.printProjectArray());
	    	     	alpharetta.updateEntireDatabase(folderName, fileName, serviceFileName);
	    	     	
	    	     	
	    	     	JOptionPane.showMessageDialog(frame, "Project Succesfully Edited with New Name: " + project.getName());
					buildIndivServiceProfileGUI(student);

	    	     	frame.dispose();
	     			}
	     			catch (Exception e1)
	 	    		{
						JOptionPane.showMessageDialog(frame, "There was an error. Please try again.");
						System.out.println("Exception occured: " + e1);
	 	    		}
	     			
	     		}
	     	});
	     	
	     	 JPanel end = new JPanel();
		     end.setLayout(new BoxLayout(end, BoxLayout.X_AXIS));
		     
		 	JButton returnButton = new JButton("Cancel");
			 returnButton.setBounds(50,100,95,30);
			    
				returnButton.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent e)
			    	{
			    		buildIndivServiceProfileGUI(student);
			    		frame.dispose();
			    	}
				});
		     
	     	
	     
	     /** finalizations ********************************************************************************************/
	    end.add(submit);
	    end.add(returnButton);
				
		projectSelectorPanel.add(cb);
	     projectSelectorPanel.add(selectButton);
	     projectSelectorPanel.add(label);
	     
	     fieldPanel.add(nameLabel);
	     fieldPanel.add(nameField);
	     fieldPanel.add(descripLabel);
	     fieldPanel.add(descripField);
		 
		 mainPanel.add(projectSelectorPanel);
		 mainPanel.add(fieldPanel);
		 mainPanel.add(end);
		 
		 mainPanel.setBackground(Color.white);

		 frame.add(mainPanel);
		 frame.setSize(600,400);  
		 frame.setLocationRelativeTo(null);  
		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		 frame.setVisible(true);
	 
	 }
	 
	 
	 public static void deleteHoursPopup(Student student)
	 {		 
		 
		 JFrame frame = new JFrame("Project Selection for Hour Deletion");
		 JPanel mainPanel = new JPanel();
		 mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		 
		 JLabel intro = new JLabel("Please select a project from the dropdown to view corresponding dates and hours for deletion.");
		    intro.setFont(new javax.swing.plaf.FontUIResource("Segoe UI Semibold",Font.PLAIN,13));

		 
		 /** project selector ****************************************************************************/
		 JPanel projectSelectorPanel = new JPanel();
		 //projectSelectorPanel.setLayout(new BoxLayout(studentSelectorPanel, BoxLayout.Y_AXIS));
		 projectSelectorPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		 String[] names = new String[student.getServiceProjectsArray().size()];
		 for (int i = 0; i < student.getServiceProjectsArray().size(); i++)
			 names[i] = student.getSpecificServiceProject(i).getName();

		 JComboBox<String> cb = new JComboBox<String>(names);
		 cb.setBounds(50, 50, 90, 20);
		
		 JLabel label = new JLabel("No project is currently selected.");
		 JLabel projName = new JLabel();
		 JButton selectButton = new JButton("Select Project");
		 selectButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 label.setText("View Project \"" + cb.getItemAt(cb.getSelectedIndex()) + "\"'s logged dates and hours.");
				 projName.setText(cb.getItemAt(cb.getSelectedIndex()));
			 }
		 });
				
		 
		 JButton confirmProj = new JButton("Confirm Project");
		 confirmProj.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				try {
				 ServiceProject tempProj = null;
				 for (int loop = 0; loop < student.getServiceProjectsArray().size(); loop++)
				 {
					 if (student.getSpecificServiceProject(loop).getName().equals(projName.getText()))
						 tempProj = student.getSpecificServiceProject(loop);
				 }
				 
				 
				 JOptionPane.showMessageDialog(frame, "Opening " + tempProj.getName() + "'s logged dates and hours");
				 deleteHours(tempProj, student);
				 frame.dispose();
				}
				catch (Exception e1)
 	    		{
					JOptionPane.showMessageDialog(frame, "There was an error. Please try again.");
					System.out.println("Exception occured: " + e1);
 	    		}
			 }
		 });
		 
		 JPanel conclusion = new JPanel();
		 conclusion.setAlignmentX(Component.LEFT_ALIGNMENT);
		 
		 
		 /** return button **********************************************************************/
		 JButton returnButton = new JButton("Cancel Hour Deletion");
		 returnButton.setBounds(50,100,95,30);
		    
			returnButton.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e)
		    	{
		    		buildIndivServiceProfileGUI(student);
		    		frame.dispose();
		    	}
			});
		 
		 
		 /** finalizations ****************************************************************************************/
		projectSelectorPanel.add(cb);
		projectSelectorPanel.add(selectButton);
			 
		conclusion.add(label);
		conclusion.add(confirmProj);
		
		mainPanel.add(returnButton);
		mainPanel.add(intro);
		mainPanel.add(projectSelectorPanel);
		mainPanel.add(conclusion);
		 mainPanel.setBackground(Color.white);

		
		
		 frame.add(mainPanel);
		 frame.setSize(600,400);  
		 frame.setLocationRelativeTo(null);  
		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		 frame.setVisible(true);
	 }

	 
	 public static void deleteHours(ServiceProject sp, Student student)
	 {
		 JFrame frame = new JFrame(sp.getName() + "'s Dates and Hours for Deletion");
		 JPanel mainPanel = new JPanel();
		 mainPanel.setLayout(new GridLayout(3,1)); //change to gridLayout

		 JPanel intro = new JPanel();
		 JLabel title = new JLabel(sp.getName());
		 JLabel explanation = new JLabel(sp.getDescription());
		 
		 JLabel directions = new JLabel("Select the following service hours for deletion.");
		 
		 JPanel hourPanel = new JPanel();
		 hourPanel.setLayout(new BoxLayout(hourPanel, BoxLayout.Y_AXIS));
		 hourPanel.add(directions);
		 for (int numHour = 0; numHour < sp.getServiceHourArray().size(); numHour++)
		 {
			 JCheckBox dateAndHour = new JCheckBox(sp.getSpecificServiceHour(numHour).toString());
			 dateAndHour.setBounds(100, 100, 150, 100);
			 dateAndHour.setName(sp.getSpecificServiceHour(numHour).toString());
			 hourPanel.add(dateAndHour);
		 }
		 
		 
		 JPanel conclusion = new JPanel();
		 conclusion.setLayout(new BoxLayout(conclusion, BoxLayout.X_AXIS));
		 
		 JButton returnButton = new JButton("Cancel Hour Deletion");
		 returnButton.setBounds(50,100,95,30);
		    
			returnButton.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e)
		    	{
		    		buildIndivServiceProfileGUI(student);
		    		frame.dispose();
		    	}
			});
		 
		 
		 ArrayList<String> hoursToDelete = new ArrayList<String>();
		 JButton deleteHours = new JButton("Delete Selected Hours");
		 deleteHours.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 for (int date = 1; date < hourPanel.getComponentCount(); date++)
				 {
					 if (((AbstractButton) hourPanel.getComponent(date)).isSelected()) {
							hoursToDelete.add(hourPanel.getComponent(date).getName());
							System.out.println(hourPanel.getComponent(date).getName());
						}
				 }
				 
				 for (int j = 0; j < hoursToDelete.size(); j++)
				 {
					 for (int k = 0; k < sp.getServiceHourArray().size(); k++)
					 {
						 if (hoursToDelete.get(j).equals(sp.getSpecificServiceHour(k).toString()))
						 {
							 sp.deleteSpecificServiceHour(k);
							 k--;
							 System.out.println(hoursToDelete.get(j) + " removed");
						 }
					 }
				 }
					
					
					alpharetta.updateEntireDatabase(folderName, fileName, serviceFileName);
				 
					System.out.println("Database altered");
					
					
					String print = "The following dates' hours were successfully removed: \n";
					
					 for (int count = 0; count < hoursToDelete.size(); count++)
						 print+= hoursToDelete.get(count) + "\n";
					
					JOptionPane.showMessageDialog(frame, print);
					
					buildIndivServiceProfileGUI(student);

			     	frame.dispose();
			 }
		 });		 
		 
		 /** finalizations ********************************************************************************/
		 intro.add(title);
		 intro.add(explanation);
		 
		 conclusion.add(returnButton);
		 conclusion.add(deleteHours);
		 
		 mainPanel.add(intro);
		 mainPanel.add(hourPanel);
		 mainPanel.add(conclusion);
		 mainPanel.setBackground(Color.white);

		 
		 frame.add(mainPanel);
		 frame.setSize(600,400);  
		 frame.setLocationRelativeTo(null);  
		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		 frame.setVisible(true);	
	 }
	 
	 
	 
	 public static void deleteProjectGUI(Student student)
	 {
		 JFrame frame = new JFrame("Project Selection for Hour Deletion");
		 JPanel mainPanel = new JPanel();
		 mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		 
		 JLabel intro = new JLabel("Please select a project from the dropdown for deletion.");
		    intro.setFont(new javax.swing.plaf.FontUIResource("Segoe UI Semibold",Font.PLAIN,16));

		 
		 
		 /** project selector ****************************************************************************/
		 JPanel projectSelectorPanel = new JPanel();
		 //projectSelectorPanel.setLayout(new BoxLayout(studentSelectorPanel, BoxLayout.Y_AXIS));
		 projectSelectorPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		 String[] names = new String[student.getServiceProjectsArray().size()];
		 for (int i = 0; i < student.getServiceProjectsArray().size(); i++)
			 names[i] = student.getSpecificServiceProject(i).getName();

		 JComboBox<String> cb = new JComboBox<String>(names);
		 cb.setBounds(50, 50, 90, 20);
		
		 JLabel label = new JLabel("No project is currently selected.");
		 JLabel projName = new JLabel();
		 JButton selectButton = new JButton("Select Project");
		 selectButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 label.setText("Delete Project \"" + cb.getItemAt(cb.getSelectedIndex()));
				 projName.setText(cb.getItemAt(cb.getSelectedIndex()));
			 }
		 });
				
		 
		 JButton confirmProj = new JButton("Confirm Project");
		 confirmProj.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				try {
				 ServiceProject tempProj = null;
				 for (int loop = 0; loop < student.getServiceProjectsArray().size(); loop++)
				 {
					 if (student.getSpecificServiceProject(loop).getName().equals(projName.getText()))
						 tempProj = student.getSpecificServiceProject(loop);
				 }
				 
				
				JOptionPane.showMessageDialog(frame, "Deleting " + tempProj.getName());
					 
	     			 for (int loop = 0; loop < student.getServiceProjectsArray().size(); loop++)
	     			 {
	     				 if (student.getSpecificServiceProject(loop).getName().equals(tempProj.getName())) {
	     					student.getServiceProjectsArray().remove(loop);
	    					alpharetta.updateEntireDatabase(folderName, fileName, serviceFileName);
	     				 }
	     				 	
	     			 }
		
				 buildIndivServiceProfileGUI(student);
				 frame.dispose();
			 }
				catch (Exception e1)
 	    		{
					JOptionPane.showMessageDialog(frame, "There was an error. Please try again.");
					System.out.println("Exception occured: " + e1);
 	    		}
			 }
		 });
		 
		 JPanel conclusion = new JPanel();
		 conclusion.setAlignmentX(Component.LEFT_ALIGNMENT);
		 
		 
		 /** return button **********************************************************************/
		 JButton returnButton = new JButton("Cancel Hour Deletion");
		 returnButton.setBounds(50,100,95,30);
		    
			returnButton.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e)
		    	{
		    		buildIndivServiceProfileGUI(student);
		    		frame.dispose();
		    	}
			});
		 
		 
		 /** finalizations ****************************************************************************************/
		projectSelectorPanel.add(cb);
		projectSelectorPanel.add(selectButton);
			 
		conclusion.add(label);
		conclusion.add(confirmProj);
		
		mainPanel.add(returnButton);
		mainPanel.add(intro);
		mainPanel.add(projectSelectorPanel);
		mainPanel.add(conclusion);
		
		 mainPanel.setBackground(Color.white);

		
		 frame.add(mainPanel);
		 frame.setSize(600,400);  
		 frame.setLocationRelativeTo(null);  
		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		 frame.setVisible(true);
	 }
	 
	 public static void restorePreviousInfo()
	 {
		 JFrame frame = new JFrame("Previous Information Restoration");
		 JPanel mainPanel = new JPanel();
		 mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		 
		 JScrollPane sp =  new JScrollPane(mainPanel);
		 sp.getVerticalScrollBar().setUnitIncrement(5);

		 
		 /** intro panel **************************************************************************************/
		 JPanel introPanel = new JPanel();
		 introPanel.setLayout(new BoxLayout(introPanel, BoxLayout.Y_AXIS));
		 
		 JLabel direction = new JLabel("Please select a date or time to return to. (sorted oldest to newest)");
		    direction.setFont(new javax.swing.plaf.FontUIResource("Segoe UI Semibold",Font.PLAIN,16));

		 
		 /** timeList / file selection *************************************************************************************/
		 // make scrollable panel!!
		 
		 
		 JPanel timeList = new JPanel();
		 timeList.setLayout(new BoxLayout(timeList, BoxLayout.Y_AXIS));
		 
		 //File file = new File("C:/Users/1100299029/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020/Previous Database Versions/");
		 File file = new File(folderName + "/Previous Database Versions/");

	     // Reading directory contents & converting to user-friendly names
	     File[] files = file.listFiles();
	     String[] folderNames = new String[files.length];
	     
	     // gets file name, specifically date and time
	     for (int i = 0; i < files.length; i++) {
	         //System.out.println(files[i]);
	         folderNames[i] = files[i].getName();
	         //System.out.println(folderNames[i]);
	     }
	     
	     // creates radio buttons for each date/time
	     for (int time = 0; time < folderNames.length; time++)
	     {
	    	 JRadioButton tempTime = new JRadioButton(folderNames[time]);
	    	 timeList.add(tempTime);
	    	 tempTime.setName(tempTime.getText());
	     }
	     
	     /** conclusion panel *****************************************************************/
	     JPanel conclusion = new JPanel();
		 conclusion.setLayout(new BoxLayout(conclusion, BoxLayout.Y_AXIS));
		 
	     JButton selectDate = new JButton("Revert to Selected Date");
	     selectDate.setBounds(50,100,95,30);
	     selectDate.addActionListener(new ActionListener() {
	    	 public void actionPerformed(ActionEvent e)
	    	 {
	    		 String revertDate = "";
	    	     int revertDateIndex = 0;
	    	     ArrayList<Integer> indexes = new ArrayList<Integer>();
	    	     
	    		 try {
	    		     // creating list of selected dates to revert to
	    		     ArrayList<String> timeToRevertTo = new ArrayList<String>();
	    		     for (int date = 0; date < timeList.getComponentCount(); date++)
	    				{
	    					if (((AbstractButton) timeList.getComponent(date)).isSelected()) {
	    						timeToRevertTo.add(timeList.getComponent(date).getName());
	    						indexes.add(date);
	    						//System.out.println(timeList.getComponent(date).getName());
	    					}
	    				}
	    		     
	    		     
	    		     // will only take first date selected in list
	    		     for (int j = 0; j < timeToRevertTo.size(); j++)
	    			 {
	    				 for (int k = 0; k < folderNames.length; k++)
	    				 {
	    					 if (timeToRevertTo.get(j).equals(folderNames[k]))
	    					 {
	    						 revertDate = timeToRevertTo.get(j);
	    						 revertDateIndex = indexes.get(0);
	    						 k = folderNames.length - 1;
	    						 j = timeToRevertTo.size() - 1;
	    						 //System.out.println("Reverting database to " + timeToRevertTo.get(j));
	    					 }
	    				 }
	    			 }
	    		     
	    		    //find file path name
	    			String revertName = ""+ files[revertDateIndex];
	    			//System.out.println(revertName);
	    			
	    			// change files
	    			alpharetta.returnToPastDate(folderName, fileName, serviceFileName, revertName);
	    			 
	    				System.out.println("Reverted to " + revertDate);
	    				
	    				JOptionPane.showMessageDialog(frame, "Database reverted to " + revertDate);
	    				buildMainPageGUI();
	    				frame.dispose();
	    		     }
	    		 catch (Exception e1)
		     		{ 
						JOptionPane.showMessageDialog(frame, "There was an error. Please try again.");
		     			System.out.println("Exception Occurred" + e1); 
		     		}  		 
	    	 }
	     });
	     
	     
	     
	     JButton returnButton = new JButton("Cancel Date Reversion");
		 returnButton.setBounds(50,100,95,30);
		    
			returnButton.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e)
		    	{
		    		buildMainPageGUI();
		    		frame.dispose();
		    	}
			});

		 
		 
		 
		 /** finalizations ****************************************************************************************/
		introPanel.add(returnButton); 
		introPanel.add(direction);
		 conclusion.add(selectDate);
	     
	     mainPanel.add(introPanel);
		 mainPanel.add(timeList);
		 mainPanel.add(conclusion);
		 mainPanel.setBackground(Color.white);

		 
		 frame.add(sp);
		 frame.setSize(600,400);  
		 frame.setLocationRelativeTo(null);  
		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		 frame.setVisible(true);
	 }
	 
	
	 public static void buildAwardSelection()
	 {
		 JFrame frame = new JFrame("Award Report Selection");
		 JPanel mainPanel = new JPanel();
		 mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		 
		 JPanel introPanel = new JPanel();
		 introPanel.setLayout(new BoxLayout(introPanel, BoxLayout.Y_AXIS));
		 introPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		 
		 String html = "<html><body style = 'width: %1spx'>%1s";
		 
		 JLabel directions = new JLabel(String.format(html, 400, "Please select which CSA Award Level's report you wish to generate."));
		 JLabel direct2 = new JLabel(String.format(html, 400, "We suggest opening your information as a Notepad file or through your default internet browser (Chrome, Internet Explorer, etc)"));
		 
		 JPanel buttonPanel = new JPanel();
		 buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		 buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		 JButton communityLevel = new JButton("Community Level");
		 communityLevel.setBounds(50,100,95,30);
		 communityLevel.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e)
		    	{
		    		alpharetta.createCommunityLevelReport(folderName);
		    	}
			});
		 
		 JButton serviceLevel = new JButton("Service Level");
		 serviceLevel.setBounds(50,100,95,30);
		 serviceLevel.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e)
		    	{
		    		alpharetta.createServiceLevelReport(folderName);
		    	}
			});
		 
		 JButton achievementLevel = new JButton("Achievement Level");
		 achievementLevel.setBounds(50,100,95,30);
		 achievementLevel.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e)
		    	{
		    		alpharetta.createAchievementLevelReport(folderName);
		    	}
			});
		 
		 
		 JButton returnButton = new JButton("Cancel Award Report Print");
		 returnButton.setBounds(50,100,95,30);
		    
			returnButton.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e)
		    	{
		    		buildMainPageGUI();
		    		frame.dispose();
		    	}
			});
		 
		 
		 /** finalizations ****************************************************************************************/
		 buttonPanel.add(communityLevel);
		 buttonPanel.add(serviceLevel);
		 buttonPanel.add(achievementLevel);
		 
		 introPanel.add(directions);
		 introPanel.add(direct2);
		 
		 mainPanel.add(introPanel);
		 mainPanel.add(buttonPanel);
		 mainPanel.add(returnButton);

		 mainPanel.setBackground(Color.white);

		 
		 frame.add(mainPanel);
		 frame.setSize(600,400);  
		 frame.setLocationRelativeTo(null);  
		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		 frame.setVisible(true);
	 }
	 
	 public static void faqGUI()
	 {
		 JFrame frame = new JFrame("Frequently Asked Questions (FAQ)");
		 JPanel mainPanel = new JPanel();
		 mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		 
		 JScrollPane sp =  new JScrollPane(mainPanel);
		 sp.getVerticalScrollBar().setUnitIncrement(5);
		 sp.setMaximumSize(new Dimension(800,600));
		 sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		 
		 JLabel intro = new JLabel("Frequently Asked Questions");
		 intro.setAlignmentX(BoxLayout.X_AXIS);
		 
		 /** questions-answer panel ****************************************************************/
		 JPanel QA = new JPanel();
		 QA.setLayout(new BoxLayout(QA, BoxLayout.Y_AXIS));
		 
		 String html = "<html><body style = 'width: %1spx'>%1s";
		 		 
		 // section 1
		 // correct way of adding student
		 JPanel section1 = new JPanel();
		 section1.setLayout(new BoxLayout(section1, BoxLayout.Y_AXIS));
		 section1.setAlignmentX(Component.LEFT_ALIGNMENT);
		 
		 JTextArea ta1 = new JTextArea(2, 20);
		 ta1.setText("Q: How you I correctly add a new student to my school roster?\n"
		 		+ "A: There are two ways of adding or registering a new student:\n"
		 		+ "   1) From the Welcome Page (the first page upon opening the application), press \"Register New Student\".\n"
		 		+ "   2) From the Main page (the page that opens after pressing \"View School Roster\" in the Welcome Page), press \"Add Student\"");
		 
		 ta1.setWrapStyleWord(true);
		 ta1.setLineWrap(true);
		 ta1.setOpaque(false);
		 ta1.setEditable(false);
		 ta1.setFocusable(false);
		 ta1.setBackground(UIManager.getColor("Label.background"));
		 ta1.setFont(UIManager.getFont("Label.font"));
		 ta1.setBorder(UIManager.getBorder("Label.border"));
		    
		 section1.add(ta1);
		 
		 section1.add(new JLabel(" "));
		 section1.add(new JLabel(" "));
		 
		 // section 2
		 // must assign number?
		 JPanel section2 = new JPanel();
		 section2.setLayout(new BoxLayout(section2, BoxLayout.Y_AXIS));
		 section2.setAlignmentX(Component.LEFT_ALIGNMENT);
		 
		 section2.add(new JLabel("Q: Do I have to give my student a student ID?\n"));
		 section2.add(new JLabel("A: Yes, all students must have a student ID in the system. The ID's first four digits are typically \"1100\"."));
		 section2.add(new JLabel("All information shown in the Main Page table (Student ID, Name, Grade) must be input when registering a new student."));
		 
		 section2.add(new JLabel(" "));
		 section2.add(new JLabel(" "));
		 
		 // section 3
		 // update/change entry
		 JPanel section3 = new JPanel();
		 section3.setLayout(new BoxLayout(section3, BoxLayout.Y_AXIS));
		 section3.setAlignmentX(Component.LEFT_ALIGNMENT);
		 
		 section3.add(new JLabel("Q: I entered my student's information (ID, name, or grade) incorrectly when registering them. How do I fix this?"));
		 section3.add(new JLabel("A: To fix a student's information or view their service information, simply press their name in the Main page table, prompting a "));
		 section3.add(new JLabel("   popup to appear. There is a button for editing student information"));
		
		 section3.add(new JLabel(" "));
		 section3.add(new JLabel(" "));
		 
		 // section 4
		 // deleting student
		 JPanel section4 = new JPanel();
		 section4.setLayout(new BoxLayout(section4, BoxLayout.Y_AXIS));
		 section4.setAlignmentX(Component.LEFT_ALIGNMENT);
		 
		 section4.add(new JLabel(String.format(html, 600, "Q: I need to delete a student from my roster. How do I do this?")));
		 section4.add(new JLabel("A: There are two ways of deleting students from a school's roster:"));
		 section4.add(new JLabel("   1) If it is the start of a new school year and you are wanting to delete the recently graduated class, follow these steps:"));
		 section4.add(new JLabel("     a. go to the Main Page (page with table roster)"));
		 section4.add(new JLabel("     b. press \"Delete Student\""));
		 section4.add(new JLabel("     c. A popup should appear with every student from the roster sorted by grade. Above the student names is a button called "));
	 		section4.add(new JLabel("     \"Adjust Roster\". Press this."));
		 section4.add(new JLabel("   2) If you want to delete an individual student or students at once but NOT deleting recent graduates, follow these steps:"));
		 section4.add(new JLabel("     a. go to the Main Page (page with table roster"));
		 section4.add(new JLabel("     b. press \"Delete Student\""));
		 section4.add(new JLabel("     c. A popup should appear with every student from the roster sorted by grade. Select the circle next to each student you want deleted."));
		 section4.add(new JLabel("     d. Press the \"Submit\" button at the bottom of the page."));
		 
		 section4.add(new JLabel(" "));
		 section4.add(new JLabel(" "));
		 
		 // section 5
		 // reports
		 JPanel section5 = new JPanel();
		 section5.setLayout(new BoxLayout(section5, BoxLayout.Y_AXIS));
		 section5.setAlignmentX(Component.LEFT_ALIGNMENT);
		 
		 section5.add(new JLabel("Q: How do I print a report of student eligible for the Community/Service/Achievement Award?"));
		 section5.add(new JLabel("A: Award reports can be printed from the Main page by pressing \"Print Award Recipient List\" below the table roster."));
		 
		 section5.add(new JLabel(" "));
		 section5.add(new JLabel(" "));
		 
		 // section 6
		 // reports
		 JPanel section6 = new JPanel();
		 section6.setLayout(new BoxLayout(section6, BoxLayout.Y_AXIS));
		 section6.setAlignmentX(Component.LEFT_ALIGNMENT);
		 
		 section6.add(new JLabel("Q: How do I print a report of all my students and their total hours?"));
		 section6.add(new JLabel("A: A report with students and their total hours can be printed from the Main page by pressing \"Print Student Hour Report\""));
		 		section6.add(new JLabel(" below the table roster."));
		 
		 section6.add(new JLabel(" "));
		 section6.add(new JLabel(" "));
		 
		// section 7
		// reverting information
		JPanel section7 = new JPanel();
		section7.setLayout(new BoxLayout(section7, BoxLayout.Y_AXIS));
		section7.setAlignmentX(Component.LEFT_ALIGNMENT);
			 
		section7.add(new JLabel("Q: I made a mistake while inputting student/service information. Is there a way I can undo changes?"));
		section7.add(new JLabel("A: There are several ways mistakes can be undone."));
		section7.add(new JLabel("   1) Editing information."));
		section7.add(new JLabel("     a. edit Student information: see Question 3 above"));
		section7.add(new JLabel("     b. edit Service Project information: From the student's Service Profile (see Question 3 for how to get there), press "));
		section7.add(new JLabel("       \"Edit Existing Project\" and then \"Change Name/Description"));
		section7.add(new JLabel("     c. edit Service Project Date/Hour: follow the steps above in 1b. Instead of pressing \"Change Name/Description\", press \"Edit Hour(s)\""));
		section7.add(new JLabel("   2) Deleting information."));
		section7.add(new JLabel("     a. delete Student: see Question 4 above"));
		section7.add(new JLabel("     b. delete Service Project: From the student's Service Profile (see Question 3 for how to get there), press \"Delete Project\""));
		section7.add(new JLabel("     c. delete Service Project Date/Hour: From the student's Service Profile (see Question 3 for how to get there), press "));
		section7.add(new JLabel("       \"Edit Existing Project\" and then \"Delete Hour(s)\""));
		section7.add(new JLabel("   3) Reverting to a previous save file."));
		section7.add(new JLabel("     a. see Question 8 below"));

		section7.add(new JLabel(" "));
		 section7.add(new JLabel(" "));
		
		// section 8
		JPanel section8 = new JPanel();
		section8.setLayout(new BoxLayout(section8, BoxLayout.Y_AXIS));
		section8.setAlignmentX(Component.LEFT_ALIGNMENT);
			 
		section8.add(new JLabel("Q: How do I revert to a previous save file?"));
		section8.add(new JLabel("A: From the Main page, press the \"Restore Previous Information\" button. Then choose a date to revert to."));
		section8.add(new JLabel("A: *Note: if more than one date is selected, the system will revert to the first one selected from the list."));
		
		section8.add(new JLabel(" "));
		 section8.add(new JLabel(" "));
		 
		// section 9
		JPanel section9 = new JPanel();
		section9.setLayout(new BoxLayout(section9, BoxLayout.Y_AXIS));
		section9.setAlignmentX(Component.LEFT_ALIGNMENT);
			 
		section9.add(new JLabel("Q: How do I see a student's service information?"));
		section9.add(new JLabel("A: Simply press their name in the Main page table, prompting a popup to appear. Press the button saying \"View Service Hours "));
		section9.add(new JLabel("and Projects\""));
		 
		section9.add(new JLabel(" "));
		 section9.add(new JLabel(" "));
		 
		 /** finalizations ****************************************************************************************/
		 QA.add(section1);
		 QA.add(section2);
		 QA.add(section3);
		 QA.add(section4);
		 QA.add(section5);
		 QA.add(section6);
		 QA.add(section7);
		 QA.add(section8);
		 QA.add(section9);
		 
		 mainPanel.add(intro);
		 mainPanel.add(QA);
		 mainPanel.setBackground(Color.white);

		 
		 frame.add(sp);
		 frame.setSize(800,600);  
		 frame.setLocationRelativeTo(null);  
		 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		 frame.setVisible(true);
	 }
	 
	/*******************************************************************************************************/
	public static void main(String[] args) throws BadLocationException
	{
		CSA_AwardProgram.buildWelcomeGUI();
		
		 //"C:/Users/1100299029/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020/src/StudentDatabase.txt";
		 

		
		
	}

}
