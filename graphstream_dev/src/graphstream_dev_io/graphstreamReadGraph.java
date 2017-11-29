package graphstream_dev_io;

import java.io.IOException;

import org.graphstream.graph.Graph;
import org.graphstream.stream.file.FileSourceDGS;

public class graphstreamReadGraph {
	
	static String nameFile = "gino.dgs";
	static String dossier	= "C:\\Users\\Michele TIRICO\\Desktop\\testReprtGraph\\";
	static String filePath = dossier + nameFile ;
	
	public static void read (Graph graph) throws IOException {	
		
		FileSourceDGS source = new FileSourceDGS();
		
		source.addSink( graph );
		source.begin(filePath);
		
		while( source.nextEvents() ){	  /* Do whatever between two events */	}
		source.end();

		graph.display(false);
		}

}
