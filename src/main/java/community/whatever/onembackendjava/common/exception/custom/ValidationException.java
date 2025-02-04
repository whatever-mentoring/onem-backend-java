package community.whatever.onembackendjava.common.exception.custom;

import community.whatever.onembackendjava.common.exception.ErrorCode;

public class ValidationException extends BusinessException {

    public ValidationException(ErrorCode errorCode) {
        super(errorCode);
    }

}
