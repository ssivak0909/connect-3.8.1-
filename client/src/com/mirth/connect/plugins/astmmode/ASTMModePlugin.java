/*
 * Copyright (c) Mirth Corporation. All rights reserved.
 * 
 * http://www.mirthcorp.com
 * 
 * The software in this package is published under the terms of the MPL license a copy of which has
 * been included with this distribution in the LICENSE.txt file.
 */

package com.mirth.connect.plugins.astmmode;

import com.mirth.connect.plugins.TransmissionModeClientProvider;
import com.mirth.connect.plugins.TransmissionModePlugin;
import com.mirth.connect.plugins.mllpmode.MLLPModeClientProvider;
import com.mirth.connect.plugins.mllpmode.MLLPModeProperties;

public class ASTMModePlugin extends TransmissionModePlugin {

    public ASTMModePlugin(String pluginName) {
        super(pluginName);
    }

    @Override
    public String getPluginPointName() {
        return ASTMModeProperties.PLUGIN_POINT;
    }

    @Override
    public TransmissionModeClientProvider createProvider() {
        return new ASTMModeClientProvider();
    }
}