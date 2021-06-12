package com.Insurance.hm.board.exception;

import com.Insurance.hm.board.constants.BoardErrorConstants;
import com.Insurance.hm.global.exception.BusinessException;
import com.Insurance.hm.global.exception.ErrorConstants;

public class OverFileSizeFileIndexError extends BusinessException {
    public OverFileSizeFileIndexError(ErrorConstants errorConstants) {
        super(errorConstants);
    }
}
