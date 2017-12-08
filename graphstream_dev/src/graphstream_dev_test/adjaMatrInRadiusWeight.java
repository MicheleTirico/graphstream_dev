package graphstream_dev_test;

import java.util.ArrayList;
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

public class adjaMatrInRadiusWeight {
	
	public static Graph graph = new SingleGraph("graph");

	public static void main(String[] args) {
		
		createGraph(2, true);
		graph.display(false);
		
		Node n1 = graph.getNode("2_2");
		
		double [][] matrixInRad = getAdMatrixInRadius(graph, n1, 2 ) ;		
		printMatrix2dDouble(matrixInRad);
	}
	
	public static double [][] getAdMatrixInRadius ( Graph graph, Node nodeTest , double radius ) {
	
		double [][] matrixWeigRad = new double [graph.getNodeCount()] [graph.getNodeCount()] ;		
		fillAjacencyMatrixInRadius(graph, nodeTest, radius, matrixWeigRad);
		return matrixWeigRad;
	}
	
	private static void fillAjacencyMatrixInRadius ( Graph graph , Node nodeTest, double radius, double[][] matrixWeigRad ) {
	
		ArrayList <String> listId = new ArrayList<String> ();	
		for ( Node n : graph.getEachNode()) {	listId.add(n.getId()) ;	}
		
		int nodeNumber = graph.getNodeCount();
		double [][] matrixWeig = adjaMatrixWeigth.getAdMatrix(graph);
		printMatrix2dDouble(matrixWeig);		System.out.println();

			for ( int x = 0 ; x < nodeNumber ; x++ ) {
				
				String IdN = listId.get(x);
				Node n2 = graph.getNode(IdN) ;
				double distN1 = adjaMatrixWeigth.getDistWeigth(graph, nodeTest, n2) ; //				System.out.println(distN1);
				
				System.out.println(distN1);
				for ( int y = 0 ; y < nodeNumber ; y++ ) {
//					double distN1 = adjaMatrixWeigth.getDistWeigth(graph, nodeTest, n2) ;
					
					if (  distN1 < radius ) {
						System.out.println("dist " + distN1 + " x " + x + " y " + y);
						matrixWeigRad[x][y] = matrixWeig[x][y] ;
						matrixWeigRad[y][x] = matrixWeig[y][x] ;
					}
					else {
						matrixWeigRad[x][y] = 0 ;
						matrixWeigRad[y][x] = 0 ;
					}				
				}
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
