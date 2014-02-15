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

		table.addRow(TexFontSize.FOOTNOTESIZE,"Field", "Type", "Summary");
		for (FieldDoc field : classDoc.fields(false)) {
			// フィールド名の表示文字列
			String fieldName = field.name();
			if (field.isStatic()) {
				fieldName = TexFontSize.TINY + " static \\\\ "+TexFontSize.SCRIPTSIZE + " " + fieldName;
			}
			
			// フィールドの型の表示文字列
			Type type = field.type();
			String typeStr = type.typeName();
			ClassDoc clsDoc = type.asClassDoc();
			if (!type.isPrimitive() && clsDoc!=null) {
				typeStr = TexFontSize.TINY + " " + clsDoc.containingPackage() + " \\\\ " + TexFontSize.SCRIPTSIZE + " " + typeStr;
			}
			
			table.addRow(TexFontSize.SCRIPTSIZE, par(fieldName), escape(typeStr), par(field.commentText()));
		}

		return table.generateTable();
	}

}
