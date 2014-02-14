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

/**
 * javadocをTeX形式で出力する為のカスタムDoclet<br>
 * 普通のカスタムDocletのように使えます．<br>
 * 例) java -doclet {@link Jdoc2Tex} -docletpath jdoc2tex.jar -sourcepath src
 * -subpackages targetpackage<br>
 * <br>
 * また，また， {@link Jdoc2Tex} クラスは，mainメソッドを持つため，以下の様にも使えます．<br>
 * 例) java -classpath jdoc2tex.jar -sourcepath src -subpackages targetpackage<br>
 * <br>
 * 
 * @author satoshi
 */
public class Jdoc2Tex {

	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		List<String> arglist = new ArrayList<>();
		arglist.add("-doclet");
		arglist.add(Jdoc2Tex.class.getCanonicalName());
		arglist.addAll(Arrays.asList(args));
		String[] strs = new String[arglist.size()];
		strs = arglist.toArray(strs);
		PrintWriter out = new PrintWriter(System.out);
		PrintWriter err = new PrintWriter(System.err);
		PrintWriter nul = new PrintWriter(File.createTempFile("nul", "out"));
		// nul = err;
		com.sun.tools.javadoc.Main.execute("javadoc", err, nul, out, "", strs);
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
