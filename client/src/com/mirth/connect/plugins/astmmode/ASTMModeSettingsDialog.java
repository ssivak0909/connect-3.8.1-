/*
 * Copyright (c) Mirth Corporation. All rights reserved.
 *
 * http://www.mirthcorp.com
 *
 * The software in this package is published under the terms of the MPL license a copy of which has
 * been included with this distribution in the LICENSE.txt file.
 */

package com.mirth.connect.plugins.astmmode;

import com.mirth.connect.client.ui.MirthDialog;
import com.mirth.connect.client.ui.PlatformUI;
import com.mirth.connect.client.ui.UIConstants;
import com.mirth.connect.client.ui.components.MirthFieldConstraints;
import com.mirth.connect.model.transmission.TransmissionModeProperties;
import com.mirth.connect.util.TcpUtil;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ASTMModeSettingsDialog extends MirthDialog implements DocumentListener {

    private boolean saved;
    private String enquiryAbbreviation;
    private String startOfFrameAbbreviation;
    private String maxFrameContentLengthAbbreviation;
    private String intermediateEndOfFrameAbbreviation;
    private String endOfFrameAbbreviation;
    private String checksumByteLengthAbbreviation;
    private String frameTerminatorAbbreviation;
    private String endOfTransimissionAbbreviation;
    private ActionListener actionListener;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ackBytes0XLabel;
    private javax.swing.JLabel ackBytesAbbrevLabel;
    private com.mirth.connect.client.ui.components.MirthTextField ackBytesField;
    private javax.swing.JLabel ackBytesLabel;
    private com.mirth.connect.client.ui.ByteAbbreviationList byteAbbreviationList1;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private com.mirth.connect.client.ui.components.MirthTextField maxRetryCountField;
    private javax.swing.JLabel maxRetryCountLabel;
    private javax.swing.JLabel nackBytes0XLabel;

    // @formatter:off
    private javax.swing.JLabel nackBytesAbbrevLabel;
    // @formatter:on
    private com.mirth.connect.client.ui.components.MirthTextField nackBytesField;
    private javax.swing.JLabel nackBytesLabel;
    private javax.swing.JButton okButton;
    private javax.swing.ButtonGroup useMLLPv2ButtonGroup;
    private javax.swing.JLabel useMLLPv2Label;
    private com.mirth.connect.client.ui.components.MirthRadioButton useMLLPv2NoRadio;
    private com.mirth.connect.client.ui.components.MirthRadioButton useMLLPv2YesRadio;
    private javax.swing.JLabel enquiryLabel;
    private com.mirth.connect.client.ui.components.MirthTextField enquiryField;
    private javax.swing.JLabel enquiryAbbrevLabel;
    private javax.swing.JLabel enquiry0XLabel;
    private javax.swing.JLabel startOfFrame0XLabel;
    private javax.swing.JLabel startOfFrameAbbrevLabel;
    private com.mirth.connect.client.ui.components.MirthTextField startOfFrameField;
    private javax.swing.JLabel startOfFrameLabel;
    private com.mirth.connect.client.ui.components.MirthTextField maxFrameContentLengthField;
    private javax.swing.JLabel maxFrameContentLengthLabel;
    private javax.swing.JLabel intermediateEndOfFrame0XLabel;
    private javax.swing.JLabel intermediateEndOfFrameAbbrevLabel;
    private com.mirth.connect.client.ui.components.MirthTextField intermediateEndOfFrameField;
    private javax.swing.JLabel intermediateEndOfFrameLabel;
    private javax.swing.JLabel endOfFrame0XLabel;
    private javax.swing.JLabel endOfFrameAbbrevLabel;
    private com.mirth.connect.client.ui.components.MirthTextField endOfFrameField;
    private javax.swing.JLabel endOfFrameLabel;
    private com.mirth.connect.client.ui.components.MirthTextField checksumByteLengthField;
    private javax.swing.JLabel checksumByteLengthLabel;
    private javax.swing.JLabel frameTerminator0XLabel;
    private javax.swing.JLabel frameTerminatorAbbrevLabel;
    private com.mirth.connect.client.ui.components.MirthTextField frameTerminatorField;
    private javax.swing.JLabel frameTerminatorLabel;
    private javax.swing.JLabel endOfTransimission0XLabel;
    private javax.swing.JLabel endOfTransimissionAbbrevLabel;
    private com.mirth.connect.client.ui.components.MirthTextField endOfTransimissionField;
    private javax.swing.JLabel endOfTransimissionLabel;

    /**
     * Creates new form MLLPModeSettingsDialog
     */
    public ASTMModeSettingsDialog(ActionListener actionListener) {
        super(PlatformUI.MIRTH_FRAME);
        initComponents();
        this.actionListener = actionListener;

        enquiryField.setDocument(new MirthFieldConstraints(0, true, false, false));
        startOfFrameField.setDocument(new MirthFieldConstraints(0, true, false, false));
        maxFrameContentLengthField.setDocument(new MirthFieldConstraints(0, true, false, false));
        intermediateEndOfFrameField.setDocument(new MirthFieldConstraints(0, false, false, true));
        endOfFrameField.setDocument(new MirthFieldConstraints(0, true, false, false));
        checksumByteLengthField.setDocument(new MirthFieldConstraints(0, true, false, false));
        frameTerminatorField.setDocument(new MirthFieldConstraints(0, true, false, false));
        endOfTransimissionField.setDocument(new MirthFieldConstraints(0, false, false, true));

        enquiryField.getDocument().addDocumentListener(this);
        startOfFrameField.getDocument().addDocumentListener(this);
        maxFrameContentLengthField.getDocument().addDocumentListener(this);
        intermediateEndOfFrameField.getDocument().addDocumentListener(this);
        endOfFrameField.getDocument().addDocumentListener(this);
        checksumByteLengthField.getDocument().addDocumentListener(this);
        frameTerminatorField.getDocument().addDocumentListener(this);
        endOfTransimissionField.getDocument().addDocumentListener(this);

        enquiryAbbreviation = "";
        startOfFrameAbbreviation = "";
        maxFrameContentLengthAbbreviation = "";
        intermediateEndOfFrameAbbreviation = "";
        endOfFrameAbbreviation = "";
        checksumByteLengthAbbreviation = "";
        frameTerminatorAbbreviation = "";
        endOfTransimissionAbbreviation = "";
    }

    @Override
    public void setVisible(boolean b) {
        if (b) {
            Dimension dlgSize = getPreferredSize();
            Dimension frmSize = getParent().getSize();
            Point loc = getParent().getLocation();

            if ((frmSize.width == 0 && frmSize.height == 0) || (loc.x == 0 && loc.y == 0)) {
                setLocationRelativeTo(null);
            } else {
                //  setLocation(421,420);

                setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
            }

            saved = false;
            resetInvalidProperties();
        }

        super.setVisible(b);
    }

    public TransmissionModeProperties getProperties() {
        ASTMModeProperties props = new ASTMModeProperties();

        props.setStartOfMessageBytes(startOfFrameField.getText());
        props.setEndOfMessageBytes(endOfFrameField.getText());
//        props.setUseMLLPv2(useMLLPv2YesRadio.isSelected());
        // props.setAckBytes(ackBytesField.getText());
        //props.setNackBytes(nackBytesField.getText());
        //props.setMaxRetries(maxRetryCountField.getText());

        return props;
    }

    public void setProperties(TransmissionModeProperties properties) {
        ASTMModeProperties props = (ASTMModeProperties) properties;

        startOfFrameField.setText(props.getStartOfMessageBytes());
        endOfFrameField.setText(props.getEndOfMessageBytes());

       /* if (props.isUseMLLPv2()) {
            useMLLPv2YesRadio.setSelected(true);
            useMLLPv2YesRadioActionPerformed(null);
        } else {
            useMLLPv2NoRadio.setSelected(true);
            useMLLPv2NoRadioActionPerformed(null);
        }

        ackBytesField.setText(props.getAckBytes());
        nackBytesField.setText(props.getNackBytes());
        maxRetryCountField.setText(props.getMaxRetries());*/

        enquiryAbbreviation = TcpUtil.convertHexToAbbreviation(enquiryField.getText());
        startOfFrameAbbreviation = TcpUtil.convertHexToAbbreviation(startOfFrameField.getText());
        maxFrameContentLengthAbbreviation = TcpUtil.convertHexToAbbreviation(maxFrameContentLengthField.getText());
        intermediateEndOfFrameAbbreviation = TcpUtil.convertHexToAbbreviation(intermediateEndOfFrameField.getText());
        endOfFrameAbbreviation = TcpUtil.convertHexToAbbreviation(endOfFrameField.getText());
        checksumByteLengthAbbreviation = TcpUtil.convertHexToAbbreviation(checksumByteLengthField.getText());
        frameTerminatorAbbreviation = TcpUtil.convertHexToAbbreviation(frameTerminatorField.getText());
        endOfTransimissionAbbreviation = TcpUtil.convertHexToAbbreviation(endOfTransimissionField.getText());

    }

    public String checkProperties() {
        resetInvalidProperties();
        String errors = "";

        if (!validBytes(startOfFrameField.getText())) {
            errors += "Invalid start of message bytes.\r\n";
            startOfFrameField.setBackground(UIConstants.INVALID_COLOR);
        }
        if (!validBytes(endOfFrameField.getText())) {
            errors += "Invalid end of message bytes.\r\n";
            endOfFrameField.setBackground(UIConstants.INVALID_COLOR);
        }
      /*  if (useMLLPv2YesRadio.isSelected()) {
            if (!validBytes(ackBytesField.getText())) {
                errors += "Invalid affirmative commit acknowledgement bytes.\r\n";
                ackBytesField.setBackground(UIConstants.INVALID_COLOR);
            }
            if (!validBytes(nackBytesField.getText())) {
                errors += "Invalid negative commit acknowledgement bytes.\r\n";
                nackBytesField.setBackground(UIConstants.INVALID_COLOR);
            }
            if (maxRetryCountField.getText().length() == 0) {
                errors += "Invalid maximum retry count.\r\n";
                maxRetryCountField.setBackground(UIConstants.INVALID_COLOR);
            }
        }
*/
        return errors;
    }

    public void resetInvalidProperties() {
        startOfFrameField.setBackground(null);
        endOfFrameField.setBackground(null);
        enquiryField.setBackground(null);
        maxFrameContentLengthField.setBackground(null);
        intermediateEndOfFrameField.setBackground(null);
        checksumByteLengthField.setBackground(null);
        frameTerminatorField.setBackground(null);
        endOfTransimissionField.setBackground(null);
    }

    public boolean isSaved() {
        return saved;
    }

    @Override
    public void changedUpdate(DocumentEvent evt) {
        changeAbbreviation(evt);
    }

    @Override
    public void insertUpdate(DocumentEvent evt) {
        changeAbbreviation(evt);
    }

    @Override
    public void removeUpdate(DocumentEvent evt) {
        changeAbbreviation(evt);
    }

    private void changeAbbreviation(DocumentEvent evt) {
        String text = "";

        try {
            text = evt.getDocument().getText(0, evt.getDocument().getLength()).trim();
        } catch (BadLocationException e) {
        }

        if (evt.getDocument().equals(startOfFrameField.getDocument())) {
            startOfFrameAbbreviation = TcpUtil.convertHexToAbbreviation(text);
            actionListener.actionPerformed(new ActionEvent(startOfFrameField, ActionEvent.ACTION_PERFORMED, ASTMModeClientProvider.CHANGE_START_BYTES_COMMAND));
        } else if (evt.getDocument().equals(endOfFrameField.getDocument())) {
            endOfFrameAbbreviation = TcpUtil.convertHexToAbbreviation(text);
            actionListener.actionPerformed(new ActionEvent(endOfFrameField, ActionEvent.ACTION_PERFORMED, ASTMModeClientProvider.CHANGE_END_BYTES_COMMAND));
        }

        changeAbbreviation();
    }

    private void changeAbbreviation() {
        startOfFrameAbbrevLabel.setText(startOfFrameAbbreviation);
        endOfFrameAbbrevLabel.setText(endOfFrameAbbreviation);
        pack();
    }

    private boolean validBytes(String byteString) {
        return StringUtils.isNotBlank(byteString) && TcpUtil.isValidHexString(byteString);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        useMLLPv2ButtonGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        startOfFrameAbbrevLabel = new javax.swing.JLabel();

        enquiryAbbrevLabel = new javax.swing.JLabel();
        enquiryLabel = new javax.swing.JLabel();
        enquiry0XLabel = new javax.swing.JLabel();
        enquiryField = new com.mirth.connect.client.ui.components.MirthTextField();

        intermediateEndOfFrameAbbrevLabel = new javax.swing.JLabel();
        intermediateEndOfFrameLabel = new javax.swing.JLabel();
        intermediateEndOfFrame0XLabel = new javax.swing.JLabel();
        intermediateEndOfFrameField = new com.mirth.connect.client.ui.components.MirthTextField();

        frameTerminatorAbbrevLabel = new javax.swing.JLabel();
        frameTerminatorLabel = new javax.swing.JLabel();
        frameTerminator0XLabel = new javax.swing.JLabel();
        frameTerminatorField = new com.mirth.connect.client.ui.components.MirthTextField();

        endOfTransimissionAbbrevLabel = new javax.swing.JLabel();
        endOfTransimissionLabel = new javax.swing.JLabel();
        endOfTransimission0XLabel = new javax.swing.JLabel();
        endOfTransimissionField = new com.mirth.connect.client.ui.components.MirthTextField();

        maxFrameContentLengthLabel = new javax.swing.JLabel();
        maxFrameContentLengthField = new com.mirth.connect.client.ui.components.MirthTextField();

        checksumByteLengthLabel = new javax.swing.JLabel();
        checksumByteLengthField = new com.mirth.connect.client.ui.components.MirthTextField();

        startOfFrameLabel = new javax.swing.JLabel();
        endOfFrameLabel = new javax.swing.JLabel();
        startOfFrameField = new com.mirth.connect.client.ui.components.MirthTextField();
        startOfFrame0XLabel = new javax.swing.JLabel();
        endOfFrameAbbrevLabel = new javax.swing.JLabel();
        endOfFrameField = new com.mirth.connect.client.ui.components.MirthTextField();
        endOfFrame0XLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        byteAbbreviationList1 = new com.mirth.connect.client.ui.ByteAbbreviationList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ASTM E1381 Settings");
        setModal(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("ASTM Settings"));
        jPanel2.setMinimumSize(new Dimension(323, 120));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Validation Settings"));
        jPanel3.setMinimumSize(new Dimension(323, 120));

        enquiryAbbrevLabel.setText("<ENQ>");
        enquiryAbbrevLabel.setToolTipText("");
        enquiryLabel.setText("Enquiry:");
        enquiryLabel.setToolTipText("");
        enquiry0XLabel.setText("0x");
        enquiry0XLabel.setToolTipText("");
        enquiryField.setToolTipText("");
        enquiryField.addActionListener(this::enquiryFieldActionPerformed);

        maxFrameContentLengthLabel.setText("Max Frame Content Length:");
        maxFrameContentLengthLabel.setToolTipText("");
        maxFrameContentLengthField.setToolTipText("");
        maxFrameContentLengthField.addActionListener(this::enquiryFieldActionPerformed);

        intermediateEndOfFrameAbbrevLabel.setText("<ETB>");
        intermediateEndOfFrameAbbrevLabel.setToolTipText("");
        intermediateEndOfFrameLabel.setText("Intermediate End Of Frame:");
        intermediateEndOfFrameLabel.setToolTipText("");
        intermediateEndOfFrame0XLabel.setText("0x");
        intermediateEndOfFrame0XLabel.setToolTipText("");
        intermediateEndOfFrameField.setToolTipText("");
        intermediateEndOfFrameField.addActionListener(this::enquiryFieldActionPerformed);

        checksumByteLengthLabel.setText("Checksum Byte Length Label:");
        checksumByteLengthLabel.setToolTipText("");
        checksumByteLengthField.setToolTipText("");
        checksumByteLengthField.addActionListener(this::enquiryFieldActionPerformed);

        frameTerminatorAbbrevLabel.setText("<CR><LF>");
        frameTerminatorAbbrevLabel.setToolTipText("");
        frameTerminatorLabel.setText("Frame Terminator:");
        frameTerminatorLabel.setToolTipText("");
        frameTerminator0XLabel.setText("0x");
        frameTerminator0XLabel.setToolTipText("");
        frameTerminatorField.setToolTipText("");
        frameTerminatorField.addActionListener(this::enquiryFieldActionPerformed);

        endOfTransimissionAbbrevLabel.setText("<EOT>");
        endOfTransimissionAbbrevLabel.setToolTipText("");
        endOfTransimissionLabel.setText("End Of Transmission:");
        endOfTransimissionLabel.setToolTipText("");
        endOfTransimission0XLabel.setText("0x");
        endOfTransimission0XLabel.setToolTipText("");
        endOfTransimissionField.setToolTipText("");
        endOfTransimissionField.addActionListener(this::enquiryFieldActionPerformed);

        startOfFrameAbbrevLabel.setText("<STX>");
        startOfFrameAbbrevLabel.setToolTipText("<html>The ASTMv2 Start Block bytes before the beginning of the actual message.<br/>Only valid hexidecimal characters (0-9, A-F) are allowed.</html>");

        startOfFrameLabel.setText("Start of Frame:");
        startOfFrameLabel.setToolTipText("<html>The ASTMv2 Start Block bytes before the beginning of the actual message.<br/>Only valid hexidecimal characters (0-9, A-F) are allowed.</html>");

        endOfFrameLabel.setText("End of Frame:");
        endOfFrameLabel.setToolTipText("<html>The ASTMv2 End Data/Block bytes after the end of the actual message.<br/>Only valid hexidecimal characters (0-9, A-F) are allowed.</html>");

        startOfFrameField.setToolTipText("<html>The ASTMv2 Start Block bytes before the beginning of the actual message.<br/>Only valid hexidecimal characters (0-9, A-F) are allowed.</html>");
        startOfFrameField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                startOfFrameFieldActionPerformed(evt);
            }
        });

        startOfFrame0XLabel.setText("0x");
        startOfFrame0XLabel.setToolTipText("<html>The ASTMv2 Start Block bytes before the beginning of the actual message.<br/>Only valid hexidecimal characters (0-9, A-F) are allowed.</html>");

        endOfFrameAbbrevLabel.setText("<ETX>");
        endOfFrameAbbrevLabel.setToolTipText("<html>The ASTMv2 End Data/Block bytes after the end of the actual message.<br/>Only valid hexidecimal characters (0-9, A-F) are allowed.</html>");

        endOfFrameField.setToolTipText("<html>The ASTMv2 End Data/Block bytes after the end of the actual message.<br/>Only valid hexidecimal characters (0-9, A-F) are allowed.</html>");
        endOfFrameField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                endOfFrameFieldActionPerformed(evt);
            }
        });

        endOfFrame0XLabel.setText("0x");
        endOfFrame0XLabel.setToolTipText("<html>The ASTMv2 End Data/Block bytes after the end of the actual message.<br/>Only valid hexidecimal characters (0-9, A-F) are allowed.</html>");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(enquiryLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(startOfFrameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(maxFrameContentLengthLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(intermediateEndOfFrameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(endOfFrameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(checksumByteLengthLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(frameTerminatorLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(endOfTransimissionLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(enquiry0XLabel)
                                                .addGap(3, 3, 3)
                                                .addComponent(enquiryField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(enquiryAbbrevLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(maxFrameContentLengthField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addComponent(startOfFrame0XLabel)
                                                                .addGap(3, 3, 3)
                                                                .addComponent(startOfFrameField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(startOfFrameAbbrevLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        /*.addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(startOfFrame0XLabel)
                                                .addGap(3, 3, 3)
                                                .addComponent(startOfFrameField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(startOfFrameAbbrevLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(endOfFrame0XLabel)
                                                .addGap(3, 3, 3)
                                                .addComponent(maxFrameContentLengthField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(endOfFrameAbbrevLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))*/
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(intermediateEndOfFrame0XLabel)
                                                .addGap(3, 3, 3)
                                                .addComponent(intermediateEndOfFrameField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(intermediateEndOfFrameAbbrevLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(checksumByteLengthField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addComponent(endOfFrame0XLabel)
                                                                .addGap(3, 3, 3)
                                                                .addComponent(endOfFrameField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(endOfFrameAbbrevLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        /*.addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(endOfFrame0XLabel)
                                                .addGap(3, 3, 3)
                                                .addComponent(endOfFrameField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(endOfFrameAbbrevLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(startOfFrame0XLabel)
                                                .addGap(3, 3, 3)
                                                .addComponent(checksumByteLengthField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(startOfFrameAbbrevLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))*/
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(frameTerminator0XLabel)
                                                .addGap(3, 3, 3)
                                                .addComponent(frameTerminatorField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(frameTerminatorAbbrevLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(endOfTransimission0XLabel)
                                                .addGap(3, 3, 3)
                                                .addComponent(endOfTransimissionField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(endOfTransimissionAbbrevLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(enquiryLabel)
                                        .addComponent(enquiry0XLabel)
                                        .addComponent(enquiryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(enquiryAbbrevLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(startOfFrameLabel)
                                        .addComponent(startOfFrame0XLabel)
                                        .addComponent(startOfFrameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(startOfFrameAbbrevLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(maxFrameContentLengthLabel)
                                        .addComponent(maxFrameContentLengthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(intermediateEndOfFrameLabel)
                                        .addComponent(intermediateEndOfFrame0XLabel)
                                        .addComponent(intermediateEndOfFrameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(intermediateEndOfFrameAbbrevLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(endOfFrameLabel)
                                        .addComponent(endOfFrame0XLabel)
                                        .addComponent(endOfFrameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(endOfFrameAbbrevLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(checksumByteLengthLabel)
                                        .addComponent(checksumByteLengthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(frameTerminatorLabel)
                                        .addComponent(frameTerminator0XLabel)
                                        .addComponent(frameTerminatorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(frameTerminatorAbbrevLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(endOfTransimissionLabel)
                                        .addComponent(endOfTransimission0XLabel)
                                        .addComponent(endOfTransimissionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(endOfTransimissionAbbrevLabel))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );


        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(enquiryLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(startOfFrameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(maxFrameContentLengthLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(intermediateEndOfFrameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(endOfFrameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(checksumByteLengthLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(frameTerminatorLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(endOfTransimissionLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(enquiry0XLabel)
                                                .addGap(3, 3, 3)
                                                .addComponent(enquiryField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(enquiryAbbrevLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(maxFrameContentLengthField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addComponent(startOfFrame0XLabel)
                                                                .addGap(3, 3, 3)
                                                                .addComponent(startOfFrameField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(startOfFrameAbbrevLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        /*.addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(startOfFrame0XLabel)
                                                .addGap(3, 3, 3)
                                                .addComponent(startOfFrameField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(startOfFrameAbbrevLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(endOfFrame0XLabel)
                                                .addGap(3, 3, 3)
                                                .addComponent(maxFrameContentLengthField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(endOfFrameAbbrevLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))*/
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(intermediateEndOfFrame0XLabel)
                                                .addGap(3, 3, 3)
                                                .addComponent(intermediateEndOfFrameField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(intermediateEndOfFrameAbbrevLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(checksumByteLengthField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                                .addComponent(endOfFrame0XLabel)
                                                                .addGap(3, 3, 3)
                                                                .addComponent(endOfFrameField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(endOfFrameAbbrevLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        /*.addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(endOfFrame0XLabel)
                                                .addGap(3, 3, 3)
                                                .addComponent(endOfFrameField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(endOfFrameAbbrevLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(startOfFrame0XLabel)
                                                .addGap(3, 3, 3)
                                                .addComponent(checksumByteLengthField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(startOfFrameAbbrevLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))*/
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(frameTerminator0XLabel)
                                                .addGap(3, 3, 3)
                                                .addComponent(frameTerminatorField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(frameTerminatorAbbrevLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(endOfTransimission0XLabel)
                                                .addGap(3, 3, 3)
                                                .addComponent(endOfTransimissionField, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(endOfTransimissionAbbrevLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(enquiryLabel)
                                        .addComponent(enquiry0XLabel)
                                        .addComponent(enquiryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(enquiryAbbrevLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(startOfFrameLabel)
                                        .addComponent(startOfFrame0XLabel)
                                        .addComponent(startOfFrameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(startOfFrameAbbrevLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(maxFrameContentLengthLabel)
                                        .addComponent(maxFrameContentLengthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(intermediateEndOfFrameLabel)
                                        .addComponent(intermediateEndOfFrame0XLabel)
                                        .addComponent(intermediateEndOfFrameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(intermediateEndOfFrameAbbrevLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(endOfFrameLabel)
                                        .addComponent(endOfFrame0XLabel)
                                        .addComponent(endOfFrameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(endOfFrameAbbrevLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(checksumByteLengthLabel)
                                        .addComponent(checksumByteLengthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(frameTerminatorLabel)
                                        .addComponent(frameTerminator0XLabel)
                                        .addComponent(frameTerminatorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(frameTerminatorAbbrevLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(endOfTransimissionLabel)
                                        .addComponent(endOfTransimission0XLabel)
                                        .addComponent(endOfTransimissionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(endOfTransimissionAbbrevLabel))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );


        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        okButton.setText("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(okButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cancelButton))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jSeparator1)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(byteAbbreviationList1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jSeparator1)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(byteAbbreviationList1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(byteAbbreviationList1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cancelButton)
                                        .addComponent(okButton))
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startOfFrameFieldActionPerformed(ActionEvent evt) {//GEN-FIRST:event_startOfFrameFieldActionPerformed
    }//GEN-LAST:event_startOfFrameFieldActionPerformed

    private void enquiryFieldActionPerformed(ActionEvent evt) {//GEN-FIRST:event_startOfFrameFieldActionPerformed
    }

    private void cancelButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        String errors = checkProperties();
        if (!errors.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error validating ASTMv2 transmission mode settings.\r\n\r\n" + errors, "Validation Error", JOptionPane.ERROR_MESSAGE);
        } else {
            saved = true;
            PlatformUI.MIRTH_FRAME.setSaveEnabled(true);
            dispose();
        }
    }//GEN-LAST:event_okButtonActionPerformed

    private void endOfFrameFieldActionPerformed(ActionEvent evt) {//GEN-FIRST:event_endOfFrameFieldActionPerformed
    }//GEN-LAST:event_endOfFrameFieldActionPerformed

    private void ackBytesFieldActionPerformed(ActionEvent evt) {//GEN-FIRST:event_ackBytesFieldActionPerformed
    }//GEN-LAST:event_ackBytesFieldActionPerformed

    private void nackBytesFieldActionPerformed(ActionEvent evt) {//GEN-FIRST:event_nackBytesFieldActionPerformed
    }//GEN-LAST:event_nackBytesFieldActionPerformed

    private void maxRetryCountFieldActionPerformed(ActionEvent evt) {//GEN-FIRST:event_maxRetryCountFieldActionPerformed
    }//GEN-LAST:event_maxRetryCountFieldActionPerformed

    private void useMLLPv2YesRadioActionPerformed(ActionEvent evt) {//GEN-FIRST:event_useMLLPv2YesRadioActionPerformed
        ackBytesLabel.setEnabled(true);
        ackBytes0XLabel.setEnabled(true);
        ackBytesField.setEnabled(true);
        ackBytesAbbrevLabel.setEnabled(true);

        nackBytesLabel.setEnabled(true);
        nackBytes0XLabel.setEnabled(true);
        nackBytesField.setEnabled(true);
        nackBytesAbbrevLabel.setEnabled(true);

        maxRetryCountLabel.setEnabled(true);
        maxRetryCountField.setEnabled(true);
    }//GEN-LAST:event_useMLLPv2YesRadioActionPerformed

    private void useMLLPv2NoRadioActionPerformed(ActionEvent evt) {//GEN-FIRST:event_useMLLPv2NoRadioActionPerformed
        ackBytesLabel.setEnabled(false);
        ackBytes0XLabel.setEnabled(false);
        ackBytesField.setEnabled(false);
        ackBytesAbbrevLabel.setEnabled(false);

        nackBytesLabel.setEnabled(false);
        nackBytes0XLabel.setEnabled(false);
        nackBytesField.setEnabled(false);
        nackBytesAbbrevLabel.setEnabled(false);

        maxRetryCountLabel.setEnabled(false);
        maxRetryCountField.setEnabled(false);
    }//GEN-LAST:event_useMLLPv2NoRadioActionPerformed


    // End of variables declaration//GEN-END:variables
}
