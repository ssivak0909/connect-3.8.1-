/*
 * Copyright (c) Mirth Corporation. All rights reserved.
 * 
 * http://www.mirthcorp.com
 * 
 * The software in this package is published under the terms of the MPL license a copy of which has
 * been included with this distribution in the LICENSE.txt file.
 */

package com.mirth.connect.plugins.astmmode;

import com.mirth.connect.donkey.server.message.StreamHandler;
import com.mirth.connect.donkey.server.message.batch.BatchStreamReader;
import com.mirth.connect.model.transmission.TransmissionModeProperties;
import com.mirth.connect.model.transmission.framemode.FrameStreamHandler;
import com.mirth.connect.plugins.TransmissionModeProvider;

import java.io.InputStream;
import java.io.OutputStream;

public class ASTMModeProvider extends TransmissionModeProvider {

    @Override
    public String getPluginPointName() {
        return ASTMModeProperties.PLUGIN_POINT;
    }

    @Override
    public StreamHandler getStreamHandler(InputStream inputStream, OutputStream outputStream, BatchStreamReader batchStreamReader, TransmissionModeProperties transmissionModeProperties) {
        if (transmissionModeProperties instanceof ASTMModeProperties ) {
            return new ASTMv2StreamHandler(inputStream, outputStream, batchStreamReader, transmissionModeProperties);
        } else {
            return new FrameStreamHandler(inputStream, outputStream, batchStreamReader, transmissionModeProperties);
        }
    }

    @Override
    public String toString() {
        return "ASTMModeProvider{}";
    }
}
