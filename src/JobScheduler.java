import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JobScheduler extends Thread {

    static ArrayList<Process> jobQueue = new ArrayList<>();
    static int nOfTotalJobs = 0;
    int lineNum = 0;

    int processId, burstTime, memoryRequired;

    // Read process information from file in a separate thread
    public void run() {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(System.getProperty("user.dir") + "/src/job.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (jobQueue.size() >= 25) {
                    System.out.println("Error: The input file contains more than 25 jobs. Ignoring additional jobs.");
                    break;
                }

                String[] parts = line.split("[:;]");
                processId = Integer.parseInt(parts[0]);
                burstTime = Integer.parseInt(parts[1]);
                memoryRequired = Integer.parseInt(parts[2]);

                if (memoryRequired > 1024) {
                    System.out.println("Process " + processId + " skipped due to excessive memory requirement.");
                    continue; 
                }

                synchronized (jobQueue) {
                    jobQueue.add(new Process(processId, burstTime, memoryRequired));
                    nOfTotalJobs++;
                }
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
