package com.scala.learn.advance.lectures.part1as

import scala.util.Try

object DarkSugar extends App {
  // 1. Syntax sugar, function with single param
  def singleArgMethod(arg: Int): String = s"$arg little duck"

  // That expression block last param would be the value for function parameter
  val desc = singleArgMethod {
    //Any code
    10
  }

  //Example 1
  //There are some similar examples, like Try companion object's apply method is called and block last expression
  // is the param value
  val tryBlock = Try {
    //...
    println("Try block")
  }

  //Example 2
  //Here both are same
  List(1,2,3).map(x => (x + 1)).foreach(print)
  println
  List(1,2,3).map { x =>
    x + 1
  }.foreach(print)

  // 2. Instance of traits can be expressed as lambda expression
  // Single abstract method
  trait Action {
    def act(x: Int): Int
  }

  // Now if I hover on new Action, IDE ask me for Single Abstract Method
  // Compiler looks into instance type and look into Action class and map the lambda expression with act method.
  val actInstance: Action = new Action {
    override def act(x: Int): Int = x + 1
  }

  val actSingleAbstractInstance: Action = (x: Int) => x + 1

  //Example 1
  //In java Runnables are used
  val aThread = new Thread(new Runnable {
    override def run(): Unit = println("Java Thread")
  })

  //Now in scala for this single method, we may syntactically write it as
  val aSweetThread = new Thread(() => println("A Sweet Thread"))

  //Example 2
  abstract class AnAbstractType {
    val defined: Int = 2
    def undefinedFunc(x: Int): Unit
  }

  //Now we may create instance of abstract class by just providing the implementation
  val anImplementedType: AnAbstractType = (x: Int) => println("An implemented function")

  // 3. Special methods prepend like :: and #: methods are special
  val prependedList = 2 :: List(1)
  //Now  as we think it would be from left to right 2 function List(1) but 2 don't have :: function
  // Actually scala says ::, last character decides associativity of Method
  // Thus instead of 2.::(List(1))
  // it is List(1).::(2)

  1 :: 2 :: 3 :: 4 :: List(5)
  //Actually it is
  List(5).::(4).::(3).::(2).::(1)
}