/** War Siblings
 * ItemSelector
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import items.Item;

public class ItemSelector implements Transferable {
	private Item item;
	private DataFlavor[] transferDataFlavors;
	
	/**
	 * @param item 
	 * 
	 */
	public ItemSelector(Item item) {
		this.item = item;
		this.transferDataFlavors = new DataFlavor[]{new DataFlavor(Item.class, "Item")};
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return this.transferDataFlavors;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		for(int i=0; i < this.transferDataFlavors.length; i++) {
			if (this.transferDataFlavors[i].equals(flavor))
				return true;
		}
		return false;
	}

	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		if (this.isDataFlavorSupported(flavor))
			return this.item;
		else 
			throw new UnsupportedFlavorException(flavor);
	}

}
