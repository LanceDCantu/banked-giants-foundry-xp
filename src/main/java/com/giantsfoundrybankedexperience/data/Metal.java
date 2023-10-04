package com.giantsfoundrybankedexperience.data;

/**
 * An enumeration of skills that a player can level.
 */
public enum Metal
{
	BRONZE("Bronze"),
	IRON("Iron"),
    STEEL("Steel"),
	MITHRIL("Mithril"),
	ADAMANTITE("Adamantite"),
	RUNITE("Runite")
    ;

	@Deprecated
	public static final Metal OVERALL = null;

	private final String name;

	Metal(String name)
	{
		this.name = name;
	}

	/**
	 * Gets the name of the skill.
	 *
	 * @return the skill name
	 */
	public String getName()
	{
		return name;
	}
}
