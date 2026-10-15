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