package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis3D;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChartDemoPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public BarChartDemoPanel() {
		setLayout(new BorderLayout());

		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 60));
		topPanel.setBorder(BorderFactory.createTitledBorder("柱状图："));

		CategoryDataset dataset1 = getDataSet1();
		ChartPanel charPanel1 = getChartPanel(dataset1);
		charPanel1.setPreferredSize(new Dimension(400, 400));

		topPanel.add(charPanel1);

		add(topPanel, BorderLayout.NORTH);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new BarChartDemoPanel());
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);

	}

	/**
	 * 获取一个演示用的简单数据集对象
	 * 
	 * @return
	 */
	private CategoryDataset getDataSet1() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(100, "北京", "苹果");
		// dataset.addValue(200, "北京", "梨子");
		// dataset.addValue(300, "北京", "葡萄");
		// dataset.addValue(400, "北京", "香蕉");
		// dataset.addValue(500, "北京", "荔枝");
		return dataset;
	}

	private JFreeChart getBarChart(CategoryDataset dataset) {

		JFreeChart chart = ChartFactory.createBarChart3D("水果产量图", // 图表标题
				"水果", // 目录轴的显示标签
				"产量", // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true, // 是否显示图例(对于简单的柱状图必须是false)
				true, // 是否生成工具
				false // 是否生成URL链接
				);

		chart.setBackgroundPaint(SystemColor.controlHighlight);

		// 设置图标标题字体
		chart.getTitle().setFont(new Font("宋体", Font.PLAIN, 20));

		CategoryPlot plot = chart.getCategoryPlot();
		// 设置横轴标题字体
		plot.getDomainAxis().setLabelFont(new Font("宋体", Font.PLAIN, 14));
		plot.getDomainAxis().setCategoryLabelPositions(
				CategoryLabelPositions.UP_45);

		// 设置横轴标记的字体
		plot.getDomainAxis().setTickLabelFont(new Font("宋体", Font.PLAIN, 12));
		// 设置横轴标记字体颜色
		plot.getDomainAxis().setTickLabelPaint(Color.RED);

		// 设置纵轴标题字体
		plot.getRangeAxis().setLabelFont(new Font("宋体", Font.PLAIN, 14));

		// 设置纵轴标记字体
		NumberAxis3D numberAxis3D = (NumberAxis3D) plot.getRangeAxis();
		numberAxis3D
				.setStandardTickUnits(NumberAxis3D.createIntegerTickUnits());
		numberAxis3D.setTickLabelPaint(Color.RED);

		// 设置图例字体
		BarRenderer3D renderer3D = (BarRenderer3D) plot.getRenderer();
		renderer3D.setBaseLegendTextFont(new Font("宋体", Font.PLAIN, 14));
		renderer3D.setSeriesPaint(0, Color.ORANGE);

		int k = dataset.getColumnCount();
		if (k == 1) {
			plot.getDomainAxis().setLowerMargin(0.26);
			plot.getDomainAxis().setUpperMargin(0.66);
		} else if (k < 6) {
			double margin = (1.0 - k * 0.08) / 3;
			plot.getDomainAxis().setLowerMargin(margin);
			plot.getDomainAxis().setUpperMargin(margin);
			// domainAxis.setCategoryMargin(0.1);
			((BarRenderer3D) plot.getRenderer()).setItemMargin(margin);
		} else {
			((BarRenderer3D) plot.getRenderer()).setItemMargin(0.1);
		}

		return chart;
	}

	private ChartPanel getChartPanel(CategoryDataset dataset) {

		JFreeChart chart = getBarChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPopupMenu(null);// 不显示弹出菜单

		return chartPanel;
	}
}
