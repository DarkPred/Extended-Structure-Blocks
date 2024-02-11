package com.github.darkpred.structuredebugger.client;

import com.github.darkpred.structuredebugger.StructureDebugger;
import com.github.darkpred.structuredebugger.mixin.StructureBlockEditScreenAccessor;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.StructureBlockEditScreen;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = StructureDebugger.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void addButton(ScreenEvent.InitScreenEvent.Post event) {
        Screen screen = event.getScreen();
        if (screen instanceof StructureBlockEditScreen editScreen) {
            var builder = CycleButton.booleanBuilder(new TextComponent("On"), new TextComponent("Off")).withValues(
                    List.of(Boolean.TRUE, Boolean.FALSE));
            IMixinStructureBlockEntity structure = (IMixinStructureBlockEntity) ((StructureBlockEditScreenAccessor) editScreen).getStructure();
            builder.withInitialValue(structure.structureDebugger$shouldSaveStructureVoid());
            event.addListener(builder.create(screen.width / 2 + 4 + 160, 80, 100, 20, new TextComponent("Save Void"),
                    (cycleButton, object) -> structure.structureDebugger$setSaveStructureVoid((Boolean) cycleButton.getValue())));

            builder.withInitialValue(structure.structureDebugger$shouldShowCaveAir());
            event.addListener(builder.create(screen.width / 2 + 4 + 160, 110, 100, 20, new TextComponent("Show Cave Air"),
                    (cycleButton, object) -> structure.structureDebugger$setShowCaveAir((Boolean) cycleButton.getValue())));
        }
    }
}
