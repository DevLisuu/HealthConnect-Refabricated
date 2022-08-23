package me.devlisuu.healthconnectrefab.command;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class CommandRegistry {
    public static void registerCommands() {
        CommandRegistrationCallback.EVENT.register(HealthConnectCommand::register);
    }
}
