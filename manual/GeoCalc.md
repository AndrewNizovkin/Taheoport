
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# GeoCalc Класс

---

## Определение

Включает в себя методы для решения основных геодезических задач.

---

## Наследование

`java.lang.Object` -> `taheoport.controllers.GeoCalc`

---

## Конструкторы

Конструктор | Описание
--- | ---
`GeoCalc())`| Создаёт пустой экземпляр объекта.

---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`public double` | `getDir(String xA, String yA, String xB, String yB)` |  Возвращает дирекционный угол направления A -> B, радиан. Аргументы `xA`, `yA`, `xB`, `yB` прямоугольные координаты, м.
`public double` | `getDX(double horLine, double direction)` |  Возвращает приращение по X (проекцию линии на ось X), м. `horLine` - горизонтальное проложение (длина линии, приведеная к горизонтальной плоскости), м. `direction` - дирекционный угол линии, радиан. 
`public double` | `getDY(double horLine, double direction)` |  Возвращает приращение по Y (проекцию линии на ось Y), м. `horLine` - горизонтальное проложение (длина линии, приведеная к горизонтальной плоскости), м. `direction` - дирекционный угол линии, радиан.
`public double` | `getHorline(String line, String vert)` |  Возвращает горизонтальное проложение в метрах. `line` - длина линии, м. `vert` - угол наклона линии, ГГГ.ММСС.
