package dev.mikzyy.atlas.menu;

import dev.mikzyy.atlas.menu.button.Button;
import dev.mikzyy.atlas.util.MessageUtil;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class Menu implements InventoryHolder {

    private final Inventory inventory;
    private final Map<Integer, Button> registeredButtons = new HashMap<>();

    @Getter
    @Setter
    private boolean cancelAll = true;

    private @Nullable ItemStack borderItem;
    private @Nullable ItemStack emptyItem;

    public Menu(final @NotNull String title, final int size) {
        this.inventory = Bukkit.createInventory(this, size, MessageUtil.toMM(title));
    }

    public Menu(final @NotNull Component title, final int size) {
        this.inventory = Bukkit.createInventory(this, size, title);
    }

    public void registerButton(final @NotNull Button button) {
        this.registeredButtons.put(button.getSlot(), button);
    }

    public void fillBorder(final @NotNull ItemStack borderItem) {
        this.borderItem = borderItem.clone();
    }

    public void fillEmpty(final @NotNull ItemStack emptyItem) {
        this.emptyItem = emptyItem.clone();
    }

    public void open(final @NotNull Player player) {
        this.inventory.clear();

        for (Button button : this.registeredButtons.values()) {
            this.inventory.setItem(button.getSlot(), button.getItem());
        }

        final int size = this.inventory.getSize();
        final int rows = size / 9;

        for (int slot = 0; slot < size; slot++) {
            if (this.registeredButtons.containsKey(slot)) {
                continue;
            }

            final int row = slot / 9;
            final int col = slot % 9;
            final boolean isBorder = row == 0 || row == rows - 1 || col == 0 || col == 8;

            if (isBorder && this.borderItem != null) {
                this.inventory.setItem(slot, this.borderItem.clone());
            } else if (this.emptyItem != null) {
                this.inventory.setItem(slot, this.emptyItem.clone());
            }
        }

        player.openInventory(this.inventory);
    }

    public void handleClose(final @NotNull Player player) {
        this.inventory.clear();
    }

    public @Nullable Button getButton(final int slot) {
        return this.registeredButtons.get(slot);
    }

    public @NotNull Map<Integer, Button> getRegisteredButtons() {
        return Collections.unmodifiableMap(this.registeredButtons);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
