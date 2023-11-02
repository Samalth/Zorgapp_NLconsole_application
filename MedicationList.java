import java.util.*;

class MedicationList extends Medication{
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

    public MedicationList() {
        addMedication(new Medication(1, "Paracetamol", "tablet"));
        addMedication(new Medication(2, "Cadexomeerjodium", "tablet, max 16g"));
        addMedication(new Medication(3, "Fexofenadine", "tablet, max 180mg"));
        addMedication(new Medication(4, "Lidocaine", "tablet, max 6g"));
        addMedication(new Medication(5, "Tripelennamine hydrochloride", "tablet, max 5ug"));
        addMedication(new Medication(6, "Ranitidine hydrochloride", "tablet, max 15ug"));
        addMedication(new Medication(7, "Cholesterol", "tablet, max 5mg"));

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

    public void addDosage(int medicationId, double dosageAmount) {
        medicationDosages.computeIfAbsent(medicationId, k -> new ArrayList<>()).add(dosageAmount);
    }


}