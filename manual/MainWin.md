
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# MainWin Класс

---

## Определение

Представляет главное окно программы. Содержит компоненты пользовательского графического интерфейса и включает методы для обслуживания ими генерируемых событий. 

---

## Наследование

`java.lang.Object` -> `javax.swing.JFrame` -> `taheoport.gui.MainWin`

---

## Поля

Модификатор и тип | Поле | Описание
--- | ---|---
`private static TahEditorFocusTransversalPolicy` | `focusPolicy` | Экземпляр внутреннего класса `TahEditorFocusTransversalPolicy`, обеспечивающий последовательность перехода по `Tab` на вкладке "Измерения"
`private JTabbedPane` | `tpMain` | Экземпляр `javax.swing.JTabbedPane`, служащий контейнером для панелей `pnlMeasurements` и `pnlPolygon`.
`private final JPanel` | `pnlMeasurements` | Экземпляр `javax.swing.JPanel`, служащий контейнером для редактора измерений (`surveyEditor`).
`private final JPanel` | `pnlPolygon` | Экземпляр `javax.swing.JPanel`, служащий контейнером для редактора полигонов (`polygonEditor`).
`private Catalog` | `catalog` | Представляет координаты твёрдых (базисных) точек для текущей сессии.
`private SurveyProject` | `surveyProject` | Представляет набор данных, необходимый для получения координат съёмочных точек (пикетов).Включает методы для математической обработки измерений, генерирования отчётов.
`private PolygonProject` | `polygonProject` | Представляет набор данных, необходимый для получения координат точек полигона. Включает методы для математической обработки и оценки точности измерений, генерирования отчётов.
`private String` | `pathWorkDir` | Путь к рабочей папке.
`private final Options` | `options` | Представляет пользовательские настройки программы.
`private HashMap<String, String>` | `titles` | Представляет словарь для UI и отчётов, в соответствии с языковыми настройками.
`private boolean` | `isCatalog` | True, если каталог загружен и False, если не загружен.
`private final int` | `wMain` | Ширина главного окна программы.
`private final int` | `hMain` | Высота главного окна программы.
`private final JMenu` | `mFile` | Меню "Файл". Служит контейнером для пунктов `fNew`, `fOpen`, `fSave`, `fSaveAs`, `fNew`, `fExit` и подменю `mImport`
`private final JMenu` | `mTools` | Меню "Инструменты". Служит контейнером для пунктов `tLoadCat`, `tUpdate`, `tRun`, `tView`, `tExtractPol`, `tOptions`.
`private final JMenu` | `mHelp` | Меню "Помощь". Служит контейнером для пунктов `hAbout`, `hHelp`.
`private final JMenu` | `mImport` | Меню "Импорт". Служит контейнером для пунктов `iLeica`, `iNicon`, `iTopcon`
`private final JMenuItem` | `fNew` | Пункт меню "Создать". ActionEvent -> `this.newFile()`
`private final JMenuItem` | `fOpen` | Пункт меню "Открыть". ActionEvent -> `this.openFile()`
`private final JMenuItem` | `fSave` | Пункт меню "Сохранить". ActionEvent -> `this.save()`
`private final JMenuItem` | `fSaveAs` | Пункт меню "Сохранить как". ActionEvent -> `this.saveAs()`
`private final JMenuItem` | `fExit` | Пункт меню "Выход". ActionEvent -> `System.exit(0)`
`private final JMenuItem` | `tOptions` | Пункт меню "Настройки". ActionEvent -> `new ShowOptions(this)`
`private final JMenuItem` | `tRun` | Пункт меню "Обработать(Уравнять)". ActionEvent -> `this.processSourceData`
`private final JMenuItem` | `tLoadCat` | Пункт меню "Загрузить каталог". ActionEvent -> `this.loadCatalog`
`private final JMenuItem` | `tView` | Пункт меню "Обработать и просмотреть". ActionEvent -> `this.viewResult()`
`private final JMenuItem` | `tUpdate` | Пункт меню "Обновить координаты опорных точек". ActionEvent -> `this.updateBasePoints()`
`private final JMenuItem` | `tExtractPol` | Пункт меню "Извлечь полигон". ActionEvent -> `this.extractPol()`
`private final JMenuItem` | `hAbout` | Пункт меню "О программе". ActionEvent -> `new ShowAbout(this)`
`private final JMenuItem` | `hHelp` | Пункт меню "Помощь". ActionEvent -> `new ShowHelp(this)`
`private final JButton` | `btnNew` | Кнопка панели инструментов "Создать". ActionEvent -> `this.newFile()`
`private final JButton` | `btnOpen` | Кнопка панели инструментов "Открыть". ActionEvent -> `this.newFile()`
`private final JButton` | `btnSave` | Кнопка панели инструментов "Сохранить". ActionEvent -> `this.save()`
`private final JButton` | `btnOpen` | Кнопка панели инструментов "Открыть". ActionEvent -> `this.newFile()`
`private final JButton` | `btnRun` | Кнопка панели инструментов "Обработать(Уравнять)". ActionEvent -> `this.processSourceData()`
`private final JButton` | `btnView` | Кнопка панели инструментов "Обработать и просмотреть". ActionEvent -> `this.viewResult()`
`private final JButton` | `btnLoadCat` | Кнопка панели инструментов "Загрузить каталог". ActionEvent -> `this.loadCatalog()`
`private final JButton` | `btnImport` | Кнопка панели инструментов "Импорт". ActionEvent -> `this.ppImport.show()`
`private SurveyEditorStandart` | `surveyEditor` | Редактор измерений. Предоставляет компоненты UI для работы с набором измерений.
`private PolygonEditorStandart` | `polygonEditor` | Редактор полигона. Предоставляет компоненты UI для работы с полигоном.
`private final JLabel` | `lblCatalog` | Отображает путь к каталогу координат, если он загружен.
`private JPopupMenu` | `ppImport` | Всплывающее меню. Служит контейнером для пунктов `ppiLeica`, `ppiNicon`, `ppiTopcon`

