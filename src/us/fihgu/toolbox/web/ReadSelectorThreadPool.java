package us.fihgu.toolbox.web;

import java.io.IOException;
import java.util.Iterator;

public class ReadSelectorThreadPool extends SelectorThreadPool<SelectionHandler>
{
	public ReadSelectorThreadPool(SelectionHandler handler, int numThreads) throws IOException
	{
		super(handler, numThreads);
	}

	@Override
	public SelectorThread<SelectionHandler> getLightest()
	{
		SelectorThread<SelectionHandler> lightest = null;
		
		Iterator<SelectorThread<SelectionHandler>> iterator = this.pool.iterator();
		while(iterator.hasNext())
		{
			SelectorThread<SelectionHandler> thread = iterator.next();
			if(thread.isValid())
			{
				if(lightest == null)
				{
					lightest = thread;
				}
				else if(lightest.getKeySize() > thread.getKeySize())
				{
					lightest = thread;
				}
			}
		}
		
		return lightest;
	}
}
