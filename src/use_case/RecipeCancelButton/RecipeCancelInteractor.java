package use_case.RecipeCancelButton;


public class RecipeCancelInteractor implements RecipeCancelInputBoundary{
    private final RecipeCancelOutputBoundary presenter;


    public RecipeCancelInteractor(RecipeCancelOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        presenter.present();

    }
}