    package taheoport;

    import javax.swing.*;
    import java.util.HashMap;
    import java.util.LinkedList;

    /**
     * this class encapsulates data in a list of stations llTeoStation and
     * provides methods for editing, adjustment
     * @author Andrey Nizovkin
     * Copyright Nizovkin A.V. 2022
     */
    public class PolygonProject {
    private final MainWin parentFrame;
    private String absolutePolPath = "";
    private BindType bindType = BindType.ZZ;
    private double fHor = 0.0;
    private double fX = 0.0;
    private double fY = 0.0;
    private double fZ = 0.0;
    private double fAbs = 0.0;
    private String fOtn = "Not";
    private double perimeter = 0.0;
    private final LinkedList<PolygonStation> llTheoStatons = new LinkedList<PolygonStation>();
    public enum BindType {ZZ,TT, TO, OT, OO, TZ, ZT}

        /**
         * Constructor
         * @param frame
         */
    public PolygonProject(MainWin frame) {
        parentFrame = frame;
    }

        /**
         * sets absolutePolPath
         * @param absolutePolPath
         */
        public void setAbsolutePolPath(String absolutePolPath) {
            this.absolutePolPath = absolutePolPath;
        }

        /**
         * return absolutePolPath
         * @return String absolutePolPath
         */
    public String getAbsolutePolPath() {
        return absolutePolPath;
    }

        /**
         * return TheoStation from llTeoStations[idx]
         * @param idx int index
         * @return
         */
        public PolygonStation getTheoStation(int idx) {
        return llTheoStatons.get(idx);
        }

        /**
         * return size of llStations
         * @return int size
         */
        public int getSizeTheoStations() {
            return llTheoStatons.size();
        }

        /**
         * get perimeter
         * @return String perimeter
         */
        public double getPerimeter() {
            return perimeter;
        }

        /**
         * get fHor
         * @return String fHor
         */
        public double getfHor() {
            return fHor;
        }

        public double getfX() {
            return fX;
        }

        public double getfY() {
            return fY;
        }

        public double getfZ() {
            return fZ;
        }

        public double getfAbs() {
            return fAbs;
        }

        public String getfOtn() {
            return fOtn;
        }

        public BindType getBindType() {
            return bindType;
        }

        /**
         * remove Station from llTheoStations
         * @param idx int index removed element
         */
        public void removeStation(int idx) {
            llTheoStatons.remove(idx);
        }

        /**
         * adds EMPTY Station at idx position
         * @param idx int idx
         */
        public void addStation(int idx) {
            llTheoStatons.add(idx, new PolygonStation());
        }

        /**
         * adds theoStation to llTheoStations
         * @param theoStation
         */
        public void addStation(PolygonStation theoStation) {
            llTheoStatons.add(theoStation);
        }
        /**
         * Load llTheoStaions from LinkedList<String> llPolList
         * @param llPolList
         * @return
         */
    public PolygonProject loadPolList(LinkedList<String> llPolList) {
        String sep = " ";
        String str = "";
        String[] array;
        if (llPolList == null) {
            return null;
        }

//        LinkedList<String> l = new MyChooser().readTextFile(pathWorkDir, "pol", "Выберите файл *.pol");
//        if (llPolList != null) {
            absolutePolPath = llPolList.removeFirst();
            while (llPolList.size() > 0) {
                str = new DataHandler(llPolList.removeFirst()).compress(sep).getStr();
                array = str.split(sep);
                if (array.length == 7) {
                    PolygonStation ts = new PolygonStation();
                    ts.setName(array[0]);
                    ts.setHor(array[1]);
                    ts.setLine(array[2]);
                    ts.setdZ(array[3]);
                    ts.setX(array[4]);
                    ts.setY(array[5]);
                    ts.setZ(array[6]);
                    if (new DataHandler(ts.getX()).isNumber() & new DataHandler(ts.getY()).isNumber() & new DataHandler(ts.getZ()).isNumber()) {
                        ts.setStatus(true);
                    }
                    llTheoStatons.add(ts);
                }

            }
//        }
        return this;
    }

        /**
         * get list for writing to file current TheoProject
         * @return LinkedList
         */
    public LinkedList<String> getPolList() {
        String sep = " ";
        String s = "";
        LinkedList <String> llPol = new LinkedList<String>();
        for (PolygonStation llTheoStaton : llTheoStatons) {
            s = llTheoStaton.getName() + sep +
                    llTheoStaton.getHor() + sep +
                    llTheoStaton.getLine() + sep +
                    llTheoStaton.getDZ() + sep;
            if (llTheoStaton.getStatus()) {
                s += llTheoStaton.getX() + sep +
                        llTheoStaton.getY() + sep +
                        llTheoStaton.getZ();
            } else {
                s += "Not" + sep +
                        "Not" + sep +
                        "Not";
            }
            llPol.add(s);
        }
        return llPol;
    }

        /**
         * Returns a list of rows in the report on the results of the polygonometry adjustment
         * @return LinkedList <String>
         */
    public LinkedList<String> getReportXY () {
        LinkedList<String> llReportXY = new LinkedList<String>();
        LinkedList<String> llTopReportXY = new Shell(parentFrame).getTopReportXY();
        HashMap<String, String> titlesReports = new Shell(parentFrame).getTitlesReports();
        String str;
        while ((str = llTopReportXY.pollFirst()) != null) {
            llReportXY.add(str);
        }
        /*
        llReportXY.add("");
        llReportXY.add("                         В  Е  Д  О  М  О  С  Т  Ь   В  Ы  Ч  И  С  Л  Е  Н  И  Я   К  О  О  Р  Д  И  Н  А  Т");
        llReportXY.add("");
        llReportXY.add("-------------------------------------------------------------------------------------------------------------------------------");
        llReportXY.add("|Наименование|   Гор.   |   Гор.   |Поправка |   Дир.   |     п  р  и  р  а  щ  е  н  и  я      |     к о о р д и н а т ы     |");
        llReportXY.add("|   точки    |   угол   |проложение|в г.угол |   угол   |---------------------------------------|-----------------------------|");
        llReportXY.add("|  стояния   |   г.мс   |    м.    |  сек.   |   г.мс   |    DX    |   dX   |    DY    |   dY   |       X      |      Y       |");
        llReportXY.add("|------------|----------|----------|---------|----------|----------|--------|----------|--------|--------------|--------------|");
        llReportXY.add("|     1      |     2    |     3    |    4    |     5    |     6    |    7   |     8    |    9   |       10     |      11      |");
        llReportXY.add("|------------|----------|----------|---------|----------|----------|--------|----------|--------|--------------|--------------|");
        */



        switch (bindType) {
            case TZ, TO, TT -> {
                llReportXY.add("| " + new DataHandler(llTheoStatons.get(0).getName()).toTable(10).getStr() +
                        " |          |          |         |          |          |        |          |        | " +
                        new DataHandler(llTheoStatons.get(0).getX()).toTable(12).getStr() +
                        " | " +
                        new DataHandler(llTheoStatons.get(0).getY()).toTable(12).getStr() +
                        " |");
                llReportXY.add("|            |          |          |         |          |          |        |          |        |              |              |");
            }
            case ZT, OT, OO -> {
                llReportXY.add("| " + new DataHandler(llTheoStatons.get(0).getName()).toTable(10).getStr() +
                        " | " +
                        new DataHandler(llTheoStatons.get(0).getHor()).toTable(8).getStr() +
                        " |          | " +
                        new DataHandler(llTheoStatons.get(0).getDDHor()).format(2).toTable(7).getStr() +
                        " |          |          |        |          |        | " +
                        new DataHandler(llTheoStatons.get(0).getX()).toTable(12).getStr() +
                        " | " +
                        new DataHandler(llTheoStatons.get(0).getY()).toTable(12).getStr() +
                        " |");
                llReportXY.add("|            |          | " +
                        new DataHandler(llTheoStatons.get(0).getLine()).toTable(8).getStr() +
                        " |         | " +
                        new DataHandler(llTheoStatons.get(0).getDirection()).format(4).toTable(8).getStr() +
                        " | " +
                        new DataHandler(llTheoStatons.get(0).getDX()).format(3).toTable(8).getStr() +
                        " | " +
                        new DataHandler(llTheoStatons.get(0).getDDX()).format(3).toTable(6).getStr() +
                        " | " +
                        new DataHandler(llTheoStatons.get(0).getDY()).format(3).toTable(8).getStr() +
                        " | " +
                        new DataHandler(llTheoStatons.get(0).getDDY()).format(3).toTable(6).getStr() +
                        " |              |              |");
            }
        }
        for (int i = 1; i < llTheoStatons.size() -2; i++) {
            llReportXY.add("| " + new DataHandler(llTheoStatons.get(i).getName()).toTable(10).getStr() +
                    " | " +
                    new DataHandler(llTheoStatons.get(i).getHor()).toTable(8).getStr() +
                    " |          | " +
                    new DataHandler(llTheoStatons.get(i).getDDHor()).format(2).toTable(7).getStr() +
                    " |          |          |        |          |        | " +
                    new DataHandler(llTheoStatons.get(i).getX()).toTable(12).getStr() +
                    " | " +
                    new DataHandler(llTheoStatons.get(i).getY()).toTable(12).getStr() +
                    " |");
            llReportXY.add("|            |          | " +
                    new DataHandler(llTheoStatons.get(i).getLine()).toTable(8).getStr() +
                    " |         | " +
                    new DataHandler(llTheoStatons.get(i).getDirection()).format(4).toTable(8).getStr() +
                    " | " +
                    new DataHandler(llTheoStatons.get(i).getDX()).format(3).toTable(8).getStr() +
                    " | " +
                    new DataHandler(llTheoStatons.get(i).getDDX()).format(3).toTable(6).getStr() +
                    " | " +
                    new DataHandler(llTheoStatons.get(i).getDY()).format(3).toTable(8).getStr() +
                    " | " +
                    new DataHandler(llTheoStatons.get(i).getDDY()).format(3).toTable(6).getStr() +
                    " |              |              |");
        }
        llReportXY.add("| " + new DataHandler(llTheoStatons.get(llTheoStatons.size() - 2).getName()).toTable(10).getStr() +
                " | " +
                new DataHandler(llTheoStatons.get(llTheoStatons.size() - 2).getHor()).toTable(8).getStr() +
                " |          | " +
                new DataHandler(llTheoStatons.get(llTheoStatons.size() - 2).getDDHor()).format(2).toTable(7).getStr() +
                " |          |          |        |          |        | " +
                new DataHandler(llTheoStatons.get(llTheoStatons.size() - 2).getX()).toTable(12).getStr() +
                " | " +
                new DataHandler(llTheoStatons.get(llTheoStatons.size() - 2).getY()).toTable(12).getStr() +
                " |");

        switch (bindType) {
            case OO, TO, TZ -> llReportXY.add("|            |          | " +
                    new DataHandler(llTheoStatons.get(llTheoStatons.size() - 2).getLine()).toTable(8).getStr() +
                    " |         | " +
                    new DataHandler(llTheoStatons.get(llTheoStatons.size() - 2).getDirection()).format(4).toTable(8).getStr() +
                    " | " +
                    new DataHandler(llTheoStatons.get(llTheoStatons.size() - 2).getDX()).format(3).toTable(8).getStr() +
                    " | " +
                    new DataHandler(llTheoStatons.get(llTheoStatons.size() - 2).getDDX()).format(3).toTable(6).getStr() +
                    " | " +
                    new DataHandler(llTheoStatons.get(llTheoStatons.size() - 2).getDY()).format(3).toTable(8).getStr() +
                    " | " +
                    new DataHandler(llTheoStatons.get(llTheoStatons.size() - 2).getDDY()).format(3).toTable(6).getStr() +
                    " |              |              |");
            case TT, ZT, OT -> llReportXY.add("|            |          |          |         |          |          |        |          |        |              |              |");
        }
        llReportXY.add("| " + new DataHandler(llTheoStatons.get(llTheoStatons.size() - 1).getName()).toTable(10).getStr() +
                " |          |          |         |          |          |        |          |        | " +
                new DataHandler(llTheoStatons.get(llTheoStatons.size() - 1).getX()).toTable(12).getStr() +
                " | " +
                new DataHandler(llTheoStatons.get(llTheoStatons.size() - 1).getY()).toTable(12).getStr() +
                " |");
        llReportXY.add("-------------------------------------------------------------------------------------------------------------------------------");
        llReportXY.add("");
        llReportXY.add(titlesReports.get("TPfoterTitle"));
        llReportXY.add(titlesReports.get("TPperimeter") + new DataHandler(perimeter).format(3).getStr() + titlesReports.get("TPm"));
        llReportXY.add(titlesReports.get("TPangleResidues"));
        if (bindType == BindType.TT) {
            llReportXY.add(titlesReports.get("TPactual") + new DataHandler(fHor).format(2).getStr() + titlesReports.get("TPsek"));
            llReportXY.add(titlesReports.get("TPacceptable") +
                    new DataHandler(parentFrame.getOptions().getValueFHor() * Math.sqrt(llTheoStatons.size() - 2)).format(0).getStr() +
                    titlesReports.get("TPsek"));
        } else {
            llReportXY.add(titlesReports.get("TPactual") + "-.-");
            llReportXY.add(titlesReports.get("TPacceptable") + "-.-");

        }
        llReportXY.add(titlesReports.get("TPlineResidues"));
        if (bindType == BindType.TZ | bindType == BindType.ZT) {
            llReportXY.add(titlesReports.get("TPlineDX") + "-.-");
            llReportXY.add(titlesReports.get("TPlineDY") + "-.-");
            llReportXY.add(titlesReports.get("TPabsoluteDeviation") + "-.-");
            llReportXY.add(titlesReports.get("TPactualRelativeDeviation") + "-.-");
            llReportXY.add(titlesReports.get("TPacceptableRelativeDeviation") + "-.-");
        } else {
            llReportXY.add(titlesReports.get("TPlineDX") + new DataHandler(fX).format(3).getStr() + "м.");
            llReportXY.add(titlesReports.get("TPlineDY") + new DataHandler(fY).format(3).getStr() + "м.");
            llReportXY.add(titlesReports.get("TPabsoluteDeviation") + new DataHandler(fAbs).format(3).getStr() + "м.");
            llReportXY.add(titlesReports.get("TPactualRelativeDeviation") + "1:" + fOtn);
            llReportXY.add(titlesReports.get("TPacceptableRelativeDeviation") + "1:" + parentFrame.getOptions().getValueFOtn());

        }
        return llReportXY;
        }

        /**
         * Returns a list of rows in the report on the results of the leveling  adjustment
         * @return LinkedList <String>
         */
        public LinkedList<String> getReportZ () {
            double dZCorrected;
            double sumDZ = 0.0;
            double sumDDZ = 0.0;
            double sumDZCorrected = 0.0;
            int start = 0;
            int finish = llTheoStatons.size() - 1;
            LinkedList<String> llReportZ = new LinkedList<String>();
            LinkedList<String> llTopReportZ = new Shell(parentFrame).getTopReportZ();
            HashMap<String, String> titlesReports = new Shell(parentFrame).getTitlesReports();
            String str;
            while ((str = llTopReportZ.pollFirst()) != null) {
                llReportZ.add(str);
            }
            /*
            llReportZ.add("");
            llReportZ.add("           В Е Д О М О С Т Ь  В Ы Ч И С Л Е Н И Я  В Ы С О Т");
            llReportZ.add("");
            llReportZ.add("-----------------------------------------------------------------------");
            llReportZ.add("|Наименование|  Длина   | Измеренное |Поправка|Исправленное|Абсолютная|");
            llReportZ.add("|    пункта  | стороны  | превышение |        | превышение | отметка  |");
            llReportZ.add("|            |     м.   |      м.    |   мм.  |     м.     |    м.    |");
            llReportZ.add("|------------|----------|------------|--------|------------|----------|");
            llReportZ.add("|     1      |     2    |      3     |    4   |      5     |     6    |");
            llReportZ.add("|------------|----------|------------|--------|------------|----------|");
            */




            if (bindType == BindType.TT | bindType == BindType.TO | bindType == BindType.TZ) {
                start = 1;
            }
            if (bindType == BindType.ZT | bindType == BindType.OT | bindType == BindType.TT) {
                finish = llTheoStatons.size() - 2;
            }
            for (int i = start; i < finish; i++) {
                dZCorrected = Double.parseDouble(llTheoStatons.get(i).getDZ()) + llTheoStatons.get(i).getDDZ();
                sumDZCorrected += dZCorrected;
                sumDDZ += llTheoStatons.get(i).getDDZ();
                sumDZ += Double.parseDouble(llTheoStatons.get(i).getDZ());
                llReportZ.add("| " + new DataHandler(llTheoStatons.get(i).getName()).toTable(10).getStr() +
                        " |          |            |        |            | " +
                        new DataHandler(llTheoStatons.get(i).getZ()).toTable(8).getStr() + " |");
                llReportZ.add("|            | " +
                        new DataHandler(llTheoStatons.get(i).getLine()).toTable(8).getStr() +
                        " | " +
                                new DataHandler(llTheoStatons.get(i).getDZ()).toTable(10).getStr() +
                        " | " +
                                new DataHandler(llTheoStatons.get(i).getDDZ() * 1000).format(2).toTable(6).getStr() +
                        " | " +
                                new DataHandler(dZCorrected).format(3).toTable(10).getStr() +
                        " |          |"
                        );
            }
            llReportZ.add("| " + new DataHandler(llTheoStatons.get(finish).getName()).toTable(10).getStr() +
                    " |          |            |        |            | " +
                    new DataHandler(llTheoStatons.get(finish).getZ()).toTable(8).getStr() + " |");
            llReportZ.add("|------------|----------|------------|--------|------------|----------|");
            llReportZ.add("|" + titlesReports.get("TPcontrol") +" | " +
                    new DataHandler(perimeter).format(3).toTable(8).getStr() +
                    " | " +
                    new DataHandler(sumDZ).format(3).toTable(10).getStr() +
                    " | " +
                    new DataHandler(sumDDZ * 1000).format(2).toTable(6).getStr() +
                    " | " +
                    new DataHandler(sumDZCorrected).format(3).toTable(10).getStr() +
                    " |          |"
                    );
//            llReportZ.add("|  суммы     |          |            |        |            |          |");
            llReportZ.add("-----------------------------------------------------------------------");
            llReportZ.add("");
            llReportZ.add(titlesReports.get("TPfoterTitle"));
            llReportZ.add(titlesReports.get("TPperimeter") +
                    new DataHandler(perimeter).format(3).getStr() +
                    titlesReports.get("TPm"));
            if (bindType == BindType.TZ | bindType == BindType.ZT) {
                llReportZ.add(titlesReports.get("TPactualResidue") + "-.-");
                llReportZ.add(titlesReports.get("TPacceptableResidue") + "-.-");

            } else {
                llReportZ.add(titlesReports.get("TPactualResidue") +
                        new DataHandler(fZ * 1000).format(0).getStr() +
                        titlesReports.get("TPmm"));
                llReportZ.add(titlesReports.get("TPacceptableResidue") +
                        new DataHandler(parentFrame.getOptions().getValueFH() * Math.sqrt(perimeter / 1000)).format(0).getStr() +
                        titlesReports.get("TPmm"));
            }
            return llReportZ;
        }

        /**
         * Returns a list of rows in the report <Name X Y Z>
         * @return LinkedList <String>
         */
        public LinkedList<String> getReportNXYZ() {
            String sep = " ";
            LinkedList<String> llReportNXYZ = new LinkedList<String>();
            for (PolygonStation llTheoStaton : llTheoStatons) {
                llReportNXYZ.add(llTheoStaton.getName() + sep +
                        llTheoStaton.getX() + sep +
                        llTheoStaton.getY() + sep +
                        llTheoStaton.getZ());
            }
            return llReportNXYZ;
        }

        /**
         * Сhecks the possibility of inserting before idx position
         * @param idx int idx
         * @return boolean
         */
    public Boolean isInsertBefore(int idx) {
        if (!llTheoStatons.get(idx).getStatus()) return true;
        if (llTheoStatons.get(idx).getStatus() & idx > 0) {
            return !llTheoStatons.get(idx - 1).getStatus();
        }
        return false;
    }

        /**
         * Сhecks the possibility of inserting after idx position
         * @param idx int idx
         * @return
         */
    public Boolean isInsertAfter(int idx) {
        if (!llTheoStatons.get(idx).getStatus()) return true;
        if (llTheoStatons.get(idx).getStatus() & idx < llTheoStatons.size() - 1) {
            return !llTheoStatons.get(idx + 1).getStatus();
        }
        return false;
    }

        /**
         * set and return bindings type of current TheoProject
         * @return
         */
        private BindType setBindType() {
            bindType = BindType.ZZ;
//          if (llTheoStatons != null) {
              if (llTheoStatons.size() > 2) {
//                  int last = llTheoStatons.size() - 1;
                  if (llTheoStatons.get(0).getStatus() &
                          llTheoStatons.get(1).getStatus() &
                          llTheoStatons.get(llTheoStatons.size() - 1).getStatus() &
                          llTheoStatons.get(llTheoStatons.size() - 2).getStatus()) {
                      bindType = BindType.TT;
                      if (!isValidSourceData(1,llTheoStatons.size() - 3) | !new DataHandler(llTheoStatons.get(llTheoStatons.size() - 2).getHor()).isPositiveNumber()) {
                          bindType = BindType.ZZ;
                          JOptionPane.showMessageDialog(parentFrame,
                                  parentFrame.getTitles().get("TPmessageError"),
                                  parentFrame.getTitles().get("TPmessageErrorTitle"),
                                  JOptionPane.ERROR_MESSAGE);
                      }


                  }
                  if (llTheoStatons.get(0).getStatus() &
                          llTheoStatons.get(1).getStatus() &
                          llTheoStatons.get(llTheoStatons.size() - 1).getStatus() &
                          !llTheoStatons.get(llTheoStatons.size() - 2).getStatus()) {
                      bindType = BindType.TO;
                      if (!isValidSourceData(1,llTheoStatons.size() - 2)) {
                          bindType = BindType.ZZ;
                          JOptionPane.showMessageDialog(null,
                                  parentFrame.getTitles().get("TPmessageError"),
                                  parentFrame.getTitles().get("TPmessageErrorTitle"),
                                  JOptionPane.ERROR_MESSAGE);
                      }

                  }
                  if (llTheoStatons.get(0).getStatus() &
                          !llTheoStatons.get(1).getStatus() &
                          llTheoStatons.get(llTheoStatons.size() - 1).getStatus() &
                          llTheoStatons.get(llTheoStatons.size() - 2).getStatus()) {
                      bindType = BindType.OT;
                      if (!isValidSourceData(0,llTheoStatons.size() - 3) | !new DataHandler(llTheoStatons.get(llTheoStatons.size() - 2).getHor()).isPositiveNumber()) {
                          bindType = BindType.ZZ;
                          JOptionPane.showMessageDialog(null,
                                  parentFrame.getTitles().get("TPmessageError"),
                                  parentFrame.getTitles().get("TPmessageErrorTitle"),
                                  JOptionPane.ERROR_MESSAGE);
                      }
                  }
                  if (llTheoStatons.get(0).getStatus() &
                          !llTheoStatons.get(1).getStatus() &
                          llTheoStatons.get(llTheoStatons.size() - 1).getStatus() &
                          !llTheoStatons.get(llTheoStatons.size() - 2).getStatus()) {
                      bindType = BindType.OO;
                      if (!isValidSourceData(0,llTheoStatons.size() - 2)) {
                          bindType = BindType.ZZ;
                          JOptionPane.showMessageDialog(null,
                                  parentFrame.getTitles().get("TPmessageError"),
                                  parentFrame.getTitles().get("TPmessageErrorTitle"),
                                  JOptionPane.ERROR_MESSAGE);
                      }
                  }
                  if (llTheoStatons.get(0).getStatus() &
                          llTheoStatons.get(1).getStatus() &
                          !llTheoStatons.get(llTheoStatons.size() - 1).getStatus() &
                          !llTheoStatons.get(llTheoStatons.size() - 2).getStatus()) {
                      bindType = BindType.TZ;
                      if (!isValidSourceData(1,llTheoStatons.size() - 3) | !new DataHandler(llTheoStatons.get(llTheoStatons.size() - 2).getHor()).isPositiveNumber()) {
                          bindType = BindType.ZZ;
                          JOptionPane.showMessageDialog(null,
                                  parentFrame.getTitles().get("TPmessageError"),
                                  parentFrame.getTitles().get("TPmessageErrorTitle"),
                                  JOptionPane.ERROR_MESSAGE);
                      }
                  }
                  if (!llTheoStatons.get(0).getStatus() &
                          !llTheoStatons.get(1).getStatus() &
                          llTheoStatons.get(llTheoStatons.size() - 1).getStatus() &
                          llTheoStatons.get(llTheoStatons.size() - 2).getStatus()) {
                      bindType = BindType.ZT;
                      if (!isValidSourceData(0,llTheoStatons.size() - 3) | !new DataHandler(llTheoStatons.get(llTheoStatons.size() - 2).getHor()).isPositiveNumber()) {
                          bindType = BindType.ZZ;
                          JOptionPane.showMessageDialog(null,
                                  parentFrame.getTitles().get("TPmessageError"),
                                  parentFrame.getTitles().get("TPmessageErrorTitle"),
                                  JOptionPane.ERROR_MESSAGE);
                      }
                  }

              }
              if (noNeed()) {
                  bindType = BindType.ZZ;
              }
//          }
          return bindType;
        }

        /**
         * checks the need for adjustment
         * @return boolen
         */
        private boolean noNeed() {
            for (PolygonStation llTheoStaton : llTheoStatons) {
                if (!llTheoStaton.getStatus()) {
                    return false;
                }
            }
            return true;
        }
        /**
         * Determines the index of the TheoStation with the longest line length
         * @param start index of the start of the search
         * @param finish index of the end of the search
         * @return int
         */
        private int idxMaxLength(int start, int finish) {
            int k = start;
            for (int i = k; i <= finish; i++) {
                if (new DataHandler(llTheoStatons.get(i).getLine()).getDbl() > new DataHandler(llTheoStatons.get(k).getLine()).getDbl()) {
                    k = i;
                }
            }
            return k;
        }

        /**
         * Сhecks the source data for errors
         * @param start index of the start of the search
         * @param finish index of the end of the search
         * @return boolean
         */
        private Boolean isValidSourceData(int start, int finish) {
            for (int i = start; i <= finish; i++) {
                if (!new DataHandler(llTheoStatons.get(i).getName()).isValidName() |
                        !new DataHandler(llTheoStatons.get(i).getHor()).isPositiveNumber() |
                !new DataHandler(llTheoStatons.get(i).getLine()).isPositiveNumber() |
                !new DataHandler(llTheoStatons.get(i).getDZ()).isNumber()) {
                    return false;
                }
            }
            return true;
        }

        /**
         * sets Directions for llTheoStations in the specified range
         * @param start index of the start of the search
         * @param finish index of the end of the search
         */
        private void setDirections(int start, int finish) {
            double dir;
            DataHandler hor;
            if (start <= finish) {
                for (int i = start; i <= finish; i++) {
                    hor = new DataHandler(llTheoStatons.get(i).getHor());
                    dir = llTheoStatons.get(i - 1).getDirection() + 180 + hor.dmsToDeg() + llTheoStatons.get(i).getDDHor() / 3600;
                    while (dir >= 360) {
                        dir = dir - 360;
                    }
                    llTheoStatons.get(i).setDirection(dir);
                }
            } else {
                for (int i = start; i >= finish; i--) {
                    hor = new DataHandler(llTheoStatons.get(i+1).getHor());
                    dir = llTheoStatons.get(i +1).getDirection() - 180 - hor.dmsToDeg();
                    while (dir < 0) {
                        dir = dir + 360;
                    }
                    llTheoStatons.get(i).setDirection(dir);
                }
            }
        }

        /**
         * sets dX and dY for llTheoStations in the specified range
         * @param start index of the start of the search
         * @param finish index of the end of the search
         */
        private void setDXDYs(int start, int finish) {
            DataHandler line;
            for (int i = start; i <= finish; i++) {
                line = new DataHandler(llTheoStatons.get(i).getLine());
                llTheoStatons.get(i).setDX(line.getDbl() * Math.cos(Math.toRadians(llTheoStatons.get(i).getDirection())));
                llTheoStatons.get(i).setDY(line.getDbl() * Math.sin(Math.toRadians(llTheoStatons.get(i).getDirection())));
            }
        }

        /**
         * sets coordinates X, Y, Z in the specified range
         * @param start index of the start of the search
         * @param finish index of the end of the search
         */
        private void setXYZs(int start, int finish) {
            double parentX;
            double parentY;
            double parentZ;
            for (int i = start; i <= finish; i++) {
                parentX = Double.parseDouble(llTheoStatons.get(i - 1).getX());
                parentY = Double.parseDouble(llTheoStatons.get(i - 1).getY());
                parentZ = Double.parseDouble(llTheoStatons.get(i -1).getZ());
                llTheoStatons.get(i).setX(new DataHandler(parentX + llTheoStatons.get(i - 1).getDX() + llTheoStatons.get(i - 1).getDDX()).format(3).getStr());
                llTheoStatons.get(i).setY(new DataHandler(parentY + llTheoStatons.get(i - 1).getDY() + llTheoStatons.get(i - 1).getDDY()).format(3).getStr());
                llTheoStatons.get(i).setZ(new DataHandler(parentZ + Double.parseDouble(llTheoStatons.get(i -1).getDZ()) + llTheoStatons.get(i - 1).getDDZ()).format(3).getStr());
            }

        }

        /**
         * initializes ddHor, ddX, ddY, ddZ for all llTheoStations
         */
        private void iniDDs() {
            for (PolygonStation llTheoStaton : llTheoStatons) {
                llTheoStaton.setDDHor(0.0);
                llTheoStaton.setDDX(0.0);
                llTheoStaton.setDDY(0.0);
                llTheoStaton.setDDZ(0.0);
            }
        }

        /**
         * calculate and set perimeter
         * @param start index of the start of the search
         * @param finish index of the end of the search
         */
        private void setPerimeter(int start, int finish) {
            double sum = 0.0;
            DataHandler line;
            for (int i = start; i <= finish; i++) {
                line = new DataHandler(llTheoStatons.get(i).getLine());
                sum += Double.parseDouble(llTheoStatons.get(i).getLine());
            }
            perimeter = sum;
        }

        /**
         * calculate fHor and sets ddHor for all llTheoStations with bindType
         */
        private void setDDHors() {
            DataHandler hor;
            double d = llTheoStatons.get(0).getDirection();
            for (int i = 1; i <= llTheoStatons.size() - 2; i++) {
                hor = new DataHandler(llTheoStatons.get(i).getHor());
                d = d + hor.dmsToDeg() + 180;
            }
            while (d >= 360) {
                d = d - 360;
            }
            fHor = (d - llTheoStatons.get(llTheoStatons.size() - 2).getDirection()) * 3600;
            for (int i = 1; i <= llTheoStatons.size() - 2; i++) {
                d = -1 * fHor / (llTheoStatons.size() - 2);
                llTheoStatons.get(i).setDDHor(d);
            }
        }

        /**
         * calculate fX, fY and sets ddX, ddY for all llTheoStations with interval
         * @param start index of the start of the search
         * @param finish index of the end of the search
         */
        private void setDDXDDYs(int start, int finish) {
            double sumDX = 0.0;
            double sumDY = 0.0;
            for (int i = start; i <= finish; i++) {
                sumDX = sumDX + llTheoStatons.get(i).getDX();
                sumDY = sumDY + llTheoStatons.get(i).getDY();
            }
            fX = sumDX + Double.parseDouble(llTheoStatons.get(start).getX()) - Double.parseDouble(llTheoStatons.get(finish + 1).getX());
            fY = sumDY + Double.parseDouble(llTheoStatons.get(start).getY()) - Double.parseDouble(llTheoStatons.get(finish + 1).getY());
            fAbs = Math.hypot(fX, fY);
            fOtn = new DataHandler(1 / (new DataHandler(fAbs).format(3).getDbl() / new DataHandler(perimeter).format(3).getDbl())).format(0).getStr();
            for (int i = start; i <= finish; i++) {
                llTheoStatons.get(i).setDDX(-1 * fX / perimeter * Double.parseDouble(llTheoStatons.get(i).getLine()));
                llTheoStatons.get(i).setDDY(-1 * fY / perimeter * Double.parseDouble(llTheoStatons.get(i).getLine()));
            }
        }

        /**
         * calculate fZ and sets ddZ for all llTheoStations with interval
         * @param start index of the start of the search
         * @param finish index of the end of the search
         */
        private void setDDZs(int start, int finish) {
            double sumZ = 0.0;
            for (int i = start; i <= finish; i++) {
                sumZ = sumZ + Double.parseDouble(llTheoStatons.get(i).getDZ());
            }
            fZ = sumZ + Double.parseDouble(llTheoStatons.get(start).getZ()) - Double.parseDouble(llTheoStatons.get(finish +1).getZ());
            for (int i = start; i <= finish; i++) {
                llTheoStatons.get(i).setDDZ(-1 * fZ / perimeter * Double.parseDouble(llTheoStatons.get(i).getLine()));
            }
        }

        /**
         * Processes the source data.
         * Adjustment the network and determines the coordinates of the defined points llTheoStations
         */
        public void processSourceData() {
            perimeter = 0;
            double sumDX = 0.0;
            double sumDY = 0.0;
            double sumDZ = 0.0;
            GeoCalc geoCalc = new GeoCalc();
            bindType = setBindType();
            switch (bindType) {
                case ZZ -> JOptionPane.showMessageDialog(parentFrame,
                        parentFrame.getTitles().get("TPmessageNoAdjustment"),
                        parentFrame.getTitles().get("TPmessageNoAdjustmentTitle"),
                        JOptionPane.ERROR_MESSAGE);
                case TT -> {
                    iniDDs();
                    setPerimeter(1, llTheoStatons.size() - 3);
                    llTheoStatons.get(0).setDirection(Math.toDegrees(geoCalc.getDirAB(
                            llTheoStatons.get(0).getX(),
                            llTheoStatons.get(0).getY(),
                            llTheoStatons.get(1).getX(),
                            llTheoStatons.get(1).getY())));
                    llTheoStatons.get(llTheoStatons.size() - 2).setDirection(Math.toDegrees(geoCalc.getDirAB(
                            llTheoStatons.get(llTheoStatons.size() - 2).getX(),
                            llTheoStatons.get(llTheoStatons.size() - 2).getY(),
                            llTheoStatons.get(llTheoStatons.size() - 1).getX(),
                            llTheoStatons.get(llTheoStatons.size() - 1).getY())));
                    setDDHors();
                    setDirections(1, llTheoStatons.size() - 3);
                    setDXDYs(1, llTheoStatons.size() - 3);
                    setDDXDDYs(1, llTheoStatons.size() - 3);
                    setDDZs(1, llTheoStatons.size() - 3);
                    setXYZs(2, llTheoStatons.size() - 3);
                }
                case TO -> {
                    iniDDs();
                    setPerimeter(1, llTheoStatons.size() - 2);
                    llTheoStatons.get(0).setDirection(Math.toDegrees(new SurveyStation("Not",
                            llTheoStatons.get(0).getX(),
                            llTheoStatons.get(0).getY(),
                            llTheoStatons.get(0).getZ(),
                            "Not",
                            llTheoStatons.get(1).getX(),
                            llTheoStatons.get(1).getY(),
                            llTheoStatons.get(1).getZ(),
                            "0.000").getDirection()));
                    setDirections(1, llTheoStatons.size() - 2);
                    setDXDYs(1, llTheoStatons.size() - 2);
                    setDDXDDYs(1, llTheoStatons.size() - 2);
                    setDDZs(1, llTheoStatons.size() - 2);
                    setXYZs(2, llTheoStatons.size() - 2);
                }
                case OT -> {
                    iniDDs();
                    setPerimeter(0, llTheoStatons.size() - 3);
                    llTheoStatons.get(llTheoStatons.size() - 2).setDirection(Math.toDegrees(new SurveyStation("Not",
                            llTheoStatons.get(llTheoStatons.size() - 2).getX(),
                            llTheoStatons.get(llTheoStatons.size() - 2).getY(),
                            llTheoStatons.get(llTheoStatons.size() - 2).getZ(),
                            "Not",
                            llTheoStatons.get(llTheoStatons.size() - 1).getX(),
                            llTheoStatons.get(llTheoStatons.size() - 1).getY(),
                            llTheoStatons.get(llTheoStatons.size() - 1).getZ(),
                            "0.000").getDirection()));
                    setDirections(llTheoStatons.size() - 3, 0);
                    setDXDYs(0, llTheoStatons.size() - 3);
                    setDDXDDYs(0, llTheoStatons.size() - 3);
                    setDDZs(0, llTheoStatons.size() - 3);
                    setXYZs(1, llTheoStatons.size() - 3);
                }
                case OO -> {
                    iniDDs();
                    setPerimeter(0, llTheoStatons.size() - 2);
                    llTheoStatons.get(0).setDirection(0.0);
                    setDirections(1, llTheoStatons.size() - 2);
                    setDXDYs(0, llTheoStatons.size() - 2);
                    for (int i = 0; i <= llTheoStatons.size() - 2; i++) {
                        sumDX = sumDX + llTheoStatons.get(i).getDX();
                        sumDY = sumDY + llTheoStatons.get(i).getDY();
                    }
                    sumDX = sumDX + Double.parseDouble(llTheoStatons.get(0).getX());
                    sumDY = sumDY + Double.parseDouble(llTheoStatons.get(0).getY());
                    sumDZ = Math.toDegrees(new SurveyStation("Not",
                            llTheoStatons.get(0).getX(),
                            llTheoStatons.get(0).getY(),
                            "0.000",
                            "Not",
                            llTheoStatons.get(llTheoStatons.size() - 1).getX(),
                            llTheoStatons.get(llTheoStatons.size() - 1).getY(),
                            "0.000",
                            "0.000").getDirection()) -
                            Math.toDegrees(new SurveyStation("Not",
                                    llTheoStatons.get(0).getX(),
                                    llTheoStatons.get(0).getY(),
                                    "0.000",
                                    "Not",
                                    new DataHandler(sumDX).format(3).getStr(),
                                    new DataHandler(sumDY).format(3).getStr(),
                                    "0.000",
                                    "0.000").getDirection());
                    while (sumDZ < 0.0) {
                        sumDZ = sumDZ + 360;
                    }
                    llTheoStatons.get(0).setDirection(sumDZ);
                    setDirections(1, llTheoStatons.size() - 2);
                    setDXDYs(0, llTheoStatons.size() - 2);
                    setDDXDDYs(0, llTheoStatons.size() - 2);
                    setDDZs(0, llTheoStatons.size() - 2);
                    setXYZs(1, llTheoStatons.size() - 2);
                }
                case TZ -> {
                    iniDDs();
                    setPerimeter(1, llTheoStatons.size() - 2);
                    llTheoStatons.get(0).setDirection(Math.toDegrees(new SurveyStation("Not",
                            llTheoStatons.get(0).getX(),
                            llTheoStatons.get(0).getY(),
                            llTheoStatons.get(0).getZ(),
                            "Not",
                            llTheoStatons.get(1).getX(),
                            llTheoStatons.get(1).getY(),
                            llTheoStatons.get(1).getZ(),
                            "0.000").getDirection()));
                    setDirections(1, llTheoStatons.size() - 2);
                    setDXDYs(1, llTheoStatons.size() - 2);
                    setXYZs(2, llTheoStatons.size() - 1);
                }
                case ZT -> {
                    iniDDs();
                    setPerimeter(0, llTheoStatons.size() - 3);
                    llTheoStatons.get(llTheoStatons.size() - 2).setDirection(Math.toDegrees(new SurveyStation("Not",
                            llTheoStatons.get(llTheoStatons.size() - 2).getX(),
                            llTheoStatons.get(llTheoStatons.size() - 2).getY(),
                            llTheoStatons.get(llTheoStatons.size() - 2).getZ(),
                            "Not",
                            llTheoStatons.get(llTheoStatons.size() - 1).getX(),
                            llTheoStatons.get(llTheoStatons.size() - 1).getY(),
                            llTheoStatons.get(llTheoStatons.size() - 1).getZ(),
                            "0.000").getDirection()));
                    setDirections(llTheoStatons.size() - 3, 0);
                    setDXDYs(0, llTheoStatons.size() - 3);
                    for (int i = 0; i <= llTheoStatons.size() - 3; i++) {
                        sumDX = sumDX + llTheoStatons.get(i).getDX();
                        sumDY = sumDY + llTheoStatons.get(i).getDY();
                        sumDZ = sumDZ + Double.parseDouble(llTheoStatons.get(i).getDZ());
                    }
                    llTheoStatons.get(0).setX(new DataHandler(Double.parseDouble(llTheoStatons.get(llTheoStatons.size() - 2).getX()) - sumDX).format(3).getStr());
                    llTheoStatons.get(0).setY(new DataHandler(Double.parseDouble(llTheoStatons.get(llTheoStatons.size() - 2).getY()) - sumDY).format(3).getStr());
                    llTheoStatons.get(0).setZ(new DataHandler(Double.parseDouble(llTheoStatons.get(llTheoStatons.size() - 2).getZ()) - sumDZ).format(3).getStr());
                    setXYZs(1, llTheoStatons.size() - 3);
                }


//-->> The END of switch
            }

//-->> The END of processSourceData
        }


//-->> The END of class TheoProject
    }
