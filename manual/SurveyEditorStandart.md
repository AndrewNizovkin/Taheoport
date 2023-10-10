
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# SurveyEditorStandart Класс

---

## Определение

Представляет компоненты для отображения и редактирования набора измерений. Включает внутренние классы `SetCoordinates`, `TmodelPickets`.

---

## Наследование

`java.lang.Object` -> `javax.swing.JPanel` -> `taheoport.gui.SurveyEditorStandart`

---

## Поля

Модификатор и тип | Поле | Описание
--- | ---|---
`private JButton` | `btnDeleteRow` | Удаляет выбранную строку из таблицы измерений `tblPickets`.
`private JButton` | `btnInsertRowBefore` | Вставляет пустую строку в таблицу измерений `tblPickets` перед выбранной.
`private JButton` | `btnInsertRowAfter` | Вставляет пустую строку в таблицу измерений `tblPickets` после выбранной.
`private JButton` | `btnChangeDistance` | Отображает модальное окно, предоставляющее пользователю ввести домер в расстояние для выбранной строки в таблице `tblPickets`. action -> `changeDistance`.
`private JButton` | `btnChangeDirection` | Отображает модальное окно, предоставляющее пользователю изменить горизонтальное направление для выбранной строки в таблице `tblPickets`. action -> `changeDirection`.
`private JButton` | `btnChangeTilt` | Отображает модальное окно, предоставляющее пользователю изменить угол наклона линии для выбранной строки в таблице `tblPickets`. action -> `changeTilt`.
`private JButton` | `btnDeleteStation` | Удаляет выбранную в списке `lstStations` станцию из набора измерений `surveyPoject`.
`private JButton` | `btnInsertStationBefore` | Вставляет пустую станцию перед выбранной в списке `lstStations` в набор измерений `surveyPoject`.
`private JButton` | `btnInsertStationAfter` | Вставляет пустую станцию после выбранной в списке `lstStations` в набор измерений `surveyPoject`.
`private JButton` | `btnStationName` | Вставляет координаты станции из установленного каталога координат. action -> `SetCoordinates`.
`private JButton` | `btnOrName` | Вставляет координаты ориентира из установленного каталога координат. action -> `SetCoordinates`.
`private int` | `index` | Индекс текущей(выбранной) станции.
`private JLabel` | `lblStationI` | Текстовая метка "Высота инструмента" на панели "Параметры станции".
`private JList<String>` | `lstStations` | Список, отображающий названия станций.
`private Vector<Component>` | `order` | Включает компоненты, в порядке перехода по `tab`.
`private final MainWin` | `parentFrame` | Родительский `MainWin`.
`private JPanel` | `pnlPickets` | Включает таблицу с результатами измерениями и панель инструментов для дополнительных функций редактирования.
`private JPanel` | `pnlStation` | Отображает параметры текущей станции.
`private JPanel` | `pnlStations` | Включает список с названиями станции и панель инструментов для его редактирования.
`private int` | `selRow` | Индекс текущей(выбранной) строки в таблице измерений.
`private int` | `selColumn` | Индекс текущего(выбранного) стролбца в таблице измерений.
`private JScrollPane` | `scpPickets` | Служит контейнером для таблицы `tblPickets`, содержащей результаты измерений.
`private JScrollPane` | `scpStations` | Служит контейнером для списка `lstStations`, содержащим названия станций.
`private SurveyProject` | `surveyProject` | Инкапсулирует данные набора измерений и включает методы для их математической обработки.
`private SurveyStation` | `surveyStation` | Экземпляр `SurveyStation`, соответствующий `index`.
`private JTextField` | `tfStationName` | Служит для отображения и редактирования названия станции.
`private JTextField` | `tfStationX` | Служит для отображения и редактирования координаты X станции.
`private JTextField` | `tfStationY` | Служит для отображения и редактирования координаты Y станции.
`private JTextField` | `tfStationZ` | Служит для отображения и редактирования координаты Z станции.
`private JTextField` | `tfStationI` | Служит для отображения и редактирования высоты инструмента станции.
`private JTextField` | `tfOrName` | Служит для отображения и редактирования названия ориентира.
`private JTextField` | `tfOrX` | Служит для отображения и редактирования координаты X ориентира.
`private JTextField` | `tfOrY` | Служит для отображения и редактирования координаты Y ориентира.
`private JTable` | `tblPickets` | Служит для отображения и редактирования результаты измерений на определяемые пикеты.
`private TmodelPickets` | `tmodelPickets` | Модель таблицы `tblPickets`. Инкапсулирует данные набора измерений. Включает методы для отображения и редактирования в табличном виде. Связывает отображаемые данные с `SurveyProject`.

---

## Конструкторы

Конструктор | Описание
--- | ---
`SurveyEditorStandart(MainWin parentFrame, int index)` | Создаёт экземпляр `SurveyEditorStandart`, отображающий набор измерений на станции с индексом `index`.

---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`private void` | `changeDirection()` | Отображает диалог `ShowChangeAngle`. Обновляет поле "Направление" в строке `selRow` таблицы измерений `tbl Pickets`.
`private void` | `changeDistance()` | Отображает диалог `ShowChangeDistance`. Обновляет поле "Расстояние" в строке `selRow` таблицы измерений `tbl Pickets`.
`private void` | `changeTilt()` | Отображает диалог `ShowChangeAngle`. Обновляет поле "Угол Наклона" в строке `selRow` таблицы измерений `tbl Pickets`.
`public JButton` | `getBtnOrName()` | Возвращает `this.bntOrName`.
`public JButton` | `getBtnStationName()` | Возвращает `this.bntStationName`.
`public Vector<Component>` | `getOrder()` | Возвращает `this.order`.
`private void` | `reloadStationPickets(int index)` | Обновляет содержимое панелей "Параметры станции" `pnlStation` и "Результаты измерений" `pnlPickets`  в соответствии с `index`.
`private void` | `reloadStationPickets(int index)` | Обновляет содержимое панелей "Параметры станции" `pnlStation` и "Результаты измерений" `pnlPickets`  в соответствии с `index`.
`private void` | `reloadStations(int index)` | Обновляет содержимое панели "Список станций" `pnlStations`. Устанавливает выбранный элемент списка `lstStations` равным  `index`.
`private void` | `setFocusStations()` | Устанавливает фокус на список станций `lstStations`.
`private void` | `translate()` | Обновляет текстовые элементы пользовательского интерфейса в соответствии с установленным в настройках языком.
`private void` | `updateTblPickets()` | Обновляет содержимое таблицы измерений `tblPickets`.
