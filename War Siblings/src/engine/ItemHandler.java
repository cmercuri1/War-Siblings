/** War Siblings
 * ItemHandler
 * Author: Chris Mercuri cmercuri1@student.unimelb.edu.au
 */
package engine;

import java.awt.datatransfer.DataFlavor;

import javax.swing.TransferHandler;

public class ItemHandler extends TransferHandler {
	/**
     * We only support importing strings.
     */
    public boolean canImport(TransferHandler.TransferSupport info) {
        // Check for String flavor
        if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return false;
        }
        return true;
   }
}
