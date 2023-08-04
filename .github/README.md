<p align="center">
  <img src="https://i.imgur.com/jthR3lX.png">
</p>

<p align="center">
  <img src="https://img.shields.io/github/license/Outspending/AetherMC">
  <img src="https://img.shields.io/github/languages/code-size/Outspending/AetherMC">
  <img src="https://img.shields.io/github/repo-size/Outspending/AetherMC">
  <img src="https://img.shields.io/github/v/release/Outspending/AetherMC">
  <img src="https://img.shields.io/github/languages/top/Outspending/AetherMC">
  <img src="https://img.shields.io/github/last-commit/Outspending/AetherMC">
</p>

# What is AetherMC

AetherMC is an Asynchronous, Lightweight Minecraft Server made for Java developers. It's a bare bones version of Mojang's Minecraft Server, providing everything you need to create a Minecraft server quickly without any issues.

**Note**: AetherMC doesn't have a "Main Thread" anymore, so it's important to understand `Threads` and ensure your plugins are `Thread-Safe`. If you're not familiar with `Thread-Safety`, you can refer to this [guide](https://www.baeldung.com/java-thread-safety).

For more information about `Thread-Safety` and AetherMC's `API`, check out the [Wiki](https://github.com/Outspending/AetherMC/wiki). The wiki will guide you through each element of the API and is updated accordingly whenever changes are made. It aims to minimize questions and discussions in our Discord. If there's anything you'd like to add to the wiki, please DM me! 😄

AetherMC is currently in development, and if you're interested, you can get a build by contacting me through Discord. [My Profile](https://discord.com/users/982807217952677888)

