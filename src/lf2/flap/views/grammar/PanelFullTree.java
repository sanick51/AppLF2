package lf2.flap.views.grammar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import lf2.flap.models.entity.NodeTree;

public class PanelFullTree extends JPanel{

	private static final long serialVersionUID = 1L;
	private NodeTree<String> root;
	private String ruteTota = "";
	
	public PanelFullTree(NodeTree<String> nodeTree) {
		root = nodeTree;
	}
	
	@Override
	public void paint(Graphics g) {
		String [] total = ruteTota.split(",");
		Graphics2D g2d = (Graphics2D)(g);
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, 2000, 1000);
		if (root != null) {
	        g2d.setColor(Color.GREEN);
			g2d.fillOval((int)getSize().getWidth()/2, 0, 30, 30);
			 g2d.setColor(Color.BLACK);
			 if (root.getInfo() != null) {
				 g2d.drawString(root.getInfo() , ((int)getSize().getWidth()/2)+15 , 15);
			}
			drawChilds(g2d, root, 0, (int)getSize().getWidth() , 30 , (int)getSize().getWidth()/2, 0 , total);
		}
	}
	
	public void drawChilds(Graphics g2d , NodeTree<String> node , int minX , int xSize , int y, int originX , int originY, String[] total) {
		g2d.setColor(Color.RED);
		if (node.getSons().size() != 0 ) {
			int childsSize = (xSize/node.getSons().size()) ;
			int childSizeTotal = (childsSize/2);
			for (int i = 0; i < node.getSons().size(); i++) {
				for (int j = 0; j < total.length; j++) {
					if (total[j].equals(node.getSons().get(i).getInfo())) {
						g2d.setColor(Color.GREEN);
						g2d.fillOval((childsSize*(i+1)-childSizeTotal)+minX, y+50, 30, 30);
					}else {
						g2d.fillOval((childsSize*(i+1)-childSizeTotal)+minX, y+50, 30, 30);
					}
				}
				g2d.setColor(Color.BLACK);
				g2d.drawString(node.getSons().get(i).getInfo(), ((childsSize*(i+1)-childSizeTotal)+15)+minX , y+65);
				g2d.drawLine(originX+15, originY+30, (childsSize*(i+1)-childSizeTotal+15)+minX, y+50);
				if (node.getSons().get(i).getSons() != null) {
					drawChilds(g2d, node.getSons().get(i), childsSize*i+minX , childsSize , y+50 , childsSize*(i+1)-childSizeTotal+minX, (y+50), total);
				}
			}
		}
	}
	
	public void refreshTree(NodeTree<String> newRoot , String rute ) {
		root = newRoot;
		ruteTota = rute ;
		repaint();
		revalidate();
	}
}
