package jdoc2tex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jdoc2tex.interpreter.DocClassOverviewConverter;
import jdoc2tex.interpreter.DocMethodSummaryConverter;
import jdoc2tex.interpreter.IDocConverter;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Parameter;

public class LongTable extends TexOut {

	public LongTable() {
		addTexpackage("longtable");
	}

	public String tex(ClassDoc klass) {

		IDocConverter[] converters = new IDocConverter[]{
			new DocClassOverviewConverter(),
			new DocMethodSummaryConverter()
		};
		
		StringBuilder sb = new StringBuilder();
		sb.append("\\begin{longtable}{p{0.3\\textwidth}|p{0.7\\textwidth}}")
				.append("\n");
		
		for (IDocConverter idoc : converters) {
			sb.append(idoc.interpretConverter(klass));
		}
		
		sb.append("\\end{longtable}");
		return sb.toString();
	}


}
