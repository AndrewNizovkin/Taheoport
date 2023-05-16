package taheoport;

/**
 * This class encapsulates params of station, extracted from measurements
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class ExtractStation {
    private String name = "Not";
    private String horBack = "0.0000";
    private String horForward = "0.0000";
    private String lineBack = "0.000";
    private String lineForward = "0.000";
    private String dZBack = "0.000";
    private String dZForward = "0.000";

// Setters_____________________________________

    public void setName(String name) {
        this.name = name;
    }

    public void setHorBack(String horBack) {
        this.horBack = horBack;
    }

    public void setHorForward(String horForward) {
        this.horForward = horForward;
    }

    public void setLineBack(String lineBack) {
        this.lineBack = lineBack;
    }

    public void setLineForward(String lineForward) {
        this.lineForward = lineForward;
    }

    public void setdZBack(String dZBack) {
        this.dZBack = dZBack;
    }

    public void setdZForward(String dZForward) {
        this.dZForward = dZForward;
    }

// Getters___________________________________________


    public String getName() {
        return name;
    }

    public String getLineBack() {
        return lineBack;
    }

    public String getLineForward() {
        return lineForward;
    }

    public String getdZBack() {
        return dZBack;
    }

    public String getdZForward() {
        return dZForward;
    }

    /**
     * returns the average of the lineForward and lineBack
     * @return String
     */
    public String getLineTrue() {
        return new DataHandler((Double.parseDouble(lineBack) + Double.parseDouble(lineForward)) / 2).format(3).getStr();
    }

    /**
     * returns the horizontal angle between horForward and horBack
     * @return String horizontal angle between rear and front direction
     */
    public String getHorTrue() {
        double horTrue;
        horTrue = new DataHandler(horForward).dmsToDeg() - new DataHandler(horBack).dmsToDeg();
        while (horTrue < 0) {
            horTrue += 360;
        }
        return new DataHandler().degToDms(horTrue).getStr();
    }

    /**
     * returns the average of the dZForward and dZBack
     * @return String
     */
    public String getDZTrue() {
        return new DataHandler((Double.parseDouble(dZForward) - Double.parseDouble(dZBack)) / 2).format(3).getStr();
    }

    /**
     * returns the difference between lineForward and lineBack in mm.
     * @return String
     */
    public String getDLine() {
        return new DataHandler((Double.parseDouble(lineForward) - Double.parseDouble(lineBack)) * 1000).format(0).getStr();
    }

    /**
     * returns the difference between dZForward and dZBack in mm.
     * @return string DDZ
     */
    public String getDDZ() {
        return new DataHandler((Double.parseDouble(dZForward) + Double.parseDouble(dZBack)) * 1000).format(0).getStr();
    }
}
