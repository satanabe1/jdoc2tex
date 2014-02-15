package jdoc2tex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.PackageDoc;
import com.sun.javadoc.RootDoc;

abstract public class TexOut {

	List<String> texpackages = new ArrayList<>();

	abstract public String tex(ClassDoc klass);

	public void addTexpackage(String packagename) {
		texpackages.add(packagename);
	}

	public String tex(RootDoc root) {
		StringBuilder sb = new StringBuilder();
		sb.append("\\documentclass{jarticle}").append("\n");
		for (String usepackage : texpackages) {
			sb.append("\\usepackage{");
			sb.append(usepackage);
			sb.append("}").append("\n");
		}
		sb.append("\\setlength{\\textwidth}{155truemm}").append("\n");
		sb.append("\\title{Javadoc}").append("\n");
		sb.append("\\setlength{\\oddsidemargin}{5truemm}").append("\n");
		sb.append("\\addtolength{\\topmargin}{-1.3truein}").append("\n");
		sb.append("\\setlength{\\textheight}{262truemm}").append("\n");
		sb.append("\\date{");
		sb.append(Calendar.getInstance().getTime().toString());
		sb.append("}").append("\n");
		sb.append("\\begin{document}").append("\n");
		sb.append("\\maketitle").append("\n");

		PackageDoc[] packages = root.specifiedPackages();
		Arrays.sort(packages, new Comparator<PackageDoc>() {
			@Override
			public int compare(PackageDoc o1, PackageDoc o2) {
				return o1.name().compareTo(o2.name());
			}
		});

		for (PackageDoc pd : packages) {
			sb.append("\\input{");
			sb.append("javadoc/");
			sb.append(pd.name());
			sb.append("}").append("\n");
		}
		sb.append("\\end{document}").append("\n");
		return sb.toString();
	}

	public String tex(PackageDoc pd) {
		StringBuilder sb = new StringBuilder();
		sb.append("\\clearpage").append("\n");
		sb.append("{\\LARGE $Package: ").append(pd.name()).append("$}")
				.append("\n");
		for (ClassDoc klass : pd.allClasses()) {
			sb.append("\\input{");
			sb.append("javadoc/");
			sb.append(klass.qualifiedName());
			sb.append("}");
			sb.append("\n");
		}
		return sb.toString();
	}
}
