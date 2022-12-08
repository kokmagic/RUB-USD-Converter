# RUB-USD-Converter
Сервис, который по запросу конвертирует заданное количество рублей в доллары и наоборот.
# Запуск программы
Клонируйте репозиторий:
``` console
$ git clone https://github.com/kokmagic/RUB-USD-Converter
```
Соберите приложение с помощью gradle:
``` console
$ ./gradlew build
```
Запустите приложение:
``` console
$ java -jar build/libs/Converter-0.0.1-SNAPSHOT.jar
```
# Для запуска с помощью Docker
Соберите docker образ:
``` console
$ sudo docker build -t app .
```
Запустите его:
``` console
$ sudo docker run -p 8080:8080 app
```
# Инструкция по использованию
С помощью Postman отправьте GET запрос по адресу
`{http://localhost:8080/api/convert?currencyFrom=...&currencyTo=...&value=...}`,
где `currencyFrom` и `currencyTo` - это валюты с которой и в которую произойдет конвертация соответственно.
`value` - это количество той или иной валюты. Ответом будет результат конвертации.
# Примеры работы
Пример конвертации из рублей в доллары (RUB TO USD):

![output example #1](https://github.com/kokmagic/RUB-USD-Converter/blob/07428037f89349a7081880cba4b8635f30a29191/Examples/example1.png)

Пример конвертации из долларов в рубли (USD TO RUB):

![output example #1](https://github.com/kokmagic/RUB-USD-Converter/blob/07428037f89349a7081880cba4b8635f30a29191/Examples/example2.png)