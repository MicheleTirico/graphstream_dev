package graphstream_dev;

import java.util.Arrays;

import org.graphstream.algorithm.Toolkit;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.GridGenerator;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.graphicGraph.GraphPosLengthUtils;

public class AdjaMatrInRadiusDistance {
	
	public static Graph graph = new SingleGraph("graph");

	public static void main ( String [ ] args ) {
		
		createGraph(2, true);
		graph.display(false);
		
		int [][] testMatrix = {{ 1,2,3},{2,2,2},{1,1,1}};
		printMatrix2d(testMatrix);
		
		System.out.println();
		int [] [] adMatr = Toolkit.getAdjacencyMatrix(graph);
		printMatrix2d(adMatr);
		
		System.out.println();
		String idNode = "2_2" ;
		int [][] adMatrRad = getAdMatrixInRadius(graph, idNode, 1);
		printMatrix2d(adMatrRad);
	}
	
	public static int [][] getAdMatrixInRadius ( Graph graph , String ntest, double radius) {
		
		int n = graph.getNodeCount();	
		int[][] matrix = new int[n][n];
		fillAdjacencyMatrixInRadius(graph, ntest, matrix , radius );
		return matrix;	
	}
	
	public static void fillAdjacencyMatrixInRadius(Graph graph, String nTest, int[][] matrix, double radius ) {
		
		Node nodeTest = graph.getNode(nTest) ;
		
		for (int i = 0; i < matrix.length; i++) 
			Arrays.fill(matrix[i], 0);

		for (Edge e : graph.getEachEdge()) {
	
			int i = e.getSourceNode().getIndex();
			int j = e.getTargetNode().getIndex();
			Node n1 = graph.getNode(i);
			Node n2 = graph.getNode(j);
			
			double distN1 = getDist ( n1 , nodeTest ) ;
			double distN2 = getDist ( n2 , nodeTest ) ;
				
			if  ( distN1 <= radius && distN2 <= radius ) {			
				matrix[i][j]++;		
				if (!e.isDirected())
					matrix[j][i]++;
				}
			}
		// remove row with all values == 0
		}
	
	private static double getDist ( Node n1 , Node n2 ) {
		
		// coordinate of node n1
		double [] n1Coordinate = GraphPosLengthUtils.nodePosition(n1) ;
		double x1 = n1Coordinate [0];
		double y1 = n1Coordinate [1];
		double z1 = n1Coordinate [2];
				
		// coordinate of node n2
		double [] n2Coordinate = GraphPosLengthUtils.nodePosition(n2) ;
		double x2 = n2Coordinate [0];
		double y2 = n2Coordinate [1];
		double z2 = n2Coordinate [2];
				
		// calculate distance
		double distSq = Math.pow( ( x1 - x2 ), 2 )  + Math.pow( ( y1 - y2 ), 2 ) + Math.pow( ( z1 - z2 ), 2 ) ;
		double dist = Math.sqrt( distSq );
		return dist;
	}
	
	// print matrix 
	public static void printMatrix2d ( int[][] matrix) {
		for ( int i = 0 ; i < matrix.length; i++ ) {
			for ( int j = 0 ; j  < matrix[i].length ; j ++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
// create grid
	public static void createGraph( int size , boolean type ) {
		// generate graph
		Generator gsGen = new GridGenerator(type , false);
		gsGen.addSink(graph);
		gsGen.begin();
		for(int i = 0 ; i < size ; i++) { 	gsGen.nextEvents(); 	}
		gsGen.end();
	}


}
