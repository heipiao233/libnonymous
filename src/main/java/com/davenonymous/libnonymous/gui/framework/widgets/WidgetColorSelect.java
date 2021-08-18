package com.davenonymous.libnonymous.gui.framework.widgets;

import com.davenonymous.libnonymous.Libnonymous;
import com.davenonymous.libnonymous.gui.framework.event.MouseEnterEvent;
import com.davenonymous.libnonymous.gui.framework.event.MouseExitEvent;
import com.davenonymous.libnonymous.gui.framework.event.WidgetEventResult;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.gui.GuiUtils;

import java.awt.*;

public class WidgetColorSelect extends WidgetWithChoiceValue<Color> {
    public boolean hovered = false;

    protected static final ResourceLocation BUTTON_TEXTURES = new ResourceLocation("textures/gui/widgets.png");

    public WidgetColorSelect() {
        this.setHeight(20);
        this.setWidth(20);

        this.addListener(MouseEnterEvent.class, (event, widget) -> {((WidgetColorSelect)widget).hovered = true; return WidgetEventResult.CONTINUE_PROCESSING; });
        this.addListener(MouseExitEvent.class, (event, widget) -> {((WidgetColorSelect)widget).hovered = false; return WidgetEventResult.CONTINUE_PROCESSING; });

        this.addClickListener();
    }

    @Override
    public void draw(MatrixStack stack, Screen screen) {
        screen.getMinecraft().getTextureManager().bindTexture(BUTTON_TEXTURES);

        stack.push();
        float[] colors = this.getValue().getRGBColorComponents(null);
        RenderSystem.color4f(colors[0], colors[1], colors[2], hovered ? 0.7F : 1.0F);

        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        stack.translate(0.0f, 0.0f, 2.0f);

        if(hovered) {
            GuiUtils.drawTexturedModalRect(0, 0, 0, 46 + 2 * 20, width / 2, height, 0.0f);
            GuiUtils.drawTexturedModalRect(width / 2, 0, 200 - width / 2, 46 + 2 * 20, width / 2, height, 0.0f);
        } else {
            GuiUtils.drawTexturedModalRect(0, 0, 0, 46 + 1 * 20, width / 2, height, 0.0f);
            GuiUtils.drawTexturedModalRect(width / 2, 0, 200 - width / 2, 46 + 1 * 20, width / 2, height, 0.0f);
        }
        stack.pop();
    }
}