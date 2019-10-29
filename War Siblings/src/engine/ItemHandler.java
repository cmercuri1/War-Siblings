/** War Siblings
 * ItemHandler
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

import items.Item;

public class ItemHandler extends TransferHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static DataFlavor itemFlavor = new DataFlavor(Item.class, "Item");

	public boolean canImport(TransferHandler.TransferSupport support) {
		// Check for String flavor
		if (!support.isDataFlavorSupported(itemFlavor)) {
			return false;
		}

		return true;
	}

	protected Transferable createTransferable(JComponent c) {
		ItemLabel label = (ItemLabel) c;
		Item item = label.getItem();

		return new ItemSelector(item);
	}

	public int getSourceActions(JComponent c) {
		return MOVE;
	}

	public boolean importData(TransferHandler.TransferSupport support) {
		if (!canImport(support)) {
			return false;
		}

		// Fetch the Transferable and its data
		Transferable t = support.getTransferable();
		Item data;
		try {
			data = (Item) t.getTransferData(DataFlavor.stringFlavor);
		} catch (Exception e) {
			return false;
		}

		// Fetch the drop location
		DropLocation loc = support.getDropLocation();

		// Insert the data at this location
		insertAt(loc, data);

		return true;
	}

	protected void exportDone(JComponent c, Transferable t, int action) {
		if (action == MOVE) {

		}
	}
}
