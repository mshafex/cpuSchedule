package cpm;

public class Memory {

    static int availableMemory;

    public Memory(int memorySize) {
        Memory.availableMemory = memorySize;
    }

    // allocateMemory will receive the memory of the process and then will decide
    // whether there a space to allocate that memory or not if there was a space it
    // will book it for the process and return true otherwise will return false
    public synchronized static boolean allocateMemory(int memoryRequired) {
        if (memoryRequired <= availableMemory) {
            availableMemory -= memoryRequired;
            return true;
        }
        return false;
    }

    // releaseMemory will receive a process memory and then will empty it space in
    // MM
    public static void releaseMemory(int memoryReleased) {
        availableMemory += memoryReleased;
    }
}