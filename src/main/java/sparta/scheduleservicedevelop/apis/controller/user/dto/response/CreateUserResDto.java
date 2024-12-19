package sparta.scheduleservicedevelop.apis.controller.user.dto.response;

import lombok.Builder;
import lombok.Getter;
import sparta.scheduleservicedevelop.entity.User;

import java.time.LocalDateTime;

@Getter
public class CreateUserResDto {
    private final Long id;
    private final String userName;
    private final String email;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    @Builder
    public CreateUserResDto(Long id, String userName, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static CreateUserResDto from(User user) {
        return CreateUserResDto.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
