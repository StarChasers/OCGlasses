package com.bymarcin.openglasses.config;

import com.bymarcin.openglasses.OpenGlasses;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.server.permission.PermissionAPI;

import java.io.File;

public class Config extends PermissionAPI {
    private static Configuration config = null;

    public static void preInit(){
        File configFile = new File(Loader.instance().getConfigDir(), OpenGlasses.MODID + ".cfg");
        config = new Configuration(configFile);

        syncConfig(true);
    }

    public static void clientPreInit() {
        MinecraftForge.EVENT_BUS.register(new ConfigEventHandler());
    }

    public static Configuration getConfig() {
        return config;
    }

    private static void syncConfig(boolean loadConfigFromFile) {
        if (loadConfigFromFile)
            config.load();

        Property absolute_rendering_allowed = config.get("general", "absolute_rendering_allowed", true);
        absolute_rendering_allowed.setLanguageKey("gui.openglasses.config.general.absolute_rendering_allowed");
        absolute_rendering_allowed.setComment("allow setting the glasses terminal to absolute (world coordinates) render position");

        Property total_widget_limit = config.get("general", "widgetlimit", 255);
        total_widget_limit.setLanguageKey("gui.openglasses.config.general.widgetlimit");
        total_widget_limit.setComment("sets the maximal allowed amount of widgets per glasses instance");

        if (config.hasChanged())
            config.save();
    }

    public static class ConfigEventHandler{
        @SubscribeEvent(priority = EventPriority.NORMAL)
        public void onEvent(ConfigChangedEvent.OnConfigChangedEvent event){
            if (!event.getModID().equals(OpenGlasses.MODID))
                return;

            syncConfig(false);
            load();
        }
    }

    public static void load(){
        OpenGlasses.absoluteRenderingAllowed = Config.getConfig().getCategory("general").get("absolute_rendering_allowed").getBoolean();
        OpenGlasses.widgetLimit = Config.getConfig().getCategory("general").get("widgetlimit").getInt();
    }
}