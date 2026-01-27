package net.starrymoon92.infohotbar;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HotBarInfo implements ModInitializer {
	public static final String MOD_ID = "infohotbar";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("InfoHotbar initialized!");
	}
}