import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.Period;
import java.util.Scanner;

class Administration {
    static final int Stop = 0;
    static final int Switch_User = 1;
    static final int Switch_Patient = 2;
    static final int VIEW = 3;
    static final int BewerkPatiëntGegevens = 1;
    static final int BewerkVoornaam = 1;
    static final int BewerkAchternaam = 2;
    static final int BewerkGeboortedatum = 3;
    static final int BewerkLengte = 4;
    static final int BewerkGewicht = 5;
    static final int BewerkMedicatie = 6;
    static final int ToevoegenMedicatie = 1;
    static final int VerwijderMedicatie = 2;
    static final int BewerkDosering = 3;

    Patient currentPatient;
    User currentUser;
    ArrayList<Patient> patients = new ArrayList<>();
    int currentPatientIndex = 0;
    Scanner scanner = new Scanner(System.in);

    Administration(User user) {
        currentUser = user;
        patients.add(new Patient(1, "Alexsen", "Samuel", LocalDate.of(2006, 6, 7), 1.75, 98.4));
        patients.add(new Patient(2, "Baan", "Tijmen", LocalDate.of(2005, 5, 9), 1.92, 93.0));
        patients.add(new Patient(3, "De Boom", "Rachel", LocalDate.of(1983, 3, 30), 1.80, 80.0));
        patients.add(new Patient(4, "Cornelis", "Kees", LocalDate.of(1971, 11, 11), 1.80, 80.0));
        patients.add(new Patient(5, "Doorn", "Willem-Jan", LocalDate.of(1983, 3, 30), 1.80, 80.0));
        patients.add(new Patient(6, "Masha", "Fenna", LocalDate.of(1983, 3, 30), 1.80, 80.0));
        patients.add(new Patient(7, "Oudehof", "Kim", LocalDate.of(2006, 2, 19), 1.80, 80.0));
        patients.add(new Patient(8, "Schumacher", "Koos", LocalDate.of(1983, 3, 30), 1.80, 80.0));
        patients.add(new Patient(9, "Van Guitton", "Louis", LocalDate.of(1949, 9, 16), 1.80, 80.0));
        patients.add(new Patient(10, "Zeukel", "Mohammed", LocalDate.of(1983, 3, 30), 1.80, 80.0));

        patients.get(0).addMedication("Tocoferol, dl-alfa- acetaat", "Tabletten: 50mg");
        patients.get(0).addMedication("(Es)omeprazol ", "Tabletten: 10mg");

        patients.get(1).addMedication("Medicijn 3", "Dosage 3");
        patients.get(1).addMedication("Medicijn 4", "Dosage 4");

        currentPatient = patients.get(currentPatientIndex);
    }
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
        int userId = users.size() + 1;
        User user = new User(userId, userName);
        users.add(user);
    }

    void listUsers() {
        System.out.println("Selecteer een gebruiker: ");
        for (User user : users) {
            System.out.format("ID: [%d] %s\n", user.getUserID(), user.getUserName());
        }
    }

    void listPatients() {
        System.out.println("Lijst van alle patiënten:");
        for (Patient patient : patients) {
            System.out.format("ID: [%d] %s, %s\n", patient.id, patient.getSurname(), patient.getFirstName());
        }
    }

    void viewDataMenu() {
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
            case BewerkPatiëntGegevens -> editData();

            case Stop -> System.out.println("Terug naar hoofdmenu.");
            default -> System.out.println("Ongeldige keuze.");
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
        System.out.println("1:  Bewerk voornaam");
        System.out.println("2:  Bewerk achternaam");
        System.out.println("3:  Bewerk geboortedatum");
        System.out.println("4:  Bewerk lengte");
        System.out.println("5:  Bewerk gewicht");
        System.out.println("6:  Bewerk Medicatie");
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
            case BewerkVoornaam -> {
                System.out.print("Voer de nieuwe naam in: ");
                String newName = scanner.nextLine();
                currentPatient.setFirstName(newName);
                System.out.println("Naam bijgewerkt naar: " + newName);
                viewDataMenu();
            }
            case BewerkAchternaam -> {
                System.out.print("Voer de nieuwe achternaam in: ");
                String newSurname = scanner.nextLine();
                currentPatient.setSurname(newSurname);
                System.out.println("Achternaam bijgewerkt naar: " + newSurname);
                viewDataMenu();
            }

            case BewerkGeboortedatum -> {
                System.out.print("Voer de nieuwe geboortedatum in (dd-MM-yyyy): ");
                String newBirthDateStr = scanner.nextLine().trim();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                try {
                    LocalDate newBirthDate = LocalDate.parse(newBirthDateStr, formatter);
                    currentPatient.setDateOfBirth(newBirthDate);
                    System.out.println("Geboortedatum bijgewerkt naar: " + newBirthDate.format(formatter));
                    viewDataMenu();
                } catch (DateTimeParseException e) {
                    System.out.println("Ongeldige datum. Voer een geldige datum in (dd-MM-yyyy).");
                }
                viewDataMenu();
            }

            case BewerkLengte -> { // Bewerk lengte
                System.out.print("Voer de nieuwe lengte in (bijv. 1.75): ");
                String heightInput = scanner.nextLine();
                heightInput = heightInput.replace(",", ".");
                double newHeight;
                try {
                    newHeight = Double.parseDouble(heightInput);
                    currentPatient.setHeight(newHeight);
                    System.out.println("Lengte bijgewerkt naar: " + newHeight);
                    viewDataMenu();
                } catch (NumberFormatException e) {
                    System.out.println("Ongeldige lengte. Voer een geldig getal in (bijv. 1.75).");
                }
                viewDataMenu();
            }

            case BewerkGewicht -> {
                System.out.print("Voer het nieuwe gewicht in (bijv. 75.0): ");
                double newWeight;
                try {
                    newWeight = Double.parseDouble(scanner.nextLine());
                    currentPatient.setWeight(newWeight);
                    System.out.println("Gewicht bijgewerkt naar: " + newWeight);
                    viewDataMenu();
                } catch (NumberFormatException e) {
                    System.out.println("Ongeldig gewicht. Voer een geldig getal in (bijv. 75.0).");
                }
                viewDataMenu();
            }
            case BewerkMedicatie -> {
                try {
                    currentPatient.viewMedications();
                    System.out.println("1: Voeg medicijn toe");
                    System.out.println("2: Verwijder medicijn");
                    System.out.println("3: Bewerk medicijn dosering");
                    System.out.println("0: Terug naar hoofdmenu");
                    System.out.print("Maak een keuze: ");

                    int medicationChoice;
                    while (true) {
                        try {
                            medicationChoice = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Geen geldige keuze, voer alstublieft een *geldig* getal in");
                            System.out.print("Maak een keuze: ");
                        }
                    }

                    switch (medicationChoice) {
                        case ToevoegenMedicatie -> {
                            System.out.println("Kies een medicijn (1-10): ");
                            for (int i = 1; i <= 10; i++) {
                                System.out.println(i + ": Medicijn " + i);
                            }
                            int selectedMedication;
                            while (true) {
                                try {
                                    selectedMedication = Integer.parseInt(scanner.nextLine());
                                    if (selectedMedication >= 1 && selectedMedication <= 10) {
                                        break;
                                    } else {
                                        System.out.println("Ongeldige keuze, kies een nummer tussen 1 en 10.");
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Ongeldige keuze, voer een *geldig* getal in.");
                                }
                            }

                            System.out.println("Kies een dosering (1-10): ");
                            for (int i = 1; i <= 10; i++) {
                                System.out.println(i + ": Dosage " + i);
                            }
                            int selectedDosage;
                            while (true) {
                                try {
                                    selectedDosage = Integer.parseInt(scanner.nextLine());
                                    if (selectedDosage >= 1 && selectedDosage <= 10) {
                                        break;
                                    } else {
                                        System.out.println("Ongeldige keuze, kies een nummer tussen 1 en 10.");
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Ongeldige keuze, voer een *geldig* getal in.");
                                }
                            }

                            String medicationName = "Medicijn " + selectedMedication;
                            String medicationDosage = "Dosage " + selectedDosage;
                            currentPatient.addMedication(medicationName, medicationDosage);
                            System.out.println("Medicijn toegevoegd.");
                            viewDataMenu();
                        }

                        case VerwijderMedicatie -> {
                            System.out.print("Voer het ID in van het te verwijderen medicijn: ");
                            int medicationIndex;
                            while (true) {
                                try {
                                    medicationIndex = Integer.parseInt(scanner.nextLine());
                                    break;
                                } catch (NumberFormatException e) {
                                    System.out.println("Geen geldig ID, voer een *geldig* ID in: ");
                                    System.out.print("Voer het ID in van het te verwijderen medicijn: ");
                                }
                            }
                            currentPatient.removeMedication(medicationIndex);
                            System.out.println("Medicijn verwijderd.");
                        }
                        case BewerkDosering -> {
                            System.out.print("Voer het ID in van het te bewerken medicijn: ");
                            int medicationIndex;
                            while (true) {
                                try {
                                    medicationIndex = Integer.parseInt(scanner.nextLine());
                                    break;
                                } catch (NumberFormatException e) {
                                    System.out.println("Geen geldig ID, voer een *geldig* ID in: ");
                                    System.out.print("Voer het ID in van het te bewerken medicijn: ");
                                }
                            }
                            System.out.print("Voer de nieuwe dosering in: ");
                            String newDosage = scanner.nextLine();
                            currentPatient.editMedicationDosage(medicationIndex, newDosage);
                            System.out.println("Dosering bijgewerkt.");
                        }
                        case Stop -> {
                        }
                        default -> System.out.println("Ongeldige keuze.");
                    }
                } catch (Exception e) {
                    System.out.println("Er is een fout opgetreden bij het bewerken van medicijnen: " + e.getMessage());
                }
            }
            case Stop -> System.out.println("Terug naar hoofdmenu.");
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
            System.out.format("Huidige patiënt: %s [%s]\n", currentPatient.fullName(), currentPatient.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

            System.out.format("%d:  Stop\n", Stop);
            System.out.format("%d:  Wissel naar een andere gebruiker\n", Switch_User);
            System.out.format("%d:  Wissel naar een andere patiënt\n", Switch_Patient);
            System.out.format("%d:  Bekijk/bewerk gegevens van huidige patiënt\n", VIEW);

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
                case Stop -> nextCycle = false;

                case Switch_Patient -> {
                    listPatients();

                    int newIndex;
                    while (true) {
                        System.out.print("Vul patiënt-ID in om te wisselen: ");
                        try {
                            newIndex = Integer.parseInt(scanner.nextLine());
                            if (newIndex >= 1 && newIndex < patients.size()) {
                                currentPatientIndex = newIndex -1;

                                currentPatient = patients.get(currentPatientIndex);
                                System.out.format("Gewisseld naar patiënt: %s\n", currentPatient.fullName());
                                break;
                            } else {
                                if (newIndex != 0) {
                                    System.out.println("Geen geldig patiënt ID");
                                }
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Geen geldige invoer. Voer een geldig geheel getal in voor het patiënt-ID.");
                        }
                    }
                }

                case Switch_User -> {
                    System.out.println(" ");
                    System.out.println("Lijst van alle gebruikers:");
                    for (User user : users) {
                        System.out.format("[%d] %s\n", user.getUserID(), user.getUserName());
                    }
                    System.out.print("Vul gebruikers ID in om te wisselen: ");
                    int newIndex;
                    while (true) {
                        System.out.print("Vul gebruikers-ID in om te wisselen: ");
                        try {
                            newIndex = Integer.parseInt(scanner.nextLine());
                            if (newIndex >= 1 && newIndex < users.size()) {
                                currentUser = users.get(newIndex -1);
                                System.out.format("Gewisseld naar gebruiker: [%d] %s\n", currentUser.getUserID(), currentUser.getUserName());                                break;
                            } else {
                                System.out.println("Geen geldig gebruikers ID");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Geen geldige invoer. Voer een geldig *geldig* getal in");
                        }
                    }
                }
                case VIEW -> viewDataMenu();
                default -> System.out.println("Voer alstublieft een *geldig* getal in");
            }
        }
    }
}
