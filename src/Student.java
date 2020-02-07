import java.util.ArrayList;

public class Student {
	private String myFirstName, myLastName;
	private int myStudentId, myGradeLevel;
	private double myTotalServiceHours;
	private boolean communityEligibility, serviceEligibility, achieveEligibility = false;
	private ArrayList<ServiceProject> serviceProjects;
	
	/** constructors --------------------------------------------------------------------------**/
	public Student(String firstName, String lastName, int ID, int grade, double totalHours)
	{
		myFirstName = firstName;
		myLastName = lastName;
		myStudentId = ID;
		myGradeLevel = grade;
		myTotalServiceHours = totalHours;
		serviceProjects = new ArrayList<ServiceProject>();

	}
	
	public Student(String firstName, String lastName, int ID, int grade)
	{
		myFirstName = firstName;
		myLastName = lastName;
		myStudentId = ID;
		myGradeLevel = grade;
		myTotalServiceHours = 0;
		serviceProjects = new ArrayList<ServiceProject>();
	}
	
	public Student ()
	{
		myFirstName = "";
		myLastName = "";
		myStudentId = 0;
		myGradeLevel = 0;
		myTotalServiceHours = 0;
		serviceProjects = new ArrayList<ServiceProject>();

	}
	
	/** public methods ----------------------------------------------------------------------**/
	// accessor methods **********************************
	public String getFirstName()
	{	return myFirstName; }
	
	public String getLastName()
	{	return myLastName;	}
	
	public String getFullName()
	{	return myFirstName + " " + myLastName; }
	
	public int getStudentId()
	{	return myStudentId;	}
	
	public int getGradeLevel()
	{	return myGradeLevel; }
	
	public double getTotalServiceHours()
	{	return myTotalServiceHours;	}
	
	/* CSA Community (50 hours)
	CSA Service (200 hours)
	CSA Achievement (500 hours) */
	
	public boolean isCommunityEligibility() 
	{	
		if (myTotalServiceHours >= 50)
			setCommunityEligibility(true);
		
		return communityEligibility;
	}

	public boolean isServiceEligibility() 
	{
		if (myTotalServiceHours >= 200)
			setServiceEligibility(true);
		
		return serviceEligibility;
	}

	public boolean isAchieveEligibility() 
	{
		if (myTotalServiceHours >= 500)
			setAchieveEligibility(true);
		
		return achieveEligibility;
	}
	
	public ArrayList<ServiceProject> getServiceProjectsArray() {
		return serviceProjects;
	}
	
	public ServiceProject getSpecificServiceProject(int index) {
		return serviceProjects.get(index);
	}
	// mutator methods ***************************************

	public void setMyFirstName(String newFirstName) {
		myFirstName = newFirstName;
	}

	public void setMyLastName(String newLastName) {
		myLastName = newLastName;
	}

	public void setMyStudentId(int newStudentId) {
		myStudentId = newStudentId;
	}
	
	public void setMyGradeLevel(int newGradeLevel) {
		myGradeLevel = newGradeLevel;
	}

	public void setMyTotalServiceHours(double newTotalServiceHours) {
		myTotalServiceHours = newTotalServiceHours;
	}
	
	public double calculateTotalHours()
	{
		double hourTotal = 0;
		
		for (int i = 0; i < serviceProjects.size(); i++) //inside service project array
			for (int j = 0; j < serviceProjects.get(i).getServiceHourArray().size(); j++) //inside specific project hour array
				hourTotal += serviceProjects.get(i).getSpecificServiceHour(j).getMyHour();
		
		myTotalServiceHours = hourTotal;
		determineAwardEligibilities();
		
		return hourTotal;
	}

	public void setCommunityEligibility(boolean newCommunityEligibility) {
		communityEligibility = newCommunityEligibility;
	}

	public void setServiceEligibility(boolean newServiceEligibility) {
		serviceEligibility = newServiceEligibility;
	}

	public void setAchieveEligibility(boolean newAchieveEligibility) {
		achieveEligibility = newAchieveEligibility;
	}
	
	public void determineAwardEligibilities()
	{
		isCommunityEligibility();
		isServiceEligibility();
		isAchieveEligibility();
	}
	
	/** service project stuff **/
	public void addServiceProject(String title, String description, String date, double hour) {
		serviceProjects.add(new ServiceProject(title, description));
		serviceProjects.get(serviceProjects.size() -1).addServiceHours(date, hour);
	}
	
	public void addServiceProject(ServiceProject newProject) {
		serviceProjects.add(newProject);
	}
	
	public void addServiceProject() {
		serviceProjects.add(new ServiceProject());
	}
	
	public ServiceProject setSpecificServiceProject(int index, ServiceProject newProject) {
		return serviceProjects.set(index, newProject);
	}
	
	// print project array ----------------------------------------------------------------
	public String printProjectArray() {
		String result = "" + myStudentId + " Service Projects ------------------------\n";
		
		for (int i = 0; i < serviceProjects.size(); i++)
		{
			result += getSpecificServiceProject(i).toString();
			result += "\n\n";
		}
		
		return result;
	}
	
	// toString ----------------------------------------------------------------
	public String toString()
	{
		String result = "Full Name: " + myFirstName + " " + myLastName;
		
		result += "\nFirst Name: " + myFirstName;
		result += "\nLast Name: " + myLastName;
		result += "\nStudent Id: " + myStudentId;
		result += "\nGrade Level: " + myGradeLevel;
		result += "\nTotal Service Hours: " + myTotalServiceHours;
		result += "\nCommunity Award Eligibility (50+ hours): " + (this.isCommunityEligibility());
		result += "\nService Award Eligibility (200+ Hours): " + (this.isServiceEligibility());
		result += "\nAchieve Award Eligibility (500+ Hours): " + (this.isAchieveEligibility());
		
		return result;		
	}

}
