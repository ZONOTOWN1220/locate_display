package com.zono.locate_displayer;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class Main extends JavaPlugin implements Listener {
  ScoreboardManager manager;
  Scoreboard board;
  Objective objective;
        
  @Override
  public void onEnable() {
      getServer().getPluginManager().registerEvents(this, this);
      manager = Bukkit.getScoreboardManager();
      board = manager.getMainScoreboard();
  }
    
  @EventHandler
  public void onPlayerMove(PlayerMoveEvent event) {
      
      Player player = event.getPlayer();
      String playerName = player.getName();
      
      if (board.getObjective(playerName) != null){
          board.getObjective(playerName).unregister();
      }
      
      String x = ((Integer)(int)player.getLocation().getX()).toString();
      String y = ((Integer)(int)player.getLocation().getY()).toString();
      String z = ((Integer)(int)player.getLocation().getZ()).toString();
      
      objective = board.registerNewObjective(playerName, playerName, Component.text(playerName));
      //objective = board.registerNewObjective(playerName, playerName,Component.text(playerName));
      objective.displayName(Component.text("位置" + " X:" + x + " Y:" + y + " Z:" + z,TextColor.color(255, 255, 255)));
      objective.setDisplaySlot(DisplaySlot.SIDEBAR);
      
      objective.getScore("").setScore(0);
              
      player.setScoreboard(board);
  }
  
  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
      if(cmd.getName().equalsIgnoreCase("pos")){
          Player player = Bukkit.getPlayer(sender.getName());
          String x = ((Integer)(int)player.getLocation().getX()).toString();
          String y = ((Integer)(int)player.getLocation().getY()).toString();
          String z = ((Integer)(int)player.getLocation().getZ()).toString();
          getServer().broadcast(Component.text(player.getName() + "は" + " X:" + x + " Y:" + y + " Z:" + z + " の位置にいます。"));
          return true;
      } else {
          return false;
      }
  }
}
