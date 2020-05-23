package fr.smyler.terramap.input;

import org.lwjgl.input.Keyboard;

import fr.smyler.terramap.TerramapUtils;
import fr.smyler.terramap.gui.GuiTiledMap;
import fr.smyler.terramap.maps.TiledMap;
import fr.smyler.terramap.maps.TiledMaps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(Side.CLIENT)
public abstract class KeyBindings {

	private static final String KEY_CATEGORY = "terramap.binding.category";
	
	public static final KeyBinding OPEN_MAP = new KeyBinding("terramap.binding.open_map", Keyboard.KEY_M, KeyBindings.KEY_CATEGORY); //TODO Close the map on when pressed
	public static final KeyBinding TOGGLE_DEBUG = new KeyBinding("terramap.binding.toggle_debug", Keyboard.KEY_P, KeyBindings.KEY_CATEGORY);
	
	public static void registerBindings() {
		ClientRegistry.registerKeyBinding(OPEN_MAP);
		ClientRegistry.registerKeyBinding(TOGGLE_DEBUG);
	}
	
	public static void checkBindings() {
		if(OPEN_MAP.isPressed() && Minecraft.getMinecraft().world != null) {
			if(!TerramapUtils.isBaguette()) {
				TiledMap<?>[] maps = {TiledMaps.OSM, TiledMaps.OSM_HUMANITARIAN};
				Minecraft.getMinecraft().displayGuiScreen(new GuiTiledMap(maps, Minecraft.getMinecraft().world));
			} else {
				TiledMap<?>[] maps = {TiledMaps.OSM_FRANCE, TiledMaps.OSM, TiledMaps.OSM_HUMANITARIAN};
				Minecraft.getMinecraft().displayGuiScreen(new GuiTiledMap(maps, Minecraft.getMinecraft().world));
			}
		}
	}
	
    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {
    	KeyBindings.checkBindings();
    }
}
