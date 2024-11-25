

import java.util.Scanner;

public class Main {
	static JobScheduler Thread1 = new JobScheduler();
	static CPUScheduler Thread2 = new CPUScheduler();

	public static void main(String[] args) {
		
		Thread1.start();
		Thread2.start();
		

		
System.out.println("....");
System.out.println("....");
System.out.println("....");
System.out.println("....");
System.out.println("....");

		Scanner input = new Scanner(System.in);

		System.out.println("Choose CPU scheduling algorithm (type num) : ");
		System.out.println("1.FCFS");
		System.out.println("2.SJF");
		System.out.println("3.Round-Robin with q = 8");

		int n = input.nextInt();
	
		switch (n) {
			case 1:
				
				FCFS f = new FCFS(CPUScheduler.readyQueue);
				
				break;
			case 2:
				SJF s = new SJF(CPUScheduler.readyQueue);
				break;
			case 3:
				 new RoundRobin(CPUScheduler.readyQueue);
				break;
				default:
					System.out.println("invalid input");
		}

		input.close();
		System.exit(0);

	}
}