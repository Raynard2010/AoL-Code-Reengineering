package after.packages.system;

    // ---------------------------------------------- STUDENT CLASS ----------------------------------------------

public class Student {
    private String name;
    private int ID;
    private double GPA;
    private String year;
    private String department;

    public Student() {}

    public Student(int ID, String name, double GPA, String year, String department) {
        this.ID = ID;
        this.name = name;
        this.GPA = GPA;
        this.year = year;
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
    
    
}