
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# Picket Класс

---

## Определение

Представляет данные для определения координат съёмочной точки (пикета).

---

## Наследование

`java.lang.Object` -> `taheoport.model.Picket`

---

## Поля

Модификатор и тип | Поле | Описание
--- | ---|---
`private String` | `pName` | Наименование пикета.
`private String` | `pAltName` | Альтернативное наименование (номер) пикета.
`private String` | `line` | Наклонное расстояние от станции до пикета, м.
`private String` | `hor` | Направление на пикет, Г.ММСС.
`private String` | `vert` | Вертикальный угол наклона линии, Г.ММСС.
`private String` | `v` | Высота наведения (высота вехи), м.
`private double` | `direction` | Дирекционный угол направления от станции на пикет.
`private double` | `dX` | Приращение по оси X, м.
`private double` | `dY` | Приращение по оси Y, м.
`private double` | `dZ` | Превышение между станцией и пикетом.
`private double` | `x` | Координата X пикета, м.
`private double` | `y` | Координата Y пикета, м.
`private double` | `z` | Координата Z пикета, м.
`private double` | `horLine` | Горизонтальное проложение (проекция расстояния на горизонтальную плоскость), м.

---

## Конструкторы

Конструктор | Описание
--- | ---
`Picket(String pName, String pLine, String pHor, String pVert, String pV, String pAltName, SurveyStation station)` | Создаёт экземпляр `Picket`. Где `pName` - название пикета, `pLine` - наклонное расстояние, `pHor` - горизонтальное направление, `pVert` - угол наклона линии, `pV` - высота цели, `pAltName` - альтернативное название пикета, `station` - родительская станция.
---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`public String` | `getDX()` | Возвращает `this.dX`, м.
`public String` | `getDY()` | Возвращает `this.dY`, м.
`public String` | `getDZ()` | Возвращает `this.dZ`, м.
`public String` | `getHor()` | Возвращает `this.hor`, Г.ММСС.
`public String` | `getLine()` | Возвращает `this.line`, м..
`public String` | `getPDirection()` | Возвращает `this.direction`, Г.ММСС.
`public String` | `getHorLine()` | Возвращает `this.horLine`, м.
`public String` | `getpName()` | Возвращает `this.pName`.
`public String` | `getV()` | Возвращает `this.v`, м.
`public String` | `getVert()` | Возвращает `this.vert`, Г.ММСС.
`public String` | `getX()` | Возвращает `this.x`, м.
`public String` | `getY()` | Возвращает `this.y`, м.
`public String` | `getZ()` | Возвращает `this.z`, м.
`public void` | `setDirection(double direction)` | Устанавливает `this.direction`, радиан.
`public void` | `setdX(double dX)` | Устанавливает `this.dX`, м.
`public void` | `setdY(double dX)` | Устанавливает `this.dY`, м.
`public void` | `setdZ(double dX)` | Устанавливает `this.dZ`, м.
`public void` | `setHor(String hor)` | Устанавливает `this.hor`, Г.ММСС.
`public void` | `setHorLine(double horLine)` | Устанавливает `this.horLine`, м.
`public void` | `setLine(String line)` | Устанавливает `this.line`, м.
`public void` | `setpName(String pName)` | Устанавливает `this.pName`.
`public void` | `setV(double dX)` | Устанавливает `this.v`, м.
`public void` | `setVert(String vert)` | Устанавливает `this.vert`, Г.ММСС.
`public void` | `setX(double x)` | Устанавливает `this.x`, м.
`public void` | `setY(double y)` | Устанавливает `this.y`, м.
`public void` | `setZ(double z)` | Устанавливает `this.z`, м.
`public String` | `toString()` | Переопределяет метод `toString`