
public class ServiceHour {
	private String myDate;
	private double myHour;
	
	/** constructors ************************************/
	public ServiceHour() {
		myDate = "01/01/2010";
		myHour = 0.0;
	}
	
	public ServiceHour(String date, double hour) {
		myDate = date;
		myHour = hour;
	}
	
	/** public methods *********************************/
	public String getMyDate()
	{
		return myDate;
	}
	
	public double getMyHour()
	{
		return myHour;
	}
	
	public void setMyDate(String newDate)
	{
		myDate = newDate;
	}
	
	public void setMyHour(double newHour)
	{
		myHour = newHour;
	}
	
	/** toString ***************************************************/
	public String toString() {
		String print = "";
		print += ("Date: " + myDate + " | Hour(s): " + myHour);
		return print;
	}
}
