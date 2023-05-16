
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# ShowChangeAngle Класс

---

## Определение

Отображает форму для внесения изменений в угловые величины набора измерений.

---

## Наследование

`java.lang.Object` -> `javax.swing.JDialog` -> `taheoport.ShowChangeAngle`

---

## Реализованные интерфейсы

`ChangeListener`, `ActionListener`

---

## Поля

Модификатор и тип | Поле | Описание
--- | ---|---
`private final JLabel` | `lblOffset` | Пояснительная надпись для поля ввода поправки в угол.
`private final MainWin` | `parentFrame` | Родительский MainWin.
`private final JRadioButton` | `rbOffset` | Выбор изменения путём введения смещения в угол.
`private final JRadioButton` | `rbCopy` | Выбор изменения путём копирования значения из следующей записи.
`private final String` | `title` | Заголовок формы.
`private final JTextField` | `tfOffset` | Текстовое поле для ввода смещения.

---

## Конструкторы

Конструктор | Описание
--- | ---
`ShowChangeAngle(MainWin parentFrame, String title)` | Отображает форму для внесения изменения угловых величин набора измерения с заголовком `title`.

---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`public void` | `actionPerformed(ActionEvent e)` | Вносит изменения в настройки программы `parentFrame.getOption`, которые считываются и обрабатываются вызывающим кодом.
`public void` | `stateChanged(ChangeEvent e)` | Вносит изменения в настройки программы `parentFrame.getOption`, которые считываются и обрабатываются вызывающим кодом. Перезаписывает файл настроек.
`private void` | `updateStatus()` | Обновляет статус доступности для компонентов формы в соответствии с настройками программы.
