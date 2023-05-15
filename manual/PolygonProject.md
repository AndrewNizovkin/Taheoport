
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# PolygonProject Класс

---

## Определение

Представляет набор станций полигона. Включает методы для просмотра, редактирования, уравнивания и предоставления финальных отчётов о математической обработке полигона.

---

## Наследование

`java.lang.Object` -> `taheoport.PolygonPaintPanel`

---

## Поля

Модификатор и тип | Поле | Описание
--- | ---|---
`private String` | `absolutePolPath` | Путь к файлу, в соответствующий данному экземпляру `PolygonProject`.
`private BindType` | `bindType` | Тип привязки полигона к опорной сети.
`private double` | `fHor` | Угловая невязка полигона, сек.
`private double` | `fX` | Линейная невязка по оси X, м.
`private double` | `fY` | Линейная невязка по оси Y, м.
`private double` | `fZ` | Высотная невязка, мм.
`private double` | `fAbs` | Абсолютная линейная невязка , м.
`private String` | `fOtn` | Относительная линейная невязка .
`private final LinkedList<PolygonStation>` | `listPolygonStation` | Список станций полигона.
`private final MainWin` | `parentFrame` | Родительский MainWin.
`private double` | `perimeter` | Периметр полигона.
`public enum` | `BindType` | Перечисление возможных типов невязок.

---

## Конструкторы

Конструктор | Описание
--- | ---
`PolygonProject(MainWin frame)` | Создаёт пустой экземпляр `PolygonProject`.

---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`public void` | `addStation(int idx)` | Вставляет пустой экземпляр `PolygonStation` в позицию `idx`.
`public void` | `addStation(PolygonStation polygonStation)` | Вставляет `polygonStation` в конец `listPolygonStation`.
`private BindType` | `defBindType()` | Определяет и возвращает `this.bindType`.
`public String` | `getAbsolutePolPath()` | Возвращает `this.absolutePolPath`.
`public BindType` | `getBindType()` | Возвращает `this.bindType`.
`public double` | `getfAbs()` | Возвращает `this.fAbs`.
`public double` | `getfHor()` | Возвращает `this.fHor`.
`public double` | `getfOtn()` | Возвращает `this.fOtn`.
`public double` | `getfX()` | Возвращает `this.fX`.
`public double` | `getfY()` | Возвращает `this.fY`.
`public double` | `getfZ()` | Возвращает `this.fZ`.
`public double` | `getPerimeter()` | Возвращает `this.perimeter`.
`public LinkedList<String>` | `getPolList()` | Возвращает список для записи в файл `*.pol`.
`public PolygonStation` | `getPolygonStation(int idx)` | Возвращает станцию с индексом `idx`.
`public LinkedList<String>` | `getReportNXYZ()` | Возвращает список-каталог координат для записи в файл `*.kat` или `*.dat`.
`public LinkedList<String>` | `getReportXY()` | Возвращает список-отчёт об уравнивании полигона в плане для записи в файл `*.txt` или отображения на панели просмотра.
`public LinkedList<String>` | `getReportZ()` | Возвращает список-отчёт об уравнивании полигона по высоте для записи в файл `*.txt` или отображения на панели просмотра.
`public int` | `getSizePolygonStations()` | Возвращает количество станций в полигоне.
`private void` | `iniDDs()` | Инициализирует (обнуляет) величины поправок `ddHor`, `ddX`, `ddY`, `ddZ` для всех элементов списка `listPolygonStations`. 
`public boolean` | `isInsertAfter(int idx)` | Проверяет возможность вставки станции в список станций после позиции `idx`.
`public boolean` | `isInsertBefore(int idx)` | Проверяет возможность вставки станции в список станций перед позицией `idx`.
`public boolean` | `isValidSourceData(int start, int finish)` | Проверяет корректность и полноту данных списка станций для уравнивания полигона.
`public PolygonProject` | `loadPolList(LinkedList<String>)` | Заполняет список станций.
`private boolean` | `noNeed()` | Проверяет наличие в полигоне определяемых станций.
`public void` | `processSourceData()` | Выполняет уравнивание полигона.
`public void` | `removeStation(int idx)` | Удаляет станцию с индексом `idx` из списка станций.
`public void` | `setAbsolutePolPath(int idx)` | Устанавливает `this.absolutePolPath`.
`private void` | `setDDHors()` | Определяет и устанавливает поправки к горизонтальным углам для всех станций полигона.
`private void` | `setDDXDDYs(int start, int end)` | Определяет линейные невязки и устанавливает поправки к приращениям координат для всех станций полигона в диапазоне от `start` до `end` включительно.
`private void` | `setDDZs(int start, int end)` | Определяет высотную невязку и устанавливает поправки к превышениям для всех станций полигона в диапазоне от `start` до `end` включительно.
`private void` | `setDirections(int start, int end)` | Определяет дирекционные углы направлений на переднюю точку для всех станций полигона в диапазоне от `start` до `end` включительно.
`private void` | `setDXDYs(int start, int end)` | Определяет приращения плановых координат для всех станций полигона в диапазоне от `start` до `end` включительно.
`private void` | `setPerimeter(int start, int end)` | Определяет периметр полигона в диапазоне от `start` до `end` включительно.
`private void` | `setXYZs(int start, int end)` | Определяет координаты точек  полигона в диапазоне от `start` до `end` включительно.

