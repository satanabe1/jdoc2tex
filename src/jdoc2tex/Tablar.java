package jdoc2tex;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;

public class Tablar extends TexOut {

	public Tablar() {
		// TODO Auto-generated constructor stub
	}

	public String tex(ClassDoc klass) {
		String classname = klass.qualifiedName();
		String classsummary = shortstack(klass.commentText());

		StringBuilder sb = new StringBuilder();
		sb.append("\\begin{table}[]").append("\n");
		sb.append(" \\footnotesize").append("\n");
		sb.append(" \\label{javadoc:").append(classname).append("}")
				.append("\n");
		sb.append(" \\begin{tabular}{p{0.35\\textwidth}|p{0.65\\textwidth}}")
				.append("\n");
		sb.append(" \\hline").append("\n");
		sb.append(" \\multicolumn{2}{l}{\\large ").append(classname)
				.append(" } \\\\").append("\n");
		sb.append(" \\hline").append("\n");
		sb.append(" \\multicolumn{2}{l}{");
		sb.append(classsummary);
		sb.append("} \\\\").append("\n");
		sb.append(" \\hline").append("\n");
		sb.append("Method  & Summary \\\\").append("\n");
		sb.append(" \\hline \\hline").append("\n");
		for (MethodDoc method : klass.methods()) {
			sb.append(row(method));
			sb.append("\n");
		}
		sb.append(" \\end{tabular}").append("\n");
		sb.append("\\end{table}");
		return sb.toString();
	}

	private String row(MethodDoc method) {
		StringBuilder sb = new StringBuilder(" ");
		sb.append(method.name());
		sb.append(" & ");
		sb.append(shortstack(method.commentText()));
		sb.append(" \\\\").append("\n");
		sb.append(" \\hline");
		return sb.toString();
	}
}
