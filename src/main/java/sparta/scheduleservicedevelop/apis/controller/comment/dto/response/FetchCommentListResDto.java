package sparta.scheduleservicedevelop.apis.controller.comment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FetchCommentListResDto {
    private final int count;
    private final List<FetchCommentResDto> data;
}
