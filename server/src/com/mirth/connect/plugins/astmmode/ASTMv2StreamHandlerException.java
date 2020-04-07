/*
 * Copyright (c) Mirth Corporation. All rights reserved.
 * 
 * http://www.mirthcorp.com
 * 
 * The software in this package is published under the terms of the MPL license a copy of which has
 * been included with this distribution in the LICENSE.txt file.
 */

package com.mirth.connect.plugins.astmmode;

import com.mirth.connect.model.transmission.StreamHandlerException;

public class ASTMv2StreamHandlerException extends StreamHandlerException {

    public ASTMv2StreamHandlerException() {
        super();
    }

    public ASTMv2StreamHandlerException(String message) {
        super(message);
    }

    public ASTMv2StreamHandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ASTMv2StreamHandlerException(Throwable cause) {
        super(cause);
    }
}
