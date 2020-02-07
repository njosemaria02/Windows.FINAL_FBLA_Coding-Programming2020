import java.util.ArrayList;

public class ServiceProject {
	private String myTitle, myDescription;
	private ArrayList<ServiceHour> serviceHours;
	
	/** constructors *********************************************/
	public ServiceProject() {
		myTitle = "";
		myDescription = "";
		serviceHours = new ArrayList<ServiceHour>();
	}
	
	public ServiceProject(String title, String description) {
		myTitle = title;
		myDescription = description;
		serviceHours = new ArrayList<ServiceHour>();
	}
	
	public ServiceProject(String title, String description, String date, double hour) {
		myTitle = title;
		myDescription = description;
		serviceHours = new ArrayList<ServiceHour>();
		serviceHours.add(new ServiceHour(date, hour));
	}
	
	/** public methods **********************************************/
	public String getName()
	{
		return myTitle;
	}
	
	public String getDescription()
	{
		return myDescription;
	}
	
	public ArrayList<ServiceHour> getServiceHourArray()
	{
		return serviceHours;
	}
	
	public ServiceHour getSpecificServiceHour(int index)
	{
		return serviceHours.get(index);
	}
	
	public void addServiceHours(String date, double hour) {
		ServiceHour newHour = new ServiceHour(date, hour);
		serviceHours.add(newHour);
	}
	
	public void addServiceHours(String dateAndHour) {
		String date = dateAndHour.substring(6, 16);
		String hour = dateAndHour.substring(28);
		double actualHour = Double.parseDouble(hour);
		
		ServiceHour newHour = new ServiceHour(date, actualHour);
		serviceHours.add(newHour);
	}
	
	public void deleteSpecificServiceHour(int index)
	{ serviceHours.remove(index); }
	
	public void setName(String name)
	{
		myTitle = name;
	}
	
	public void setDescription(String descrip)
	{
		myDescription = descrip;
	}
	
	//toString ------------------------------------------------------
	public String toString() {
		String result = "Project Name: " + myTitle;
		result += "\nDescription: " + myDescription;
		
		result += "\nHours: {\n";
		for (int i = 0; i < serviceHours.size(); i++)
			result += serviceHours.get(i).toString() +", ";
		result+= "\n}";
		
		return result;
	}
}
