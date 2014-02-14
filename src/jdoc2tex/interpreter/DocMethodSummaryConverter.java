package jdoc2tex.interpreter;

import jdoc2tex.layout.DocTableManager;
import jdoc2tex.layout.ITableManager;
import jdoc2tex.layout.TexFontSize;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Parameter;

public class DocMethodSummaryConverter extends AbstractDocConverter {

	@Override
	public String interpretConverter(ClassDoc classDoc) {
		if (classDoc.interfaces().length==0) {
			return "";
		}

		ITableManager table = new DocTableManager(0.3, 0.7);

		table.addRow("Method", "Summary");
		for (MethodDoc method : classDoc.methods()) {
			table.addRow(TexFontSize.SCRIPTSIZE, getMethodName(method),
					par(method.commentText()));
		}

		return table.generateTable();
	}

	private String getMethodName(MethodDoc method) {
		StringBuilder sb = new StringBuilder();
		sb.append(escape(method.name()));
		if (method.parameters().length > 0) {
			sb.append("\\\\");
		}
		sb.append("\\ \\ (");

		for (Parameter param : method.parameters()) {
			sb.append(escape(param.type().simpleTypeName())).append(",");
		}
		if (method.parameters().length > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}

		sb.append(")");
		return sb.toString();
	}
}
