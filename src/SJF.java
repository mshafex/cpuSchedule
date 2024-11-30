import java.util.ArrayList;

public class SJF extends Algorithm {

	public SJF(ArrayList<Process> readyQueue) {

		if (readyQueue.isEmpty()) {
			System.out.println("\n\n\t\treadyQueue is Empty\n\n");
			return;
		}

		readyQueue.sort((p1, p2) -> p1.getBurstTime() - p2.getBurstTime());

		do {
			sjf(readyQueue);

		} while (!JobScheduler.jobQueue.isEmpty() || !CPUScheduler.readyQueue.isEmpty());

		loadResults();
	}

	public static void sjf(ArrayList<Process> readyQueue) {
		while (!readyQueue.isEmpty()) {
			Process p = readyQueue.remove(0);
			Process.changeProcessState(p, States.Running);

			Time += p.getBurstTime();
			p.setTurnAround(Time - p.getArrivalTime());
			p.setWaitTime((p.getTurnAround() - p.getBurstTime()) + p.getWaitTime());
			p.setTerminationTime(Time);

			GranttChart cG = new GranttChart("P" + p.getProcessID(), Time - p.getBurstTime(), Time);
			granttChart.add(cG);
			finalQueue.add(p);

			p.setState(States.Terminated);
			Memory.releaseMemory(p.getMemory());

			System.out.println("Process " + p.getProcessID() + " State: " + p.getState() + "\nTerminated at: "
					+ p.getTerminationTime());

			try {
				Main.cpuSchedulerThread.join(10);
				readyQueue.sort((p1, p2) -> p1.getBurstTime() - p2.getBurstTime());
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			System.out.println("--------------------------------------------------------------------------");
		}
	}

	public static void sort(ArrayList<Process> readyQueue) {
		readyQueue.sort((p1, p2) -> p1.getBurstTime() - p2.getBurstTime());
	}

}