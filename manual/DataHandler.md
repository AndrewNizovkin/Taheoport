
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# DataHandler Класс

---

## Определение

Предоставляет методы для парсинга, анализа и конвертирования принимаемого значения

---

## Наследование

`java.lang.Object` -> `taheoport.service.DataHandler`

---

## Конструкторы

Конструктор | Описание
--- | ---
`DataHandler()`| Создаёт экземпляр объекта, со значением "0" поля `str` 
`DataHandler(String str)`| Создаёт экземпляр объекта, со значением поля `str`, соответствующем переданной строке
`DataHandler(Double dbl)`| Создаёт экземпляр объекта, со значением поля `str`, преобразованным из переданного `Double`

---

## Поля

Модификатор и тип | Поле | Описание
--- | ---|---
`private String` | `str` | Строка


---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`public DataHandler` | `commaToPoint()` |  Заменяет в `str` все запятые на точки
`public DataHandler` | `comress(String separator)` |  Удаляет повторяющиеся значения разделителя
`public DataHandler` | `degToDms(double dbl)` |  Преобразует значение угловой величины из формата d.dddddd в формат d.mmss
`public double` | `dmsToDeg()` |  Возвращает значение `str`, хранящееся в формате d.mmss, преобразованное в  формат d.ddddd
`public double` | `dmsToRad()` |  Возвращает значение `str`, хранящееся в формате d.mmss, преобразованное в радианы
`public DataHandler` | `format(int f)` |  Округляет значение `str` до `f` знаков после десятичной точки
`public double` | `getDbl()` |  Возвращает преобразованное в `double` значение поля `str`
`public String` | `getStr()` |  Возвращает строковое значение поля `str`
`public boolean` | `isNumber()` |  Проверяет является ли значение в `str` числом
`public boolean` | `isPositiveNumber()` |  Проверяет является ли значение в `str` положительным числом
`public boolean` | `isValidName()` |  Проверяет является ли значение в `str` допустимым названием
`public DataHandler` | `radToDms(double dbl)` |  Преобразует угловую величину в радианах, результат помещает в `str`
`public DataHandler` | `removeFirstZero()` |  Удаляет первые '0' в поле `str`
`public DataHandler` | `setPointPosition(int k)` |  Вставляет десятичную точку в указанную позицию поля `str`
`public void` | `setStr(String str)` |  Присваивает полю `str` значение
`public DataHandler` | `toTable(int width)` |  Добавляет к началу строки `str` пробелы до требуемой ширины
`public DataHandler` | `ZenithToVert()` |  Преобразует зенитное расстояние в вертикальный угол





