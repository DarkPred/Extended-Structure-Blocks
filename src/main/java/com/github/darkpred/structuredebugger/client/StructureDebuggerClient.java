package com.github.darkpred.structuredebugger.client;

import com.github.darkpred.structuredebugger.IMixinStructureBlockEntity;
import com.github.darkpred.structuredebugger.mixin.StructureBlockEditScreenAccessor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.screens.inventory.StructureBlockEditScreen;
import net.minecraft.network.chat.TextComponent;

import java.util.List;

public class StructureDebuggerClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {

        ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            if (screen instanceof StructureBlockEditScreen editScreen) {
                var builder = CycleButton.booleanBuilder(new TextComponent("On"), new TextComponent("Off")).withValues(
                        List.of(Boolean.TRUE, Boolean.FALSE));
                IMixinStructureBlockEntity structure = (IMixinStructureBlockEntity) ((StructureBlockEditScreenAccessor) editScreen).getStructure();
                builder.withInitialValue(structure.structureDebugger$shouldSaveStructureVoid());
                CycleButton<Boolean> saveVoid =  builder.create(screen.width / 2 + 4 + 160, 80, 100, 20, new TextComponent("Save Void"),
                        (cycleButton, object) -> structure.structureDebugger$setSaveStructureVoid((Boolean) cycleButton.getValue()));
                Screens.getButtons(screen).add(saveVoid);

                builder.withInitialValue(structure.structureDebugger$shouldShowCaveAir());
                CycleButton<Boolean> showCaveAir = builder.create(screen.width / 2 + 4 + 160, 110, 100, 20, new TextComponent("Show Cave Air"),
                        (cycleButton, object) -> structure.structureDebugger$setShowCaveAir((Boolean) cycleButton.getValue()));
                Screens.getButtons(screen).add(showCaveAir);

                ScreenEvents.afterRender(screen).register((screen1, matrices, mouseX, mouseY, tickDelta) -> {
                    saveVoid.render(matrices, mouseX, mouseY, tickDelta);
                    showCaveAir.render(matrices, mouseX, mouseY, tickDelta);
                });
            }
        });
    }
}
