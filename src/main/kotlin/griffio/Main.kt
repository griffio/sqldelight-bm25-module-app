package griffio

import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import griffio.queries.Sample
import org.postgresql.ds.PGSimpleDataSource

private fun getSqlDriver() = PGSimpleDataSource().apply {
    setURL("jdbc:postgresql://localhost:5432/postgres")
    applicationName = "App Main"
    user = "postgres"
    password = "mysecretpassword"
}.asJdbcDriver()

fun main() {
    val driver = getSqlDriver()
    val sample = Sample(driver)
    println(sample.bm25Queries.tokenizeBert("A quick brown fox jumps over the lazy dog.").executeAsOne())
    println(sample.bm25Queries.tokenizeTocken("A quick brown fox jumps over the lazy dog.").executeAsOne())
    sample.bm25Queries.insertDocuments()
    sample.bm25Queries.tokenizeDocuments()
    sample.bm25Queries.rankDocuments("PostgreSQL").executeAsList().map(::println)
}
