package streams



/**
 * A main object that can be used to execute the Bloxorz solver
 */
object Bloxorz extends App {
//  object myTest extends Solver with InfiniteTerrain {
//    val b = Block(Pos(1,2),Pos(1,3))
//    val startPos = Pos(1,3)
//    val goal = Pos(5,8)
//  }
//  
//  myTest.b.neighbors.foreach(println)
//  /**
//   * A level constructed using the `InfiniteTerrain` trait which defines
//   * the terrain to be valid at every position.
//   */
//  object InfiniteLevel extends Solver with InfiniteTerrain {
//    val startPos = Pos(1,3)
//    val goal = Pos(5,8)
//  }
//  
//  println(InfiniteLevel.solution)
//
  /**
   * A simple level constructed using the StringParserTerrain 
   */
  abstract class Level extends Solver with StringParserTerrain
  
  object Level0 extends Level {
    val level =
      """------
        |--ST--
        |--oo--
        |--oo--
        |------""".stripMargin
  }
  
  for(s <- Level0.pathsFromStart)
  {
    println(s)
    if(Level0.done(s._1))
      println("Find")
  }

  println(Level0.solution)
//
//  /**
//   * Level 1 of the official Bloxorz game
//   */
//  object Level1 extends Level {
//    val level =
//      """ooo-------
//        |oSoooo----
//        |ooooooooo-
//        |-ooooooooo
//        |-----ooToo
//        |------ooo-""".stripMargin
//  }
//
//  println(Level1.solution)
}
