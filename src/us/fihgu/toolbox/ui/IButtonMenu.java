package us.fihgu.toolbox.ui;


public interface IButtonMenu
{
	/**
	/* sets the button and updates the inventory <br>
	/* it is preferred to use #setButton(int slot, Button button) if you are setting multiple buttons at once.
	/*/
	public void setButtonAndUpdate(int slot, Button button);
	
	/**
	/* sets the button to the internal button map <br>
	/* Note the inventory is not updated so the button is invisible to user until you #updateList() <br>
	/* button should never be null.
	/*/
	public void setButton(int slot, Button button);
	
	/**
	/* removes the button on given slot<br>
	/* Note the inventory is NOT updated, so user will still see the ItemStack until you do #updateList()
	/*/
	public void removeButton(int slot);
	
	/**
	/* removes button on given slot and #updateList()<br>
	/* its recommended to use #removeButton(int slot) then #updateList() when removing multiple buttons
	/*/
	public void removeButtonAndUpdate(int slot);
	
		/**
	/* remap the buttons into the inventory.
	/*/
	public void update();
}
