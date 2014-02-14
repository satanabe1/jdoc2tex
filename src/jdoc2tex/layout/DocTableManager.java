package jdoc2tex.layout;

import java.util.ArrayList;
import java.util.List;

public class DocTableManager implements ITableManager {

	private List<String[]> rows;

	private double[] width;

	private String topMargin = "\\vspace{-2zw}\n";

	public DocTableManager(double... width) {
		rows = new ArrayList<>();
		this.width = width;
	}

	public void enableTopMargin() {
		topMargin = "";
	}

	public boolean addRow(String... row) {
		return rows.add(row);
	}

	public boolean addRow(TexFontSize size, String... row) {
		String[] tmp = new String[row.length];
		for (int i = 0; i < row.length; i++) {
			tmp[i] = size + " "
					+ row[i].replaceAll("\\\\\\\\", "\\\\par \\" + size + " ");
		}
		return rows.add(tmp);
	}

	public String generateTable() {
		StringBuilder sb = new StringBuilder();

		sb.append(topMargin);
		sb.append(begin()).append("\n"); // \\begin{longtable}{p{0.3\\textwidth}|p{0.7\\textwidth}}
		sb.append(" \\hline").append("\n");
		for (String[] row : rows) {
			sb.append(genrow(row));
			sb.append("\n");
		}
		sb.append("\\end{longtable}").append("\n");

		return sb.toString();
	}

	/**
	 * \\begin{longtable}{p{0.3\\textwidth}|p{0.7\\textwidth}}
	 * 
	 * @return
	 */
	private String begin() {
		StringBuilder sb = new StringBuilder();
		sb.append("\\begin{longtable}{");
		for (double wd : width) {
			// p{0.3\\textwidth}|
			sb.append("p{");
			sb.append(wd).append("\\textwidth}|");
		}
		if (width.length > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("}");
		return sb.toString();
	}

	/**
	 * row[0] & row[1] & row[2] ...
	 * 
	 * @param row
	 * @return
	 */
	private String genrow(String[] row) {
		StringBuilder sb = new StringBuilder();
		for (String cel : row) {
			sb.append(cel);
			sb.append("&");
		}
		if (row.length > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		if (sb.length() != 0) {
			sb.append("\\\\ ").append("\n");
		}
		sb.append(" \\hline");
		return sb.toString();
	}
}
