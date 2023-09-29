import java.util.ArrayList;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;
import java.util.*;

class Administration {
    static final int Stop = 0;
    static final int Switch_User = 1;
    static final int Switch_Patient = 2;
    static final int VIEW = 3;

    Patient currentPatient;
    User currentUser;

    ArrayList<Patient> patients = new ArrayList<>();
    int currentPatientIndex = 0;
    Scanner scanner = new Scanner(System.in);

    Administration(User user) {
        currentUser = user;
        patients.add(new Patient(0, "Alexsen", "Samuel", LocalDate.of(2006, 6, 7), 1.75, 98.4));
        patients.add(new Patient(1, "Baan", "Tijmen", LocalDate.of(2005, 5, 9), 1.92, 93.0));
        patients.add(new Patient(2, "Darnell", "Jairano", LocalDate.of(1983, 3, 30), 1.80, 80.0));
        patients.add(new Patient(3, "De Boom", "Rachel", LocalDate.of(1983, 3, 30), 1.80, 80.0));
        patients.add(new Patient(4, "Cornelis", "Kees", LocalDate.of(1971, 11, 11), 1.80, 80.0));
        patients.add(new Patient(5, "Doorn", "Willem-Jan", LocalDate.of(1983, 3, 30), 1.80, 80.0));
        patients.add(new Patient(6, "Masha", "Fenna", LocalDate.of(1983, 3, 30), 1.80, 80.0));
        patients.add(new Patient(7, "Oudehof", "Kim", LocalDate.of(2006, 2, 19), 1.80, 80.0));
        patients.add(new Patient(8, "Schumacher", "Koos", LocalDate.of(1983, 3, 30), 1.80, 80.0));
        patients.add(new Patient(9, "Van Guitton", "Louis", LocalDate.of(1949, 9, 16), 1.80, 80.0));
        patients.add(new Patient(10, "Zeukel", "Mohammed", LocalDate.of(1983, 3, 30), 1.80, 80.0));

        currentPatient = patients.get(currentPatientIndex);
    }
    /**
     * Wat ik hierboven heb gedaan met de patiënten ga ik proberen hieronder met Users
     */
    ArrayList<User> users = new ArrayList<>();

