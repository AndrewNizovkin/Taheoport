
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# SurveyProject Класс

---

## Определение

Инкапсулирует набор измерений. Включает методы для импорта данных из различных источников(типов тахеометров), математической обработки и предоставления финальных отчётов.
---

## Наследование

`java.lang.Object` -> `taheoport.SurveyProject`

---

## Поля

Модификатор и тип | Поле | Описание
--- | ---|---
`private String` | `absoluteTahPath` | Путь к файлу `*.tah`.
`private final LinkedList <SurveyStation>` | `surveyStations` | Список станций.
`private final MainWin` | `parentFrame` | Родительский `MainWin`.

---

## Конструкторы

Конструктор | Описание
--- | ---
`SurveyProject(MainWin parentFrame)` | Создаёт пустой экземпляр `SurveyProject`.

---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`public SurveyStation` | `addStation()` | Добавляет новый пустой экземпляр `SurveyStation` в конец списка станций `surveyStations`. Возвращает ссылку на добавленный элемент.
`public SurveyStation` | `addStation(int index)` | Добавляет новый пустой экземпляр `SurveyStation` в список станций `surveyStations` на позицию `index`. Возвращает ссылку на добавленный элемент.
`public SurveyStation` | `addStation(String sName, String sXst, String sYst, String sZst, String sNameOr, String sXor, String sYor, String sZor, String sI)` | Добавляет новый экземпляр `SurveyStation` с переданными параметрами в конец списка станций `surveyStations`. Возвращает ссылку на добавленный элемент.
`public void` | `addStation(SurveyStation surveyStation)` | Добавляет `surveyStation` в конец списка станций `surveyStations`.
`public String` | `getAbsoluteTahPath()` | Возвращает this.absoluteTahPath.
`public LinkedList<String>` | `getPicketsList()` | Возвращает каталог координат пикетов.
`public LinkedList<String>` | `getReportList()` | Возвращает ведомость вычисления координат.
`public SurveyStation` | `getStation(int index)` | Возвращает экземпляр `SurveyStation` из позиции `index` списка станций `surveyStations`.
`public LinkedList<String>` | `getTahList()` | Возвращает список для записи набора измерений в файл `*.tah`.
