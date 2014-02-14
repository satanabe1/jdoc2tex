package jdoc2tex;


import jdoc2tex.interpreter.DocClassOverviewConverter;
import jdoc2tex.interpreter.DocFieldSummaryConverter;
import jdoc2tex.interpreter.DocImplementedInterfacesConverter;
import jdoc2tex.interpreter.DocMethodSummaryConverter;
import jdoc2tex.interpreter.DocSuperClassSummaryConverter;
import jdoc2tex.interpreter.IDocConverter;

import com.sun.javadoc.ClassDoc;
import com.sun.tools.javadoc.DocletInvoker;

public class LongTable extends TexOut {

	public LongTable() {
		addTexpackage("longtable");
	}

	public String tex(ClassDoc klass) {
		IDocConverter[] converters = new IDocConverter[] {
				new DocClassOverviewConverter(),	//
				new DocSuperClassSummaryConverter(),	//
				new DocImplementedInterfacesConverter(),	//
				new DocFieldSummaryConverter(),		//
				new DocMethodSummaryConverter() 	//
				};

		StringBuilder sb = new StringBuilder();

		sb.append(" { \n");
		for (IDocConverter idoc : converters) {
			sb.append(idoc.interpretConverter(klass));
		}
		sb.append(" } \n");
		
		return sb.toString();
	}

}
