
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# SurveyStation Класс

---

## Определение
Инкапсулирует параметры станции и набор измерений на определяемые точки(пикеты).

---

## Наследование

`java.lang.Object` -> `taheoport.model.SurveyStation`

---

## Поля

Модификатор и тип | Поле | Описание
--- | ---|---
`private String` | `name` | Наименование станции.
`private String` | `x` | Координата X станции, м.
`private String` | `y` | Координата Y станции, м.
`private String` | `z` | Координата Z станции, м.
`private String` | `nameOr` | Наименование ориентира.
`private String` | `xOr` | Координата X ориентира, м.
`private String` | `yOr` | Координата Y ориентира, м.
`private String` | `zOr` | Координата z ориентира, м.
`private String` | `vi` | Высота инструмента, м.
`private final LinkedList<Picket>` | `pickets` | Набор измерений на определяемые точки(пикеты), выполненных на данной станции.

---

## Конструкторы

Конструктор | Описание
--- | ---
`SurveyStation()` | Создаёт экземпляр `SurveyStation`. Устанавливает значения полей в начальные значения.
`SurveyStation(String name, String  x, String y, String z, String sNameOr, String sXor, String sYor, String sZor, String vi)` | Создаёт экземпляр `SurveyStation`. Устанавливает значения полей в соответствие переданным агрументам.

---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`public Picket` | `addPicket()` | Добавляет новый пустой экземпляр `Picket` в конец списка пикетов `this.pickets` и возвращает его.
`public Picket` | `addPicket(int index)` | Добавляет новый пустой экземпляр `Picket` в позицию `index` списка пикетов `this.pickets` и возвращает его.
`public Picket` | `addPicket(String pName, String pLine, String pHor, String pVert, String pV, String pAltName)` | Добавляет новый экземпляр `Picket` с переданными аргументами в конец списка пикетов `this.pickets` и возвращает его.
`public double` | `getDirection()` | Возвращает диррекционный угол направления от станции на ориентир.
`public String` | `getName()` | Возвращает `this.name`.
`public String` | `getNameOr()` | Возвращает `this.nameOr`.
`public Picket` | `getPicket(int index)` | Возвращает `index` элемент списка пикетов`this.pickets.get(index)`.
`public String` | `getVi()` | Возвращает `this.vi`.
`public String` | `getX()` | Возвращает `this.X`.
`public String` | `getxOr()` | Возвращает `this.sOr`.
`public String` | `getxY()` | Возвращает `this.y`.
`public String` | `getyOr()` | Возвращает `this.yOr`.
`public String` | `getZ()` | Возвращает `this.z`.
`public String` | `getzOr()` | Возвращает `this.zOr`.
`public Picket` | `removePicket(int index)` | Удаляет элемент с индексом `index` из списка пикетов `this.pickets`.
`public void` | `setName(String name)` | Устанавливает `this.name`.
`public void` | `setNameOr(String nameOr)` | Устанавливает `this.nameOr`.
`public void` | `setVi(String vi)` | Устанавливает `this.vi`.
`public void` | `setX(String x)` | Устанавливает `this.x`.
`public void` | `setxOr(String xOr)` | Устанавливает `this.xOr`.
`public void` | `setY(String y)` | Устанавливает `this.y`.
`public void` | `setyOr(String yOr)` | Устанавливает `this.yOr`.
`public void` | `setyZ(String z)` | Устанавливает `this.z`.
`public void` | `setyzOr(String zOr)` | Устанавливает `this.zOr`.
`public int` | `sizePickets()` | Возвращает количество пикетов в списке пикетов `this.pickets`.
`public String` | `toString()` | Возвращает строку из разделёных пробелом полей: `name`, `x`, `y`, `z`, `nameOr`, `xOr`, `yOr`, `zOr`, `vi`. 