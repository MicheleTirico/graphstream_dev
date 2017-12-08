package graphstream_dev_test;

import java.util.Arrays;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.algorithm.Toolkit;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.GridGenerator;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.graphicGraph.GraphPosLengthUtils;

public class adjaMatrInRadiusTopo {
	
	public static Graph graph = new SingleGraph("graph");

	public static void main(String[] args) {
		 
			createGraph(5, true);
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

		public static int [][] getAdMatrixInRadius ( Graph graph , String ntest, double radius ) {
			
			int n = graph.getNodeCount();	
			int[][] matrix = new int[n][n];
			fillAdjacencyMatrixInRadiusNet(graph, ntest, matrix , radius );
			return matrix;	
		}
		
		public static void fillAdjacencyMatrixInRadiusNet(Graph graph, String nTest, int[][] matrix, double radius ) {
			
			Node nodeTest = graph.getNode(nTest) ;
			
			for (int i = 0; i < matrix.length; i++) 
				Arrays.fill(matrix[i], 0);

			for (Edge e : graph.getEachEdge()) {
		
				int i = e.getSourceNode().getIndex();
				int j = e.getTargetNode().getIndex();
				Node n1 = graph.getNode(i);
				Node n2 = graph.getNode(j);
				
				double distN1 = getDistTopo ( graph, n1 , nodeTest ) ;
				double distN2 = getDistTopo ( graph, n2 , nodeTest ) ;
					
				if  ( distN1 <= radius && distN2 <= radius ) {			
					matrix[i][j]++;		
					if (!e.isDirected())
						matrix[j][i]++;
					}
				}
			// remove row with all values == 0
			}
		
		
		private static double getDistTopo ( Graph graph, Node n1, Node n2) {
			
			Dijkstra dist = new Dijkstra();
			
			dist.init(graph);
			dist.setSource(n1);
			dist.compute();
	
			return dist.getPathLength(n2);	
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
