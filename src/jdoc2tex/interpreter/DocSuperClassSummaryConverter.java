package jdoc2tex.interpreter;

import java.util.Stack;

import jdoc2tex.layout.DocTableManager;
import jdoc2tex.layout.ITableManager;
import jdoc2tex.layout.TexFontSize;

import com.sun.javadoc.ClassDoc;

public class DocSuperClassSummaryConverter extends AbstractDocConverter {

	@Override
	public String interpretConverter(ClassDoc classDoc) {
		ITableManager table = new DocTableManager(1);

		table.addRow(
				TexFontSize.FOOTNOTESIZE,
				genarateSuperClassList(pushSuperClass(new Stack<String>(),
						classDoc)));

		return table.generateTable();
	}

	private Stack<String> pushSuperClass(Stack<String> superClassStack,
			ClassDoc currentClassDoc) {
		superClassStack.push(escape(currentClassDoc.qualifiedName()));
		if (currentClassDoc.superclass() != null) {
			superClassStack = pushSuperClass(superClassStack,
					currentClassDoc.superclass());
		}
		return superClassStack;
	}

	private String genarateSuperClassList(Stack<String> superClassStack) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (0 < superClassStack.size()) {
			sb.append("\\hspace{" + i + "em} \n");
			sb.append(superClassStack.pop());
			if (!superClassStack.empty()) {
				sb.append(" \\\\ \n");
			}
			i++;
		}
		return sb.toString();
	}

}
