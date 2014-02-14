package jdoc2tex.interpreter;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Parameter;

public class DocMethodSummaryConverter extends AbstractDocConverter {

	@Override
	public String interpretConverter(ClassDoc classDoc) {
		StringBuilder sb = new StringBuilder();
		sb.append(" \\hline").append("\n");
		sb.append("Method  & Summary \\\\").append("\n");
		sb.append(" \\hline").append("\n");
		for (MethodDoc method : classDoc.methods()) {
			sb.append(row(method));
			sb.append("\n");
		}
		
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
		return sb.toString();
	}
}
