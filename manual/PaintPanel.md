
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# PaintPanel Класс

---

## Определение

Отображает результаты обработки измерений.

---

## Наследование

`java.lang.Object` -> javax.swing.JPanel -> `taheoport.PaintPanel`

---

## Поля

Модификатор и тип | Поле | Описание
--- | ---|---
`private final int` | `index` | Индекс выделенной точки в массиве точек.
`private final ShowViewResults` | `parentFrame` | Родительский `ShowViewResults`.
`private PaintProject` | `ppPaintPoints` | Экземпляр `PaintProject` для текущего набора измерений. Представляет набор координат, масштабированных под текущий размер родительского окна.

---

## Конструкторы

Конструктор | Описание
--- | ---
`PaintPanel(ShowViewResults frame, int sellRow)`| Создаёт экземпляр `PaintPanel`, на котором отрисованы станции, ориентиры, съёмочные точки (пикеты). Выделяет точку, соответствующую `sellRow` и направление на станцию, с которой были выполнены наблюдения на неё.

---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`protected void` | `paintComponent(Graphics g)` | Отрисовывает компоненты при создании или изменении размеров панели.
