package io.github.shuoros.badgify.web.controller;

import io.github.shuoros.badgify.aop.controller.InterceptIconBadgeController;
import io.github.shuoros.badgify.aop.controller.InterceptLabelBadgeController;
import io.github.shuoros.badgify.aop.controller.InterceptTextBadgeController;
import io.github.shuoros.badgify.domain.enumeration.Size;
import io.github.shuoros.badgify.domain.enumeration.Theme;
import io.github.shuoros.badgify.domain.model.badge.IconBadge;
import io.github.shuoros.badgify.domain.model.badge.LabelBadge;
import io.github.shuoros.badgify.domain.model.badge.TextBadge;
import io.github.shuoros.badgify.domain.model.color.AbstractColor;
import io.github.shuoros.badgify.domain.model.icon.AbstractIcon;
import io.github.shuoros.badgify.service.badge.IconBadgeGeneratorService;
import io.github.shuoros.badgify.service.badge.LabelBadgeGeneratorService;
import io.github.shuoros.badgify.service.badge.TextBadgeGeneratorService;
import io.github.shuoros.badgify.util.editor.CaseInsensitiveEnumEditor;
import io.github.shuoros.badgify.util.editor.ColorEditor;
import io.github.shuoros.badgify.util.editor.IconEditor;
import javax.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/badge")
public class BadgeController {

    @Resource
    private ColorEditor colorEditor;

    @Resource
    private IconEditor iconEditor;

    @Resource(name = "theme-enum-editor")
    private CaseInsensitiveEnumEditor themeCaseInsensitiveEnumEditor;

    @Resource(name = "size-enum-editor")
    private CaseInsensitiveEnumEditor sizeCaseInsensitiveEnumEditor;

    @Resource
    private LabelBadgeGeneratorService labelBadgeGeneratorService;

    @Resource
    private IconBadgeGeneratorService iconBadgeGeneratorService;

    @Resource
    private TextBadgeGeneratorService textBadgeGeneratorService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(AbstractColor.class, colorEditor);
        binder.registerCustomEditor(AbstractIcon.class, iconEditor);
        binder.registerCustomEditor(Theme.class, themeCaseInsensitiveEnumEditor);
        binder.registerCustomEditor(Size.class, sizeCaseInsensitiveEnumEditor);
    }

    /**
     * http://localhost:8080/api/badge/label?text=Badgify&fontColor=lavender&size=l&theme=edge
     *
     * @param labelBadge
     * @return
     * @throws Exception
     */
    @GetMapping(path = "/label", produces = "image/svg+xml")
    @InterceptLabelBadgeController
    public ResponseEntity<String> labelBadge(LabelBadge labelBadge) throws Exception {
        return ResponseEntity.ok().body(labelBadgeGeneratorService.generate(labelBadge));
    }

    /**
     * http://localhost:8080/api/badge/icon?icon=linkedin&fontColor=lavender&size=l&theme=edge
     *
     * @param iconBadge
     * @return
     * @throws Exception
     */
    @GetMapping(path = "/icon", produces = "image/svg+xml")
    @InterceptIconBadgeController
    public ResponseEntity<String> iconBadge(IconBadge iconBadge) throws Exception {
        return ResponseEntity.ok().body(iconBadgeGeneratorService.generate(iconBadge));
    }

    /**
     * http://localhost:8080/api/badge/text?text=Badgify&fontColor=lavender&size=l&theme=edge
     *
     * @param textBadge
     * @return
     * @throws Exception
     */
    @GetMapping(path = "/text", produces = "image/svg+xml")
    @InterceptTextBadgeController
    public ResponseEntity<String> textBadge(TextBadge textBadge) throws Exception {
        return ResponseEntity.ok().body(textBadgeGeneratorService.generate(textBadge));
    }
}
