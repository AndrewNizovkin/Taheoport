package taheoport.dispatcher;

import taheoport.gui.MainWin;
import taheoport.gui.ShowCatalog;
import taheoport.model.SurveyStation;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SetCoordinates extends AbstractAction {

    private final String name;
    private MainWin parentFrame;

    public SetCoordinates(MainWin frame, String name) {
        super();
        this.name = name;
        parentFrame = frame;
        setEnabled(parentFrame.hasCatalog());
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (parentFrame.hasCatalog()) {
//            new ShowCatalog(parentFrame, index, name);
//            SurveyStation surveyStation= parentFrame.getSurveyService().getSurveyRepository().findById(index);
//            parentFrame.updateStation(surveyStation);

        }
    }
}
