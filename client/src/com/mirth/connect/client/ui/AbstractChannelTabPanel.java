/*
 * Copyright (c) Mirth Corporation. All rights reserved.
 * 
 * http://www.mirthcorp.com
 * 
 * The software in this package is published under the terms of the MPL license a copy of which has
 * been included with this distribution in the LICENSE.txt file.
 */

package com.mirth.connect.client.ui;

import javax.swing.JPanel;

import com.mirth.connect.model.Channel;

public abstract class AbstractChannelTabPanel extends JPanel {

    public abstract void load(Channel channel);

    public abstract void save(Channel channel);
}