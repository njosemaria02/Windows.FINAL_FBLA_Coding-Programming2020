import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.awt.Desktop;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

public class School_CSA {
	private ArrayList<Student> studentList;
	
	/** constructors *************************************/
	public School_CSA()
	{
		studentList = new ArrayList<Student>();
	}
	
	public School_CSA(ArrayList temp)
	{
		studentList = new ArrayList<Student>();

		for (int len = 0; len < temp.size(); len++)
			addStudent((Student) temp.get(len));
		
	}
	
	/** public methods *********************************************************************************************/
	// accessor methods
	public int getArrayLength()
	{
		return studentList.size();
	}
	
	public Student getRecentStudent()
	{
		return studentList.get(studentList.size() - 1);
	}
	
	public Student getSpecificStudent(int index)
	{
		return studentList.get(index);
	}
	
	//mutator methods
	public void addStudent(String firstName, String lastName, String ID, String grade, String totalServiceHours)
	{
		Integer id = Integer.parseInt(ID);
		Integer aGrade = Integer.parseInt(grade);
		Double serviceHours = Double.parseDouble(totalServiceHours);
		
		studentList.add(new Student(firstName, lastName, id, aGrade, serviceHours));
	}
	
	public void addStudent(String firstName, String lastName, String ID, String grade)
	{
		Integer id = Integer.parseInt(ID);
		Integer aGrade = Integer.parseInt(grade);
		
		studentList.add(new Student(firstName, lastName, id, aGrade));
	}
	
	public void addStudent(Student student)
	{
		studentList.add(student);
	}
	
	public boolean removeStudent(int index)
	{
		Student temp = studentList.get(index);
		return (studentList.remove(index) == temp);
	}

	public void updateStudentDatabase(String fn) /** used for testing purposes ; please ignore**/
	{
//		try 
//		{ 
//			// copy to other file
//		 	SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss");  
//		    Date date = new Date();  
//		 
//		    File f1 = new File(fn);
//		 	//Files.copy(f1.toPath(), new File("C:/Users/SYFGWC19/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020/StudentDatabaseLog/studentDataVersion" + (formatter.format(date))).toPath());
//		 	Files.copy(f1.toPath(), new File("C:/Users/1100299029/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020/StudentDatabaseLog/studentDataVersion" + (formatter.format(date))).toPath());
//		 	
//		 	// rewrite current file with updated information
//			BufferedWriter out2 = new BufferedWriter(new FileWriter(fn)); 
//			out2.write(this.toString()); 
//			out2.close(); 
//				
//			System.out.println("Student Database Update Successful and Backup Saved");
//		} 
//		catch (IOException e1) 
//		{ 
//			System.out.println("Exception Occurred" + e1); 
//		} 
	}
	
