package student;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

/**
 * Display reminders of students having a birthday soon.
 * @author you
 */
public class StudentApp {

	/**
	 * Print the names (and birthdays) of students having a birtday in the
	 * specified month.
	 * @param students list of students
	 * @param month the month to use in selecting bithdays
	 */
	
	
	public void filterAndPrint( List<Student> students, Predicate<Student> filter ) {
		for(Student s : students ) {
			if (filter.test(s))
	                  System.out.println( s );
		}
	}
	
	public static void main(String[] args) {
		List<Student> students = Registrar.getInstance().getStudents();
		LocalDate localDate = LocalDate.now();
		Predicate<Student> filter = s -> s.getBirthdate().getMonthValue() == localDate.getMonthValue();
		StudentApp app = new StudentApp();
		app.filterAndPrint(students, filter );
	}
}
