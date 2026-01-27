package net.starrymoon92.infohotbar;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.Text;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ModConfig config = ModConfig.get();

            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Text.translatable("mod.infohotbar.name"));

            ConfigEntryBuilder entryBuilder = builder.entryBuilder();
            ConfigCategory general = builder.getOrCreateCategory(Text.translatable("option.infohotbar.general"));

            general.addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.infohotbar.enable"), config.enabled)
                    .setDefaultValue(true)
                    .setSaveConsumer(newValue -> config.enabled = newValue)
                    .build());

            general.addEntry(entryBuilder.startColorField(Text.translatable("option.infohotbar.color"), config.color)
                    .setDefaultValue(0xFFFFFF)
                    .setSaveConsumer(newValue -> config.color = newValue)
                    .build());

            general.addEntry(entryBuilder.startIntField(Text.translatable("option.infohotbar.y_offset"), config.yOffset)
                    .setDefaultValue(-70)
                    .setTooltip(Text.translatable("option.infohotbar.y_offset.tooltip"))
                    .setSaveConsumer(newValue -> config.yOffset = newValue)
                    .build());

            general.addEntry(entryBuilder.startIntField(Text.translatable("option.infohotbar.x_offset"), config.xOffset)
                    .setDefaultValue(0)
                    .setSaveConsumer(newValue -> config.xOffset = newValue)
                    .build());

            general.addEntry(entryBuilder.startStrField(Text.translatable("option.infohotbar.format"), config.formatString)
                    .setDefaultValue("{x} | {y} | {z}")
                    .setTooltip(Text.translatable("option.infohotbar.format.tooltip"))
                    .setSaveConsumer(newValue -> config.formatString = newValue)
                    .build());

            builder.setSavingRunnable(ModConfig::save);

            return builder.build();
        };
    }
}