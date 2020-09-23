package lf2.flap.models.entity;

/**
 * Transition - Crea las transiciones entre estados guardando el estado donde inicia,
 * el estado a donde llega y una etiqueta
 * @author Felipe Chaparro - Yohan Caro - Fabian Cristancho
 *	
 * 22 sep. 2020
 */
public class Transition {
	protected String value;
	protected State startState, endState;
	
	/**
	 * Constructor que crea una transición normal
	 * @param value etiqueta
	 * @param start estado donde inicia
	 * @param end estado a donde llega
	 */
	public Transition(String value, State start, State end) {
		this.value = value;
		this.startState = start;
		this.endState = end;
	}
	
	/**
	 * Crea una transición bucle (sale y llega a mismo estado)
	 * @param value etiqueta
	 * @param state estado
	 */
	public Transition(String value, State state) {
		this.value = value;
		this.startState = state;
		this.endState = state;
	}
	
	/**
	 * Verifica si es una transición bucle
	 * @return true / false
	 */
	public boolean isRecursive() {
		return this.endState == this.startState;
	}
	
	/**
	 * Obtiene el valor de la etiqueta
	 * @return value valor
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Obtiene el estado de inicio
	 * @return startState estado
	 */
	public State getStartState() {
		return startState;
	}
	
	/**
	 * Obtiene el estado de llegada
	 * @return endState
	 */
	public State getEndState() {
		return endState;
	}
	
	/**
	 * Cambia el valor de la etiqueta
	 * @param value nueva etiqueta
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * Cambia el estado a que llega
	 * @param endState estado
	 */
	public void setEndState(State endState) {
		this.endState = endState;
	}
	
	/**
	 * Cambia el estado donde inicia
	 * @param startState estado
	 */
	public void setStartState(State startState) {
		this.startState = startState;
	}
}
