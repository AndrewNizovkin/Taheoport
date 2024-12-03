
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# PolygonPaintPanel Класс

---

## Определение

Представляет графическое отображение полигона на панели.

---

## Наследование

`java.lang.Object` -> `javax.swing.JPanel`-> `taheoport.gui.PolygonPaintPanel`

---

## Поля

Модификатор и тип | Поле | Описание
--- | ---|---
`private final PolygonProject` | `polygonRepository` | Текущий экземпляр `PolygonProject`.
`private final int` | `index` | Индекс выбранной станции.

---

## Конструкторы

Конструктор | Описание
--- | ---
`PolygonPaintPanel(PolygonProject polygonRepository, int idx)` | Отрисовывает на панели полигон инкапсулированный в `polygonRepository`. Выделяет цветом станцию с индексом `idx`.

---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`protected void` | `paintComponent(Graphics g)` | Отрисовывает компоненты при создании или изменении размеров панели.
