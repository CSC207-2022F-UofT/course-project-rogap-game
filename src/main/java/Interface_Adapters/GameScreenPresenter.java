package Interface_Adapters;

public class GameScreenPresenter {
    UpdateScreenModel screenModel;
    //TODO: This will call Repaint method

    public GameScreenPresenter(UpdateScreenModel screenModel){

        this.screenModel = screenModel;
    }

    public UpdateScreenModel create(){
        return this.screenModel;
    }
    public void update(){
        screenModel.update();
    }




}