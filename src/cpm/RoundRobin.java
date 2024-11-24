package cpm;

import java.util.ArrayList;

public class RoundRobin extends Algorithm {

	public RoundRobin(ArrayList<Process> readyQueue) {
		// Ensure the constructor does not prematurely terminate due to an empty queue.
		if (readyQueue == null || readyQueue.isEmpty()) {
			System.out.println("\n\n\t\tReadyQueue is Empty\n\n");
			return;
		}

		do {
			// Process jobs if the ready queue has items
			if (!readyQueue.isEmpty()) {
				roundRobin(readyQueue);
			} else {
				// Wait briefly to allow other threads to populate the ready queue
				try {
					Main.Thread2.join(10); // Waits for Thread2 to load more processes
				} catch (InterruptedException e) {
					System.err.println("Thread was interrupted while waiting.");
					e.printStackTrace();
				}
			}
		} while (!CPUScheduler.readyQueue.isEmpty() || !JobScheduler.jobQueue.isEmpty());

		// Load results after processing all jobs
		loadResults();

		// Exit application after completion
		System.exit(0);
	}

	public static void roundRobin(ArrayList<Process> readyQueue) {
		int quantum = 8; // Quantum for round-robin scheduling

		while (!readyQueue.isEmpty()) {
			Process p = readyQueue.remove(0);

			// Process requires more time than the quantum, so it will be rescheduled
			if (p.getBurstTime() > quantum) {
				Time += quantum;

				// Calculate and update wait and termination times
				p.setWaitTime(p.getWaitTime() + (Time - quantum - p.getTerminationTime()));
				p.setBurstTime(p.getBurstTime() - quantum);
				p.setTerminationTime(Time);

				// Add the process back to the ready queue for the next round
				readyQueue.add(p);

				// Log process execution in the Gantt Chart
				GranttChart cG = new GranttChart("P" + p.getProcessID(), Time - quantum, Time);
				granttChart.add(cG);

			} else {
				// Process completes within the current quantum
				Time += p.getBurstTime();

				// Calculate turnaround and wait times
				p.setTurnAround(Time - p.getArrivalTime());
				p.setWaitTime(p.getWaitTime() + (Time - p.getBurstTime() - p.getTerminationTime()));
				p.setTerminationTime(Time);
				p.setState(States.Terminated);

				// Output process termination details
				System.out.println("Process " + p.getProcessID() + " State: " + p.getState() +
						" Terminated at: " + p.getTerminationTime());

				// Log process execution in the Gantt Chart
				GranttChart cG = new GranttChart("P" + p.getProcessID(), Time - p.getBurstTime(), Time);
				granttChart.add(cG);

				// Move the completed process to the final queue
				finalQueue.add(p);

				// Release memory occupied by the terminated process
				Memory.releaseMemory(p.getMemory());
			}
		}
	}
}
