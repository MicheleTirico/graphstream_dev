package graphstream_dev_toolkit;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.event.TreeWillExpandListener;

import org.graphstream.graph.DepthFirstIterator;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class DFSinRadius {
	
	public static ArrayList<String> getIdInRadiusTopo ( Graph graph , Node startNode , double radius) {
		
		ArrayList<String> nodeIdInRadius = new ArrayList<String>();
		
		DepthFirstIterator<Node> iter = new DepthFirstIterator<>(startNode);		
		while ( iter.hasNext()) {
			Node n = iter.next();
		
			int dist = (int) toolkit.getDistTopo(graph, startNode, n) ;		
			
			if ( dist < radius) 	{ nodeIdInRadius.add(n.getId()); }
			else 					{ break; }	
		}	
		return nodeIdInRadius;
	}

	public static ArrayList<String> getIdInRadiusWeight ( Graph graph , Node startNode , double radius) /*throws InterruptedException*/ {
		
		ArrayList<String> nodeIdInRadius = new ArrayList<String>();
		
		DepthFirstIterator<Node> iter = new DepthFirstIterator<>(startNode);		
		while ( iter.hasNext()) {
			Node n = iter.next();
		
			double dist = toolkit.getDistWeight(graph, startNode, n);
			
			if ( dist < radius) 	{ nodeIdInRadius.add(n.getId()); }
			else 					{ break; }
//			for(Edge e: n.getEachEdge()){  e.addAttribute("ui.class","highlight"); Thread.sleep(100);  }		
		}
		
//		graph.addAttribute("ui.stylesheet","" +           "edge.highlight {  " +	             "   fill-color: rgb(200,39,65);\n" +	             "   size: 3px;" +	             "}");
		
		return nodeIdInRadius;
	}

	public static ArrayList<String> getIdInRadiusGeom ( Node startNode , double radius) /* throws InterruptedException */ {
		
		ArrayList<String> nodeIdInRadius = new ArrayList<String>();
		
		DepthFirstIterator<Node> iter = new DepthFirstIterator<>(startNode);		
		while ( iter.hasNext()) {
			Node n = iter.next();
		
			double dist = toolkit.getDistGeom(startNode, n) ;
			
			if ( dist < radius) 	{ nodeIdInRadius.add(n.getId()); }
			else 					{ continue; }
//			for(Edge e: n.getEachEdge()){  e.addAttribute("ui.class","highlight"); Thread.sleep(100);  }	
		}		
		return nodeIdInRadius;
	}
}
