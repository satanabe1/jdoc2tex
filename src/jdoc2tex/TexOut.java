package jdoc2tex;

import java.util.ArrayList;
import java.util.Calendar;
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
		sb.append("\\title{Javadoc}").append("\n");
		sb.append("\\date{");
		sb.append(Calendar.getInstance().getTime().toString());
		sb.append("}").append("\n");
		sb.append("\\begin{document}").append("\n");
		sb.append("\\maketitle").append("\n");
		for (PackageDoc pd : root.specifiedPackages()) {
			sb.append("\\input{");
			sb.append(pd.name());
			sb.append("").append("}").append("\n");
		}
		sb.append("\\end{document}").append("\n");
		return sb.toString();
	}

	public String tex(PackageDoc pd) {
		StringBuilder sb = new StringBuilder();
		sb.append("\\clearpage").append("\n");
		sb.append("{\\LARGE ").append(pd.name()).append("}").append("\n");
		for (ClassDoc klass : pd.allClasses()) {
			sb.append("\\input{");
			sb.append(klass.qualifiedName());
			sb.append("").append("}");
			sb.append("\n");
		}
		return sb.toString();
	}

	protected String shortstack(String plain) {
		String ss = plain.toString();
		// {@link Hogehoge} を Hogehoge だけにする
		ss = ss.replaceAll("\\{@link (.*?)\\}", "$1");
		// <br> を \\ に置換
		ss = ss.replaceAll("<\\s*?[Bb][Rr].*?>", " \\\\\\\\");
		ss = escape(ss);

		ss = ss.replaceAll("\r\n", "").replaceAll("\r", "")
				.replaceAll("\n", "");

		StringBuilder sb = new StringBuilder();
		sb.append("\\shortstack[l]{");
		sb.append(ss);
		// sb.append("\\smallskip}");
		sb.append("{}}");
		return sb.toString();
	}

	protected String escape(String plain) {
		String ss = plain.toString();
		// {}_$#%&^
		// { を \{ に置換
		ss = ss.replaceAll("\\{", "\\\\{");
		// } を \} に置換
		ss = ss.replaceAll("\\}", "\\\\}");
		// _ を \_ に置換
		ss = ss.replaceAll("_", "\\\\_");
		// $ を \$ に置換
		ss = ss.replaceAll("\\$", "\\\\$");
		// # を \# に置換
		ss = ss.replaceAll("#", "\\\\#");
		// % を に置換
		ss = ss.replaceAll("%", "\\\\%");
		// & を に置換
		ss = ss.replaceAll("&", "\\\\&");
		// ^ を に置換
		ss = ss.replaceAll("\\^", "\\\\^");
		// < を に置換
		ss = ss.replaceAll("<", "\\\\textless");
		// > を に置換
		ss = ss.replaceAll(">", "\\\\textgreater");
		// | を に置換
		ss = ss.replaceAll("\\|", "\\\\textbar");
		return ss;
	}
}
