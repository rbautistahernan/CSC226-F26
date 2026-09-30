package patient_intake;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
   public static void main(String[] args) {
      String filePath = "src/main/java/patient_intake/patients.csv";
      PatientRegistry patients = new PatientRegistry();

      try (Scanner fileReader = new Scanner(new File(filePath))) {
         if (fileReader.hasNextLine()) {
            fileReader.nextLine(); // Skip the CSV header.
         }

         while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            if (line.trim()){ continue;}
            
            String[] parts = line.split(",", -1);
            String[] nameParts=parts[1].trim().split("",2);

            String firstName=nameParts[0];
            String lastName=nameParts.length >1?nameParts[1]:"";

            int age= Integer.parseInt(parts[1].trim());
            String id=parts[2].trim();
            // TODO REQUIRED: Create a Patient and add it to patients.
            Patient patient=new Patient(
               parts[0].trim(),
               firstName,
               lastName,
               Integer.parseInt(parts[2].trim()),
               parts[3].trim(),
               Integer.parseInt(parts[4].trim()),
               parts[5].trim(),
               parts[6].trim(),
               Integer.parseInt(parts[7].trim()),
               parts[8].trim());
            patients.addPatient(patient);
         }

         System.out.println(patients);
      } catch (FileNotFoundException exception) {
        System.err.println("Error: Could not find the input file at " + filePath); // TODO REQUIRED: Report a missing input file.
      }
   }
}
