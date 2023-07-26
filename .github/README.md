# What is AetherMC
AetherMC is an Asynchronous, Lightweight Minecraft Server. AetherMC is made for java developers and built for developers. This is a bare bones version of Mojang's Minecraft Server. It has everything you need to make a minecraft server. AetherMC has all the essentials for creating a minecraft server quickly without any issues.

In AetherMC there is no "Main Thread" anymore. If you don't know how to properly use `Threads` or making your plugins `Thread-Safe` this isn't the Minecraft Server for you. But the main API should handle things without any major issues if you don't fully know `Thread-Safety` here is a little guide on Thread-Safety ([HERE](https://www.baeldung.com/java-thread-safety))

AetherMC is currently in development, you may be able to get a build from me if you ask nicely by contacting my through discord [My Profile](https://discord.com/users/982807217952677888)
> **AetherMC is a fork of Minestom**
>
> Minestom is a lightweight minecraft server.
> It doesn't contain any code from mojang
> 
> You can find more details [HERE](https://minestom.net/)
# To-Do List
This is the To-Do List for AetherMC before the first full release. You can build the project whenever and test it out for yourself!

Completed: 0/38

- Regions
  - [ ] Saving Regions
  - [ ] Loading Regions
  - [ ] Region BoundingBoxes
  - [ ] Region Events
- Chunks
  - [ ] Chunk Saving
  - [ ] Saving Directional Blockstates
  - [ ] Chunk Borders
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
  - [ ] Rewrite the Whole LightingEngine
  - [ ] Fix Dark Chunks
  - [ ] Parallel Light Updates
  - [ ] Batch Light Updates
  - [ ] Fix Lighting Updates (Literally takes like 10 seconds to compute 50 chunks of light)
  - [ ] Add Light Source Blocks (Torch, Sea Lantern, ect)
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
- Suggestions
  - [ ] Spark Profiler Integration
  - [ ] World Noise Generation (Not Currently in the main API)
  - [ ] Timespans
- Future Plans
  - [ ] Improved Plugin System
  - [ ] Custom Crafting Recipes
  - [ ] "WorldEdit" API

ETA: Unknown
# Features
AetherMC provides many API's at your disposal to improve and create a lagless minecraft server!
- Multi Threading
  - Setting Blocks, Updating Light, Chunk Loading, Chunk Unloading is all Asynchronous
- Custom Plugin Manager
  - AetherMC has a very complex plugin system and is fully async. AetherMC does not support **Bukkit, Spigot,  or Paper**
- WorldCreator
  - AetherMC provides a very complex world management, you can **Create, Delete, Unload, and Load** worlds very fast
- NPC Support
  - AetherMC has built-in NPC support, no need for another plugin to do this for you.
- 1.20.1 Support
  - AetherMC has been created for 1.20.1 and any other versions below 1.20.1 isn't supported. You shouldn't be worried about performance though
- Modify and Listen for packets
  - AetherMC provides a very simple Packet API for your disposal.
- Commands and Events are Annotation based
  - Very simple command system, you no longer need the hastle of brigadier and `plugin.yml` anymore! Everything is handled with 1 line of code
- And More Coming Soon
  - I'm very excited to work on this project and keep it updated please check out the CHANGELOGS.md for the most recent changelogs
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
## Regions
Regions are like BoundingBoxes from `Spigot` with more featues like `Events`. These can also be saved so they can be loaded once the server starts again
## Chunks
Chunks now have "borders" borders are the outline of a chunk `16x16`. They act like regions, you can get the border of a list of chunks and it will return a outline border of all the chunks in the list
## Commands
AetherMC has a fully recoded version of Minestoms command API which is now Annotation based. Creating commands no longer needs to be inside the `plugin.yml` and will automaticlly be registered in the background
## Events
AetherMC has also recoded the events system. It is more friendly to the eye and easier to comprehend. Each event can be async or not. Here's an example: `AsyncBlockBreakEvent` or `BlockBreakEvent`
## Blocks
The block system has also been fully recoded because it didn't meet the needs of this API, blocks consist of many new methods that Minestom doesn't offer
## Worlds
The world system in AetherMC is very user friendly, it has many builders so you can let your imagination go wild with the world system
## Biomes 
Biomes haven't changed much from Minestom you can easily create a custom biome which has been changed a bit to remove the frustation of all the builders you have to deal with
# Thanks
If you are considering this as your pick for your Minecraft Server thank you, hopefully this project meets the needs of your expectations, i am fully willing you listen to your suggestions and change whatever accordingly :heart:
