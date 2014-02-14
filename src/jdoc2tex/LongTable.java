package jdoc2tex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jdoc2tex.interpreter.DocClassOverviewConverter;
import jdoc2tex.interpreter.DocImplementedInterfacesConverter;
import jdoc2tex.interpreter.DocMethodSummaryConverter;
import jdoc2tex.interpreter.DocSuperClassSummaryConverter;
import jdoc2tex.interpreter.IDocConverter;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Parameter;

public class LongTable extends TexOut {

	public LongTable() {
		addTexpackage("longtable");
	}

	public String tex(ClassDoc klass) {

		IDocConverter[] converters = new IDocConverter[] {
				new DocClassOverviewConverter(),	//
				new DocSuperClassSummaryConverter(),	//
				new DocMethodSummaryConverter() 	//
				};

		StringBuilder sb = new StringBuilder();

		for (IDocConverter idoc : converters) {
			sb.append(idoc.interpretConverter(klass));
		}
		
		return sb.toString();
	}

}
