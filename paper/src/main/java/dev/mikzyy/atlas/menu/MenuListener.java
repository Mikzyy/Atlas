package dev.mikzyy.atlas.menu;

import dev.mikzyy.atlas.menu.button.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;

public class MenuListener implements Listener {

    @EventHandler
    public void onClick(final InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        final Inventory topInventory = event.getView().getTopInventory();
        if (!(topInventory.getHolder() instanceof Menu menu)) return;

        if (menu.isCancelAll()) {
            event.setCancelled(true);
        }

        if (event.getClickedInventory() == null || event.getClickedInventory() != topInventory) {
            return;
        }

        final Button button = menu.getButton(event.getSlot());
        if (button != null) {
            event.setCancelled(true);
            button.onClick(player);
        }
    }

    @EventHandler
    public void onDrag(final InventoryDragEvent event) {
        if (!(event.getView().getTopInventory().getHolder() instanceof Menu menu)) return;

        if (menu.isCancelAll()) {
            event.setCancelled(true);
            return;
        }

        final int topSize = event.getView().getTopInventory().getSize();
        for (int rawSlot : event.getRawSlots()) {
            if (rawSlot < topSize) {
                event.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler
    public void onClose(final InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player player)) return;
        if (!(event.getInventory().getHolder() instanceof Menu menu)) return;

        menu.handleClose(player);
    }
}