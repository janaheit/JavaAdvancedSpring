package be.abis.exercise.model;


import be.abis.exercise.exception.InvoiceException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class PublicSession extends Session {

	private static int COUNT=0;
	public final static Company ABIS = new Company("Abis");
	private ArrayList<CourseParticipant> enrolments = new ArrayList<CourseParticipant>();
	private int sessionNr;

	public PublicSession(Course course, LocalDate date, Company location,
			Instructor instructor) {
		super(course, date, location, instructor);
		COUNT++;
		this.sessionNr = COUNT;
	}

	public int getSessionNr() {
		return sessionNr;
	}

	public void setSessionNr(int sessionNr) {
		this.sessionNr = sessionNr;
	}

	@Override
	public Company getOrganizer() {
		return ABIS;
	}

	public ArrayList<CourseParticipant> getEnrolments() {
		return enrolments;
	}

	public void setEnrolments(ArrayList<CourseParticipant> enrolments) {
		this.enrolments = enrolments;
	}

	@Override
	public double invoice() throws InvoiceException {
		System.out.println("Invoice in PublicSession");
		return 500;
	}

	public double calculateRevenue(){
		// revenue = (price * numParticipants) * 0.79 (VAT of 21%)

		BigDecimal dailyPrice = new BigDecimal(String.valueOf(this.getCourse().getDailyPrice()));
		BigDecimal numParticipants = new BigDecimal(String.valueOf(this.enrolments.size()));
		BigDecimal vat = new BigDecimal("0.79");

		BigDecimal revenue = dailyPrice.multiply(numParticipants).multiply(vat);
		return revenue.doubleValue();
	}

	public void addEnrolment(CourseParticipant... cps) {
		for (CourseParticipant c : cps)
			this.addEnrolment(c);
	}

	protected void addEnrolment(CourseParticipant cp) {
		if (!enrolments.contains(cp)) {
			enrolments.add(cp);
			System.out.println("Enrolment added to the list, now "
					+ enrolments.size() + " enrolments.");
			System.out.println("enrollee is: " + cp);
		} else {
			System.out.println("Couldn't add " + cp + " as enrollee, since he was already enrolled");
		}
	}

	public void removeEnrolment(CourseParticipant... cps) {
		for (CourseParticipant c : cps)
			removeEnrolment(c);
	}

	protected void removeEnrolment(CourseParticipant cp) {
		if (enrolments.contains(cp)) {
			enrolments.remove(cp);
			System.out.println("Enrollee " + cp + " removed from the list, now "
					+ enrolments.size() + " enrolments.");
		} else {
			System.out.println("Couldn't remove enrolment.");
		}
	}
	
	public Iterator<CourseParticipant> getEnrolmentsIterator(){
		return enrolments.iterator();
	}

	public void printListOfParticipants() {
		StringBuilder filename = new StringBuilder("/temp/javacourses/participants-");
		filename.append(sessionNr);
		filename.append(".txt");


		try (PrintWriter pw = new PrintWriter(filename.toString())) {

			pw.printf("%s\n",getCourse().getTitle());
			pw.printf("------------------------------------------------------\n");
			pw.printf("%-15s%s\n", "Instructor:", getInstructor().getName());

			if (getLocation().getAddress() == null){
				pw.printf("%-15s%s\n", "Location:", getLocation().getName());
			} else {
				pw.printf("%-15s%s, %s %s, %s %s\n", "Location:", getLocation().getName(),getLocation().getAddress().getStreet(),
						getLocation().getAddress().getNr(), getLocation().getAddress().getZipCode(),
						getLocation().getAddress().getTown());
			}

			pw.printf("------------------------------------------------------\n");

			// TODO make the sorting work! either put companies or work on a sorting that works anyway
			// enrolments.sort(Comparator.comparing(e -> ((Person) e).getCompany().getName()));
			// enrolments.sort((e1, e2)-> (((Person)e1).getCompany().getName().compareTo(((Person)e2).getCompany().getName())) );

			// Collections.sort(this.enrolments, Comparator.comparing(p -> ((Person) p).getCompany().getName()).thenComparing(p -> ((Person) p).getLastName()));
			for (int x=0; x< enrolments.size();x++) {
				Person person = (Person) enrolments.get(x);
				if (person.getCompany() == null) {
					pw.printf("%02d%-30s%s %S\n", x+1, "", person.getFirstName(), person.getLastName());
				} else {
					pw.printf("%02d%-15s%-15s%s %S\n", x+1, "", person.getCompany().getName(),
							person.getFirstName(), person.getLastName());
				}
			}

			pw.printf("------------------------------------------------------\n");

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}