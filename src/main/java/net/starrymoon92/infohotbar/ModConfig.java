package net.starrymoon92.infohotbar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ModConfig {

    private static ModConfig INSTANCE;
    private static final File CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve("infohotbar.json").toFile();

    public boolean enabled = true;
    public int color = 0xFFFFFF;
    public int xOffset = 0;
    public int yOffset = -70;

    public String formatString = "{x} | {y} | {z}";

    public static ModConfig get() {
        if (INSTANCE == null) {
            load();
        }
        return INSTANCE;
    }

    public static void load() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                INSTANCE = new Gson().fromJson(reader, ModConfig.class);
            } catch (IOException e) {
                HotBarInfo.LOGGER.error("Failed to load config", e);
                INSTANCE = new ModConfig();
            }
        } else {
            INSTANCE = new ModConfig();
            save();
        }
    }

    public static void save() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            new GsonBuilder().setPrettyPrinting().create().toJson(INSTANCE, writer);
        } catch (IOException e) {
            HotBarInfo.LOGGER.error("Failed to save config", e);
        }
    }
}