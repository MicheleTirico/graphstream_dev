package graphstream_dev_toolkit;

import java.util.ArrayList;
import java.util.Arrays;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.ui.graphicGraph.GraphPosLengthUtils;

public class distanceMatrixInRadius extends distanceMatrix  {
	
	public static double [][] getDistanceMatrixInRadiusWeight ( Graph graph, Node nodeTest , double radius ) {
		
		double [][] matrixWeightRad = new double [graph.getNodeCount()] [graph.getNodeCount()] ;		
		fillDistanceMatrixInRadiusWeight (graph, nodeTest, radius, matrixWeightRad);
		return matrixWeightRad;
	}
	
	public static int [][] getDistanceMatrixInRadiusTopo ( Graph graph , Node nodeTest , double radius ) {
		int n = graph.getNodeCount();	
		int[][] matrix = new int[n][n];
		fillDistanceMatrixInRadiusTopo(graph, nodeTest, matrix , radius );
		return matrix;	
	}
	
	public static int [][] getDistanceMatrixInRadiusGeom ( Graph graph , Node nodeTest, double radius) {
		int n = graph.getNodeCount();	
		int[][] matrix = new int[n][n];
		fillDistanceMatrixInRadiusGeom(graph, nodeTest, matrix , radius );
		return matrix;	
	}
	
// Private methods -----------------------------------------------------------------------------------------------------------------
	
	private static void fillDistanceMatrixInRadiusWeight ( Graph graph , Node nodeTest, double radius, double[][] matrixWeightRad ) {
		
		ArrayList <String> listId = new ArrayList<String> ();	
		for ( Node n : graph.getEachNode()) {	listId.add(n.getId()) ;	}
		
		int nodeNumber = graph.getNodeCount();
		double [][] matrixWeight = distanceMatrix.getDistanceMatrixWeight(graph);

			for ( int x = 0 ; x < nodeNumber ; x++ ) {
				
				String IdN = listId.get(x);
				Node n2 = graph.getNode(IdN) ;
				double distN1 = distanceMatrix.getDistWeight(graph, nodeTest, n2) ; //				System.out.println(distN1);
				
				System.out.println(distN1);
				for ( int y = 0 ; y < nodeNumber ; y++ ) {

					if (  distN1 < radius ) {
						System.out.println("dist " + distN1 + " x " + x + " y " + y);
						matrixWeightRad[x][y] = matrixWeight[x][y] ;
						matrixWeightRad[y][x] = matrixWeight[y][x] ;
					}
					else {
						matrixWeightRad[x][y] = 0 ;
						matrixWeightRad[y][x] = 0 ;
					}				
				}
			}
		}
	
private static void fillDistanceMatrixInRadiusGeom(Graph graph, Node  nodeTest, int[][] matrix, double radius ) {
		
		for (int i = 0; i < matrix.length; i++) 
			Arrays.fill(matrix[i], 0);

		for (Edge e : graph.getEachEdge()) {
	
			int i = e.getSourceNode().getIndex();
			int j = e.getTargetNode().getIndex();
			Node n1 = graph.getNode(i);
			Node n2 = graph.getNode(j);
			
			double distN1 = getDistGeom ( n1 , nodeTest ) ;
			double distN2 = getDistGeom ( n2 , nodeTest ) ;
				
			if  ( distN1 <= radius && distN2 <= radius ) {			
				matrix[i][j]++;		
				if (!e.isDirected())
					matrix[j][i]++;
				}
			}
		// remove row with all values == 0
		}
private static void fillDistanceMatrixInRadiusTopo(Graph graph, Node nodeTest, int[][] matrix, double radius ) {
	
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

private static double getDistGeom ( Node n1 , Node n2 ) {
	
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

private static double getDistTopo ( Graph graph, Node n1, Node n2) {
	
	Dijkstra dist = new Dijkstra();
	
	dist.init(graph);
	dist.setSource(n1);
	dist.compute();

	return dist.getPathLength(n2);	
}

}
