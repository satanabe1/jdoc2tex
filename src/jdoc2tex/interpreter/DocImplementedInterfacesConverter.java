package jdoc2tex.interpreter;

import jdoc2tex.layout.DocTableManager;
import jdoc2tex.layout.ITableManager;
import jdoc2tex.layout.TexFontSize;

import com.sun.javadoc.ClassDoc;

public class DocImplementedInterfacesConverter extends AbstractDocConverter {

	@Override
	public String interpretConverter(ClassDoc classDoc) {
		ITableManager table = new DocTableManager(1);

		if (classDoc.interfaces().length!=0) { 
			table.addRow(TexFontSize.FOOTNOTESIZE, "All Implemented Interfaces: \\\\ \\hspace{1zw} " + getInterfaces(classDoc));
			return table.generateTable();
		}
		return "";
	}
	
	private String getInterfaces(ClassDoc classDoc) {
		StringBuilder sb = new StringBuilder();
		for (ClassDoc interfaceDoc : classDoc.interfaces()) {
			sb.append(escape(interfaceDoc.qualifiedName())).append(", ");
		}
		return sb.toString();
	}
}