    boolean switchUser(int newUserId) {
        for (User user : users) {
            if (user.getUserID() == newUserId) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    void addUser(String userName) {
        int userId = users.size();
        User user = new User(userId, userName);
        users.add(user);
    }

    void listUsers() {
        System.out.println("Lijst van alle gebruikers:");
        for (User user : users) {
            System.out.format("ID: [%d] %s\n", user.getUserID(), user.getUserName());
        }
    }

    void listPatients() {
        System.out.println("Lijst van alle patiënten:");
        for (Patient patient : patients) {
            int age = calculateAge(patient.getDateOfBirth());
            System.out.format("ID: [%d] %s, %s (%d jaar oud)\n", patient.id, patient.getSurname(), patient.getFirstName(), age);
        }
    }


    void editData() {
        System.out.println("=================================================");
        System.out.println(" ");
        System.out.println("===============");
        System.out.println("====SUBMENU====");
        System.out.println("===============");
        System.out.println(" ");

        System.out.println("Bewerk patiëntgegevens:");
        System.out.println("1:  Bewerk naam");
        System.out.println("2:  Bewerk geboortedatum");
        System.out.println("3:  Bewerk lengte");
        System.out.println("4:  Bewerk gewicht");
        System.out.println("0:  Terug naar hoofdmenu");

        System.out.print("Maak een keuze: ");


        int editChoice;
        while (true) {
            try {
                editChoice = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Geen geldige keuze, voer alstublieft een *geldig* getal in");
                System.out.print("Maak een keuze: ");
            }
        }


        switch (editChoice) {
            case 1 -> {
                System.out.print("Voer de nieuwe naam in: ");
                String newName = scanner.nextLine();
                currentPatient.setFirstName(newName);
                System.out.println("Naam bijgewerkt naar: " + newName);
            }
            case 2 -> {
                System.out.print("Voer de nieuwe geboortedatum in (YYYY-MM-DD): ");
                String newBirthDateStr = scanner.nextLine().trim();
                LocalDate newBirthDate = LocalDate.parse(newBirthDateStr);
                currentPatient.setDateOfBirth(newBirthDate);
                System.out.println("Geboortedatum bijgewerkt naar: " + newBirthDateStr);
            }
            case 3 -> { // Bewerk lengte
                System.out.print("Voer de nieuwe lengte in (bijv. 1.75): ");
                String heightInput = scanner.nextLine();
                heightInput = heightInput.replace(",", ".");
                double newHeight;
                try {
                    newHeight = Double.parseDouble(heightInput);
                    currentPatient.setHeight(newHeight);
                    System.out.println("Lengte bijgewerkt naar: " + newHeight);
                } catch (NumberFormatException e) {
                    System.out.println("Ongeldige lengte. Voer een geldig getal in (bijv. 1.75).");
                }
            }
            case 4 -> {
                System.out.print("Voer het nieuwe gewicht in (bijv. 75.0): ");
                double newWeight;
                try {
                    newWeight = Double.parseDouble(scanner.nextLine());
                    currentPatient.setWeight(newWeight);
                    System.out.println("Gewicht bijgewerkt naar: " + newWeight);
                } catch (NumberFormatException e) {
                    System.out.println("Ongeldig gewicht. Voer een geldig getal in (bijv. 75.0).");
                }
            }
            case 0 -> System.out.println("Terug naar hoofdmenu.");
            default -> System.out.println("Ongeldige keuze.");
        }
    }


    void menu() {
        var scanner = new Scanner(System.in);

        boolean nextCycle = true;
        while (nextCycle) {
            System.out.format("%s\n", "=".repeat(80));
            System.out.println(" ");
            System.out.println("===============");
            System.out.println("===HOOFDMENU===");
            System.out.println("===============");
            System.out.println(" ");
            System.out.format("Huidige gebruiker: [%d] %s\n", currentUser.getUserID(), currentUser.getUserName());
            System.out.println(" ");
            System.out.format("Huidige patiënt: %s (%d jaar oud)\n", currentPatient.fullName(), calculateAge(currentPatient.getDateOfBirth()));


            System.out.format("%d:  Stop\n", Stop);
            System.out.format("%d:  Wissel naar een andere gebruiker\n", Switch_User);
            System.out.format("%d:  Wissel naar een andere patiënt\n", Switch_Patient);
            System.out.format("%d:  Bekijk de data van huidige patiënt\n", VIEW);


            System.out.print("Maak een keuze: ");


            int choice;
            while (true) {
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Geen geldige keuze, voer alstublieft een *geldig* getal in");
                    System.out.print("Maak een keuze: ");
                }
            }

            switch (choice) {
                case Stop ->
                        nextCycle = false;

                case Switch_Patient -> {
                    listPatients();
                    System.out.print("Vul gebruikers ID in om te wisselen: ");


                    int newIndex;
                    while (true) {
                        try {
                            newIndex = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Geen geldig patiënt ID");
                            System.out.print("Vul gebruikers ID in om te wisselen: ");
                        }
                    }

                    if (newIndex >= 0 && newIndex < patients.size()) {
                        currentPatientIndex = newIndex;
                        currentPatient = patients.get(currentPatientIndex);
                        System.out.format("Gewisseld naar patiënt: %s\n", currentPatient.fullName());
                    } else {
                        System.out.println("Geen geldig patiënt ID");
                    }
                }

                case Switch_User -> {
                    System.out.println("Lijst van alle gebruikers:");
                    for (User user : users) {
                        System.out.format("[%d] %s\n", user.getUserID(), user.getUserName());
                    }
                    System.out.print("Vul gebruikers ID in om te wisselen: ");


                    int newIndex;
                    while (true) {
                        try {
                            newIndex = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Voer alstublieft een *geldig* getal in");
                            System.out.print("Vul gebruikers ID in om te wisselen: ");
                        }
                    }

                    if (newIndex >= 0 && newIndex < users.size()) {
                        currentUser = users.get(newIndex);
                        System.out.format("Gewisseld naar gebruiker: [%d] %s\n", currentUser.getUserID(), currentUser.getUserName());
                    } else {
                        System.out.println("ID niet herkend");
                    }
                }

                case VIEW -> {
                    System.out.println(" ");
                    currentPatient.viewData();
                    System.out.println("=================================================");
                    System.out.println(" ");
                    System.out.println("1: Bewerk patiëntgegevens");
                    System.out.println("0: Terug naar hoofdmenu");
                    System.out.println(" ");
                    System.out.print("Maak een keuze: ");

                    int editChoice;
                    while (true) {
                        try {
                            editChoice = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Geen geldige keuze, voer alstublieft een *geldig* getal in");
                            System.out.print("Maak een keuze: ");
                        }
                    }

                    switch (editChoice) {
                        case 1 -> editData();
                        case 0 -> System.out.println("Terug naar hoofdmenu.");
                        default -> System.out.println("Ongeldige keuze.");
                    }

                }

                default -> System.out.println("Voer alstublieft een *geldig* getal in");
            }
        }
    }

    int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);
        return period.getYears();
    }
}
