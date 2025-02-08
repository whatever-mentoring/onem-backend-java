package community.whatever.onembackendjava.common.exception.custom;

import community.whatever.onembackendjava.common.exception.ErrorCode;

public class ExpiredUrlException extends BusinessException{

    public ExpiredUrlException(ErrorCode errorCode) {
        super(errorCode);
    }
}
