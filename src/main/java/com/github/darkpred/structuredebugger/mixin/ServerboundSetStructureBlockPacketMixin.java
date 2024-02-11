package com.github.darkpred.structuredebugger.mixin;

import com.github.darkpred.structuredebugger.IMixinStructurePacket;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ServerboundSetStructureBlockPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerboundSetStructureBlockPacket.class)
public abstract class ServerboundSetStructureBlockPacketMixin implements IMixinStructurePacket {

    @Unique
    private boolean structureDebugger$saveStructureVoid;
    @Unique
    private boolean structureDebugger$showCaveAir;

    @Inject(method = "<init>(Lnet/minecraft/network/FriendlyByteBuf;)V", at = @At("TAIL"))
    private void updateConstructor(CallbackInfo ci, @Local(argsOnly = true) FriendlyByteBuf pBuffer) {
        structureDebugger$setSaveStructureVoid(pBuffer.readBoolean());
        structureDebugger$setShowCaveAir(pBuffer.readBoolean());
    }

    @Inject(method = "write", at = @At("TAIL"))
    private void updateWrite(CallbackInfo ci, @Local(argsOnly = true) FriendlyByteBuf pBuffer) {
        pBuffer.writeBoolean(structureDebugger$saveStructureVoid);
        pBuffer.writeBoolean(structureDebugger$showCaveAir);
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
