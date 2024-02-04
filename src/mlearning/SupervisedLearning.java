package mlearning;

import mlearning.datatools.DataSeries;
import mlearning.datatools.DataSet;


/**
 * An interface for supervised learning algorithms.
 * Supervised learning involves training on a labeled dataset.
 */
public interface SupervisedLearning {

    /**
     * Trains the model using a labeled dataset.
     *
     * @param X The feature dataset.
     * @param y The target variable dataset.
     */
    void train(DataSet X, DataSeries y);

    /**
     * Predicts a single output based on the trained model.
     *
     * @param y The input features for prediction.
     * @return The predicted output.
     */
    double predictOne(DataSeries y);

    /**
     * Predicts output values for a given feature dataset.
     *
     * @param X The feature dataset for prediction.
     * @return A DataSeries containing the predicted output values.
     */
    DataSeries predict(DataSet X);
}

