
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# Shell Класс

---

## Определение

Представляет текстовые элементы для компонентов пользовательского интерфейса и финальных отчётов в соответствии с выбранным в настройках языком.

---

## Наследование

`java.lang.Object` -> `taheoport.gui.Shell`

---

## Поля

Модификатор и тип | Поле | Описание
--- | ---|---
`private final MainWin` | `parentFrame` | Родительский MainWin.

---

## Конструкторы

Конструктор | Описание
--- | ---
`Shell(MainWin parentFrame)` | Создаёт экземпляр `Shell`.

---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`public LinkedList<String>` | `getLicense()` | Возвращает текст лицензионного соглашения.
`public HashMap<String, String>` | `getTitles()` | Возвращает набор текстовых элементов для компонентов пользовательского интерфейса.
`public HashMap<String, String>` | `getTitlesReports()` | Возвращает набор текстовых элементов для компонентов финальных отчётов.
`public LinkedList<String>` | `getTopReportExtract()` | Возвращает шапку для отчёта об извлечении полигона из набора измерений.
`public LinkedList<String>` | `getTopReportSurvey()` | Возвращает шапку для отчёта о математической обработке набора измерений.
`public LinkedList<String>` | `getTopReportXY()` | Возвращает шапку для отчёта об уравнивании полигона в плане.
`public LinkedList<String>` | `getTopReportZ()` | Возвращает шапку для отчёта об уравнивании полигона по высоте.