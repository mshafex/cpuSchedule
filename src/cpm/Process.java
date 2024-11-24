package cpm;

public class Process {

	private int ProcessID, BurstTime, Memory;
	private int arrivalTime, turnAround, waitTime, terminationTime;
	private States state;

	public Process(int processId, int burstTime, int memoryRequired) {
		this.ProcessID = processId;
		this.Memory = memoryRequired;
		this.BurstTime = burstTime;

		this.state = States.Waiting;
	}

	public int getProcessID() {
		return ProcessID;
	}

	public void setProcessID(int processID) {
		ProcessID = processID;
	}

	public int getBurstTime() {
		return BurstTime;
	}

	public void setBurstTime(int burstTime) {
		BurstTime = burstTime;
	}

	public int getMemory() {
		return Memory;
	}

	public void setMemory(int memory) {
		Memory = memory;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getTurnAround() {
		return turnAround;
	}

	public void setTurnAround(int turnAround) {
		this.turnAround = turnAround;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public int getTerminationTime() {
		return terminationTime;
	}

	public void setTerminationTime(int terminationTime) {
		this.terminationTime = terminationTime;
	}

	public States getState() {
		return state;
	}

	public void setState(States state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return Integer.toString(this.ProcessID);
	}

}