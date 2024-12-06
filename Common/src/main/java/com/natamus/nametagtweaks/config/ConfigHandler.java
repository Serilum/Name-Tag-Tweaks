package com.natamus.nametagtweaks.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.nametagtweaks.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean droppedNameTagbyEntityKeepsNameValue = true;
	@Entry public static boolean enableNameTagCommand = true;
	@Entry public static boolean nameTagCommandReplaceUnderscoresWithSpaces = true;

	public static void initConfig() {
		configMetaData.put("droppedNameTagbyEntityKeepsNameValue", Arrays.asList(
			"If enabled, any name tag that drops on an entity's death will keep its named value."
		));
		configMetaData.put("enableNameTagCommand", Arrays.asList(
			"If enabled, adds the /nametag <name> command. Used to change the name value without an anvil."
		));
		configMetaData.put("nameTagCommandReplaceUnderscoresWithSpaces", Arrays.asList(
			"If enabled, replaces underscores with spaces when using the /nametag command."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}