package graphstream_dev_io_test;

import java.io.IOException;

import org.graphstream.graph.Graph;
import org.graphstream.stream.file.FileSinkDGS;
import org.graphstream.stream.file.FileSourceDGS;

public class graphstreamWriteGraph {
	
	static String nameFile = "gino.dgs";
	static String dossier	= "C:\\Users\\Michele TIRICO\\Desktop\\testReprtGraph\\";
	static String filePath = dossier + nameFile ;

	public static void write (Graph graph) throws IOException {
	
		FileSinkDGS fsd = new FileSinkDGS();
	
		graph.write(fsd, filePath);
	}
}
