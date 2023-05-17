
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# ShowViewAdjustment Класс

---

## Определение

Отображает результаты уравнивания полигона.

---

## Наследование

`java.lang.Object` -> `javax.swing.JDialog` -> `taheoport.ShowViewAdjustment`

---

## Поля

Модификатор и тип | Поле | Описание
--- | ---|---
`private final MainWin` | `parentFrame` | Родительский `MainWin`.
`private final JPanel` | `pnlNXYZ` | Отображает каталог координат.
`private final PolygonPaintPanel` | `pnlViewNXYZ` | Отображает схему полигона.
`private int` | `sellrow` | Индекс выделенной строки каталога координат.
`private JTabbedPane` | `tp` | Служит контейнером для панелей, отображающих каталог координат со схемой `pnlNXYZ`, ведомость вычисления координат `spReportXY` и ведомость вычисления высот `spReportZ`.

---

## Конструкторы

Конструктор | Описание
--- | ---
`ShowViewAdjustment(MainWin parentFrame)` | Создаёт экземпляр `ShowViewAdjustment`, включающий панель с вкладками, отображающими результаты уравнивания полигона.

---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`private void` | `reloadPnlViewNXYZ()` | Обновляет панель, отображающую схему хода `pnlViewNXYZ`.
