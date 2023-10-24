import java.util.*;

class MedicationList {
    public Medication getMedicationById(int medicationId) {
        for (Medication medication : medications) {
            if (medication.medicationId == medicationId) {
                return medication;
            }
        }
        return null;
    }
    private List<Medication> medications = new ArrayList<>();
    private Map<Integer, List<Double>> medicationDosages = new HashMap<>();
    private int dosageIdCounter = 1;

    public MedicationList() {
        addMedication(new Medication(1, "Paracetamol"));
        addMedication(new Medication(2, "Cadexomeerjodium"));
        addMedication(new Medication(3, "Fexofenadine"));
        addMedication(new Medication(4, "Lidocaine"));
        addMedication(new Medication(5, "Tripelennamine hydrochloride"));
        addMedication(new Medication(6, "Ranitidine hydrochloride"));
        addMedication(new Medication(7, "Cholesterol"));

        addDosage(1, 5.0);
        addDosage(1, 10.0);
        addDosage(2, 5.0);
        addDosage(2, 10.0);
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void addMedication(Medication medication) {
        medications.add(medication);
    }

    void displayAvailableMedications() {
        System.out.println("Beschikbare medicijnen:");
        List<Medication> medications = getMedications();
        for (Medication medication : medications) {
            System.out.format("[%d] %s\n", medication.medicationId, medication.medicationName);
        }
    }

    public void addDosage(int medicationId, double dosageAmount) {
        medicationDosages.computeIfAbsent(medicationId, k -> new ArrayList<>()).add(dosageAmount);
    }


}