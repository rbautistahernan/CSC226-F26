package medical_action_tracking;

import patient_intake.Patient;

public class EmergencyWaitingRoom {
    private LinkedQueue<Patient> level1;
    private LinkedQueue<Patient> level2;
    private LinkedQueue<Patient> level3;
    private LinkedQueue<Patient> level4;

    public EmergencyWaitingRoom() {
        // TODO: Create one empty FIFO queue for each triage level.
        level1 = new LinkedQueue<>();
        level2 = new LinkedQueue<>();
        level3 = new LinkedQueue<>();
        level4 = new LinkedQueue<>();
    }

    /**
     * Add a patient to the FIFO lane matching their triage level.
     *
     * @return true if added; false for null patients or levels outside 1-4
     */
    public boolean addPatient(Patient patient) {
        // TODO: Validate the patient and place them in the matching lane.
        if (patient== null){
        return false;
        }

        int level = patient.getTriageLevel();

        if(level==1){
            level1.enqueue(patient);
        }else if (level ==2){
            level2.enqueue(patient);
        }else if(level==3){
            level3.enqueue(patient);
        }else if (level==4){
            level4.enqueue(patient);
        }else{
            return false;
        }
        return true;
    }

    /**
     * Remove the next patient, checking triage level 1 before level 2, and so on.
     */
    public Patient nextPatient() {
        // TODO: Dequeue from the first non-empty lane, or return null if all are empty.
        if (!level1.isEmpty()){
        return level1.dequeue();
        }
        if (!level2.isEmpty()){
            return level2.dequeue();
        }
        if (!level3.isEmpty()){
            return level3.dequeue();
        }
        if (!level4.isEmpty()){
            return level4.dequeue();
        }
        return null;
    }

    public boolean isEmpty() {
        // TODO: Return true only when all four lanes are empty.
        return level1.isEmpty()
            && level2.isEmpty()
            && level3.isEmpty()
            && level4.isEmpty();
    }

    public int size() {
        // TODO: Return the total number of patients across all four lanes.
        return level1.size() + level2.size() + level3.size() + level4.size();
    }
}
