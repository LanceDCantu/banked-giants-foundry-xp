package com.giantsfoundrybankedexperience;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class GiantsFoundryBankedExperiencePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(GiantsFoundryBankedExperiencePlugin.class);
		RuneLite.main(args);
	}
}