---

## Конструкторы

Конструктор | Описание
--- | ---
`MainWin()`| Отображает главное окно программы.

---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`private void` | `extractPol()` | Создаёт новый экземпляр `ExtractProject` в `this.extractProject`, извлекает данные из `this.surveyProject`. Создаёт новый экземпляр `PolygonProject` в `this.polygonProject` и загружает данные из `this.extractProject`. Обновляет редактор полигонов, делая вкладку "Полигон" активной. Отображает модальное окно `ShowViewExtractPol` с анализом результатов извлечения полигона.
`public Catalog` | `getCatalog()` | Возвращает `this.catalog`.
`public ExtractProject` | `getExtractProject()` | Возвращает `this.extractProject`.
`public int` | `getHeightMain()` | Возвращает `this.hMain`.
`public Options` | `getOptions()` | Возвращает `this.options`.
`public String` | `getPathWorkDir()` | Возвращает `this.pathWorkDir`.
`public PolygonProject` | `getPolygonProject()` | Возвращает `this.polygonProject`.
`public SurveyProject` | `getSurveyProject()` | Возвращает `this.surveyProject`.
`public HashMap<String, String>` | `getTitles()` | Возвращает `this.titles`.
`public int` | `getWidthMain()` | Возвращает `this.wMain`. 
`private void` | `importLeica()` | Создаёт новый экземпляр `SurveyProject` в `this.surveyProject`. Загружает набор измерений из текстового файла формата *.gsi (Leica) и отображает его в редакторе измерений.
`private void` | `importNicon()` | Создаёт новый экземпляр `SurveyProject` в `this.surveyProject`. Загружает набор измерений из текстового файла формата *.row (Nicon) и отображает его в редакторе измерений.
`private void` | `importTopcon()` | Создаёт новый экземпляр `SurveyProject` в `this.surveyProject`. Загружает набор измерений из текстового файла формата *.txt (Topcon) и отображает его в редакторе измерений.
`public boolean` | `isCatalog()` | Возвращает `this.isCatalog`.
`private void` | `loadCatalog` | Создаёт новый экземпляр `Catalog` в `this.catalog` и загружает в него данные из текстового файла, выбранного пользователем.
`public static void` | `main(String[] args)` | Точка входа в программу.
`private void` | `newFile()` | В зависимости от активной вкладки ("Измерения" или "Полигон"), создаёт либо новый экземпляр `SurveyProject` в `this.surveyProject` с одной пустой стацией и одним пустым измерением, либо новый экземпляр `PolygonProject` в `this.polygonProject` с одной пустой записью. Обновляет редактор измерений/полигона.
`private void` | `openFile()` | В зависимости от активной вкладки ("Измерения" или "Полигон"), создаёт либо новый экземпляр `SurveyProject` в `this.surveyProject` и загружает его данными из файла *.tah, выбранного пользователем, либо новый экземпляр `PolygonProject` в `this.polygonProject` и загружает его данными из файла *.pol, выбранного пользователем. Обновляет редактор измерений/полигона.
`private void` | `processSourceData()` | Если активна вкладка "Измерения", выполняет обработку набора измерений и предлагает пользователю сохранить на диске результаты в файле *.dat. Если активна вкладка "Полигон", выполняет уравнивание полигона, выводит в окно редактора полигона величины полученных невязок.
`private void` | `reloadPolygonEditor()` | Обновляет панель редактора полигона.
`private void` | `reloadSurveyEditor()` | Обновляет панель редактора измерений.
`private void` | `save()` | Сохраняет данные в файле *.tah / *.pol, в контексте активной вкладки "Измерения" / "Полигон". Если `this.surveyProject.getAbsolutePath` / `this.polygonProject.getAbsolutePath` не определены, предлагает пользователю выбрать или создать файлы "вручную".
`private void` | `saveAs()` | Сохраняет данные в файле *.tah / *.pol, в контексте активной вкладки "Измерения" / "Полигон". Предлагает пользователю выбрать или создать файлы "вручную".
`private void` | `setControlsOff()` | Делает недоступными для пользователя следующие компоненты UI: `fSave`, `fSaveAs`, `tExtractPol`, `btnSave`, `btnRun`, `btnLoadCat`, `btnView`, `tLoadCat`, `tView`
`private void` | `setControlsOff()` | Делает доступными для пользователя следующие компоненты UI: `fSave`, `fSaveAs`, `tExtractPol`, `btnSave`, `btnRun`, `btnLoadCat`, `btnView`, `tLoadCat`, `tView`
`private void` | `setUIFont(javax.swing.plaf.FontUIResource f)` | Устанавливает основной шрифт для пользовательского интерфейса программы.
`public void` | `translate()` | Устанавливает текстовые элементы пользовательского интерфейса в соответствии с языковыми настройками программы.
`private void` | `updateBasePoints()` | Заменяет координаты "опорных" точек в `this.surveyProject` или `this.polygonProject` при совпадении названий с точками в `this.catalog` координатами из `this.catalog`.
`private void` | `viewResult()` | Обрабатывает данные. Отображает результаты в модальных окнах `ShowViewResults` или `ShowViewAdjustment`
