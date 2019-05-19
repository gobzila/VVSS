package agenda.startApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import agenda.exceptions.InvalidFormatException;

import agenda.model.base.Activity;
import agenda.model.base.Contact;
import agenda.model.base.User;
import agenda.model.repository.classes.RepositoryActivityFile;
import agenda.model.repository.classes.RepositoryContactFile;
import agenda.model.repository.classes.RepositoryUserFile;
import agenda.model.repository.interfaces.RepositoryActivity;
import agenda.model.repository.interfaces.RepositoryContact;
import agenda.model.repository.interfaces.RepositoryUser;
import agenda.service.ActivityService;
import agenda.service.ContactService;
import agenda.service.Service;
import agenda.service.UserService;

//functionalitati
//F01.	 adaugarea de contacte (nume, adresa, numar de telefon, adresa email);
//F02.	 programarea unor activitati (denumire, descriere, data, locul, ora inceput, durata, contacte).
//F03.	 generarea unui raport cu activitatile pe care le are utilizatorul (nume, user, parola) la o anumita data, ordonate dupa ora de inceput.

public class MainClass {

	public static void main(String[] args) {
		BufferedReader in = null;
		try {
//			ContactService contactService = new ContactService();
//			UserService userService = new UserService();
//			ActivityService activityService = new ActivityService();
			Service service = new Service();
			User user = null;
			in = new BufferedReader(new InputStreamReader(System.in));
			while (user == null) {
				System.out.printf("Enter username: ");
				String username = in.readLine();
				System.out.printf("Enter password: ");
				String password = in.readLine();

				if (!service.isUserValid(username,password)) {
					user = null;
				} else {
					user = service.getUserByUsername(username);
				}
			}

			int chosen = 0;
			while (chosen != 4) {
				printMenu();
				chosen = Integer.parseInt(in.readLine());
				try {
					switch (chosen) {
					case 1:
						addContact(service, in);
						break;
					case 2:
						addActivity(service, in, user);
						break;
					case 3:
						showActivity(service, in, user);
						break;
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		} catch (Exception e) {

		}
		System.out.println("Program over and out\n");
	}

	public static void showActivity(Service activityService,
			BufferedReader in, User user) {
		try {
			System.out.printf("Afisare Activitate: \n");
			System.out.printf("Data(format: mm/dd/yyyy): ");
			String dateS = in.readLine();
			Calendar c = Calendar.getInstance();
			c.set(Integer.parseInt(dateS.split("/")[2]),
					Integer.parseInt(dateS.split("/")[0]) - 1,
					Integer.parseInt(dateS.split("/")[1]));
			Date d = c.getTime();

			System.out.println("Activitatile din ziua " + d.toString() + ": ");

			List<Activity> act = activityService.getAllByDate(user.getName(), d);
			for (Activity a : act) {
				System.out.printf("%s - %s: %s - %s with: ", a.getStart()
						.toString(), a.getEnd().toString(), a
						.getDescription());
				for (Contact con : a.getContacts())
					System.out.printf("%s, ", con.getName());
				System.out.println();
			}
		} catch (IOException e) {
			System.out.printf("Eroare de citire: %s\n" + e.getMessage());
		}
	}

	public static void addActivity(Service activityService,
			BufferedReader in, User user) {
		try {
			System.out.printf("Adauga Activitate: \n");
			System.out.printf("Descriere: ");
			String description = in.readLine();
			System.out.printf("Start Date(format: mm/dd/yyyy): ");
			String dateS = in.readLine();
			System.out.printf("Start Time(hh:mm): ");
			String timeS = in.readLine();
			Calendar c = Calendar.getInstance();
			c.set(Integer.parseInt(dateS.split("/")[2]),
					Integer.parseInt(dateS.split("/")[0]) - 1,
					Integer.parseInt(dateS.split("/")[1]),
					Integer.parseInt(timeS.split(":")[0]),
					Integer.parseInt(timeS.split(":")[1]));
			Date start = c.getTime();

			System.out.printf("End Date(format: mm/dd/yyyy): ");
			String dateE = in.readLine();
			System.out.printf("End Time(hh:mm): ");
			String timeE = in.readLine();
			
			c.set(Integer.parseInt(dateE.split("/")[2]),
					Integer.parseInt(dateE.split("/")[0]) - 1,
					Integer.parseInt(dateE.split("/")[1]),
					Integer.parseInt(timeE.split(":")[0]),
					Integer.parseInt(timeE.split(":")[1]));
			Date end = c.getTime();

			Activity act = new Activity(user.getName(), start, end,
					new LinkedList<Contact>(), description);

			boolean added = activityService.addActivity(act);

			if(added) {
				System.out.printf("S-a adaugat cu succes\n");
			} else {
				System.out.println("Nu s-a putut adauga activitatea");
			}

		} catch (IOException e) {
			System.out.printf("Eroare de citire: %s\n" + e.getMessage());
		}

	}

	public static void addContact(Service contactService,
			BufferedReader in) {

		try {
			System.out.printf("Adauga Contact: \n");
			System.out.printf("Nume: ");
			String name = in.readLine();
			System.out.printf("Adresa: ");
			String adress = in.readLine();
			System.out.printf("Numar de telefon: ");
			String telefon = in.readLine();

			contactService.addContact(name, adress, telefon);

			System.out.printf("S-a adugat cu succes\n");
		} catch (IOException e) {
			System.out.printf("Eroare de citire: %s\n" + e.getMessage());
		}

	}

	private static void printMenu() {
		System.out.printf("Alegeti o optiune:\n");
		System.out.printf("1. Adauga contact\n");
		System.out.printf("2. Adauga activitate\n");
		System.out.printf("3. Afisare activitati din data de...\n");
		System.out.printf("4. Exit\n");
		System.out.printf("Alege: ");
	}
}
