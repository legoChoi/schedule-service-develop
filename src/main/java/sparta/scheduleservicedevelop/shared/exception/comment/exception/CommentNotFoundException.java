package sparta.scheduleservicedevelop.shared.exception.comment.exception;

import sparta.scheduleservicedevelop.shared.exception.comment.exception.messages.CommentExceptionMessages;

public class CommentNotFoundException extends RuntimeException {

    private final int errorCode;

    public CommentNotFoundException() {
        super(CommentExceptionMessages.COMMENT_NOT_FOUND.getErrorMessage());
        this.errorCode = CommentExceptionMessages.COMMENT_NOT_FOUND.getErrorCode();
    }
}
