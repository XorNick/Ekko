package com.bizarrealex.ekko.profiles;

import com.bizarrealex.ekko.utils.player.PlayerUtility;
import com.bizarrealex.ekko.utils.player.SimpleOfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Copyright 2016 Alexander Maxwell
 * Use and or redistribution of compiled JAR file and or source code is permitted only if given
 * explicit permission from original author: Alexander Maxwell
 */
public class ProfileListeners implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Profile.sendTabUpdate(player);
        Profile profile = Profile.getByUuid(player.getUniqueId());

        for (Player otherPlayer : PlayerUtility.getOnlinePlayers()) {
            profile.updateTab(otherPlayer);
        }

        SimpleOfflinePlayer offlinePlayer = SimpleOfflinePlayer.getByUuid(player.getUniqueId());

        if (offlinePlayer == null) {
            new SimpleOfflinePlayer(player);
        } else {
            if (!(offlinePlayer.getName().equals(player.getName()))) {
                offlinePlayer.setName(player.getName());
            }
        }
    }
}
