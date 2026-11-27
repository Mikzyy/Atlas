package dev.mikzyy.atlas.menu.button;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public abstract class Button {

    @Getter
    private final int slot;

    public Button(int slot) {
        this.slot = slot;
    }

    public abstract void onClick(Player player);

    public abstract ItemStack getItem();

    public static @NotNull Button of(final int slot, final @NotNull ItemStack item) {
        return of(slot, item, player -> {});
    }

    public static @NotNull Button of(final int slot, final @NotNull ItemStack item, final @NotNull Consumer<Player> action) {
        return new Button(slot) {
            @Override
            public void onClick(final Player player) {
                action.accept(player);
            }

            @Override
            public ItemStack getItem() {
                return item;
            }
        };
    }
}
