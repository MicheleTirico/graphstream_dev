package graphstream_dev_toolkit;

import org.graphstream.algorithm.Toolkit;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.GridGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class testMatrix extends distanceMatrixInRadius { 
	
	static Graph graph = new SingleGraph("test");
	
	static String nodeTestStr = "0_0";
	static Node nodeTest = graph.getNode(nodeTestStr);
	static int NodeNumber = graph.getNodeCount(); 
	static double radius = 3 ;
	
	static int[][] adjacencyMatrix = new int [NodeNumber][NodeNumber] ;
	static double[][] distanceMatrixWeight = new double [NodeNumber][NodeNumber] ;
	static double[][] distanceMatrixTopo = new double [NodeNumber][NodeNumber] ;
	
	static int[][] distanceMatrixInRadiusTopo = new int [NodeNumber][NodeNumber] ;
	static int[][] distanceMatrixInRadiusGeom = new int [NodeNumber][NodeNumber] ;
	static double[][] distanceMatrixInRadiusWeight = new double [NodeNumber][NodeNumber] ;
	
	public static void main(String[] args) {
		
		toolkit.createGraph(graph, 3, true);
		graph.display(false);
		
		// adjacency matrix
		System.out.println("adjacency matrix");
		adjacencyMatrix = Toolkit.getAdjacencyMatrix(graph);
		toolkit.printMatrix2dInt(adjacencyMatrix);
		
		// distance matrix weight
		System.out.println("\n distance matrix weight");
		distanceMatrixWeight = getDistanceMatrixWeight(graph);
		toolkit.printMatrix2dDouble(distanceMatrixWeight);
		
		// distance matrix Topo // not yet implemented
		System.out.println("\n distance matrix topo");
		distanceMatrixTopo = getDistanceMatrixTopo(graph);
		toolkit.printMatrix2dDouble(distanceMatrixTopo);
		
		// distance matrix in radius Topo
		System.out.println("\n distance matrix in radius topo");
		distanceMatrixInRadiusTopo = getDistanceMatrixInRadiusTopo(graph, nodeTestStr, radius) ;
		toolkit.printMatrix2dInt(distanceMatrixInRadiusTopo);
		
		// distance matrix in radius geom
		System.out.println("\n distance matrix in radius geom");
		distanceMatrixInRadiusGeom = getDistanceMatrixInRadiusGeom(graph, nodeTestStr, radius) ;
		toolkit.printMatrix2dInt(distanceMatrixInRadiusGeom);
		
		// distance matrix in radius weight
		System.out.println("\n distance matrix in radius weight");
		distanceMatrixInRadiusWeight = getDistanceMatrixInRadiusWeight(graph, nodeTestStr, radius) ;
		toolkit.printMatrix2dDouble(distanceMatrixInRadiusWeight);
	}
	
}
