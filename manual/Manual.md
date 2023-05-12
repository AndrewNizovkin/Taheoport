
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# Manual Класс

---

## Определение

Представляет встроенную справочную систему программы. Включает методы, возвращающие текст справки в соответствии с языковыми настройками программы.
---

## Наследование

`java.lang.Object` -> `taheoport.Manual`

---

## Поля

Модификатор и тип | Поле | Описание
--- | ---|---
`private final MainWin` | `parentFrame` | Родительский MainWin.
`private final String` | `newLine` | Константа "\n"
`private final Insets` | `insets` | Константа.
---

## Конструкторы

Конструктор | Описание
--- | ---
`Manual(MainWin parentFrame)`| Создаёт экземпляр Manual

---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`public JTextArea` | `getAdjustment()` | Возвращает текст раздела "Уравнивание".
`public JTextArea` | `getExtract()` | Возвращает текст раздела "Извлечение полигона".
`public JTextArea` | `getFiles()` | Возвращает текст раздела "Типы файлов".
`public JTextArea` | `getImport()` | Возвращает текст раздела "Импорт измерений".
`public JTextArea` | `getIntroduction()` | Возвращает текст раздела "Введение".
`public JTextArea` | `getMainMenu()` | Возвращает текст раздела "Главное меню".
`public JTextArea` | `getMeasurements()` | Возвращает текст раздела "Вкладка Измерения".
`public JTextArea` | `getPolygon()` | Возвращает текст раздела "Вкладка Полигон".
`public JTextArea` | `getSettings()` | Возвращает текст раздела "Настройки".
`public JTextArea` | `getToolBarDemo()` | Возвращает текст раздела "Панель инструментов - Введение".
`public JTextArea` | `getToolBarImport()` | Возвращает текст раздела "Панель инструментов - Импорт".
`public JTextArea` | `getToolBarLoadCat()` | Возвращает текст раздела "Панель инструментов - Загрузить каталог".
`public JTextArea` | `getToolBarNew()` | Возвращает текст раздела "Панель инструментов - Создать".
`public JTextArea` | `getToolBarOpen()` | Возвращает текст раздела "Панель инструментов - Открыть".
`public JTextArea` | `getToolBarRun()` | Возвращает текст раздела "Панель инструментов - Обработать (уравнять)".
`public JTextArea` | `getToolBarSave()` | Возвращает текст раздела "Панель инструментов - Сохранить".
`public JTextArea` | `getToolBarView()` | Возвращает текст раздела "Панель инструментов - Обработать и просмотреть".