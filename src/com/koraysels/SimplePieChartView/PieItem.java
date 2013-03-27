package com.koraysels.SimplePieChartView;

public class PieItem {
	private int count;
	private String label;
	private float percent;
	private int color;

	public PieItem(int count, String label, float percent, int color) {
		this.count = count;
		this.label = label;
		this.percent = percent;
		this.color = color;
	}

	public int getColor() {
		return this.color;
	}

	public int getCount() {
		return this.count;
	}

	public float getPercent() {
		return this.percent;
	}

	public String getLabel() {
		return this.label;
	}
}
