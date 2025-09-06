package net.example.norain;

import btw.BTWAddon;
import net.minecraft.src.World;

public class NoRainAddon extends BTWAddon {

    public NoRainAddon() {
        super(); // must call no-arg constructor
    }

    @Override
    public void initialize() {
        System.out.println("[NoRainAddon] Initialized - Disabling rain and thunderstorms...");
    }

    // Called from WorldMixin
    public static void onWorldTick(World world) {
        if (world.getWorldInfo().isRaining() || world.getWorldInfo().isThundering()) {
            world.getWorldInfo().setRaining(false);
            world.getWorldInfo().setThundering(false);
        }
    }
}


