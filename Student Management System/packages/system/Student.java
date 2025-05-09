package packages.system;

    // ---------------------------------------------- STUDENT CLASS ----------------------------------------------

/*
 * smell code	: dispensables - data class
 * penyebab		: ada public field
 * solusi		: encapsulate field
 */

public class Student {
    public String name;
    public int ID;
    public double GPA;
    public String year;
    public String department;

    public Student() {}

    public Student(int ID, String name, double GPA, String year, String department) {
        this.ID = ID;
        this.name = name;
        this.GPA = GPA;
        this.year = year;
        this.department = department;
    }
}