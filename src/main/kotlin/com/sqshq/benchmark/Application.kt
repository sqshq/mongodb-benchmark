package com.sqshq.benchmark

import org.bson.Document
import org.bson.types.ObjectId
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.core.BulkOperations
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.bulkOps
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.Instant
import java.util.*
import javax.annotation.PostConstruct
import kotlin.concurrent.thread

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

data class Record(val id: ObjectId,
                  val s: String,
                  val pmId: UUID,
                  val scdId: UUID,
                  val scdIdpmId: String,
                  val kdId: UUID,
                  val afdId: UUID,
                  val afdR: Boolean,
                  val sq: Int,
                  val ut: Instant,
                  val ct: Instant,
                  val at: Instant,
                  val bc: String,
                  val bk: UUID)

@Component
class Benchmark(private val mongoTemplate: MongoTemplate) {

    private val collectionName = "record"
    private val documentCount = 1_000_000
    private val runCount = 3

    @PostConstruct
    fun init() {
        val collection = mongoTemplate.getCollection(collectionName)
        collection.createIndex(fields("scdIdpmId", "s", "kdId"))
        collection.createIndex(fields("s", "at", "sq"))
    }

    @PostConstruct
    fun start() {
        thread {
            println("Starting benchmark")
            for (i in 1..runCount) {
                println("\nrun #$i")
                run()
            }
        }
    }

    private fun run() {

        val records = generateRecords()

        printTiming("bulk insert") {
            insert(records)
        }

        printTiming("bulk update") {
            update()
        }

        printTiming("bulk delete") {
            delete()
        }
    }

    private fun insert(records: List<Record>) {
        val bulk = mongoTemplate.bulkOps<Record>(BulkOperations.BulkMode.UNORDERED)
        bulk.insert(records)
        bulk.execute()
    }

    private fun update() {
        mongoTemplate.updateMulti(Query(), Update().set("s", "IN_PROGRESS"), collectionName)
    }

    private fun delete() {
        mongoTemplate.remove(Query(), collectionName)
    }

    private fun generateRecords(): List<Record> {
        val records = mutableListOf<Record>()
        for (i in 1..documentCount) {
            records.add(Record(
                    id = ObjectId(),
                    s = "NEW",
                    pmId = UUID.randomUUID(),
                    scdId = UUID.randomUUID(),
                    scdIdpmId = "${UUID.randomUUID()}_${UUID.randomUUID()}",
                    kdId = UUID.randomUUID(),
                    afdId = UUID.randomUUID(),
                    afdR = true,
                    sq = kotlin.random.Random.nextInt(),
                    ut = Instant.now(),
                    ct = Instant.now(),
                    at = Instant.now(),
                    bc = "SOME",
                    bk = UUID.randomUUID()
            ))
        }
        return records
    }

    private fun <T> printTiming(title: String, function: () -> T) {
        val start = Instant.now()
        function()
        val duration = Duration.between(start, Instant.now())
        println("$title: ${duration.toMillis()}ms (${String.format("%.1f", documentCount.toDouble() / duration.seconds.toDouble())} per sec)")
    }

    private fun fields(vararg fields: String) = Document(fields.associateBy({ it }, { 1 }))
}