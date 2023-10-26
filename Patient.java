import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Patient {

    String RESET = "\u001B[0m";
    String CYAN_TEXT = "\u001B[36m";
    String surname;
    String firstName;
    LocalDate dateOfBirth;
    double lungCapacity;
    int id;

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
    double height;
    double weight;
    double calculateBMI() {
        if (height <= 0 || weight <= 0) {
            return 0.0;
        }

        return weight / (height * height);
    }

    Patient(int id, String surname, String firstName, LocalDate dateOfBirth, double height, double weight, Double lungCapacity) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.height = height;
        this.weight = weight;
        this.lungCapacity = lungCapacity;
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
    public void setLungCapacity(double longCapaciteit) {
        this.lungCapacity = longCapaciteit;
    }
    public double getLongCapacitity() {
        return lungCapacity;
    }

    void viewData() {
        System.out.format("===== Patiënt id=%d ==============================\n", id);
        System.out.format("%-17s %s\n", "Voornaam:", firstName);
        System.out.format("%-17s %s\n", "Achternaam:", surname);
        System.out.format("%-17s %s\n", "Geboortedatum:", dateOfBirth.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        System.out.format("%-17s %d    jaar\n", "Leeftijd:", calculateAge(dateOfBirth));
        System.out.format("%-17s %.2f  m\n", "Lengte:", height);
        System.out.format("%-17s %.1f kg\n", "Gewicht:", weight);
        System.out.format("%-17s %.1f kg/m2\n", "BMI:", calculateBMI());
        System.out.println(" ");
        viewAssignedMedications();
    }

    void viewDataFysiotherapeut() {
        System.out.format("===== Patiënt id=%d ==============================\n", id);
        System.out.format("%-17s %s\n", "Voornaam:", firstName);
        System.out.format("%-17s %s\n", "Achternaam:", surname);
        System.out.format("%-17s %s\n", "Geboortedatum:", dateOfBirth.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        System.out.format("%-17s %d    jaar\n", "Leeftijd:", calculateAge(dateOfBirth));
        System.out.format("%-17s %.2f  m\n", "Lengte:", height);
        System.out.format("%-17s %.1f kg\n", "Gewicht:", weight);
        System.out.format("%-17s %.1f kg/m2\n", "BMI:", calculateBMI());
        System.out.format("%-17s %.2f ml\n", "Longcapaciteit", lungCapacity);
        System.out.println(" ");
    }

    void viewDataTandarts() {
        System.out.format("===== Patiënt id=%d ==============================\n", id);
        System.out.format("%-17s %s\n", "Voornaam:", firstName);
        System.out.format("%-17s %s\n", "Achternaam:", surname);
        System.out.format("%-17s %s\n", "Geboortedatum:", dateOfBirth.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        System.out.format("%-17s %d    jaar\n", "Leeftijd:", calculateAge(dateOfBirth));
    }

    private Map<Medication, Double> assignedMedications = new HashMap<>();

    public void assignMedication(Medication medication, double dosage) {
        assignedMedications.put(medication, dosage);
    }
    public void viewAssignedMedications() {
        if (assignedMedications.isEmpty()) {
            System.out.println(CYAN_TEXT + "Geen medicatie toegewezen aan de patiënt." + RESET);
        } else {
            System.out.println(CYAN_TEXT + "Toegewezen medicatie en doseringen:" + RESET);
            for (Map.Entry<Medication, Double> entry : assignedMedications.entrySet()) {
                Medication medication = entry.getKey();
                double dosage = entry.getValue();
                System.out.format("%s, Dosering: %.1f mg\n", medication.getMedicationName(), dosage);
            }
        }
    }
    public Map<Medication, Double> getAssignedMedications() {
        return assignedMedications;
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
