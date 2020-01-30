## Тестовое задание "Аренда автомобилей"

[Само задание](doc/task_description.pdf)

#### Модель

- [Арендуемый автомобиль](src/main/java/ru/perm/v/rent/model/Car.java)
- [Марка автомобиля](src/main/java/ru/perm/v/rent/model/ModelCar.java)
- [Пункт проката](src/main/java/ru/perm/v/rent/model/RentalPoint.java)
- [Текущий статус автомобиля](src/main/java/ru/perm/v/rent/model/Status.java)
- [Сведения об аренде](src/main/java/ru/perm/v/rent/model/RentHistory.java)

#### Сервисы

Все сервисы реализуют CRUD интерфейс:
- GET-запрос __/api/{сервис}__ - получение всех сущностей сервиса
- GET-запрос __/api/{сервис}/{id}__ - получение конкретной сущности сервиса по идентификатору
- PUT-запрос __/api/{сервис}__ - создание конкретной сущности сервиса
- POST-запрос __/api/{сервис}__ - изменение конкретной сущности сервиса
- DELETE-запрос __/api/{сервис}/{id}__ - удаление конкретной сущности сервиса

Список сервисов: 
- [http://localhost:8080/api/car](http://localhost:8080/api/car) - сервис работы с машинами 
- [http://localhost:8080/api/modelcar](http://localhost:8080/api/modelcar
) - сервис работы с моделями машин 
- [http://localhost:8080/api/status](http://localhost:8080/api/status
) - сервис работы со статусами машин ("Свободна","Арендована") 
- [http://localhost:8080/api/rentalpoint](http://localhost:8080/api/rentalpoint
) - сервис работы с пунктами проката
- [http://localhost:8080/api/rentalhistory](http://localhost:8080/api/rentalhistory
) - отчеты об аренде 

