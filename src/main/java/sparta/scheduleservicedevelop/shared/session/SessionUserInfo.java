package sparta.scheduleservicedevelop.shared.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUserInfo {

    public static Long getId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (Long) session.getAttribute(SessionTags.LOGIN_USER.getTag());
    }
}
