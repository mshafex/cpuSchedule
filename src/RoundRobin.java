import java.util.ArrayList;

public class RoundRobin extends Algorithm {

	public RoundRobin(ArrayList<Process> readyQueue) {

		do {

			if (!readyQueue.isEmpty())
				roundrobin(readyQueue);

		} while (!CPUScheduler.readyQueue.isEmpty() || !JobScheduler.jobQueue.isEmpty());

		loadResults();

		System.exit(0);
	}

	public static void roundrobin(ArrayList<Process> readyQueue) {
		int quantum = 8;

		while (!readyQueue.isEmpty()) {
			Process p = readyQueue.remove(0);
			Process.changeProcessState(p, States.Running);

			if (p.getBurstTimer() > quantum) {

				Time += quantum;

				GranttChart cG = new GranttChart("P" + p.getProcessID(), Time - quantum, Time);
				granttChart.add(cG);

				p.setWaitTime(p.getWaitTime() + (Time - quantum - p.getTerminationTime()));
				p.setBurstTimer(p.getBurstTimer() - quantum);
				p.setTerminationTime(Time);

				readyQueue.add(p);

			} else {

				Time += p.getBurstTimer();
				p.setTurnAround(Time - p.getArrivalTime());

				p.setWaitTime(p.getWaitTime() + (Time - p.getBurstTimer() - p.getTerminationTime()));

				p.setTerminationTime(Time);
				p.setState(States.Terminated);

				GranttChart cG = new GranttChart("P" + p.getProcessID(), Time - p.getBurstTimer(), Time);

				granttChart.add(cG);

				System.out.println("Process " + p.getProcessID() + " State: " + p.getState() + "\nTerminated at Time: "
						+ p.getTerminationTime());

				finalQueue.add(p);
				Memory.releaseMemory(p.getMemory());

				try {
					Main.cpuSchedulerThread.join(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println("--------------------------------------------------------------------------");
			}
		}
	}

}