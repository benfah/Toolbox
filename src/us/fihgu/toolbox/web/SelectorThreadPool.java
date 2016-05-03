package us.fihgu.toolbox.web;

import java.io.IOException;
import java.util.LinkedList;

/**
 * A SelectorThreadPool holds a number of {@link SelectorThread}<br>
 * It is responsible for balancing the work load of each Selector Thread.<br>
 * All SelectorThreads under the same SelectorThreadPool is expected to be using the same SelectionHandler and do the same task<br>
 */
public abstract class SelectorThreadPool<T extends SelectionHandler>
{
	LinkedList<SelectorThread<T>> pool = new LinkedList<SelectorThread<T>>();
	
	/**
	 * @param numThreads the number of SelectorThreads in this pool
	 * @throws IOException 
	 */
	public SelectorThreadPool(T handler, int numThreads) throws IOException
	{
		for(int i = 0; i < numThreads; i++)
		{
			pool.add(new SelectorThread<>(handler));
		}
	}
	
	/**
	 * @return a valid SelectorThread with the Lightest work load.<br>
	 * return null if this pool is empty or no valid SelectorThread exists.
	 */
	public abstract SelectorThread<T> getLightest();
	
	public void startAll()
	{
		for(SelectorThread<T> thread : this.pool)
		{
			thread.start();
		}
	}
	
	/**
	 * close all SelectorThreads in this pool. <br>
	 * this method will block until all Threads are closed. <br>
	 */
	public void closeAll()
	{
		for(SelectorThread<T> thread : this.pool)
		{
			
			try
			{	
				synchronized (thread)
				{
					if(!thread.isStopped())
					{
						thread.closeSelector();
						thread.wait();
					}
				}
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
