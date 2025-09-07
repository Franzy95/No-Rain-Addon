package com.franzy.norain.mixin;


import com.franzy.norain.NoRainAddon;
import net.minecraft.src.World;
import net.minecraft.src.WorldInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(World.class)
public class WorldMixin {

    @Inject(method = "updateWeather", at = @At("HEAD"))
    private void onUpdateWeather(CallbackInfo ci) {
        World world = (World)(Object)this;
        WorldInfo info = world.getWorldInfo();

        long time = world.getWorldTime();

        if (time < NoRainAddon.disableRainTicks) {
            world.getWorldInfo().setRaining(false);
            world.getWorldInfo().setThundering(false);
        }
    }
}



