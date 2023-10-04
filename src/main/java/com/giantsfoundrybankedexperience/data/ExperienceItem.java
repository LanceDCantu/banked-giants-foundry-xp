/*
 * Copyright (c) 2019, TheStonedTurtle <https://github.com/TheStonedTurtle>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.giantsfoundrybankedexperience.data;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import net.runelite.api.ItemComposition;
import net.runelite.api.ItemID;
import net.runelite.api.Skill;
import net.runelite.client.game.ItemManager;

/**
 *
 */
@Getter
public enum ExperienceItem
{
	/**
	 * Smithing
	 */
	// Ores
	COPPER_ORE(ItemID.COPPER_ORE, Metal.BRONZE, Skill.SMITHING, "Ores", 1),
	TIN_ORE(ItemID.TIN_ORE, Metal.BRONZE, Skill.SMITHING, "Ores", 1),
	IRON_ORE(ItemID.IRON_ORE, Metal.IRON, Skill.SMITHING, "Ores", 1),
	MITHRIL_ORE(ItemID.MITHRIL_ORE, Metal.MITHRIL, Skill.SMITHING, "Ores", 1),
	ADAMANTITE_ORE(ItemID.ADAMANTITE_ORE, Metal.ADAMANTITE, Skill.SMITHING, "Ores", 1),
	RUNITE_ORE(ItemID.RUNITE_ORE, Metal.RUNITE, Skill.SMITHING, "Ores", 1),
	// Bars
	BRONZE_BAR(ItemID.BRONZE_BAR, Metal.BRONZE, Skill.SMITHING, "Bars", 1),
	IRON_BAR(ItemID.IRON_BAR, Metal.IRON, Skill.SMITHING, "Bars", 1),
	STEEL_BAR(ItemID.STEEL_BAR, Metal.STEEL, Skill.SMITHING, "Bars", 1),
	MITHRIL_BAR(ItemID.MITHRIL_BAR, Metal.MITHRIL, Skill.SMITHING, "Bars", 1),
	ADAMANTITE_BAR(ItemID.ADAMANTITE_BAR, Metal.ADAMANTITE, Skill.SMITHING, "Bars", 1),
	RUNITE_BAR(ItemID.RUNITE_BAR, Metal.RUNITE, Skill.SMITHING, "Bars", 1),
	// Armour Pieces		
	
	// Bronze
	BRONZE_SCIMITAR(ItemID.BRONZE_SCIMITAR, Metal.BRONZE, Skill.SMITHING, "Armour", 1),
	BRONZE_LONGSWORD(ItemID.BRONZE_LONGSWORD, Metal.BRONZE, Skill.SMITHING, "Armour", 1),
	BRONZE_FULL_HELM(ItemID.BRONZE_FULL_HELM, Metal.BRONZE, Skill.SMITHING, "Armour", 1),
	BRONZE_SQ_SHIELD(ItemID.BRONZE_SQ_SHIELD, Metal.BRONZE, Skill.SMITHING, "Armour", 1),
	BRONZE_CLAWS(ItemID.BRONZE_CLAWS, Metal.BRONZE, Skill.SMITHING, "Armour", 1),
	BRONZE_WARHAMMER(ItemID.BRONZE_WARHAMMER, Metal.BRONZE, Skill.SMITHING, "Armour", 2),
	BRONZE_BATTLEAXE(ItemID.BRONZE_BATTLEAXE, Metal.BRONZE, Skill.SMITHING, "Armour", 2),
	BRONZE_CHAINBODY(ItemID.BRONZE_CHAINBODY, Metal.BRONZE, Skill.SMITHING, "Armour", 2),
	BRONZE_KITESHIELD(ItemID.BRONZE_KITESHIELD, Metal.BRONZE, Skill.SMITHING, "Armour", 2),
	BRONZE_2H_SWORD(ItemID.BRONZE_2H_SWORD, Metal.BRONZE, Skill.SMITHING, "Armour", 2),
	BRONZE_PLATELEGS(ItemID.BRONZE_PLATELEGS, Metal.BRONZE, Skill.SMITHING, "Armour", 2),
	BRONZE_PLATESKIRT(ItemID.BRONZE_PLATESKIRT, Metal.BRONZE, Skill.SMITHING, "Armour", 2),
	BRONZE_PLATEBODY(ItemID.BRONZE_PLATEBODY, Metal.BRONZE, Skill.SMITHING, "Armour", 4),

