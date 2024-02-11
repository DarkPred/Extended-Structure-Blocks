package com.github.darkpred.structuredebugger.mixin;

import com.github.darkpred.structuredebugger.IMixinStructureBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.StructureBlockEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(StructureBlockEntity.class)
public abstract class StructureBlockEntityMixin implements IMixinStructureBlockEntity {
    @Unique
    private boolean structureDebugger$saveStructureVoid;
    @Unique
    private boolean structureDebugger$showCaveAir;

    @ModifyArg(method = "saveStructure(Z)Z", index = 4, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/structure/templatesystem/StructureTemplate;fillFromWorld(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Vec3i;ZLnet/minecraft/world/level/block/Block;)V"))
    private @Nullable Block saveStructureVoid(@Nullable Block toIgnore) {
        return structureDebugger$shouldSaveStructureVoid() ? null : toIgnore;
    }

    @Override
    public boolean structureDebugger$shouldSaveStructureVoid() {
        return structureDebugger$saveStructureVoid;
    }

    @Override
    public void structureDebugger$setSaveStructureVoid(boolean saveStructureVoid) {
        this.structureDebugger$saveStructureVoid = saveStructureVoid;
    }

    @Override
    public boolean structureDebugger$shouldShowCaveAir() {
        return structureDebugger$showCaveAir;
    }

    @Override
    public void structureDebugger$setShowCaveAir(boolean showCaveAir) {
        this.structureDebugger$showCaveAir = showCaveAir;
    }
}