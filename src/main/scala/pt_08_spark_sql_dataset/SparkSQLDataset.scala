package com.oguzerdogan.sparkscala
package pt_08_spark_sql_dataset

import org.apache.log4j._
import org.apache.spark.sql.SparkSession

object SparkSQLDataset{

  case class Person(id: Int, name: String, age: Int, friends: Int)

  def main(args: Array[String]){

    // Set the log level to only print errors
    Logger.getLogger("com").setLevel(Level.ERROR)

    // Spark Session
    val spark = SparkSession.builder()
      .appName("SparkSQL")
      .master("local[*]")
      .getOrCreate()

    // Convert our csv file to a DataSet, using our Person case
    // class to infer the schema
    import spark.implicits._

    val people = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv("data/fakefriends.csv")
      .as[Person]


    // There are lots of other ways to make a DataFrame.
    // For example, spark.read.json("json file path")
    // or sqlContext.table("Hive table name")

    println("Here is our inferred schema:")
    people.printSchema()

    println("Let's select the name column:")
    people.select("name").show(5)

    println("Group by age:")
    people.groupBy("age").count().show()

    println("Make everyone 10 years older:")
    val agePlus10 = (people.col("age") + 10).as("newAge")
    people.select(people("name"), people("age"), agePlus10).show()

    spark.stop()

  }

}