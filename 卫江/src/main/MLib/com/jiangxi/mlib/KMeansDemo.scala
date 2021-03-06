/*
package com.jiangxi.mlib
import breeze.linalg
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object KMeansDemo {
  def main(args: Array[String]): Unit = {
    // 初始化
    val conf = new SparkConf().setAppName("KMeansDemo").setMaster("local[*]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")

    // 读数据，封装数据
    val filePath = "E:\\DMPData\\Inpath\\iris.csv"
    val lines = sc.textFile(filePath)
    val data: RDD[linalg.Vector] = lines.map(line => {
      val arr = line.split(",").init.map(_.toDouble)
      Vectors.dense(arr)
    })

    // 设置参数
    val k = 3
    val maxIterations = 100
    val initializationMode = "k-means||"

    // 训练模型
    val model: KMeansModel = KMeans.train(data, k, maxIterations, initializationMode)
    model.clusterCenters.foreach(println)

    // 数据分类
    val data1: RDD[(linalg.Vector, String)] = lines.map(line => {
     val arr = line.split(",").init.map(_.toDouble)
      //val arr = line.split(" ").map(_.toDouble)
      val label = line.split(",").last
      (Vectors.dense(arr), label)
    })

    data1.foreach{case (vector, label) =>
      println(model.predict(vector), label)
    }

    data1.map{case (vector, label) =>
      (model.predict(vector) + " | "+ label, 1)
    }.reduceByKey(_+_)
      .sortByKey()
      .foreach(println)

    println("********************************************************************")
    val optionK = Array(2,3,4,5,6,8,10,100,150)
    optionK.foreach(k => {
      val model1: KMeansModel = KMeans.train(data, k, maxIterations, initializationMode)
      val d: Double = model1.computeCost(data)
      println(k, d)
    })
    //    model.save(sc, "Spark MLlib")

    sc.stop()
  }
}*/
