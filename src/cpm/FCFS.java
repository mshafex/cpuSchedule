package cpm;

import java.util.ArrayList;

public class FCFS extends Algorithm {

	public FCFS(ArrayList<Process> readyQueue) {

		if (readyQueue.isEmpty()) {
			System.out.println("\n\n\t\treadyQueue is Empty\n\n");
			return;
		}

		do {

			if (!readyQueue.isEmpty())
				fcfs(readyQueue);
			else {
				try {
					Main.Thread2.join(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		} while (!JobScheduler.jobQueue.isEmpty() || !CPUScheduler.readyQueue.isEmpty());

		// At each loop it will wait for 10ms for Thread2 in case ready queue load more
		// processes from the job queue

		loadResults();

		System.exit(0);
	}

	private static void fcfs(ArrayList<Process> readyQueue) {

		while (!readyQueue.isEmpty()) {
			Process p = readyQueue.remove(0);

			Time += p.getBurstTime();
			p.setTurnAround(Time - p.getArrivalTime());
			p.setWaitTime((p.getTurnAround() - p.getBurstTime()) + p.getWaitTime());
			p.setTerminationTime(Time);

			GranttChart cG = new GranttChart("P" + p.getProcessID(), Time - p.getBurstTime(), Time);
			granttChart.add(cG);
			finalQueue.add(p);
			p.setState(States.Terminated);

			System.out.println("Process " + p.getProcessID() + " State: " + p.getState() + " Terminated at: "
					+ p.getTerminationTime());
			Memory.releaseMemory(p.getMemory());

		}

	}

}