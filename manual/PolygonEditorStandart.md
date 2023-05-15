
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# PolygonEditorStandart Класс

---

## Определение

Представляет графические компоненты для отображения и редактирования полигона.

---

## Наследование

`java.lang.Object` -> `javax.swing.JPanel`-> `taheoport.PolygonEditorStandart`

---

## Поля

Модификатор и тип | Поле | Описание
--- | ---|---
`private final JLabel` | `lblAngleResidue` | Угловая невязка полигона, сек.
`private final JLabel` | `lblFXResidue` | Линейная невязка по оси X, м.
`private final JLabel` | `lblFYResidue` | Линейная невязка по оси Y, м.
`private final JLabel` | `lblFAbsoluteResidue` | Абсолютная линейная невязка полигона, м.
`private final JLabel` | `lblFRelativeResidue` | Относительная линейная невязка полигона.
`private final JLabel` | `lblPerValue` | Периметр полигона, м.
`private final JLabel` | `lblHeightResidue` | Высотная невязка полигона, мм.
`private final MainWin` | `parentFrame` | Родительский `MainWin`.
`private int` | `selColumn` | Индекс выделенного столбца в таблице `tblStations`.
`private int` | `selRow` | Индекс выделенной строки в таблице `tblStations`.
`private JTable` | `tblStation` | Таблица, представляющая данные полигона.
`private TmodelPolygonStations` | `tmPolygonStations` | Экземпляр внутреннего класса `TmodelPolygonStations`, описывающий состав и поведение таблицы `tblStations`.

---

## Конструкторы

Конструктор | Описание
--- | ---
`PolygonEditorStandart(MainWin frame)` | Создаёт экземпляр `PolygonEditorStandart`, отображающий данные `parentFrame.getPolygonProject` и элементы пользовательского интерфейса для редактирования и обработки полигона.

---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`public void` | `setBindings()` | Устанавливает значения невязок полигона на панели невязок.
`public void` | `setFocusTable()` | Устанавливает фокус в таблице `tblStations`.
