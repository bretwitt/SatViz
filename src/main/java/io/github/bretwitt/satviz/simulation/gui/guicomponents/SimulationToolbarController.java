package io.github.bretwitt.satviz.simulation.gui.guicomponents;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

public class SimulationToolbarController {

    @FXML
    private ListView<String> satelliteListView;

    //@FXML
    //private ToolBar toolBar;

    @FXML
    private TreeView<String> satelliteTree;

    @FXML
    private AnchorPane parent;

    public void initialize() {
        setupJMetro();
        satelliteListView.getItems().addAll("SAT1","SAT2","SAT3");
    }

    private TreeItem<String> createTreeItem(String s) {
        TreeItem<String> treeItem = new TreeItem<String>();
        treeItem.setValue(s);
        return treeItem;
    }

    private void setupJMetro() {
        JMetro jMetro = new JMetro(parent, Style.DARK);
    }
}
