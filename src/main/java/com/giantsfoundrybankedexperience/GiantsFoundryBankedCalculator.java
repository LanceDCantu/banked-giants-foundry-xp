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
package com.giantsfoundrybankedexperience;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Experience;
import net.runelite.api.Skill;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.DynamicGridLayout;
import net.runelite.client.util.Text;
import com.giantsfoundrybankedexperience.components.GridItem;
import com.giantsfoundrybankedexperience.components.SelectionGrid;
import com.giantsfoundrybankedexperience.components.SelectionListener;
import com.giantsfoundrybankedexperience.components.textinput.UICalculatorInputArea;
import com.giantsfoundrybankedexperience.data.BankedItem;
import com.giantsfoundrybankedexperience.data.ExperienceItem;
import com.giantsfoundrybankedexperience.data.Metal;

@Slf4j
public class GiantsFoundryBankedCalculator extends JPanel
{
	public static final DecimalFormat XP_FORMAT_COMMA = new DecimalFormat("#,###.#");

	private final Client client;
	@Getter
	private final GiantsFoundryBankedExperienceConfig config;
	private final UICalculatorInputArea uiInput;
	@Getter
	private final ItemManager itemManager;
	private final ConfigManager configManager;

	private final Map<ExperienceItem, BankedItem> bankedItemMap = new LinkedHashMap<>();
	private final JLabel totalXpLabel = new JLabel();
	private final JLabel xpToNextLevelLabel = new JLabel();
	private final JLabel effectBarsBankedMetal1 = new JLabel();
	private final JLabel effectBarsBankedMetal2 = new JLabel();
	private SelectionGrid itemGrid = new SelectionGrid();
	private final JButton refreshBtn;

	// Store items from all sources in the same map
	private final Map<Integer, Integer> currentMap = new HashMap<>();
	// keep sources separate for recreating currentMap when one updates
	private final Map<Integer, Map<Integer, Integer>> inventoryMap = new HashMap<>();

	@Getter
	private final Set<String> ignoredItems;

	@Getter
	private Skill currentSkill;

	@Getter
	private Metal currentMetal1 = null;

	@Getter
	private Metal currentMetal2 = null;

	@Getter
	private int skillLevel, skillExp, endLevel, endExp;

