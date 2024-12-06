
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# ShowCatatalog Класс

---

## Определение

Отображает каталог координат точек опорной сети. Включает метод для замены координат в целевом объекте на значения из каталога.

---

## Наследование

`java.lang.Object` -> `javax.swing.JDialog` -> `taheoport.gui.ShowCatalog`

---

## Поля

Модификатор и тип | Поле | Описание
--- | ---|---
`private final Catalog` | `catalogRepository` | Отображаемый каталог координат.
`private final int` | `index` | Индекс станции, в которой заменяются координаты.
`private final MainWin` | `parentFrame` | Родительский MainWin.
`private final int` | `selRow` | Индекс выделенной строки в отображаемой таблице координат опорных пунктов.
`private final String` | `target` | Название параметра, в котором заменяются координаты. `StationName` или `OrName` или `TheoStation`.
`private final JTable` | `tblPoints` | Представляет таблицу опорных пунктов.

---

## Конструкторы

Конструктор | Описание
--- | ---
`ShowCatalog(MainWin frame, int index, String target)` | Отображает загруженный каталог координат опорных точек `frame.getCatalog()`.

---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`private void` | `replaceCoordinates()` | Заменяет координаты в `target` на соответсвующие `selRow` из каталога опорных точек.
