package sparta.scheduleservicedevelop.apis.controller.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateCommentReqDto {

    @NotBlank @Size(min = 1, max = 100)
    private String contents;
}
