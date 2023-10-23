package com.oguzerdogan.sparkscala
package pt_08_spark_sql_dataset

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._


// Count up hpw many of each word occurs in a book, using regular expressions and sorting the final results.
object WordCountDataset{

  case class Book(value: String)

  // Our main function where the action happens
  def main(args: Array[String]){}

  // Set the log level to only print errors
  Logger.getLogger("org").setLevel(Level.ERROR)

  // Create a SparkSession using every core of the local machine
  val spark = SparkSession.builder()
    .appName("WordCount")
    .master("local[*]")
    .getOrCreate()


  // Read each line of my book into an Dataset
  import spark.implicits._
  val input = spark.read.text("data/book.txt").as[Book]

  // Split using a regular expression that extracts words
  val words = input
    .select(split($"value", "\\W+").alias("value"))

  //+--------------------+
  //|               value|
  //+--------------------+
  //|[Self, Employment...|
  //|[Achieving, Finan...|
  //|   [By, Frank, Kane]|
  //|                  []|
  //|                  []|
  //|                  []|
  //+---------------------+


  val explodedWords = words
    .select(explode($"value").alias("word"))
  //  +----------+
  //  | col |
  //  +----------+
  //  | Self |
  //  | Employment |
  //  | Building |
  //  | an |
  //  | Internet |
  //  | Business |
  //  +----------+

  // Normalize everything to lowercase
  val lowerCaseWords = explodedWords
    .select(lower($"word").alias("word"))

  //  +----------+
  //  |      self|
  //  |employment|
  //  |  building|
  //  |        an|
  //  |  internet|
  //  |  business|
  //  +----------+

  // Count up the occurrences of each word
  val wordCounts = lowerCaseWords.groupBy("word").count()

  //  +-----------+-----+
  //  |       word|count|
  //  +-----------+-----+
  //  |     online|   50|
  //  |        few|   40|
  //  |       some|  121|
  //  |requirement|    1|
  //  |       hope|    5|
  //  |      still|   65|
  //  +-----------+-----+


  // Sort by counts and order
  val wordCountsSorted = wordCounts.sort($"count".desc)

  //  +--------+-----+
  //  |    word|count|
  //  +--------+-----+
  //  |     you| 1878|
  //  |      to| 1828|
  //  |    your| 1420|
  //  |     the| 1292|
  //  |       a| 1191|
  //  +-----------+-----+

  // Show the results.
  wordCountsSorted.show(wordCountsSorted.count.toInt)














}

