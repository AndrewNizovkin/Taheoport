
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# ShowOptions Класс

---

## Определение

Отображает окно для установки настроек программы.

---

## Наследование

`java.lang.Object` -> `javax.swing.JDialog` -> `taheoport.gui.ShowOptions`

---

## Поля

Модификатор и тип | Поле | Описание
--- | ---|---
`private final JButton` | `btnApprove` | Записывает изменённые настройки в файл. action -> `this.option.saveOptions`. Закрывает окно.
`private final JButton` | `btnCancel` | Закрывает окно. Изменения не сохраняются.
`private final JButton` | `btnFolder` | Открывает диалог выбора рабочей папки на диске. Устанавливает значение для `tfPathWorkDir`.
`private final JComboBox<String>` | `cbFAbs` | Предоставляет выбор значения допустимой абсолютной линейной невязки полигона.
`private final JComboBox<String>` | `cbFH` | Предоставляет выбор формулы для определения значения допустимой высотной невязки полигона.
`private final JComboBox<String>` | `cbFHor` | Предоставляет выбор формулы для определения значения допустимой угловой невязки полигона.
`private final JComboBox<String>` | `cbFOtn` | Предоставляет выбор значения допустимой относительной линейной невязки полигона.
`private final JLabel` | `lblFAbs` | Текстовая метка для выбора допустимой абсолютной линейной невязки.
`private final JLabel` | `lblFH` | Текстовая метка для выбора допустимой высотной невязки.
`private final JLabel` | `lblFHor` | Текстовая метка для выбора допустимой угловой невязки.
`private final JLabel` | `lblLanguage` | Текстовая метка для выбора языка.
`private final JLabel` | `lblFOtn` | Текстовая метка для выбора допустимой относительной линейной невязки.
`private final JLabel` | `lblPrefixEX` | Текстовая метка для выбора префикса свободной станции при извлечении полигона из набора измерений.
`private final Options` | `options` | Представляет настройки программы и включает методы для из считывания и записи.
`private final MainWin` | `parentFrame` | Родительский `MainWin`.
`private final JPanel` | `pnlAcceptable` | Панель допустимых невязок полигона.
`private final JPanel` | `pnlExtractor` | Панель настроек для извлечения полигона из набора измерений.
`private final JPanel` | `pnlOrientStation` | Панель настроек ориентирования тахеометра.
`private final JPanel` | `pnlWorkDir` | Панель выбора рабочей папки.
`private final JRadioButton` | `rbFirst` | Выбор ориентирования тахеометра измерением на первую точку.
`private final JRadioButton` | `rbZero` | Выбор ориентирования тахеометра нолём на ориентир.
`private final JTabbedPane` | `tp` | Служит контейнером для панелей "Общие" и "Допуски".
`private final JTextField` | `tfPathWorkDir` | Представляет путь к рабочей папке.

---

## Конструкторы

Конструктор | Описание
--- | ---
`ShowOptions(MainWin parentFrame)` | Отображает окно для установки настроек программы.

---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`private void` | `setOrientStation()` | Устанавливает выбранный тип ориентирования тахеометра.
`private void` | `showOrientStation()` | Отображает выбранный тип ориентирования тахеометра.
`private void` | `translate()` | Обновляет текстовые элементы текущего экземпляра `ShowOptions` в соответствии с выбранным языком.
