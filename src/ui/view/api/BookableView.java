package ui.view.api;

public interface BookableView {

	void enableBookContratorButton(boolean modelSelected);

	void displayErrorMessage(String errorMessage, String title);

	void clearRowModels();

	boolean clearTable();

}
