package com.github.darkpred.structuredebugger.client;

public interface IMixinStructurePacket {
    boolean structureDebugger$shouldSaveStructureVoid();
    void structureDebugger$setSaveStructureVoid(boolean saveStructureVoid);
    boolean structureDebugger$shouldShowCaveAir();
    void structureDebugger$setShowCaveAir(boolean showCaveAir);
}
