package community.whatever.onembackendjava.common.exception.custom;

import community.whatever.onembackendjava.common.exception.ErrorCode;

public class NotFoundException extends BusinessException{

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

}
