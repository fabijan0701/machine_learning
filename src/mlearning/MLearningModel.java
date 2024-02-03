package mlearning;

/**
 * Interface for any machine learning model from this package.
 * Contains two abstract methods for training model and for testing model.
 * Each machine learning model that implements this interface implements
 * this two methods.
 * */
public interface MLearningModel {
    /** Method that trains model on given DataSet.*/
    void train();
    /*Predicts y values using the given arguments. */
    double predict();
}
