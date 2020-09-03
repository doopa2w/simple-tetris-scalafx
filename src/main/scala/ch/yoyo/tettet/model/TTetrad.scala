package ch.yoyo.tettet.model

class TTetrad(val block: List[List[Array[Int]]] = List(
  List(Array(1,0),Array(0,1),Array(1,1),Array(1,2)),
  List(Array(1,0),Array(0,1),Array(1,1),Array(2,1)),
  List(Array(0,0),Array(0,1),Array(1,1),Array(0,2)),
  List(Array(0,0),Array(1,0),Array(2,0),Array(1,1)))) extends Tetrad {



}
