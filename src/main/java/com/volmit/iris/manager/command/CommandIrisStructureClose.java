package com.volmit.iris.manager.command;

import com.volmit.iris.util.KList;
import org.bukkit.entity.Player;

import com.volmit.iris.Iris;
import com.volmit.iris.IrisSettings;
import com.volmit.iris.manager.structure.StructureTemplate;
import com.volmit.iris.util.MortarCommand;
import com.volmit.iris.util.MortarSender;

public class CommandIrisStructureClose extends MortarCommand
{
	public CommandIrisStructureClose()
	{
		super("close", "x");
		requiresPermission(Iris.perm);
		setCategory("Structure");
		setDescription("Close a structure");
	}

	@Override
	public void addTabOptions(MortarSender sender, String[] args, KList<String> list) {

	}

	@Override
	public boolean handle(MortarSender sender, String[] args)
	{
		if(!IrisSettings.get().isStudio())
		{
			sender.sendMessage("To use Iris Studio Structures, please enable studio in Iris/settings.json");
			return true;
		}
		
		if(!sender.isPlayer())
		{
			sender.sendMessage("You don't have a wand");
			return true;
		}

		Player p = sender.player();
		StructureTemplate m = Iris.struct.get(p);

		if(m == null)
		{
			sender.sendMessage("You do not have an open structure");
			return true;
		}

		m.close();
		sender.sendMessage("Saved & Closed!");

		return true;
	}

	@Override
	protected String getArgsUsage()
	{
		return "";
	}
}