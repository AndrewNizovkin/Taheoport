
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# PolygonStation Класс

---

## Определение

Инкапсулирует параметры станции полигона, необходимые для его (полигона) уравнивания, получения координат и финальных отчётов.

---

## Наследование

`java.lang.Object` -> `taheoport.PolygonStation`

---

## Поля

Модификатор и тип | Поле | Описание
--- | ---|---
`private double` | `dX` | Приращение координат по оси X, м.
`private double` | `dY` | Приращение координат по оси Y, м.
`private String` | `dZ` | Приращение координат по оси Z (превышение), м.
`private double` | `ddX` | Поправка к приращению координат по оси X, м.
`private double` | `ddY` | Поправка к приращению координат по оси Y, м.
`private double` | `ddZ` | Поправка к приращению координат по оси Z, м.
`private double` | `ddHor` | Поправка к горизонтальному углу, Г.ММСС.
`private double` | `direction` | Дирекционный угол направления на переднюю точку, радиан.
`private String` | `hor` | Горизонтальный угол между направлением на заднюю и переднюю точки, Г.ММСС.
`private String` | `line` | Горизонтальное проложение линии на переднюю точку, м.
`private String` | `name` | Наименование станции полигона.
`private boolean` | `status` | Признак принадлежности точки к базисным (твёрдым) точкам с известными координатами.
`private String` | `x` | Координата X станции полигона, м.
`private String` | `y` | Координата Y станции полигона, м.
`private String` | `z` | Координата Z станции полигона, м.

---

## Конструкторы

Конструктор | Описание
--- | ---
`PolygonStation()` | Создаёт пустой экземпляр `PolygonStation`.

---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`public double` | `getDDhor()` | Возвращает `this.ddHor`.
`public double` | `getDDX()` | Возвращает `this.ddX`.
`public double` | `getDDY()` | Возвращает `this.ddY`.
`public double` | `getDirection()` | Возвращает `this.direction`.
`public double` | `getDX()` | Возвращает `this.dX`.
`public double` | `getDY()` | Возвращает `this.dY`.
`public String` | `getDZ()` | Возвращает `this.dZ`.
`public String` | `getHor()` | Возвращает `this.hor`.
`public String` | `getLine()` | Возвращает `this.line`.
`public String` | `getName()` | Возвращает `this.name`.
`public boolean` | `getStatus()` | Возвращает `this.status`.
`public String` | `getX()` | Возвращает `this.x`.
`public String` | `getY()` | Возвращает `this.y`.
`public String` | `getZ()` | Возвращает `this.z`.
`public void` | `setDDHor(double ddHor)` | Устанавливает `this.ddHor`.
`public void` | `setDDX(double ddX)` | Устанавливает `this.ddX`.
`public void` | `setDDY(double ddY)` | Устанавливает `this.ddY`.
`public void` | `setDDZ(double ddZ)` | Устанавливает `this.ddZ`.
`public void` | `setDirection(double direction)` | Устанавливает `this.direction`.
`public void` | `setDX(double dX)` | Устанавливает `this.dX`.
`public void` | `setDY(double dY)` | Устанавливает `this.dY`.
`public void` | `setDZ(String dZ)` | Устанавливает `this.dZ`.
`public void` | `setHor(String hor)` | Устанавливает `this.hor`.
`public void` | `setLine(String line)` | Устанавливает `this.line`.
`public void` | `setName(String name)` | Устанавливает `this.name`.
`public void` | `setStatus(boolean status)` | Устанавливает `this.status`.
`public void` | `setX(String x)` | Устанавливает `this.x`.
`public void` | `setY(String y)` | Устанавливает `this.y`.
`public void` | `setZ(String z)` | Устанавливает `this.z`.
