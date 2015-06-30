package ui.view.settings;

import static javax.swing.JFileChooser.APPROVE_OPTION;
import static javax.swing.JFileChooser.FILES_AND_DIRECTORIES;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

import ui.view.ParentTracker;
import ui.view.textholder.TextHolder;

public class DirectorySelectionHandler implements ActionListener {

	private JButton browseButton;
	private JFileChooser fileChooser;
	private TextHolder selectedDirectoryHolder;

	public DirectorySelectionHandler(TextHolder textHolder) {
		this.selectedDirectoryHolder = textHolder;
		fileChooser = createFileChooser();
		setUpBrowseButton();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		int response = displayFileChooserAndGetResponse();

		if (isApproved(response)) {
			String directory = getSelectedDirectory();
			selectedDirectoryHolder.setText(directory);
		}
	}

	public void addBrowseButtonToPanel(JComponent component) {
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
		fileChooser.setCurrentDirectory(new File(selectedDirectoryHolder.getText()));
		return fileChooser.showDialog(parent, "Select folder");
	}

	private boolean isApproved(int response) {
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
