# Simulation

Это симулятор экосистемы, в которой присутствуют травоядные, хищники и растения.
С помощью этого проекта можно моделировать поведение животных в разных условиях, изучать их взаимодействие и движение по
карте.

## Описание

Проект реализует симуляцию экосистемы, в которой:

- **Травоядные (Herbivores)** питаются травой.
- **Хищники (Predators)** охотятся на травоядных.
- **Трава (Grass)** служит пищей для травоядных.
- Существа взаимодействуют между собой на **карте** и могут передвигаться по ней.
- Вывод взаимодействий существ отображается в консоли.

## Отображение сущностей

В консоли сущности отображаются с помощью символов:

- `P` — хищник (Predator)
- `H` — травоядное животное (Herbivore)
- `G` — трава (Grass)
- `M` — гора (Mountain)
- `T` — дерево (Tree)
- `.` — пустая клетка (empty cell).

Каждое существо или объект на карте отображается соответствующим символом. 
Пустые клетки карты отображаются точкой `.`.

Пример карты:
```text
. H G . T T . G .
H G . T . . H G .
T . P . P . . . .
. . . H M . . . T
```

### Основные возможности:

- `Травоядные` поедают траву и передвигаются по карте.
- `Хищники` атакуют травоядных.
- `Существа` используют алгоритм поиска пути (BFS) для нахождения ближайшей пищи или для перемещения по карте.
- `Существа` умирают, когда теряют все здоровье.
- Симуляция завершится, если исчезнут все сущности одного из видов: травоядные, хищники или трава. Также симуляция
  завершится, если сущности больше не могут найти путь к жертве (например, жертва окружена горами или другими
  препятствиями).

## Структура проекта

Проект разделён на несколько основных компонентов:

- **Сущности (Entities)**: классы для представления существ, растений и других объектов.
- **Контроллеры (Controllers)**: логика для управления движением существ по карте и взаимодействиями между объектами.
- **Фабрики (Factories)**: классы для создания сущностей с настройкой их начальных параметров.
- **Менеджер (Manager)**: класс, который управляет созданием сущностей и запуском симуляции.
- **Карта (GameMap)**: двухмерная карта, на которой происходят все взаимодействия.

## Установка

Для запуска симуляции потребуется JDK 17 и выше, а также инструмент для сборки, например, Maven.

## Сборка проекта

1. **Клонируйте репозиторий:**
    ```bash
    git clone https://github.com/aveasura/Simulation.git
    ```

2. **Перейдите в каталог проекта:**
    ```bash
    cd Simulation
    ```

3. **Соберите проект:**

   Для сборки проекта с использованием Maven, выполните команду:
    ```bash
    mvn package
    ```
   Эта команда соберет проект и создаст JAR-файл в папке `target/`.

## Использование

1. **После того как проект будет собран с помощью `mvn package`, JAR-файл будет готов к запуску:**

   Перейдите в директорию `target`, где находится собранный JAR-файл:
    ```bash
    cd target
    ```

   Убедитесь, что вы находитесь в каталоге `target`, где находится файл `SimulationV1.jar`.
   Затем запустите программу напрямую с помощью команды:
    ```bash
    java -jar SimulationV1.jar
    ```

2. Программа будет ожидать, что вы укажите общее количество случайных сущностей для генерации на карте:
    - **Травоядные** — растения, которые могут быть съедены травоядными.
    - **Хищники** — животные, которые могут атаковать травоядных.
    - **Трава** — растительность, которую могут съесть травоядные.

3. После этого симуляция начнется, и вы будете видеть, как существа движутся по карте и взаимодействуют друг с другом.

4. Симуляция завершится, когда исчезнут все сущности одного из видов: травоядные, хищники или трава.
   А также симуляция завершится, если сущности больше не могут прокладывать путь к жертве(жертва окружена горами, итп).

## Структура классов

- **Simulation** — главный класс, который содержит метод `main` и запускает симуляцию.
- **Entity** — абстрактный класс для всех объектов на карте.
- **Creature** — абстрактный класс для всех существ (травоядных и хищников).
- **Herbivore** — класс для травоядных животных.
- **Predator** — класс для хищников.
- **Grass** — класс для травы.
- **Mountain** - класс для размещения статического объекта (Гора).
- **Tree** - класс для размещения статического объекта (Дерево).
- **MovementController** — контроллер для перемещения объектов по карте.
- **DisplayController** — контроллер для отображения карты на консоли.
- **SimulationManager** - класс, отвечающий за управление процессом симуляции.

## Пример взаимодействия

1. Хищник атакует травоядное:
    - Хищник перемещается к травоядному.
    - Хищник атакует травоядного, нанося урон.
    - Если здоровье травоядного падает до 0, оно умирает.

2. Травоядное поедает траву:
    - Травоядное перемещается к траве.
    - Травоядное поедает траву, трава удаляется с карты.

## Вклад

Если вы хотите внести изменения или улучшения в этот проект, пожалуйста, создайте fork и откройте Pull Request. Ваш
вклад всегда приветствуется! :)

## Лицензия

Этот проект лицензирован под MIT License - см. [LICENSE.md](LICENSE.md) для подробностей.