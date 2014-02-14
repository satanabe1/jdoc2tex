package jdoc2tex.layout;

public interface ITableManager {

	boolean addRow(String... row);

	boolean addRow(TexFontSize size, String... row);

	String generateTable();
}
