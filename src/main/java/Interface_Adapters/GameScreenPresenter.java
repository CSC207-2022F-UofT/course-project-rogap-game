package Interface_Adapters;

import Frameworks.UpdateScreenModel;

public class GameScreenPresenter {
    UpdateScreenModel screenModel;
    //TODO: This will call Repaint method

    public GameScreenPresenter(UpdateScreenModel screenModel){
        this.screenModel = screenModel;
    }

    void update(){
        screenModel.update();
    }




}