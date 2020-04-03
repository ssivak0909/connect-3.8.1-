/*
 * Copyright (c) Mirth Corporation. All rights reserved.
 * 
 * http://www.mirthcorp.com
 * 
 * The software in this package is published under the terms of the MPL license a copy of which has
 * been included with this distribution in the LICENSE.txt file.
 */

package com.mirth.connect.client.ui.editors;

import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;

import javax.swing.border.TitledBorder;

import com.mirth.connect.client.ui.TreePanel;

public class MessageTreePanel extends javax.swing.JPanel {

    public static final String MAPPER_PREFIX = "msg";
    public static final String MAPPER_SUFFIX = ".toString()";
    public static final String MESSAGE_BUILDER_PREFIX = "tmp";
    public static final String MESSAGE_BUILDER_SUFFIX = "";

    private BaseEditorPane<?, ?> editorPane;

    public MessageTreePanel(BaseEditorPane<?, ?> editorPane) {
        this.editorPane = editorPane;
        initComponents();

        ((TitledBorder) treePanelInbound.getBorder()).setTitle("Inbound Message Template Tree");
        ((TitledBorder) treePanelOutbound.getBorder()).setTitle("Outbound Message Template Tree");

        treePanelInbound.setPrefix(MAPPER_PREFIX);
        treePanelInbound.setSuffix(MAPPER_SUFFIX);
        treePanelInbound.setupPopupMenu();

        treePanelOutbound.setPrefix(MESSAGE_BUILDER_PREFIX);
        treePanelOutbound.setSuffix(MESSAGE_BUILDER_SUFFIX);
        treePanelOutbound.setupPopupMenu();

        try {
            split.addHierarchyListener(new HierarchyListener() {

                public void hierarchyChanged(HierarchyEvent e) {
                    if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0) {
                        split.setDividerLocation(.5); //There we set the initial divider locatino
                        //split.removeHierarchyListener(this);
                    }
                }
            });
        } catch (Exception e) {
        }
        split.setOneTouchExpandable(true);
    }

    public void hideOutbound() {
        split.setBottomComponent(null);
        split.setDividerSize(0);
        split.setOneTouchExpandable(false);
    }

    public void showOutbound() {
        split.setBottomComponent(treePanelOutbound);
        split.setDividerSize(6);
        split.setDividerLocation(.5);
        split.setResizeWeight(.5);
        split.setOneTouchExpandable(true);
    }

    public TreePanel getInboundTreePanel() {
        return this.treePanelInbound;
    }

    public TreePanel getOutboundTreePanel() {
        return this.treePanelOutbound;
    }

    public void clearInboundTree() {
        treePanelInbound.clearMessage();
    }

    public void clearOutboundTree() {
        treePanelOutbound.clearMessage();
    }

    public void resizePanes() {
        split.setResizeWeight(.5);
        split.setDividerLocation(.5);
    }

    // @formatter:off
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        split = new javax.swing.JSplitPane();
        treeScrollPaneOutbound = new javax.swing.JScrollPane();
        treePanelOutbound = new com.mirth.connect.client.ui.TreePanel(editorPane);
        treeScrollPaneInbound = new javax.swing.JScrollPane();
        treePanelInbound = new com.mirth.connect.client.ui.TreePanel(editorPane);

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        split.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        split.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        treeScrollPaneOutbound.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        treeScrollPaneOutbound.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        treeScrollPaneOutbound.setViewportView(treePanelOutbound);

        split.setRightComponent(treeScrollPaneOutbound);

        treeScrollPaneInbound.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        treeScrollPaneInbound.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        treeScrollPaneInbound.setViewportView(treePanelInbound);

        split.setLeftComponent(treeScrollPaneInbound);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(split, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(split, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // @formatter:on

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane split;
    private com.mirth.connect.client.ui.TreePanel treePanelInbound;
    private com.mirth.connect.client.ui.TreePanel treePanelOutbound;
    private javax.swing.JScrollPane treeScrollPaneInbound;
    private javax.swing.JScrollPane treeScrollPaneOutbound;
    // End of variables declaration//GEN-END:variables
}
