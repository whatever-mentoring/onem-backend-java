package community.whatever.onembackendjava.common.exception.custom;

import community.whatever.onembackendjava.common.exception.ErrorCode;

public class UnAuthorizedException extends BusinessException{

    public UnAuthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
