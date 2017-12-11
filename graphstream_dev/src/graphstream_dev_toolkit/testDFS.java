package graphstream_dev_toolkit;

import java.util.ArrayList;

import org.graphstream.algorithm.generator.BarabasiAlbertGenerator;
import org.graphstream.graph.DepthFirstIterator;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import graphstream_dev_toolkit.DFSinRadius;

public class testDFS {

	static Graph graph = new SingleGraph("graph");
	
	public static void main (String[] args) throws InterruptedException  {
		
		toolkit.createGraphGrid(graph, 10, true); 
//		toolkit.createGraphRandom(graph, 100);
		Node startNode = graph.getNode("6_6");

		  // display the graph before running the search
        graph.display(false);
        graph.addAttribute("ui.stylesheet","" +
                "edge.highlight {  " +
                "   fill-color: rgb(200,39,65);\n" +
                "   size: 3px;" +
                "}");
        
        


        System.out.println("list of id in radius topo");
        ArrayList<String> arrId = DFSinRadius.getIdInRadiusTopo(graph, startNode, 2);
        System.out.println(arrId);
        
        System.out.println("\n" + "list of id in radius weight");
//        ArrayList<String> arrIdWei = DFSinRadius.getIdInRadiusWeight(graph, startNode, 2); System.out.println(arrIdWei);
        
        System.out.println("\n" + "list of id in radius geom");
//        ArrayList<String> arrIdGeom = DFSinRadius.getIdInRadiusGeom(startNode, 1.5);        System.out.println(arrIdGeom);
	}
		
	
	
	
	
	
	
}
