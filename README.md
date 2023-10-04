# Giants Foundry Banked Experience

All credits to [TheStoneTurtle](https://github.com/TheStonedTurtle) for the basis of this plugin: ([Banked Experience Plugin](https://github.com/TheStonedTurtle/banked-experience))

This plugin is an extension to the Banked Experience Plugin, specialized to the calculate the amount of experience you have banked for giants foundry taking into account ores, bars, and scrapable items.


## How It Works
This plugin searches through your bank for specific items usable in the crucible in giants foundry and calculates the estimated experience, reputation and GP earned from using them. 

You must view your bank, or other inventories, while the plugin is enabled for it to register what items you have.
This data does not persist between client sessions so closing the client will require you to redo this step.

## Consideration
- This plugin is only an estimate and can vary based on your commissions
- This calculator assumes no mistakes during play
- This calculator does not take into account the required ammount of coal needed to smelt the bars
- The final xp calcation includes smelting xp

## Config Options
Like most plugins the Banked Experience plugin includes some config options to help customize its functionality.
See the relative section for each config option for an in-depth description.

| Name 														| Enabled by Default|
| :--- 														| :-----: |
| [Respect level requirements](#respect-level-requirements)	| &check; |
| [Include player inventory](#include-player-inventory)		| X |
| [Include looting bag](#include-looting-bag)				| X |


### Respect level requirements
<details>
<summary>Details</summary>
<p>

This option is **Enabled** by default

Most activities require a specific level in order to be completed. When this option is enabled activities which you lack
the required level for will be excluded from the list, when disabled all activities will be available regardless of your current level.

**Level limitation is based off current level and does not account for levels gained from other banked experience**
</p>
</details>

### Include player inventory
<details>
<summary>Details</summary>
<p>

This option is **Disabled** by default.

**It is highly recommend to keep this option disabled as the Inventory changes frequently. This feature was added for UIM who don't have a bank**

Controls whether the items inside your Inventory will be included in the calculations. 
</p>
</details>

### Include looting bag
<details>
<summary>Details</summary>
<p>

This option is **Disabled** by default

Controls whether the items stored inside your Looting Bag will be included in the calculations.
This feature requires checking your looting bag, using items on it or picking up items while its opened does not work.
</p>
</details>


## FAQ
<details>
  <summary>Q: How do I ignore specific items from the calculator?</summary>
  <p>

You can right-click items inside the grid to Ignore/Include them. Ignored items will have a red background color.
  </p>
</details>

<details>
  <summary>Q: I only want to use part of my banked resources, how can i specify how many of a specific item I want to use?</summary>
  <p>

To limit the amount of a specific items you should withdraw all but the amount you want to calculate from your bank. Items in your inventory are not included in the calculations by default
  </p>
</details>

<details>
  <summary>Q: I visited my bank but the UI hasn't updated, what do I do?</summary>
  <p>

The UI does not automatically update when your bank content changes, you must change skills for the updates to be applied or click the Refresh Button underneath the item grid. This was done intentionally to minimize performance issues from constantly refreshing the UI.
  </p>
</details>

<details>
  <summary>Q: How can I limit experience based on the secondaries I have banked?</summary>
  <p>

This is not currently possible. Some secondaries, like coal, are split between multiple activities which would require a prioritization system that I'm not sure how to implement.  
  </p>
</details>

<details>
  <summary>Q: What does the orange background color inside the item grid mean?</summary>
  <p>

This means the current selected activity is RNG based and may affect the accuracy of the calculations. 
  </p>
</details>