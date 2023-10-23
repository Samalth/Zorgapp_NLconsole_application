class Dosage {
    int medicationId;
    int dosageId;
    double dosageAmount;
    Dosage(int medicationId, int dosageId, double dosageAmount) {
        this.medicationId = medicationId;
        this.dosageId = dosageId;
        this.dosageAmount = dosageAmount;
    }
public int getMedicationID(){return medicationId;}
}
