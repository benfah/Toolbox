package us.fihgu.toolbox.web;

import java.nio.channels.SelectionKey;

public interface SelectionHandler
{
	public void handleSelection(SelectionKey selectionKey);
	public int getDefaultInterestSet();
}
