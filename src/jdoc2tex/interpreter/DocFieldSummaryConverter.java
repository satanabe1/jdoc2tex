package jdoc2tex.interpreter;

import jdoc2tex.layout.DocTableManager;
import jdoc2tex.layout.ITableManager;
import jdoc2tex.layout.TexFontSize;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Parameter;

public class DocFieldSummaryConverter extends AbstractDocConverter {

	@Override
	public String interpretConverter(ClassDoc classDoc) {

		ITableManager table = new DocTableManager(0.3, 0.7);

		table.addRow("Method", "Summary");
		for (FieldDoc field : classDoc.fields()) {
			table.addRow(TexFontSize.SCRIPTSIZE, getFieldName(field),
					par(field.commentText()));
		}

		return table.generateTable();
	}

	private String getFieldName(FieldDoc field) {
		StringBuilder sb = new StringBuilder();
		sb.append(escape(field.name()));
			sb.append("\\\\");
		sb.append("\\ \\ (");


		sb.append(")");
		return sb.toString();
	}
}
