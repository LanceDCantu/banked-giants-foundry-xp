package com.giantsfoundrybankedexperience;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("giantsfoundrybankedexperience")
public interface GiantsFoundryBankedExperienceConfig extends Config
{
	@ConfigItem(
		keyName = "cascadeBankedXp",
		name = "Include output items",
		description = "Includes output items in the item quantity calculations",
		position = 1
	)
	default boolean cascadeBankedXp()
	{
		return true;
	}

	@ConfigItem(
		keyName = "grabFromInventory",
		name = "Include player inventory",
		description = "Toggles whether the items inside your inventory will be included in the calculations",
		position = 2
	)
	default boolean grabFromInventory()
	{
		return false;
	}

	@ConfigItem(
		keyName = "grabFromLootingBag",
		name = "Include looting bag",
		description = "Toggles whether the items stored inside your Looting Bag will be included in the calculations",
		position = 3
	)
	default boolean grabFromLootingBag()
	{
		return false;
	}

	@ConfigItem(
		keyName = "ignoredItems",
		name = "",
		description = "",
		hidden = true
	)
	default String ignoredItems()
	{
		return "";
	}

	@ConfigItem(
		keyName = "ignoredItems",
		name = "",
		description = ""
	)
	void ignoredItems(String val);
}
