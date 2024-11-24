package cpm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Scanner;

public class JobScheduler extends Thread {

    static ArrayList<Process> jobQueue = new ArrayList<>();
    static int nOfTotalJobs = 0;
    int lineNum = 0;

    int processId, burstTime, memoryRequired;

    // Read process information from file in a separate thread
    public void run() {

        // Reading process

        try (BufferedReader reader = new BufferedReader(
                new FileReader(System.getProperty("user.dir") + "/src/job.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("[:;]");

                processId = Integer.parseInt(parts[0]);
                burstTime = Integer.parseInt(parts[1]);
                memoryRequired = Integer.parseInt(parts[2]);
                synchronized (jobQueue) {
                    jobQueue.add(new Process(processId, burstTime, memoryRequired));
                    nOfTotalJobs++;
                }

            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }

}