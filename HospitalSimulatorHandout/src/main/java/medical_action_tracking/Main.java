package medical_action_tracking;

import patient_intake.Patient;
import patient_intake.PatientRegistry;
import triage_efficiency.EfficiencyTester;

public class Main {
    public static void main(String[] args) {
        PatientRegistry registry = new PatientRegistry();
        Patient[] samplePatients = {
                patient("P001", 3),
                patient("P002", 1),
                patient("P003", 1)
        };
        for (Patient patient : samplePatients) {
            registry.addPatient(patient);
        }

        Patient[] patients = registry.getPatientRegistry();
        Patient searchedPatient = new EfficiencyTester().linearSearch(patients, "P002");
        System.out.println("Week 2 search found: " + searchedPatient);

        EmergencyWaitingRoom waitingRoom = new EmergencyWaitingRoom();
        for (Patient patient : patients) {
            waitingRoom.addPatient(patient);
        }
        while (!waitingRoom.isEmpty()) {
            Patient nextPatient = waitingRoom.nextPatient();
            System.out.println("Next patient: " + nextPatient.getPatientID()
                    + " (triage level " + nextPatient.getTriageLevel() + ")");
        }

        TreatmentHistory history = new TreatmentHistory();
        history.addTreatment("P002", "Initial assessment", "2026-09-27 09:00");
        history.addTreatment("P001", "X-ray", "2026-09-27 09:10");
        history.addTreatment("P002", "Pain relief", "2026-09-27 09:15");

        System.out.println("Treatment log: " + history.displayHistory());
        TreatmentRecord undone = history.undoLastAction();
        System.out.println("Undid: " + undone);
        System.out.println("Remaining log: " + history.displayHistory());

        runWaitingTimeSimulation();
    }

    private static void runWaitingTimeSimulation(){
        Patient[] simulationPatients = {
            patient("P001", 3), 
            patient("P002", 1),
            patient("P003", 1)
        };

        int[] arrivalMinutes = {0, 2, 2};
        int[] serviceMinutes = {10, 5, 3};

        EmergencyWaitingRoom room = new EmergencyWaitingRoom();

        int clock = 0;
        int nextArrival = 0;
        int served = 0;
        int totalWaitingTime = 0;

        System.out.println("Waiting-time simulation:");

        while (served < simulationPatients.length){
            while (nextArrival < simulationPatients.length 
            && arrivalMinutes[nextArrival] <= clock){
                room.addPatient(simulationPatients[nextArrival]);
                nextArrival++;
            }

            if (room.isEmpty()){
                clock++;
                continue;
            }

            Patient next = room.nextPatient();

            int index = 0;
            while (simulationPatients[index] != next){
                index++;
            }

            int waitingTime = clock - arrivalMinutes[index];
            totalWaitingTime+= waitingTime;

            System.out.println(next.getPatientID()
            + ": arrived at " + arrivalMinutes[index]
            + ", started at " + clock
            + ", waited " + waitingTime + " minutes");

            clock += serviceMinutes[index];
            served++;
        }

        double averageWaitingTime = (double) totalWaitingTime / served;

        System.out.println("Average waiting time: " + averageWaitingTime + " minutes");
    }

    private static Patient patient(String patientID, int triageLevel) {
        return new Patient(patientID, "Test", "Patient", 30, "Checkup", triageLevel,
                "Waiting", "ER-001", 9, "INS-001");
    }
}
