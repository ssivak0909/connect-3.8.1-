/*
 * Copyright (c) Mirth Corporation. All rights reserved.
 * 
 * http://www.mirthcorp.com
 * 
 * The software in this package is published under the terms of the MPL license a copy of which has
 * been included with this distribution in the LICENSE.txt file.
 */

package com.mirth.connect.plugins.astmmode;

import com.mirth.connect.model.transmission.TransmissionModeProperties;
import com.mirth.connect.model.transmission.framemode.FrameModeProperties;
import com.mirth.connect.plugins.FrameTransmissionModeClientProvider;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ASTMModeClientProvider extends FrameTransmissionModeClientProvider {

    static final String CHANGE_START_BYTES_COMMAND = "changeStartBytes";
    static final String CHANGE_END_BYTES_COMMAND = "changeEndBytes";

    protected ASTMModeSettingsPanel settingsPanel;
    private ASTMModeProperties mllpModeProperties;

    @Override
    public void initialize(ActionListener actionListener) {
        super.initialize(actionListener);
        settingsPanel = new ASTMModeSettingsPanel(this);
        super.settingsPanel.switchComponent(settingsPanel);
        setProperties(new ASTMModeProperties());
    }

    @Override
    public TransmissionModeProperties getProperties() {
        FrameModeProperties frameModeProperties = (FrameModeProperties) super.getProperties();
        mllpModeProperties.setStartOfMessageBytes(frameModeProperties.getStartOfMessageBytes());
        mllpModeProperties.setEndOfMessageBytes(frameModeProperties.getEndOfMessageBytes());
        return mllpModeProperties;
    }

    @Override
    public TransmissionModeProperties getDefaultProperties() {
        return new ASTMModeProperties();
    }

    @Override
    public void setProperties(TransmissionModeProperties properties) {
        super.setProperties(properties);

        if (properties instanceof ASTMModeProperties) {
            mllpModeProperties = (ASTMModeProperties) properties;
        } else {
            mllpModeProperties = new ASTMModeProperties();
            FrameModeProperties frameModeProperties = (FrameModeProperties) properties;
            mllpModeProperties.setStartOfMessageBytes(frameModeProperties.getStartOfMessageBytes());
            mllpModeProperties.setEndOfMessageBytes(frameModeProperties.getEndOfMessageBytes());
        }

        changeSampleValue();
    }

    @Override
    public JComponent getSettingsComponent() {
        return settingsPanel;
    }

    @Override
    public String getSampleLabel() {
        return "ASTM Sample Frame:";
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals(CHANGE_START_BYTES_COMMAND)) {
            super.settingsPanel.startOfMessageBytesField.setText(((JTextField) evt.getSource()).getText());
        } else if (evt.getActionCommand().equals(CHANGE_END_BYTES_COMMAND)) {
            super.settingsPanel.endOfMessageBytesField.setText(((JTextField) evt.getSource()).getText());
        } else {
            ASTMModeSettingsDialog settingsDialog = new ASTMModeSettingsDialog(this);
            settingsDialog.setProperties(mllpModeProperties);
            settingsDialog.setVisible(true);

            if (settingsDialog.isSaved()) {
                setProperties(settingsDialog.getProperties());
            } else {
                setProperties(mllpModeProperties);
            }
        }
    }
}