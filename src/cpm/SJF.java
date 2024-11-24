package cpm;

import java.util.ArrayList;

public class SJF extends Algorithm {

	public SJF(ArrayList<Process> readyQueue) {

		readyQueue.sort((p1, p2) -> p1.getBurstTime() - p2.getBurstTime());

		if (readyQueue.isEmpty()) {
			System.out.println("\n\n\t\treadyQueue is Empty\n\n");
			return;
		}
		do {

			if (!readyQueue.isEmpty()) {
				sjf(readyQueue);
			}

			else {

				try {
					Main.Thread2.join(10);
					readyQueue.sort((p1, p2) -> p1.getBurstTime() - p2.getBurstTime());
				} catch (InterruptedException e) {

					e.printStackTrace();
				}

			}
		} while (!JobScheduler.jobQueue.isEmpty() || !CPUScheduler.readyQueue.isEmpty());
		loadResults();
	}

	public static void sjf(ArrayList<Process> readyQueue) {
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

	public static void sort(ArrayList<Process> readyQueue) {
		readyQueue.sort((p1, p2) -> p1.getBurstTime() - p2.getBurstTime());
	}

}