> **AetherMC is a fork of Minestom**
>
> Minestom is a lightweight Minecraft server that doesn't contain any code from Mojang.
>
> You can find more details [HERE](https://minestom.net/)

| Version   | Support        |
| :---      |     :----:     |
| 1.20.x    | ✅             |
| 1.20      | ✅             |
| 1.19.x    | ❌             |
| 1.19      | ❌             |
| 1.18.x    | ❌             |
| 1.18      | ❌             |
| < 1.17    | ❌             |

## Features:

AetherMC provides many useful API's and Libraries at your disposal, maximizing the efficiency of creating your dream server!

### Multi-Threading

AetherMC supports multi-threading, offering more freedom with asynchronous operations compared to `Spigot` and `Paper`. The following actions are performed asynchronously:

- Setting Blocks
- Updating Blocks
- Updating Light
- World Loading
- Plugin Loading

If you want to learn more about multi-threading, check it out [HERE]().

### Plugin API

Our API offers a wide variety of brand new features that Minestom doesn't offer, such as:

- New Methods
- New Util Classes
- Leaderboard Implementation
- NPC Implementation
- Blockstate Manipulation
- Packet Manipulation
- World Creation

All of these features serve specific purposes, which is why they aren't provided through separate plugins.

### World Creator

The world creator in AetherMC uses `AnvilWorld` and allows easy modification of your world using `Dimensions`. With our world creator, you can create:

- Custom World Biomes (Without the need for restarting your server)
- Custom Dimensions

These features enable you to create an epic landscape for your server, offering nearly limitless possibilities.

#### Built-in NPC Support

AetherMC has built-in NPC support, eliminating the need for another plugin to handle NPCs.

### Settings

AetherMC offers over 100+ options to optimize your server and maximize its potential. Some of these settings include:

- Render Distance
- Loading Worlds Async
- Loading Worlds (This can be disabled for optimization purposes when loading your server)

These are just a few examples of what you can do with AetherMC!

### Packets

Our Packet API is very powerful, allowing you to easily `Receive`, `Listen`, and `Modify` packets. However, it comes with a performance cost and can create dangerous environments if not used carefully. Beware of packet overloading, as it can crash Minecraft clients easily.

### Commands

The command system in AetherMC is fully recoded from `Minestom` and no longer requires editing the `plugin.yml`. This saves time and is very easy to understand.

### Plans

I have many plans for AetherMC, but first, I need to finish the core features. After that, I will work on the following:

- A "WorldEdit" type API for creating your worlds quickly
- Spark Profiler Integration
- and more

I have lots of plans for this project, and I'm excited for it to be used by people to create their servers. Check out the to-do list below for the current progress on this project.

# To-Do List

This is the To-Do List for AetherMC before the first full release. You can build the project whenever and test it out for yourself!

Completed: 9/36

- Regions
    - [ ] Saving Regions
    - [ ] Loading Regions
    - [x] Region BoundingBoxes
    - [ ] Region Events
- Chunks
    - [ ] Chunk Saving
    - [ ] Saving Directional Blockstates
    - [x] Chunk Borders
- Plugins
    - [ ] Make Sure Plugin's Load Correctly
    - [ ] Expand the API for Developers
- Worlds
    - [ ] World Creator
    - [ ] World Saving
    - [ ] World Deleting
    - [ ] World Unloading
    - [ ] World Loading
- Lighting Engine
    - [x] Rewrite the Whole LightingEngine
    - [x] Fix Dark Chunks
    - [x] Parallel Light Updates
    - [x] Batch Light Updates
    - [x] Fix Lighting Updates (Takes like 10 seconds to compute 50 chunks of light)
    - [x] Add Light Source Blocks (Torch, Sea Lantern, etc.)
- Blocks
    - [ ] BlockStates
    - [ ] New Methods (#getWorld(), #getChunk(), #getChunkBorder(), #isInsideRegion(Region region), etc.)
    - [ ] Floating Torches, Signs, etc.
- Misc
    - [ ] Fix Ram Issue (Using up around 5GB without any plugins; this is due to the current LightingEngine though)
    - [ ] Add Console Commands
    - [ ] Opped Players
    - [ ] Admin Commands (/ban, /tempban, /mute, /tempmute, etc.)
    - [ ] Locations (Replaces `Pos` and `Point` in Minestom API)
    - [x] ParticleUtils? (Drawing Spheres, Triangles, and other shapes)
    - [ ] Block Drops (😭)
    - [ ] Leaderboards API
- Suggestions
    - [ ] Spark Profiler Integration
    - [ ] World Noise Generation (Not Currently in the main API)
    - [ ] Timespans
- Future Plans
    - [ ] Improved Plugin System
    - [ ] Custom Crafting Recipes
    - [ ] "WorldEdit" API

ETA: Unknown

# API

AetherMC contains lots of Utility classes and methods to help improve your server without 500 lines of code. The API provides many utility classes for **Math, Location, Chunk, LightingEngine, and much more** to help you.

Utility Classes:

- RegionUtils
    - This contains many QOL features to make regions in your server, including getting `borders, blocks, chunks, and more` from regions.
- ChunkUtils
    - This contains methods such as `#getBorder(), #clearChunk(boolean save), and more`.
    - Yes! There are chunk borders in AetherMC! These can be used for many use cases for developers.
- BlockUtils
    - This contains methods such as `#getDrops(Material material), and more`.
- MathUtils
    - This contains very easy-to-use methods to get `Locations` like never before.
- VectorUtils
    - This contains methods for rotating a vector and changing many of its properties like the Minestoms can't.

### Regions

Regions are like BoundingBoxes from `Spigot` with more features like `Events`. These can also be saved so they can be loaded once the server starts again.

### Chunks

Chunks now have "borders." Borders are the outline of a chunk (16x16). They act like regions, and you can get the border of a list of chunks, returning an outline border of all the chunks in the list.

### Commands

AetherMC has a fully recoded version of Minestom's command API, which is now Annotation-based. Creating commands no longer needs to be inside the `plugin.yml`, and they will be automatically registered in the background.

### Events

AetherMC has also recoded the events system, making it more friendly to the eye and easier to comprehend. Each event can be async or not. Here's an example: `AsyncBlockBreakEvent` or `BlockBreakEvent`.

### Blocks

The block system has also been fully recoded because it didn't meet the needs of this API. Blocks consist of many new methods that Minestom doesn't offer.

### Worlds

The world system in AetherMC is very user-friendly, and it offers many builders to let your imagination go wild with the world system.

### Biomes

Biomes haven't changed much from Minestom, and you can easily create a custom biome, which has been changed a bit to remove the frustration of all the builders you have to deal with.

# Credits

Thanks to all of these `Libraries` and `API's`:

- [YamlBeans](https://github.com/EsotericSoftware/yamlbeans): Utilizing YML files
- [Gson](https://github.com/google/gson): For Storage Files in JSON Format
- [The Minecraft Wiki](https://minecraft.fandom.com/wiki/Minecraft_Wiki): For all the very useful information about Loot Tables, Enchantments, etc.
- [The Minecraft Coalition](https://wiki.vg/Main_Page): For file formats

# Thanks

If you are considering AetherMC for your Minecraft Server, thank you! Hopefully, this project meets your expectations. I am fully willing to listen to your suggestions and make changes accordingly. ❤️