	IRON_SCIMITAR(ItemID.IRON_SCIMITAR, Metal.IRON, Skill.SMITHING, "Armour", 1),
	IRON_LONGSWORD(ItemID.IRON_LONGSWORD, Metal.IRON, Skill.SMITHING, "Armour", 1),
	IRON_FULL_HELM(ItemID.IRON_FULL_HELM, Metal.IRON, Skill.SMITHING, "Armour", 1),
	IRON_SQ_SHIELD(ItemID.IRON_SQ_SHIELD, Metal.IRON, Skill.SMITHING, "Armour", 1),
	IRON_CLAWS(ItemID.IRON_CLAWS, Metal.IRON, Skill.SMITHING, "Armour", 1),
	IRON_WARHAMMER(ItemID.IRON_WARHAMMER, Metal.IRON, Skill.SMITHING, "Armour", 2),
	IRON_BATTLEAXE(ItemID.IRON_BATTLEAXE, Metal.IRON, Skill.SMITHING, "Armour", 2),
	IRON_CHAINBODY(ItemID.IRON_CHAINBODY, Metal.IRON, Skill.SMITHING, "Armour", 2),
	IRON_KITESHIELD(ItemID.IRON_KITESHIELD, Metal.IRON, Skill.SMITHING, "Armour", 2),
	IRON_2H_SWORD(ItemID.IRON_2H_SWORD, Metal.IRON, Skill.SMITHING, "Armour", 2),
	IRON_PLATELEGS(ItemID.IRON_PLATELEGS, Metal.IRON, Skill.SMITHING, "Armour", 2),
	IRON_PLATESKIRT(ItemID.IRON_PLATESKIRT, Metal.IRON, Skill.SMITHING, "Armour", 2),
	IRON_PLATEBODY(ItemID.IRON_PLATEBODY, Metal.IRON, Skill.SMITHING, "Armour", 4),

	STEEL_SCIMITAR(ItemID.STEEL_SCIMITAR, Metal.STEEL, Skill.SMITHING, "Armour", 1),
	STEEL_LONGSWORD(ItemID.STEEL_LONGSWORD, Metal.STEEL, Skill.SMITHING, "Armour", 1),
	STEEL_FULL_HELM(ItemID.STEEL_FULL_HELM, Metal.STEEL, Skill.SMITHING, "Armour", 1),
	STEEL_SQ_SHIELD(ItemID.STEEL_SQ_SHIELD, Metal.STEEL, Skill.SMITHING, "Armour", 1),
	STEEL_CLAWS(ItemID.STEEL_CLAWS, Metal.STEEL, Skill.SMITHING, "Armour", 1),
	STEEL_WARHAMMER(ItemID.STEEL_WARHAMMER, Metal.STEEL, Skill.SMITHING, "Armour", 2),
	STEEL_BATTLEAXE(ItemID.STEEL_BATTLEAXE, Metal.STEEL, Skill.SMITHING, "Armour", 2),
	STEEL_CHAINBODY(ItemID.STEEL_CHAINBODY, Metal.STEEL, Skill.SMITHING, "Armour", 2),
	STEEL_KITESHIELD(ItemID.STEEL_KITESHIELD, Metal.STEEL, Skill.SMITHING, "Armour", 2),
	STEEL_2H_SWORD(ItemID.STEEL_2H_SWORD, Metal.STEEL, Skill.SMITHING, "Armour", 2),
	STEEL_PLATELEGS(ItemID.STEEL_PLATELEGS, Metal.STEEL, Skill.SMITHING, "Armour", 2),
	STEEL_PLATESKIRT(ItemID.STEEL_PLATESKIRT, Metal.STEEL, Skill.SMITHING, "Armour", 2),
	STEEL_PLATEBODY(ItemID.STEEL_PLATEBODY, Metal.STEEL, Skill.SMITHING, "Armour", 4),

