package mlearning.datatools;

import java.util.*;
import java.util.function.Predicate;

/**
 * The DataSeries class extends ArrayList class and represents a series of data points
 * organized into a structured sequence. It may contain values of various data types.
 * The class provides methods for manipulating data points within the series.
 * <p>
 * Features: <br>
 * - Dynamic Structure: DataSeries allows dynamic addition and removal of data points. <br>
 * - Flexible Data Types: Each data point can hold values of different data types. <br>
 * - Descriptive Statistics: Offers various methods that are used in descriptive statistics,
 * because class itself implements an interface 'DescriptiveStatistics'.
 * <p>
 * Usage:
 * - Create an instance of DataSeries and add data points using unique identifiers.
 * - Retrieve data points by identifier or iterate over the series.
 * - Manipulate the series by adding, removing, or updating data points.
 * <p>
 * Note: This class does not guarantee thread-safety and should be used in a single-threaded environment
 * or with appropriate synchronization mechanisms in a multithreaded environment.
 * */
public class DataSeries extends ArrayList<Object> implements DescriptiveStatistics {

    /**
     * Represents the label associated with the DataSeries.
     * The label provides a descriptive identifier for the DataSeries.
     * It is commonly used to give context or a name to the set of data.
     */
    private String label;


    /**
     * Constructs an empty DataSeries with an empty label.
     */
    public DataSeries() {
        this.label = "";
    }


    /**
     * Constructs a DataSeries with a specified label and no initial elements.
     *
     * @param label The label for the DataSeries.
     */
    public DataSeries(String label) {
        this.label = label;
    }


    /**
     * Constructs a DataSeries with an empty label and initializes it with elements from an array.
     *
     * @param array An array of objects to initialize the DataSeries.
     */
    public DataSeries(Object[] array) {
        this.addAll(Arrays.asList(array));
        this.label = "";
    }


