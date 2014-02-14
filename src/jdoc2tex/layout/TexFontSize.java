package jdoc2tex.layout;

public enum TexFontSize {

	TINY("\\tiny"), //
	FOOTNOTESIZE("\\footnotesize"), //
	SCRIPTSIZE("\\scriptsize"), //
	SMALL("\\small"), //
	NORMALSIZE("\\nomalsize"), //
	LARGE1("\\large"), //
	LARGE2("\\Large"), //
	LARGE3("\\LARGE"), //
	HUGE1("\\huge"), //
	HUGE2("\\Huge"), //
	HUGE3("\\HUGE");//

	private String value;

	private TexFontSize(String val) {
		value = val;
	}

	public String toString() {
		return value;
	}
}
