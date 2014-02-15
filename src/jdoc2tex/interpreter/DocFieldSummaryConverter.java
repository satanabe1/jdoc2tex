package jdoc2tex.interpreter;

import jdoc2tex.layout.DocTableManager;
import jdoc2tex.layout.ITableManager;
import jdoc2tex.layout.TexFontSize;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.Type;

public class DocFieldSummaryConverter extends AbstractDocConverter {

	@Override
	public String interpretConverter(ClassDoc classDoc) {
		if (classDoc.fields(false).length==0) {
			return "";
		}

		ITableManager table = new DocTableManager(0.2, 0.25, 0.55);

		table.addRow("Field", "Type", "Summary");
		for (FieldDoc field : classDoc.fields(false)) {
			Type type = field.type();
			ClassDoc clsDoc = type.asClassDoc();
			String typeStr = new String();
			if (!type.isPrimitive() && clsDoc!=null) {
				typeStr = TexFontSize.TINY + " " + clsDoc.containingPackage() + " \\\\ ";
			} 

			typeStr += TexFontSize.SCRIPTSIZE + " " + type.typeName();
			table.addRow(TexFontSize.SCRIPTSIZE, par(field.name()), escape(typeStr), par(field.commentText()));
		}

		return table.generateTable();
	}

}