	GiantsFoundryBankedCalculator(UICalculatorInputArea uiInput, Client client, GiantsFoundryBankedExperienceConfig config,
					ItemManager itemManager, ConfigManager configManager)
	{
		this.uiInput = uiInput;
		this.client = client;
		this.config = config;
		this.itemManager = itemManager;
		this.configManager = configManager;

		this.ignoredItems = new HashSet<>(Text.fromCSV(config.ignoredItems()));

		setLayout(new DynamicGridLayout(0, 1, 0, 5));

		this.refreshBtn = new JButton("Refresh Calculator");
		refreshBtn.setFocusable(false);
		refreshBtn.addMouseListener((new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				if (e.getButton() == MouseEvent.BUTTON1)
				{
					open(currentSkill, currentMetal1, currentMetal2, true);
				}
			}
		}));

		itemGrid.setSelectionListener(new SelectionListener()
		{
			@Override
			public boolean selected(BankedItem item)
			{
				return true;
			}

			@Override
			public boolean ignored(BankedItem item)
			{
				toggleIgnoreBankedItem(item);

				// Update Config
				config.ignoredItems(Text.toCSV(ignoredItems));

				// Update UI
				calculateBankedXpTotal();

				return true;
			}
		});
	}

	/**
	 * opens the Banked Calculator for this skill
	 */
	void open(final Skill newSkill, final Metal newMetal1, final Metal newMetal2)
	{
		open(newSkill, newMetal1, newMetal2, false);
	}

	/**
	 * opens the Banked Calculator for this skill
	 */
	void open(final Skill newSkill, final Metal newMetal1, final Metal newMetal2, final boolean refresh)
	{
		
		this.currentSkill = newSkill;
		this.currentMetal1 = newMetal1 != null ? newMetal1 : currentMetal1;
		this.currentMetal2 = newMetal2 != null ? newMetal2 : currentMetal2;
		removeAll();
		itemGrid.setSelectedItem(null);
		refreshBtn.setVisible(false);

		if (currentMap.size() <= 0)
		{
			add(new JLabel("Please visit a bank!", JLabel.CENTER));
			add(refreshBtn);
			revalidate();
			repaint();
			return;
		}

		skillLevel = client.getRealSkillLevel(currentSkill);
		skillExp = client.getSkillExperience(currentSkill);
		endLevel = skillLevel;
		endExp = skillExp;

		uiInput.setCurrentLevelInput(skillLevel);
		uiInput.setCurrentXPInput(skillExp);
		uiInput.setTargetLevelInput(endLevel);
		uiInput.setTargetXPInput(endExp);

		recreateBankedItemMap();
		recreateItemGrid();

		// This should only be null if there are no items in their bank for this skill
		if (itemGrid.getSelectedItem() == null)
		{
			add(new JLabel( "Couldn't find any items for this skill.", JLabel.CENTER));
		}
		else
		{
			add(totalXpLabel);
			add(xpToNextLevelLabel);
			add(effectBarsBankedMetal1);
			add(effectBarsBankedMetal2);
			add(itemGrid);
		}

		add(refreshBtn);

		revalidate();
		repaint();
	}

	private void recreateBankedItemMap()
	{
		bankedItemMap.clear();

		final Collection<ExperienceItem> items = new ArrayList<>();

		if(currentMetal1 == null && currentMetal2 == null) {
			final Collection<ExperienceItem> smithingItems = ExperienceItem.getBySkill(Skill.SMITHING);
			items.addAll(smithingItems);
		}

		if(currentMetal1 != null) {
			final Collection<ExperienceItem> metal1Items = ExperienceItem.getByMetal(currentMetal1);
			items.addAll(metal1Items);
		}

		if(currentMetal2 != null) {
			final Collection<ExperienceItem> metal2Items = ExperienceItem.getByMetal(currentMetal2);
			items.addAll(metal2Items);
		}

		log.debug("Experience items for the {} Skill: {}", currentSkill.getName(), items);

		for (final ExperienceItem item : items)
		{
			// Convert to bankedItems
			final BankedItem banked = new BankedItem(item, currentMap.getOrDefault(item.getItemID(), 0));
			bankedItemMap.put(item, banked);

			/*Activity a = item.getSelectedActivity();
			if (a == null)
			{
				final List<Activity> activities = Activity.getByExperienceItem(item, skillLevel);
				if (activities.size() == 0)
				{
					item.setSelectedActivity(null);
					continue;
				}

				item.setSelectedActivity(activities.get(0));
				a = activities.get(0);
			}*/
		}
		log.debug("Banked Item Map: {}", bankedItemMap);
	}

	/**
	 * Populates the detailContainer with the necessary BankedItemPanels
	 */
	private void recreateItemGrid()
	{
		// Selection grid will only display values with > 0 items
		itemGrid.recreateGrid(this, bankedItemMap.values(), itemManager);

		calculateBankedXpTotal();
	}

	/**
	 * Calculates total item quantity accounting for backwards linked items
	 * @param item starting item
	 * @return item qty including linked items
	 */
	public int getItemQty(final BankedItem item)
	{
		int qty = item.getQty();

		if (!config.cascadeBankedXp())
		{
			return qty;
		}

		return qty;
	}

	private void calculateBankedXpTotal()
	{
		double total = 0.0;
		int metal1BarTotal = 0;
		int metal2BarTotal = 0;
		for (final GridItem i : itemGrid.getPanelMap().values())
		{
			if (i.isIgnored())
			{
				continue;
			}

			final BankedItem bi = i.getBankedItem();
			total += getItemQty(bi) * 10;//BIG HUGE LINE
			bi.getItem().getBars();
			if (bi.getItem().getMetal() == currentMetal1) {
				metal1BarTotal += (bi.getItem().getBars() * getItemQty(bi));
			}
			if (bi.getItem().getMetal() == currentMetal2) {
				metal2BarTotal += (bi.getItem().getBars() * getItemQty(bi));
			}
		}

		endExp = Math.min(Experience.MAX_SKILL_XP, (int) (skillExp + total));
		endLevel = Experience.getLevelForXp(endExp);

		totalXpLabel.setText("Total Banked: " + XP_FORMAT_COMMA.format(total) + "xp");
		if (currentMetal1 != null) {
			effectBarsBankedMetal1.setText(currentMetal1.getName() + " Bars Banked: " + metal1BarTotal);
		}
		if (currentMetal2 != null) {
			effectBarsBankedMetal2.setText(currentMetal2.getName() + " Bars Banked: " + metal2BarTotal);	
		}
		uiInput.setTargetLevelInput(endLevel);
		uiInput.setTargetXPInput(endExp);

		final int nextLevel = Math.min(endLevel + 1, 126);
		final int nextLevelXp = Experience.getXpForLevel(nextLevel) - endExp;
		xpToNextLevelLabel.setText("Level " + nextLevel + " requires: " + XP_FORMAT_COMMA.format(nextLevelXp) + "xp");

		revalidate();
		repaint();
	}

	public int getItemQtyFromBank(final int id)
	{
		return currentMap.getOrDefault(id, 0);
	}

	public void resetInventoryMaps()
	{
		inventoryMap.clear();
		updateCurrentMap();
		if (currentSkill == null)
		{
			return;
		}
		open(currentSkill, currentMetal1, currentMetal2, true);

		// Reset experience level stuff
		uiInput.setCurrentLevelInput(1);
		uiInput.setCurrentXPInput(0);
		uiInput.setTargetLevelInput(1);
		uiInput.setTargetXPInput(0);
	}

	void setInventoryMap(final int inventoryId, final Map<Integer, Integer> map)
	{
		inventoryMap.put(inventoryId, map);
		updateCurrentMap();
		refreshBtn.setVisible(true);
	}

	private void updateCurrentMap()
	{
		currentMap.clear();
		for (final Map<Integer, Integer> map : inventoryMap.values())
		{
			for (final int id : map.keySet())
			{
				final int qty = map.get(id) + currentMap.getOrDefault(id, 0);
				currentMap.put(id, qty);
			}
		}
	}

	private void ignoreBankedItem(BankedItem item, boolean ignored)
	{
		final String name = item.getItem().name();
		if (ignored)
		{
			ignoredItems.add(name);
		}
		else
		{
			ignoredItems.remove(name);
		}
	}

	private void toggleIgnoreBankedItem(BankedItem item)
	{
		boolean ignore = !ignoredItems.contains(item.getItem().name());
		ignoreBankedItem(item, ignore);
	}

	public void setIgnoreAllItems(boolean ignored)
	{
		itemGrid.getPanelMap().values().forEach((i) ->
		{
			ignoreBankedItem(i.getBankedItem(), ignored);
			i.setIgnore(ignored);
		});

		// Update Config
		config.ignoredItems(Text.toCSV(ignoredItems));

		// Update UI
		calculateBankedXpTotal();
	}
}
