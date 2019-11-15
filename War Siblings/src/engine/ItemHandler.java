/** War Siblings
 * ItemHandler
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

import items.Item;

public class ItemHandler extends TransferHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static DataFlavor localItemFlavour, serialItemFlavour;

	ItemLabel source = null;

	public ItemHandler() {
		try {
			localItemFlavour = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + ";class=Item");
		} catch (ClassNotFoundException e) {
			System.out.println("ItemTransferHandler: unable to create data flavor");
		}
		serialItemFlavour = new DataFlavor(Item.class, "Item");
	}

	public boolean importData(JComponent c, Transferable t) {
		ItemLabel target = null;
		Item item = null;
		if (!canImport(c, t.getTransferDataFlavors())) {
			return false;
		}
		try {
			target = (ItemLabel) c;
			if (hasLocalItemFlavor(t.getTransferDataFlavors())) {
				item = (Item) t.getTransferData(localItemFlavour);
			} else if (hasSerialItemFlavor(t.getTransferDataFlavors())) {
				item = (Item) t.getTransferData(serialItemFlavour);
			} else {
				return false;
			}
		} catch (UnsupportedFlavorException ufe) {
			System.out.println("importData: unsupported data flavor");
			return false;
		} catch (IOException ioe) {
			System.out.println("importData: I/O exception");
			return false;
		}

		// At this point we use the same code to retrieve the data
		// locally or serially.

		// Prevent the user from dropping data back on itself.
		// For example, if the user is moving items #4,#5,#6 and #7 and
		// attempts to insert the items after item #5, this would
		// be problematic when removing the original items.
		// This is interpreted as dropping the same data on itself
		// and has no effect.
		if (source.equals(target)) {
			return true;
		}
		
		target.setItem(item);
		return true;
	}

	protected void exportDone(JComponent c, Transferable data, int action) {
	}

	private boolean hasLocalItemFlavor(DataFlavor[] flavors) {
		if (localItemFlavour == null) {
			return false;
		}

		for (int i = 0; i < flavors.length; i++) {
			if (flavors[i].equals(localItemFlavour)) {
				return true;
			}
		}
		return false;
	}

	private boolean hasSerialItemFlavor(DataFlavor[] flavors) {
		if (serialItemFlavour == null) {
			return false;
		}

		for (int i = 0; i < flavors.length; i++) {
			if (flavors[i].equals(serialItemFlavour)) {
				return true;
			}
		}
		return false;
	}

	public boolean canImport(JComponent c, DataFlavor[] flavors) {
		if (hasLocalItemFlavor(flavors)) {
			return true;
		}
		if (hasSerialItemFlavor(flavors)) {
			return true;
		}
		return false;
	}

	protected Transferable createTransferable(JComponent c) {
		if (c instanceof ItemLabel) {
			source = (ItemLabel) c;
			Item item = source.getItem();
			if (item == null) {
				return null;
			}
			return new ItemTransferable(item);
		}
		return null;
	}

	public int getSourceActions(JComponent c) {
		return COPY;
	}

	public class ItemTransferable implements Transferable {
		Item data;

		public ItemTransferable(Item item) {
			data = item;
		}

		public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
			if (!isDataFlavorSupported(flavor)) {
				throw new UnsupportedFlavorException(flavor);
			}
			return data;
		}

		public DataFlavor[] getTransferDataFlavors() {
			return new DataFlavor[] { localItemFlavour, serialItemFlavour };
		}

		public boolean isDataFlavorSupported(DataFlavor flavor) {
			if (localItemFlavour.equals(flavor)) {
				return true;
			}
			if (serialItemFlavour.equals(flavor)) {
				return true;
			}
			return false;
		}
	}
}
