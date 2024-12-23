package taheoport.service;

import taheoport.dispatcher.DependencyInjector;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * This class provide language support for UI
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class Shell {

    private final SettingsService settingsService;

    /**
     * Constructor
     */
    public Shell(DependencyInjector dependencyInjector) {
        settingsService = dependencyInjector.getSettingsService();
    }

    /**
     * Gets HashMap titles. Provides text for user interface
     */
    public HashMap<String, String> getTitles() {
        HashMap<String, String> titles = new HashMap<>();
        switch (settingsService.getLanguage()) {
            case 0 -> {
                titles.put("MWmFile", "File");
                titles.put("MWmTools", "Tools");
                titles.put("MWmHelp", "Help");
                titles.put("MWmImport", "Import");
                titles.put("MWfNew", "Create");
                titles.put("MWfOpen", "Open");
                titles.put("MWfSave", "Save");
                titles.put("MWfSaveAs", "Save As");
                titles.put("MWfExit", "Exit");
                titles.put("MWtLoadCat", "Load catalog");
                titles.put("MWtUpdate", "Update coordinates");
                titles.put("MWtRun", "Process (Adjust)");
                titles.put("MWtView", "Process and view");
                titles.put("MWtExtractPol", "Extract polygon");
                titles.put("MWtOptions", "Settings");
                titles.put("MWhAbout", "About");
                titles.put("MWhHelp", "Help");
                titles.put("MWbtnNewTT", "Create");
                titles.put("MWbtnOpenTT", "Open");
                titles.put("MWbtnImportTT", "Import");
                titles.put("MWbtnSaveTT", "Save");
                titles.put("MWbtnRunTT", "Process (adjustment)");
                titles.put("MWbtnVewTT", "Process and view");
                titles.put("MWbtnLoadCatTT", "Load catalog");
                titles.put("MWlblCatalog", "Catalog is not loaded");
                titles.put("MWlblCatalogTT", "The path to catalog");
                titles.put("MWtpMain0", "Measurements");
                titles.put("MWtpMain1", "Polygon");
                titles.put("MWupdateMessageTitle", "Updating coordinates");
                titles.put("MWupdateMessage", " updates have been made");
                titles.put("MWloadCatalogTitle", "Choosing a coordinate catalog");
                titles.put("MWopenFileTitle", "Open file");
                titles.put("MWsaveTahTitle", "Save measurements to file");
                titles.put("MWsavePolTitle", "Save polygon to file");
                titles.put("TAHlblStationTitle", "Station parameters");
                titles.put("TAHlblStationI", "Tool height i =  ");
                titles.put("TAHlblStationListTitle", "Station list");
                titles.put("TAHlblPicketsTitle", "Measurement results");
                titles.put("TAHbtnStationName", "Station name");
                titles.put("TAHbtnStationNameTT", "Insert coordinates from the catalog");
                titles.put("TAHbtnOrName", "Orientir name");
                titles.put("TAHbtnOrNameTT", "Insert coordinates from the catalog");
                titles.put("TAHbtnDeleteStationTT", "Delete selected station");
                titles.put("TAHbtnInsertStationBeforeTT", "Insert station before the selected");
                titles.put("TAHbtnInsertStationAfterTT", "Insert station after the selected");
                titles.put("TAHbtnDeleteRowTT", "Delete selected row");
                titles.put("TAHbtnInsertRowBeforeTT", "Insert row before the selected");
                titles.put("TAHbtnInsertRowAfterTT", "Insert row after the selected");
                titles.put("TAHbtnAddDistanceTT", "Change distance");
                titles.put("TAHbtnChangeDirectionTT", "Change direction");
                titles.put("TAHbtnChangeTiltAngleTT", "Change tilt");
                titles.put("SADlblOffset", "Enter the offset, m.:");
                titles.put("SADtitle", "Changing distance");
                titles.put("SADrbHor", "Horizontal");
                titles.put("SADrbInc", "Inclined");
                titles.put("SADpnlOffsetTitle", "Offset");
                titles.put("SCAtitleChangeDirection", "Changing direction");
                titles.put("SCAtitleChangeTiltAngle", "Changing the tilt of line");
                titles.put("SCArbCopy", "Copy from next row");
                titles.put("SCArbOffset", "Add offset to angle");
                titles.put("SCAlblOffset", "Enter the offset value, D.MMSS");
                titles.put("TAHtmColumnName0", "Name");
                titles.put("TAHtmColumnName1", "Distance");
                titles.put("TAHtmColumnName2", "Direction");
                titles.put("TAHtmColumnName3", "TiltAngle");
                titles.put("TAHtmColumnName4", "TargetHeight");
                titles.put("THEOlblTitleBinding", "Adjustments results");
                titles.put("THEOpnlPaintTitle", "Polygon map");
                titles.put("THEOlblHeight", "Height residue, m.    ");
                titles.put("THEOlblAngle", "Angle residue, sek.    ");
                titles.put("THEOlblFX", "FX, m.    ");
                titles.put("THEOlblFY", "FY, m.    ");
                titles.put("THEOlblFAbsolute", "Absolute deviation, m.    ");
                titles.put("THEOlblRelative", "Relative deviation, m.    ");
                titles.put("THEOlblPer", "Perimeter, m.    ");
                titles.put("TAHbtnImportFromCatalogTT", "Insert station coordinates from the catalog");
                titles.put("THEOtmColumnName0", "Name");
                titles.put("THEOtmColumnName1", "Hor.Angle");
                titles.put("THEOtmColumnName2", "Hor.Distance");
                titles.put("THEOtmColumnName3", "Elevation");
                titles.put("THEOtmColumnName7", "Basis");
                titles.put("SOtitle", "Settings");
                titles.put("SOlblLanguage", "Language");
                titles.put("SOpnlWorkDirTitle", "Working folder");
                titles.put("SOtfPathWorkDirTT", "Full path to the working folder");
                titles.put("SObtnFolderTT", "Select a folder on disk");
                titles.put("SOsetDialogTitle", "Choosing a working folder");
                titles.put("SOpnlOrientStationTitle", "Orientation of the total station");
                titles.put("SOrbZero", "Zero total station on the Orientir");
                titles.put("SOrbFirst", "The first measurement on the Orientir");
                titles.put("SOpnlExtractorTitle", "Polygon extraction");
                titles.put("SOlblPrefixEX", "Prefix in the name of a \"free\" station");
                titles.put("SOlblPrefixEXTT", "Taheoport will skip this station when extracting polygon from the measurement file");
                titles.put("SOpnlAcceptableTitle", "Acceptable deviations");
                titles.put("SOlblFH", "Height residue, mm.");
                titles.put("SOlblFHor", "Angle residue, sek.");
                titles.put("SOlblFAbs", "Absolute deviation, m.");
                titles.put("SOlblFOtn", "Relative deviation");
                titles.put("SOcbFHTT", "L - polygon length, km.");
                titles.put("SOcbFHorTT", "n - number of angles in the polygon");
                titles.put("SOtpTitle0", "General");
                titles.put("SOtpTitle1", "Deviations");
                titles.put("SObtnApprove", "Approve");
                titles.put("SObtnApproveTT", "Sets and saves current options");
                titles.put("SObtnCancel", "Cancel");
                titles.put("SObtnCancelTT", "Cancel all changes");
                titles.put("SCdialogTitle", "Catalog of basis point coordinates");
                titles.put("SCbtnInsertCoordinates", "Insert");
                titles.put("SCbtnInsertCoordinatesTT", "Insert selected point");
                titles.put("SCbtnCancel", "Cancel");
                titles.put("SVRdialogTitle", "Processing results");
                titles.put("SVRbtnCloseTT", "Close the window");
                titles.put("SVRbtnSaveReportTT", "Save a report to disk");
                titles.put("SVRsaveTitle0", "Save a coordinates catalog to disk");
                titles.put("SVRsaveTitle1", "Save a report to disk");
                titles.put("SVRtpSurveyTitle0", "Coordinates catalog");
                titles.put("SVRtpSurveyTitle1", "Coordinates calculation report");
                titles.put("SVRtpSurveyTitle2", "Heights calculation report");
                titles.put("SVEdialogTitle", "Analysis of polygon extraction from the measurements");
                titles.put("SVAdialogTitle", "Polygon adjustment results");
                titles.put("SAdialogTitle", "About");
                titles.put("TPmessageError", "Check the correctness of the source data");
                titles.put("TPmessageErrorTitle", "Error in source data");
                titles.put("TPmessageNoAdjustment", "Add basis points to the polygon");
                titles.put("TPmessageNoAdjustmentTitle", "Adjustment is not possible");
                titles.put("SAbtnClose", "Close");
                titles.put("SHtitleFrame", "Taheoport: Users guid");
                titles.put("SHtnContent", "Content                                       ");
                titles.put("SHtnIntroduction", "Introduction");
                titles.put("SHtnTasks", "Allowed tasks");
                titles.put("SHtnImport", "Import measurements");
                titles.put("SHtnExtract", "Extract polygon");
                titles.put("SHtnAdjustment", "Adjust polygon");
                titles.put("SHtnInterface", "User interface");
                titles.put("SHtnMainMenu", "Main menu");
                titles.put("SHtnToolbar", "Toolbar");
                titles.put("SHtnMeasurements", "\"Measurements\" Tab");
                titles.put("SHtnPolygon", "\"Polygon\" Tab");
                titles.put("SHtnFiles", "File types");
                titles.put("SHtnOptions", "Settings");
            }

            case 1 -> {
                titles.put("MWmFile", "Файл");
                titles.put("MWmTools", "Инструменты");
                titles.put("MWmHelp", "Помощь");
                titles.put("MWmImport", "Импорт");
                titles.put("MWfNew", "Создать");
                titles.put("MWfOpen", "Открыть");
                titles.put("MWfSave", "Сохранить");
                titles.put("MWfSaveAs", "Сохранить как");
                titles.put("MWfExit", "Выход");
                titles.put("MWtLoadCat", "Загрузить каталог");
                titles.put("MWtUpdate", "Обновить координаты опорных точек");
                titles.put("MWtRun", "Обработать (уравнять)");
                titles.put("MWtView", "Обработать и просмотреть");
                titles.put("MWtExtractPol", "Извлечь полигон");
                titles.put("MWtOptions", "Настройки");
                titles.put("MWhAbout", "О программе");
                titles.put("MWhHelp", "Помощь");
                titles.put("MWbtnNewTT", "Создать");
                titles.put("MWbtnOpenTT", "Открыть");
                titles.put("MWbtnImportTT", "Импорт");
                titles.put("MWbtnSaveTT", "Сохранить");
                titles.put("MWbtnRunTT", "Обработать (уравнять)");
                titles.put("MWbtnVewTT", "Обработать и просмотреть");
                titles.put("MWbtnLoadCatTT", "Загрузить каталог");
                titles.put("MWlblCatalog", "Каталог не загружен");
                titles.put("MWlblCatalogTT", "Путь к каталогу координат");
                titles.put("MWtpMain0", "Измерения");
                titles.put("MWtpMain1", "Полигон");
                titles.put("MWupdateMessageTitle", "Обновление координат");
                titles.put("MWupdateMessage", " обновлений было произведено");
                titles.put("MWloadCatalogTitle", "Выбор каталога координат");
                titles.put("MWopenFileTitle", "Открыть файл");
                titles.put("MWsaveTahTitle", "Сохранить измерения в файл");
                titles.put("MWsavePolTitle", "Сохранить полигон в файл");
                titles.put("TAHlblStationTitle", "Параметры станции");
                titles.put("TAHlblStationI", "Высота инструмента i =  ");
                titles.put("TAHlblStationListTitle", "Список станций");
                titles.put("TAHlblPicketsTitle", "Результаты измерений");
                titles.put("TAHbtnStationName", "Название станции");
                titles.put("TAHbtnStationNameTT", "Вставить координаты из каталога");
                titles.put("TAHbtnOrName", "Название ориентира");
                titles.put("TAHbtnOrNameTT", "Вставить координаты из каталога");
                titles.put("TAHbtnDeleteStationTT", "Удалить выделенную станцию");
                titles.put("TAHbtnInsertStationBeforeTT", "Вставить станцию перед выделенной");
                titles.put("TAHbtnInsertStationAfterTT", "Вставить станцию после выделенной");
                titles.put("TAHbtnDeleteRowTT", "Удалить выделенную строку");
                titles.put("TAHbtnInsertRowBeforeTT", "Вставить строку перед выделенной");
                titles.put("TAHbtnInsertRowAfterTT", "Вставить строку после выделенной");
                titles.put("TAHbtnAddDistanceTT", "Изменить расстояние");
                titles.put("TAHbtnChangeDirectionTT", "Изменить направление");
                titles.put("TAHbtnChangeTiltAngleTT", "Изменить наклон");
                titles.put("SADlblOffset", "Введите смещение, м.:");
                titles.put("SADtitle", "Изменение расстояния");
                titles.put("SADrbHor", "Горизонтальное");
                titles.put("SADrbInc", "Наклонное");
                titles.put("SADpnlOffsetTitle", "Смещение");
                titles.put("SCAtitleChangeDirection", "Изменение направления");
                titles.put("SCAtitleChangeTiltAngle", "Изменение наклона линии");
                titles.put("SCArbCopy", "Копировать из следующей строки");
                titles.put("SCArbOffset", "Добавить смещение в угол");
                titles.put("SCAlblOffset", "Введите смещение, Г.ММСС");
                titles.put("TAHtmColumnName0", "Название");
                titles.put("TAHtmColumnName1", "Расстояние");
                titles.put("TAHtmColumnName2", "Направление");
                titles.put("TAHtmColumnName3", "Уг.Наклона");
                titles.put("TAHtmColumnName4", "Выс.Цели");
                titles.put("THEOlblTitleBinding", "Результаты уравнивания");
                titles.put("THEOpnlPaintTitle", "Схема полигона");
                titles.put("THEOlblHeight", "Высотная, м.    ");
                titles.put("THEOlblAngle", "Угловая, сек.    ");
                titles.put("THEOlblFX", "FX, м.    ");
                titles.put("THEOlblFY", "FY, м.    ");
                titles.put("THEOlblFAbsolute", "Абсолютная, м.    ");
                titles.put("THEOlblRelative", "Относительная, м.    ");
                titles.put("THEOlblPer", "Периметр, м.    ");
                titles.put("TAHbtnImportFromCatalogTT", "Вставить координаты станции из каталога");
                titles.put("THEOtmColumnName0", "Название");
                titles.put("THEOtmColumnName1", "Гор.Угол");
                titles.put("THEOtmColumnName2", "Гор.Длина");
                titles.put("THEOtmColumnName3", "Превышение");
                titles.put("THEOtmColumnName7", "Опора");
                titles.put("SOtitle", "Настройки");
                titles.put("SOlblLanguage", "Язык интерфейса");
                titles.put("SOpnlWorkDirTitle", "Рабочая папка");
                titles.put("SOtfPathWorkDirTT", "Полный путь к рабочей папке");
                titles.put("SObtnFolderTT", "Выбрать рабочую папку на диске");
                titles.put("SOsetDialogTitle", "Выбор рабочей папки");
                titles.put("SOpnlOrientStationTitle", "Ориентирование тахеометра");
                titles.put("SOrbZero", "Ноль тахеометра на Ориентир");
                titles.put("SOrbFirst", "Первое измерение на Ориентир");
                titles.put("SOpnlExtractorTitle", "Извлечение полигона");
                titles.put("SOlblPrefixEX", "Префикс в названии\"свободной\" станции");
                titles.put("SOlblPrefixEXTT", "Taheoport пропустит такие станции при извлечении полигона из файла измерений");
                titles.put("SOpnlAcceptableTitle", "Допустимые невязки");
                titles.put("SOlblFH", "Высотная невязка, мм.");
                titles.put("SOlblFHor", "Угловая невязка, сек.");
                titles.put("SOlblFAbs", "Абсолютная невязка, м.");
                titles.put("SOlblFOtn", "Относительная невязка");
                titles.put("SOcbFHTT", "L - длина полигона, км.");
                titles.put("SOcbFHorTT", "n - количество углов в полигоне");
                titles.put("SOtpTitle0", "Общие");
                titles.put("SOtpTitle1", "Допуски");
                titles.put("SObtnApprove", "Принять");
                titles.put("SObtnApproveTT", "Установить и сохранить текущие настройки");
                titles.put("SObtnCancel", "Отменить");
                titles.put("SObtnCancelTT", "Отменить все изменения");
                titles.put("SCdialogTitle", "Каталог координат опорных точек");
                titles.put("SCbtnInsertCoordinates", "Вставить");
                titles.put("SCbtnInsertCoordinatesTT", "Вставить выбранный пункт");
                titles.put("SCbtnCancel", "Отмена");
                titles.put("SVRdialogTitle", "Результаты обработки");
                titles.put("SVRbtnCloseTT", "Закрыть окно");
                titles.put("SVRbtnSaveReportTT", "Сохранить на диске");
                titles.put("SVRsaveTitle0", "Сохранение на диске каталога координат");
                titles.put("SVRsaveTitle1", "Сохранение на диске отчёта");
                titles.put("SVRtpSurveyTitle0", "Каталог координат");
                titles.put("SVRtpSurveyTitle1", "Ведомость вычисления координат");
                titles.put("SVRtpSurveyTitle2", "Ведомость вычисления высот");
                titles.put("SVEdialogTitle", "Анализ извлечения полигона из файла измерений");
                titles.put("SVAdialogTitle", "Результаты уравнивания полигона");
                titles.put("SAdialogTitle", "О программе");
                titles.put("TPmessageError", "Проверьте корректность исходных данных");
                titles.put("TPmessageErrorTitle", "Ошибка в исходных данных");
                titles.put("TPmessageNoAdjustment", "Добавьте опорные точки в полигон");
                titles.put("TPmessageNoAdjustmentTitle", "Уравнивание невозможно");
                titles.put("SAbtnClose", "Закрыть");
                titles.put("SHtitleFrame", "Taheoport: Руководство пользователя");
                titles.put("SHtnContent", "Содержание");
                titles.put("SHtnIntroduction", "Введение");
                titles.put("SHtnTasks", "Решаемые задачи");
                titles.put("SHtnImport", "Импорт измерений");
                titles.put("SHtnExtract", "Извлечение полигона");
                titles.put("SHtnAdjustment", "Уравнивание полигона");
                titles.put("SHtnInterface", "Пользовательский интерфейс");
                titles.put("SHtnMainMenu", "Главное меню");
                titles.put("SHtnToolbar", "Панель инструментов");
                titles.put("SHtnMeasurements", "Вкладка \"Изменения\"");
                titles.put("SHtnPolygon", "Вкладка \"Полигон\"");
                titles.put("SHtnFiles", "Типы файлов");
                titles.put("SHtnOptions", "Настройки");
            }
        }
        return titles;
    }

    /**
     * Gets titles for reports
     * @return HashMap
     */

    public HashMap<String, String> getTitlesReports() {
        HashMap <String, String>titlesReports = new HashMap<>();
        switch (settingsService.getLanguage()) {
            case 0 -> {
                titlesReports.put("SPstation", "Station ");
                titlesReports.put("SPorientir", "Orientir ");
                titlesReports.put("SPtoolHeight", "Tool Height i = ");
                titlesReports.put("TPfoterTitle", "   Technical parameters of the polygon");
                titlesReports.put("TPperimeter", "Polygon perimeter : ");
                titlesReports.put("TPm", "m.");
                titlesReports.put("TPangleResidues", "     1. Angle residues");
                titlesReports.put("TPactual", "actual : ");
                titlesReports.put("TPsek", "sek.");
                titlesReports.put("TPacceptable", "acceptable : ");
                titlesReports.put("TPlineResidues", "     2. Line residues");
                titlesReports.put("TPlineDX", "line residue DX : ");
                titlesReports.put("TPlineDY", "line residue DY : ");
                titlesReports.put("TPabsoluteDeviation", "absolute deviation : ");
                titlesReports.put("TPactualRelativeDeviation", "actual relative deviation: ");
                titlesReports.put("TPacceptableRelativeDeviation", "acceptable relative deviation: ");
                titlesReports.put("TPcontrol", "  checksum ");
                titlesReports.put("TPactualResidue", "actual height residue : ");
                titlesReports.put("TPacceptableResidue", "acceptable height residue : ");
                titlesReports.put("TPmm", "mm.");

            }

            case 1 -> {
                titlesReports.put("SPstation", "Станция ");
                titlesReports.put("SPorientir", "Ориентир ");
                titlesReports.put("SPtoolHeight", "Высота инструмента i = ");
                titlesReports.put("TPfoterTitle", "   Технические параметры полигона");
                titlesReports.put("TPperimeter", "Периметр полигона : ");
                titlesReports.put("TPm", "м.");
                titlesReports.put("TPangleResidues", "     1. Угловые невязки");
                titlesReports.put("TPactual", "фактическая : ");
                titlesReports.put("TPsek", "сек.");
                titlesReports.put("TPacceptable", "допустимая : ");
                titlesReports.put("TPlineResidues", "     2. Линейные невязки");
                titlesReports.put("TPlineDX", "DX : ");
                titlesReports.put("TPlineDY", "DY : ");
                titlesReports.put("TPabsoluteDeviation", "абсолютная невязка : ");
                titlesReports.put("TPactualRelativeDeviation", "фактическая относительная : ");
                titlesReports.put("TPacceptableRelativeDeviation", "допустимая относительная : ");
                titlesReports.put("TPcontrol", "контр.суммы");
                titlesReports.put("TPactualResidue", "фактическая высотная невязка : ");
                titlesReports.put("TPacceptableResidue", "допустимая высотная невязка : ");
                titlesReports.put("TPmm", "мм.");

            }
        }

        return titlesReports;
    }

    /**
     * Gets header to report of determinations of average values
     * @return LinkedList
     */
    public LinkedList<String> getTopReportExtract() {
    LinkedList<String> llReportExtract = new LinkedList<String>();
        switch (settingsService.getLanguage()) {
            case 0 -> {
                llReportExtract.add("");
                llReportExtract.add("                       REPORT OF DETERMINATION OF AVERAGE VALUES.");
                llReportExtract.add("--------------------------------------------------------------------------------------------------");
                llReportExtract.add("|  Station   |     Horizontal distance, m.    |        |           Elevations           |        |");
                llReportExtract.add("|    name    |--------------------------------| DL,мм. |--------------------------------| Dh,мм. |");
                llReportExtract.add("|            |  forward |  back    |  average |        |  forward |   back   |  average |        | ");
                llReportExtract.add("|------------|----------|----------|----------|--------|----------|----------|----------|--------|");
                llReportExtract.add("|      1     |     2    |     3    |     4    |   5    |     6    |     7    |     8    |    9   |");
                llReportExtract.add("|------------|----------|----------|----------|--------|----------|----------|----------|--------|");

            }
            case 1 -> {
                llReportExtract.add("");
                llReportExtract.add("              ВЕДОМОСТЬ ВЫЧИСЛЕНИЯ СРЕДНИХ ГОРИЗОНТАЛЬНЫХ ПРОЛОЖЕНИЙ И ПРЕВЫШЕНИЙ.");
                llReportExtract.add("--------------------------------------------------------------------------------------------------");
                llReportExtract.add("|Наименование|       Гор. проложение, м.      |        |          Превышение, м.        |        |");
                llReportExtract.add("|   точки    |--------------------------------| DL,мм. |--------------------------------| Dh,мм. |");
                llReportExtract.add("|  стояния   |  вперёд  |  назад   |  среднее |        |  вперёд  |   назад  |  среднее |        | ");
                llReportExtract.add("|------------|----------|----------|----------|--------|----------|----------|----------|--------|");
                llReportExtract.add("|      1     |     2    |     3    |     4    |   5    |     6    |     7    |     8    |    9   |");
                llReportExtract.add("|------------|----------|----------|----------|--------|----------|----------|----------|--------|");

            }
        }

    return llReportExtract;
    }

    /**
     * Gets header to coordinate calculation report of surveyProject
     * @return LinkedList
     */
    public LinkedList<String> getTopReportSurvey() {
        LinkedList<String> llReportSurvey = new LinkedList<String>();
        switch (settingsService.getLanguage()) {
            case 0 -> {
                llReportSurvey.add("");
                llReportSurvey.add("                           C  O  O  R  D  I  N  A  T  E   C  A  L  C  U  L  A  T  I  O  N   R  E  P  O  R  T");
                llReportSurvey.add("-----------------------------------------------------------------------------------------------------------------------------------------------");
                llReportSurvey.add("|  Station    |distance |   Hor    |  Tilt   |  Target  |Dirrection|      C o r r e c t i o n s      |        C o o r d i n a t e s           |");
                llReportSurvey.add("|   name      |         |  angle   |  angle  |  height  |          |---------------------------------|----------------------------------------|");
                llReportSurvey.add("|             |    m.   |  d.ms    |   d.ms  |     m.   |   d.ms   |   DX, m. |   DY, m. |   DZ, m.  |     X, m.   |    Y, m.    |     Z, m.  |");
                llReportSurvey.add("|-------------|---------|----------|---------|----------|----------|----------|----------|-----------|--------- ---|-------------|------------|");
                llReportSurvey.add("|      1      |     2   |     3    |    4    |     5    |     6    |     7    |     8    |      9    |     10      |      11     |      12    |");
                llReportSurvey.add("-----------------------------------------------------------------------------------------------------------------------------------------------");
            }

            case 1 -> {
                llReportSurvey.add("");
                llReportSurvey.add("                           В  Е  Д  О  М  О  С  Т  Ь    В  Ы  Ч  И  С  Л  Е  Н  И  Я    К  О  О  Р  Д  И  Н  А  Т");
                llReportSurvey.add("-----------------------------------------------------------------------------------------------------------------------------------------------");
                llReportSurvey.add("|  Название   |  Длина  | Направ-  |  Угол   |  Высота  |   Дир.   |        П р и р а щ е н и я      |            К о о р д и н а т ы         |");
                llReportSurvey.add("|   точки     |  линии  |  ление   | наклона | наведения|   угол   |---------------------------------|----------------------------------------|");
                llReportSurvey.add("|             |    м.   |  г.мс    |   г.мс  |     м.   |   г.мс   |   DX, м. |   DY, м. |   DZ, м.  |     X, м.   |    Y, м.    |     Z, м.  |");
                llReportSurvey.add("|-------------|---------|----------|---------|----------|----------|----------|----------|-----------|--------- ---|-------------|------------|");
                llReportSurvey.add("|      1      |     2   |     3    |    4    |     5    |     6    |     7    |     8    |      9    |     10      |      11     |      12    |");
                llReportSurvey.add("-----------------------------------------------------------------------------------------------------------------------------------------------");
            }
        }

        return llReportSurvey;
    }

    /**
     * Gets header to coordinate calculation report for polygonProject
     * @return LinkedList
     */
    public LinkedList<String> getTopReportXY() {
        LinkedList<String> llTopReportXY = new LinkedList<String>();
        switch (settingsService.getLanguage()) {
            case 0 -> {
                llTopReportXY.add("");
                llTopReportXY.add("                       C  O  O  R  D  I  N  A  T  E   C  A  L  C  U  L  A  T  I  O  N   R  E  P  O  R  T");
                llTopReportXY.add("");
                llTopReportXY.add("-------------------------------------------------------------------------------------------------------------------------------");
                llTopReportXY.add("|   Station  |   Hor.   |   Hor.   | H.angle |Direction |     C  o  r  r  e  c  t  i  o  n  s   |     C o o r d i n a t e s   |");
                llTopReportXY.add("|    name    |   angle  | distance | correct.|          |---------------------------------------|-----------------------------|");
                llTopReportXY.add("|            |   d.ms   |    m.    |   sek.  |   d.ms   |    DX    |   dX   |    DY    |   dY   |       X      |      Y       |");
                llTopReportXY.add("|------------|----------|----------|---------|----------|----------|--------|----------|--------|--------------|--------------|");
                llTopReportXY.add("|     1      |     2    |     3    |    4    |     5    |     6    |    7   |     8    |    9   |       10     |      11      |");
                llTopReportXY.add("|------------|----------|----------|---------|----------|----------|--------|----------|--------|--------------|--------------|");

            }
            case 1 -> {
                llTopReportXY.add("");
                llTopReportXY.add("                         В  Е  Д  О  М  О  С  Т  Ь   В  Ы  Ч  И  С  Л  Е  Н  И  Я   К  О  О  Р  Д  И  Н  А  Т");
                llTopReportXY.add("");
                llTopReportXY.add("-------------------------------------------------------------------------------------------------------------------------------");
                llTopReportXY.add("|Наименование|   Гор.   |   Гор.   |Поправка |   Дир.   |     п  р  и  р  а  щ  е  н  и  я      |     к о о р д и н а т ы     |");
                llTopReportXY.add("|   точки    |   угол   |проложение|в г.угол |   угол   |---------------------------------------|-----------------------------|");
                llTopReportXY.add("|  стояния   |   г.мс   |    м.    |  сек.   |   г.мс   |    DX    |   dX   |    DY    |   dY   |       X      |      Y       |");
                llTopReportXY.add("|------------|----------|----------|---------|----------|----------|--------|----------|--------|--------------|--------------|");
                llTopReportXY.add("|     1      |     2    |     3    |    4    |     5    |     6    |    7   |     8    |    9   |       10     |      11      |");
                llTopReportXY.add("|------------|----------|----------|---------|----------|----------|--------|----------|--------|--------------|--------------|");

            }
        }

        return llTopReportXY;
    }

    /**
     * Gets header to height calculation report
     * @return LinkedList
     */
    public LinkedList<String> getTopReportZ() {
        LinkedList<String> llReportZ = new LinkedList<String>();
        switch (settingsService.getLanguage()) {
            case 0 -> {
                llReportZ.add("");
                llReportZ.add("           H E I G H T   C A L C U L A T I O N   R E P O R T");
                llReportZ.add("");
                llReportZ.add("-----------------------------------------------------------------------");
                llReportZ.add("|  Station   |   Side   |  Measured  |Correct.| Corrected  | Absolute |");
                llReportZ.add("|    name    |   lenth  | elevation  |        | elevation  |  height  |");
                llReportZ.add("|            |     m.   |      m.    |    m.  |     m.     |    m.    |");
                llReportZ.add("|------------|----------|------------|--------|------------|----------|");
                llReportZ.add("|     1      |     2    |      3     |    4   |      5     |     6    |");
                llReportZ.add("|------------|----------|------------|--------|------------|----------|");

            }

            case 1 -> {
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

            }
        }

        return llReportZ;
    }

    /**
     * gets text for license agreement
     * @return LinkedList
     */
    public LinkedList<String> getLicense() {
        LinkedList<String> llLicense = new LinkedList<>();
        switch (settingsService.getLanguage()) {
            case 0 -> {
                llLicense.add("                    LICENSE AGREEMENT");
                llLicense.add("              To use the program Taheoport");
                llLicense.add("  This license agreement (next the \"Agreement\") is a contract,");
                llLicense.add("concluded between the end user (next the \"User\") and");
                llLicense.add("Nizovkin Andrey Victorovich (next the \"Author\") regarding");
                llLicense.add("the Author's software product specified in the title");
                llLicense.add("(next the \"Program\"), including the program itself and");
                llLicense.add("the built-in documentation.");
                llLicense.add("  By reproducing, distributing or otherwise using the Program,");
                llLicense.add("the User thereby accepts the terms of the Agreement.");
                llLicense.add("  If the User does not accept the terms of the Agreement,");
                llLicense.add("then he has no right to use the Program");
                llLicense.add("                 1. Subject of the license");
                llLicense.add("  The subject of the license is the right to use the Program,");
                llLicense.add("regarding which the Author claims that it is");
                llLicense.add("a product of his intellectual activity, and all");
                llLicense.add("copyrights and other rights to it belong to the Author.");
                llLicense.add("  In case of disputes over the copyright of the Program,");
                llLicense.add("the Author assumes full responsibility.");
                llLicense.add("                    2. License Scope");
                llLicense.add("  The Author grants the User the right to use the Program");
                llLicense.add("without royalties in favor of the Author if the User fulfills");
                llLicense.add("the following conditions:");
                llLicense.add("* The User uses the Program in accordance with");
                llLicense.add("  the help system built into the Program and in accordance");
                llLicense.add("  with the current legislation.");
                llLicense.add("* The time of using the Program is not limited.");
                llLicense.add("* The User has the right to make any number of");
                llLicense.add("  backup copies of the Program");
                llLicense.add("  for personal use, provided that all copyright notices");
                llLicense.add("  on all copies of the Program");
                llLicense.add("                    3. User support");
                llLicense.add("  You can inform the Author about all cases of");
                llLicense.add("abnormal functioning of the Program");
                llLicense.add("that goes against the help system built into the program");
                llLicense.add("at the following addresses:");
                llLicense.add("* email: andreynizovkin@inbox.ru");
                llLicense.add("* tel: +7 928 178 78 45");
                llLicense.add("                    4. Constraints");
                llLicense.add("  This License Agreement prohibits the User from:");
                llLicense.add("* Decompile or modify the Program.");
                llLicense.add("* Embed a Program or parts of it into");
                llLicense.add("  the Users software products.");
                llLicense.add("* Transfer the Program to a third party(s).");
                llLicense.add("* Use the Program for any other purposes");
                llLicense.add("  except for personal use.");
                llLicense.add("* To distribute the Program in any form without");
                llLicense.add("  the consent of the Author:");
                llLicense.add("  on CD-ROM disks, or any other media,");
                llLicense.add("  in public and local computer networks.");
                llLicense.add("                     5. Copyright");
                llLicense.add("  All copyrights to the Program belong to the Author.");
                llLicense.add("The program is protected by copyright laws and");
                llLicense.add("international agreements, as well as the provisions of");
                llLicense.add("other laws and international treaties");
                llLicense.add("in the field of intellectual property.");

            }
            case 1 -> {
                llLicense.add("               ЛИЦЕНЗИОННОЕ СОГЛАШЕНИЕ");
                llLicense.add("           На использование программы Taheoport");
                llLicense.add("  Настоящее лицензионное соглашение (далее \"Соглашение\"),");
                llLicense.add("является договором, заключаемым между");
                llLicense.add("конечным пользователем (далее \"Пользователем\") и");
                llLicense.add("Низовкиным Андреем Викторовичем (далее \"Автором\")");
                llLicense.add("относительно указанного в заглавии документа ");
                llLicense.add("программного продукта Автора (далее \"Программа\"),");
                llLicense.add("включающего в себя саму Программу и встроенную в нее");
                llLicense.add("документацию.");
                llLicense.add("  Воспроизводя, распространяя или иным образом ");
                llLicense.add("используя Программу, Пользователь тем самым принимает");
                llLicense.add("на себя условия Соглашения.");
                llLicense.add("  Если Пользователь не принимает условий Соглашения,");
                llLicense.add("то он не имеет права использовать Программу.");
                llLicense.add("                1. Предмет лицензии");
                llLicense.add("  Предметом лицензии является право использования Программы,");
                llLicense.add("относительно которой Автор утверждает, что она является");
                llLicense.add("продуктом его интеллектуальной деятельности, и все");
                llLicense.add("авторские и иные права на нее принадлежат Автору.");
                llLicense.add("  В случае возникновения споров по поводу авторских прав");
                llLicense.add("на Программу всю ответственность берет на себя Автор.");
                llLicense.add("                2. Объём лицензии");
                llLicense.add("  Автор предоставляет Пользователю право");
                llLicense.add("использования Программы без лицензионных платежей");
                llLicense.add("в пользу Автора при исполнении Пользователем:");
                llLicense.add("следующих условий:");
                llLicense.add("* Пользователь использует Программу в соответствии");
                llLicense.add("  со встроенной в Программу справочной системой и");
                llLicense.add("  в соответствии с действующим законодательством.");
                llLicense.add("* Время использования Программы не ограничено.");
                llLicense.add("* Пользователь вправе делать любое количество");
                llLicense.add("  резервных копий Программы для личного использования,");
                llLicense.add("  при условии сохранения или воспроизводства всех");
                llLicense.add("  уведомлений об авторских правах на всех копиях Программы");
                llLicense.add("            3. Услуги по обеспечению");
                llLicense.add("  Сообщать Автору обо всех случаях ненормального");
                llLicense.add("функционирования Программы, идущего в разрез");
                llLicense.add("с встроенной в программу справочной системой,");
                llLicense.add("можно по следующим адресам:");
                llLicense.add("* Адрес электронной почты: andreynizovkin@inbox.ru");
                llLicense.add("* Телефон: +7 928 178 78 45");
                llLicense.add("                 4. Ограничения");
                llLicense.add("  Настоящим лицензионным соглашением");
                llLicense.add("Пользователю запрещается:");
                llLicense.add("* Декомпилировать или модифицировать Программу.");
                llLicense.add("* Встраивать Программу или ее части в собственные");
                llLicense.add("  программные продукты Пользователя;");
                llLicense.add("* Передавать Программу третьему лицу (лицам).");
                llLicense.add("* Использовать Программу в каких-либо других целях,");
                llLicense.add("  кроме как для личного использования.");
                llLicense.add("* Без согласования с Автором производить распространение");
                llLicense.add("  Программы в любом виде: на дисках CD-ROM, либо");
                llLicense.add("  любых других носителях информации,");
                llLicense.add("  в компьютерных сетях публичного и локального характера.");
                llLicense.add("                 5. Авторское право");
                llLicense.add(" Все авторские права на Программу принадлежат Автору. ");
                llLicense.add("Программа защищена законами и международными соглашениями");
                llLicense.add("об авторских правах, а также");
                llLicense.add("положениями иных законов и международных договоров");
                llLicense.add("в области интеллектуальной собственности.");

            }
        }
        return llLicense;
    }


    // The END of Class
}
