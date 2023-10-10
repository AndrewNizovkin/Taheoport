
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# LinearOffsetPaintPanel Класс

---

## Определение

Представляет UI компонент для отрисовки схемы определения линейного домера.

---

## Наследование

`java.lang.Object` -> `javax.swing.JPanel` -> `taheoport.gui.LinearOffsetPaintPanel`

---

## Поля

Модификатор и тип | Поле | Описание
--- | ---|---
`private final MainWin` | `parentFrame` | Родительский MainWin


---

## Конструкторы

Конструктор | Описание
--- | ---
`LinearOffsetPaintPanel(MainWin parentFrame))`| Создаёт экземпляр объекта с отрисованной схемой, в соответствии с установками типа домера.

---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`protected void` | `paintComponent(Graphics g)` |  Отрисовывает схему при создании нового `LinearOffsetPaintPanel`. 
