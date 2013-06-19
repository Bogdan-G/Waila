package mcp.mobius.waila.gui;

import mcp.mobius.waila.gui.widget.Button2States;
import mcp.mobius.waila.gui.widget.ButtonChangeScreen;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiBaseConfigScreen extends GuiScreen {

    protected GuiScreen parentGui;	// GUI we will return to in the case we are call from a GUI
	
	public GuiBaseConfigScreen(GuiScreen _parentGui) {
		this.parentGui = _parentGui;
	}

   @Override
   /* Here we draw anything special, and super takes care of the buttons for us */
   public void drawScreen(int i, int j, float f)
   {
       drawDefaultBackground();        
       super.drawScreen(i, j, f);
   }   

   @Override
   /* Here we handle key presses. Super will just close the gui on ESC, and we like it like that */
   public void keyTyped(char keyChar, int keyID)
   {
       if (keyID == 1)
       {
    	   if (this.parentGui == null){
    		   this.mc.displayGuiScreen((GuiScreen)null);
    		   this.mc.setIngameFocus();
    	   } else {
    		   this.mc.displayGuiScreen(this.parentGui);
    	   }
       }	   
   }   
   
   @Override
   protected void actionPerformed(GuiButton guibutton)
   {
	   if (guibutton instanceof ButtonChangeScreen)
		   ((ButtonChangeScreen)guibutton).pressButton();
	   
	   else if (guibutton instanceof Button2States)
		   ((Button2States)guibutton).pressButton();	   
   }
   
}
