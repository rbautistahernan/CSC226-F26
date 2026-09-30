package triage_efficiency;

import patient_intake.Patient;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Patient[] patients= generatePatients(100);
        EfficiencyTester tester = new EfficiencyTester();

        String foundId = "P00050";
        String missingId= "P99999";

        System.out.println("Linear found: " + tester.linearSearch(patients, foundId));
        System.out.println("Linear missing: " + tester.linearSearch(patients, missingId));

        sortByPatientId(patients);

        System.out.println("Binary found: " + tester.binarySearch(patients, foundId));
        System.out.println("Binary missing: " + tester.binarySearch(patients, missingId));

        System.out.println("Exponential found: " + tester.logNSearch(patients, foundId));
        System.out.println("Exponential missing: " + tester.logNSearch(patients, missingId));
        // TODO REQUIRED: Generate the patient data.
        // TODO REQUIRED: Sort the data by patientID when needed.
        // TODO REQUIRED: Run each search method and print a found and not-found example.
        // TODO OPTIONAL: Call timeDemo() to compare algorithm runtimes.
    }

    /**
     * REQUIRED (80%): Generate sample patient data for testing.
     *
     * Build an array of Patient objects with realistic IDs, names, complaints,
     * and triage information so you can test each search method.
     */
    public static Patient[] generatePatients(int count) {
        Patient[] patients = new Patient[count];

        String[] firstNames= {"Ana", "Luis", "Emma", "James"};
        String[] lastNames={"Garcia", "Smith", "Lopez", "Brown"};
        String[] complaints= {"Headache", "Back pain", "Nausea", "Sprained ankle"};

        for(int i=0; i<count; i++){
            String patientId=String.format("P%05d", i +1);

            patients[i]=new Patient(patientId, firstNames[i % firstNames.length], lastNames[i % lastNames.length],
                                    18+(i % 83), complaints[i % complaints.length], 3+(i % 3), "Waiting", "Unassigned",
                                    i % 24, String.format("INS%05d", i +1));}
        
        // TODO REQUIRED: Create the patient array and fill it with sample data.
        return patients; // Replace this with your implementation.
    }

    /**
     * REQUIRED (80%): Sort patients by patientID before binary search.
     *
     * The binary-search version only works on an array sorted by patientID.
     */
    public static Patient[] sortByPatientId(Patient[] patients) {
        Arrays.sort(patients, new Comparator<Patient>() {
            @Override
            public int compare(Patient first, Patient second){
                return first.getPatientID().compareTo(second.getPatientID());}
        });
        return patients; // Replace this with your implementation.
    }
}
