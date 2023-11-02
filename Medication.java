class Medication {
    public int medicationId;
    public String medicationName;
    public String medicationType;

    Medication(){
    }

    public Medication(int medicationId, String medicationName, String medicationType) {
        this.medicationId = medicationId;
        this.medicationName = medicationName;
        this.medicationType = medicationType;
    }
    void displayMedication() {
        System.out.format("[%d] %s %s\n", medicationId, medicationName, medicationType);
    }

    public String getMedicationName() {
        return medicationName;
    }
}
