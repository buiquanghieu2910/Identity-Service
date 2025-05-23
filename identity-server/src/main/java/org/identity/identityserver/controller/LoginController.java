package org.identity.identityserver.controller;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.identity.identityserver.service.ApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author BUI_QUANG_HIEU
 * 5/21/2025
 * LoginController.java
 */
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final ApplicationService applicationService;

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String client_id, @RequestParam(required = false) String redirect_uri, HttpSession session, Model model) {
        if (StringUtils.isNotBlank(client_id)) {
            session.setAttribute("original_client_id", client_id);
            model.addAttribute("clientId", client_id);
            model.addAttribute("applicationName", applicationService.getApplicationNameByClientId(client_id));
        }
        if (StringUtils.isNotBlank(redirect_uri)) {
            session.setAttribute("original_redirect_uri", redirect_uri);
            model.addAttribute("redirectUri", redirect_uri);
        }
        return "login";
    }

}
