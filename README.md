# Дипломный проект по профессии «Тестировщик»
## Описание
 В данном проекте осуществлена автоматизация тестирование комплексного сервиса, взаимодействующего с СУБД и API Банка
 
## Отчетная документация
* [План автоматизации](https://github.com/Maxim-GT/QA-Diploma-Maxim/blob/379129ba8c0d4234608d5fc09c896a6b545b6599/Plan.md)

* Репорты

* Итоги


## Шаги по запуску тестов
1. Склонировать данный проект
2. Открыть проект в IntelliJ IDEA
3. Запустить контейнеры в терминале командой
`
docker-compose up -d --force-recreate --build 
`
4. Для запуска приложения с параметрами: 
* под MySQL ввести в терминале команду
`
java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" "-Dspring.datasource.username=app" "-Dspring.datasource.password=pass" -jar artifacts/aqa-shop.jar
`

* под PostgreSQL ввести в терминале команду
`
java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/postgres" "-Dspring.datasource.username=app" "-Dspring.datasource.password=pass" -jar artifacts/aqa-shop.jar
`
5. Для запуска тестов:
* при работе с MySQL ввести команду
`
gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app
`
    
* при работе с PostgreSQL ввести команду
`
gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/postgres
`