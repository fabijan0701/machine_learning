package mlearning.datatools;

import java.util.HashMap;

public interface DescriptiveStatistics {

    /**
     * Checks if all elements in the given data set are of categorical type.
     * Returns true if they are, otherwise returns false.
     */
    boolean isCategorical();

    /**
     * Checks if all elements in the given data set are of numerical type.
     * Returns true if they are, otherwise returns false.
     */
    boolean isNumerical();

    /**
     * Calculates the sum of all elements in the given data set.
     * @throws ArithmeticException if not all elements in the set are numerical data
     */
    double sum() throws ArithmeticException;

    /**
     * Calculates the mean (average) of all elements in the given data set.
     * @throws ArithmeticException if not all elements in the set are numerical data
     */
    double mean() throws ArithmeticException;

    /**
     * Calculates the median of all elements in the given data set.
     * @throws ArithmeticException if not all elements in the set are numerical data
     */
    double median() throws ArithmeticException;

    /**
     * Calculates the mode of all elements in the given data set.
     * @throws ArithmeticException if not all elements in the set are numerical data
     */
    double mode() throws ArithmeticException;

    /**
     * Finds the minimum element in the given data set.
     * @throws ArithmeticException if not all elements in the set are numerical data
     */
    double min() throws ArithmeticException;

    /**
     * Finds the maximum element in the given data set.
     * @throws ArithmeticException if not all elements in the set are numerical data
     */
    double max() throws ArithmeticException;

    /**
     * Calculates the range of the data set.
     * @throws ArithmeticException if not all elements in the set are numerical data
     */
    double range() throws ArithmeticException;

    /**
     * Returns the number of elements in the given data set.
     * @throws ArithmeticException if not all elements in the set are numerical data
     */
    int count() throws ArithmeticException;

    /**
     * Calculates the first quartile value of the data set.
     * @throws ArithmeticException if not all elements in the set are numerical data
     */
    double firstQuartile() throws ArithmeticException;

    /**
     * Calculates the third quartile value of the data set.
     * @throws ArithmeticException if not all elements in the set are numerical data
     */
    double thirdQuartile() throws ArithmeticException;

    /**
     * Calculates the variance of the given data set.
     * @throws ArithmeticException if not all elements in the set are numerical data
     */
    double variance() throws ArithmeticException;

    /**
     * Calculates the standard deviation of the given data set.
     * @throws ArithmeticException if not all elements in the set are numerical data
     */
    double standardDeviation() throws ArithmeticException;

    /**
     * Returns the frequencies of each element in the data set.
     * Works only on numerical data.
     */
    HashMap<Double, Integer> frequencies();

    /**
     * Calculates the covariance with another data set.
     */
    double covariance(DataSeries other);

    /**
     * Calculates the correlation with another data set.
     */
    double correlation(DataSeries other);
}
