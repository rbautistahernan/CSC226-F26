package medical_action_tracking.optional;

import medical_action_tracking.EmergencyWaitingRoom;
import org.junit.jupiter. api.Test;
import patient_intake.Patient;

import static org.junit.jupiter.api.Assertions.*;

public class BoundedWaitingRoomTests{

    @Test
    void fullLaneRejectsPatientWithoutChangingTheRoom(){
        EmergencyWaitingRoom room = new EmergencyWaitingRoom(2);

        Patient chan = patient("P001", 1);
        Patient felix = patient("P002", 1);
        Patient han = patient("P003", 1);
        Patient jeongin = patient("P004", 2);

        assertTrue(room.addPatient(chan));
        assertTrue(room.addPatient(felix));
        assertTrue(room.addPatient(jeongin));

        assertEquals(3, room.size());
        assertEquals(chan, room.nextPatient());
        assertEquals(felix, room.nextPatient());
        assertTrue(room.isEmpty());
    }

    @Test 
    void servingPatientMakesSpaceInTheirLane(){
        EmergencyWaitingRoom room = new EmergencyWaitingRoom(1);

        Patient chan = patient("P001", 1);
        Patient feliz = patient("P002", 1);

        assertTrue(room.addPatient(chan));
        assertFalse(room.addPatient(felix));

        assertSame(chan, room.nextPatient());

        assertTrue(room.addPatient(felix));
        assertEquals(1, room.size());
        assertSame(felix, room.nextPatient());
        assertTrue(room.isEmpty());
    }

    @Test
    void capacityAppliesSeparatelyToEveryLane(){
        EmergencyWaitingRoom room = new EmergencyWaitingRoom(1);

        for(int level=1; level<=4; level++){
            assertTrue(room.addPatient(patient("P"+level, level)));
            assertFalse(room.addPatient(patient("Extra"+ level, level)));
        }

        assertEquals(4, room.size());

        for (int level=1; level<=4; level++){
            assertEquals("P" + level, room.nextPatient().getPatientID());
        }

        assertTrue(room.isEmpty());
    }

    @Test
    void nonpositiveCapacityIsRejected(){
        assertThrows(IllegalArgumentException.class, () -> new EmergencyWaitingRoom(0));

        assertThrows(IllegalArgumentException.class, ()-> new EmergencyWaitingRoom(-1));
    }

    private Patient patient(String id, int level){
        return new Patient(
            id, "Test", "Patient", 30, "Checkup", level, "Waiting, "ER-001", 9, "INS-001"
        );
    }
}