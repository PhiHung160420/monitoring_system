import java.io.IOException;
import java.net.Socket;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WatcherService implements Runnable {
	private final WatchService watcher;
	private Socket socket;
    private MainFrame mainFrame;
    private String path;
    
    public WatcherService(Socket socket, String path) throws IOException {
    	this.watcher = FileSystems.getDefault().newWatchService();
    	this.path = path;
    	this.socket = socket;
    	mainFrame = new MainFrame();
    }
    
    public void dispose() throws IOException {
    	watcher.close();
    }
    
    private void watcherCreate(Path fileName) throws IOException {
    	DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY HH:mm:ss");
    	
        Date date = new Date();
        
        int rowCount = mainFrame.tblModel.getRowCount();
        
        Object[] obj = new Object[] { rowCount + 1, path, dateFormat.format(date), "Created", MainFrame.clientName, Constant.MESSAGE_CREATE + fileName };
        
        System.out.printf("obj: ", obj);

        mainFrame.tblModel.addRow(obj);
        MainFrame.table.setModel(mainFrame.tblModel);
        
        new SendToServer(socket, MainFrame.clientName, "10",  Constant.MESSAGE_CREATE + fileName, path);
    }
    
    private void watcherDelete(Path fileName) throws IOException {
         DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY HH:mm:ss");
         
         Date date = new Date();

         int rowCount = mainFrame.tblModel.getRowCount();
         
         Object[] obj = new Object[] { rowCount + 1, path, dateFormat.format(date), "Deleted", MainFrame.clientName, Constant.MESSAGE_DELETE + fileName };

         mainFrame.tblModel.addRow(obj);
         MainFrame.table.setModel(mainFrame.tblModel);
         
         new SendToServer(socket, MainFrame.clientName, "10",  Constant.MESSAGE_CREATE + fileName, path);
    }
    
    private void watcherModify(Path fileName) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY HH:mm:ss");
        
        Date date = new Date();
        
        int rowCount = mainFrame.tblModel.getRowCount();
        
        Object[] obj = new Object[] {rowCount + 1, path, dateFormat.format(date), "Modified", MainFrame.clientName, Constant.MESSAGE_MODIFY + fileName };

        mainFrame.tblModel.addRow(obj);
        MainFrame.table.setModel(mainFrame.tblModel);
        
        new SendToServer(socket, MainFrame.clientName, "10",  Constant.MESSAGE_CREATE + fileName, path);
   }
    
    private void processEvents() throws IOException {
    	WatchKey key = Paths.get(path).register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
    	
    	for (;;) {
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();
 
                Path fileName = ((WatchEvent<Path>) event).context();
 
                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                	watcherCreate(fileName);
                }
                
                if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                	watcherDelete(fileName);
                }
                
                if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                	watcherModify(fileName);
                }
            }
 
            boolean valid = key.reset();
            
            if (!valid) {
                break;
            }
        } 
    }

	@Override
	public void run() {
		try {
			processEvents();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
}