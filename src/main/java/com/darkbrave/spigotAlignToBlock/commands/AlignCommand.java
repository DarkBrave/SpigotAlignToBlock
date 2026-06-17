package com.darkbrave.spigotAlignToBlock.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class AlignCommand implements CommandExecutor
{
    private final JavaPlugin plugin;
    public AlignCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(final @NotNull CommandSender sender, final @NotNull Command command, final @NotNull String label, final String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player) sender;
            Entity teleportEntity;

            if (player.isInsideVehicle()) {
                teleportEntity = player.getVehicle();
            } else {
                teleportEntity = player;
            }
            Location newLocation = teleportEntity.getLocation().clone();

            newLocation.setX(newLocation.getBlockX()+0.5);
            newLocation.setY(newLocation.getBlockY());
            newLocation.setZ(newLocation.getBlockZ()+0.5);

            float newYaw = Math.round(newLocation.getYaw()/90f)*90f;
            float newPitch = Math.round(newLocation.getPitch()/10f)*10f;
            newLocation.setPitch(newPitch);
            newLocation.setYaw(newYaw);

            teleportEntity.teleport(newLocation);
            teleportEntity.setVelocity(new Vector(0,0,0));
            player.setRotation(newYaw, newPitch);

            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                teleportEntity.setVelocity(new Vector(0, 0, 0));
                teleportEntity.setRotation(newYaw, newPitch);
            }, 1L);
        }
        else {
            sender.sendMessage("You need to be a player to execute this command!");
        }
        return true;
    }
}