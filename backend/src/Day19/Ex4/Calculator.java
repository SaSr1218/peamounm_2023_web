package Day19.Ex4;

public class Calculator {
	

	private int memory;

	public int getMemory() { // getter
		return memory;
	}

	public synchronized void setMemory(int memory) { // setter
		this.memory = memory;
		try { Thread.sleep(1000);} 
		catch (Exception e) { }
		System.out.println(Thread.currentThread().getName() + ":" + this.memory); }
	
}
