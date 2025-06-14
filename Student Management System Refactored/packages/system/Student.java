package packages.system;

    // ---------------------------------------------- STUDENT CLASS ----------------------------------------------

public class Student {
    private String name;
    private int ID;
    private double GPA;
    private String year;
    private String department;

	public void setName(String name) {
		this.name = name;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public void setGPA(double gPA) {
		GPA = gPA;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getName() {
		return name;
	}

	public int getID() {
		return ID;
	}

	public double getGPA() {
		return GPA;
	}

	public String getYear() {
		return year;
	}

	public String getDepartment() {
		return department;
	}
	
	public boolean isFailing() {
		if(this.GPA < 2) return true;
		else return false;
	}
    
    
}