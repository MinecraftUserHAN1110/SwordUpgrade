package upgrade.sword.api.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SwordUpgradeEvent extends Event {
    private static HandlerList handlers = new HandlerList();
    private EventType type;
    private boolean cancelled;
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public EventType getType() {
        return type;
    }

    public boolean isPre() {
        if (type == EventType.PRE) {
            return true;
        }
        return false;
    }

    public boolean isPost() {
        if (type == EventType.POST) {
            return true;
        }
        return false;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}
