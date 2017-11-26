package graphstream_dev;

import java.util.Arrays;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class adjaMatrix {

	static String[] listId ;
	static int [][] matrix ;
	
	public static void computeAdMatrix ( Graph graph  ) {
		
		String[] listId = new String[graph.getNodeCount()];
		int n = graph.getNodeCount();	
		matrix = new int[n][n];
		fillAdjacencyMatrixWeigth(graph , matrix , listId);
	}
	
	private static void fillAdjacencyMatrixWeigth(Graph graph,  int[][] matrix, String[] listId) {
	
		for (int i = 0; i < matrix.length; i++) 
			Arrays.fill(matrix[i], 0);
	
		for (Edge e : graph.getEachEdge()) {
	
			int i = e.getSourceNode().getIndex();
			int j = e.getTargetNode().getIndex();
			Node n1 = graph.getNode(i);
			Node n2 = graph.getNode(j);
			
			matrix[i][j]++;		
			if (!e.isDirected())
				matrix[j][i]++;		
			
			listId[i] = n1.getId();
//		System.out.println(listId[i]);
		}


		}
	private static String[] getListId (Graph graph) { 
		
		String[] listId = new String[graph.getNodeCount()];
		
		int n = graph.getNodeCount();	
		matrix = new int[n][n];
//		fillAdjacencyMatrix(graph , matrix , listId);
		
		return listId ; }
}

	
	
	



