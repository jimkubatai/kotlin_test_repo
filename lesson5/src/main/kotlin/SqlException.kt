class SqlReadDataException(ex: Exception, msg: String = "Не удалось прочитать результаты запроса") : Exception(msg, ex) {
}

class SqlExecuteException(ex: Exception,msg: String = "Не удалось извлечь запрос") : Exception(msg, ex) {
}

class SqlNoDataException(msg: String = "Нет данных") : Exception(msg) {
}