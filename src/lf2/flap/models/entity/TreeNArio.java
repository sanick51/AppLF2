package lf2.flap.models.entity;

import java.util.Comparator;

public class TreeNArio<T> {

	protected NodeTree<T> root;
	protected Comparator<T> comparator;

	public TreeNArio(T info, Comparator<T> comparator) {
		this.root = new NodeTree<>(info);
		this.comparator = comparator;
	}

	public void addSon(T father, T info) {
		addSon(root, father, info);
	}

	private void addSon(NodeTree<T> root, T father, T info) {
		if (comparator.compare(father, root.info) != 0) {
			for (NodeTree<T> nodeTree : root.sons) {
				addSon(nodeTree, father, info);
			}
		} else {
			root.sons.add(new NodeTree<>(root, info));
		}
	}

	public NodeTree<T> search(T info) {
		return (root != null) ? search(root, info) : null;
	}
	
	public NodeTree<T> getRoot() {
		return root;
	}
	
	public Comparator<T> getComparator() {
		return comparator;
	}

	private NodeTree<T> search(NodeTree<T> root, T info) {
		if (comparator.compare(root.info, info) == 0) {
			return root;
		} else if (root.sons.isEmpty()) {
			return null;
		} else {
			for (int i = 0; i < root.sons.size(); i++) {
				NodeTree<T> aux = search(root.sons.get(i), info);
				if (aux != null) {
					return aux;
				}
			}
			return null;
		}
	}
}
