package com.github.darkpred.structuredebugger.mixin;

import com.github.darkpred.structuredebugger.client.IMixinStructureBlockEntity;
import com.github.darkpred.structuredebugger.client.IMixinStructurePacket;
import net.minecraft.client.gui.screens.inventory.StructureBlockEditScreen;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.level.block.entity.StructureBlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(StructureBlockEditScreen.class)
public abstract class StructureBlockEditScreenMixin {

    @Shadow
    @Final
    private StructureBlockEntity structure;

    @ModifyArg(method = "sendToServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ClientPacketListener;send(Lnet/minecraft/network/protocol/Packet;)V"))
    private Packet<?> injected(Packet<?> pPacket) {
        ((IMixinStructurePacket) pPacket).structureDebugger$setSaveStructureVoid(((IMixinStructureBlockEntity)structure).structureDebugger$shouldSaveStructureVoid());
        ((IMixinStructurePacket) pPacket).structureDebugger$setShowCaveAir(((IMixinStructureBlockEntity)structure).structureDebugger$shouldShowCaveAir());
        return pPacket;
    }
}
