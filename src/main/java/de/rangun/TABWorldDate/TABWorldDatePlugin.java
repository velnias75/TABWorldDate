/*
 * Copyright 2022 by Heiko Schäfer <heiko@rangun.de>
 *
 * This file is part of TABWorldDate.
 *
 * TABWorldDate is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * TABWorldDate is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with TABWorldDate.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.rangun.TABWorldDate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.neznamy.tab.api.TabAPI;

/**
 * @author heiko
 *
 */
public final class TABWorldDatePlugin extends JavaPlugin {

	@Override
	public void onEnable() {

		saveDefaultConfig();

		final FileConfiguration config = getConfig();

		World world = null;

		for (final World w : Bukkit.getWorlds()) {

			if (World.Environment.NORMAL.equals(w.getEnvironment())) {
				world = w;
				break;
			}
		}

		final Calendar cal = Calendar.getInstance();
		final SimpleDateFormat sdf = new SimpleDateFormat(config.getString("date-format", "EEE, d MMM y, HH:mm"),
				Locale.forLanguageTag(config.getString("date-locale", "en-US")));

		final World world2 = world;

		if (world2 != null) {

			TabAPI.getInstance().getPlaceholderManager().registerServerPlaceholder("%world-date%",
					config.getInt("refresh-interval", 950), () -> {

						final long dayTime = world2.getTime();
						final long gameTime = world2.getGameTime();
						final long gameTimeDays = gameTime - (gameTime % 24000L);

						cal.set(0, Calendar.JANUARY, 1, 0, 0, 0);

						cal.setTimeInMillis(cal.getTimeInMillis() + gameTimeDays * 3600L);

						cal.set(Calendar.HOUR_OF_DAY, (int) (dayTime / 1000L) + 6);
						cal.set(Calendar.MINUTE, (int) ((dayTime % 1000L) * 60L / 1000L));
						cal.set(Calendar.SECOND, 0);
						cal.set(Calendar.MILLISECOND, 0);

						return sdf.format(cal.getTime());

					});
		} else {
			Bukkit.getLogger().warning("No suitable world found. Placeholder %world-date% won't be available.");
		}
	}
}
