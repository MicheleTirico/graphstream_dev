package graphstream_dev;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.algorithm.Dijkstra.Element;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.GridGenerator;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.graphicGraph.GraphPosLengthUtils;

public class testDijkstra {
	
	public static Graph g = new SingleGraph("graph");

	public static void main ( String [ ] args ) {
		
		createGraph(10, true);
		g.display(false);
		
		Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "length");

		// Compute the shortest paths in g from A to all nodes
		dijkstra.init(g);
		dijkstra.setSource(g.getNode("0_0"));
		dijkstra.compute();

		// Print the lengths of all the shortest paths
		for (Node node : g)
			System.out.printf("%s->%s:%10.2f%n", dijkstra.getSource(), node,
					dijkstra.getPathLength(node));
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
			gsGen.addSink(g);
			gsGen.begin();
			for(int i = 0 ; i < size ; i++) { 	gsGen.nextEvents(); 	}
			gsGen.end();
			
			for ( Edge e : g.getEachEdge()) {
				e.addAttribute(  "length",  getDist(e.getNode0() ,e.getNode1()));
			} 
			
			
		}
}
