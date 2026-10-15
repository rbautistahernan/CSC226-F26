## Required Big-O Analysis
| Operation | Time complexity | Explanation |
|---|---|---|
| Queue enqueue/dequeue | O(1) | Updates front or rear references without traversing the queue. |
| Waiting room nextPatient() | O(1) | Checks at most four fixed lanes, regardless of patient count. |
| Stack push/pop/peek | O(1) | Access or updates the top directly. |
| Recursive queue toString() | O(n) | Visits every node once, assuming each item's text has bounded length. |

## Optional: Queue Implementation Comparison

| Feature | Linked queue | Circular-array queue |
|---|---|---|
| Enqueue/dequeue/peek | O(1) | O(1) |
| Capacity | Unbounded, subject to available memory | Fixed when constructed|
| Storage | Allocates nodes with next references | Allocates an array, including unused slots |
| Display | Recursive; O(n) traversal | Interactive; O(n) Traversal |


##Optional: Bounded Waiting Lanes

Each triage lane has the capacity supplied to the constructor.
If the selected lane is full, addPatient() returns to false without 
changing any lane. The default constructor preserves unbounded behavior.

##Optional: Recursive Queue Count

recursiveCount() counts the linked nodes without reomoving them.
A null node returns 0. Each other node contributes 1 plus the count of the remainign nodes. Tests compare this result with 
size() for empty and multi node queues.

##Optional: Simulation Metrics

The simulation uses one clinician and a clock measure in minutes.
Each patient has an arrival munute and a service duration.
Waiting time is the service start minute minus the arrival minute.
The example waiting times are 0, 8, and 13 minutes, giving an average waiting time of 7.0 minutes.
