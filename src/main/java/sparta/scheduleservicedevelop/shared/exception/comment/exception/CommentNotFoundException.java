package sparta.scheduleservicedevelop.shared.exception.comment.exception;

import lombok.Getter;
import sparta.scheduleservicedevelop.shared.exception.comment.exception.messages.CommentExceptionMessages;

@Getter
public class CommentNotFoundException extends RuntimeException {

    private final int errorCode = CommentExceptionMessages.COMMENT_NOT_FOUND.getErrorCode();

    public CommentNotFoundException() {
        super(CommentExceptionMessages.COMMENT_NOT_FOUND.getErrorMessage());
    }
}
