# 📅 TABWorldDate ![Spiget Version](https://img.shields.io/spiget/version/105185?label=Latest%20version)

![TAB](https://github.com/NEZNAMY/TAB) addon plugin providing a placeholder for the world's current Gregorian date.

*Display your world's `gametime` (ticks since creation of the world) in human readable form like ` Sat, 10 Sep 1, 05:32`.*<br />
*The date starts at the January 1st, of the year 1 and counts up every Minecraft day (24000 ticks).*

It adds a placeholder `%world-date%` to **![NEZNAMY's TAB plugin](https://github.com/NEZNAMY/TAB)** for use in *headers* or *footers*.

### Installation

Install **NEZNAMY's TAB plugin** from https://github.com/NEZNAMY/TAB#links as well as this plugin into the `plugins`-folder.

### Configuration

Restart the server once, to create the config files and edit the settings in ![config.yml](https://github.com/velnias75/TABWorldDate/blob/master/src/main/resources/config.yml) to your needs.

In **![NEZNAMY's TAB plugin](https://github.com/NEZNAMY/TAB)**-`config.yml` you can add now something like this:
```
# https://github.com/NEZNAMY/TAB/wiki/Feature-guide:-Header-&-Footer
header-footer:
  [...]
  header:
  [...]
  - '&r&7%world-date%'
  [...]  '
```

### Building
After *checkout/clone* do
`git submodule update --init --recursive` once, than `./gradlew clean build`.

---

![](https://bstats.org/signatures/bukkit/TABWorldDate.svg)
