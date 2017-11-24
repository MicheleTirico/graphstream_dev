package graphstream_dev;

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

public class AdjaMatrInRadiusWeight {
	
	public static Graph graph = new SingleGraph("graph");

	public static void main(String[] args) {
		 
	
			createGraph(2, true);
			graph.display(false);
			
			int [][] testMatrix = {{ 1,2,3},{2,2,2},{1,1,1}};
//			printMatrix2d(testMatrix);
			
			System.out.println();
			int [] [] adMatr = Toolkit.getAdjacencyMatrix(graph);
//			printMatrix2d(adMatr);
			
			System.out.println();
			String idNode = "0_0" ;
			double [][] adMatrRad = getAdMatrixInRadius(graph, idNode, 2.8);
			
		}

		public static double [][] getAdMatrixInRadius ( Graph graph , String ntest, double radius ) {
			
			int n = graph.getNodeCount();	
			double[][] matrix = new double[n][n];
			fillAdjacencyMatrixInRadiusNet(graph, ntest, matrix , radius );
			return matrix;	
		}
		
		public static void fillAdjacencyMatrixInRadiusNet( Graph graph , String nTest , double[][] matrix , double radius ) {
			
			adjaMatrix.computeAdMatrix(graph);
			int[][] AdjMatrix = adjaMatrix.getMatrix();
			
			String[] listId = adjaMatrix.getListId();
			
//			printMatrix2dInt(AdjMatrix);
			
			System.out.println(listId);
			int n = graph.getNodeCount();
			
			int[] [] weigMatrix = new int [n][n] ;
			
			
			for ( int i = 0 ; i < AdjMatrix.length; i++ ) {
				
				for ( int j = 0 ; j  < matrix[i].length ; j ++) {
					
					if (AdjMatrix[i][j] == 1 ) {
	
//						Node n1 = graph.getNode(listId[i]);
//						Node n2 = graph.getNode(listId[j]);
						
						
//						weigMatrix[i][j] = (int) getDistWeigth(graph, n1, n2);
						
						
					}
				}
			}
			
			printMatrix2dInt( weigMatrix);
			
		
			}
		
		private static void setWeigth ( Graph graph ) {
			
			for ( Edge e : graph.getEachEdge()) {
				e.addAttribute(  "length",  getDist(e.getNode0() ,e.getNode1()));				
//				double x = e.getAttribute("length");		System.out.println(x);
			}
		}
		
		
	
		private static double getDistWeigth ( Graph graph, Node n1, Node n2) {
			
			setWeigth(graph) ;

			Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "length");

			// Compute the shortest paths in g from A to all nodes
			dijkstra.init(graph);
			dijkstra.setSource(graph.getNode(n1.getId()));
			dijkstra.compute();
			
			double dist =  dijkstra.getPathLength(n2);	//System.out.println(dist);
			return dist;			
		}
		
		
		
		// print matrix 
		public static void printMatrix2dInt ( int[][] testMatrix) {
			for ( int i = 0 ; i < testMatrix.length; i++ ) {
				for ( int j = 0 ; j  < testMatrix[i].length ; j ++) {
					System.out.print(testMatrix[i][j] + " ");
				}
				System.out.println();
			}
		}
		
		public static void printMatrix2dDouble ( double[][] testMatrix) {
			for ( int i = 0 ; i < testMatrix.length; i++ ) {
				for ( int j = 0 ; j  < testMatrix[i].length ; j ++) {
					System.out.print(testMatrix[i][j] + " ");
				}
				System.out.println();
			}
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
