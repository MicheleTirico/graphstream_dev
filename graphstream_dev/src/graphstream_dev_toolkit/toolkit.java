package graphstream_dev_toolkit;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.algorithm.generator.BarabasiAlbertGenerator;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.GridGenerator;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.ui.graphicGraph.GraphPosLengthUtils;

public abstract class toolkit {

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
				e.addAttribute(  "length",  getDistGeom(e.getNode0() ,e.getNode1()));	//			double x = e.getAttribute("length");		System.out.println(x);
			}
		}
		
		// get spatial distance  from 2 nodes 
		protected static double getDistGeom ( Node n1 , Node n2 ) {	
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
		
		protected static double getDistTopo ( Graph graph, Node n1, Node n2) {
			
			Dijkstra dist = new Dijkstra();
		
			dist.init(graph);
			dist.setSource(n1);
			dist.compute();

			return dist.getPathLength(n2);	
		}
		
		// create grid
		public static void createGraphGrid ( Graph graph , int size , boolean type ) {
			// generate graph
			Generator gsGen = new GridGenerator(type , false);
			gsGen.addSink(graph);
			gsGen.begin();
			for(int i = 0 ; i < size ; i++) { 	gsGen.nextEvents(); 	}
			gsGen.end();
			}
		
		public static void createGraphRandom (Graph graph , int size ) {
			 // create a random graph
	        BarabasiAlbertGenerator gen = new BarabasiAlbertGenerator();
	        gen.addSink(graph);
	        gen.begin();
	        for(int i =0; i< size ; i++)
	            gen.nextEvents();

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
