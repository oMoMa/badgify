package solutions.thex.badgify.svg.resolver.badge;

import org.springframework.stereotype.Service;
import solutions.thex.badgify.svg.SvgResolver;
import solutions.thex.badgify.svg.generator.badge.IconGenerator;

import java.io.IOException;
import java.util.Map;

/**
 * An implementation of {@link solutions.thex.badgify.svg.SvgResolver} which resolves given parameters by user to
 * a {@link solutions.thex.badgify.svg.generator.badge.IconGenerator} instance.
 *
 * @author Soroush Shemshadi
 * @version 1.2.0
 * @since 1.0.0
 */
@Service
public class IconResolver extends SvgResolver {

    @Override
    public String resolve(Map<String, String> params) throws IOException {
        final String bg = resolveBG(params.get("bg"));

        return IconGenerator.builder()//
                .theme(params.get("theme"))//
                .size(params.get("size"))//
                .title(params.get("icon"))//
                .icon(resolveIcon(params.get("icon"), getColor(params, bg)))//
                .link(params.get("link"))//
                .width(resolveWidth(params.get("size")))//
                .height(resolveHeight(params.get("size")))//
                .bg(bg)//
                .build().render();
    }

    private String resolveWidth(String size) {
        return switch (size) {
            case "s" -> "24";
            case "m" -> "29";
            case "l" -> "35";
            default -> "";
        };
    }

    private String resolveHeight(String size) {
        return switch (size) {
            case "s" -> "24";
            case "m" -> "29";
            case "l" -> "35";
            default -> "";
        };
    }

}
