package com.github.darkpred.structuredebugger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(StructureDebugger.MOD_ID)
public class StructureDebugger {
    public static final String MOD_ID = "structuredebugger";

    public StructureDebugger() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
