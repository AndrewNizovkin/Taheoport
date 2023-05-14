
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# PaintPoint Класс

---

## Определение

Представляет пространственные координаты точки в системах координат съёмки и панели отображения. 

---

## Наследование

`java.lang.Object` -> `taheoport.PaintPoint`

---

## Поля

Модификатор и тип | Поле | Описание
--- | ---|---
`private String` | `name` | Наименование точки.
`private String` | `x` | Координата X точки в исходной СК, м.
`private String` | `y` | Координата Y точки в исходной СК, м.
`private String` | `z` | Координата Z точки в исходной СК, м.
`private String` | `xOr` | Координата X ориентира (родителя) в исходной СК, м.
`private String` | `yOr` | Координата Y ориентира (родителя) в исходной СК, м.
`private boolean` | `status` | Статус принадлежности точки к твёрдым (базисным.)
`private int` | `xPaint` | Координата X точки в СК панели отображения, м.
`private int` | `yPaint` | Координата Y точки в СК панели отображения, м.
`private int` | `xOrPaint` | Координата X ориентира (родителя) в СК панели отображения, м.
`private int` | `yOrPaint` | Координата Y ориентира (родителя) в СК панели отображения, м.

---

## Конструкторы

Конструктор | Описание
--- | ---
`PaintPoint(String name, String x, String y, String z)`| Создаёт экземпляр `PaintPoint`, с пространственными координатами `x`, `y`, `z` в исходной системе координат (системе координат съёмки).

---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`public String` | `getName()` | Возвращает `this.name`.
`public boolean` | `getStatus()` | Возвращает `this.status`.
`public String` | `getX()` | Возвращает `this.x`.
`public double` | `getxDbl()` | Возвращает `Double.parseDouble(this.x)`.
`public String` | `getxOr()` | Возвращает `this.xOr`.
`public double` | `getxOrDbl()` | Возвращает `Double.parseDouble(this.xOr)`.
`public int` | `getxOrPaint()` | Возвращает `this.xOrPaint`.
`public int` | `getxPaint()` | Возвращает `this.xPaint`.
`public String` | `getY()` | Возвращает `this.y`.
`public double` | `getyDbl()` | Возвращает `Double.parseDouble(this.y)`.
`public String` | `getyOr()` | Возвращает `this.yOr`.
`public double` | `getyOrDbl()` | Возвращает `Double.parseDouble(this.yOr)`.
`public int` | `getyOrPaint()` | Возвращает `this.yOrPaint`.
`public int` | `getyPaint()` | Возвращает `this.yPaint`.
`public String` | `getZ()` | Возвращает `this.z`.
`public void` | `setName(String name)` | Устанавливает `this.name`.
`public void` | `setStatus(boolean status)` | Устанавливает `this.status`.
`public void` | `setX(String x)` | Устанавливает `this.x`.
`public void` | `setxOr(String xOr)` | Устанавливает `this.xOr`.
`public void` | `setxOrPaint(int xOrPaint)` | Устанавливает `this.xOrPaint`.
`public void` | `setxPaint(int xPaint)` | Устанавливает `this.xPaint`.
`public void` | `setY(String y)` | Устанавливает `this.y`.
`public void` | `setyOr(String yOr)` | Устанавливает `this.yOr`.
`public void` | `setyOrPaint(int yOrPaint)` | Устанавливает `this.yOrPaint`.
`public void` | `setyPaint(int yPaint)` | Устанавливает `this.yPaint`.
`public void` | `setZ(String z)` | Устанавливает `this.z`.