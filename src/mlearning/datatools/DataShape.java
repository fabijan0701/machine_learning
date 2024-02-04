package mlearning.datatools;

/**
 * A record representing the shape of a data structure.
 * It includes the number of rows and columns in the data.
 *
 * @param rows    The number of rows in the data structure.
 * @param columns The number of columns in the data structure.
 */
public record DataShape(int rows, int columns) {
}