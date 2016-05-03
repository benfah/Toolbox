package us.fihgu.toolbox.web;

import java.io.IOException;
import java.util.Iterator;

public class WriteSelectorThreadPool extends SelectorThreadPool<WriteSelectionHandler>
{

	public WriteSelectorThreadPool(WriteSelectionHandler handler, int numThreads) throws IOException
	{
		super(handler, numThreads);
	}

	@Override
	public SelectorThread<WriteSelectionHandler> getLightest()
	{
		SelectorThread<WriteSelectionHandler> lightest = null;
		
		Iterator<SelectorThread<WriteSelectionHandler>> iterator = this.pool.iterator();
		while(iterator.hasNext())
		{
			SelectorThread<WriteSelectionHandler> thread = iterator.next();
			if(thread.isValid())
			{
				if(lightest == null)
				{
					lightest = thread;
				}
				else if(lightest.getSelectionHandler().getWorkLoadSize() > thread.getSelectionHandler().getWorkLoadSize())
				{
					lightest = thread;
				}
			}
		}
		
		return lightest;
	}
}
