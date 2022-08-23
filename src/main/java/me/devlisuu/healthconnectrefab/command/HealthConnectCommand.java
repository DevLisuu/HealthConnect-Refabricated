package me.devlisuu.healthconnectrefab.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.mojang.logging.LogUtils;
import me.devlisuu.healthconnectrefab.HealthConnect;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class HealthConnectCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess, CommandManager.RegistrationEnvironment registrationEnvironment) {
        final LiteralCommandNode<ServerCommandSource> HealthConnectNode = dispatcher.register(CommandManager.literal("healthconnect")
                .requires(source -> source.hasPermissionLevel(4))
                    .then(CommandManager.literal("enabled")
                        .then(CommandManager.argument("boolean", BoolArgumentType.bool())
                            .executes(HealthConnectCommand::execute))));

        dispatcher.register(CommandManager.literal("healthconnectrefabricated").redirect(HealthConnectNode));
    }

    public static int execute(CommandContext<ServerCommandSource> context) {
        boolean bl = BoolArgumentType.getBool(context, "boolean");

        if(bl) {
            HealthConnect.setModEnabled(true);
            context.getSource().sendFeedback(Text.literal("HealthConnect is now enabled"), true);
        }else {
            HealthConnect.setModEnabled(false);
            context.getSource().sendFeedback(Text.literal("HealthConnect is now disabled"), true);
        }

        return 1;
    }
}
