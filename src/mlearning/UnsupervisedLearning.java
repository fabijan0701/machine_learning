package mlearning;

import mlearning.datatools.DataSeries;
import mlearning.datatools.DataSet;

/**
 * An interface for unsupervised learning algorithms.
 * Unsupervised learning involves training on an unlabeled dataset.
 */
public interface UnsupervisedLearning {

    /**
     * Trains the model using an unlabeled dataset.
     *
     * @param X The feature dataset.
     */
    void train(DataSet X);

    /**
     * Predicts a single output based on the trained model.
     * This method is used in unsupervised learning scenarios.
     *
     * @param y The input features for prediction.
     * @return The predicted output.
     */
    double predictOne(DataSeries y);

    /**
     * Predicts output values for a given feature dataset.
     * This method is used in unsupervised learning scenarios.
     *
     * @param X The feature dataset for prediction.
     * @return A DataSeries containing the predicted output values.
     */
    DataSeries predict(DataSet X);
}
