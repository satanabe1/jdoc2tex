package jdoc2tex.layout;

public interface ITableManager {

	

	boolean addRow(String row);

	boolean addRow(String row, String size);

	String generateTable();
}
