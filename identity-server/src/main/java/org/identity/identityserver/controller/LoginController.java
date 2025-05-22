package org.identity.identityserver.controller;

import jakarta.servlet.http.HttpSession;
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
public class LoginController {

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String client_id, @RequestParam(required = false) String redirect_uri, HttpSession session, Model model) {
        if (client_id != null) {
            session.setAttribute("original_client_id", client_id);
            session.setAttribute("original_redirect_uri", redirect_uri);
            model.addAttribute("clientId", client_id);
            model.addAttribute("redirectUri", redirect_uri);
        }
        return "login";
    }

}
