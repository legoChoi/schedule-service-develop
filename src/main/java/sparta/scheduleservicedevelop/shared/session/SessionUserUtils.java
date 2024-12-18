package sparta.scheduleservicedevelop.shared.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUserUtils {

    public static Long getId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (Long) session.getAttribute(SessionTags.LOGIN_USER.getTag());
    }

    public static void setId(Long id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionTags.LOGIN_USER.getTag(), id);
    }
}