	MITHRIL_SCIMITAR(ItemID.MITHRIL_SCIMITAR, Metal.MITHRIL, Skill.SMITHING, "Armour", 1),
	MITHRIL_LONGSWORD(ItemID.MITHRIL_LONGSWORD, Metal.MITHRIL, Skill.SMITHING, "Armour", 1),
	MITHRIL_FULL_HELM(ItemID.MITHRIL_FULL_HELM, Metal.MITHRIL, Skill.SMITHING, "Armour", 1),
	MITHRIL_SQ_SHIELD(ItemID.MITHRIL_SQ_SHIELD, Metal.MITHRIL, Skill.SMITHING, "Armour", 1),
	MITHRIL_CLAWS(ItemID.MITHRIL_CLAWS, Metal.MITHRIL, Skill.SMITHING, "Armour", 1),
	MITHRIL_WARHAMMER(ItemID.MITHRIL_WARHAMMER, Metal.MITHRIL, Skill.SMITHING, "Armour", 2),
	MITHRIL_BATTLEAXE(ItemID.MITHRIL_BATTLEAXE, Metal.MITHRIL, Skill.SMITHING, "Armour", 2),
	MITHRIL_CHAINBODY(ItemID.MITHRIL_CHAINBODY, Metal.MITHRIL, Skill.SMITHING, "Armour", 2),
	MITHRIL_KITESHIELD(ItemID.MITHRIL_KITESHIELD, Metal.MITHRIL, Skill.SMITHING, "Armour", 2),
	MITHRIL_2H_SWORD(ItemID.MITHRIL_2H_SWORD, Metal.MITHRIL, Skill.SMITHING, "Armour", 2),
	MITHRIL_PLATELEGS(ItemID.MITHRIL_PLATELEGS, Metal.MITHRIL, Skill.SMITHING, "Armour", 2),
	MITHRIL_PLATESKIRT(ItemID.MITHRIL_PLATESKIRT, Metal.MITHRIL, Skill.SMITHING, "Armour", 2),
	MITHRIL_PLATEBODY(ItemID.MITHRIL_PLATEBODY, Metal.MITHRIL, Skill.SMITHING, "Armour", 4),

	ADAMANT_SCIMITAR(ItemID.ADAMANT_SCIMITAR, Metal.ADAMANTITE, Skill.SMITHING, "Armour", 1),
	ADAMANT_LONGSWORD(ItemID.ADAMANT_LONGSWORD, Metal.ADAMANTITE, Skill.SMITHING, "Armour", 1),
	ADAMANT_FULL_HELM(ItemID.ADAMANT_FULL_HELM, Metal.ADAMANTITE, Skill.SMITHING, "Armour", 1),
	ADAMANT_SQ_SHIELD(ItemID.ADAMANT_SQ_SHIELD, Metal.ADAMANTITE, Skill.SMITHING, "Armour", 1),
	ADAMANT_CLAWS(ItemID.ADAMANT_CLAWS, Metal.ADAMANTITE, Skill.SMITHING, "Armour", 1),
	ADAMANT_WARHAMMER(ItemID.ADAMANT_WARHAMMER, Metal.ADAMANTITE, Skill.SMITHING, "Armour", 2),
	ADAMANT_BATTLEAXE(ItemID.ADAMANT_BATTLEAXE, Metal.ADAMANTITE, Skill.SMITHING, "Armour", 2),
	ADAMANT_CHAINBODY(ItemID.ADAMANT_CHAINBODY, Metal.ADAMANTITE, Skill.SMITHING, "Armour", 2),
	ADAMANT_KITESHIELD(ItemID.ADAMANT_KITESHIELD, Metal.ADAMANTITE, Skill.SMITHING, "Armour", 2),
	ADAMANT_2H_SWORD(ItemID.ADAMANT_2H_SWORD, Metal.ADAMANTITE, Skill.SMITHING, "Armour", 2),
	ADAMANT_PLATELEGS(ItemID.ADAMANT_PLATELEGS, Metal.ADAMANTITE, Skill.SMITHING, "Armour", 2),
	ADAMANT_PLATESKIRT(ItemID.ADAMANT_PLATESKIRT, Metal.ADAMANTITE, Skill.SMITHING, "Armour", 2),
	ADAMANT_PLATEBODY(ItemID.ADAMANT_PLATEBODY, Metal.ADAMANTITE, Skill.SMITHING, "Armour", 4),

