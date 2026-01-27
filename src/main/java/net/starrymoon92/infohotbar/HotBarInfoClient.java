package net.starrymoon92.infohotbar;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.LightType;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class HotBarInfoClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModConfig.load();
        HudRenderCallback.EVENT.register(this::onRenderHud);
    }

    private void onRenderHud(DrawContext context, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();
        ModConfig config = ModConfig.get();

        if (!config.enabled || client.player == null || client.options.hudHidden || client.getDebugHud().shouldShowDebugHud()) {
            return;
        }

        BlockPos pos = client.player.getBlockPos();

        long time = client.world.getTimeOfDay();
        long hours = (time / 1000 + 6) % 24;
        long minutes = (time % 1000) * 60 / 1000;
        String timeStr = String.format("%02d:%02d", hours, minutes);
        long days = client.world.getTimeOfDay() / 24000;

        String realTimeStr = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));

        int lightLevel = client.world.getLightLevel(LightType.BLOCK, pos);

        String netherCoords = "N/A";
        boolean isNether = client.world.getRegistryKey().getValue().toString().equals("minecraft:the_nether");
        boolean isOverworld = client.world.getRegistryKey().getValue().toString().equals("minecraft:overworld");

        if (isOverworld) {
            netherCoords = String.format("N: %d %d", pos.getX() / 8, pos.getZ() / 8);
        } else if (isNether) {
            netherCoords = String.format("Ow: %d %d", pos.getX() * 8, pos.getZ() * 8);
        }

        String biome = "Unknown";
        if (client.world != null) {
            var biomeEntry = client.world.getBiome(pos);
            if (biomeEntry.getKey().isPresent()) {
                Identifier biomeId = biomeEntry.getKey().get().getValue();
                String translationKey = Util.createTranslationKey("biome", biomeId);
                biome = Text.translatable(translationKey).getString();
            }
        }

        Direction direction = client.player.getHorizontalFacing();
        String dirText = "";
        switch (direction) {
            case NORTH -> dirText = Text.translatable("text.infohotbar.direction.north").getString();
            case SOUTH -> dirText = Text.translatable("text.infohotbar.direction.south").getString();
            case EAST -> dirText = Text.translatable("text.infohotbar.direction.east").getString();
            case WEST -> dirText = Text.translatable("text.infohotbar.direction.west").getString();
        }

        String text = config.formatString;
        text = text.replace("{x}", String.valueOf(pos.getX()));
        text = text.replace("{y}", String.valueOf(pos.getY()));
        text = text.replace("{z}", String.valueOf(pos.getZ()));
        text = text.replace("{day}", String.valueOf(days));
        text = text.replace("{gametime}", timeStr);
        text = text.replace("{realtime}", realTimeStr);
        text = text.replace("{light}", String.valueOf(lightLevel));
        text = text.replace("{nether}", netherCoords);
        text = text.replace("{biome}", biome);
        text = text.replace("{fps}", String.valueOf(client.getCurrentFps()));
        text = text.replace("{dir}", dirText);

        TextRenderer textRenderer = client.textRenderer;
        int screenWidth = context.getScaledWindowWidth();
        int screenHeight = context.getScaledWindowHeight();
        int textWidth = textRenderer.getWidth(text);

        int drawX = (screenWidth - textWidth) / 2 + config.xOffset;
        int drawY = screenHeight + config.yOffset;

        int actualColor = config.color | 0xFF000000;

        context.drawTextWithShadow(textRenderer, text, drawX, drawY, actualColor);
    }
}