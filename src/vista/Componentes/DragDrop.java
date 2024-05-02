package vista.Componentes;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DragDrop {
    public static File[] getDropFiles(DropTargetDropEvent dtde){
        try{
            if(dtde.getDropAction() == DnDConstants.ACTION_MOVE){
                dtde.acceptDrop(dtde.getDropAction());
                final Transferable transferable = dtde.getTransferable();
                if(transferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor)){
                    List<File> listFiles = (List) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                    dtde.dropComplete(true);
                    return listFiles.toArray(new File[listFiles.size()]);
                }
            }
        }catch(UnsupportedFlavorException | IOException e){
            System.out.println(e);
        }
        return null;
    }
 
    
}