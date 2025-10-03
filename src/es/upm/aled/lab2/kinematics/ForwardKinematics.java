package es.upm.aled.lab2.kinematics;

import es.upm.aled.lab2.gui.Node;

/**
 * This class implements a forward kinematics algorithm using recursion. It
 * expects a tree of Segments (defined by its length and angle with respect to
 * the previous Segment in the tree) and returns a tree of Nodes (defined by
 * their absolute coordinates in a 2-dimensional space).
 * 
 * @author rgarciacarmona
 */
public class ForwardKinematics {

	/**
	 * Returns a tree of Nodes to be used by SkeletonPanel to draw the position of
	 * an exoskeleton. This method is the public facade to a recursive method that
	 * builds the result from a tree of Segments defined by their angle and length,
	 * and the relationship between them (which Segment is children of which).
	 * 
	 * @param root    The root of the tree of Segments.
	 * @param originX The X coordinate for the origin point of the tree.
	 * @param originY The Y coordinate for the origin point of the tree.
	 * @return The tree of Nodes that represent the exoskeleton position in absolute
	 *         coordinates.
	 */
	// Public method: returns the root of the position tree
	// Método fachada -  hace la primera llamada al método recursivo.
	public static Node computePositions(Segment root, double originX, double originY) {
		// TODO: Implemente este método
		// Deberá llamar al método privado pasándole los mismos
		// argumentos que ha recibido y un accumulatedAngle de 0, ya que al principio del algoritmo no
		 // hay ningún ángulo previo que sumar al actual
		Node nodoOrigen = ForwardKinematics.computePositions(root, originX, originY, 0);
		return nodoOrigen;
		
	}

	// Private helper method that implements the recursive algorithm
	
	private static Node computePositions(Segment link, double baseX, double baseY, double accumulatedAngle) {
		 long startTime = System.nanoTime();
		// TODO: Implemente este método
		// Código general
		// Ángulo acumulado (fórmula del punto 1.4)
		double newAngle = accumulatedAngle + link.getAngle();
		
		// Posción de cada nodo (fórmula del apartado 1.4)
		double x = baseX + link.getLength()*Math.cos(newAngle);
		double y = baseY + link.getLength()*Math.sin(newAngle);
		
		// Creamos un Nodo pertinente
		Node currentNode = new Node(x,y);
		// Caso base --> el caso base deberá ocurrir cuando el Segment que
		//se pasa al método recursivo no tenga hijos. Si se da esta circunstancia, deberá construirse
		// el Node pertinente, pero este no tendrá tampoco hijos.
		if (link.getChildren().size() == 0) {
			return currentNode;
		}
		// Paso recursivo - ponga cuidado en que los argumentos que se pasa a la nueva llamada
		// al método son los correctos. Un error muy común es pasar otra vez los argumentos que ya
		// se habían recibido de la anterior llamada
		
		// Llamamos a todos los hijos de los segmentos
		for (Segment child : link.getChildren()) {
		// Actializamos el método recursivo con los nuevos nodos, posiciones (y longitud) y ángulo acumulado
			Node childNode = ForwardKinematics.computePositions(child, x, y, newAngle);	
		// Añadimos al nodo pertinente (sin hijos) los nodos hijo
			currentNode.addChild(childNode);
		}
		 long runningTime = System.nanoTime()- startTime;
		 System.out.println("Tiempo de computePositions para un segmento con "
		 + link.getChildren().size() + " hijos: "
		 + runningTime + " nanosegundos");
		// Devolvemos el nodo actual
		return currentNode;
	}
}