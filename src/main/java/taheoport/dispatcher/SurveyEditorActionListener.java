package taheoport.dispatcher;

import taheoport.gui.SurveyEditorRenderer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SurveyEditorActionListener implements ActionListener {
    private final SurveyEditorRenderer renderer;

    /**
     * Constructor
     * @param surveyEditorRenderer SurveyEditorRenderer
     */
    public SurveyEditorActionListener(SurveyEditorRenderer surveyEditorRenderer) {
        renderer = surveyEditorRenderer;
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
