package jdoc2tex.layout;

import java.util.ArrayList;
import java.util.List;

public class DocTableManager implements ITableManager {

	private List<String> rows;

	private double[] width;

	public DocTableManager(double... width) {
		rows = new ArrayList<>();
		this.width = width;
	}

	public boolean addRow(String row) {
		return rows.add(row);
	}

	public boolean addRow(String row, String size) {
		return rows.add(size + " " + row);
	}

	public String generateTable() {
		StringBuilder sb = new StringBuilder();

		// sb.append("\\begin{longtable}{p{0.3\\textwidth}|p{0.7\\textwidth}}")
		// .append("\n");

		sb.append(begin());

		sb.append("\\end{longtable}");

		return sb.toString();
	}

	private String begin() {
		StringBuilder sb = new StringBuilder();
		sb.append("\\begin{longtable}{");

		sb.append("}");
		return sb.toString();
	}
}
