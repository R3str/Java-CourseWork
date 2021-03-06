package program.classes;

import program.Configuration;
import program.Controller;
import program.Main;
import program.util.ITickable;
import program.util.State;

import java.util.ArrayList;
import java.util.Random;

public class Resource implements ITickable
{
    private final String name;
    private Queue queue;
    private Status status;

    private int processTime;
    private Process currentTask;
    private int timer;

    private Random random = new Random();

    public Resource(String name)
    {
        this.name = name;
        queue = new Queue();
        status = Status.READY;
    }

    public void addProcess(Process process)
    {
        process.setState(State.WAITING);
        queue.addProcess(process);
    }

    private boolean setProcess(Process process)
    {
        if(status == Status.BUSY) return false;

        timer = 0;
        currentTask = process;
        currentTask.setResource(name);
        process.setState(State.WAITING);
        processTime = Math.floorDiv(process.getTimeRequired(), 100) * random.nextInt(20) + 5;

        Main.guiController.updateTable(Controller.Tables.RESOURCES);

        return true;
    }

    public String getName()
    {
        return name;
    }

    public void setStatus(Status value)
    {
        this.status = value;
    }

    public void sendTaskToCPU()
    {
        currentTask.setResource("");
        Main.getTaskScheduler().scheduleTask(currentTask);
        status = Status.READY;
    }

    @Override
    public void tick(int currentTime)
    {
        if(queue.isEmpty()) return;
        if(status == Status.READY)
        {
            setProcess(queue.getFirstProcess());
            setStatus(Status.BUSY);
        }
        else if(currentTask != null)
        {
            if (timer < processTime)
            {
                if(Configuration.runtimeErrorsEnabled() && random.nextInt(Configuration.getProcessTerminationChance()) == 0)
                    simulateException();

                timer++;
            }
            else
            {
                sendTaskToCPU();
                queue.removeProcess(currentTask);
                setStatus(Status.READY);
            }
        }

        Main.guiController.updateTable(Controller.Tables.RESOURCES);
    }

    public void simulateException()
    {
        queue.removeProcess(currentTask);
        currentTask.setState(State.TERMINATED);
        currentTask.setInterruptionReason("Runtime Error (" + name + ")");
        setStatus(Status.READY);
    }

    public ArrayList<Process> getTaskList()
    {
        return queue.getList();
    }

    public Process getCurrentTask()
    {
        return currentTask;
    }

    public void finishWork()
    {
        queue.clear();
        currentTask = null;
    }

    public enum Status
    {
        READY,
        BUSY
    }
}


