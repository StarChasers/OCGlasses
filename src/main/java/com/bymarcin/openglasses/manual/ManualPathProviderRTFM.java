package com.bymarcin.openglasses.manual;

import li.cil.manual.api.detail.ManualDefinition;
import li.cil.manual.api.manual.PathProvider;
import li.cil.manual.api.ManualAPI;
import li.cil.manual.api.prefab.manual.TextureTabIconRenderer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class ManualPathProviderRTFM extends ManualPathProvider implements PathProvider {
    static ManualDefinition manual;

    public void initialize(ResourceLocation iconResourceLocation, String tooltip, String path) {
        manual = ManualAPI.createManual(false);

        if(FMLCommonHandler.instance().getEffectiveSide().equals(Side.CLIENT)) {
            manual.addProvider(new ManualPathProviderRTFM());
            manual.addProvider(new ManualContentProviderRTFM());
            manual.setDefaultPage(path);
            manual.addTab(new TextureTabIconRenderer(iconResourceLocation), tooltip, path);
        }
    }

    public static Item getManualItem(){
        return ManualAPI.createItemForManual(manual);
    }

}
