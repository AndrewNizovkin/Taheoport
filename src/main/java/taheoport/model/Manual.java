package taheoport.model;

import taheoport.gui.MainWin;

import javax.swing.*;
import java.awt.*;

/**
 * This class encapsulates User's Guide
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class Manual {
    private final MainWin parentFrame;
    private final String newLine = "\n";
    private final Insets insets= new Insets(10, 10, 10, 10);

    /**
     * Constructor
     * @param parentFrame MainWin
     */
    public Manual(MainWin parentFrame) {
        this.parentFrame = parentFrame;
    }

    /**
     * gets content for panel Introduction of ShowHelp
     * @return JTextArea
     */
    public JTextArea getIntroduction() {
        JTextArea textArea = new JTextArea();
        switch (parentFrame.getOptions().getLanguage()) {
            case 0 -> {
                textArea.append("    Total stations are used in carrying out any geodetic work related to measurements:" + newLine +newLine);
                textArea.append("\t• creation of basic points networks;" + newLine + newLine);
                textArea.append("\t• creating digital models of landscapes or buildings;" + newLine + newLine);
                textArea.append("\t• surveys in the design and construction of engineering construction;" + newLine + newLine);
                textArea.append("\t• observations of deformations of the earth's surface and engineering construction;" + newLine + newLine);
                textArea.append("\t• surveying and land management works;" + newLine + newLine);
                textArea.append("\t• surveying work in mine workings;" + newLine + newLine);
                textArea.append("\t• other tasks." + newLine + newLine);
                textArea.append("    High accuracy and the presence of a non-prism  distance" +
                        " measurement mode in modern devices in combination with the necessary" +
                        " software makes the total station a universal tool of the surveyor." + newLine + newLine);
                textArea.append("    The results of measurements performed by the total station allow" +
                        " you to calculate the 3D coordinates of the special points, which can be used" +
                        " to build a 3D  model of  a landscape or a construction or other tasks." + newLine + newLine);
                textArea.append("    The development of geodetic GNSS equipment makes it possible to solve" +
                        " many applied tasks, including determining the coordinates of the reference and survey" +
                        " network. Using GNSS, it is possible to determine the coordinates of the points of the survey" +
                        " network in parallel and together with the surveys." + newLine + newLine);
                textArea.append("    With a sufficient number of satellites, an acceptable distance from the base" +
                        " station, in a work area devoid of high structures (reflected signal generators)," +
                        " and not requiring high accuracy, it is quite possible to perform work exclusively" +
                        " using GNSS equipment." + newLine + newLine);
                textArea.append("    It is obvious that the most advanced GNSS technologies cannot always be used" +
                        " to work effectively and obtain results with the necessary and sufficient level of accuracy." +
                        " Will you be able to perform an executive survey of an oil refinery with “a million” pillars" +
                        " and trestles at different levels using exclusively GNSS. What should I do if the route of your" +
                        " research crosses a high-trunked forest, cut by ravines and gullies, or is laid in a narrow strip" +
                        " of multi-storey urban development?" + newLine + newLine);
                textArea.append("    The using of an electronic total station in many cases is not only justified," +
                        " but also the only possible and moreover more effective way to solve the task. The development" +
                        " of  survey network by laying polygonometric and leveling polygons between the basic points of" +
                        " the reference network is devoid of GNSS limitations and allows you to evaluate the accuracy of" +
                        " the measurements performed." + newLine + newLine);
                textArea.append("    Taheoport provides an opportunity to automate tasks frequently encountered in geodetic practice:" + newLine + newLine);
                textArea.append("\t• import of measurements from measurement files of electronic total stations;" + newLine + newLine);
                textArea.append("\t• editing the source data and getting the coordinates of the defined points;" + newLine + newLine);
                textArea.append("\t• extracting a polygon from a measurement file;" + newLine + newLine);
                textArea.append("\t• adjustment of polygons with a different method of binding to the reference" +
                        " geodetic network and obtaining coordinates of points of the survey network;" + newLine + newLine);
                textArea.append("\t• and others.");

            }
            case  1 -> {
                textArea.append("    Электронные тахеометры используются при проведении " +
                        "любых геодезических работ, связанных с измерениями:" + newLine +newLine);
                textArea.append("\t• создание опорных и съёмочных планово-высотных сетей;" + newLine + newLine);
                textArea.append("\t• создание цифровых моделей местности или сооружений;" + newLine + newLine);
                textArea.append("\t• изыскания при проектировании и строительстве инженерных сооружений;" + newLine + newLine);
                textArea.append("\t• наблюдения за деформациями земной поверхности и инженерных сооружений;" + newLine + newLine);
                textArea.append("\t• землеустроительные работы;" + newLine + newLine);
                textArea.append("\t• маркшейдерские работы в горных выработках;" + newLine + newLine);
                textArea.append("\t• другие прикладные задачи." + newLine + newLine);
                textArea.append("    Высокая точность и наличие в современных приборах " +
                        "безотражательного режима измерений расстояний в сочетании с необходимым " +
                        "программным обеспечением делает тахеометр универсальным, а " +
                        "иногда и безальтернативным инструментом геодезиста." + newLine + newLine);
                textArea.append("    Результаты измерений, выполненных тахеометром позволяют вычислить " +
                        "3D координаты характерных точек (пикетов), которые могут быть " +
                        "использованы для построения 3D модели сооружения или других задач." + newLine + newLine);
                textArea.append("    Развитие геодезического спутникового оборудования позволяет решать многие прикладные задачи, в том числе" +
                        " и определение координат опорной и съёмочной сети. Используя GNSS, можно определять координаты точек съёмочной сети" +
                        " параллельно и совместно с изысканиями." + newLine + newLine);
                textArea.append("    При достаточном количестве спутников, приемлемом удалении от базовой станции" +
                        ",  на участке работ лишённом высоких сооружений (генераторов отражённого сигнала), и не требующем высокой точности," +
                        " вполне возможно выполнить работу исключительно с использованием GNSS оборудования." + newLine + newLine);
                textArea.append("    Очевидно, что самые передовые спутниковые технологии далеко не всегда могут быть использованы" +
                        " для эффективной работы и получения результата с необходимым и достаточным уровнем точности. Сможете ли Вы" +
                        " выполнить исполнительную съёмку нефтеперерабатывающего завода с “миллионом” опор и эстакад в разных уровнях" +
                        " с  использованием исключительно GNSS. Что делать если трасса Ваших изысканий пересекает высокоствольный лес," +
                        " изрезанный оврагами и балками или проложена в узкой полосе многоэтажной городской застройки?" + newLine + newLine);
                textArea.append("    Использование электронного тахеометра во многих случаях является не только оправданным," +
                        " но и единственно возможным и кроме того более эффективным способом решения поставленной задачи." +
                        " Развитие планово-высотной съёмочной сети (ПВСС) путём прокладки полигонометрических и нивелирных ходов" +
                        " (полигонов) между пунктами планово-высотной опорной сети (ПВОС) лишено ограничений GNSS и позволяет оценить" +
                        " точность выполненных измерений. " + newLine + newLine);
                textArea.append("    Taheoport предоставляет возможность автоматизировать часто встречающиеся в практике задачи:" + newLine + newLine);
                textArea.append("\t• импорт измерений из файлов измерений электронных тахеометров;" + newLine);
                textArea.append(newLine);
                textArea.append("\t• редактирование исходных данных и получение координат определяемых точек;" + newLine + newLine);
                textArea.append("\t• извлечение полигона из файла измерений;" + newLine + newLine);
                textArea.append("\t• уравнивание полигонов с различным способом привязки к " +
                        "опорной геодезической сети и получение координат точек съёмочной сети;" + newLine + newLine);
                textArea.append("\t• и другие.");
            }
        }
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setMargin(insets);
        textArea.setCaretPosition(0);
        return textArea;
    }

    /**
     * Gets content for pnlImport of ShowHelp
     * @return JTextArea
     */
    public JTextArea getImport() {
        JTextArea textArea = new JTextArea();
        switch (parentFrame.getOptions().getLanguage()) {
            case 0 -> {
                textArea.append("    Taheoport may to import data from measurement files into its own format and saved" +
                        " the data on disk in a file with the tah extension. In addition, it is possible to create a" +
                        " measurement file manually, using the capabilities of the measurement editor." + newLine + newLine);
                textArea.append("    To import measurements from the measurement file of your XXXX total station, go to the" +
                        " \"Measurements\" tab and select FILE – IMPORT – XXXX in the main menu of the program." +
                        " Using the suggested dialog, select a file on your computer's disk. If the import is successful," +
                        " Taheoport will offer to save the measurements on disk in its own format in a file with the tah extension." + newLine +newLine);
                textArea.append("    Using the capabilities of the measurement editor (the \"Measurements\" tab), append" +
                        " the received data set with the coordinates of stations and landmarks." + newLine + newLine);
                textArea.append("    Select the main menu item TOOLS – PROCESS (ADJUST) or click the corresponding button" +
                        " on the toolbar. The program will calculate the coordinates of the pickets and offer a standard" +
                        " dialog for saving a file with the tah extension." + newLine + newLine);
                textArea.append("    Select the menu item TOOLS – PROCESS AND VIEW or click the corresponding button on the toolbar." +
                        " The \"Data processing results\" window that opens has a toolbar and two tabs:" + newLine + newLine);
                textArea.append("\t• \"Coordinates catalog\" – shows the coordinates list of the pickets;" + newLine + newLine);
                textArea.append("\t• \"Coordinate calculation report\" – shows the calculation report (X, Y, Z) of the coordinates" +
                        " of the pickets obtained as a result of processing." + newLine + newLine);
                textArea.append("    Click the \"Save a report to disk\" button of the \"Data processing results\" window" +
                        " to save the contents of the active tab to a file." + newLine);

            }
            case 1 -> {
                textArea.append("    Taheoport импортирует данные из файлов измерений в собственный формат" +
                        " и сохраняет данные на диске в файле с расширением tah. Кроме того существует" +
                        " возможность создать файл измерений вручную, воспользовавшись возможностями  редактора измерений." + newLine + newLine);
                textArea.append("    Для импорта измерений из файла измерений Вашего ХХХХ тахеометра перейдите на вкладку \"Измерения\"" +
                        " и выберите в главном меню программы пункт ФАЙЛ – ИМПОРТ – ХХХХ. Используя предложенный диалог выберите файл на" +
                        " диске Вашего компьютера. В случае успешного импорта Taheoport предложит сохранить на диске " +
                        "измерения в собственном" +
                        " формате в файле с расширением tah. " + newLine + newLine);
                textArea.append("    Используя возможности редактора измерений (вкладка  \"Измерения\"), дополните полученный" +
                        " набор данных координатами станций и ориентиров." + newLine + newLine);
                textArea.append("    Выберите пункт главного меню ИНСТРУМЕНТЫ – ОБРАБОТАТЬ (УРАВНЯТЬ) или нажмите соответствующую кнопку" +
                        " на панели инструментов. Программа вычислит координаты пикетов и предложит стандартный диалог сохранения файла с расширением dat." + newLine + newLine);
                textArea.append("    Выберите пункт меню ИНСТРУМЕНТЫ – ОБРАБОТАТЬ И ПРОСМОТРЕТЬ или нажмите соответствующую кнопку на панели инструментов.");
                textArea.append("    Открывшееся окно \"Результаты обработки\" имеет панель инструментов и две вкладки:" + newLine + newLine);
                textArea.append("\t• \"Каталог координат\" – отображает каталог координат пикетов;" + newLine + newLine);
                textArea.append("\t• \"Ведомость вычисления координат\" – отображает ведомость вычисления (X, Y, Z) координат пикетов, полученных в результате обработки." + newLine + newLine);
                textArea.append("    Нажмите кнопку \"Сохранить на диске\" окна \"Результаты обработки\" чтобы сохранить в файл содержимое активной вкладки." + newLine);
            }
        }
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setMargin(insets);
        textArea.setCaretPosition(0);
        return textArea;
    }

    /**
     * Gets content for pnlExtract
     * @return JTextArea
     */
    public JTextArea getExtract() {
        JTextArea textArea = new JTextArea();
        switch (parentFrame.getOptions().getLanguage()) {
            case 0 -> {
                textArea.append("    Taheoport extracts the polygon from the measurement file," +
                        " if the first measurement record at each station is measurements at the back point of the polygon," +
                        " and the second is measurements at the front point. You can exclude a station from the polygon" +
                        " by making it with a special prefix (see Options). The program creates a polygon using the average" +
                        " values of horizontal distance and elevation calculated in the forward and reverse directions." + newLine + newLine);
                textArea.append("    In the Tayport model, a polygon is a list of stations, for each of which are defined:" + newLine + newLine);
                textArea.append("\t• station name;" + newLine + newLine);
                textArea.append("\t• horizontal angle between the back and front points of the polygon;" + newLine + newLine);
                textArea.append("\t• horizontal distance between the station and the front point;" + newLine + newLine);
                textArea.append("\t• elevation between the station and the front point;" + newLine + newLine);
                textArea.append("\t• coordinates X, Y, Z. *" + newLine + newLine);
                textArea.append("    * Only for basic polygon points." + newLine + newLine);
                textArea.append("    Open the measurement file (*.tah) containing the polygon on the \"Measurements\" tab or import" +
                        " it from the measurement file of your total station." + newLine + newLine);
                textArea.append("    Select the menu item TOOLS – EXTRACT POLYGON." + newLine + newLine);
                textArea.append("    The opened window \"Analysis of polygon extraction from the measurements\" has a toolbar" +
                        " and show a report about calculating the average values of horizontal distances and elevations obtained" +
                        " as a result of mathematical processing of measurements in the forward and reverse directions." + newLine + newLine);
                textArea.append("    Click the \"Save to Disk\" button of the \"Analysis of polygon extraction from the measurements\" window to save the contents of the window to a file." + newLine + newLine);
                textArea.append("    Using the capabilities of the polygon editor (the \"Polygon\" tab), append the polygon opened on the table with the coordinates of basic points." + newLine + newLine);
                textArea.append("    Select the menu item TOOLS – PROCESS (ADJUST) or click the corresponding button on the toolbar." + newLine);
                textArea.append("    The \"Adjustments results\" panel will show the values of the actual residuals obtained as a result of adjustment" +
                        " in accordance with your type of binding. The residuals that do not exceed the acceptable values will be green, else – red." + newLine + newLine);
                textArea.append("    Select the menu item TOOLS – PROCESS AND VIEW or click the corresponding button on the toolbar." + newLine);
                textArea.append("    The \"Polygon adjustment results\" window that opens has a toolbar and three tabs:" + newLine + newLine);
                textArea.append("\t• \"Coordinate catalog\" – shows the catalog of coordinates of polygon points;" + newLine + newLine);
                textArea.append("\t• \"Coordinate calculation report\" – showed the calculation report of coordinates (X, Y)" +
                        " polygon points and technical parameters of the polygon obtained as a result of adjustment;;" + newLine + newLine);
                textArea.append("\t• \"Heights calculation report\" – shows the height calculation report (Z) of polygon points and" +
                        " the technical parameters obtained as a result of adjustment." + newLine + newLine);
                textArea.append("    Click the \"Save a report to disk\" button of the \"Polygon adjustment results\" window" +
                        " to save the contents of the active tab to a file." + newLine + newLine);
                textArea.append("    The resulting file (*.kat), you can use as an external plug–in Catalog by selecting" +
                        " the menu item TOOLS - LOAD CATALOG or by clicking the corresponding button on the main toolbar." + newLine + newLine);
                textArea.append("    The reports of coordinates and heights contain information about the process and results" +
                        " of mathematical processing of the source data and can be used by you to report on the work performed." + newLine);

            }
            case 1 -> {
                textArea.append("    Taheoport извлекает полигон из файла измерений, если первая запись измерений на каждой станции," +
                        " это измерения на заднюю точку полигона, а вторая,  это измерения на переднюю точку. Исключить станцию из полигона можно пометив" +
                        " её специальным префиксом (см. настройки). Программа создаёт полигон, используя средние значения горизонтальных проложений и превышений," +
                        " вычисленных в прямом и обратном направлениях." + newLine + newLine);
                textArea.append("    В модели Taheoport полигон это упорядоченный набор станций, для каждой из которых определены:" + newLine + newLine);
                textArea.append("\t• название станции;" + newLine + newLine);
                textArea.append("\t• горизонтальный угол между задней и передней точками полигона;" + newLine + newLine);
                textArea.append("\t• горизонтальное проложение между станцией и передней точкой;" + newLine + newLine);
                textArea.append("\t• превышение между станцией и передней точкой;" + newLine + newLine);
                textArea.append("\t• координаты X, Y, Z. *" + newLine + newLine);
                textArea.append("    * Только для твёрдых пунктов полигона." + newLine + newLine);
                textArea.append("    Откройте файл измерений (*.tah), содержащий полигон на вкладке \"Измерения\" или импортируйте его из файла измерений Вашего тахеометра." + newLine + newLine);
                textArea.append("    Выберите пункт меню ИНСТРУМЕНТЫ – ИЗВЛЕЧЬ ПОЛИГОН." + newLine + newLine);
                textArea.append("    Открывшееся окно \"Анализ извлечения полигона из файла измерений\" имеет панель инструментов" +
                        " и отображает ведомость вычисления средних значений горизонтальных проложений и превышений, полученных" +
                        " в результате математической обработки измерений в прямом и обратном направлениях." + newLine + newLine);
                textArea.append("    Нажмите кнопку \"Сохранить на диске\" окна \"Анализ извлечения полигона из файла измерений\" чтобы сохранить в файл содержимое окна." + newLine + newLine);
                textArea.append("    Используя возможности редактора полигона (вкладка  \"Полигон\"), дополните открытый на вкладке полигон координатами твёрдых пунктов." + newLine + newLine);
                textArea.append("    Выберите пункт меню ИНСТРУМЕНТЫ – ОБРАБОТАТЬ (УРАВНЯТЬ) или нажмите соответствующую кнопку на панели инструментов." + newLine);
                textArea.append("    На панели \"Результаты уравнивания\" отобразятся величины полученных в результате" +
                        " математической обработки фактических невязок, в соответствии с Вашим типом привязки. Невязки," +
                        " не превышающие допустимых значений, будут зелёного цвета, иначе – красного." + newLine + newLine);
                textArea.append("    Выберите пункт меню ИНСТРУМЕНТЫ – ОБРАБОТАТЬ И ПРОСМОТРЕТЬ или нажмите соответствующую кнопку на панели инструментов." + newLine);
                textArea.append("    Открывшееся окно \"Результаты уравнивания полигона\" имеет панель инструментов и три вкладки:" + newLine + newLine);
                textArea.append("\t• \"Каталог координат\" – отображает каталог координат точек полигона;" + newLine + newLine);
                textArea.append("\t• \"Ведомость вычисления координат\" – отображает ведомость вычисления плановых (X, Y)" +
                        " координат точек полигона и технические параметры полигона, полученные в результате уравнивания;" + newLine + newLine);
                textArea.append("\t• \"Ведомость вычисления высот\" – отображает ведомость вычисления высот (Z) точек полигона" +
                        " и технические параметры, полученные в результате уравнивания." + newLine + newLine);
                textArea.append("    Нажмите кнопку \"Сохранить на диске\" окна \"Результаты уравнивания полигона\" чтобы сохранить в файл содержимое активной вкладки." + newLine + newLine);
                textArea.append("    Полученный таким образом каталог координат (*.kat), Вы можете использовать как внешний подключаемый каталог, выбрав пункт меню ИНСТРУМЕНТЫ" +
                        " – ЗАГРУЗИТЬ КАТАЛОГ или нажав соответствующую кнопку на главной панели инструментов." + newLine + newLine);
                textArea.append("    Ведомости координат и высот содержат информацию о процессе и результатах математической" +
                        " обработки исходных данных и могут быть использованы Вами для отчёта о выполненной работе." + newLine);

            }
        }

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setMargin(insets);
        textArea.setCaretPosition(0);
        return textArea;
    }

    /**
     * Gets content for pnlAdjustment
     * @return JTextArea
     */
    public JTextArea getAdjustment() {
        JTextArea textArea = new JTextArea();
        switch (parentFrame.getOptions().getLanguage()) {
            case 0 -> {
                textArea.append("    Taheoport adjust and calculates the accuracy of a single polygon that" +
                        " has one of the following types of binding to the basic network:" + newLine + newLine);
                textArea.append("\t• basic sides at the beginning and end of the polygon;" + newLine + newLine);
                textArea.append("\t• basic side at the beginning and a basic point at the end of the polygon;" + newLine + newLine);
                textArea.append("\t• basic point at the beginning and a basic side at the end of the polygon;" + newLine + newLine);
                textArea.append("\t• basic point at the beginning and a basic point at the end of the polygon;" + newLine + newLine);
                textArea.append("\t• basic side at the beginning of the polygon; *" + newLine + newLine);
                textArea.append("\t• basic side at the end of the polygon. *" + newLine + newLine);
                textArea.append("    Taheoport saves polygons in files with the extension \"pol\"." + newLine + newLine);
                textArea.append("    In the Tayport model, a polygon is a list of stations, for each of which are defined:" + newLine + newLine);
                textArea.append("\t• station name;" + newLine + newLine);
                textArea.append("\t• horizontal angle between the back and front points of the polygon;" + newLine + newLine);
                textArea.append("\t• horizontal distance between the station and the front point;" + newLine + newLine);
                textArea.append("\t• elevation between the station and the front point;" + newLine + newLine);
                textArea.append("\t• coordinates X, Y, Z. *" + newLine + newLine);
                textArea.append("    * Only for basic polygon points." + newLine + newLine);
                textArea.append("    To work with a polygon, go to the \"Polygon\" tab and do one of the following:" + newLine + newLine);
                textArea.append("\t• create a new polygon by selecting the FILE – CREATE menu item or by clicking on the corresponding button on the toolbar;" + newLine + newLine);
                textArea.append("\t• get the polygon from the measurement file by selecting the menu item TOOLS - EXTRACT POLYGON;" + newLine + newLine);
                textArea.append("\t• open the polygon you created earlier by selecting the FILE – OPEN menu item or by clicking" +
                        " the corresponding button on the toolbar." + newLine + newLine);
                textArea.append("    \"Polygon Tab\" section of this manual gives a detailed description" +
                        " of the tools for editing data and adding coordinates of basic points." + newLine + newLine);
                textArea.append("    Select the menu item TOOLS – PROCESS (ADJUST) or click the corresponding button on the toolbar." + newLine);
                textArea.append("    The \"Adjustments results\" panel will show the values of the actual residuals obtained as a result of adjustment" +
                        " in accordance with your type of binding. The residuals that do not exceed the acceptable values will be green, else – red." + newLine + newLine);
                textArea.append("    Select the menu item TOOLS – PROCESS AND VIEW or click the corresponding button on the toolbar." + newLine);
                textArea.append("    The \"Polygon adjustment results\" window that opens has a toolbar and three tabs:" + newLine + newLine);
                textArea.append("\t• \"Coordinate catalog\" – shows the catalog of coordinates of polygon points;" + newLine + newLine);
                textArea.append("\t• \"Coordinate calculation report\" – showed the calculation report of coordinates (X, Y)" +
                        " polygon points and technical parameters of the polygon obtained as a result of adjustment;;" + newLine + newLine);
                textArea.append("\t• \"Heights calculation report\" – shows the height calculation report (Z) of polygon points and" +
                        " the technical parameters obtained as a result of adjustment." + newLine + newLine);
                textArea.append("    Click the \"Save a report to disk\" button of the \"Polygon adjustment results\" window" +
                        " to save the contents of the active tab to a file." + newLine + newLine);
                textArea.append("    The resulting file (*.kat), you can use as an external plug–in Catalog by selecting" +
                        " the menu item TOOLS - LOAD CATALOG or by clicking the corresponding button on the main toolbar." + newLine + newLine);
                textArea.append("    The reports of coordinates and heights contain information about the process and results" +
                        " of mathematical processing of the source data and can be used by you to report on the work performed." + newLine);

            }
            case 1 -> {
                textArea.append("    Taheoport выполняет уравнивание и оценку точности одиночного полигона, имеющего одну из следующих типов привязки к опорной сети:" + newLine + newLine);
                textArea.append("\t• твёрдые стороны в начале и в конце полигона;" + newLine + newLine);
                textArea.append("\t• твёрдая сторона в начале и твёрдый пункт в конце полигона;" + newLine + newLine);
                textArea.append("\t• твёрдый пункт в начале и твёрдая сторона в конце полигона;" + newLine + newLine);
                textArea.append("\t• твёрдые пункт в начале и твёрдый пункт в конце полигона;" + newLine + newLine);
                textArea.append("\t• твёрдая сторона в начале полигона; *" + newLine + newLine);
                textArea.append("\t• твёрдая сторона в конце полигона. *" + newLine + newLine);
                textArea.append("    Taheoport сохраняет полигоны в файлах с расширением “pol”." + newLine + newLine);
                textArea.append("    В модели Taheoport полигон – упорядоченный набор (массив, перечень) станций, для каждой из которых определены:" + newLine + newLine);
                textArea.append("\t• название станции;" + newLine + newLine);
                textArea.append("\t• горизонтальный угол между задней и передней точками полигона;" + newLine + newLine);
                textArea.append("\t• горизонтальное проложение между станцией и передней точкой;" + newLine + newLine);
                textArea.append("\t• превышение между станцией и передней точкой;" + newLine + newLine);
                textArea.append("\t• координаты X, Y, Z. *" + newLine + newLine);
                textArea.append("    * Только для твёрдых пунктов полигона." + newLine + newLine);
                textArea.append("    Для работы с полигоном перейдите на вкладку \"Полигон\" и выполните одно из действий:" + newLine + newLine);
                textArea.append("\t• создайте новый полигон, выбрав пункт меню ФАЙЛ – СОЗДАТЬ или нажав на соответствующую кнопку на панели инструментов;" + newLine + newLine);
                textArea.append("\t• получите его из файла измерений, выбрав пункт меню ИНСТРУМЕНТЫ - ИЗВЛЕЧЬ ПОЛИГОН;" + newLine + newLine);
                textArea.append("\t• откройте созданный ранее полигон, выбрав пункт меню ФАЙЛ – ОТКРЫТЬ или нажав соответствующую кнопку на панели инструментов." + newLine + newLine);
                textArea.append("    В разделе \"Вкладка Полигон\" настоящего руководства дано подробное описание" +
                        " инструментов редактирования данных и добавления координат твёрдых точек." + newLine + newLine);
                textArea.append("    Выберите пункт меню ИНСТРУМЕНТЫ – ОБРАБОТАТЬ (УРАВНЯТЬ) или нажмите соответствующую кнопку на панели инструментов." + newLine);
                textArea.append("    На панели \"Результаты уравнивания\" отобразятся величины полученных в результате" +
                        " математической обработки фактических невязок, в соответствии с Вашим типом привязки. Невязки," +
                        " не превышающие допустимых значений, будут зелёного цвета, иначе – красного." + newLine + newLine);
                textArea.append("    Выберите пункт меню ИНСТРУМЕНТЫ – ОБРАБОТАТЬ И ПРОСМОТРЕТЬ или нажмите соответствующую кнопку на панели инструментов." + newLine);
                textArea.append("    Открывшееся окно \"Результаты уравнивания полигона\" имеет панель инструментов и три вкладки:" + newLine + newLine);
                textArea.append("\t• \"Каталог координат\" – отображает каталог координат точек полигона;" + newLine + newLine);
                textArea.append("\t• \"Ведомость вычисления координат\" – отображает ведомость вычисления плановых (X, Y)" +
                        " координат точек полигона и технические параметры полигона, полученные в результате уравнивания;" + newLine + newLine);
                textArea.append("\t• \"Ведомость вычисления высот\" – отображает ведомость вычисления высот (Z) точек полигона" +
                        " и технические параметры, полученные в результате уравнивания." + newLine + newLine);
                textArea.append("    Нажмите кнопку \"Сохранить на диске\" окна \"Результаты уравнивания полигона\" чтобы сохранить в файл содержимое активной вкладки." + newLine + newLine);
                textArea.append("    Полученный таким образом каталог координат (*.kat), Вы можете использовать как внешний подключаемый каталог, выбрав пункт меню ИНСТРУМЕНТЫ" +
                        " – ЗАГРУЗИТЬ КАТАЛОГ или нажав соответствующую кнопку на главной панели инструментов." + newLine + newLine);
                textArea.append("    Ведомости координат и высот содержат информацию о процессе и результатах математической" +
                        " обработки исходных данных и могут быть использованы Вами для отчёта о выполненной работе." + newLine);

            }
        }

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setMargin(insets);
        textArea.setCaretPosition(0);
        return textArea;
    }

    /**
     * Gets content for pnlMainMenu of ShowHelp
     * @return JTextArea
     */
    public JTextArea getMainMenu() {
        JTextArea textArea = new JTextArea();
        switch (parentFrame.getOptions().getLanguage()) {
            case 0 -> {
                textArea.append("    Actions related to the selection of menu items are determined by the active" +
                        " (user-selected) tab. When the program starts, the \"Measurements\" tab is active." + newLine + newLine);
                textArea.append("    The main menu of the Taheoport program consists of three items:" + newLine + newLine);
                textArea.append("    • \"File\" - contains items:" + newLine + newLine);
                textArea.append("\t\uF0FC \"Create\" - depending on the context, creates either a measurement project containing" +
                        " a set of one station and measurements for one picket with zero values, or a polygon project containing" +
                        " a set of one station with zero values." + newLine + newLine);
                textArea.append("\t\uF0FC \"Open\" - offers a standard dialog for searching and opening a file, the type of which" +
                        " depends on the context (*.tah or *.pol). Opens the file in the appropriate editor." + newLine + newLine);
                textArea.append("\t\uF0FC \"Import\" is available only when the \"Measurements\" tab is active and contains" +
                        " sub-items with the names of the types of electronic total stations available in the current version" +
                        " of the program. Selecting one of the sub-items offers a standard dialog for searching and opening a file," +
                        " the type of which depends on the context. Opens the file in the measurement editor on the \"Measurements\" tab." + newLine + newLine);
                textArea.append("\t\uF0FC \"Save\" - overwrites the file opened in the active tab or offers a standard file saving dialog," +
                        " the type of which depends on the context if the data is saved for the first time" + newLine + newLine);
                textArea.append("\t\uF0FC \"Save As\" - offers a standard dialog for saving a file, the type of which depends" +
                        " on the context (*.tah or *.pol). Saves data on a local computer or an external device in a file with" +
                        " the selected name." + newLine + newLine);
                textArea.append("\t\uF0FC \"Exit\" - completes the execution of the program without saving changes to the files opened on the tabs." + newLine + newLine);
                textArea.append("    •  \"Tools\" - contains items:" + newLine + newLine);
                textArea.append("\t\uF0FC \"Load catalog\" - offers a standard dialog for searching and opening a file of the" +
                        " \"Coordinate catalog\" type, a file with the cat extension. Creates a new catalog of coordinates" +
                        " basic points in the program memory, while deleting the old one, kill it with data from the selected" +
                        " file. The full path to the file is displayed in the text field on the toolbar.l" + newLine + newLine);
                textArea.append("\t\uF0FC \"Update coordinates\" - replaces the coordinates of stations and landmarks" +
                        " in the case of the active tab \"Measurements\" and basic stations, in the case of the active" +
                        " tab \"Polygon\" with the coordinates of the corresponding points of the installed coordinate" +
                        " catalog of basic points if the names match." + newLine + newLine);
                textArea.append("\t\uF0FC \"Process (Adjust)\" - performs mathematical data processing and offers a standard" +
                        " dialog for saving a file of type *.dat if the \"Measurements\" tab is active." +
                        " With the \"Polygon\" tab active, performs polygon adjustment and displays the values of the actual" +
                        " residuals on the \"Adjustment Results\" panel." + newLine + newLine);
                textArea.append("\t\uF0FC \"Process and view\" - performs mathematical data processing and opens a window with tabs" +
                        " containing the results in the form of catalogs and lists of coordinates and heights." + newLine + newLine);
                textArea.append("\t\uF0FC \"Extract Polygon\" - extracts a polygon from a measurement file opened in the" +
                        " \"Measurements\" tab. Opens a window with a report on calculating the average values of horizontal distances" +
                        " and elevations. Opens the resulting polygon on the \"Polygon\" tab." + newLine + newLine);
                textArea.append("\t\uF0FC \"Options\" - opens the \"Options\" window." + newLine + newLine);
                textArea.append("    • \"Help\" contains items:" + newLine + newLine);
                textArea.append("\t\uF0FC \"About\" - contains information about the installed version of the program and the text of the license agreement." + newLine + newLine);
                textArea.append("\t\uF0FC \"Help\" - opens the \"User Manual\" window for working with the built-in help system." +
                        " The window is not modal, that is, you can continue working with Taheoport and keep the \"User Manual\" open," +
                        " which is convenient when exploring the program's capabilities." + newLine);

            }
            case 1 -> {
                textArea.append("    Действия, связанные с выбором пунктов меню и их доступность определяются активной" +
                        " (выбранной пользователем) вкладкой. При запуске программы активна вкладка \"Измерения\"." + newLine + newLine);
                textArea.append("    Главное меню программы Taheoport состоит из трёх пунктов:" + newLine + newLine);
                textArea.append("    • \"Файл\" - содержит подпункты:" + newLine + newLine);
                textArea.append("\t\uF0FC \"Создать\" - в зависимости от контекста создаёт либо проект измерений," +
                        " содержащий набор из одной станции и измерениями на один пикет с нулевыми значениями," +
                        " либо проект полигона, содержащий набор из одной станции с нулевыми значениями. " + newLine + newLine);
                textArea.append("\t\uF0FC \"Открыть\" - предлагает стандартный диалог поиска и открытия файла," +
                        " тип которого зависит от контекста (*.tah или *.pol). Открывает файл в соответствующем редакторе." + newLine + newLine);
                textArea.append("\t\uF0FC \"Импорт\" - доступен только при активной вкладке \"Измерения\" и содержит подпункты" +
                        " с названиями доступных в текущей версии программы типов электронных тахеометров. Выбор одного из" +
                        " подпунктов предлагает стандартный диалог поиска и открытия файла, тип которого зависит от контекста." +
                        " Открывает файл в редакторе измерений на вкладке \"Измерения\"." + newLine + newLine);
                textArea.append("\t\uF0FC \"Сохранить\" - перезаписывает файл открытый в активной вкладке или предлагает" +
                        " стандартный диалог сохранения файла, тип которого зависит от контекста если данные сохраняются впервые." + newLine + newLine);
                textArea.append("\t\uF0FC \"Сохранить как\" - предлагает стандартный диалог сохранения файла," +
                        " тип которого зависит от контекста (*.tah или *.pol). Сохраняет данные на локальном диске" +
                        " или внешнем устройстве в файле с выбранным именем." + newLine + newLine);
                textArea.append("\t\uF0FC \"Выход\" - завершает выполнение программы, не сохраняя изменений в файлах, открытых на вкладках." + newLine + newLine);
                textArea.append("    •  \"Инструменты\" - содержит подпункты:" + newLine + newLine);
                textArea.append("\t\uF0FC \"Загрузить каталог\" - предлагает стандартный диалог поиска" +
                        " и открытия файла типа \"Каталог координат\", файла с расширением kat. Создаёт" +
                        " в памяти программы новый каталог координат опорных точек, удаляя при этом старый," +
                        " заполняет его данными из выбранного файла. Полный путь к файлу отображается" +
                        " в текстовом поле на панели инструментов." + newLine + newLine);
                textArea.append("\t\uF0FC \"Обновить координаты опорных точек\" - заменяет координаты станций" +
                        " и ориентиров в случае активной вкладки \"Измерения\" и твёрдых станций, в случае активной" +
                        " вкладки \"Полигон\" на координаты соответствующих точек установленного каталога координат" +
                        " опорных точек при совпадении названий." + newLine + newLine);
                textArea.append("\t\uF0FC \"Обработать (Уравнять)\" - выполняет математическую обработку данных" +
                        " и предлагает стандартный диалог сохранения файла типа *.dat если активна вкладка \"Измерения\"." +
                        " При активной вкладке \"Полигон\", выполняет уравнивание полигона и выводит на панель \"Результаты" +
                        " уравнивания\" значения фактических невязок." + newLine + newLine);
                textArea.append("\t\uF0FC \"Обработать и просмотреть\" - выполняет математическую обработку данных" +
                        " и открывает окно с вкладками, содержащими результаты в виде каталогов и ведомостей координат и высот." + newLine + newLine);
                textArea.append("\t\uF0FC \"Извлечь полигон\" - извлекает полигон из файла измерений, открытого во вкладке \"Измерения\"." +
                        " Открывает окно с отчётом о вычислении средних значений горизонтальных проложений и превышений. Открывает полученный" +
                        " полигон на вкладке \"Полигон\"." + newLine + newLine);
                textArea.append("\t\uF0FC \"Настройки\" - открывает окно \"Настройки\"." + newLine + newLine);
                textArea.append("    • \"Помощь\" содержит подпункты:" + newLine + newLine);
                textArea.append("\t\uF0FC \"О программе\" - содержит информацию об установленной версии программы и текст лицензионного соглашения." + newLine + newLine);
                textArea.append("\t\uF0FC \"Помощь\" - открывает окно \"Руководство пользователя\" для работы со встроенной справочной системой." +
                        " Окно не является модальным, то есть Вы можете продолжать работать с Taheoport и держать открытым \"Руководство пользователя\"," +
                        " что удобно при изучении возможностей программы." + newLine);
            }
        }

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setMargin(insets);
        textArea.setCaretPosition(0);
        return textArea;

    }

    /**
     * Gets content for pnlToolbarDemo start position of ShowHelp
     * @return JTextArea
     */
    public JTextArea getToolbarDemo() {
        JTextArea textArea = new JTextArea();
        switch (parentFrame.getOptions().getLanguage()) {
            case 0 -> {
                textArea.append("    The toolbar of the main window of the Taheoport program contains buttons" +
                        " duplicating the actions of the corresponding main menu items and a text field displaying" +
                        " the path to the Coordinates Catalog, if it is loaded into the program memory." + newLine + newLine);
                textArea.append("    Click on the toolbar button you are interested in, located above, to display information about its purpose.");

            }
            case 1 -> {
                textArea.append("    Панель инструментов главного окна программы Taheoport содержит кнопки," +
                        " дублирующие действия  соответствующих пунктов главного меню и текстовое поле отображающее" +
                        " путь к каталогу координат, если он загружен в память программы." + newLine + newLine);
                textArea.append("    Нажмите на интересующую Вас кнопку панели инструментов, расположенную выше," +
                        " для отображения информации о её назначении.");
            }
        }

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setMargin(insets);
        textArea.setCaretPosition(0);
        return textArea;

    }

    /**
     * Gets content for pnlToolbarDemo of New Button
     * @return JTextArea
     */
    public JTextArea getToolbarNew () {
        JTextArea textArea = new JTextArea();
        switch (parentFrame.getOptions().getLanguage()) {
            case 0 -> {
                textArea.append("    Clicking the button is similar to selecting the FILE - CREATE menu item and depends on the selected tab:" + newLine + newLine);
                textArea.append("\t• \"Measurements\" - creates a measurement project containing a set of one station and measurements for one picket with zero values;" + newLine + newLine);
                textArea.append("\t• \"Polygon\" - creates a measurement project, a polygon containing a set of one station with zero values." + newLine + newLine);

            }
            case 1 -> {
                textArea.append("    Нажатие кнопки аналогично выбору пункта меню ФАЙЛ - СОЗДАТЬ и зависит от выбранной вкладки:" + newLine + newLine);
                textArea.append("\t• \"Измерения\" - создаёт проект измерений, содержащий набор из одной станции и измерениями на один пикет с нулевыми значениями;" + newLine + newLine);
                textArea.append("\t• \"Полигон\" - создаёт проект измерений, полигона, содержащий набор из одной станции с нулевыми значениями." + newLine + newLine);

            }
        }

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setMargin(insets);
        textArea.setCaretPosition(0);
        return textArea;
    }

    /**
     * Gets content for pnlToolbarDemo of Open Button
     * @return JTextArea
     */
    public JTextArea getToolbarOpen () {
        JTextArea textArea = new JTextArea();
        switch (parentFrame.getOptions().getLanguage()) {
            case 0 -> {
                textArea.append("    Clicking the button is similar to selecting the FILE - OPEN menu item and depends on the selected tab:" + newLine + newLine);
                textArea.append("\t• \"Measurements\" - offers a standard dialog for searching and opening a measurement file (*.tah). Opens the file in the measurements editor;" + newLine + newLine);
                textArea.append("\t• \"Polygon\" - offers a standard dialog for searching and opening a polygon file (*.pol). Opens the file in the polygon editor." + newLine + newLine);

            }
            case 1 -> {
                textArea.append("    Нажатие этой кнопки аналогично выбору пункта меню ФАЙЛ - ОТКРЫТЬ и зависит от выбранной вкладки:" + newLine + newLine);
                textArea.append("\t• \"Измерения\" - предлагает стандартный диалог для поиска и открытия файла измерений (*.tah). Открывает файл в редакторе измерений;" + newLine + newLine);
                textArea.append("\t• \"Полигон\" - предлагает стандартный диалог для поиска и открытия файла полигона (*.pol). Открывает файл в редакторе полигона." + newLine + newLine);
            }
        }

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setMargin(insets);
        textArea.setCaretPosition(0);
        return textArea;
    }

    /**
     * Gets content for pnlToolbarDemo of Open Button
     * @return JTextArea
     */
    public JTextArea getToolbarImport () {
        JTextArea textArea = new JTextArea();
        switch (parentFrame.getOptions().getLanguage()) {
            case 0 -> {
                textArea.append("    Clicking the button is similar to selecting the FILE - IMPORT. Is available only when the \"Measurements\" tab is active" + newLine + newLine);
            }
            case 1 -> {
                textArea.append("    Нажатие этой кнопки аналогично выбору пункта меню ФАЙЛ - ИМПОРТ. Доступна только при активной вкладке \"Измерения\"." + newLine + newLine);
            }
        }

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setMargin(insets);
        textArea.setCaretPosition(0);
        return textArea;
    }

    /**
     * Gets content for pnlToolbarDemo of Save Button
     * @return JTextArea
     */
    public JTextArea getToolbarSave () {
        JTextArea textArea = new JTextArea();
        switch (parentFrame.getOptions().getLanguage()) {
            case 0 -> {
                textArea.append("    Clicking the button is similar to selecting the FILE - SAVE menu item" +
                        " and depends on the selected tab:" + newLine + newLine);
                textArea.append("\t• \"Measurements\" - overwrites an open measurement file or offers a standard file" +
                        " saving dialog (*.tah) if the data is saved for the first time;" + newLine + newLine);
                textArea.append("\t• \"Polygon\" - overwrites an open polygon file or offers a standard file" +
                        " saving dialog (*.pol) if the data is saved for the first time." + newLine + newLine);

            }
            case 1 -> {
                textArea.append("    Нажатие кнопки аналогично выбору пункта меню ФАЙЛ - СОХРАНИТЬ" +
                        " и зависит от выбранной вкладки:" + newLine + newLine);
                textArea.append("\t• \"Измерения\" - перезаписывает открытый файл измерений или предлагает стандартный диалог" +
                        " сохранения файла (*.tah), если данные сохраняются впервые;" + newLine + newLine);
                textArea.append("\t• \"Полигон\" - перезаписывает открытый файл полигона или предлагает стандартный" +
                        " диалог сохранения файла (*.pol), если данные сохраняются впервые." + newLine + newLine);
            }
        }

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setMargin(insets);
        textArea.setCaretPosition(0);
        return textArea;
    }

    /**
     * Gets content for pnlToolbarDemo of Run Button
     * @return JTextArea
     */
    public JTextArea getToolbarRun () {
        JTextArea textArea = new JTextArea();
        switch (parentFrame.getOptions().getLanguage()) {
            case 0 -> {
                textArea.append("    Clicking the button is similar to selecting the menu item TOOLS - PROCESS (EQUALIZE) and depends on the selected tab:" + newLine + newLine);
                textArea.append("\t• \"Measurements\" - performs mathematical data processing and offers a standard dialog for saving" +
                        " the file of the picket directory (*.dat);" + newLine + newLine);
                textArea.append("\t• \"Polygon\" - performs the adjustment of the polygon and displays" +
                        " the values of the actual residuals on the \"Adjustment Results\" panel.");

            }
            case 1 -> {
                textArea.append("    Нажатие кнопки аналогично выбору пункта меню ИНСТРУМЕНТЫ - ОБРАБОТАТЬ (УРАВНЯТЬ) и зависит от выбранной вкладки:" + newLine + newLine);
                textArea.append("\t• \"Измерения\" - выполняет математическую обработку данных и предлагает стандартный диалог сохранения" +
                        " файла каталога пикетов (*.dat);" + newLine + newLine);
                textArea.append("\t• \"Полигон\" - выполняет уравнивание полигона и выводит на панель \"Результаты уравнивания\" значения фактических невязок.");

            }
        }

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setMargin(insets);
        textArea.setCaretPosition(0);
        return textArea;
    }


    /**
     * Gets content for pnlToolbarDemo of View Button
     * @return JTextArea
     */
    public JTextArea getToolbarView () {
        JTextArea textArea = new JTextArea();
        switch (parentFrame.getOptions().getLanguage()) {
            case 0 -> {
                textArea.append("    Clicking the button is similar to selecting the menu item TOOLS - PROCESS AND VIEW and depends" +
                        " on the selected tab:" + newLine + newLine);
                textArea.append("\t• \"Measurements\" - performs mathematical data processing and opens the \"Processing results\" window," +
                        " which has a toolbar and two tabs:" + newLine + newLine);
                textArea.append("\t\t\uF0FC \"Coordinates catalog\" – displays the coordinates catalog of the pickets;" + newLine + newLine);
                textArea.append("\t\t\uF0FC \"Coordinates calculation report\" – displays the calculation report (X, Y, Z)" +
                        " of the coordinates of the pickets obtained as a result of processing." + newLine + newLine);
                textArea.append("    Click the \"Save to Disk\" button of the \"Processing results\" window to save the contents of the active tab to a file." + newLine + newLine);
                textArea.append("\t• \"Polygon\" - performs polygon adjustment and opens the \"Polygon Adjustment Results\" window, which has a toolbar and three tabs:" + newLine + newLine);
                textArea.append("\t\t\uF0FC \"Coordinate catalog\" – displays the catalog of coordinates of polygon points;" + newLine + newLine);
                textArea.append("\t\t\uF0FC \"Coordinate calculation report\" – displays the calculation report of the (X, Y)" +
                        "  coordinates of the polygon points and the technical parameters of the polygon obtained as a result of adjustment;" + newLine + newLine);
                textArea.append("\t\t\uF0FC \"Height calculation report – displays the height calculation report (Z) of polygon" +
                        " points and the technical parameters obtained as a result of adjustment." + newLine + newLine);
                textArea.append("    Click the \"Save to Disk\" button of the \"Polygon Adjustment Results\" window to save the contents of the active tab to a file." + newLine + newLine);
                textArea.append("    The resulting coordinate catalog (*.kat), you can use as an external plug–in directory" +
                        " by selecting the menu item TOOLS - UPLOAD DIRECTORY or by clicking the corresponding button on the main toolbar." + newLine + newLine);
                textArea.append("    The reports of coordinates and heights contain information about the process" +
                        " and results of mathematical processing of the source data and can be used by you to report on the work performed." + newLine + newLine);

            }
            case 1 -> {
                textArea.append("    Нажатие кнопки аналогично выбору пункта меню ИНСТРУМЕНТЫ - ОБРАБОТАТЬ И ПРОСМОТРЕТЬ и зависит от выбранной вкладки:" + newLine + newLine);
                textArea.append("\t• \"Измерения\" - выполняет математическую обработку данных и открывает окно \"Результаты обработки\"," +
                        " которое имеет панель инструментов и две вкладки:" + newLine + newLine);
                textArea.append("\t\t\uF0FC \"Каталог координат\" – отображает каталог координат пикетов;" + newLine + newLine);
                textArea.append("\t\t\uF0FC \"Ведомость вычисления координат\" – отображает ведомость вычисления (X, Y, Z) координат пикетов," +
                        " полученных в результате обработки." + newLine + newLine);
                textArea.append("    Нажмите кнопку \"Сохранить на диске\" окна \"Результаты обработки\" чтобы сохранить в файл содержимое активной вкладки." + newLine + newLine);
                textArea.append("\t• \"Полигон\" - выполняет уравнивание полигона и открывает окно \"Результаты уравнивания полигона\"," +
                        " которое имеет панель инструментов и три вкладки:" + newLine + newLine);
                textArea.append("\t\t\uF0FC \"Каталог координат\" – отображает каталог координат точек полигона;" + newLine + newLine);
                textArea.append("\t\t\uF0FC \"Ведомость вычисления координат\" – отображает ведомость вычисления плановых (X, Y)" +
                        " координат точек полигона и технические параметры полигона, полученные в результате уравнивания;" + newLine + newLine);
                textArea.append("\t\t\uF0FC \"Ведомость вычисления высот\" – отображает ведомость вычисления высот (Z) точек полигона" +
                        " и технические параметры, полученные в результате уравнивания." + newLine + newLine);
                textArea.append("    Нажмите кнопку \"Сохранить на диске\" окна \"Результаты уравнивания полигона\" чтобы сохранить в файл содержимое активной вкладки." + newLine + newLine);
                textArea.append("    Полученный таким образом каталог координат (*.kat), Вы можете использовать" +
                        " как внешний подключаемый каталог, выбрав пункт меню ИНСТРУМЕНТЫ" +
                        " ЗАГРУЗИТЬ КАТАЛОГ или нажав соответствующую кнопку на главной панели инструментов." + newLine + newLine);
                textArea.append("    Ведомости координат и высот содержат информацию о процессе и результатах математической" +
                        " обработки исходных данных и могут быть использованы Вами для отчёта о выполненной работе." + newLine + newLine);

            }
        }

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setMargin(insets);
        textArea.setCaretPosition(0);
        return textArea;
    }


    /**
     * Gets content for pnlToolbarDemo of View Button
     * @return JTextArea
     */
    public JTextArea getToolbarLoadCat() {
        JTextArea textArea = new JTextArea();
        switch (parentFrame.getOptions().getLanguage()) {
            case 0 -> {
                textArea.append("Clicking the button is similar to selecting the TOOLS - UPLOAD CATALOG menu item." + newLine + newLine);
                textArea.append("    It offers a standard dialog for searching and opening" +
                        " a file of the \"Coordinates Catalog\" type, a file with the cat extension." +
                        " Creates a new directory of reference point coordinates in the program memory," +
                        " while deleting the old one, fill it with data from the selected file." +
                        " The full path to the file is displayed in the text field on the toolbar.");

            }
            case 1 -> {
                textArea.append("Нажатие кнопки аналогично выбору пункта меню ИНСТРУМЕНТЫ - ЗАГРУЗИТЬ КАТАЛОГ." + newLine + newLine);
                textArea.append("    Предлагает стандартный диалог поиска и открытия файла типа \"Каталог координат\"," +
                        " файла с расширением kat. Создаёт в памяти программы новый каталог координат опорных точек," +
                        " удаляя при этом старый, заполняет его данными из выбранного файла. Полный путь к файлу" +
                        " отображается в текстовом поле на панели инструментов.");
            }
        }

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setMargin(insets);
        textArea.setCaretPosition(0);
        return textArea;
    }


    /**
     * Gets content for pnlMeasurements of ShowHelp
     * @return JTextArea
     */
    public JTextArea getMeasurements() {
        JTextArea textArea = new JTextArea();
        switch (parentFrame.getOptions().getLanguage()) {
            case 0 -> {
                textArea.append("    The \"Measurements\" tab is designed for creating and/or editing a measurement file and consists of three panels:" + newLine + newLine);
                textArea.append("    • \"Station parameters\" - includes text fields and buttons for editing station parameters:" + newLine + newLine);
                textArea.append("\t\uF0FC The name of the station. *" + newLine + newLine);
                textArea.append("\t\uF0FC X coordinate of the station. **" + newLine + newLine);
                textArea.append("\t\uF0FC Y coordinate of the station. **" + newLine + newLine);
                textArea.append("\t\uF0FC Z coordinate of the station. **" + newLine + newLine);
                textArea.append("\t\uF0FC Tool height. **" + newLine + newLine);
                textArea.append("\t\uF0FC The name of the orientir. *" + newLine + newLine);
                textArea.append("\t\uF0FC X coordinate of the orientir. **" + newLine + newLine);
                textArea.append("\t\uF0FC Y coordinate of the orientir. **" + newLine + newLine);
                textArea.append("\t\uF0FC \"Station name\" is a button that opens a window with a coordinates catalog." +
                        " Allows you to insert station coordinates from the catalog. Active if a catalog of" +
                        " basic point coordinates is loaded into the program memory." + newLine + newLine);
                textArea.append("\t\uF0FC \"Orientir name\" is a button that opens a window with a directory of coordinates." +
                        " Allows you to insert the coordinates of a back point from the catalog. Active if a catalog of" +
                        " basic point coordinates is loaded into the program memory." + newLine + newLine);
                textArea.append("    * Any characters except space and \"/\" are allowed." + newLine + newLine);
                textArea.append("    ** Only numeric characters are allowed. To separate the decimal part, the characters \".\" and \",\" are allowed." + newLine + newLine);
                textArea.append("    • \"Station list\" - has a toolbar for deleting existing and inserting new, empty stations." +
                        " The list is used to select the current station displayed on the tab for viewing and editing." + newLine + newLine);
                textArea.append("    • \"Measurement results\" - the table displays measurements on survey pickets" +
                        " made from the current station and consists of the following columns:" + newLine + newLine);
                textArea.append("\t\uF0FC \"Name\" – the name (code) of the picket. *" + newLine + newLine);
                textArea.append("\t\uF0FC \"Distance\" – the inclined distance from the station to the picket, m. **" + newLine + newLine);
                textArea.append("\t\uF0FC \"Direction\" – horizontal direction to the picket, in the format D.MMSS **" + newLine + newLine);
                textArea.append("\t\uF0FC \"TiltAngle\" – the angle of inclination of the measured line, in the format D.MMSS. **" + newLine + newLine);
                textArea.append("\t\uF0FC \"TargetHeight\" – the height of the target (reflector) above the picket, m. **" + newLine + newLine);
                textArea.append("    * Any characters except space and \"/\" are allowed." + newLine + newLine);
                textArea.append("    ** Only numeric characters are allowed. To separate the decimal part, the characters \".\" and \",\" are allowed." + newLine + newLine);
                textArea.append("    The toolbar provides additional tools for editing the contents of the \"Measurement results\" table:" + newLine + newLine);
                textArea.append("\t\uF0FC \"Delete selected row\" - deletes a row from the \"Measurement results\" table;" + newLine + newLine);
                textArea.append("\t\uF0FC \"Insert a line before the selected\" - adds an empty row to the \"Measurement results\" table before the selected row;" + newLine + newLine);
                textArea.append("\t\uF0FC \"Insert a line after the selected\" - adds an empty row to the \"Measurement results\" table after the selected row;" + newLine + newLine);
                textArea.append("\t\uF0FC \"Change distance\" - opens the \"Changing distance\" window. Adds a linear offset" +
                        " to the \"Distance\" column in the selected row." + newLine);
                textArea.append("    The \"Changing distance\" window is designed to enter the value of the offset and provides" +
                        " the user with one of the modes for adding corrections to the distance:" + newLine);
                textArea.append("\t\uF0FC \"Inclined\" - the value entered in the text field is added to the distance" +
                        " in the \"Measurement results\" table;" + newLine);
                textArea.append("\t\uF0FC \"Horizontal\" - the value entered in the text field is adjusted taking into account" +
                        " the angle of inclination of the line before being added to the distance in the \"Measurement results\" table;" + newLine + newLine);

                textArea.append("\t\uF0FC \"Change direction\" - changes the direction values in the selected row according" +
                        " to the choice made in the window \"Changing direction\"" + newLine);
                textArea.append("    The \"Changing direction\" window - provides the user with a choice of one of the options:" + newLine);
                textArea.append("\t\"Copy from next row\" - the direction value in the current line is replaced by the direction value from the next line;" + newLine);
                textArea.append("\t\"Add offset to angle\" - the offset entered by the user in the format G.MMSS" +
                        " is added to the direction value in the highlighted line." + newLine + newLine);
                textArea.append("\t\uF0FC \"Change tilt\" - changes the value of the tilt angle and adjusts" +
                        " the distance in the selected row according to the selection made in the window \"Changing the tilt of line\"" + newLine);
                textArea.append("    The \"Changing the tilt of line\" window provides the user with a choice of one of the options:" + newLine);
                textArea.append("\t\"Copy from next row\" - the value of the tilt angle  in the selected row is replaced" +
                        " by the value of the tilt angle from the next line. The distance value is corrected according to the new tilt angle of the line;" + newLine);
                textArea.append("\t\"Add offset to angle\" - The offset entered by the user in the format D.MMSS  is added" +
                        " to the value of the tilt angle in the selected line. The distance value is corrected according" +
                        " to the new tilt angle  of the line. The target height value is set to \"0.000\"." + newLine);


            }
            case 1 -> {
                textArea.append("    Вкладка \"Измерения\" предназначена для создания и (или) редактирования файла измерений и состоит из трёх панелей:" + newLine + newLine);
                textArea.append("    • \"Параметры станции\" - включает текстовые поля и кнопки для редактирования параметров станции:" + newLine + newLine);
                textArea.append("\t\uF0FC Название станции. *" + newLine + newLine);
                textArea.append("\t\uF0FC X координата станции. **" + newLine + newLine);
                textArea.append("\t\uF0FC Y координата станции. **" + newLine + newLine);
                textArea.append("\t\uF0FC Z координата станции. **" + newLine + newLine);
                textArea.append("\t\uF0FC Высота инструмента. **" + newLine + newLine);
                textArea.append("\t\uF0FC Название ориентира. *" + newLine + newLine);
                textArea.append("\t\uF0FC X координата ориентира. **" + newLine + newLine);
                textArea.append("\t\uF0FC Y координата ориентира. **" + newLine + newLine);
                textArea.append("\t\uF0FC \"Название станции\" - кнопка, открывающая окно с каталогом координат." +
                        " Позволяет вставить координаты станции из каталога. Активна, если в память программы" +
                        " загружен каталог координат опорных точек." + newLine + newLine);
                textArea.append("\t\uF0FC \"Название ориентира\" - кнопка, открывающая окно с каталогом координат." +
                        " Позволяет вставить координаты ориентира из каталога. Активна, если в память программы" +
                        " загружен каталог координат опорных точек." + newLine + newLine);
                textArea.append("    * Допустимы любые символы, кроме пробела и \"/\"." + newLine + newLine);
                textArea.append("    ** Допустимы только цифровые символы. Для разделения десятичной части допустимы символы \".\" и \",\"." + newLine + newLine);
                textArea.append("    • \"Список станций\" - имеет панель инструментов для удаления существующих и вставки новых, пустых станций." +
                        " Список служит для выбора текущей, отображаемой на вкладке станции для просмотра и редактирования. " + newLine + newLine);
                textArea.append("    •  \"Результаты измерений\" - таблица отображает измерения на съёмочные пикеты," +
                        " выполненные с текущей станции и состоит из следующих колонок:" + newLine + newLine);
                textArea.append("\t\uF0FC \"Название\" – название (код) пикета. *" + newLine + newLine);
                textArea.append("\t\uF0FC \"Расстояние\" – наклонное расстояние от станции до пикета, м. **" + newLine + newLine);
                textArea.append("\t\uF0FC \"Направление\" – горизонтальное направление на пикет, в формате D.MMSS **" + newLine + newLine);
                textArea.append("\t\uF0FC \"Уг.Наклона\" – угол наклона измеренной линии, в формате D.MMSS. **" + newLine + newLine);
                textArea.append("\t\uF0FC \"Выс.Цели\" – высота цели (отражателя) над пикетом, м. **" + newLine + newLine);
                textArea.append("    * Допустимы любые символы, кроме пробела и \"/\"." + newLine);
                textArea.append("    ** Допустимы только цифровые символы. Для разделения десятичной части допустимы символы \".\" и \",\"." + newLine);
                textArea.append("    Панель инструментов предоставляет дополнительные средства редактирования содержимого таблицы \"Результаты измерений\":" + newLine + newLine);
                textArea.append("\t\uF0FC \"Удалить выделенную строку\" - удаляет строку из таблицы \"Результаты измерений\";" + newLine + newLine);
                textArea.append("\t\uF0FC \"Вставить строку перед выделенной\" - добавляет пустую строку в таблицу \"Результаты измерений\" перед выделенной;" + newLine + newLine);
                textArea.append("\t\uF0FC \"Вставить строку после выделенной\" - добавляет пустую строку в таблицу \"Результаты измерений\" после выделенной;" + newLine + newLine);
                textArea.append("\t\uF0FC \"Изменить расстояние\" - открывает окно \"Изменение расстояния\"." +
                        " Добавляет линейное смещение в колонку \"Расстояние\" в выделенной строке ." + newLine);
                textArea.append("    Окно \"Изменение расстояния\" предназначено для ввода величины смещения и предоставляет" +
                        " пользователю один из вариантов добавления поправки в расстояние:" + newLine);
                textArea.append("\t\"Наклонное\" - значение, введённое в текстовое поле добавляется к расстоянию в таблице измерений;" + newLine);
                textArea.append("\t\"Горизонтальное\" - значение, введённое в текстовое поле корректируется" +
                        " с учётом угла наклона линии перед добавлением к расстоянию в таблице измерений;" + newLine + newLine);
                textArea.append("\t\uF0FC \"Изменить направление\" - изменяет значение направления в выделенной строке в соответствии с выбором," +
                        " сделанным в окне \"Изменение направления\"" + newLine);
                textArea.append("    Окно \"Изменение направления\" предоставляет пользователю выбор одного из вариантов:" + newLine);
                textArea.append("\t\"Копировать из следующей строки\" - значение направления в выделенной строке заменяется значением направления из следующей строки.;" + newLine);
                textArea.append("\t\"Добавить смещение в угол\" - к значению направления в выделенной строке добавляется введённое пользователем смещение в формате Г.ММСС;" + newLine + newLine);
                textArea.append("\t\uF0FC \"Изменить наклон\" - изменяет значение угла наклона и кооректирует расстояние" +
                        " в выделенной строке в соответствии с выбором, сделенным в окне \"Изменение наклона\"" + newLine);
                textArea.append("    Окно \"Изменение наклона\" предоставляет пользователю выбор одного из вариантов:" + newLine);
                textArea.append("\t\"Копировать из следующей строки\" - значение угла наклона в выделенной строке заменяется" +
                        " значением угла наклона из следующей строки. Значение расстояния корректируется в соответствии с новым углом наклона линии. Значение высоты цели устанавливается равным \"0.000\".;" + newLine);
                textArea.append("\t\"Добавить смещение в угол\" - к значению угла наклона в выделенной строке добавляется" +
                        " введённое пользователем смещение в формате Г.ММСС. Значение расстояния корректируется в соответствии с новым углом наклона линии." + newLine);

            }
        }


        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setMargin(insets);
        textArea.setCaretPosition(0);
        return textArea;

    }

    /**
     * Gets content for pnlPolygon of ShowHelp
     * @return JTextArea
     */
    public JTextArea getPolygon() {
        JTextArea textArea = new JTextArea();
        switch (parentFrame.getOptions().getLanguage()) {
            case 0 -> {
                textArea.append("    The \"Polygon\" tab is designed for creating and/or editing a polygon and consists of three main components:" + newLine + newLine);
                textArea.append("    • Data table – has a toolbar for deleting existing and inserting new, empty fields into the measurement table," +
                        " inserting coordinates from the catalog loaded into the program memory. The table consists of the following columns:" + newLine + newLine);
                textArea.append("\t\uF0FC Name – the name of the station. *" + newLine + newLine);
                textArea.append("\t\uF0FC Hor.Angle – the horizontal angle between the back and front points of the polygon, D.MMSS. **" + newLine + newLine);
                textArea.append("\t\uF0FC Hor.Distance – horizontal distance between the station and the front point of the polygon, m. **" + newLine + newLine);
                textArea.append("\t\uF0FC Elevation – the elevation between the station and the front point of the polygon, m. **" + newLine + newLine);
                textArea.append("\t\uF0FC X, Y, Z – coordinates of the station, m. Editing is possible if the point is basic." +
                        " These fields can be filled in by inserting coordinates from coordinates catalog loaded into the" +
                        " program memory or using the TOOLS - UPDATE COORDINATES. **" + newLine + newLine);
                textArea.append("\t\uF0FC Basis – a switch that sets or cancels the basic status of the station." + newLine + newLine);
                textArea.append("    * Any characters except space and \"/\" are allowed." + newLine + newLine);
                textArea.append("    ** Only numeric characters are allowed. To separate the decimal part, the characters \".\" and \",\" are allowed." + newLine + newLine);
                textArea.append("    • Panel \"Adjustments results\" – contains information about the technical characteristics" +
                        " of the polygon. Updated after the polygon is adjust. The values of the actual deviations of the polygon" +
                        " are displayed in green if they do not exceed the permissible values, which can be set or changed by selecting the main menu item TOOLS – OPTIONS." + newLine + newLine);
                textArea.append("    • The view panel displays a polygon at an arbitrary scale, oriented to the North. Updated after the polygon is adjust." + newLine + newLine);

            }
            case 1 -> {
                textArea.append("    Вкладка \"Полигон\" предназначена для создания и (или) редактирования полигона и состоит из трёх основных компонентов:" + newLine + newLine);
                textArea.append("    • Таблица данных – имеет панель инструментов для удаления существующих и вставки новых, пустых полей в таблицу измерений," +
                        " вставки координат из загруженного в память программы каталога. Таблица состоит из следующих колонок:" + newLine + newLine);
                textArea.append("\t\uF0FC Название – название станции. *" + newLine + newLine);
                textArea.append("\t\uF0FC Гор.Угол – горизонтальный угол между задней и передней точками полигона, D.MMSS. **" + newLine + newLine);
                textArea.append("\t\uF0FC Гор.Длина – горизонтальное проложение между станцией и передней точкой полигона, м. **" + newLine + newLine);
                textArea.append("\t\uF0FC Превышение – превышение между станцией и передней точкой полигона, м. **" + newLine + newLine);
                textArea.append("\t\uF0FC X, Y, Z – координаты станции. Возможно редактирование, если точка является твёрдой," +
                        " м. Эти поля можно заполнить, вставив координаты из каталога координат опорных точек, загруженного" +
                        " в память программы или воспользоваться пунктом главного меню ИНСТРУМЕНТЫ – ОБНОВИТЬ КООРДИНАТЫ ОПОРНЫХ ТОЧЕК. **" + newLine + newLine);
                textArea.append("\t\uF0FC Опора – переключатель, устанавливающий или отменяющий твёрдый статус станции." + newLine + newLine);
                textArea.append("    * Допустимы любые символы, кроме пробела и \"/\"." + newLine + newLine);
                textArea.append("    ** Допустимы только цифровые символы. Для разделения десятичной части допустимы символы \".\" и \",\"." + newLine + newLine);
                textArea.append("    • Панель \"Результаты уравнивания\" – содержит информацию о технических характеристиках полигона." +
                        " Обновляется после уравнивания полигона. Величины фактических невязок полигона отображаются зелёным цветом" +
                        " если не превышают допустимых величин, установить или изменить которые можно выбрав пункт главного меню" +
                        " ИНСТРУМЕНТЫ – НАСТРОЙКИ." + newLine + newLine);
                textArea.append("    • Панель просмотра отображает схему хода в произвольном масштабе, ориентированную на север. " +
                        " Обновляется после уравнивания полигона." + newLine + newLine);
            }
        }
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setMargin(insets);
        textArea.setCaretPosition(0);
        return textArea;

    }

    /**
     * Gets content for pnlOptions of ShowHelp
     * @return JTextArea
     */
    public JTextArea getSettings() {
        JTextArea textArea = new JTextArea();
        switch (parentFrame.getOptions().getLanguage()) {
            case 0 -> {
                textArea.append("    The \"Settings\" window contains the \"General\" and \"Deviations\" tabs." + newLine + newLine);
                textArea.append("    The \"General\" tab includes the following components:" + newLine + newLine);
                textArea.append("    • \"Language\" - sets the interface language selected by the user." + newLine + newLine);
                textArea.append("    • \"Working folder\" is a panel containing a text field displaying the path to the working" +
                        " folder and a button that opens a standard dialog for selecting a local folder. The path to the working" +
                        " folder is used in standard dialogs for opening and saving files as the default folder." + newLine + newLine);
                textArea.append("    • \"Orientation of the total station\" is a group of switches that sets how to determine" +
                        " the horizontal angle on the picket in the measurement table on the \"Measurements\" tab:" + newLine + newLine);
                textArea.append("\t\uF0FC \"Zero total station on the Orientir\" – the tool is oriented to a Orientir" +
                        " with known coordinates, the value in the \"Direction\" column is the horizontal angle between" +
                        " the directions to the landmark and the defined picket." + newLine + newLine);
                textArea.append("\t\uF0FC \"The first measurement on the Orientir\" – when calculating the coordinates of the pickets," +
                        " the horizontal angle between the directions to the Orientir and the defined picket is defined" +
                        " as the difference between the directions to the picket and the direction" +
                        " to the first, measured at the station picket." + newLine + newLine);
                textArea.append("    • \"Polygon extraction\" - stations whose names begin with the symbol selected" +
                        " in the proposed list will be ignored when extracting the polygon from the measurement file." + newLine + newLine);
                textArea.append("    The \"Deviations\" tab contains the \"Acceptable deviations\" panel, which includes" +
                        " components that allow you to select from the suggested options the values of acceptable" +
                        " residuals when adjusting the polygon." + newLine);

            }
            case 1 -> {
                textArea.append("    Окно \"Настройки\" содержит вкладки \"Общие\" и \"Допуски\"." + newLine + newLine);
                textArea.append("    Вкладка \"Общие\" включает следующие компоненты:" + newLine + newLine);
                textArea.append("    • \"Язык интерфейса\" - устанавливает выбранный пользователем язык интерфейса." + newLine + newLine);
                textArea.append("    • \"Рабочая папка\" - панель, содержащая текстовое поле, отображающее путь к рабочей" +
                        " папке и кнопку, открывающую стандартный диалог для выбора локальной папки." +
                        " Путь к рабочей папке используется в стандартных диалогах открытия и сохранения" +
                        " файлов в качестве папки по умолчанию." + newLine + newLine);
                textArea.append("    • \"Ориентирование тахеометра\" - группа переключателей, устанавливающая" +
                        " каким образом определять горизонтальный угол на пикет в таблице измерений на вкладке \"Измерения\":" + newLine + newLine);
                textArea.append("\t\uF0FC \"Ноль тахеометра на Ориентир\" – инструмент сориентирован на Ориентир с известными координатами," +
                        " значение в колонке \"Направление\" это горизонтальный угол между направлениями на ориентир и определяемый пикет." + newLine + newLine);
                textArea.append("\t\uF0FC Первое измерение на Ориентир – при вычислении координат пикетов горизонтальный угол между направлениями" +
                        " на ориентир и определяемый пикет определяется как разность направлений на пикет и направления на первый," +
                        " измеренный на станции пикет." + newLine + newLine);
                textArea.append("    • \"Извлечение полигона\" - станции, название которых начинаются с выбранного в предложенном" +
                        " списке символа, будут игнорированы при извлечении полигона из файла измерений." + newLine + newLine);
                textArea.append("    Вкладка \"Допуски\" содержит панель \"Допустимые невязки\", включающую компоненты," +
                        " позволяющие выбрать из предложенных вариантов значения допустимых невязок при уравнивании полигона." + newLine);
            }
        }
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setMargin(insets);
        textArea.setCaretPosition(0);
        return textArea;
    }

    /**
     * Gets content for pnlFiles of ShowHelp
     * @return JTextArea
     */
    public JTextArea getFiles() {
        JTextArea textArea = new JTextArea();
        switch (parentFrame.getOptions().getLanguage()) {
            case 0 -> {
                textArea.append("    The files used by Taheport, depending on the purpose, have the following extensions:" + newLine);
                textArea.append(newLine);
                textArea.append("\t• tah – it is used by the Taheoport program to save the measurement file in an internal format." + newLine);
                textArea.append(newLine);
                textArea.append("\t• pol – it is used by the Taheoport program to save the polygon in an internal format." + newLine);
                textArea.append(newLine);
                textArea.append("\t• kat – catalog of coordinates containing an array of strings consisting of the point name and coordinates (X, Y, Z) separated by a space;" + newLine);
                textArea.append(newLine);
                textArea.append("\t• dat – catalog of coordinates containing an array of strings consisting of the point name and coordinates (X, Y, Z) separated by a space;" + newLine);
                textArea.append(newLine);
                textArea.append("\t• txt – it is used by the Taheoport program to save reports." + newLine);
                textArea.append(newLine);
                textArea.append("\t• gsi, row, txt,.. – total station measurement files." + newLine);

            }
            case 1 -> {
                textArea.append("    Файлы, используемые Taheoport, в зависимости от назначения имеют следующие расширения:" + newLine);
                textArea.append(newLine);
                textArea.append("\t• tah – используется программой Taheoport для сохранения файла измерений во внутреннем формате;" + newLine);
                textArea.append(newLine);
                textArea.append("\t• pol – используется программой Taheoport для сохранения полигона во внутреннем формате;" + newLine);
                textArea.append(newLine);
                textArea.append("\t• kat – каталог координат, содержащий массив строк, состоящих из названия точки и координат (X, Y, Z), разделённых пробелом;" + newLine);
                textArea.append(newLine);
                textArea.append("\t• dat – каталог координат, содержащий массив строк, состоящих из названия точки и координат (X, Y, Z), разделённых пробелом;" + newLine);
                textArea.append(newLine);
                textArea.append("\t• txt – используется программой Taheoport для сохранения различных отчётов;" + newLine);
                textArea.append(newLine);
                textArea.append("\t• gsi, row, txt,.. – файлы измерений тахеометров." + newLine);
            }
        }

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setMargin(insets);
        textArea.setCaretPosition(0);
        return textArea;
    }
// The END of class
}
