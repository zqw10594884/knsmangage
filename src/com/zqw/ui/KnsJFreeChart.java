package com.zqw.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class KnsJFreeChart   {
	ArrayList<String[]> datasetLst = new ArrayList<String[]>();
	
	public ArrayList<String[]> getDatasetLst() {
		return datasetLst;
	}

	public void setDatasetLst(ArrayList<String[]> datasetLst) {
		this.datasetLst = datasetLst;
	}

	public KnsJFreeChart() {
		super();
	}
	public CategoryDataset createDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < datasetLst.size(); i++) {
			String[] s = datasetLst.get(i);
			dataset.setValue(Double.parseDouble(s[0]), s[1], s[2]);
		}
		return dataset;

	}

	public JFreeChart createChart(CategoryDataset dataset) {
		JFreeChart jfreechart = ChartFactory.createBarChart("客户销量统计", // 标题
				"货物", // categoryAxisLabel （category轴，横轴，X轴的标签）
				"销量", // valueAxisLabel（value轴，纵轴，Y轴的标签）
				dataset, // dataset
				PlotOrientation.VERTICAL, false, // legend
				false, // tooltips
				false); // URLs
		Font labelFont = new Font("SansSerif", Font.BOLD, 16);

		jfreechart.setTextAntiAlias(false);
		jfreechart.setBackgroundPaint(Color.white);

		CategoryPlot plot = jfreechart.getCategoryPlot();// 获得图表区域对象
		// 设置横虚线可见
		plot.setRangeGridlinesVisible(true);
		// 虚线色彩
		plot.setRangeGridlinePaint(Color.gray);
		// 数据轴精度
		NumberAxis vn = (NumberAxis) plot.getRangeAxis();
		 vn.setAutoRangeIncludesZero(true);
//		DecimalFormat df = new DecimalFormat("#0.0");
//		vn.setNumberFormatOverride(df); // 数据轴数据标签的显示格式

		// x轴设置
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setLabelFont(labelFont);// 轴标题
		domainAxis.setTickLabelFont(labelFont);// 轴数值
//		 Lable（Math.PI/3.0）度倾斜
		 domainAxis.setCategoryLabelPositions(CategoryLabelPositions
		 .createUpRotationLabelPositions(Math.PI / 5.0));
		domainAxis.setMaximumCategoryLabelWidthRatio(6.00f);// 横轴上的 Lable
		// 是否完整显示

		// 设置距离图片左端距离
		domainAxis.setLowerMargin(0.01);
		// 设置距离图片右端距离
		domainAxis.setUpperMargin(0.01);
		// 设置 columnKey 是否间隔显示
		// domainAxis.setSkipCategoryLabelsToFit(true);
		plot.setDomainAxis(domainAxis);
		// 设置柱图背景色（注意，系统取色的时候要使用16位的模式来查看颜色编码，这样比较准确）
		plot.setBackgroundPaint(new Color(255, 255, 204));

		// y轴设置
		ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setLabelFont(labelFont);
		rangeAxis.setTickLabelFont(labelFont);
		// 设置最高的一个 Item 与图片顶端的距离
		rangeAxis.setUpperMargin(0.15);
		// 设置最低的一个 Item 与图片底端的距离
		rangeAxis.setLowerMargin(0.15);
		plot.setRangeAxis(rangeAxis);

		// 解决中文乱码问题(关键)
		TextTitle textTitle = jfreechart.getTitle();
		textTitle.setFont(new Font("黑体", Font.PLAIN, 20));
		domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
		domainAxis.setLabelFont(new Font("宋体", Font.PLAIN, 12));
		vn.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
		vn.setLabelFont(new Font("黑体", Font.PLAIN, 12));
		// jfreechart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));

		BarRenderer renderer = new BarRenderer();
		// 设置柱子宽度
		renderer.setMaximumBarWidth(0.2);
		// 设置柱子高度
		renderer.setMinimumBarLength(0.2);
		// 设置柱子边框颜色
		renderer.setBaseOutlinePaint(Color.BLACK);
		// 设置柱子边框可见
		renderer.setDrawBarOutline(true);
		// // 设置柱的颜色
		renderer.setSeriesPaint(0, Color.decode("#8BBA00"));
		// 设置每个地区所包含的平行柱的之间距离
		renderer.setItemMargin(5);
		jfreechart.getRenderingHints().put(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
		// 显示每个柱的数值，并修改该数值的字体属性
		renderer.setIncludeBaseInRange(true);
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		plot.setRenderer(renderer);
		// 设置柱的透明度
		plot.setForegroundAlpha(1.0f);

		// 背景色 透明度
		plot.setBackgroundAlpha(0.5f);
		
		return jfreechart;
	}
}