package Use_Cases;

public class ReadFromBoardInteractor implements ReadFromBoardOutputBoundary{

    ReadFromBoardGatewayBoundary readFromBoardAccess;
    public ReadFromBoardInteractor(ReadFromBoardGatewayBoundary readFromBoardAccess) {
        this.readFromBoardAccess = readFromBoardAccess;
    }

    @Override
    public String readFromDatabase() {
        return readFromBoardAccess.readFromDatabase();
    }
}
