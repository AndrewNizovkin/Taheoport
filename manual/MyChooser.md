
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# MyChooser Класс

---

## Определение

Включает методы для чтения и записи файлов на диск

---

## Наследование

`java.lang.Object` -> `taheoport.controllers.MyChooser`

---

## Поля

Модификатор и тип | Поле | Описание
--- | ---|---
`private final MainWin` | `parentFrame` | Родительский MainWin.

---

## Конструкторы

Конструктор | Описание
--- | ---
`MyChooser(MainWin parentFrame)`| Создаёт экземпляр MyChooser

---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`public LinkedList<String>` | `readTextFile(String ... array)` | Возвращает список строк в порядке очереди, считанных из текстового файла. Варианты: `readTextFile(String path)`, `readTextFile(String path, String filter, String title)`. Где `path` - путь к файлу, `filter` - расширение файла, `title` - текст заголовка диалога выбора файла.
`public String` | `writeTextFile(String absolutePath, LinkedList<String> list)` | Записывает строки из `list` в текстовый файл, путь к которому `absolutePath`. Возвращает `absolutePath`.
`public String` | `writeTextFile(String path, String filter, String title, LinkedList<String> list)` | Записывает строки из `list` в текстовый файл, выбираемый пользователем. Где `path` - путь к папке открываемой диалогом с загаловком `title`, `filter` - расширение файла. Возвращает `absolutePath` - путь к файлу.
