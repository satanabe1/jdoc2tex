package jdoc2tex.interpreter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractDocConverter implements IDocConverter {

	protected String vspace() {
		StringBuilder sb = new StringBuilder();
		sb.append("\\vspace{-2zw}").append("\n");
		return sb.toString();
	}

//	protected String shortstack(String plain) {
//		String ss = plain.toString();
//		// {@link Hogehoge} を Hogehoge だけにする
//		ss = ss.replaceAll("\\{@link (.*?)\\}", "$1");
//		// <br> を \\ に置換
//		ss = ss.replaceAll("<\\s*?[Bb][Rr].*?>", " \\\\\\\\");
//		ss = escape(ss);
//
//		ss = ss.replaceAll("\r\n", "").replaceAll("\r", "")
//				.replaceAll("\n", "");
//
//		StringBuilder sb = new StringBuilder();
//		sb.append("\\shortstack[l]{");
//		sb.append(ss);
//		// sb.append("\\smallskip}");
//		sb.append("{}}");
//		return sb.toString();
//	}

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

	protected String par(String plain) {
		String ss = plain.toString();
		// {@link Hogehoge} を Hogehoge だけにする
		ss = ss.replaceAll("\\{@link (.*?)\\}", "$1");
		// <br> を \\ に置換
		ss = ss.replaceAll("<\\s*?[Bb][Rr].*?>", " \\\\par");
		ss = escape(ss);
		return ss.toString();
	}
}
