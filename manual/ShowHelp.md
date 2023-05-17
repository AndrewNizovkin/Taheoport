
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# ShowHelp Класс

---

## Определение

Отображает окно для просмотра разделов встроенной справочной информации.

---

## Наследование

`java.lang.Object` -> `javax.swing.JDialog` -> `taheoport.ShowHelp`

---

## Поля

Модификатор и тип | Поле | Описание
--- | ---|---
`private final CardLayout` | `cardLayout` | Менеджер компоновки для панели отображения разделов справки.
`private final Manual` | `manual` | Предоставляет разделы справочной системы на языке, выбранном в настройках.
`private final JPanel` | `pnlViewContent` | Панель отображения разделов справки.
`private final JPanel` | `pnlToolbarDemo` | Панель для отображения возможностей главной панели инструментов программы.
`private final JScrollPane` | `spToolbarDemo` | Служит контейнером для разделов, описывающих возможности главной панели инструментов программы.
`private final JTree` | `tContent` | Представляет содержание справочной системы в виде дерева в панели содержания.
`private final JTree` | `tViewContent` | Представляет содержание справочной системы в виде дерева в панели отображения разделов справки.
`private final DefaultMutableTreeNode` | `tnContent` | Служит контейнером для узлов `tnIntroduction`, `tnTasks`, `tnInterface`, `tnFiles`.
`private final DefaultMutableTreeNode` | `tnIntroduction` | Связан с разделом "Введение".
`private final DefaultMutableTreeNode` | `tnImport` | Связан с разделом "Импорт".
`private final DefaultMutableTreeNode` | `tnExtract` | Связан с разделом "Извлечение полигона".
`private final DefaultMutableTreeNode` | `tnAdjustment` | Связан с разделом "Уравнивание полигона".
`private final DefaultMutableTreeNode` | `tnMainMenu` | Связан с разделом "Главное меню".
`private final DefaultMutableTreeNode` | `tnToolbar` | Связан с разделом "Панель Инструментов".
`private final DefaultMutableTreeNode` | `tnMeasurements` | Связан с разделом "Вкладка "Измерения"".
`private final DefaultMutableTreeNode` | `tnPolygon` | Связан с разделом "Вкладка "Полигон"".
`private final DefaultMutableTreeNode` | `tnFiles` | Связан с разделом "Типы файлов".
`private final DefaultMutableTreeNode` | `tnOptions` | Связан с разделом "Настройки".

---

## Конструкторы

Конструктор | Описание
--- | ---
`ShowHelp(MainWin parentFrame)` | Отображает окно с разделами встроенной справочной системы программы Taheoport.

---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`private void` | `updateToolbarDemo(JTextArea textArea)` | Обновляет содержимое панели отображения разделов справки в соответствии с выбранным узлом в панели содержания.
`private void` | `updateViewContent(DefaultMutableTreeNode node)` | Обновляет содержимое панели `pnlToolbarDemo`.
