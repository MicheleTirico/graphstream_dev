package graphstream_dev_toolkit;

import java.util.ArrayList;
import java.util.Arrays;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.ui.graphicGraph.GraphPosLengthUtils;

public class distanceMatrix {
	
	public static double[][] getDistanceMatrixWeight ( Graph graph  ) {
		
		int n = graph.getNodeCount();
		double[][] matrix = new double[n][n];
		double [][] matrixWeight = new double [graph.getNodeCount()] [graph.getNodeCount()] ;
		fillDistanceMatrixWeigth(graph, matrix, matrixWeight);
		return matrixWeight;	
		}
	
	private static void fillDistanceMatrixWeigth(Graph graph,  double[][] matrix , double[][] matrixWeight ) {
	
		ArrayList <String> listId = new ArrayList<String> ();
		for ( Node n : graph.getEachNode()) {	listId.add(n.getId()) ;	}	//		System.out.println(listId);
		
		for (int i = 0; i < matrix.length; i++) {	Arrays.fill(matrix[i], 0);	}
		
		for (Edge e : graph.getEachEdge()) {
			double i = e.getSourceNode().getIndex();
			double j = e.getTargetNode().getIndex();		
			
			matrix[(int)i][(int)j]++;
			if (!e.isDirected())
				matrix[(int)j][(int)i]++;		
		}

			for ( int  x = 0 ; x < matrixWeight.length ; x++) {
				Arrays.fill(matrixWeight[x], 0);
			}
			
			for (Edge e : graph.getEachEdge()) {		
				for ( int x = 0 ; x < matrixWeight.length ; x++) {

					for ( int y = 0 ; y < matrixWeight.length ; y++) {
							
						Node n1 = graph.getNode(listId.get(x));
						Node n2 = graph.getNode(listId.get(y));
					
						double dist = getDistWeight(graph, n1, n2);//					System.out.println(dist) ;
						if ( x != y ) {//						System.out.println(dist) ;
							matrixWeight[x][y] = dist ;
					}			
				}
			}
		}	
	}

// SERVICE METHODS ------------------------------------------------------------------------------------------
	// get distance in net
	protected static double getDistWeight ( Graph graph, Node n1, Node n2) {
		
		setWeigth(graph) ;
		Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "length");

		// Compute the shortest paths in g from A to all nodes
		dijkstra.init(graph);
		dijkstra.setSource(graph.getNode(n1.getId()));
		dijkstra.compute();
		
		double dist =  dijkstra.getPathLength(n2);	//System.out.println(dist);
		return dist;			
	}
	
	private static void setWeigth ( Graph graph ) {
		
		for ( Edge e : graph.getEachEdge()) {
			e.addAttribute(  "length",  getDist(e.getNode0() ,e.getNode1()));	//			double x = e.getAttribute("length");		System.out.println(x);
		}
	}
	
	// get spatial distance  from 2 nodes 
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
		return Math.sqrt( distSq );
	}

}
