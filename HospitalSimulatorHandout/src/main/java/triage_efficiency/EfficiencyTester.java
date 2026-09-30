package triage_efficiency;

import java.util.*;
import patient_intake.Patient;

public class EfficiencyTester {
    /**
     * REQUIRED (80%): Implement linear search.
     *
     * Search through the patient array one element at a time until the matching
     * patientID is found. Return the Patient if it exists; otherwise return null.
     *
     * This method must run in O(n) time.
     */
    public Patient linearSearch(Patient[] patients, String pid) {
        for (Patient patient : patients){
    if (patients.getPatientID().equals(pid)){
        return patient;
    }
    }
        // Search the entire array in order and return the matching Patient.
        return null; 
    }
    /**
     * REQUIRED (80%): Implement binary search.
     *
     * This method works only on an array that is sorted by patientID.
     * Repeatedly divide the search range in half until the target is found.
     *
     * This method must run in O(log n) time.
     */
    public Patient binarySearch(Patient[] patients, String pid) {
        int left=0;
        int right = patients.length -1;

        while (left<=right){
            int middle=left+(right-left)/2;
            int comparison=patients[middle].getPatientID().compareTo(pid);

            if(comparison==0){
                return patients[middle];
                    }
            else if (comparison <0){
                left = middle +1;}
            else{
                right = middle-1;}
        }
        // The array must be sorted by patientID before calling this method.
        return null; 
    }

    /**Optional algorithm: Exponential search
    *Learnign Source: ChatGPT explanation and implementation guidance
    *
    *The array must be sorted by paritent ID. This method checks position
    *1, 2, 4, 8, and continues on to find a possible search range.
    *Then it uses binary search within that range.
    *Doubling the position and halving the range give O(log n)
    *worst case time.
    */
    public Patient logNSearch(Patient[] patients, String pid) {
        if (patients.length==0){
            return null;}
        if (patients[0].getPetientID().equals(pid)){
            return patients[0];}
        int bound =1;
        while (bound < patients.length
               && patients[bound]getPatientID().compareTo(pid) < 0) {
            bound *=2;}
        int left =bound/2;
        int right= Math.min(bound, patients.length -1);
        while (left<=right){
            int middle =left +(right-left)/2;
            int comparison=patients[middle].getPatientID().compareTo(pid);

            if(comparison ==0){
                return patients[middle];}
            else if (comparison <0){
                left = middle+1;}
            else{
                right = middle -1;}
        }
        return null; 
    }

    public void timeDemo() {
        long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            int x = 5 + 5;
        }
        long endTime = System.nanoTime();

        System.out.println("The example addition took: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            int x = 5 * 5;
        }
        endTime = System.nanoTime();
        System.out.println("The example multiplication took: " + (endTime - startTime) + " ns");
    }
}
