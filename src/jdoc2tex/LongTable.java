package jdoc2tex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Parameter;

public class LongTable extends TexOut {

	public LongTable() {
		addTexpackage("longtable");
	}

	public String tex(ClassDoc klass) {
		String classname = klass.qualifiedName();
		String classsummary = shortstack(klass.commentText());

		StringBuilder sb = new StringBuilder();
		sb.append("\\begin{longtable}{p{0.3\\textwidth}|p{0.7\\textwidth}}")
				.append("\n");
		sb.append(" \\hline").append("\n");
		sb.append(" \\hline").append("\n");
		sb.append(" \\multicolumn{2}{l}{\\large ").append(classname)
				.append(" } \\\\").append("\n");
		sb.append(" \\hline").append("\n");
		sb.append(" \\multicolumn{2}{l}{");
		sb.append("\\footnotesize ");
		sb.append(classsummary);
		sb.append("} \\\\").append("\n");
		// sb.append(" \\hline \\hline").append("\n");
		sb.append(" \\hline").append("\n");
		sb.append("Method  & Summary \\\\").append("\n");
		sb.append(" \\hline").append("\n");
		for (MethodDoc method : klass.methods()) {
			sb.append(row(method));
			sb.append("\n");
		}
		sb.append("\\end{longtable}");
		return sb.toString();
	}

	private String row(MethodDoc method) {
		StringBuilder sb = new StringBuilder(" ");
		sb.append("\\scriptsize ");
		// sb.append(shortstack(getMethodName(method)));
		sb.append(par(getMethodName(method)));
		sb.append(" & ");
		sb.append("\\scriptsize ");
		// sb.append(shortstack(method.commentText()));
		sb.append(par(method.commentText()));
		sb.append(" \\\\").append("\n");
		sb.append(" \\hline");
		return sb.toString();
	}

	private String getMethodName(MethodDoc method) {
		StringBuilder sb = new StringBuilder();
		sb.append(method.name());
		if (method.parameters().length > 0) {
			sb.append("\\\\");
		}
		sb.append("\\scriptsize \\ \\ (");

		for (Parameter param : method.parameters()) {
			sb.append(param.type().simpleTypeName()).append(",");
		}
		if (method.parameters().length > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}

		sb.append(")");

		// int height = getHeight(method.commentText());
		// for (int i = 0; i < height; i++) {
		// sb.append(" \\\\ \\smallskip");
		// }

		return sb.toString();
	}

	/**
	 * &lt;br&gt;の個数-1を返す
	 * 
	 * @param text
	 * @return
	 */
	@SuppressWarnings("unused")
	private int getHeight(String text) {
		Pattern pattern = Pattern.compile("(<\\s*?[Bb][Rr].*?>)");
		Matcher matcher = pattern.matcher(text);
		int num = -1;
		while (matcher.find()) {
			num++;
		}
		return num;
	}

	private String par(String plain) {
		String ss = plain.toString();
		// {@link Hogehoge} を Hogehoge だけにする
		ss = ss.replaceAll("\\{@link (.*?)\\}", "$1");
		// <br> を \\ に置換
		ss = ss.replaceAll("<\\s*?[Bb][Rr].*?>", " \\\\par");
		ss = escape(ss);
		return ss.toString();
	}
}
