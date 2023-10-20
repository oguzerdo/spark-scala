package com.oguzerdogan.sparkscala
package pt_05_assignment

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col

object Main {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("spark-scala")
      .master("local[*]")
      .getOrCreate

    // Read Data
    val df = spark.read
      .option("header", value=true)
      .csv("data/AAPL.csv")

    df.show()
    df.printSchema()

//  1. Rename all columns to be of camel case format
//  2. Add a column containing the diff between open and close
//  3. Filter to days when the close price was more than 10% higher than the open price

//  1. Rename all columns to be of camel case format
    val renameColumns =  List(
      col("Date").as("date"),
      col("Open").as("open"),
      col("High").as("high"),
      col("Low").as("low"),
      col("Close").as("close"),
      col("Adj Close").as("adjClose"),
      col("Volume").as("volume")
    )

    df.select(renameColumns: _*).show()

//  or we can use this, prefer first one

    df.withColumnRenamed("Date", "date")
      .withColumnRenamed("Open", "open")
      .withColumnRenamed("High", "high")
      .withColumnRenamed("Low", "low")
      .withColumnRenamed("Close", "close")
      .withColumnRenamed("Adj Close", "adjClose")
      .withColumnRenamed("Volume", "volume")

//  Scala way, just for lower case
    df.select(df.columns.map(c => col(c).as(c.toLowerCase())): _*).show()


//  2. Add a column containing the diff between open and close

    val stockData = df.select(renameColumns: _*)
      .withColumn("diff", col("close") - col("open"))

    stockData.show()

//  3. Filter to days when the close price was more than 10% higher than the open price

    val stockDataFiltered = df.select(renameColumns: _*)
      .withColumn("diff", col("close") - col("open"))
      .filter(col("close") > col("open") * 1.1)

    stockDataFiltered.show()
  }
}