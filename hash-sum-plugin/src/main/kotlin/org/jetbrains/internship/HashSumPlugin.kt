package org.jetbrains.internship

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
  class HashSumClass (val digest : MessageDigest, val path: String ){
     val fileName = "src/resources/myfile.txt"
     val myfile = File(fileName)
     fun f(){
         File(path).walk().filter{it.endsWith(".kt") && it.endsWith(".java")}.forEach{
             val byteArray = ByteArray(1024)
             var bytesCount = 0
             if (it.isFile) {
                 val inputStream = it.inputStream()
                 while (inputStream.read(byteArray).also { bt -> bytesCount = bt } != -1) {
                     digest.update(byteArray, 0, bytesCount)
                 }
             }
         }
         val br = BigInteger(1, digest.digest()).toString(16).padStart(32, '0')
         myfile.printWriter().use { out ->
             out.println(br)
         }
     }
}
class HashSumPlugin : Plugin<Project> {
    val fileName = "src/resources/myfile.txt"
    val myfile = File(fileName)
    override fun apply(p0: Project) {
        p0.tasks.create("calculateSha1") {
            val HashSumClassInstance:HashSumClass = HashSumClass(MessageDigest.getInstance("SHA-1"), p0.getPath())
            HashSumClassInstance.f()
        }
        p0.tasks.create("calculateMD2") {
            val HashSumClassInstance:HashSumClass = HashSumClass(MessageDigest.getInstance("MD2"), p0.getPath())
            HashSumClassInstance.f()
        }
        p0.tasks.create("calculateMD5") {
            val HashSumClassInstance:HashSumClass = HashSumClass(MessageDigest.getInstance("MD5"), p0.getPath())
            HashSumClassInstance.f()
        }
        p0.tasks.create("calculateSha256") {
            val HashSumClassInstance:HashSumClass = HashSumClass(MessageDigest.getInstance("SHA-256"), p0.getPath())
            HashSumClassInstance.f()
        }
        p0.tasks.create("calculateSha384") {
            val HashSumClassInstance:HashSumClass = HashSumClass(MessageDigest.getInstance("SHA-384"), p0.getPath())
            HashSumClassInstance.f()
        }
        p0.tasks.create("calculateSha512") {
            val HashSumClassInstance:HashSumClass = HashSumClass(MessageDigest.getInstance("SHA-512"), p0.getPath())
            HashSumClassInstance.f()
        }



       /* p0.tasks.create("calculateSha1") {
            val digest = MessageDigest.getInstance("SHA-1")

            p0.getRootDir().walk().filter{it.endsWith(".kt") && it.endsWith(".java")}.forEach{
                val byteArray = ByteArray(1024)
                var bytesCount = 0
                if (it.isFile) {
                    val inputStream = it.inputStream()
                    while (inputStream.read(byteArray).also { bt -> bytesCount = bt } != -1) {
                        digest.update(byteArray, 0, bytesCount)
                    }
                }
            }
            val br = BigInteger(1, digest.digest()).toString(16).padStart(32, '0')
            myfile.printWriter().use { out ->
                out.println(br)
            }
        }*/
        println("HashingPlugin applied!")
    }
}