package graphstream_dev_toolkit;

import org.graphstream.algorithm.Toolkit;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.GridGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class runTest extends distanceMatrixInRadius { 
	
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
		
		createGraph(graph, 3, true);
//		graph.display(false);
		
		// adjacency matrix
		System.out.println("adjacency matrix");
		adjacencyMatrix = Toolkit.getAdjacencyMatrix(graph);
		printMatrix2dInt(adjacencyMatrix);
		
		// distance matrix weight
		System.out.println("\n distance matrix weight");
		distanceMatrixWeight = getDistanceMatrixWeight(graph);
		printMatrix2dDouble(distanceMatrixWeight);
		
		// distance matrix Topo // not yet implemented
		System.out.println("\n distance matrix topo");
		distanceMatrixTopo = getDistanceMatrixTopo(graph);
		printMatrix2dDouble(distanceMatrixTopo);
		
		// distance matrix in radius Topo
		System.out.println("\n distance matrix in radius topo");
		distanceMatrixInRadiusTopo = getDistanceMatrixInRadiusTopo(graph, nodeTestStr, radius) ;
		printMatrix2dInt(distanceMatrixInRadiusTopo);
		
		// distance matrix in radius geom
		System.out.println("\n distance matrix in radius geom");
		distanceMatrixInRadiusGeom = getDistanceMatrixInRadiusGeom(graph, nodeTestStr, radius) ;
		printMatrix2dInt(distanceMatrixInRadiusGeom);
		
		// distance matrix in radius weight
		System.out.println("\n distance matrix in radius weight");
		distanceMatrixInRadiusWeight = getDistanceMatrixInRadiusWeight(graph, nodeTestStr, radius) ;
		printMatrix2dDouble(distanceMatrixInRadiusWeight);
		
		
		
		
	}
	
	
	
	
	// create grid
	public static void createGraph( Graph graph , int size , boolean type ) {
		// generate graph
		Generator gsGen = new GridGenerator(type , false);
		gsGen.addSink(graph);
		gsGen.begin();
		for(int i = 0 ; i < size ; i++) { 	gsGen.nextEvents(); 	}
		gsGen.end();
		}

	// print matrix  int
	public static void printMatrix2dInt ( int[][] matrix) {
		for ( int i = 0 ; i < matrix.length; i++ ) {
			for ( int j = 0 ; j  < matrix[i].length ; j ++) {
				System.out.print(matrix[i][j] + " ");
			}
		System.out.println();
		}
	}
			
	// print matrix double
	public static void printMatrix2dDouble ( double[][] matrix) {
		for ( int i = 0 ; i < matrix.length; i++ ) {
			for ( int j = 0 ; j  < matrix[i].length ; j ++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}
