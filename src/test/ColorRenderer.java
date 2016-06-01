package test;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

class ColorRenderer extends JComponent implements ListCellRenderer {

	Color color;

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(color);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		if (value instanceof Color) {
			color = (Color) value;
			if (isSelected) {
				color = color.darker();
			}
		}
		return this;
	}

	@Override
	public Dimension getPreferredSize() {
		Dimension d = super.getPreferredSize();
		d.height = 20;
		return d;
	}

	private static void createGUI() {
		JFrame frame = new JFrame();
		Vector v = new Vector(0);
		v.add(Color.red);
		v.add(Color.blue);
		v.add(Color.green);
		v.add(Color.orange);
		JList list = new JList(v);
		list.setCellRenderer(new ColorRenderer());
		frame.add(new JScrollPane(list));
		frame.setSize(200, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				createGUI();
			}
		});
	}
}
