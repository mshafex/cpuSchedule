package cpm;

import java.util.ArrayList;

public class Algorithm {
	
	static ArrayList<Process> finalQueue = new ArrayList<>();
	static ArrayList<GranttChart> granttChart = new ArrayList<>();
	protected static int Time = 0;
	
	public static int getTime() {
		return Time;
	}

	public static void setTime(int time) {
		Time = time;
	}
	
	public static void loadResults() {
		// Print the Grantt chart
		GranttChart.Table(granttChart);

		double sumWait = 0, sumTurn = 0;

		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println(
				"\tProcess Id\t\tMemory Required\t\tArrival Time\t\t  Burst Time\t\tWaiting Time\t\tTurnAround Time\t\tTermination Time");

		for (Process p : finalQueue) {
			System.out.println("\t" + p.getProcessID() + "               \t\t" + p.getMemory() + "\t\t      "
					+ p.getArrivalTime() + "         \t\t" + p.getBurstTime() + "\t\t         " + p.getWaitTime()
					+ "       \t\t" + p.getTurnAround() + "             \t\t" + p.getTerminationTime());

			sumWait += p.getWaitTime();
			sumTurn += p.getTurnAround();
		}
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		double avgWait = sumWait / finalQueue.size();
		double avgTurn = sumTurn / finalQueue.size();

		System.out.println("AVG Waiting: " + avgWait + "\nAVG TrunAround: " + avgTurn);
	}
	
	
}