package d0mano.thalassite.mixin;

import d0mano.thalassite.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.FluidTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Inject(method = "getBlockBreakingSpeed", at = @At("RETURN"), cancellable = true)
    private void removeThalassiteUnderwaterPenalty(BlockState block, CallbackInfoReturnable<Float> cir) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        ItemStack mainHandStack = player.getMainHandStack();

        // SAFEGUARD 1: Check if the player is holding a Thalassite tool.
        // If they switch to ANY other item, this becomes false and NO boost is given.
        boolean holdingThalassiteTool = mainHandStack.isOf(ModItems.THALASSITE_PICKAXE) ||
                mainHandStack.isOf(ModItems.THALASSITE_AXE)     ||
                mainHandStack.isOf(ModItems.THALASSITE_SHOVEL)  ||
                mainHandStack.isOf(ModItems.THALASSITE_HOE)     ||
                mainHandStack.isOf(ModItems.THALASSITE_SWORD);

        if (holdingThalassiteTool) {
            float speed = cir.getReturnValue();

            // SAFEGUARD 2: Check if the player is actively submerged in water.
            // If they are ABOVE water (on a bridge, boat, or floating on the surface),
            // this is false, meaning they get regular land mining speed with no extra boosts.
            if (player.isSubmergedIn(FluidTags.WATER)) {

                // Grab the 1.21 aquatic penalty attribute (defaults to 0.2)
                float currentAttributeMultiplier = (float) player.getAttributeValue(EntityAttributes.SUBMERGED_MINING_SPEED);

                // Normalize back to 100% land speed if they don't have Aqua Affinity
                if (currentAttributeMultiplier < 1.0f && currentAttributeMultiplier > 0.0f) {
                    speed *= (1.0f / currentAttributeMultiplier);
                }

                // Counteract the independent 5x penalty for floating while fully underwater
                if (!player.isOnGround()) {
                    speed *= 5.0f;
                }
            }

            // Return the calculated speed (either normalized underwater speed or normal land speed)
            cir.setReturnValue(speed);
        }
    }
}