package studentmarks;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;


/*
 * This class does the entire process. 
 */

public class ReadStudentMarks {
	
	private static Integer rank = 0;
	private static List<Student> studentList = new ArrayList<Student>();
	private static	List<Double> sortedList = new ArrayList<Double>();
	
	private static HashMap<Double, Integer> rankList = new HashMap<Double, Integer>();
	
	private static Integer numberOfStudents;
	private static Double totalMarks;
	private static Double averageMarks ;
	private static Double standardDeviation ;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//opening file
		try (Scanner scanner = new Scanner(new FileReader("studentmarks.txt"))) {
			//reading data from from file and storing name, marks in Student class. 
			//Adding student objects to studentList object
			while (scanner.hasNext()) {

				String line = scanner.nextLine();

				// this will ignore empty lines
				if (line.equals(""))
					continue;

				String[] lineArray = line.split(" ");
				String studentName = lineArray[0];
				Double mark = Double.parseDouble(lineArray[1]);
				sortedList.add(mark);

				Student student = new Student();
				student.setName(studentName);
				student.setMarks(mark);

				studentList.add(student);

			}
			
			
			HashSet<Double> marksContainer = new HashSet<Double>(sortedList);
			List<Double> sortedList1 = new ArrayList<Double>(marksContainer);

			Comparator cmp = Collections.reverseOrder();

			// sort the ArrayList in reverse order
			Collections.sort(sortedList1, cmp);
			for (int i = 0; i < sortedList1.size(); i++) {
				rankList.put(sortedList1.get(i), i + 1);
			}

			//setting rank for every student
			for (int j = 0; j < studentList.size(); j++) {

				int rank = rankList.get(studentList.get(j).getMarks());
				studentList.get(j).setRank(rank);

			}
			
			//sorting studentList object according to Names Alphabetically
			Collections.sort(studentList, new Comparator<Student>(){
				public int compare(Student s1, Student s2) {
			        return s1.getName().compareTo(s2.getName());
			   
			    }
			});
			
			//Printing students details according to Names Alphabetically
			
			System.out.println("Alphabetical order");
			
			for (Student st : studentList) {

				System.out.println(st.getName()+" "+st.getRank()+" "+st.getMarks());

			}
			
			//sorting studentList object according to highest Marks 
			Collections.sort(studentList, new Comparator<Student>(){
			     public int compare(Student o1, Student o2){
			         if(o1.getMarks() == o2.getMarks())
			             return 0;
			         return o1.getMarks() > o2.getMarks() ? -1 : 1;
			     }
			});
			
			
			System.out.println("   ");
			System.out.println("  ");
			
			
			//Printing students details according to highest marks
			
			System.out.println("Merit order");
			
			for (Student st : studentList) {
				
				

				System.out.println(st.getName()+" "+st.getRank()+" "+st.getMarks());
				
				
				
			}
			
			
			//calculating number of students by calculating number of objects in ArrayList.
			numberOfStudents = studentList.size();
		
			System.out.println("Number of students:   "+numberOfStudents);
			
			 Double average = findMean(sortedList);
			 Double deviation =  findDeviation(sortedList);
			 
			 //This class formats the decimal numbers
		     DecimalFormat form = new DecimalFormat("##.00");
		    
			System.out.println("Average student mark  : " + form.format(average));
			
			
			System.out.println("The standard deviation is  : " +form.format(deviation));
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/*
	 * This method calculates the average marks
	 */
	
	public static double findMean(List<Double> sortedList2) {
		
		double sum = 0;
		
		 for (int i = 0; i < sortedList2.size(); i++) {
		
		sum += sortedList2.get(i);
		
		}
		
		 return sum / sortedList2.size();
		
		}
	
	/*
	 * This method calculates the deviation
	 */
	public static double findDeviation(List<Double> sortedList2) {
		
		double mean = findMean(sortedList2);
		
		double squareSum = 0;
		
		 for (int i = 0; i < sortedList2.size(); i++) {
		
		squareSum += Math.pow(sortedList2.get(i) - mean, 2);
		
		}
		
		return Math.sqrt((squareSum) / (sortedList2.size() - 1));
		
		} 
	

}




