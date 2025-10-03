package es.upm.aled.lab2.kinematics;

import java.util.ArrayList;
import java.util.List;

// TODO: Implemente la clase
public class Segment {
	// Atributos (o campos) de la clase
	private double length;
	private double angle;
	private List<Segment> children;
	
	// Constructor
	public Segment(double length, double angle) {
		this.length = length;
		this.angle = angle;
		this.children = new ArrayList<>();
	}
	// Getters y setters
	public double getLength() {
		return length; //Obtenemos la longitud en cm
	}
	public double getAngle() {
		return angle; // Obtenemos el ángulo
	}
	public void setAngle(double angle) {
		this.angle = angle; // Permitimos que cambie el ángulo
	}
	public List<Segment> getChildren() {
		return children; // Obtenemos la lista de niños
	}
	
	// Añadir un niño y NO DEBE AÑADIRSE UN NUEVO SEGMENT A LA LISTA DE HIJOS SI ESTE YA SE ENCONTRABA EN ELLA
	public void addChild(Segment child) {
		// Comprobamos que no haya duplicados
		if (!children.contains(child)) {
		// Si no hay duplicados, añdimos al hijo
			children.add(child);
		}
	}
	
}
