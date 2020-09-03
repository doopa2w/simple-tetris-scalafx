package ch.yoyo.tettet.model

class ChaosTetrad(val block: List[List[Array[Int]]] =
                  List(List(Array(1,0),Array(1,1),Array(1,2),Array(1,3),
                  Array(2,0),Array(2,1),Array(2,2),Array(2,3),
                  Array(3,0),Array(3,1),Array(3,2),Array(3,3),
                  Array(4,0),Array(4,1),Array(4,2),Array(4,3),
                  Array(5,0),Array(5,1),Array(5,2),Array(5,3),
                  Array(6,0),Array(6,1),Array(6,2),Array(6,3),
                  Array(7,0),Array(7,1),Array(7,2),Array(7,3),
                  Array(8,0),Array(8,1),Array(8,2),Array(8,3),
                  Array(9,0),Array(9,1),Array(9,2),Array(9,3)),
                  List(Array(0,0),Array(0,1),Array(0,2),Array(0,3),
                    Array(2,0),Array(2,1),Array(2,2),Array(2,3),
                    Array(3,0),Array(3,1),Array(3,2),Array(3,3),
                    Array(4,0),Array(4,1),Array(4,2),Array(4,3),
                    Array(5,0),Array(5,1),Array(5,2),Array(5,3),
                    Array(6,0),Array(6,1),Array(6,2),Array(6,3),
                    Array(7,0),Array(7,1),Array(7,2),Array(7,3),
                    Array(8,0),Array(8,1),Array(8,2),Array(8,3),
                    Array(9,0),Array(9,1),Array(9,2),Array(9,3)),
                  List(Array(0,0),Array(0,1),Array(0,2),Array(0,3),
                    Array(1,0),Array(1,1),Array(1,2),Array(1,3),
                    Array(3,0),Array(3,1),Array(3,2),Array(3,3),
                    Array(4,0),Array(4,1),Array(4,2),Array(4,3),
                    Array(5,0),Array(5,1),Array(5,2),Array(5,3),
                    Array(6,0),Array(6,1),Array(6,2),Array(6,3),
                    Array(7,0),Array(7,1),Array(7,2),Array(7,3),
                    Array(8,0),Array(8,1),Array(8,2),Array(8,3),
                    Array(9,0),Array(9,1),Array(9,2),Array(9,3)),
                  List(Array(0,0),Array(0,1),Array(0,2),Array(0,3),
                    Array(1,0),Array(1,1),Array(1,2),Array(1,3),
                    Array(2,0),Array(2,1),Array(2,2),Array(2,3),
                    Array(4,0),Array(4,1),Array(4,2),Array(4,3),
                    Array(5,0),Array(5,1),Array(5,2),Array(5,3),
                    Array(6,0),Array(6,1),Array(6,2),Array(6,3),
                    Array(7,0),Array(7,1),Array(7,2),Array(7,3),
                    Array(8,0),Array(8,1),Array(8,2),Array(8,3),
                    Array(9,0),Array(9,1),Array(9,2),Array(9,3)),
                  List(Array(0,0),Array(0,1),Array(0,2),Array(0,3),
                    Array(1,0),Array(1,1),Array(1,2),Array(1,3),
                    Array(2,0),Array(2,1),Array(2,2),Array(2,3),
                    Array(3,0),Array(3,1),Array(3,2),Array(3,3),
                    Array(5,0),Array(5,1),Array(5,2),Array(5,3),
                    Array(6,0),Array(6,1),Array(6,2),Array(6,3),
                    Array(7,0),Array(7,1),Array(7,2),Array(7,3),
                    Array(8,0),Array(8,1),Array(8,2),Array(8,3),
                    Array(9,0),Array(9,1),Array(9,2),Array(9,3)),
                  List(Array(0,0),Array(0,1),Array(0,2),Array(0,3),
                    Array(1,0),Array(1,1),Array(1,2),Array(1,3),
                    Array(2,0),Array(2,1),Array(2,2),Array(2,3),
                    Array(3,0),Array(3,1),Array(3,2),Array(3,3),
                    Array(4,0),Array(4,1),Array(4,2),Array(4,3),
                    Array(6,0),Array(6,1),Array(6,2),Array(6,3),
                    Array(7,0),Array(7,1),Array(7,2),Array(7,3),
                    Array(8,0),Array(8,1),Array(8,2),Array(8,3),
                    Array(9,0),Array(9,1),Array(9,2),Array(9,3)),
                  List(Array(0,0),Array(0,1),Array(0,2),Array(0,3),
                    Array(1,0),Array(1,1),Array(1,2),Array(1,3),
                    Array(2,0),Array(2,1),Array(2,2),Array(2,3),
                    Array(3,0),Array(3,1),Array(3,2),Array(3,3),
                    Array(4,0),Array(4,1),Array(4,2),Array(4,3),
                    Array(5,0),Array(5,1),Array(5,2),Array(5,3),
                    Array(7,0),Array(7,1),Array(7,2),Array(7,3),
                    Array(8,0),Array(8,1),Array(8,2),Array(8,3),
                    Array(9,0),Array(9,1),Array(9,2),Array(9,3)),
                  List(Array(0,0),Array(0,1),Array(0,2),Array(0,3),
                    Array(1,0),Array(1,1),Array(1,2),Array(1,3),
                    Array(2,0),Array(2,1),Array(2,2),Array(2,3),
                    Array(3,0),Array(3,1),Array(3,2),Array(3,3),
                    Array(4,0),Array(4,1),Array(4,2),Array(4,3),
                    Array(5,0),Array(5,1),Array(5,2),Array(5,3),
                    Array(6,0),Array(6,1),Array(6,2),Array(6,3),
                    Array(8,0),Array(8,1),Array(8,2),Array(8,3),
                    Array(9,0),Array(9,1),Array(9,2),Array(9,3)),
                  List(Array(0,0),Array(0,1),Array(0,2),Array(0,3),
                    Array(1,0),Array(1,1),Array(1,2),Array(1,3),
                    Array(2,0),Array(2,1),Array(2,2),Array(2,3),
                    Array(3,0),Array(3,1),Array(3,2),Array(3,3),
                    Array(4,0),Array(4,1),Array(4,2),Array(4,3),
                    Array(5,0),Array(5,1),Array(5,2),Array(5,3),
                    Array(6,0),Array(6,1),Array(6,2),Array(6,3),
                    Array(7,0),Array(7,1),Array(7,2),Array(7,3),
                    Array(9,0),Array(9,1),Array(9,2),Array(9,3)),
                  List(Array(0,0),Array(0,1),Array(0,2),Array(0,3),
                    Array(1,0),Array(1,1),Array(1,2),Array(1,3),
                    Array(2,0),Array(2,1),Array(2,2),Array(2,3),
                    Array(3,0),Array(3,1),Array(3,2),Array(3,3),
                    Array(4,0),Array(4,1),Array(4,2),Array(4,3),
                    Array(5,0),Array(5,1),Array(5,2),Array(5,3),
                    Array(6,0),Array(6,1),Array(6,2),Array(6,3),
                    Array(7,0),Array(7,1),Array(7,2),Array(7,3),
                    Array(8,0),Array(8,1),Array(8,2),Array(8,3))
                )) extends Tetrad {}