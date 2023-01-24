package solutions.thex.badgify.svg.resolver.badge.util.color;

import solutions.thex.badgify.svg.resolver.badge.util.ColorResolver;
import solutions.thex.badgify.svg.resolver.badge.util.DefaultColor;

import java.util.Objects;

public class DefaultColorResolver extends ColorResolver {

    public static String resolve(String color) {
        return Objects.requireNonNull(DefaultColor.getColor(color)).toString();
    }

}
