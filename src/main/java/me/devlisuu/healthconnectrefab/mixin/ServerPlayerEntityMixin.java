package me.devlisuu.healthconnectrefab.mixin;

import me.devlisuu.healthconnectrefab.HealthConnect;
import me.devlisuu.healthconnectrefab.util.HCIgnoreInterface;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin implements HCIgnoreInterface {
    boolean HCIgnore = false;

    @Override
    public void setHCIgnore(boolean bool) {
        HCIgnore = bool;
    }

    @Inject(method = "damage", at = @At("HEAD"))
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if(HealthConnect.getModEnabled() && !HCIgnore) {
            setHCIgnore(true);

            var player = (ServerPlayerEntity) (Object) this;
            HealthConnect.onPlayerDamaged(player, source, amount);

            setHCIgnore(false);
        }
    }
}
