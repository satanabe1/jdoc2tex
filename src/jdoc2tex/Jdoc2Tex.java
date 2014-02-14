package jdoc2tex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.javadoc.*;

public class Jdoc2Tex {

	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		// com.sun.javadoc.Doclet.
		List<String> arglist = new ArrayList<>();
		arglist.add("-doclet");
		arglist.add(Jdoc2Tex.class.getCanonicalName());
		arglist.addAll(Arrays.asList(args));
		// com.sun.tools.javadoc.Main
		// .main(//
		// "-doclet",
		// Jdoc2Tex.class.getCanonicalName(),
		// //
		// "-sourcepath",
		// "../cgg/src",//
		// "-classpath",
		// "/Users/wtnbsts/workspace1/Lib/astparser_4.3/org.eclipse.text_3.5.300.v20121210-150853.jar:/Users/wtnbsts/workspace1/Lib/astparser_4.3/org.eclipse.osgi_3.9.0.v20121210-201226.jar:/Users/wtnbsts/workspace1/Lib/astparser_4.3/org.eclipse.jface_3.8.200.v20121212-113328.jar:/Users/wtnbsts/workspace1/Lib/astparser_4.3/org.eclipse.jface.text_3.8.100.v20121126-164058.jar:/Users/wtnbsts/workspace1/Lib/astparser_4.3/org.eclipse.jdt.core_3.9.0.v20121208-144805.jar:/Users/wtnbsts/workspace1/Lib/astparser_4.3/org.eclipse.equinox.registry_3.5.300.v20120731-134527.jar:/Users/wtnbsts/workspace1/Lib/astparser_4.3/org.eclipse.equinox.preferences_3.5.0.v20120918-182152.jar:/Users/wtnbsts/workspace1/Lib/astparser_4.3/org.eclipse.equinox.common_3.6.100.v20120522-1841.jar:/Users/wtnbsts/workspace1/Lib/astparser_4.3/org.eclipse.core.runtime_3.9.0.v20121004-163638.jar:/Users/wtnbsts/workspace1/Lib/astparser_4.3/org.eclipse.core.resources_3.8.100.v20121214-121812.jar:/Users/wtnbsts/workspace1/Lib/astparser_4.3/org.eclipse.core.jobs_3.5.300.v20120912-155018.jar:/Users/wtnbsts/workspace1/Lib/astparser_4.3/org.eclipse.core.contenttype_3.4.200.v20120523-2004.jar:/Users/wtnbsts/workspace1/Lib/astparser_4.3/list.sh:"
		// // ,"cgg.model"
		// , "-subpackages", "cgg"//
		// );
		String[] strs = new String[arglist.size()];
		strs = arglist.toArray(strs);
		// com.sun.tools.javadoc.Main.main(strs);
		// com.sun.tools.javadoc.Main.execute(strs);
		PrintWriter out = new PrintWriter(System.out);
		PrintWriter err = new PrintWriter(System.err);
		PrintWriter nul = new PrintWriter(File.createTempFile("nul", "out"));
		// nul = err;
		com.sun.tools.javadoc.Main.execute("javadoc", err, nul, out, "", strs);
		// com.sun.tools.javadoc.Main.
	}

	public Jdoc2Tex() {
	}

	public static boolean start(RootDoc root) {
		String outdir = "out";
		String subdir = outdir + File.separator + "javadoc";
		TexOut texOut = new LongTable();

		write(outdir, "javadoc.tex", texOut.tex(root));

		for (PackageDoc pd : root.specifiedPackages()) {
			write(subdir, pd.name(), texOut.tex(pd));
		}

		ClassDoc[] classes = root.classes();
		for (int i = 0; i < classes.length; i++) {
			String tex = texOut.tex(classes[i]);
			write(subdir, classes[i].qualifiedName(), tex);
		}

		System.out.println("---");
		return true;
	}

	private static void write(String dirpath, String filename, String text) {
		try {
			File dir = new File(dirpath);
			File file = new File(dir, filename);
			dir.mkdirs();

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), "SJIS"));
			bw.write(text);
			bw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
