package patient_intake;
// Switch from using ArrayList to a flat array implementation for patient storage.

public class PatientRegistry {
    // Flat array to store patients and a size field to track the number of stored patients.
    private Patient[] patientRegistry;
    private int size; // track actual number of patients

    // Initial capacity for the flat array. Can be adjusted as needed.
    private static final int INITIAL_CAPACITY = 10;

    public PatientRegistry() {
        this.patientRegistry = new Patient[INITIAL_CAPACITY];
        this.size = 0;
        // TODO REQUIRED: Create the initial array and set the starting size.
    }

    public void addPatient(Patient patient) {
        // TODO REQUIRED: Add a patient to the registry.
        // TODO OPTIONAL (+5%): Expand the array when it becomes full.
        if (size==patientRegistry.length){
            resizeArray();}
        patientRegistry[size]=patient;
        size++;
    }

    /**
     * Returns the patients currently stored in the registry.
     * The optional encapsulation extension requires returning a defensive copy.
     */
    private void resizeArray() {
        Patient[] newRegistry = new Patient[patientRegistry.length * 2];
        for (int i = 0; i < size; i++) {
            newRegistry[i] = patientRegistry[i];
        }
        patientRegistry = newRegistry;
    }
    public int getSize() {
        return size;
    }
    
    public Patient[] getPatientRegistry() {
        Patient[] copy = new Patient[size];
        System.arraycopy(patientRegistry, 0, copy, 0, size);
        return copy; // TODO REQUIRED: Return the patients currently stored.
    }

    public Patient getPatientByID(String patientID) {
        if (patientID==null){
        return null; // TODO REQUIRED: Search for and return the matching patient.
    }
    for (int i=0; i<size; i++){
        if (patientRegistry[i] != null && patientID.equals(patientRegistry[i].getPatientID())) {
                return patientRegistry[i];
            }
        }
        return null;
    }
    /**
     * Removes a patient from the registry by patientID.
     * @param patientID The ID of the patient to remove
     * @return true if patient was found and removed, false otherwise
     */
    public boolean removePatient(String patientID) {
        if (patientID==null){
        return false; // TODO OPTIONAL (+5%): Remove the patient with this ID.
    }
    for (int i = 0; i < size; i++) {
            if (patientRegistry[i] != null && patientID.equals(patientRegistry[i].getPatientID())) {
                removePatient(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a patient from the registry by index.
     * @param index The index of the patient to remove
     * @return the removed Patient, or null if index is invalid
     */
    public Patient removePatient(int index) {
        if (index<0||index>=size){
        return null; // TODO OPTIONAL (+5%): Remove by index and shift later elements left.
    }
    Patient removedPatient = patientRegistry[index];
        for (int i = index; i < size - 1; i++) {
            patientRegistry[i] = patientRegistry[i + 1];
        }
        patientRegistry[size - 1] = null;
        size--;

        return removedPatient;
    }
    /**
     * Updates a patient in the registry by matching patientID.
     * @param updatedPatient The patient with updated information
     * @return true if patient was found and updated, false otherwise
     */
    public boolean updatePatient(Patient updatedPatient) {
        if (updatedPatient==null || updatedPatient.getPatientID()==null){
        return false; // TODO OPTIONAL (+5%): Replace the patient with the same ID.
    }
     for (int i = 0; i < size; i++) {
            if (patientRegistry[i] != null && updatedPatient.getPatientID().equals(patientRegistry[i].getPatientID())) {
                patientRegistry[i] = updatedPatient;
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        if(size==0){
        return "PatientRegistry is empty.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("PatientRegistry (Total Patients: ").append(size).append("):\n");

        for (int i = 0; i < size; i++) {
            sb.append("  [").append(i).append("] ")
              .append(patientRegistry[i] != null ? patientRegistry[i].toString() : "null")
              .append("\n");
        }

        return sb.toString();
    } // TODO REQUIRED: Return a useful representation of the registry.
}


