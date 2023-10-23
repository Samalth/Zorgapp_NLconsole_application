import java.util.*;

class Medication {
    int medicationId;
    String medicationName;

    Medication(int medicationId, String medicationName) {
        this.medicationId = medicationId;
        this.medicationName = medicationName;
    }
    void displayMedication() {
        System.out.format("[%s] %s\n", medicationId, medicationName);

    }
}

