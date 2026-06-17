package com.darkbrave.spigotAlignToBlock;

import com.darkbrave.spigotAlignToBlock.commands.*;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class SpigotAlignToBlock extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginManager pm = getServer().getPluginManager();
        Objects.requireNonNull(getCommand("align")).setExecutor((CommandExecutor)new AlignCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
