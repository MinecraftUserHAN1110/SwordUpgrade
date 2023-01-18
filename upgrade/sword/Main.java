package upgrade.sword;

import com.sun.istack.internal.NotNull;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import upgrade.sword.command.UpgradeCommand;
import upgrade.sword.event.GUI;
import upgrade.sword.event.UpgradeBase;

public class Main extends JavaPlugin {
    @Setter
    private static UpgradeBase base;

    public static Main main;
    @Override
    public void onEnable() {
        // Plugin startup logic
        main = this;
        base = new UpgradeBase(this);
        System.out.println("Plugin Enabling...");
        base.setup();
        getCommand("강화").setExecutor(new UpgradeCommand());
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new GUI(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Plugin Disabling...");
        main = null;
    }

    public static void addChatMessage(String message) {
        final String prefix = ChatColor.GRAY + "[" + ChatColor.GRAY + "공지" + ChatColor.GRAY + "] " + ChatColor.WHITE;
        final String text = prefix + message;

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(text);
        }
    }

    public static void addError(@NotNull String errorName, Player target, @NotNull String message) {
        final String prefix = ChatColor.RED + "오류 (" + errorName + ") >> ";
        message = prefix + message;
        target.sendMessage(message);
    }
}
