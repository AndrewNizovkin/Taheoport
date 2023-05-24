
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
`public boolean` | `containPolygon()` | Проверяет возможность извлечения полигона из набора измерений.
`public String` | `getAbsoluteTahPath()` | Возвращает `this.absoluteTahPath`.
`public LinkedList<String>` | `getPicketsList()` | Возвращает каталог координат пикетов.
`public LinkedList<String>` | `getReportList()` | Возвращает ведомость вычисления координат.
`public SurveyStation` | `getStation(int index)` | Возвращает экземпляр `SurveyStation` из позиции `index` списка станций `surveyStations`.
`public LinkedList<String>` | `getTahList()` | Возвращает список для записи набора измерений в файл `*.tah`.
`public SurveyProject` | `loadLeicaList(LinkedList<String> list)` | Импортирует данные в `this.surveyStations` из списка формата GSI(Leica). Возвращает `this`.
`public SurveyProject` | `loadNiconList(LinkedList<String> list)` | Импортирует данные в `this.surveyStations` из списка формата RAW(Nicon). Возвращает `this`.
`public SurveyProject` | `loadTahList(LinkedList<String> list)` | Импортирует данные в `this.surveyStations` из списка формата TAH(внутренний формат сохранения набора измерений). Возвращает `this`.
`public SurveyProject` | `loadTopconList(LinkedList<String> list)` | Импортирует данные в `this.surveyStations` из списка формата TXT(Topcon). Возвращает `this`.
`public void` | `processSourceData()` | Выполняет математическую обработку набора измерений. Для всех пикетов всех станций из списка станций `this.surveyStations` определяет `direction`, `dX`, `dY`, `dZ`, `x`, `y`, `z`.
`public void` | `removeStation(int index)` | Удаляет из списка станций `this.surveyStations` элемент с индексом `index`.
`public void` | `setAbsoluteTahPath(String absoluteTahPath)` | Устанавливает `this.absoluteTahPath`.
`public int` | `sizeStations()` | Возвращает колличество станций в списке станций `this.surveyStations`

