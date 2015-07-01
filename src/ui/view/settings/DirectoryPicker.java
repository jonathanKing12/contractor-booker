package ui.view.settings;

import static javax.swing.JFileChooser.APPROVE_OPTION;
import static javax.swing.JFileChooser.FILES_AND_DIRECTORIES;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import ui.view.ParentTracker;
import ui.view.textwidget.TextWidget;

public class DirectoryPicker implements ActionListener {

    private JButton browseButton;
    private JFileChooser fileChooser;
    private TextWidget directoryWidget;

    public DirectoryPicker(TextWidget directoryWidget) {
        this.directoryWidget = directoryWidget;
        fileChooser = createFileChooser();
        setUpBrowseButton();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        int response = displayFileChooserAndGetResponse();

        if (isOfTypeApproved(response)) {
            String directory = getSelectedDirectory();
            directoryWidget.setText(directory);
        }
    }

    public void addBrowseButtonToComponent(JComponent component) {
        component.add(browseButton);
    }

    private JFileChooser createFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(FILES_AND_DIRECTORIES);
        return fileChooser;
    }

    private void setUpBrowseButton() {
        browseButton = new JButton("browse");
        browseButton.addActionListener(this);
    }

    private int displayFileChooserAndGetResponse() {
        Component parent = ParentTracker.getInstance().getCurrentParent();
        fileChooser.setSelectedFile(new File(""));
        fileChooser.setCurrentDirectory(new File(directoryWidget.getText()));
        return fileChooser.showDialog(parent, "Select folder");
    }

    private boolean isOfTypeApproved(int response) {
        return response == APPROVE_OPTION;
    }

    private String getSelectedDirectory() {
        File file = fileChooser.getSelectedFile();

        if (file.isFile()) {
            file = fileChooser.getCurrentDirectory();
        }
        return file.getAbsolutePath();
    }
}
