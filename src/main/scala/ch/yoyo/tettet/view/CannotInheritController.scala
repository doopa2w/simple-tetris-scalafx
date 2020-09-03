/*
package ch.yoyo.tettet.view

import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.control.{Button,Label}
import scalafx.scene.layout.{AnchorPane, GridPane}
import scalafx.scene.media.{Media, MediaPlayer}
import scalafx.util.Duration
import java.io.File
import scalafxml.core.macros.sfxml
import scalafx.scene.shape.Rectangle
import scalafx.animation._
import scala.util.Random
import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType
import scalafx.application.Platform
import scalafx.scene.input.{KeyCode,KeyEvent}
import ch.yoyo.tettet.model.Tetrad

@sfxml
class GaiaController(val tetris: AnchorPane, val  tetrisBoard: GridPane,
                     val nextPieceBoard: GridPane,
                     val score: Label, val showPaused: Label)extends BaseGameController() {

  // hold the scores
  var _scores = 0

  // hold all the _tetrad
  val _tetrad = List(Tetrad.l, Tetrad.J, Tetrad.L, Tetrad.T, Tetrad.O, Tetrad.Z, Tetrad.S)

  // hold the piece with the orientation
  var _currenttetrad: List[List[Array[Int]]] = List()

  // hold currentPiece data
  var _currentPiece: List[Array[Int]] = List()

  // hold the current orientation number
  var _currentZ: Int = 0

  // hold the currentY
  var _currentY: Int = 0

  // hold the currentX
  var _currentX: Int = 5

  // hold nextPiece data
  var _nextPiece: List[List[Array[Int]]] = List()

  // a virtual board that holds all the diePiece data
  var _board = Array.ofDim[Int](20, 10)

  // whether gameOver or not
  var _gameOver = false


  // initialize board
  for (row <- 0 until 20) {
    for (col <- 0 until 10) {
      if (row >= 17 && row <= 19) {
        if (col >= 3 && col <= 6) {
          _board(row)(col) = 1
        }
        else {
          _board(row)(col) = 0
        }
      }
    }
  }

  // Board print
  // rectangles(row)(col)
  var _rectangles: List[List[Rectangle]] = List()
  // create every single rectangles for tetrisBoard
  for (row <- 0 until 20) {
    var tmpRec: List[Rectangle] = List()
    for (col <- 0 until 10) {
      if (col == 0 || col == 9) {
        tmpRec = tmpRec :+ new Rectangle {
          width = 27
          height = 27
          fill = "pink"
        }

      }
      else if (row >= 17 && row <= 19) {
        if (col >= 3 && col <= 6) {
          tmpRec = tmpRec :+ new Rectangle {
            width = 27
            height = 27
            fill = "brown"
          }

        } else if (row == 19) {
          if (col < 3 || col > 6) {
            tmpRec = tmpRec :+ new Rectangle {
              width = 27
              height = 27
              fill = "pink"
            }
          }

        }
        else {
          tmpRec = tmpRec :+ new Rectangle {
            width = 27
            height = 27
            fill = "white"
          }
        }

      }
      else if (row == 0) {
        tmpRec = tmpRec :+ new Rectangle {
          width = 27
          height = 27
          fill = "pink"
        }
      }
      else {
        tmpRec = tmpRec :+ new Rectangle {
          width = 27
          height = 27
          fill = "white"
        }
      }

      tetrisBoard.add(tmpRec(col), col, row)
    }

    _rectangles = _rectangles ++: List(tmpRec)
  }

  // create every single rectangles for nextPiece
  var _nextPieceRectangles: List[List[Rectangle]] = List()

  // create every single rectangles for nextPieceBoard
  for (row <- 0 until 4) {
    var tmpRec: List[Rectangle] = List() // for every row iterate, reset tmpRec(holder for columns)
    for (col <- 0 until 4) {
      tmpRec = tmpRec :+ new Rectangle {
        width = 27
        height = 27
        fill = "white"
      }
      nextPieceBoard.add(tmpRec(col), col, row)
    }
    _nextPieceRectangles = _nextPieceRectangles ++: List(tmpRec)
  }


  // bind with virtual board
  override def refreshBoard() = {
    // row
    for (row <- 0 until 20) {
      // column
      for (col <- 0 until 10) {
        // if it occupy, fill blue, else white
        if (_board(row)(col) == 1) {
          _rectangles(row)(col).fill = "brown"
        } else {
          if (row == 0) {
            _rectangles(row)(col).fill = "pink"
          } else if (col == 0 || col == 9) {
            _rectangles(row)(col).fill = "pink"
          } else if (row == 19) {
            if (col < 3 || col > 6) {
              _rectangles(row)(col).fill = "pink"
            }
          } else {
            _rectangles(row)(col).fill = "white"
          }
        }
      }
    }
  }

  override def clearPieceFromBoard(piece: List[Array[Int]], _currentX: Int, _currentY: Int) = {
    // paint the board back to white according to the piece
    super.clearPieceFromBoard(piece: List[Array[Int]], _currentX: Int, _currentY: Int)

  }

  override def paintPieceToBoard(piece: List[Array[Int]], _currentX: Int, _currentY: Int) = {
    super.paintPieceToBoard(piece: List[Array[Int]], _currentX: Int, _currentY: Int)
  }

  // paint the virtual board
  override def paintBoard(_currentX: Int, _currentY: Int) {
    super.paintBoard(_currentX: Int, _currentY: Int)
  }

  // add score
  override def addScore(point: Int) {
    _scores += point
    score.setText(_scores.toString)
  }

  //Retrieve random piece
  override def randomPiece(): List[List[Array[Int]]] = {
    super.randomPiece()
  }

  // move method, pass the x and y to move left,right,up,down
  // pass 0 if you dont want to move the block
  override def move(x: Int, y: Int): Unit = {
      for (a <- 0 until currentPiece.size) {
        // check if it hits the side of the board
        if (currentPiece(a)(0) + currentX + x >= 10 || currentPiece(a)(0) + currentX + x < 0) {

          return
        }

        // check whether it collide with other pieces when pressed left or right
        if (collisionDetection(currentPiece(a),(currentX + x),(currentY))) {

          return
        }

        //println(currentY+y)
        // check if it hits the bottom of the board
        if ((currentPiece(a)(1) + currentY + y) > 19) {

          paintBoard(currentX + x,currentY)
          currentPiece = List()
          return
        }

        // check whether it collides the other pieces
        if (collisionDetection(currentPiece(a),(currentX + x),(currentY + y))) {

          paintBoard(currentX + x,currentY)
          currentPiece = List()
          return
        }
      }

      clearPieceFromBoard(currentPiece,currentX,currentY)
      currentX += x
      currentY += y
      paintPieceToBoard(currentPiece,currentX,currentY)
    }

  // Collision detection
  override def collisionDetection(piece: Array[Int], _currentX: Int, _currentY: Int): Boolean = {
    super.collisionDetection(piece: Array[Int], _currentX: Int, _currentY: Int)
  }

  // for animationTimer
  var _time = 0L

  //animationTimer
  val _timer: AnimationTimer = AnimationTimer(t => {

    // if nextPiece is empty, get new piece from randomPiece
    if (_nextPiece.isEmpty) {
      _nextPiece = randomPiece()
      for (a <- 0 until _nextPiece(0).size) {
        _nextPieceRectangles(_nextPiece(0)(a)(1))(_nextPiece(0)(a)(0) + 1).fill = "red"
      }
    }

    if (_currentPiece.isEmpty) {
      var tmpPiece = _nextPiece
      _currenttetrad = tmpPiece
      for (a <- 0 until _nextPiece(0).size) {
        _nextPieceRectangles(_nextPiece(0)(a)(1))(_nextPiece(0)(a)(0) + 1).fill = "white"
      }
      _nextPiece = List()
      _currentZ = 0
      _currentX = 4
      _currentY = 0
      _currentPiece = _currenttetrad(0)
      if (checkGameOver()) {
        _gameOver = true
        _timer.stop
        val alert = new Alert(AlertType.Information) {
          title = "Game Over"
          headerText = "You Lose!"
          contentText = "You have scored: " + _scores.toString
        }
        showPaused.setText("Game Over!")
        Platform.runLater(alert.showAndWait())
      }
      for (a <- 0 until _currentPiece.size) {
        // rectangles(x)(y)
        // if y + 1, move right
        // if y - 1, move left
        // if x + 1, move down
        // if x - 1, move up
        _rectangles(_currentPiece(a)(1) + _currentY)(_currentPiece(a)(0) + _currentX).fill = "blue"
      }
    }

    if (_leftPressed) {
      move(-1, 0)
      _leftPressed = false
    }
    if (_rightPressed) {
      // go right
      move(1, 0)
      _rightPressed = false
    }
    if (_upPressed) {
      // rotate
      if (isRotatable()) {
        clearPieceFromBoard(_currentPiece, _currentX, _currentY)
        _currentPiece = rotate()
        paintPieceToBoard(_currentPiece, _currentX, _currentY)
      }
      _upPressed = false
    }
    if (_downPressed) {
      move(0, 1)
      _downPressed = false
    }
    if (_enterPressed) {
      do {
        move(0, 1)
      }
      while (!_currentPiece.isEmpty)
      _enterPressed = false
    }

    // make the body of this if statement to run every second
    if ((t - _time) > 1e+9) {
      move(0, 1)

      _time = t
    }

  })

  _timer.start

  // control
  var _pause = false
  var _leftPressed = false
  var _rightPressed = false
  var _upPressed = false
  var _downPressed = false
  var _enterPressed = false
  tetris.onKeyPressed = (e: KeyEvent) => {
    if (e.code == KeyCode.A) _leftPressed = true
    if (e.code == KeyCode.D) _rightPressed = true
    if (e.code == KeyCode.W) _upPressed = true
    if (e.code == KeyCode.S) _downPressed = true
    if (e.code == KeyCode.B) _enterPressed = true
    if (e.code == KeyCode.P) {
      if (_pause == false &&  _gameOver ==  false) {
        _timer.stop
        showPaused.setText("Game Paused!")
        _pause = true
      } else {
        if (_pause == true && _gameOver == false) {
          _timer.start
          showPaused.setText("")
          _pause = false
        }
      }
    }
  }


  // rotate the piece
  override def rotate(): List[Array[Int]] = {
    super.rotate(): List[Array[Int]]
  }

  override def isRotatable(): Boolean = {
    super.isRotatable(): Boolean
  }

  override def checkGameOver(): Boolean = {
    for (a <- 0 until _currentPiece.size) {
      if (collisionDetection(_currentPiece(a), _currentX, _currentY)) {
        return true
      }
    }

    for (row <- 0 until 2) {
      for (col <- 0 until 10) {
        if (_board(row)(col) == 1) {
          return true
        }
      }
    }

    return false
  }
}
*/