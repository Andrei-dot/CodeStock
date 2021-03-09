package com.flansmod.client;

import com.flansmod.api.IControllable;
import com.flansmod.client.gui.GuiTeamScores;
import com.flansmod.client.gui.GuiTeamSelect;
import com.flansmod.common.FlansMod;
import com.flansmod.common.network.PacketReload;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class KeyInputHandler
{
  public static KeyBinding downKey = new KeyBinding("Down key", Keyboard.KEY_NONE, "Flan's Mod");
  
  public static KeyBinding inventoryKey = new KeyBinding("Inventory key", Keyboard.KEY_NONE, "Flan's Mod");
  public static KeyBinding bombKey = new KeyBinding("Bomb Key", Keyboard.KEY_NONE, "Flan's Mod");
  public static KeyBinding gunKey = new KeyBinding("Gun Key", Keyboard.KEY_NONE, "Flan's Mod");
  public static KeyBinding controlSwitchKey = new KeyBinding("Control Switch key", Keyboard.KEY_NONE, "Flan's Mod");
  public static KeyBinding reloadKey = new KeyBinding("Reload key", Keyboard.KEY_NONE, "Flan's Mod");
  public static KeyBinding teamsMenuKey = new KeyBinding("Teams Menu Key", Keyboard.KEY_NONE, "Flan's Mod");
  public static KeyBinding teamsScoresKey = new KeyBinding("Teams Scores Key", Keyboard.KEY_NONE, "Flan's Mod");
  public static KeyBinding leftRollKey = new KeyBinding("Roll Left Key", Keyboard.KEY_NONE, "Flan's Mod");
  public static KeyBinding rightRollKey = new KeyBinding("Roll Right Key", Keyboard.KEY_NONE, "Flan's Mod");
  public static KeyBinding gearKey = new KeyBinding("Gear Up / Down Key", Keyboard.KEY_NONE, "Flan's Mod");
  public static KeyBinding doorKey = new KeyBinding("Door Open / Close Key", Keyboard.KEY_NONE, "Flan's Mod");
  public static KeyBinding modeKey = new KeyBinding("Mode Switch Key", Keyboard.KEY_NONE, "Flan's Mod");
  
  public static KeyBinding debugKey = new KeyBinding("Debug Key", Keyboard.KEY_NONE, "Flan's Mod");
  public static KeyBinding reloadModelsKey = new KeyBinding("Reload Models Key", Keyboard.KEY_NONE, "Flan's Mod");
  
  Minecraft mc;
  
  public KeyInputHandler() {
    ClientRegistry.registerKeyBinding(downKey);
    
    ClientRegistry.registerKeyBinding(inventoryKey);
    ClientRegistry.registerKeyBinding(bombKey);
    ClientRegistry.registerKeyBinding(gunKey);
    ClientRegistry.registerKeyBinding(controlSwitchKey);
    ClientRegistry.registerKeyBinding(reloadKey);
    ClientRegistry.registerKeyBinding(teamsMenuKey);
    ClientRegistry.registerKeyBinding(teamsScoresKey);
    ClientRegistry.registerKeyBinding(leftRollKey);
    ClientRegistry.registerKeyBinding(rightRollKey);
    ClientRegistry.registerKeyBinding(gearKey);
    ClientRegistry.registerKeyBinding(doorKey);
    ClientRegistry.registerKeyBinding(modeKey);
    
    ClientRegistry.registerKeyBinding(debugKey);
    ClientRegistry.registerKeyBinding(reloadModelsKey);

    
    this.mc = Minecraft.getMinecraft();
  }

  
  @SubscribeEvent
  public void onKeyInput(InputEvent.KeyInputEvent event) {
    if (FMLClientHandler.instance().isGUIOpen(net.minecraft.client.gui.GuiChat.class) || this.mc.currentScreen != null) {
      return;
    }
    EntityClientPlayerMP entityClientPlayerMP = this.mc.player;
    Entity ridingEntity = entityClientPlayerMP.field_70154_o;

    if (teamsMenuKey.isPressed()) {
      
      this.mc.displayGuiScreen(new GuiTeamSelect());
      return;
    } 
    if (teamsScoresKey.isPressed()) {
      
      this.mc.displayGuiScreen(new GuiTeamScores());
      return;
    } 
    if (reloadKey.isPressed() && FlansModClient.shootTime(false) <= 0) {
      
      FlansMod.getPacketHandler().sendToServer(new PacketReload(false));
      return;
    } 
    if (debugKey.isPressed())
    {
      FlansMod.DEBUG = !FlansMod.DEBUG;
    }
    if (reloadModelsKey.isPressed())
    {
      FlansModClient.reloadModels(Keyboard.isKeyDown(42));
    }

if (ridingEntity instanceof IControllable) {
      
      IControllable riding = (IControllable)ridingEntity;
      if (this.mc.gameSettings.keyBindForward.isPressed())
        riding.pressKey(0, entityClientPlayerMP); 
      if (this.mc.gameSettings.keyBindJump.isPressed())
        riding.pressKey(1, entityClientPlayerMP); 
      if (this.mc.gameSettings.keyBindJump.isPressed())
        riding.pressKey(2, entityClientPlayerMP); 
      if (this.mc.gameSettings.keyBindJump.isPressed())
        riding.pressKey(3, entityClientPlayerMP); 
      if (this.mc.gameSettings.keyBindJump.isPressed())
        riding.pressKey(4, entityClientPlayerMP); 
      if (downKey.isPressed())
        riding.pressKey(5, entityClientPlayerMP); 
      if (this.mc.gameSettings.keyBindSneak.isPressed())
        riding.pressKey(6, entityClientPlayerMP); 
      if (this.mc.gameSettings.keyBindInventory.isPressed() || inventoryKey.isPressed())
        riding.pressKey(7, entityClientPlayerMP); 
      if (bombKey.isPressed())
        riding.pressKey(8, entityClientPlayerMP); 
      if (gunKey.isPressed())
        riding.pressKey(9, entityClientPlayerMP); 
      if (controlSwitchKey.func_151468_f())
        riding.pressKey(10, entityClientPlayerMP); 
      if (leftRollKey.isPressed())
        riding.pressKey(11, entityClientPlayerMP); 
      if (rightRollKey.isPressed())
        riding.pressKey(12, entityClientPlayerMP); 
      if (gearKey.isPressed())
        riding.pressKey(13, entityClientPlayerMP); 
      if (doorKey.isPressed())
        riding.pressKey(14, entityClientPlayerMP); 
      if (modeKey.isPressed())
        riding.pressKey(15, entityClientPlayerMP); 
    } 
  }
}