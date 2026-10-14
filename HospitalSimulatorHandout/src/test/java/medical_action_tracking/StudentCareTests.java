package medical_action_tracking;

import org.junit.jupiter.api.Test;
import patient_intake.Patient;

import static org.junit.jupiter.api.Assertions.*;


public class StudentCareTests{

    @Test
    void queueCanBeReusedAfterBecomingEmpty(){
        LinkedQueue<String>queue = new LinkedQueue<>();
        queue.enqueue("Bang Chan");

        assertEquals("Bang Chan", queue.dequeue());

        queue.enqueue("Felix");
        assertEquals(1, queue.size());
        assertEquals("[Felix]", queue.toString());
        assertEquals("Felix", queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    void queueDisplayPreservesOrderAfterMixedOperations(){
        LinkedQueue<String> queue = new LinkedQueue<>();
        queue.enqueue("Bang Chan");
        queue.enqueue("Felix");
        queue.enqueue("Jeongin");
        queue.dequeue();
        queue.enqueue("Han");

        assertEquals("[Felix, Jeongin, Han]", queue.toString());
        assertEquals("[Felix, Jeongin, Han]", queue.toString());
        assertEquals(3, queue.size());
        assertEquals("Felix", queue.dequeue());
        assertEquals("Jeongin", queue.dequeue());
        assertEquals("Han", queue.dequeue());
    }

    @Test 
    void rejectedStackPushPreservesExistingItems(){
        LinkedStack<String> stack = new LinkedStack<>();
        stack.push("Maniac");
        stack.push("Thunderous");

        assertThrows(IllegalArgumentException.class, () -> stack.push(null));

        assertEquals(2, stack.size());
        assertEquals("Thunderous", stack.pop());
        assertEquals("Maniac", stack.pop());
        assertNull(stack.pop());
        assertEquals(0, stack.size());
    }

    @Test
    void waitingRoomServesAllFourLevelsInPriorityOrder(){
        EmergencyWaitingRoom room = new EmergencyWaitingRoom();
        Patient fourth = patient("P004", 4);
        Patient third = patient("P003", 3);
        Patient second = patient("P002", 2);
        Patient first = patient("P001", 1);

        room.addPatient(fourth);
        room.addPatient(third);
        room.addPatient(second);
        room.addPatient(first);

        assertSame(first, room.nextPatient());
        assertSame(second, room.nextPatient());
        assertSame(third, room.nextPatient());
        assertSame(fourth, room.nextPatient());
        assertEquals(0, room.size());
        assertNull(room.nextPatient());
    }

    @Test
    void invalidArrivalPreservesExistingLaneOrder(){
        EmergencyWaitingRoom room = new EmergencyWaitingRoom();
        Patient first = patient("P001", 2);
        Patient second = patient("P002", 2);
        room.addPatient(first);
        room.addPatient(second);

        assertFalse(room.addPatient(patient("P000", 0)));
        assertFalse(room.addPatient(null));
        assertEquals(2, room.size());
        assertSame(first, room.nextPatient());
        assertSame(second, room.nextPatient());
    }

    @Test 
    void historyUsesInsertionOrderAndCanBeReusedAfterUndo(){
        TreatmentHistory history = new TreatmentHistory();

        history.addTreatment("P001", "Assesment", "2026-10-13 10:00");
        history.addTreatment("P002", "Bangage", "2026-10-13 09:00");

        String expected = "[2026-10-13 09:00 | P002 | Bandage," + "2026-10-13 10:00 | P001 | Assesment]";

        assertEquals(expected, history.displayHistory());
        assertEquals(expected, history.displayHistory());

        assertEquals("P002", history.undoLastAction().getPatientID());
        assertEquals("P001", history.undoLastAction().getPatientID());
        assertNull(history.undoLastAction());
        assertEquals("[]", history.displayHistory());

        history.addTreatment("P003", "Checkup", "2026-10-13 11:00");
        assertEquals("P003", history.undoLastAction().getPatientID());
        assertEquals("[]", history.displayHistory());
    }

    private Patient patient(String id, int level){
        return new Patient(
            id, "Test", "Patient", 30, "Checkup", level, "Waiting", "ER-001", 9, "INS-001"
        );
    }
}