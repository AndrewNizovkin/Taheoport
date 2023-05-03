
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# ExtractProject Класс

---

## Определение

Представляет результаты извлечения полигона из набора измерений. Включает методы для извлечения данных и предоставления результатов вычислений в виде отчёта.

---

## Наследование

`java.lang.Object` -> `java.util.LinkedList<ExtractStation>` -> `taheoport.ExtractProject`

---

## Поля

Модификатор и тип | Поле | Описание
--- | ---|---
`private MainWIn` | `parentFrame` | Родительский MainWin


---

## Конструкторы

Конструктор | Описание
--- | ---
`ExtractProject(MainWin frame)`| Создаёт пустой экземпляр объекта.

---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`public LinkedList<String>` | `extractPolygonProject` |  Извлекает данные из parentFrame.getSurveyProject. Добавляет экземпляры ExtractStation как элементы списка.
`public LinkedList` | `getExtractReport` |  Возвращает отчёт о результатах извлечения полигона в виде очереди строк.





