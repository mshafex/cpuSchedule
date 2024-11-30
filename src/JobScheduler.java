import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// Read process information from file in a separate thread
public class JobScheduler extends Thread {

    static ArrayList<Process> jobQueue = new ArrayList<>();
    static int nOfTotalJobs = 0;
    int lineNum = 0;

    int processId, burstTime, memoryRequired;

    Process newProcess;

    public void run() {
        // Read File
        try (BufferedReader reader = new BufferedReader(
                new FileReader(System.getProperty("user.dir") + "/src/job.txt"))) {
            String line;
            // loop through lines
            while ((line = reader.readLine()) != null) {
                // test if jobs are more than 25
                if (jobQueue.size() >= 25) {
                    System.out.println("Error: The input file contains more than 25 jobs. Ignoring additional jobs.");
                    break;
                }
                // split line to get job informations
                String[] parts = line.split("[:;]");

                processId = Integer.parseInt(parts[0]);
                burstTime = Integer.parseInt(parts[1]);
                memoryRequired = Integer.parseInt(parts[2]);

                // test if job needs more than memory limit (1024 MB) - skip if exceed memory
                if (memoryRequired > 1024) {
                    System.out.println("Process " + processId + " skipped due to excessive memory requirement.");
                    continue;
                }

                // add job to array (queue) and mark queue as shared resource
                synchronized (jobQueue) {
                    // create new process
                    newProcess = new Process(processId, burstTime, memoryRequired);
                    // add process to queue
                    jobQueue.add(newProcess);
                    // increase total of jobs
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
