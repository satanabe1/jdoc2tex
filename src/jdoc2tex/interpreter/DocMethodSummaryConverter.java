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
		if (classDoc.methods().length == 0) {
			return "";
		}

		ITableManager table = new DocTableManager(0.3, 0.7);

		table.addRow("Method", "Summary");
		for (MethodDoc method : classDoc.methods()) {

			table.addRow(TexFontSize.SCRIPTSIZE, getMethodName(method), par(getCommentSuperClass(classDoc, method)));
		}

		return table.generateTable();
	}

	private String getMethodName(MethodDoc method) {
		StringBuilder sb = new StringBuilder();
		if (method.isStatic()) {
			sb.append(TexFontSize.TINY + " static \\\\ \n " + TexFontSize.SCRIPTSIZE +" ");
		}
		
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

	private String getCommentSuperClass(ClassDoc classDoc, MethodDoc methodDoc) {
		MethodDoc mt = getAnothorClasssMethodDoc(classDoc, methodDoc);
		if (mt == null) {
			return "";
		}

		if (!mt.commentText().equals("")) {
			return mt.commentText();
		}

		for (ClassDoc cl : classDoc.interfaces()) {
			String comment = getCommentSuperClass(cl, mt);
			if (!comment.equals("")) {
				return comment;
			}
		}
		if (classDoc.superclass() != null) {
			String comment = getCommentSuperClass(classDoc.superclass(), mt);
			if (!comment.equals("")) {
				return comment;
			}
		}
		return "";
	}

	private MethodDoc getAnothorClasssMethodDoc(ClassDoc targetClassDoc, MethodDoc thisClassMethodDoc) {
		MethodDoc m = thisClassMethodDoc;
		for (MethodDoc mt : targetClassDoc.methods()) {
			if (mt.name().equals(m.name()) && mt.signature().equals(m.signature())) {
				return mt;
			}
		}
		return null;
	}
}
