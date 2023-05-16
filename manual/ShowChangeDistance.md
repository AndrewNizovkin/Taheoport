
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# ShowChangeDistance Класс

---

## Определение

Отображает форму для внесения изменений пользователем в поле "расстояние" таблицы результатов измерений редактора измерений. Включает элементы управления для выбора метода измерения и ввода величины домера.

---

## Наследование

`java.lang.Object` -> `javax.swing.JDialog` -> `taheoport.ShowChangeDistance`

---

## Реализованные интерфейсы

`ChangeListener`, `ActionListener`

---

## Поля

Модификатор и тип | Поле | Описание
--- | ---|---
`private final MainWin` | `parentFrame` | Родительский MainWin.
`private LinearOffsetPaintPanel` | `pnlPaintPanel` | Отображает схему определения домера в расстояние.
`private final JRadioButton` | `rbHor` | Определяет горизонтальный тип домера.
`private final JTextField` | `tfOffset` | Текстовое поле для ввода домера.

---

## Конструкторы

Конструктор | Описание
--- | ---
`ShowChangeDistance(MainWin parentFrame)` | Отображает форму для внесения изменений в поле "расстояние" таблицы результатов измерений редактора измерений.

---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`public void` | `actionPerformed(ActionEvent e)` | Вносит изменения в настройки программы `parentFrame.getOption`, которые считываются и обрабатываются вызывающим кодом.
`private void` | `reloadPaintPanel()` | Обновляет `pnlPaintPanel`.
`public void` | `stateChanged(ChangeEvent e)` | Вносит изменения в настройки программы `parentFrame.getOption`, которые считываются и обрабатываются вызывающим кодом. Перезаписывает файл настроек.
