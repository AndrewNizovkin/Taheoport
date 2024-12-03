
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# PaintProject Класс

---

## Определение

Представляет набор точек для отрисовки в панели отображения. 

---

## Наследование

`java.lang.Object` -> `LinkedList<PaintPoint>` -> `taheoport.model.PaintProject`

---

## Поля

Модификатор и тип | Поле | Описание
--- | ---|---
`private double` | `xMin` | Наименьшая координата X.
`private double` | `xMax` | Наибольшая координата X.
`private double` | `yMin` | Наименьшая координата Y.
`private double` | `xMax` | Наибольшая координата Y.
`private int` | `x0` | Наименьшая координата X в СК панели отображения.
`private int` | `y0` | Наименьшая координата Y в СК панели отображения.

---

## Конструкторы

Конструктор | Описание
--- | ---
`PaintProject(LinkedList<PaintPoint> paintPoints, int pWidth, int pHeight)` | Создаёт экземпляр `PaintProject`. Где `paintPoints` - список `PaintPoint` в исходных координатах, `pWidth` - ширина панели отображения, `pHeight` - высота панели отображения.
`PaintProject(PolygonProject polygonRepository, int pWidth, int pHeight)` | Создаёт экземпляр `PaintProject`. Где `polygonRepository` - экземпляр `PolygonProject`, `pWidth` - ширина панели отображения, `pHeight` - высота панели отображения.
`PaintProject(SurveyProject surveyRepository, int pWidth, int pHeight)` | Создаёт экземпляр `PaintProject`. Где `surveyRepository` - экземпляр `SurveyProject`, `pWidth` - ширина панели отображения, `pHeight` - высота панели отображения.

---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`public double` | `getScale()` | Возвращает масштаб отображения.
