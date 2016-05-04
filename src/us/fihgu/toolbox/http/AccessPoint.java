package us.fihgu.toolbox.http;

public class AccessPoint
{
	private IReleasable reousrce;
	
	protected AccessPoint(IReleasable resource)
	{
		this.reousrce = resource;
	}
	
	public void release()
	{
		if(this.reousrce != null)
		{
			this.reousrce.removeAccessPoint(this);
			this.reousrce = null;
		}
	}
}