	public void updateServiceDatabase(String fn) /** used for testing purposes ; please ignore**/
	{		
//		// transfer current serviceDatabase to new text file in log folder
//		 try {
//			 	// copy to other file
//			 	SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss");  
//			    Date date = new Date();  
//			 
//			    File f1 = new File(fn);
//			 	//Files.copy(f1.toPath(), new File("C:/Users/SYFGWC19/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020/ServiceDatabaseLog/serviceDataVersion" + (formatter.format(date))).toPath());
//			 	Files.copy(f1.toPath(), new File("C:/Users/1100299029/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020/ServiceDatabaseLog/serviceDataVersion" + (formatter.format(date))).toPath());
//
//			 	// rewrite current file with updated information
//				BufferedWriter out2 = new BufferedWriter(new FileWriter(fn)); 
//				out2.write(this.printServiceDatabase()); 
//				out2.close(); 
//				
//				System.out.println("Service Database Update Successful and Backup Saved");
//
//	        } catch (IOException e) {
//				System.out.println("Exception Occurred" + e); 
//	        }
	}
	
	
	/** incorporates the antiquated updateStudentDatabase() and updateServiceDatabase() methods 
	 * to update both databases together. This is very efficient for the many changes that occur
	 * as student information and service information change. All it does is copy the text files to
	 * new ones, which are placed under "Previous Database Versions" with the updated  date and time 
	 * of editing. After finishing this, it appends any changes made before the method call to the current
	 * text files that have just been backed up and are ready for override.	 
	 * 
	 * *Note: The commented out code was used during local machine testing. Please disregard.
	 * 
	 * @param folder - This is root file path for the entire program
	 * @param studentFn - This is the studentDatabase text file
	 * @param serviceFn - This is the serviceDatabase text file
	 */
	public void updateEntireDatabase(String folder, String studentFn, String serviceFn) {
		// transfer current serviceDatabase to new text file in log folder
		for (int i = 0; i < studentList.size(); i++)
			studentList.get(i).calculateTotalHours();
		
		try {
			// getting timestamp for naming folder
			SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss");  
			Date date = new Date();  
			
			// creates folder for holding two databases
			//new File("C:/Users/SYFGWC19/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020/Previous Database Versions/" + (formatter.format(date))).mkdirs();
			//new File("C:/Users/1100299029/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020/Previous Database Versions/" + (formatter.format(date))).mkdirs();
			//new File("C:/Users/1100299029/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020/Previous Database Versions/" + (formatter.format(date))).mkdirs();
			new File(folder + "/Previous Database Versions/" + (formatter.format(date))).mkdirs();
			
			// -------
			
			// copying student database to folder in new file
			File studentFile = new File(studentFn);
			//Files.copy(studentFile.toPath(), new File("C:/Users/SYFGWC19/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020/Previous Database Versions/" + (formatter.format(date)) + "/studentDatabase").toPath());
//			Files.copy(studentFile.toPath(), new File("C:/Users/1100299029/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020/Previous Database Versions/" + (formatter.format(date)) + "/studentDatabase").toPath());
			Files.copy(studentFile.toPath(), new File(folder + "/Previous Database Versions/" + (formatter.format(date)) + "/studentDatabase").toPath());

			
			// rewrite current file with updated information
			BufferedWriter stuOut = new BufferedWriter(new FileWriter(studentFn)); 
			stuOut.write(this.toString()); 
			stuOut.close(); 
						
			System.out.println("Student Database Update Successful and Backup Saved");
			
			// -------
			
			// copying service database to folder in new file
			File serviceFile = new File(serviceFn);
			//Files.copy(serviceFile.toPath(), new File("C:/Users/SYFGWC19/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020//Previous Database Versions/" + (formatter.format(date)) + "/serviceDatabase").toPath());
			//Files.copy(serviceFile.toPath(), new File("C:/Users/1100299029/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020//Previous Database Versions/" + (formatter.format(date)) + "/serviceDatabase").toPath());
			Files.copy(serviceFile.toPath(), new File(folder + "/Previous Database Versions/" + (formatter.format(date)) + "/serviceDatabase").toPath());

			
			// rewrite current file with updated information
			BufferedWriter serveOut = new BufferedWriter(new FileWriter(serviceFn)); 
			serveOut.write(this.printServiceDatabase()); 
			serveOut.close(); 
						
			System.out.println("Service Database Update Successful and Backup Saved");
			
        } catch (IOException e) {
			System.out.println("Exception Occurred" + e); 
        }
	}
	
