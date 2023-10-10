
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# Catalog Класс

---

## Определение

Представляет список объектов [`CatalogPoint`](https://github.com/AndrewNizovkin/Taheoport/blob/main/manual/CatalogPoint.md), содержащих пространственные координаты опорных точек, доступных по индексу.

---

## Наследование

`java.lang.Object` -> ...-> `java.util.Linkedlist<E>` -> `taheoport.model.Catalog`

---

## Конструкторы

Конструктор | Описание
--- | ---
`Catalog()`|Создаёт пустой каталог координат 

---

## Поля

Модификатор и тип | Поле | Описание
--- | ---|---
`private String` | `absoluteCatalogPath` | Содержит путь к каталогу координат
---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`public Catalog` | `loadCatalogList(LinkedList<String>)` |  Загружает каталог из списка, переданного аргументом
`public CatalogPoint` | `getCatalogPoint(int index)` | Возвращает из каталога объект `CatalogPoint`, соответствующий индексу
`public int` | `getSizeCatalog()` | Возвращает количество элементов каталога
`public String` | `getAbsoluteCatalogPath()` | Возвращает полный путь к каталогу координат


