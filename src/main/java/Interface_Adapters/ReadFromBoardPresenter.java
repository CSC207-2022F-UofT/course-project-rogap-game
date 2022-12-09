package Interface_Adapters;

import Use_Cases.ReadFromBoardOutputBoundary;
import Use_Cases.ReadFromBoardResponseModel;

public class ReadFromBoardPresenter {
    final ReadFromBoardOutputBoundary readFromBoardInteractor;

    public ReadFromBoardPresenter(ReadFromBoardOutputBoundary readFromBoardInteractor){
        this.readFromBoardInteractor = readFromBoardInteractor;
    }


    // In the presenter class cosntructor, we pass create the interactor object but its go to be of type Input Boundary.
    // We also write methods that call methods in the interactor class and pass in the output data object through the
    // through these methods.
    public String readFromDatabase() {
        this.readFromBoardInteractor.readFromDatabase(new ReadFromBoardResponseModel());
    }
}
