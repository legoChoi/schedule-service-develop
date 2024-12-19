package sparta.scheduleservicedevelop.apis.controller.user.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import sparta.scheduleservicedevelop.entity.User;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class FetchUserResDto {
    private final Long id;
    private final String userName;
    private final String email;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static FetchUserResDto from(User user) {
        return FetchUserResDto.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
