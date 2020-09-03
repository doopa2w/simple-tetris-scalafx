import java.awt.Component.BaselineResizeBehavior

trait Tetrad {

  //(a)(b)(c);
  //a= list of all the rotation
  //b= list of squares to create a block
  //c= a square
  // Array(x,y)
  // x + 1 = move left
  // y + 1 = move down

  val l: List[List[Array[Int]]]
  val L: List[List[Array[Int]]]
  val J: List[List[Array[Int]]]
  val T:List[List[Array[Int]]]
  val O:List[List[Array[Int]]]
  val S:List[List[Array[Int]]]
  val Z:List[List[Array[Int]]]


}

object GaiaTetrad extends Tetrad {

  val l = List(List(Array(0,0),Array(0,1),Array(0,2),Array(0,3)),
    List(Array(0,0),Array(1,0),Array(2,0),Array(3,0))
  )

  val L = List(List(Array(0,0),Array(0,1),Array(0,2),Array(1,2)),
    List(Array(0,1),Array(0,0),Array(1,0),Array(2,0)),
    List(Array(0,0),Array(1,0),Array(1,1),Array(1,2)),
    List(Array(0,1),Array(1,1),Array(2,1),Array(2,0))
  )

  val J = List(List(Array(1,0),Array(0,0),Array(0,1),Array(0,2)),
    List(Array(0,0),Array(1,0),Array(2,0),Array(2,1)),
    List(Array(0,2),Array(1,2),Array(1,1),Array(1,0)),
    List(Array(0,0),Array(0,1),Array(1,1),Array(2,1))
  )

  val T = List(
    List(Array(1,0),Array(0,1),Array(1,1),Array(1,2)),
    List(Array(1,0),Array(0,1),Array(1,1),Array(2,1)),
    List(Array(0,0),Array(0,1),Array(1,1),Array(0,2)),
    List(Array(0,0),Array(1,0),Array(2,0),Array(1,1))
  )

  val O = List(List(Array(0,0),Array(0,1),Array(1,0),Array(1,1)),
    List(Array(0,0),Array(0,1),Array(1,0),Array(1,1))
  )

  val S = List(List(Array(0,0),Array(0,1),Array(1,1),Array(1,2)),
    List(Array(0,1),Array(1,1),Array(1,0),Array(2,0))
  )

  val Z = List(List(Array(1,0),Array(1,1),Array(0,1),Array(0,2)),
    List(Array(0,0),Array(1,0),Array(1,1),Array(2,1))
  )

  val B = List(List(Array(0,0), Array(0,1)),
    List(Array(0,1), Array(1,1))
  )
}


val tetrad = List(GaiaTetrad.l, GaiaTetrad.J, GaiaTetrad.L, GaiaTetrad.T, GaiaTetrad.O, GaiaTetrad.Z, GaiaTetrad.S, GaiaTetrad.B)

var currenttetrad: List[List[Array[Int]]] = tetrad(0)


val B_test = List(List(Array(0,0), Array(0,1)),
  List(Array(0,1), Array(1,1))
)

B_test equals GaiaTetrad.B  //false

GaiaTetrad.B equals GaiaTetrad.B  //true

val c = GaiaTetrad

var currentT = c.B

abstract class Tetramino{

  val z:List[List[Array[Int]]]
}
trait grow{
  def grow:Unit
}
class BranchTetramino extends Tetramino with grow{
  val z= List(List(Array(1,0),Array(1,1),Array(0,1),Array(0,2)),
              List(Array(0,0),Array(1,0),Array(1,1),Array(2,1)))


  def grow: Unit = {
    print("growing")
  }

}

object TestApp extends App{

  val branch = new BranchTetramino

  branch.grow

  if (branch.isInstanceOf[Tetramino]){
    print("is instance of Tetramino")
  }
  else if (branch.isInstanceOf[grow]){
    print("can grow eh")
  }
  else
    print("pariah dog")

}






