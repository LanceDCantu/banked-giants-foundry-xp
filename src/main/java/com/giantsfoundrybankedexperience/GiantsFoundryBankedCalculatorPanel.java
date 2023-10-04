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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.List;
import java.awt.event.ItemEvent;
import java.util.Collection;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Skill;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.game.ItemManager;
import net.runelite.client.game.SkillIconManager;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.util.AsyncBufferedImage;

import com.giantsfoundrybankedexperience.components.combobox.ComboBoxIconEntry;
import com.giantsfoundrybankedexperience.components.combobox.ComboBoxIconListRenderer;
import com.giantsfoundrybankedexperience.components.textinput.UICalculatorInputArea;
import com.giantsfoundrybankedexperience.data.ExperienceItem;
import com.giantsfoundrybankedexperience.data.Metal;

@Slf4j
public class GiantsFoundryBankedCalculatorPanel extends PluginPanel
{
	private final GiantsFoundryBankedCalculator calculator;

	public GiantsFoundryBankedCalculatorPanel(Client client, GiantsFoundryBankedExperienceConfig config, SkillIconManager skillIconManager,
								ItemManager itemManager, ConfigManager configManager)
	{
		super();

		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new GridBagLayout());

		final UICalculatorInputArea inputs = new UICalculatorInputArea();
		inputs.setBorder(new EmptyBorder(15, 0, 15, 0));
		inputs.setBackground(ColorScheme.DARK_GRAY_COLOR);

		inputs.getUiFieldTargetXP().setEditable(false);
		inputs.getUiFieldTargetLevel().setEditable(false);

		calculator = new GiantsFoundryBankedCalculator(inputs, client, config, itemManager, configManager);

		// Create the Skill dropdown with icons
		final JComboBox<ComboBoxIconEntry> barOneDropdown = new JComboBox<>();
		barOneDropdown.setFocusable(false);
		barOneDropdown.setForeground(Color.WHITE);
		barOneDropdown.setMaximumRowCount(6);
		barOneDropdown.setBorder(new EmptyBorder(0, 0, 0, 0));
		final ComboBoxIconListRenderer barOneRenderer = new ComboBoxIconListRenderer();
		barOneRenderer.setDefaultText("Select Metal #1 ...");
		barOneDropdown.setRenderer(barOneRenderer);

		// Create the Skill dropdown with icons
		final JComboBox<ComboBoxIconEntry> barTwoDropdown = new JComboBox<>();
		barTwoDropdown.setFocusable(false);
		barTwoDropdown.setForeground(Color.WHITE);
		barTwoDropdown.setMaximumRowCount(6);
		barTwoDropdown.setBorder(new EmptyBorder(0, 0, 0, 0));
		final ComboBoxIconListRenderer barTwoRenderer = new ComboBoxIconListRenderer();
		barTwoRenderer.setDefaultText("Select Metal #2 ...");
		barTwoDropdown.setRenderer(barTwoRenderer);

		Collection<ExperienceItem> barTypes = ExperienceItem.getByCategory("Bars");

		for (final ExperienceItem bar : barTypes)
		{
			final AsyncBufferedImage img = itemManager.getImage(bar.getItemID(), 1, false);
			final String metalName = StringUtils.substringBefore(bar.name(), "_");
			final String name = metalName.substring(0,1) + metalName.substring(1).toLowerCase();
			final ComboBoxIconEntry entry = new ComboBoxIconEntry(new ImageIcon(img), name, bar.getMetal());
			barOneDropdown.addItem(entry);
			barTwoDropdown.addItem(entry);
		}

		barOneDropdown.setSelectedIndex(-1);
		barTwoDropdown.setSelectedIndex(-1);

		barOneDropdown.addItemListener(e ->
		{
			if (e.getStateChange() == ItemEvent.SELECTED)
			{
				final ComboBoxIconEntry source = (ComboBoxIconEntry) e.getItem();
				if (source.getData() instanceof Metal)
				{
					final Metal metal = (Metal) source.getData();
					this.calculator.open(Skill.SMITHING, metal, null);
				}
			}
		});

		barTwoDropdown.addItemListener(e ->
		{
			if (e.getStateChange() == ItemEvent.SELECTED)
			{
				final ComboBoxIconEntry source = (ComboBoxIconEntry) e.getItem();
				if (source.getData() instanceof Metal)
				{
					final Metal metal = (Metal) source.getData();
					this.calculator.open(Skill.SMITHING, null, metal);
				}
			}
		});

		final GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 0;
		c.gridy = 0;
		
		add(inputs, c);
		c.gridy++;
		add(barOneDropdown, c);
		c.gridy++;
		add(barTwoDropdown, c);
		c.gridy++;
		add(calculator, c);
	}

	void setInventoryMap(final int inventoryId, final Map<Integer, Integer> map)
	{
		calculator.setInventoryMap(inventoryId, map);
	}

	void resetInventoryMaps()
	{
		calculator.resetInventoryMaps();
	}
}
