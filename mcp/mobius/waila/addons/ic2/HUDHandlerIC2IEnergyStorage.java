package mcp.mobius.waila.addons.ic2;

import java.util.List;
import java.util.logging.Level;

import mcp.mobius.waila.mod_Waila;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import net.minecraft.item.ItemStack;

public class HUDHandlerIC2IEnergyStorage implements IWailaDataProvider {

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor,	IWailaConfigHandler config) {
		return null;
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
		return currenttip;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,	IWailaConfigHandler config) {
		if (!config.getConfig("ic2.storage"))
			return currenttip;
		
		int stored   = -1;
		int capacity = -1;
		try{
			if (IC2Module.IEnergyStorage.isInstance(accessor.getTileEntity()))
				capacity = (Integer)(IC2Module.IEnergyStorage_GetCapacity.invoke(IC2Module.IEnergyStorage.cast(accessor.getTileEntity())));			
		} catch (Exception e){
			mod_Waila.log.log(Level.SEVERE, "[IC2] Unhandled exception trying to access an IEnergyStorage for display !.\n" + String.valueOf(e));
			return currenttip;				
		}

		if (accessor.getNBTData().hasKey("energy"))
			stored = accessor.getNBTData().getInteger("energy");

		if ((capacity != -1) && (stored != -1))
			currenttip.add(String.format("%s/%s EU", stored, capacity));
		
		return currenttip;
	}	
}
