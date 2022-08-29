package me.devlisuu.healthconnectrefab;

import me.devlisuu.healthconnectrefab.command.CommandRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;

public class HealthConnect implements ModInitializer {
    static boolean modEnabled = false;

    @Override
    public void onInitialize() {
        CommandRegistry.registerCommands();
    }

    public static void setModEnabled(boolean bool) {
        modEnabled = bool;
    }

    public static boolean isModEnabled() {
        return modEnabled;
    }

    public static void onPlayerDamaged(ServerPlayerEntity player, DamageSource source, float amount) {
        List<ServerPlayerEntity> players = player.server.getPlayerManager().getPlayerList();

        for(ServerPlayerEntity item : players) {
            if(item == player) {continue;}
            item.damage(source, amount);
        }
    }
}
