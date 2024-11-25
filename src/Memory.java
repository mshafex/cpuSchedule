
public class Memory {

    static int availableMemory;

    public Memory(int memorySize) {
        Memory.availableMemory = memorySize;
    }

    public synchronized static boolean allocateMemory(int memoryRequired) {
        if (memoryRequired <= availableMemory) {
            availableMemory -= memoryRequired;
            System.out.print("Memory allocated: " + memoryRequired + " MB. Available memory: " + availableMemory + " MB.");
            return true;
        }
        System.out.println("Not enough memory. Available memory: " + availableMemory + " MB.");
        return false;
    }

    public static void releaseMemory(int memoryReleased) {
        availableMemory += memoryReleased;
        System.out.println("Memory released: " + memoryReleased + " MB. Available memory: " + availableMemory + " MB.");
        
    }
}
