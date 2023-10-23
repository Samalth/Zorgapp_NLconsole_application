import java.util.*;
class MedicationList {
    List<Medication> medications = new ArrayList<>();
    List<Dosage> dosages = new ArrayList<>();
    int dosageIdCounter = 1;
    public MedicationList() {
        addMedication(new Medication(1, "Paracetamol"));
        addMedication(new Medication(2, "Cholesterol"));

        addDosage(1, 5.0);
        addDosage(1, 10.0);
        addDosage(2, 5.0);
        addDosage(2, 10.0);
    }

    void addMedication(Medication medication) {
        medications.add(medication);
    }
    void displayDosages(int medicationId) {
        for (Dosage dosage : dosages) {
            if (dosage.medicationId == medicationId) {
                System.out.format("[%s] %s\n", dosage.dosageId,  dosage.dosageAmount + " gram");
            }
        }
    }
    Dosage getDosageById(int dosageId) {
        for (Dosage dosage : dosages) {
            if (dosage.dosageId == dosageId) {
                return dosage;
            }
        }
        return null;
    }
    void removeMedication(int medicationId) {
        medications.removeIf(medication -> medication.medicationId == medicationId);
        dosages.removeIf(dosage -> dosage.medicationId == medicationId);
    }
    Medication getMedicationById(int medicationId) {
        for (Medication medication : medications) {
            if (medication.medicationId == medicationId) {
                return medication;
            }
        }
        return null;
    }

    void displayMedications() {
        for (Medication medication : medications) {
            medication.displayMedication();
        }
    }
    void viewMedication(Medication medication) {
        if (medication != null) {
            System.out.println("Medicatie:");
            System.out.format("  - %s\n", medication.medicationName);
        } else {
            System.out.println("Ongeldig medicijn.");
        }
    }
    public List<Medication> getMedications() {
        return medications;
    }
    public List<Dosage> getDosages() {
        return dosages;
    }

    void viewMedicationDosage(Medication medication) {
        if (medication != null) {
            System.out.println("Doseringen:");
            displayDosages(medication.medicationId);
        } else {
            System.out.println("Ongeldig medicijn voor doseringen.");
        }
    }
    void addDosage(int medicationId, double dosageAmount) {
        dosages.add(new Dosage(medicationId, dosageIdCounter++, dosageAmount));
    }

    void editDosage(int medicationId, int dosageId, double newDosageAmount) {
        for (Dosage dosage : dosages) {
            if (dosage.medicationId == medicationId && dosage.dosageId == dosageId) {
                dosage.dosageAmount = newDosageAmount;
                return;
            }
        }
    }
}