	/** This method is what allows the user to revert to a previous save file within the system. It reverts
	 * by simply taking the current student and service databases and overriding them with a previous save file 
	 * withing needing to create or delete any data. Nothing is deleted because prior to overriding, the current
	 * service and student files are saved and appended to the Previous Database Versions folder like any other
	 * change in the system.
	 * 
	 * 
	 * @param folder - This is root file path for the entire program
	 * @param studentFn - This is the studentDatabase text file
	 * @param serviceFn - This is the serviceDatabase text file
	 * @param oldFn- This is the date being reverted to.
	 */
	public void returnToPastDate(String folder, String studentFn, String serviceFn, String oldFn)
	{
		try {
		/** making copy of files before altering ***********************************************/
		// getting timestamp for naming folder
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss");  
		Date date = new Date();  
					
		// creates folder for holding two databases
		//new File("C:/Users/SYFGWC19/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020/Previous Database Versions/" + (formatter.format(date))).mkdirs();
		//new File("C:/Users/1100299029/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020/Previous Database Versions/" + (formatter.format(date))).mkdirs();
		new File(folder + "/Previous Database Versions/" + (formatter.format(date))).mkdirs();


		// -------
		
		// copying student database to folder in new file
		File studentFile = new File(studentFn);
		//Files.copy(studentFile.toPath(), new File("C:/Users/SYFGWC19/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020/Previous Database Versions/" + (formatter.format(date)) + "/studentDatabase").toPath());
		//Files.copy(studentFile.toPath(), new File("C:/Users/1100299029/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020/Previous Database Versions/" + (formatter.format(date)) + "/studentDatabase").toPath());
		Files.copy(studentFile.toPath(), new File(folder + "/Previous Database Versions/" + (formatter.format(date)) + "/studentDatabase").toPath());
	
		
		// copying service database to folder in new file
		File serviceFile = new File(serviceFn);
		//Files.copy(serviceFile.toPath(), new File("C:/Users/SYFGWC19/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020//Previous Database Versions/" + (formatter.format(date)) + "/serviceDatabase").toPath());
		//Files.copy(serviceFile.toPath(), new File("C:/Users/1100299029/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020/Previous Database Versions/" + (formatter.format(date)) + "/serviceDatabase").toPath());
		Files.copy(serviceFile.toPath(), new File(folder + "/Previous Database Versions/" + (formatter.format(date)) + "/serviceDatabase").toPath());
		
		/** copying previous file to current file ***********************************************/
		FileInputStream fis = new FileInputStream(oldFn + "/studentDatabase"); 
		FileOutputStream fos = new FileOutputStream(studentFn); 

	    int b; 
	    while  ((b=fis.read()) != -1) 
	        fos.write(b); 

	    fis.close(); 
	    fos.close(); 
	    
	    
	    FileInputStream fis1 = new FileInputStream(oldFn + "/serviceDatabase"); 
		FileOutputStream fos1 = new FileOutputStream(serviceFn); 

	    int b1; 
	    while  ((b1=fis1.read()) != -1) 
	        fos1.write(b1); 

	    fis1.close(); 
	    fos1.close(); 
		}
		catch (IOException e) {
			System.out.println("Exception Occurred" + e);
		}
		
								
	}
	
	/** Prints a report of every student within the system and their hours.
	 * 
	 * @param folder - This is root file path for the entire program
	 */
	public void printStudentHourReport(String folder) {
		try {
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss");  
		Date date = new Date();  
		
		//File fn = new File("C:/Users/1100299029/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020/prints/" + (formatter.format(date)) + " studentHourReport");
		File fn = new File(folder + "/Prints/" + (formatter.format(date)) + " studentHourReport");
		
		String result = "Alpharetta High School Student Total Hour Report (as of " + (formatter.format(date)) + ")\n";
		
		for (int i = 0; i < studentList.size(); i++)
		{
			result += studentList.get(i).getStudentId() + " - " + studentList.get(i).getFullName() + " - " + studentList.get(i).getTotalServiceHours() + "\n";
		}
		
		//System.out.println(result);
		
		BufferedWriter serveOut = new BufferedWriter(new FileWriter(fn)); 
		serveOut.write(result); 
		serveOut.close(); 
		
		System.out.println("Student Total Hour Report Created.");
		
		Desktop desktop = Desktop.getDesktop();
		desktop.open(fn);
		}
		catch (IOException e) {
			System.out.println("Exception Occurred" + e);
		}
	}
	
