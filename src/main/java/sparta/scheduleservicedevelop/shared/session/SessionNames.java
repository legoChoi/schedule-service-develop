package sparta.scheduleservicedevelop.shared.session;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SessionNames {
    LOGIN_USER("login_user");

    private final String tag;
}
