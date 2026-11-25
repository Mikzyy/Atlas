package dev.mikzyy.atlas.util;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

@UtilityClass
public final class MessageUtil {

    private static final MiniMessage MM = MiniMessage.miniMessage();

    @Getter
    @Setter
    @NotNull
    private static String prefix = "<yellow>Default</yellow>";

    @Getter
    @Setter
    @NotNull
    private static String separator = " <dark_gray>»</dark_gray> ";

    public static @NotNull Component toMM(@Nullable String text) {
        if (text == null || text.isEmpty()) {
            return Component.empty();
        }
        return MM.deserialize(text);
    }

    public static @NotNull Component toMM(@Nullable String text, @NotNull TagResolver... resolvers) {
        if (text == null || text.isEmpty()) {
            return Component.empty();
        }
        return MM.deserialize(text, resolvers);
    }

    public static @NotNull Component toMMwPrefix(@Nullable String text, @NotNull TagResolver... resolvers) {
        if (text == null || text.isEmpty()) {
            return Component.empty();
        }
        return MM.deserialize(Objects.toString(prefix, "") + Objects.toString(separator, ""))
                .append(MM.deserialize(text, resolvers));
    }

    public static @NotNull List<Component> listToMM(@Nullable List<String> lines, @NotNull TagResolver... resolvers) {
        if (lines == null || lines.isEmpty()) {
            return List.of();
        }
        return lines.stream()
                .map(line -> toMM(line, resolvers))
                .toList();
    }

    public static void send(@NotNull Audience audience, @Nullable String text, @NotNull TagResolver... resolvers) {
        if (text != null && !text.isEmpty()) {
            audience.sendMessage(toMM(text, resolvers));
        }
    }

    public static void send(@NotNull Audience audience, @Nullable List<String> lines, @NotNull TagResolver... resolvers) {
        if (lines == null || lines.isEmpty()) {
            return;
        }
        for (String line : lines) {
            send(audience, line, resolvers);
        }
    }

    public static void sendPrefixed(@NotNull Audience audience, @Nullable String text, @NotNull TagResolver... resolvers) {
        if (text != null && !text.isEmpty()) {
            audience.sendMessage(toMMwPrefix(text, resolvers));
        }
    }
}