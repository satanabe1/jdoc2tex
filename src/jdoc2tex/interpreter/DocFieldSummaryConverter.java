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
		if (classDoc.fields(false).length==0) {
			return "";
		}

		ITableManager table = new DocTableManager(0.3, 0.7);

		table.addRow("Field", "Summary");
		for (FieldDoc field : classDoc.fields()) {
			System.out.println(classDoc.name()+"."+field.name()+"   :  "+field.commentText());
			table.addRow(TexFontSize.SCRIPTSIZE, par(field.name()),
					par(field.commentText()));
		}

		return table.generateTable();
	}

}
