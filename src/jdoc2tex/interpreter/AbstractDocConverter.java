package jdoc2tex.interpreter;

public abstract class AbstractDocConverter implements IDocConverter {

	protected String vspace() {
		StringBuilder sb = new StringBuilder();
		sb.append("\\vspace{-2zw}").append("\n");
		return sb.toString();
	}

	// protected String shortstack(String plain) {
	// String ss = plain.toString();
	// // {@link Hogehoge} を Hogehoge だけにする
	// ss = ss.replaceAll("\\{@link (.*?)\\}", "$1");
	// // <br> を \\ に置換
	// ss = ss.replaceAll("<\\s*?[Bb][Rr].*?>", " \\\\\\\\");
	// ss = escape(ss);
	//
	// ss = ss.replaceAll("\r\n", "").replaceAll("\r", "")
	// .replaceAll("\n", "");
	//
	// StringBuilder sb = new StringBuilder();
	// sb.append("\\shortstack[l]{");
	// sb.append(ss);
	// // sb.append("\\smallskip}");
	// sb.append("{}}");
	// return sb.toString();
	// }

	protected String escape(String plain) {
		String ss = plain.toString();
		// {}_$#%&^
		// { を \{ に置換
		ss = replaceAll("\\{", "\\\\{", ss);
		// } を \} に置換
		ss = replaceAll("\\}", "\\\\}", ss);
		// _ を \_ に置換
		ss = replaceAll("_", "\\\\_", ss);
		// $ を \$ に置換
		ss = replaceAll("\\$", "\\\\\\$", ss);
		// # を \# に置換
		ss = replaceAll("#", "\\\\#", ss);
		// % を に置換
		ss = replaceAll("%", "\\\\%", ss);
		// & を に置換
		ss = replaceAll("&", "\\\\&", ss);
		// ^ を に置換
		ss = replaceAll("\\^", "\\\\^", ss);
		// < を に置換
		ss = replaceAll("<", "{\\\\textless}", ss);
		// > を に置換
		ss = replaceAll(">", "{\\\\textgreater}", ss);
		// | を に置換
		ss = replaceAll("\\|", "{\\\\textbar}", ss);
		return ss;
	}

	protected String par(String plain) {
		String ss = plain.toString();
		// {@link Hogehoge} を Hogehoge だけにする
		ss = ss.replaceAll("\\{@link (.*?)\\}", "$1");
		// <br> を \\ に置換
		ss = ss.replaceAll("<\\s*?[Bb][Rr].*?>", " \\\\par");
		// // \n を に置換
		// ss = ss.replaceAll("\\n", "");
		ss = escape(ss);
		return ss.toString();
	}

	private String replaceAll(String regex, String replacement, String text) {
		String ss = text.toString();
		try {
			return ss.replaceAll(regex, replacement);
		} catch (Exception ex) {
			System.err.println("REGEX:" + regex);
			System.err.println("REPLACEMENT:" + replacement);
			System.err.println(text);
			ex.printStackTrace();
		}
		return ss;
	}
}
