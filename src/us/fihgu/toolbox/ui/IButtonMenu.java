package us.fihgu.toolbox.ui;


public interface IButtonMenu
{
	/**
	* sets the button and updates the inventory <br>
	* it is preferred to use {@link #setButton(int, Button)} if you are setting multiple buttons at once.
	*/
	public void setButtonAndUpdate(int slot, Button button);
	
	/**
	* sets the button to the internal button map <br>
	* Note the inventory is not updated so the button is invisible to user until you {@link #update()} <br>
	* button should never be null.
	*/
	public void setButton(int slot, Button button);
	
	/**
	* removes the button on given slot<br>
	* Note the inventory is NOT updated, so user will still see the ItemStack until you do {@link #update()}
	*/
	public void removeButton(int slot);
	
	/**
	* removes button on given slot and {@link #update()} <br>
	* its recommended to use {@link #removeButton(int)} then {@link #update()} when removing multiple buttons
	*/
	public void removeButtonAndUpdate(int slot);
	
	/**
	 * remap the buttons into the inventory.<br>
	 * {@link #update()} should be performed each time you make change to the appearance of the menu.
	 */
	public void update();
}
