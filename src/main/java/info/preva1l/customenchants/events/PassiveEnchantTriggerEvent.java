package info.preva1l.customenchants.events;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

@Getter
public class PassiveEnchantTriggerEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final PassiveType type;

    public PassiveEnchantTriggerEvent(@NotNull Player who, @NotNull PassiveType type) {
        super(who);
        this.type = type;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
