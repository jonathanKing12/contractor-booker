package ui.view.settings;

import static javax.swing.JFileChooser.APPROVE_OPTION;
import static javax.swing.JFileChooser.FILES_AND_DIRECTORIES;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import ui.view.common.ParentTracker;
import ui.view.common.textwidget.TextWidget;

/**
 * Stores and select the folder of the database.
 */
public class DirectoryPicker implements ActionListener {

    private JButton browseButton;
    private JFileChooser fileChooser;
    private TextWidget directoryWidget;

    /**
     * Constructs a instance of DirectoryPicker
     * 
     * @param directoryWidget
     *            - the directoryWidget
     */
    public DirectoryPicker(TextWidget directoryWidget) {
        this.directoryWidget = directoryWidget;
        fileChooser = createFileChooser();
        setUpBrowseButton();
    }

    /**
     * Sets the new database location.
     * 
     * <p>
     * Displays a {@link JFileChooser} for the user to select a folder. If a folder is selected and also the okay button on the JFileChooser, then
     * that folder location will be set to this instance directoryWidget's text. If a file was selected then the directoryWidget's text will be set to
     * its folder location.
     * </p>
     * 
     * @param event
     *            - the event that triggered this action to occur
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        int response = displayFileChooserAndGetResponse();

        if (isOfTypeApproved(response)) {
            String directory = getSelectedDirectory();
            directoryWidget.setText(directory);
        }
    }

    /**
     * Adds this instance browseButton to specified component. The browseButton is a {@link JButton}.
     * 
     * @param component
     *            - the component
     */
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
        Component parent = ParentTracker.getInstance().getParent();
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
