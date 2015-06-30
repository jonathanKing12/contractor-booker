package ui.view;

import static java.lang.Boolean.FALSE;

public class TextHolderFactory {

	public TextHolder createIpAddressTextHolder() {
		TextHolderBuilder builder = new TextHolderBuilder();
		return builder.addLabel("Enter server's IP address:").addNumberOfColumns(10).build();
	}

	public TextHolder createDirectoryTextHolder() {
		TextHolderBuilder builder = new TextHolderBuilder();
		return builder.addLabel("select folder database is in:").addIsEditable(FALSE)
				.addNumberOfColumns(35).build();
	}

	public TextHolder createClientPortNumberTextHolder() {
		return createPortNumberTextHolder("Enter port number to listen to server:");
	}

	public TextHolder createServerPortNumberTextHolder() {
		return createPortNumberTextHolder("Enter port number to listen to clients:");
	}

	private TextHolder createPortNumberTextHolder(String label) {
		TextHolderBuilder builder = new TextHolderBuilder();
		return builder.addLabel(label).addNumberOfColumns(5).build();
	}
}
