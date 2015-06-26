package gui.view;

import static java.lang.Boolean.FALSE;

public class TextHolderFactory {

	public TextHolder createPortNumberTextHolder() {
		TextHolderBuilder builder = new TextHolderBuilder();
		return builder.addLabel("Select port number").addNumberOfColumns(6).build();
	}

	public TextHolder createIpAddressTextHolder() {
		TextHolderBuilder builder = new TextHolderBuilder();
		return builder.addLabel("enter server's IP address").addNumberOfColumns(10).build();
	}

	public TextHolder createDirectoryTextHolder() {
		TextHolderBuilder builder = new TextHolderBuilder();
		return builder.addLabel("select location").addIsEditable(FALSE).addNumberOfColumns(20).build();
	}
}
