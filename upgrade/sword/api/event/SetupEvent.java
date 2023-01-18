package upgrade.sword.api.event;

import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import upgrade.sword.event.UpgradeBase;

import java.util.logging.Logger;

public class SetupEvent extends SwordUpgradeEvent implements PrivateEvent {
    private static SetupEvent INSTANCE;

    public SetupEvent(UpgradeBase base) {
        this.INSTANCE = this;
    }
    /**
     * handle call event
     */
    @Override
    public void handle() {
        Bukkit.getLogger().info("SwordUpgrade B1");
    }

    public SetupEvent getEvent() {
        return this;
    }
}
