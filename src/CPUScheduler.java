import java.util.ArrayList;

public class CPUScheduler extends Thread {

    static ArrayList<Process> readyQueue = new ArrayList<>();

    static Memory memory = new Memory(1024);

    
    public static void changeProcessState(Process process, States newState) {
        process.setState(newState); 
        System.out.println("Process " + process.getProcessID() + " state changed to " + newState + " at time: "+Algorithm.getTime());
    }

    // Load jobs to ready queue in a separate thread
    public void run() {
        while (true) {
            Process selectedProcess = null;

            
            for (int i = 0; i < JobScheduler.jobQueue.size(); i++) {
                Process process = JobScheduler.jobQueue.get(i);
                if (process.getMemory() <= Memory.availableMemory) {
                    selectedProcess = process; 
                    break; 
                }
            }

            
            if (selectedProcess == null) {
                try {
                    Thread.sleep(10); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

           
            if (Memory.allocateMemory(selectedProcess.getMemory())) {
                JobScheduler.jobQueue.remove(selectedProcess); 
                readyQueue.add(selectedProcess); 
                selectedProcess.setArrivalTime(Algorithm.getTime()); 
                
              
                changeProcessState(selectedProcess, States.Ready);

                System.out.println("Available memory after allocation: " + Memory.availableMemory + " MB: "); 
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