	RUNE_SCIMITAR(ItemID.RUNE_SCIMITAR, Metal.RUNITE, Skill.SMITHING, "Armour", 1),
	RUNE_LONGSWORD(ItemID.RUNE_LONGSWORD, Metal.RUNITE, Skill.SMITHING, "Armour", 1),
	RUNE_FULL_HELM(ItemID.RUNE_FULL_HELM, Metal.RUNITE, Skill.SMITHING, "Armour", 1),
	RUNE_SQ_SHIELD(ItemID.RUNE_SQ_SHIELD, Metal.RUNITE, Skill.SMITHING, "Armour", 1),
	RUNE_CLAWS(ItemID.RUNE_CLAWS, Metal.RUNITE, Skill.SMITHING, "Armour", 1),
	RUNE_WARHAMMER(ItemID.RUNE_WARHAMMER, Metal.RUNITE, Skill.SMITHING, "Armour", 2),
	RUNE_BATTLEAXE(ItemID.RUNE_BATTLEAXE, Metal.RUNITE, Skill.SMITHING, "Armour", 2),
	RUNE_CHAINBODY(ItemID.RUNE_CHAINBODY, Metal.RUNITE, Skill.SMITHING, "Armour", 2),
	RUNE_KITESHIELD(ItemID.RUNE_KITESHIELD, Metal.RUNITE, Skill.SMITHING, "Armour", 2),
	RUNE_2H_SWORD(ItemID.RUNE_2H_SWORD, Metal.RUNITE, Skill.SMITHING, "Armour", 2),
	RUNE_PLATELEGS(ItemID.RUNE_PLATELEGS, Metal.RUNITE, Skill.SMITHING, "Armour", 2),
	RUNE_PLATESKIRT(ItemID.RUNE_PLATESKIRT, Metal.RUNITE, Skill.SMITHING, "Armour", 2),
	RUNE_PLATEBODY(ItemID.RUNE_PLATEBODY, Metal.RUNITE, Skill.SMITHING, "Armour", 4),
	;

	private final int itemID;
	private final Metal metal;
	private final Skill skill;
	private final String category;
	private final int bars;

	@Setter
	// Stores the item composition info we use since we don't operate on the game thread
	private ItemInfo itemInfo = null;

	private static final Multimap<Skill, ExperienceItem> SKILL_MAP = ArrayListMultimap.create();
	private static final Multimap<Metal, ExperienceItem> METAL_MAP = ArrayListMultimap.create();
	private static final Map<Integer, ExperienceItem> ITEM_ID_MAP = new HashMap<>();
	static
	{
		for (ExperienceItem i : values())
		{
			Skill s = i.getSkill();
			SKILL_MAP.put(s, i);
			Metal m = i.getMetal();
			METAL_MAP.put(m, i);
			ITEM_ID_MAP.put(i.getItemID(), i);
		}
	}

	ExperienceItem(int itemID, Metal metal, Skill skill)
	{
		this.itemID = itemID;
		this.metal = metal;
		this.category = "NA";
		this.skill = skill;
		this.bars = 0;
	}

	ExperienceItem(int itemID, Metal metal, Skill skill, String category, int bars)
	{
		this.itemID = itemID;
		this.metal = metal;
		this.category = category;
		this.skill = skill;
		this.bars = bars;
	}

	public static Collection<ExperienceItem> getBySkill(Skill skill)
	{
		Collection<ExperienceItem> items = SKILL_MAP.get(skill);
		if (items == null)
		{
			items = new ArrayList<>();
		}

		return items;
	}

	public static Collection<ExperienceItem> getByMetal(Metal metal)
	{
		Collection<ExperienceItem> items = METAL_MAP.get(metal);
		if (items == null)
		{
			items = new ArrayList<>();
		}

		return items;
	}

	public static ExperienceItem getByItemId(int id)
	{
		return ITEM_ID_MAP.get(id);
	}

	public static Collection<ExperienceItem> getByCategory(String category)
	{
		Collection<ExperienceItem> items = new ArrayList<>();

		for (Map.Entry<Integer, ExperienceItem> item : ITEM_ID_MAP.entrySet()) {
			if (item.getValue().category == category) {
				items.add(item.getValue());
			}
		}

		return items;
	}

	/**
	 * Attaches the Item Composition to each ExperienceItem on client initial load
	 * May be a better way to do this
	 * @param m ItemManager
	 */
	public static void prepareItemCompositions(ItemManager m)
	{
		for (ExperienceItem i : values())
		{
			if (i.itemInfo != null)
			{
				return;
			}

			final ItemComposition c = m.getItemComposition(i.getItemID());
			i.itemInfo = new ItemInfo(c.getName(), c.isStackable());
		}
	}

	public boolean isStackable()
	{
		return itemInfo != null && itemInfo.isStackable();
	}
}
