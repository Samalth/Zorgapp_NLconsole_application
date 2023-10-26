import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
    static final int BewerkLongCapaciteit = 6;
    static final int BewerkMedicatie = 7;
    static final int ToevoegenMedicatie = 1;
    static final int VerwijderMedicatie = 2;
    static final int BewerkDosering = 3;
    Patient currentPatient;
    User currentUser;
    String RESET = "\u001B[0m";
    String RED_TEXT = "\u001B[31m";
    ArrayList<Patient> patients = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();

    int currentPatientIndex = 3;
    Scanner scanner = new Scanner(System.in);

    Administration(User user) {
        currentUser = user;
        patients.add(new Patient(1, "Alexsen", "Samuel", LocalDate.of(2006, 6, 7), 1.75, 98.4, 6000.0));
        patients.add(new Patient(2, "Baan", "Tijmen", LocalDate.of(2005, 5, 9), 1.92, 93.0, 6000.0));
        patients.add(new Patient(3, "Boerenfluitjes", "Jan", LocalDate.of(1983, 3, 30), 1.80, 80.0, 6000.0));
        patients.add(new Patient(4, "ter Sint", "Klaas", LocalDate.of(1971, 11, 11), 1.80, 80.0, 6000.0));
        patients.add(new Patient(5, "Rozenbrood", "Willem", LocalDate.of(1983, 3, 30), 1.80, 80.0, 6000.0));
        patients.add(new Patient(6, "Masha", "Fenna", LocalDate.of(1983, 3, 30), 1.80, 80.0, 6000.0));
        patients.add(new Patient(7, "Jonkhof", "Kim", LocalDate.of(2006, 2, 19), 1.80, 80.0, 6000.0));
        patients.add(new Patient(8, "op het Stap", "Max", LocalDate.of(1983, 3, 30), 1.80, 80.0, 6000.0));
        patients.add(new Patient(9, "Van Guitton", "Louis", LocalDate.of(1949, 9, 16), 1.80, 80.0, 6000.0));
        patients.add(new Patient(11, "Zeukel", "Mohammed", LocalDate.of(1983, 3, 30), 1.80, 80.0, 6000.0));

        currentPatient = patients.get(currentPatientIndex);
    }

    MedicationList medicationList = new MedicationList();
    boolean switchUser(int newUserId) {
        for (User user : users) {
            if (user.getUserID() == newUserId) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    void addUser(User user) {
        users.add(user);
    }

    void listUsers() {
        System.out.println("Selecteer een gebruiker: ");
        for (User user : users) {
            System.out.format("[%d] %s %s\n", user.getUserID(),user.getUserRole(), user.getUserName());
        }
    }

    void listPatients() {
        System.out.println(" ");
        System.out.println("Lijst van alle patiënten:");
        for (Patient patient : patients) {
            System.out.format("[%d] %s, %s\n", patient.id, patient.getSurname(), patient.getFirstName());
        }
    }

    public void viewAssignedMedications() {
        if (currentPatient != null) {
            currentPatient.viewAssignedMedications();
        } else {
            System.out.println("Geen patiënt geselecteerd.");
        }
    }

    void viewDataMenu() {
        System.out.println(" ");
        String userRole = currentUser.getUserRole();
        if (userRole.equals("Fysiotherapeut")) {
            currentPatient.viewDataFysiotherapeut();
        } else {
            if (userRole.equals("Tandarts")) {
                currentPatient.viewDataTandarts();
            } else {currentPatient.viewData();}
        }
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
        System.out.println("6:  Bewerk longcapaciteit");
        System.out.println("7:  Bewerk Medicatie");
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

            case BewerkLengte -> {
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
            case BewerkLongCapaciteit -> {
                String userRole = currentUser.getUserRole();
                if (userRole.equals("Fysiotherapeut")  || (userRole.equals("SB"))) {
                    System.out.print("Voer de nieuwe longcapaciteit in (bijv. 5555.5 ml): ");
                    String longCapaciteitInput = scanner.nextLine();
                    try {
                        double newLongCapaciteit = Double.parseDouble(longCapaciteitInput);
                        currentPatient.setLungCapacity(newLongCapaciteit);
                        System.out.println("Longcapaciteit bijgewerkt naar: " + newLongCapaciteit + " ml");
                    } catch (NumberFormatException e) {
                        System.out.println("Ongeldige longcapaciteit. Voer een geldig getal in (bijv. 5555.5).");
                    }
                    viewDataMenu();
                } else {
                    System.out.println(RED_TEXT + "Geen juiste rechten om deze actie uit te voeren." + RESET);
                    viewDataMenu();
                }
            }

            case BewerkMedicatie -> {
                String userRole = currentUser.getUserRole();
                if (userRole.equals("Fysiotherapeut") || (userRole.equals("Tandarts"))) {
                    System.out.println(RED_TEXT + "Geen juiste rechten om deze actie uit te voeren." + RESET);
                    viewDataMenu();
                } else {
                    try {
                        System.out.format("%s\n", "-".repeat(60));
                        viewAssignedMedications();
                        System.out.println(" ");
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
                                List<Medication> availableMedications = medicationList.getMedications();
                                if (availableMedications.isEmpty()) {
                                    System.out.println("Geen beschikbare medicijnen om toe te voegen.");
                                } else {
                                    System.out.println("Beschikbare medicijnen:");
                                    for (Medication medication : availableMedications) {
                                        medication.displayMedication();
                                    }
                                    System.out.print("Voer het ID van het medicijn in dat je wilt toevoegen: ");
                                    int chosenMedicationId = -1;
                                    while (chosenMedicationId == -1) {
                                        try {
                                            chosenMedicationId = Integer.parseInt(scanner.nextLine());
                                            Medication chosenMedication = medicationList.getMedicationById(chosenMedicationId);
                                            if (chosenMedication != null) {
                                                System.out.print("Voer de dosering in (bijv. 5.0 mg): ");
                                                String dosageInput = scanner.nextLine();
                                                try {
                                                    double dosage = Double.parseDouble(dosageInput);
                                                    currentPatient.assignMedication(chosenMedication, dosage);
                                                    System.out.println("Medicijn toegevoegd aan de patiënt.");
                                                } catch (NumberFormatException e) {
                                                    System.out.println("Ongeldige dosering. Voer een geldige dosering in.");
                                                }
                                            } else {
                                                System.out.println("Ongeldige medicatie. Voer een geldig ID in.");
                                            }
                                        } catch (NumberFormatException e) {
                                            System.out.println("Ongeldige invoer. Voer een geldig ID in.");
                                            System.out.print("Voer het ID van het medicijn in dat je wilt toevoegen: ");
                                        }
                                    }
                                }
                                viewDataMenu();
                            }

                            case VerwijderMedicatie -> {
                                Map<Medication, Double> assignedMedications = currentPatient.getAssignedMedications();

                                if (assignedMedications.isEmpty()) {
                                    System.out.println("Geen toegewezen medicatie aan huidige patiënt.");
                                } else {
                                    System.out.println("Kies medicatie om te verwijderen:");

                                    int index = 1;
                                    for (Map.Entry<Medication, Double> entry : assignedMedications.entrySet()) {
                                        Medication medication = entry.getKey();
                                        Double dosage = entry.getValue();

                                        System.out.println("[" + index + "] " + medication.medicationName + " - Dosering: " + dosage + " mg");
                                        index++;
                                    }
                                    System.out.print("Voer het nummer in van de medicatie die je wilt verwijderen: ");

                                    int choice = -1;
                                    while (choice == -1) {
                                        try {
                                            choice = Integer.parseInt(scanner.nextLine());
                                            if (choice >= 1 && choice <= assignedMedications.size()) {
                                                Medication[] medications = assignedMedications.keySet().toArray(new Medication[0]);
                                                Medication medicationToRemove = medications[choice - 1];
                                                assignedMedications.remove(medicationToRemove);
                                                System.out.println("Medicatie verwijderd.");
                                            } else {
                                                System.out.println("Ongeldige keuze. Voer een geldig nummer in.");
                                                System.out.print("Voer het nummer in van de medicatie die je wilt verwijderen: ");
                                                choice = -1;
                                            }
                                        } catch (NumberFormatException e) {
                                            System.out.println("Ongeldige invoer. Voer een geldig nummer in.");
                                            System.out.print("Voer het nummer in van de medicatie die je wilt verwijderen: ");
                                        }
                                    }
                                }
                                viewDataMenu();
                            }

                            case BewerkDosering -> {
                                Map<Medication, Double> assignedMedications = currentPatient.getAssignedMedications();

                                if (assignedMedications.isEmpty()) {
                                    System.out.println("Geen toegewezen medicatie aan huidige patiënt.");
                                } else {
                                    System.out.println("Kies medicatie om de dosering te bewerken:");

                                    int index = 1;
                                    for (Map.Entry<Medication, Double> entry : assignedMedications.entrySet()) {
                                        Medication medication = entry.getKey();
                                        Double dosage = entry.getValue();

                                        System.out.println("[" + index + "] " + medication.medicationName + " - Dosering: " + dosage + " mg");
                                        index++;
                                    }
                                    System.out.print("Voer het nummer in van de medicatie waarvan je de dosering wilt bewerken: ");

                                    int choice = -1;
                                    while (choice == -1) {
                                        try {
                                            choice = Integer.parseInt(scanner.nextLine());
                                            if (choice >= 1 && choice <= assignedMedications.size()) {
                                                Medication[] medications = assignedMedications.keySet().toArray(new Medication[0]);
                                                Medication medicationToEdit = medications[choice - 1];

                                                System.out.print("Voer de nieuwe dosering in voor " + medicationToEdit.medicationName + ": ");

                                                double newDosage = -1;
                                                while (newDosage == -1) {
                                                    try {
                                                        newDosage = Double.parseDouble(scanner.nextLine());
                                                        if (newDosage > 0) {
                                                            assignedMedications.put(medicationToEdit, newDosage);
                                                            System.out.println("Dosering bewerkt.");
                                                        } else {
                                                            System.out.println("Ongeldige dosering. Voer een positief getal in.");
                                                            System.out.print("Voer de nieuwe dosering in voor " + medicationToEdit.medicationName + ": ");
                                                            newDosage = -1;
                                                        }
                                                    } catch (NumberFormatException e) {
                                                        System.out.println("Ongeldige invoer. Voer een geldig getal in.");
                                                        System.out.print("Voer de nieuwe dosering in voor " + medicationToEdit.medicationName + ": ");
                                                    }
                                                }
                                            } else {
                                                System.out.println("Ongeldige keuze. Voer een geldig nummer in.");
                                                System.out.print("Voer het nummer in van de medicatie waarvan je de dosering wilt bewerken: ");
                                                choice = -1;
                                            }
                                        } catch (NumberFormatException e) {
                                            System.out.println("Ongeldige invoer. Voer een geldig nummer in.");
                                            System.out.print("Voer het nummer in van de medicatie waarvan je de dosering wilt bewerken: ");
                                        }
                                    }
                                }
                                viewDataMenu();
                            }
                            case Stop -> System.out.println("Terug naar hoofdmenu.");
                            default -> System.out.println("Ongeldige keuze.");
                        }
                } catch (Exception e) {
                    System.out.println("Er is een fout opgetreden bij het bewerken van medicijnen: " + e.getMessage());
                    }
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
            System.out.format("Huidige gebruiker: [%d] %s %s\n", currentUser.getUserID(), currentUser.getUserRole(), currentUser.getUserName());
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
                    System.out.println(" ");
                    int newPatientId;
                    while (true) {
                        System.out.print("Voer het ID van de patiënt in om te wisselen: ");
                        try {
                            newPatientId = Integer.parseInt(scanner.nextLine());
                            Patient newPatient = null;
                            for (Patient patient : patients) {
                                if (patient.getId() == newPatientId) {
                                    newPatient = patient;
                                    break;
                                }
                            }
                            if (newPatient != null) {
                                currentPatient = newPatient;
                                System.out.format("Gewisseld naar patiënt: %s\n", currentPatient.fullName());
                                break;
                            } else {
                                System.out.println("Geen patiënt gevonden met dit ID.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Geen geldige invoer. Voer een *geldig* patiënt-ID in.");
                        }
                    }
                }

                case Switch_User -> {
                    System.out.format("%s\n", "-".repeat(80));
                    System.out.println(" ");
                    System.out.println("Lijst van alle gebruikers:");
                    for (User user : users) {
                        System.out.format("[%d] %s %s\n", user.getUserID(), user.getUserRole(), user.getUserName());
                    }
                    boolean userSwitched = false;
                    while (!userSwitched) {
                        System.out.print("Kies een gebruiker om naar te wisselen (gebruikers-ID): ");
                        try {
                            int switchUserId = scanner.nextInt();

                            if (switchUser(switchUserId)) {
                                userSwitched = true;
                                System.out.format("Gewisseld naar gebruiker: %s %s\n", currentUser.getUserRole(), currentUser.getUserName());
                                System.out.println(" ");
                            } else {
                                System.out.println("Ongeldige gebruikers-ID. Probeer opnieuw.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Ongeldige invoer. Voer een geldig geheel getal in voor de gebruikers-ID.");
                            scanner.next();
                        }
                    }
                }
                case VIEW -> viewDataMenu();
                default -> System.out.println("Voer alstublieft een *geldig* getal in");
            }
        }
    }
}
