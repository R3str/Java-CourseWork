package program;

public class Configuration
{
    private static int memoryVolume = 2048;   /*1024 < m < 16384*/
    private static int resourcesCount = 3;    /*3 < m < 5*/

    private static int clockTps = 1;
    private static int OrderingEvery = 10;     /*1 < m < 1000*/

    public static final int OS_MEMORY_USAGE = 128;
    public static final int PROCESS_MAX_PRIORITY = 32;
    public static final int PROCESS_MAX_MEMORY_USAGE = 512;
    public static final int PROCESS_MIN_MEMORY_USAGE = 16;

    private static boolean generateRandomProcesses = true;
    //error chance
    private static int processTerminationChance = 800;
    private static boolean generateErrors = true;

    public static synchronized int getMemoryVolume() { return memoryVolume; }
    public static synchronized void setMemoryVolume(int value) { memoryVolume = value; }

    public static synchronized int getResourcesCount() { return resourcesCount; }
    public static synchronized void setResourcesCount(int value) { resourcesCount = value; }

    public static synchronized int getOrderingCount() { return OrderingEvery; }
    public static synchronized void setOrderingCount(int value) { OrderingEvery = value; }

    public static synchronized int getClockTps() { return clockTps; }
    public static synchronized void setClockTps(int value) { clockTps = value; }

    public static synchronized boolean randomProcessGenerationEnabled() { return generateRandomProcesses; }

    public static synchronized boolean runtimeErrorsEnabled() { return generateErrors; }

    public static synchronized int getProcessTerminationChance() { return processTerminationChance; }

    public static void setDefaultResources()
    {
        memoryVolume = 2048;
        resourcesCount = 3;
    }
}
