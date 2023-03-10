
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# CatalogPoint Класс

---

## Определение

Инкапсулирует информацию об опорном пункте геодезической сети

---

## Наследование

`java.lang.Object` -> `taheoport.CatalogPoint`

---

## Конструкторы

Конструктор | Описание
--- | ---
`CatalogPoint(String name, String x, String y, String z)`| Создаёт экземпляр объекта, с переданными названием и пространственными координатами 

---

## Поля

Модификатор и тип | Поле | Описание
--- | ---|---
`private String` | `name` | Название пункта
`private String` | `x` | Координата X пункта
`private String` | `y` | Координата Y пункта
`private String` | `Z` | Координата Z пункта
---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`public void` | `getName(String name)` |  Возвращает значение поля `name`
`public void` | `getX(String x)` |  Возвращает значение поля `x`
`public void` | `getY(String y)` | Возвращает значение поля `y`
`public void` | `getZ(String z)` |  Возвращает значение поля `z`
`public void` | `setName(String name)` |  Устанавливает значение поля `name`
`public void` | `setX(String x)` |  Устанавливает значение поля `x`
`public void` | `setY(String y)` |  Устанавливает значение поля `y`
`public void` | `setZ(String z)` |  Устанавливает значение поля `z`
