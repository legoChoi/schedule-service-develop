package sparta.scheduleservicedevelop.apis.controller.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateCommentReqDto {

    private Long userId;

    @NotNull
    private Long scheduleId;

    @NotBlank @Size(min = 1, max = 100)
    private String contents;
}
