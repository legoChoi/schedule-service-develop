package sparta.scheduleservicedevelop.shared.exception.comment.exception.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CommentExceptionMessages {
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND.value(),"댓글이 존재하지 않습니다.");

    private final int errorCode;
    private final String errorMessage;
}
