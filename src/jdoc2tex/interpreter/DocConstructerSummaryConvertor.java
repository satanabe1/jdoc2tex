package jdoc2tex.interpreter;

import jdoc2tex.layout.DocTableManager;
import jdoc2tex.layout.ITableManager;
import jdoc2tex.layout.TexFontSize;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.ConstructorDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Parameter;

public class DocConstructerSummaryConvertor extends AbstractDocConverter {

	@Override
	public String interpretConverter(ClassDoc classDoc) {
		if (classDoc.constructors().length == 0) {
			return "";
		}

		ITableManager table = new DocTableManager(0.3, 0.7);

		table.addRow(TexFontSize.FOOTNOTESIZE,"Constructor", "Summary");
		for (ConstructorDoc constructor : classDoc.constructors()) {
			// デフォルトコンストラクタのコメント
			String constructorComment = constructor.commentText();
			if (constructorComment.equals("") && constructor.parameters().length==0) {
				constructorComment = "新しい" + classDoc.name() + "クラスのインスタンスを生成します．";
			}

			table.addRow(TexFontSize.SCRIPTSIZE, getConstructorName(constructor), par(constructorComment));
		}

		return table.generateTable();
	}

	private String getConstructorName(ConstructorDoc constructor) {
		StringBuilder sb = new StringBuilder();
		sb.append(escape(constructor.name()));
		if (constructor.parameters().length > 0) {
			sb.append("\\\\");
		}
		sb.append("\\ \\ (");

		for (Parameter param : constructor.parameters()) {
			sb.append(escape(param.type().simpleTypeName())).append(", ");
		}
		if (constructor.parameters().length > 0) {
			sb.deleteCharAt(sb.length() - 1);
			sb.deleteCharAt(sb.length() - 1);
		}

		sb.append(")");
		return sb.toString();
	}

}