    /**
     * Constructs a DataSeries with a specified label and initializes it with elements from an array.
     *
     * @param label The label for the DataSeries.
     * @param array An array of objects to initialize the DataSeries.
     */
    public DataSeries(String label, Object[] array) {
        this.label = label;
        this.addAll(Arrays.asList(array));
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof DataSeries) {
            for (Object element : this) {
                if (!((DataSeries) o).contains(element)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    /**
     * Gets the label of the DataSeries.
     *
     * @return The label of the DataSeries.
     */
    public String getLabel() {
        return this.label;
    }


    /**
     * Sets the label for the DataSeries.
     *
     * @param label The new label for the DataSeries.
     */
    public void setLabel(String label) {
        this.label = label;
    }


    /**
     * Checks if all elements in the DataSeries are categorical (String or Boolean).
     *
     * @return True if all elements are categorical, false otherwise.
     */
    @Override
    public boolean isCategorical() {
        for (Object val : this) {
            if (!(val instanceof String || val instanceof Boolean)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Checks if all elements in the DataSeries are numerical (Integer or Double).
     *
     * @return True if all elements are numerical, false otherwise.
     */
    @Override
    public boolean isNumerical() {
        for (Object val : this) {
            if (!(val instanceof Integer || val instanceof Double)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Calculates the sum of the numeric values in the DataSeries.
     *
     * @return The sum of the numeric values in the DataSeries.
     */
    @Override
    public double sum() {
        double totalSum = 0;

        // Iterate through the elements and add their numeric representations to the sum
        for (Object element : this) {
            totalSum += DataOps.toDouble(element);
        }

        return totalSum;
    }


    /**
     * Calculates the mean (average) of the values in the DataSeries.
     *
     * @return The mean of the values in the DataSeries.
     * @throws ArithmeticException If the DataSeries is empty, resulting in division by zero.
     */
    @Override
    public double mean() throws ArithmeticException {
        if (isEmpty()) {
            throw new ArithmeticException("Cannot calculate mean of an empty DataSeries.");
        }

        return this.sum() / this.count();
    }


    /**
     * Calculates the median of the values in the DataSeries.
     * If the DataSeries has an odd number of elements, returns the middle value.
     * If the DataSeries has an even number of elements, returns the average of the two middle values.
     *
     * @return The median of the values in the DataSeries.
     * @throws ArithmeticException If the DataSeries is empty.
     */
    @Override
    public double median() throws ArithmeticException {
        if (isEmpty()) {
            throw new ArithmeticException("Cannot find median in an empty DataSeries.");
        }

        ArrayList<Double> doubles = new ArrayList<>(size());
        for (Object element : this) {
            doubles.add(DataOps.toDouble(element));
        }

        Collections.sort(doubles);

        int size = doubles.size();
        int middle = size / 2;

        if (size % 2 != 0) {
            // Odd number of elements
            return doubles.get(middle);
        } else {
            // Even number of elements
            return (doubles.get(middle) + doubles.get(middle - 1)) / 2.0;
        }
    }


    /**
     * Finds the mode (most frequent value) in the DataSeries.
     * If there are multiple modes, returns the first encountered mode.
     *
     * @return The mode of the values in the DataSeries.
     * @throws ArithmeticException If the DataSeries is empty.
     */
    @Override
    public double mode() throws NoSuchElementException {
        if (isEmpty()) {
            throw new ArithmeticException("Cannot find mode in an empty DataSeries.");
        }

        HashMap<Double, Integer> counter = this.frequencies();

        int maxFrequency = 0;
        Double mode = null;

        for (Double key : counter.keySet()) {
            int frequency = counter.get(key);

            if (frequency > maxFrequency) {
                mode = key;
                maxFrequency = frequency;
            }
        }

        if (mode == null) {
            throw new NoSuchElementException("No mode found in the DataSeries.");
        }

        return mode;
    }


    /**
     * Finds the minimum value in the DataSeries.
     *
     * @return The minimum value in the DataSeries.
     * @throws ArithmeticException If the DataSeries is empty.
     */
    @Override
    public double min() throws ArithmeticException {
        double minVal = Double.MAX_VALUE;
        for (Object elem : this) {
            double d = DataOps.toDouble(elem);
            if (d < minVal) {
                minVal = d;
            }
        }
        return minVal;
    }


    /**
     * Finds the maximum value in the DataSeries.
     *
     * @return The maximum value in the DataSeries.
     * @throws ArithmeticException If the DataSeries is empty.
     */
    @Override
    public double max() throws ArithmeticException {
        double maxValue = Double.MIN_VALUE;
        for (Object elem : this) {
            double d = DataOps.toDouble(elem);
            if (d > maxValue) {
                maxValue = d;
            }
        }
        return maxValue;
    }


    /**
     * Calculates the range of values in the DataSeries.
     * The range is the difference between the maximum and minimum values.
     *
     * @return The range of values in the DataSeries.
     */
    @Override
    public double range() throws ArithmeticException{
        double max = this.max();
        double min = this.min();

        if (min == 0 || max == 0) {
            throw new ArithmeticException("Insufficient data for calculation!");
        }

        return Math.abs(max - min);
    }

    /**
     * Returns the count of data points in the DataSeries.
     * The count is equivalent to the size of the DataSeries,
     * indicating the total number of elements it contains.
     *
     * @return The count of data points in the DataSeries.
     */
    @Override
    public int count() {
        return this.size();
    }


    /**
     * Calculates the first quartile (Q1) of the values in the DataSeries.
     * The first quartile is the value below which approximately 25% of the data falls.
     * If the DataSeries has an even number of elements, the first quartile is the average
     * of the values at the positions closest to the 25% and 26% marks.
     *
     * @return The first quartile (Q1) of the values in the DataSeries.
     * @throws ArithmeticException If the DataSeries is empty, indicating insufficient data for calculation.
     */
    @Override
    public double firstQuartile() throws ArithmeticException {
        ArrayList<Double> doubles = new ArrayList<>();

        // Convert DataSeries elements to Doubles and store in an ArrayList
        for (Object element : this) {
            doubles.add(DataOps.toDouble(element));
        }

        // Sort the ArrayList in ascending order
        Collections.sort(doubles);

        // Calculate the first quartile based on the size of the ArrayList
        int size = doubles.size();
        if (size == 0) {
            throw new ArithmeticException("Insufficient data for first quartile calculation. DataSeries is empty.");
        }

        if (size % 2 == 0) {
            // If even, calculate the average of values at positions closest to the 25% and 26% marks
            int index1 = size / 4;
            int index2 = index1 + 1;
            return (doubles.get(index1 - 1) + doubles.get(index2 - 1)) / 2;
        } else {
            // If odd, directly return the value at the position closest to the 25% mark
            int index = size / 4;
            return doubles.get(index);
        }
    }


    /**
     * Calculates the third quartile (Q3) of the values in the DataSeries.
     * The third quartile is the value below which approximately 75% of the data falls.
     * If the DataSeries has an even number of elements, the third quartile is the average
     * of the values at the positions closest to the 75% and 76% marks.
     *
     * @return The third quartile (Q3) of the values in the DataSeries.
     * @throws ArithmeticException If the DataSeries is empty, indicating insufficient data for calculation.
     */
    @Override
    public double thirdQuartile() throws ArithmeticException {
        ArrayList<Double> doubles = new ArrayList<>();

        // Convert DataSeries elements to Doubles and store in an ArrayList
        for (Object element : this) {
            doubles.add(DataOps.toDouble(element));
        }

        // Sort the ArrayList in ascending order
        Collections.sort(doubles);

        // Calculate the third quartile based on the size of the ArrayList
        int size = doubles.size();
        if (size == 0) {
            throw new ArithmeticException("Insufficient data for third quartile calculation. DataSeries is empty.");
        }

        if (size % 2 == 0) {
            // If even, calculate the average of values at positions closest to the 75% and 76% marks
            int index1 = size * 3 / 4;
            int index2 = index1 + 1;
            return (doubles.get(index1 - 1) + doubles.get(index2 - 1)) / 2;
        } else {
            // If odd, directly return the value at the position closest to the 75% mark
            int index = size * 3 / 4;
            return doubles.get(index);
        }
    }


    /**
     * Calculates the variance of the values in the DataSeries.
     * Variance is a measure of the spread or dispersion of a set of values.
     * It is computed as the average of the squared differences between each value
     * and the mean of the DataSeries.
     *
     * @return The variance of the values in the DataSeries.
     * @throws ArithmeticException If the count of values in the DataSeries is zero,
     *                             indicating insufficient data for variance calculation.
     */
    @Override
    public double variance() throws ArithmeticException {
        double sum = 0;
        double mean = this.mean();

        // Calculate variance using the formula: var(X) = Σ (Xᵢ - mean(X))² / N
        for (Object element : this) {
            double value = DataOps.toDouble(element);
            sum += Math.pow(value - mean, 2);
        }

        // Normalize the sum by dividing by the count of values
        if (this.count() == 0) {
            throw new ArithmeticException("Insufficient data for variance calculation. DataSeries is empty.");
        }

        return sum / this.count();
    }


    /**
     * Calculates the standard deviation of the values in the DataSeries.
     * The standard deviation is a measure of the amount of variation or dispersion in a set of values.
     * It is computed as the square root of the variance of the DataSeries.
     *
     * @return The standard deviation of the values in the DataSeries.
     * @throws ArithmeticException If the variance calculation results in a negative value,
     *                             indicating an invalid input or insufficient data for calculation.
     */
    @Override
    public double standardDeviation() throws ArithmeticException {
        return Math.sqrt(this.variance());
    }


    /**
     * Computes the frequencies of unique values in the DataSeries.
     * Generates a HashMap where keys are unique values from the DataSeries,
     * and values represent the frequency (count) of each unique value in the DataSeries.
     *
     * @return A HashMap containing unique values from the DataSeries as keys
     *         and their corresponding frequencies as values.
     */
    @Override
    public HashMap<Double, Integer> frequencies() {
        HashMap<Double, Integer> counter = new HashMap<>();

        // Count the frequencies of unique values in the DataSeries
        for (Object element : this) {
            Double value = DataOps.toDouble(element);
            if (!counter.containsKey(value)) {
                counter.put(value, 1);
            } else {
                int currentFrequency = counter.get(value);
                counter.replace(value, currentFrequency + 1);
            }
        }

        return counter;
    }


    /**
     * Calculates the covariance between this DataSeries and another DataSeries.
     * Covariance measures how much two sets of data vary together.
     * A positive covariance indicates a positive linear relationship,
     * while a negative covariance indicates a negative linear relationship.
     * Covariance is sensitive to the scales of the variables.
     *
     * @param other Another DataSeries to calculate the covariance with.
     * @return The covariance between this DataSeries and the provided DataSeries.
     */
    @Override
    public double covariance(DataSeries other) {
        double sum = 0;
        double meanThis = this.mean();

        // Calculate covariance using the formula: cov(X, Y) = Σ [(Xᵢ - mean(X)) * (Yᵢ - mean(Y))]
        for (int i = 0; i < this.count(); i++) {
            double valueThis = DataOps.toDouble(this.get(i));
            double valueOther = DataOps.toDouble(other.get(i));
            sum += ((valueThis - meanThis) * (valueOther - meanThis));
        }

        // Normalize the sum by dividing by the number of data points
        return sum / this.count();
    }


    /**
     * Calculates the Pearson correlation coefficient between this DataSeries and another DataSeries.
     * The correlation coefficient measures the linear relationship between two sets of data.
     * It is normalized to a scale of [-1, 1], where 1 indicates a perfect positive linear correlation,
     * -1 indicates a perfect negative linear correlation, and 0 indicates no linear correlation.
     *
     * @param other Another DataSeries to calculate the correlation with.
     * @return The Pearson correlation coefficient between this DataSeries and the provided DataSeries.
     */
    @Override
    public double correlation(DataSeries other) {
        return this.covariance(other) / (this.standardDeviation() * other.standardDeviation());
    }

    /**
     * Codes values in the DataSeries based on a provided mapping.
     * Replaces each data point in the DataSeries with its corresponding code
     * obtained from the given mapping HashMap. The mapping associates original
     * values with their corresponding codes.
     *
     * @param map A HashMap representing the mapping of original values to their codes.
     *            Each key in the map corresponds to a unique original value,
     *            and the associated value is the code to replace it with.
     */
    public void codeValues(HashMap<Object, Object> map) {

        /*for (int i = 0; i < this.size(); i++) {
            Object data = this.get(i);
            double mapValue = map.get(data);
            this.set(i, mapValue);
        }*/

        this.replaceAll(map::get);
    }


    /**
     * Creates a new DataSeries containing unique values from the original DataSeries.
     * Iterates through the original DataSeries and adds each unique value to the new DataSeries.
     * The method ensures that no duplicate values are included in the resulting DataSeries.
     *
     * @return A new DataSeries containing unique values from the original DataSeries.
     */
    public DataSeries unique() {

        DataSeries unq = new DataSeries(this.label);
        for (Object o: this) {
            if (!unq.contains(o)) {
                unq.add(o);
            }
        }
        return unq;
    }


    /**
     * Finds indices of data points in the DataSeries where a specified predicate is true.
     * Iterates through the DataSeries and applies the given predicate to each data point.
     * Returns an array of Integer indices corresponding to the positions where the predicate is true.
     *
     * @param predicate A predicate that determines whether a data point meets a certain condition.
     * @return An array of Integer indices representing positions in the DataSeries where the predicate is true.
     *         If no matching data points are found, an empty array is returned.
     */
    public Integer[] indicesWhere(Predicate<Object> predicate) {

        List<Integer> indices = new ArrayList<>();

        // Iterate through the DataSeries and apply the predicate.
        for (int i = 0; i < this.count(); i++) {
            if (predicate.test(this.get(i))) {
                indices.add(i);
            }
        }

        return indices.toArray(new Integer[0]);
    }


    /**
     * Generates an auto-coding mapping for unique values in the DataSeries.
     * Assigns a unique code to each distinct value present in the DataSeries.
     * The method returns a HashMap where keys are unique values, and values are
     * corresponding auto-generated codes starting from 0.
     *
     * @return A HashMap representing the auto-coding mapping for unique values in the DataSeries.
     *         Each unique value is associated with a unique code starting from 0.
     */
    public HashMap<Object, Object> autoCoding() {
        // Starting code.
        int code = 0;
        HashMap<Object, Object> map = new HashMap<>();

        // Obtain unique values from the DataSeries
        for (Object value : this.unique()) {
            map.put(value, code);
            code++;
        }

        return map;
    }


    /**
     * Retrieves a new DataSeries containing data points at specified indices from the current DataSeries.
     * The method creates a new DataSeries instance with the same label as the original series and populates it
     * with data points retrieved from the provided indices.
     *
     * @param indices An array of Integer values representing the indices of the data points to be retrieved.
     * @return A new DataSeries containing the selected data points. If an index is out of bounds,
     *         the corresponding entry in the result will be null.
     * @throws IndexOutOfBoundsException If any index in the 'indices' array is less than 0 or greater than
     *                                   or equal to the size of the original DataSeries.
     */
    public DataSeries getAll(Integer[] indices) throws IndexOutOfBoundsException{

        DataSeries series = new DataSeries(this.label);

        for (int index: indices) {
            series.add(this.get(index));
        }

        return series;
    }


    /**
     * Returns collection sorted. <br>
     * Note: Only works for the numeric values.
     * */
    public DataSeries sorted() {
        DataSeries n = new DataSeries();
        n.addAll(this);
        n.sort(Comparator.comparingDouble(DataOps::toDouble));
        return n;
    }
}
