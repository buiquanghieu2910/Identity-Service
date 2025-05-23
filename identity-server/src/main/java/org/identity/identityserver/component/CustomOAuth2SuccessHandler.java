package org.identity.identityserver.component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.identity.identityserver.model.entity.Application;
import org.identity.identityserver.repository.ApplicationRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author BUI_QUANG_HIEU
 * 5/21/2025
 * CustomOAuth2SuccessHandler.java
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final ApplicationRepository applicationRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        // Đảm bảo chỉ xử lý nếu là OAuth2 login
        if (!(authentication instanceof OAuth2AuthenticationToken oauthToken)) {
            handleError(request, response, "Unsupported+authentication+type", null, null);
            return;
        }

        // Lấy tên nhà cung cấp đăng nhập (google, github, facebook, ...)
        String provider = oauthToken.getAuthorizedClientRegistrationId();

        // Lấy thông tin người dùng từ provider
        OAuth2User oAuth2User = oauthToken.getPrincipal();

        // Lấy lại thông tin client đã lưu trước khi redirect sang OAuth provider
        String clientId = (String) request.getSession().getAttribute("original_client_id");
        String redirectUri = (String) request.getSession().getAttribute("original_redirect_uri");

        // Kiểm tra thông tin client có bị thiếu không
        if (clientId == null || redirectUri == null) {
            handleError(request, response, "Missing+client+info+in+session.", clientId, redirectUri);
            return;
        }

        // Tìm application theo clientId
        var applicationOptional = applicationRepository.findByClientId(clientId);
        if (applicationOptional.isEmpty()) {
            handleError(request, response, "Invalid+client+ID.", clientId, redirectUri);
            return;
        }
        var application = applicationOptional.get();

        // Kiểm tra xem provider đó có được bật cho application này không
        if (!isProviderEnabledForApp(provider, application)) {
            handleError(request, response, "Unsupported+authentication+type.", clientId, redirectUri);
            return;
        }

        // Lấy identifier duy nhất từ provider (email, id, ...)
        String identifier = extractIdentifier(provider, oAuth2User);
        if (identifier == null) {
            handleError(request, response, "Unable+to+extract+user+identifier.", clientId, redirectUri);
            return;
        }

        // Kiểm tra xem người dùng này có được phép truy cập hệ thống không
        boolean isAuthorized = applicationRepository.checkAcceptByIdentifier(clientId, identifier);
        if (!isAuthorized) {
            handleError(request, response, String.format("User+%s+is+not+authorized.+Please+contact+the+administrator.", identifier), clientId, redirectUri);
            return;
        }

        // Redirect về redirectUri nếu chưa gửi response
        if (!response.isCommitted()) {
            response.sendRedirect(redirectUri);
        }
    }

    // ===============================
    // ✅ Hàm kiểm tra provider có được bật cho app không
    // ===============================
    private boolean isProviderEnabledForApp(String provider, Application app) {
        return switch (provider) {
            case "google" -> Boolean.TRUE.equals(app.getGoogleLoginEnable());
//            case "github" -> Boolean.TRUE.equals(app.getGithubLoginEnable());
//            case "facebook" -> Boolean.TRUE.equals(app.getFacebookLoginEnable());
            default -> false;
        };
    }

    // ===============================
    // ✅ Hàm trích xuất thông tin định danh duy nhất từ OAuth2User
    // ===============================
    private String extractIdentifier(String provider, OAuth2User user) {
        return switch (provider) {
            case "google" -> user.getAttribute("email"); // Google dùng email
            case "github" -> {
                Object id = user.getAttribute("id");
                yield id != null ? id.toString() : null;  // GitHub trả về id số
            }
            case "facebook" -> user.getAttribute("id"); // Facebook trả về id dạng chuỗi
            default -> null;
        };
    }

    private void handleError(HttpServletRequest request, HttpServletResponse response, String error, String clientId, String redirectUri) throws IOException {
        SecurityContextHolder.clearContext();

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("/login?client_id=" + clientId + "&redirect_uri=" + redirectUri + "&error=" + error);
    }
}

