class Medication {
    public int medicationId;
    public String medicationName;

    public Medication(int medicationId, String medicationName) {
        this.medicationId = medicationId;
        this.medicationName = medicationName;
    }
    void displayMedication() {
        System.out.format("[%d] %s\n", medicationId, medicationName);
    }

    public int getMedicationId() {
        return medicationId;
    }

    public String getMedicationName() {
        return medicationName;
    }

}
