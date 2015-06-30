package ui.view.mergers;

import javax.swing.JTable;

import ui.controller.ControllerFactory;
import ui.controller.api.BookableController;
import ui.view.ButtonPanel;
import ui.view.MessageBoxPresenter;
import ui.view.api.BookableView;

public class BookableMerger implements BookableView {

	private static BookableMerger bookableMerger;
	private ButtonPanel buttonPanel;
	private BookableController bookableController;
	private MessageBoxPresenter messageBoxPresenter;

	private BookableMerger() {
		ControllerFactory factory = new ControllerFactory();
		bookableController = factory.getContractorController(this);
		messageBoxPresenter = MessageBoxPresenter.getInstance();
	}

	public static BookableMerger getInstance() {
		if (bookableMerger == null) {
			bookableMerger = new BookableMerger();
		}
		return bookableMerger;
	}

	public void addButtonPanel(ButtonPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}

	public void bookSelectedContractorWithCustomer(String customerId) {
		bookableController.bookSelectedContractorWithCustomer(customerId);
	}

	public void search(String name, String location) {
		bookableController.search(name, location);
	}

	public JTable getJTableWithModel() {
		return bookableController.getJTableWithModel();
	}

	@Override
	public void enableBookContratorButton(boolean enabled) {
		buttonPanel.enableBookContratorButton(enabled);
	}

	@Override
	public void displayErrorMessage(String errorMessage, String title) {
		messageBoxPresenter.displayErrorMessageBox(errorMessage, title);
	}
}
