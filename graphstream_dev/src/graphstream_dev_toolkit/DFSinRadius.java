package graphstream_dev_toolkit;

import java.util.ArrayList;

import org.graphstream.graph.DepthFirstIterator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class DFSinRadius {
	
	public static ArrayList<String> getIdInRadiusTopo ( Graph graph , Node startNode , int radius) {
		
		ArrayList<String> nodeIdInRadius = new ArrayList<String>();
		
		DepthFirstIterator<Node> iter = new DepthFirstIterator<>(startNode);		
		while ( iter.hasNext()) {
			Node n = iter.next();
		
			double dist = distanceServiceMethods.getDistTopo(graph, startNode, n) ;
			
			if ( dist <= radius) 	{ nodeIdInRadius.add(n.getId()); }
			else 					{ break; }
		}		
		return nodeIdInRadius;
	}

	public static ArrayList<String> getIdInRadiusWeight ( Graph graph , Node startNode , double radius) {
		
		ArrayList<String> nodeIdInRadius = new ArrayList<String>();
		
		DepthFirstIterator<Node> iter = new DepthFirstIterator<>(startNode);		
		while ( iter.hasNext()) {
			Node n = iter.next();
		
			double dist = distanceServiceMethods.getDistWeight(graph, startNode, n);
			
			if ( dist <= radius) 	{ nodeIdInRadius.add(n.getId()); }
			else 					{ break; }
		}		
		return nodeIdInRadius;
	}

public static ArrayList<String> getIdInRadiusGeom ( Node startNode , double radius) {
		
		ArrayList<String> nodeIdInRadius = new ArrayList<String>();
		
		DepthFirstIterator<Node> iter = new DepthFirstIterator<>(startNode);		
		while ( iter.hasNext()) {
			Node n = iter.next();
		
			double dist = distanceServiceMethods.getDistGeom(startNode, n) ;
			
			if ( dist <= radius) 	{ nodeIdInRadius.add(n.getId()); }
			else 					{ break; }
		}		
		return nodeIdInRadius;
	}


}
