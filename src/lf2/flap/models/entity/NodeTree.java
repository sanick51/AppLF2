package lf2.flap.models.entity;

import java.util.ArrayList;

public class NodeTree<T> {

	protected ArrayList<NodeTree<T>> sons;
	protected NodeTree<T> father;
	protected T info; 

	public NodeTree(T info) {
		this.father = null;
		this.info = info;
		sons = new ArrayList<>();
	}

	public NodeTree(NodeTree<T> father, T info) {
		this.father = father;
		this.info = info;
		sons = new ArrayList<>();
	}

	public ArrayList<NodeTree<T>> getSons() {
		return sons;
	}

	public void setSons(ArrayList<NodeTree<T>> sons) {
		this.sons = sons;
	}

	public NodeTree<T> getFather() {
		return father;
	}

	public void setFather(NodeTree<T> father) {
		this.father = father;
	}

	public T getInfo() {
		return info;
	}
}
