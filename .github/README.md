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
AetherMC is an Asynchronous, Lightweight Minecraft Server. AetherMC is made for java developers and built for developers. This is a bare bones version of Mojang's Minecraft Server. It has everything you need to make a minecraft server. AetherMC has all the essentials for creating a minecraft server quickly without any issues.

In AetherMC there is no "Main Thread" anymore. If you don't know how to properly use `Threads` or making your plugins `Thread-Safe` this isn't the Minecraft Server for you. But the main API should handle things without any major issues if you don't fully know `Thread-Safety` here is a little guide on Thread-Safety ([HERE](https://www.baeldung.com/java-thread-safety))

Check out the [Wiki](https://github.com/Outspending/AetherMC/wiki) for more information about `Thread-Safety` and our `API`. This will help you thought each element of the API and is updated accordingly whenever we change anything. The wiki is made for helping people and to minimize the questions in our discord and discussions. Thanks, and if you want something adding to the wiki that isn't there please DM me :smile:

AetherMC is currently in development, you may be able to get a build from me if you ask nicely by contacting my through discord [My Profile](https://discord.com/users/982807217952677888)

> **AetherMC is a fork of Minestom**
>
> Minestom is a lightweight minecraft server.
> It doesn't contain any code from mojang
>
> You can find more details [HERE](https://minestom.net/)
>
## Features:
AetherMC provides many useful API's and Libraries at your disposal. This will maximize the efficiency of creating your dream server!

### Multi-Threading
AetherMC is multi-threading, you can do anything anywhere using our API. In `Spigot, and Paper` it isn't possible to have this much freedom with multi-threading. Now you can! AetherMC is utilizing this to its potental so your CPU doesn't only use 1 thread. Here's everything AetherMC is using asynchronously

- Setting Blocks
- Updating Blocks
- Updating Light
- World Loading
- Plugin Loading

If you aren't fully sure about multi-threading you can check it out [HERE]().
### Plugin API
Our API has a wide variaty of brand new things that minestom doesn't offer such as:

- New Methods
- New Util Classes
- Leaderboard Implementation
- NPC Implementation
- Blockstate Manipulation
- Packet Manipulation
- World Creation

All of these have a purpose which is why they aren't seperate using plugins.
### World Creator
The world creator in AetherMC is using `AnvilWorld` and can easily modify your world using `Dimensions` very simple with our world creator you can create:

- Custom World Biomes (Without the need of restarting your server)
- Custom Dimensions

These can be used for creating a very epic landscape for your server. You can do basically anything with this API and hopefully you'll use it to its full potential!
#### Built-in NPC Support
- AetherMC has built-in NPC support, no need for another plugin to do this for you.
### Settings
In AetherMC you have over 100+ options to optimize your server and use it to its potental! Some of these settings include:

- Render Distance
- Loading Worlds Async
- Loading Worlds (This can be disabled for optimization purposes when loading your server)

These are just the surface of what you can do with AetherMC!
### Packets
Our Packet API is very complex, you can easily `Recieve, Listen, and Modify` packets. This does come at a performance cost though. And can easily create some dangerous environments. With packet overloading you can crash minecraft clients easily. So be careful!
### Commands
The command system in AetherMC is completely from `Minestom` and i do expect to change it in a future update. This doesn't require you to edit the `plugin.yml` anymore. This does save some time and is very easy to understand.
### Plans
I have many plans for AetherMC but first i need to finish the core features then i will do the following:

- A "WorldEdit" type API for creating your worlds quickly
- Spark Profiler Integration
- and more

I have lots of plans for this and im excited for this to be used by people to create their servers. Check out the to-do list below for the current progress on this project
# To-Do List
This is the To-Do List for AetherMC before the first full release. You can build the project whenever and test it out for yourself!

Completed: 8/38

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
    - [x] Fix Lighting Updates (Literally takes like 10 seconds to compute 50 chunks of light)
    - [x] Add Light Source Blocks (Torch, Sea Lantern, ect)
- Blocks
    - [ ] BlockStates
    - [ ] New Methods (#getWorld(), #getChunk(), #getChunkBorder(), #isInsideRegion(Region region), ect)
    - [ ] Floating Torches, Signs, ect
- Events
    - [ ] Events rewrite
    - [ ] `Async` and `Sync` events (`AsyncBlockBreakEvent` and `BlockBreakEvent`)
- Misc
    - [ ] Fix Ram Issue (Using up around 5GB without any plugins, this is due to the current LightingEngine though)
    - [ ] Add Console Commands
    - [ ] Opped Players
    - [ ] Admin Commands (/ban, /tempban, /mute, /tempmute, ect)
    - [ ] Locations (Replaces `Pos` and `Point` in Minestom API)
    - [ ] ParticleUtils? (Drawing Spheres, Triangles, and other shapes)
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
AetherMC contains lots of Utility classes and methods to help improve your server without 500 lines of code.
The API provides many utility classes for **Math, Location, Chunk, LightingEngine, and much more** to help you.
Utility Classes:
- RegionUtils
    - This contains many QOL features to make regions in your server. By getting `borders, blocks, chunks, and more` from regions
- ChunkUtils
    - This contains methods such as `#getBorder(), #clearChunk(boolean save), and more`
    - Yes! There are chunk borders in AetherMC! Which can be used for many use cases for developers
- BlockUtils
    - This contains methods such as `#getDrops(Material material), and more`
- MathUtils
    - This contains very easy to use methods to get `Locations` like never before
- VectorUtils
    - This contains methods for rotating a vector, and changing many of its properties like the Minestoms can't
### Regions
Regions are like BoundingBoxes from `Spigot` with more featues like `Events`. These can also be saved so they can be loaded once the server starts again
### Chunks
Chunks now have "borders" borders are the outline of a chunk `16x16`. They act like regions, you can get the border of a list of chunks and it will return a outline border of all the chunks in the list
### Commands
AetherMC has a fully recoded version of Minestoms command API which is now Annotation based. Creating commands no longer needs to be inside the `plugin.yml` and will automaticlly be registered in the background
### Events
AetherMC has also recoded the events system. It is more friendly to the eye and easier to comprehend. Each event can be async or not. Here's an example: `AsyncBlockBreakEvent` or `BlockBreakEvent`
### Blocks
The block system has also been fully recoded because it didn't meet the needs of this API, blocks consist of many new methods that Minestom doesn't offer
### Worlds
The world system in AetherMC is very user friendly, it has many builders so you can let your imagination go wild with the world system
### Biomes
Biomes haven't changed much from Minestom you can easily create a custom biome which has been changed a bit to remove the frustation of all the builders you have to deal with
# Credits
Thanks to all of these `Libraries` and `API's`:

- [YamlBeans](https://github.com/EsotericSoftware/yamlbeans): Utilizing YML files
- [Gson](https://github.com/google/gson): For Storage Files in JSON Format
- [The Minecraft Wiki](https://minecraft.fandom.com/wiki/Minecraft_Wiki): For all the very useful information about Loot Tables, Enchantements, ect
- [The Minecraft Coalition](https://wiki.vg/Main_Page): For file formats

# Thanks
If you are considering this as your pick for your Minecraft Server thank you, hopefully this project meets the needs of your expectations, i am fully willing you listen to your suggestions and change whatever accordingly :heart: