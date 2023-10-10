import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

class Patient {
    private List<Medication> medications;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public void setHeight(double length) {
        this.height = length;
    }
    public void setWeight(double length) {
        this.weight = length;
    }
    public void addMedication(String name, String dosage) {
        Medication medication = new Medication(name, dosage);
        medications.add(medication);
    }
    public void editMedicationDosage(int index, String newDosage) {
        if (index >= 0 && index < medications.size()) {
            medications.get(index).setDosage(newDosage);
        }
    }

    public void removeMedication(int index) {
        if (index >= 0 && index < medications.size()) {
            medications.remove(index);
        }
    }

    public void viewMedications() {
        System.out.println("Medicijnen van de patiënt:");
        for (Medication medication : medications) {
            System.out.format("Medicijn: %s, Dosering: %s\n", medication.getName(), medication.getDosage());
        }
    }

    double height;
    double weight;
    double calculateBMI() {
        if (height <= 0 || weight <= 0) {
            return 0.0;  // Dit is zodat er niet wordt gedeeld door nul of dat de uitkomst het negatief wordt
        }

        return weight / (height * height);
    }

    int       id;
    String    surname;
    String    firstName;
    LocalDate dateOfBirth;

    /**
     * Constructor
     */
    Patient(int id, String surname, String firstName, LocalDate dateOfBirth, double height, double weight) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.height = height;
        this.weight = weight;

        medications = new ArrayList<>();
    }

    LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    String getSurname() {
        return surname;
    }

    String getFirstName() {
        return firstName;
    }

    public int getId() { return id; }
    public double getHeight() { return height; }
    public double getWeight() { return weight; }

    /**
     * Display patient data.
     */
    void viewData() {
        System.out.format("===== Patiënt id=%d ==============================\n", id);
        System.out.format("%-17s %s\n", "Voornaam:", firstName);
        System.out.format("%-17s %s\n", "Achternaam:", surname);
        System.out.format("%-17s %s\n", "Geboortedatum:", dateOfBirth.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        System.out.format("%-17s %d    jaar\n", "Leeftijd:", calculateAge(dateOfBirth));
        System.out.format("%-17s %.2f  m\n", "Lengte:", height);
        System.out.format("%-17s %.2f kg\n", "Gewicht:", weight);
        System.out.format("%-17s %.2f kg/m2\n", "BMI:", calculateBMI());

    }

    String fullName() {
        return String.format("%s %s", firstName, surname);
    }

    int calculateAge(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);
        return period.getYears();
    }
}
