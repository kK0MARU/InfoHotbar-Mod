# InfoHotbar-Mod

# 🧭 InfoHotbar

**Minimalist. Customizable. Informative.**

InfoHotbar is a lightweight client-side Fabric mod that displays essential information directly above your hotbar. Designed to replace the cluttered F3 debug screen, it gives you exactly what you need for survival and exploration, blending perfectly with the vanilla interface.

![InfoHotbar Screenshot](https://i.imgur.com/8cS5zDP.png)

## ✨ Key Features

*   **📍 Real-time Coordinates:** Display X, Y, Z cleanly without opening the debug menu.
*   **🎨 Fully Customizable:** Change text color (supports RGB, Hex, and Transparency), adjust position (X/Y offsets), and toggle visibility.
*   **🌍 Smart Biome Names:** Automatically displays the biome name in your game's language (supports English, Russian, and any other Minecraft language).
*   **⚡ Smart Action Bar:** The HUD automatically hides when vanilla messages (like "You can only sleep at night") appear, preventing text overlap.
*   **🛠️ Extensive Placeholders:** Build your own HUD layout using simple tags.

## 📝 Available Placeholders

Customize the displayed text in the settings menu (`Format String`):

| Placeholder | Description | Example |
| :--- | :--- | :--- |
| `{x} {y} {z}` | Your current coordinates | `100 64 -200` |
| `{dir}` | Cardinal direction (Localized) | `N`, `S`, `E`, `W` |
| `{biome}` | Current biome name (Localized) | `Plains`, `Desert` |
| `{day}` | Days played in the world | `1`, `42` |
| `{gametime}` | In-game time (HH:MM) | `06:30` |
| `{realtime}` | Real-world PC time | `21:45` |
| `{light}` | Block light level (useful for spawn proofing) | `0`, `15` |
| `{nether}` | Smart Nether coordinate conversion | `N: 12 12` (in Overworld) |
| `{fps}` | Current Frames Per Second | `144` |

## ⚙️ Configuration

Press your **Mod Menu** button to access the settings screen (requires Mod Menu & Cloth Config).

*   **Format String:** Define exactly what you want to see.
    *   *Default:* `{x} | {y} | {z}`
    *   *Rich Example:* `{gametime} | {biome} | Light: {light}`
*   **Offsets:** Move the text up, down, left, or right to fit your resource pack.
*   **Colors:** Choose any color using the color picker or Hex codes.

## 📥 Installation

1.  Download and install **Fabric Loader**.
2.  Download **InfoHotbar**.
3.  **Required Dependencies:**
    *   [Fabric API](https://modrinth.com/mod/fabric-api)
    *   [Cloth Config API](https://modrinth.com/mod/cloth-config) (For the settings screen)
    *   [Mod Menu](https://modrinth.com/mod/modmenu) (To open settings)
4.  Place the `.jar` files into your `mods` folder.

## 🌐 Localization

The mod is fully localized for:
*   🇺🇸 English (en_us)
*   🇷🇺 Russian (ru_ru)
*   *Biome names and directions adapt to your game language automatically.*

## 📜 License

This mod is available under the **MIT License**. You are free to use it in any modpack.

