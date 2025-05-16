package packages.fileHandler;

import java.io.*;
import java.util.Scanner;
import packages.system.*;

    // --------------------------------- CSV FILE HANDLER CLASS ---------------------------------

public class CsvFileHandler {
    private static final String OUTPUT_CSV_FILE = "Final_Students.csv";

    public static Student fromCSV(String csvLine) {
        csvLine = csvLine.replaceAll("\"", "");
        String[] parts = csvLine.split(",");

        Student student = new Student();
        student.setID(Integer.parseInt(parts[0]));
        student.setName(parts[1]);
        student.setGPA(Double.parseDouble(parts[2]));
        student.setYear(parts[3]);
        student.setDepartment(parts[4]);
        return student;
    }

    public static StudentSystem readCsvFile() {
        StudentSystem students = new StudentSystem();
        Scanner scanner = new Scanner(System.in);
        String INPUT_CSV_FILE = InputValidator.getFilePath(scanner, ".csv");


        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_CSV_FILE))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                Student student = fromCSV(line);
                boolean isUnique = true;
                for (Student s : students.getStudentList()) {
                    if (s.getID() == student.getID() || s.getName().equals(student.getName())) {
                        isUnique = false;
                        break;
                    }
                }

                if (isUnique) {
                    if (student.getYear().equals("First") || student.getYear().equals("Second")) student.setDepartment("General");
                    students.addStudent(student, true);
                }
            }

            System.out.println("\nCSV file read successfully.\n");
        }
        catch (IOException e) {
            System.err.println("\nError reading CSV file: " + e.getMessage());
            System.out.println();
        }
        return students;
    }
    
    public static <T> void writeDataToCsvFile(FileWriter writer, T data) throws IOException{
    	writer.write("\"" + data + "\",");
    }
    
    public static void writeCsvFile(StudentSystem students) {
        try (FileWriter writer = new FileWriter(OUTPUT_CSV_FILE)) {
            if (students.getStudentList().isEmpty()) {
                System.out.println("\nNo students to write to CSV file.\n");
                return;
            }

            students.sortStudentsBy("ID");

            writer.write("\"ID\",\"Name\",\"GPA\",\"Year\",\"Department\"\n");
            for (Student student : students.getStudentList()) {
            	writeDataToCsvFile(writer, student.getID());
            	writeDataToCsvFile(writer, student.getName());
            	writeDataToCsvFile(writer, student.getGPA());
            	writeDataToCsvFile(writer, student.getYear());
            	writeDataToCsvFile(writer, student.getDepartment());
            	writer.write("\n");
            }
            writer.flush();
            System.out.println("\nCSV file written successfully.");
            System.out.println("File Path: " + OUTPUT_CSV_FILE);
        }
        catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
        }
        System.out.println();
    }
}
