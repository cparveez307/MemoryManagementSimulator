import java.util.*;

public class MemoryManagementSimulator {

    // Constants
    static final int TOTAL_MEMORY_MB = 16000; // 16 GB
    static final int TOTAL_PAGES = 100;
    static final int PAGE_SIZE_MB = 160;
    static final int BASE_ADDRESS = 2000;

    static int[] memory = new int[TOTAL_PAGES]; // Stores process number in each page
    static List<Process> processList = new ArrayList<>();

    static class Process {
        int processId;
        int startingAddress;
        int processSizeMB;
        int unusedSpaceMB;
        int pagesUsed;

        public Process(int processId, int startingAddress, int processSizeMB,
                       int unusedSpaceMB, int pagesUsed) {
            this.processId = processId;
            this.startingAddress = startingAddress;
            this.processSizeMB = processSizeMB;
            this.unusedSpaceMB = unusedSpaceMB;
            this.pagesUsed = pagesUsed;
        }
    }

    public static void main(String[] args) {
        userMemoryAllocation();
        printSummaryReport();
    }

    public static void userMemoryAllocation() {
        Random random = new Random();
        int currentPageIndex = 0;
        int currentAddress = BASE_ADDRESS;
        int processId = 1;

        while (currentPageIndex < TOTAL_PAGES) {

            int randomNumber = random.nextInt(30) + 1; // 1 to 30
            int processSizeMB = randomNumber * 80;

            // Calculate required pages (round up)
            int pagesRequired = (int) Math.ceil((double) processSizeMB / PAGE_SIZE_MB);

            // Stop if not enough space
            if (currentPageIndex + pagesRequired > TOTAL_PAGES) {
                break;
            }

            int totalAllocatedMemory = pagesRequired * PAGE_SIZE_MB;
            int unusedSpace = totalAllocatedMemory - processSizeMB;

            // Assign pages to process
            for (int i = 0; i < pagesRequired; i++) {
                memory[currentPageIndex + i] = processId;
            }

            // Save process info
            processList.add(new Process(
                    processId,
                    currentAddress,
                    processSizeMB,
                    unusedSpace,
                    pagesRequired
            ));

            // Update tracking values
            currentAddress += totalAllocatedMemory;
            currentPageIndex += pagesRequired;
            processId++;
        }
    }

    public static void printSummaryReport() {
        System.out.println("\nSummary Report:");
        System.out.println("Process Id\tStarting Address\tProcess Size (MB)\tUnused Space (MB)");

        for (Process p : processList) {
            System.out.printf("%d\t\t%d\t\t\t%d\t\t\t%d\n",
                    p.processId,
                    p.startingAddress,
                    p.processSizeMB,
                    p.unusedSpaceMB);
        }
    }
}