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
            String[] parts = line.split(",");

            // TODO REQUIRED: Parse the fields from parts.
            String fullName=parts[0].trim();
            // TODO REQUIRED: Split the full name into firstName and lastName.
            String[] nameParts= fullName.split(" ",2);
            String firstName=nameParts[0];
            String lastName=nameParts.length >1?nameParts[1]:"";

            int age= Integer.parseInt(parts[1].trim());
            String id=parts[2].trim();
            // TODO REQUIRED: Create a Patient and add it to patients.
            Patient patient=new Patient(firstName, lastName, age, id);
            patient.addPatient(patient);
         }

         patient.display();// TODO REQUIRED: Display the completed registry.
      } catch (FileNotFoundException exception) {
        System.err.println("Error: Could not find the input file at " + filePath); // TODO REQUIRED: Report a missing input file.
      }
   }
}
