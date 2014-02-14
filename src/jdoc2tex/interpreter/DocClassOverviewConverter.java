package jdoc2tex.interpreter;

import com.sun.javadoc.ClassDoc;

public class DocClassOverviewConverter extends AbstractDocConverter{

	@Override
	public String interpretConverter(ClassDoc classDoc) {
		String classname = classDoc.qualifiedName();
		String classsummary = shortstack(classDoc.commentText());
		
		StringBuilder sb = new StringBuilder();
		sb.append(" \\hline").append("\n");
		sb.append(" \\hline").append("\n");
		sb.append(" \\multicolumn{2}{l}{\\large ").append(classname)
				.append(" } \\\\").append("\n");
		sb.append(" \\hline").append("\n");
		sb.append(" \\multicolumn{2}{l}{");
		sb.append("\\footnotesize ");
		sb.append(classsummary);
		sb.append("} \\\\").append("\n");
		
		return sb.toString();
	}
	
}
