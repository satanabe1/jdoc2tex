package jdoc2tex.layout;

import java.util.ArrayList;
import java.util.List;

public class DocTableManager implements ITableManager {

	private List<String[]> rows;

	private double[] width;

	private String topMargin = "\\vspace{-2zw}\n";
	private String paddingCommand = "\\rule{0.3zw}{0ex} ";

	public DocTableManager(double... width) {
		rows = new ArrayList<>();
		this.width = width;
	}

	public void enableTopMargin() {
		topMargin = "";
	}

	public boolean addRow(String... row) {
		return addRow(TexFontSize.SCRIPTSIZE, row);
	}

	public boolean addRow(TexFontSize size, String... row) {
		String[] tmp = new String[row.length];
		if (empty(row)) {
			return rows.add(row);
		}
		for (int i = 0; i < row.length; i++) {
			tmp[i] = row[i].replaceAll("\\\\\\\\", "\\\\par");
			tmp[i] = paddingLeft(tmp[i]);
			tmp[i] = size + " "
					+ tmp[i].replaceAll("\\\\par", "\\\\par \\" + size + " ");

			// tmp[i] = size + " "
			// + row[i].replaceAll("\\\\\\\\", "\\\\par \\" + size + " ");
		}
		return rows.add(tmp);
	}

	public String generateTable() {
		StringBuilder sb = new StringBuilder();

		sb.append(topMargin);
		// セル高さを0.7倍に
		sb.append("\\renewcommand{\\arraystretch}{0.75}").append("\n");
		sb.append(begin()).append("\n"); // \\begin{longtable}{p{0.3\\textwidth}|p{0.7\\textwidth}}
		sb.append(" \\hline").append("\n");
		for (String[] row : rows) {
			sb.append(genrow(row));
			sb.append("\n");
		}
		sb.append("\\end{longtable}").append("\n");
		sb.append("\\renewcommand{\\arraystretch}{1.0}").append("\n");

		return sb.toString();
	}

	/**
	 * \\begin{longtable}{p{0.3\\textwidth}|p{0.7\\textwidth}}
	 * 
	 * @return
	 */
	private String begin() {
		StringBuilder sb = new StringBuilder();
		// セル内左右パディングを0mmに
		sb.append("\\setlength{\\tabcolsep}{0mm}").append("\n");
		sb.append("\\begin{longtable}{");
		for (double wd : width) {
			// p{0.3\\textwidth}|
			sb.append("p{");
			sb.append(wd).append("\\textwidth}|");
		}
		if (width.length > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("\n");
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
		if (empty(row)) {
			sb.append(" \\hline");
			return sb.toString();
		}
		for (String cel : row) {
			sb.append(cel);
			// sb.append(paddingLeft(cel));
			sb.append("&");
		}
		if (row.length > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("\\\\ ").append("\n");
		sb.append(" \\hline");
		return sb.toString();
	}

	private boolean empty(String[] row) {
		for (String s : row) {

			if (s.length() != 0) {
				return false;
			}
		}
		return true;
	}

	private String paddingLeft(String plain) {
		return paddingCommand
				+ plain.replaceAll("\\\\par", "\\\\par \\" + paddingCommand
						+ " ");
		// return plain;
	}
}
