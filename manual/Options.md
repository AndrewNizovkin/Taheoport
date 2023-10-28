
[taheoport](https://github.com/AndrewNizovkin/Taheoport/blob/main/README.md)

# Options Класс

---

## Определение

Представляет настройки программы.

---

## Наследование

`java.lang.Object` -> `taheoport.model.Settings`

---

## Поля

Модификатор и тип | Поле | Описание
--- | ---|---
`private final String[]` | `FHs` | Массив строк, содержащий формулы для вычисления допустимой высотной невязки.
`private final String[]` | `FHors` | Массив строк, содержащий формулы для вычисления допустимой угловой невязки.
`private final String[]` | `FAbss` | Массив строк, содержащий константы для определения допустимой абсолютной линейной невязки.
`private final String[]` | `FOnts` | Массив строк, содержащий константы для определения допустимой относительной линейной невязки.
`private boolean` | `isChanged` | Флаг состояния.
`private int` | `idxFH` | Индекс допустимой высотной невязки.
`private int` | `idxFHor` | Индекс допустимой угловой невязки.
`private int` | `idxFAbs` | Индекс допустимой абсолютной линейной невязки.
`private int` | `idxFOtn` | Индекс допустимой относительной линейной невязки.
`private int` | `language` | Язык пользовательского интерфейса и документов. `0` - English, `1` - Русский.
`private String` | `pathWorkDir` | Путь к рабочей папке.
`private int` | `orientStation` | Ориентирование инструмента. `0` - Ноль тахеометра на ориентир. `1` - Первое измерение на ориентир.
`private int` | `offsetDistanceType` | Тип изменения расстояния. `0` - горизонтальное. `1` - наклонное.
`private int` | `offsetDirectionType` | Тип изменения направления. `0` - копировать из следующей записи. `1` - добавить смещение в угол.
`private int` | `offsetTiltType` | Тип изменения наклона. `0` - копировать из следующей записи. `1` - добавить смещение в угол.
`private String` | `offsetDistance` | Величина линейного домера по умолчанию, м.
`private String` | `offsetDirection` | Величина углового смещения направления по умолчанию, Г.ММСС.
`private String` | `offsetTiltAngle` | Величина углового смещения наклона по умолчанию, Г.ММСС.
`private  MainWin` | `parentFrame` | Родительский MainWin.
`private  int` | `prefixEX` | Код символа-префикса для свободной станции.

---

## Конструкторы

Конструктор | Описание
--- | ---
`Options(MainWin frame)`| Создаёт экземпляр `Options`. Считывает настройки из файла `taheoport.ini` или создаёт его и записывает в него начальные настройки, если файла нет в папке программы.

---

## Методы

Модификатор и тип | Метод | Описание
--- | --- | ---
`public String[]` | `getFAbss()` | Возвращает `this.FAbss`
`public String[]` | `getFHors()` | Возвращает `this.FHors`
`public String[]` | `getFHs()` | Возвращает `this.FHs`
`public String[]` | `getFOtns()` | Возвращает `this.FOtns`
`public int` | `getIdxFAbs()` | Возвращает `this.idxFAbs`
`public int` | `getIdxFH()` | Возвращает `this.idxFH`
`public int` | `getIdxFHor()` | Возвращает `this.idxFHor`
`public int` | `getIdxFOtn()` | Возвращает `this.idxFOtn`
`public int` | `getLanguage()` | Возвращает `this.language`
`public String` | `getOffsetDirection()` | Возвращает `this.offsetDirection`
`public int` | `getOffsetDirectionType()` | Возвращает `this.offsetDirectionType`
`public String` | `getOffsetDistance()` | Возвращает `this.offsetDistance`
`public int` | `getOffsetDistanceType()` | Возвращает `this.offsetDistanceType`
`public String` | `getOffsetTiltAngle()` | Возвращает `this.offsetTiltAngle`
`public int` | `getOffsetTiltType()` | Возвращает `this.offsetTiltType`
`public int` | `getOrientStation()` | Возвращает `this.orientStation`
`public String` | `getPathWorkDir()` | Возвращает `this.pathWorkDir`
`public int` | `getPrefixEX()` | Возвращает `this.prefixEX`
`public double` | `getValueFAbs())` | Возвращает значение допустимой абсолютной линейной невязки из `this.FAbss`, соответствующее `this.idxFAbs`
`public double` | `getValueFH())` | Возвращает значение коэффициента для определения допустимой высотной невязки из `this.FHs`, соответствующее `this.idxFH`
`public double` | `getValueFHor())` | Возвращает значение коэффициента для определения допустимой угловой невязки из `this.FHors`, соответствующее `this.idxFHor`
`public String` | `getValueFOtn())` | Возвращает значение знаменателя допустимой относительной невязки из `this.FOtns`, соответствующее `this.idxFOtn`
`public boolean` | `isChanged()` | Возвращает значение `this.isChanged`
`public void` | `saveOptions()` | Записывает значения настроек в файл `taheoport.ini`
`public void` | `setChanged(boolean changed)` | Устанавливает значение `this.isChanged`
`public void` | `setIdxFAbs(int idxFAbs)` | Устанавливает значение `this.idxFAbs`
`public void` | `setIdxFH(int idxFH)` | Устанавливает значение `this.idxFH`
`public void` | `setIdxFHor(int idxFHor)` | Устанавливает значение `this.idxFHor`
`public void` | `setIdxFOtn(int idxFOtn)` | Устанавливает значение `this.idxFOtn`
`public void` | `setLanguage(int language)` | Устанавливает значение `this.language`
`public void` | `setOffsetDirection(String offsetDirection)` | Устанавливает значение `this.offsetDirection`
`public void` | `setOffsetDirectionType(int offsetDirectionType)` | Устанавливает значение `this.offsetDirectionType`
`public void` | `setOffsetDistance(String offsetDistance)` | Устанавливает значение `this.offsetDistance`
`public void` | `setOffsetDistanceType(int offsetDistanceType)` | Устанавливает значение `this.offsetDistanceType`
`public void` | `setOffsetTiltAngle(String offsetTiltAngle)` | Устанавливает значение `this.offsetTiltAngle`
`public void` | `setOffsetTiltType(int offsetTiltType)` | Устанавливает значение `this.offsetTiltType`
`public void` | `setOrientStation(int orientStation)` | Устанавливает значение `this.orientStation`
`public void` | `setPathWorkDir(String pathWorkDir)` | Устанавливает значение `this.pathWorkDir`
`public void` | `setPrefixEX(int prefixEX)` | Устанавливает значение `this.prefixEX`