	/** Prints a report of every student winning the Community award and their hours.
	 * 
	 * @param folder - This is root file path for the entire program
	 */	
	public void createCommunityLevelReport(String folder)
	{
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss");  
			Date date = new Date();  
			
			//File fn = new File("C:/Users/1100299029/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020/prints/" + (formatter.format(date)) + " communityLevelReport");
			File fn = new File(folder + "/Prints/" + (formatter.format(date)) + " communityLevelReport");
			
			String result = "Alpharetta High School Community Level Report (as of " + (formatter.format(date)) + ")\n";
			
			for (int i = 0; i < studentList.size(); i++)
			{
				if (studentList.get(i).isCommunityEligibility())
					result += studentList.get(i).getStudentId() + " - " + studentList.get(i).getFullName() + " - " + studentList.get(i).getTotalServiceHours() + "\n";
			}
			
			//System.out.println(result);
			
			BufferedWriter serveOut = new BufferedWriter(new FileWriter(fn)); 
			serveOut.write(result); 
			serveOut.close(); 
			
			System.out.println("Community Level Report Created.");
			
			Desktop desktop = Desktop.getDesktop();
			desktop.open(fn);
			}
			catch (IOException e) {
				System.out.println("Exception Occurred" + e);
			}
	}
	
	/** Prints a report of every student winning the Service award and their hours.
	 * 
	 * @param folder - This is root file path for the entire program
	 */	
	public void createServiceLevelReport(String folder)
	{
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss");  
			Date date = new Date();  
			
			File fn = new File(folder + "/Prints/" + (formatter.format(date)) + " serviceLevelReport");

			String result = "Alpharetta High School Service Level Report (as of " + (formatter.format(date)) + ")\n";
			
			for (int i = 0; i < studentList.size(); i++)
			{
				if (studentList.get(i).isServiceEligibility())
					result += studentList.get(i).getStudentId() + " - " + studentList.get(i).getFullName() + " - " + studentList.get(i).getTotalServiceHours() + "\n";
			}
			
			//System.out.println(result);
			
			BufferedWriter serveOut = new BufferedWriter(new FileWriter(fn)); 
			serveOut.write(result); 
			serveOut.close(); 
			
			System.out.println("Service Level Report Created.");
			
			Desktop desktop = Desktop.getDesktop();
			desktop.open(fn);
			}
			catch (IOException e) {
				System.out.println("Exception Occurred" + e);
			}
	}
	
	/** Prints a report of every student winning the Achievement award and their hours.
	 * 
	 * @param folder - This is root file path for the entire program
	 */	
	public void createAchievementLevelReport(String folder)
	{
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss");  
			Date date = new Date();  
			
			//File fn = new File("C:/Users/1100299029/OneDrive - Fulton County Schools/FBLA Programming 2019/FBLACSA2019-2020/prints/" + (formatter.format(date)) + " achievementLevelReport");
			File fn = new File(folder + "/Prints/" + (formatter.format(date)) + " achievementLevelReport");
			
			String result = "Alpharetta High School Achievement Level Report (as of " + (formatter.format(date)) + ")\n";
			
			for (int i = 0; i < studentList.size(); i++)
			{
				if (studentList.get(i).isAchieveEligibility())
					result += studentList.get(i).getStudentId() + " - " + studentList.get(i).getFullName() + " - " + studentList.get(i).getTotalServiceHours() + "\n";
			}
			
			//System.out.println(result);
			
			BufferedWriter serveOut = new BufferedWriter(new FileWriter(fn)); 
			serveOut.write(result); 
			serveOut.close(); 
			
			System.out.println("Achievement Level Report Created.");
			
			Desktop desktop = Desktop.getDesktop();
			desktop.open(fn);
			}
			catch (IOException e) {
				System.out.println("Exception Occurred" + e);
			}
	}
	
	//printServiceDatabase() ------------------------------------------------------------------------------------
	public String printServiceDatabase()
	{	
		String result = "Alpharetta High School Student Projects\n";
		
		for (int i = 0; i < studentList.size(); i++)
		{
			result += studentList.get(i).printProjectArray();
		}
		result += "***";
		
		return result;
	}
	
	//toString ---------------------------------------------------------------------------------------------------	
	public String toString()
	{	
		String result = "Alpharetta High School Roster";
		
		for (int i = 0; i < studentList.size(); i++)
		{
			result += "\nNo. " + (i+1) + " Alpharetta High School Student ------------------------\n"; // 24 dashes
			result += (studentList.get(i));
			result += "\n---";
		}
		
		return result;
	}
}