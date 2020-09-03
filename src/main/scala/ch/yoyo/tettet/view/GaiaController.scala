package ch.yoyo.tettet.view

import ch.yoyo.tettet.model._
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

@sfxml
class GaiaController(tetris: AnchorPane, tetrisBoard: GridPane,
                     nextPieceBoard: GridPane,
                     score: Label, showPaused: Label) {

  // hold the scores
  var scores = 0

  //create tetrad instances
  val iTetrad = new ITetrad()
  val bTetrad = new BranchTetrad()
  val jTetrad = new JTetrad()
  val oTetrad = new OTetrad()
  val tTetrad = new TTetrad()
  val sTetrad = new STetrad()
  val zTetrad = new ZTetrad()
  val lTetrad = new LTetrad()

  //hold all instances
  val tetradLst = List(iTetrad, jTetrad, lTetrad, tTetrad, oTetrad, zTetrad, sTetrad, bTetrad) //refer
  // hold all the tetrad
  val tetrad = List(iTetrad.block, jTetrad.block, lTetrad.block, tTetrad.block, oTetrad.block, zTetrad.block, sTetrad.block, bTetrad.block)

  // hold the piece with the orientation
  var currenttetrad: List[List[Array[Int]]] = List()

  // hold currentPiece data
  var currentPiece: List[Array[Int]] = List()

  // hold the current orientation number
  var currentZ: Int = 0

  // hold the currentY
  var currentY: Int = 0

  // hold the currentX
  var currentX: Int = 5

  // hold nextPiece data
  var nextPiece: List[List[Array[Int]]] = List()

  // a virtual board that holds all the diePiece data
  var board = Array.ofDim[Int](20, 10)

  // whether gameOver or not
  var gameOver = false

  val file = new File("src/main/resources/ch/yoyo/tettet/sound/rotate.wav")
  val media = new Media(source = file.toURI().toString)
  val player = new MediaPlayer(media = media)

  val file2 = new File("src/main/resources/ch/yoyo/tettet/sound/clear.wav")
  val media2= new Media(source = file2.toURI().toString)
  val player2= new MediaPlayer(media = media2)

  val file3 = new File("src/main/resources/ch/yoyo/tettet/sound/move.wav")
  val media3= new Media(source = file3.toURI().toString)
  val player3= new MediaPlayer(media = media3)

  val file4 = new File("src/main/resources/ch/yoyo/tettet/sound/collision.wav")
  val media4= new Media(source = file4.toURI().toString)
  val player4= new MediaPlayer(media = media4)

  val file5 = new File("src/main/resources/ch/yoyo/tettet/sound/gameover.wav")
  val media5= new Media(source = file5.toURI().toString)
  val player5= new MediaPlayer(media = media5)

  val file6 = new File("src/main/resources/ch/yoyo/tettet/sound/hitBorder.wav")
  val media6= new Media(source = file6.toURI().toString)
  val player6= new MediaPlayer(media = media6)

  val file7 = new File("src/main/resources/ch/yoyo/tettet/sound/pause.wav")
  val media7= new Media(source = file7.toURI().toString)
  val player7= new MediaPlayer(media = media7)


  var sound: List[MediaPlayer] = List(player,player2,player3,player4,player5,player6,player7)

  // Initialize the board
  for (row <- 0 until 20) {
    for (col <- 0 until 10) {
      if (row >=17 && row <= 19){
        if(col >=3 && col <=6){
          board(row)(col) = 1
        }
        else{
          board(row)(col) = 0
        }
      }
    }
  }

  // rectangles(row)(col)
  var rectangles: List[List[Rectangle]] = List()
  // create every single rectangles for tetrisBoard
  for (row <- 0 until 20) {
    var tmpRec: List[Rectangle] = List()
    for (col <- 0 until 10) {
      if(col == 0 || col == 9) {
        tmpRec = tmpRec :+ new Rectangle {
          width = 27
          height = 27
          fill = "pink"
        }

      }
      else if (row >=17 && row <= 19){
        if(col >=3 && col <=6){
          tmpRec = tmpRec :+ new Rectangle {
            width = 27
            height = 27
            fill = "brown"
          }

        }else if(row == 19 ) {
          if(col <3 || col > 6) {
            tmpRec = tmpRec :+ new Rectangle {
              width = 27
              height = 27
              fill = "pink"
            }
          }

        }
        else{
          tmpRec = tmpRec :+ new Rectangle {
            width = 27
            height = 27
            fill = "white"
          }
        }

      }
      else if(row == 0 ){
        tmpRec = tmpRec :+ new Rectangle {
          width = 27
          height = 27
          fill = "pink"
        }
      }
      else{
        tmpRec = tmpRec :+ new Rectangle {
          width = 27
          height = 27
          fill = "white"
        }
      }

      tetrisBoard.add(tmpRec(col),col,row)
    }

    rectangles = rectangles ++: List(tmpRec)
  }

  // create every single rectangles for nextPiece
  var nextPieceRectangles: List[List[Rectangle]] = List()

  // create every single rectangles for nextPieceBoard
  for (row <- 0 until 4) {
    var tmpRec: List[Rectangle] = List()      // for every row iterate, reset tmpRec(holder for columns)
    for (col <- 0 until 4) {
      tmpRec = tmpRec :+ new Rectangle {
        width = 27
        height = 27
        fill = "white"
      }
      nextPieceBoard.add(tmpRec(col),col,row)
    }
    nextPieceRectangles = nextPieceRectangles ++: List(tmpRec)
  }

  // control
  var pause = false
  var leftPressed = false
  var rightPressed = false
  var upPressed = false
  var downPressed = false
  var enterPressed = false
  tetris.onKeyPressed = (e: KeyEvent) => {
    if(e.code == KeyCode.A) leftPressed = true
    if(e.code == KeyCode.D) rightPressed = true
    if(e.code == KeyCode.W) upPressed = true
    if(e.code == KeyCode.S) downPressed = true
    if(e.code == KeyCode.B) enterPressed = true
    if(e.code == KeyCode.P) {
      if (pause == false && gameOver == false) {
        sound(6).stop
        sound(6).play
        timer.stop
        showPaused.setText("Game Paused!")
        pause = true
      } else {
        if (pause == true && gameOver == false) {
          sound(6).stop
          sound(6).play
          timer.start
          showPaused.setText("")
          pause = false
        }
      }
    }
  }

  // for animationTimer
  var time = 0L

  //animationTimer
  val timer: AnimationTimer = AnimationTimer(t => {

    // if nextPiece is empty, get new piece from randomPiece
    if (nextPiece.isEmpty) {
      nextPiece = randomPiece()
      for (a <- 0 until nextPiece(0).size) {
        nextPieceRectangles(nextPiece(0)(a)(1))(nextPiece(0)(a)(0)+1).fill = "red"
      }
    }

    // if currentPiece is empty, get new one
    // randomPiece
    if (currentPiece.isEmpty) {
      var tmpPiece = nextPiece
      currenttetrad = tmpPiece
      for (a <- 0 until nextPiece(0).size) {
        nextPieceRectangles(nextPiece(0)(a)(1))(nextPiece(0)(a)(0)+1).fill = "white"
      }
      nextPiece = List()
      currentZ = 0
      currentX = 4
      currentY = 0
      currentPiece = currenttetrad(0)
      if (checkGameOver()) {
        gameOver = true
        timer.stop
        sound(4).play
        val alert = new Alert(AlertType.Information) {
          title = "Game Over"
          headerText = "You Lose!"
          contentText = "You have scored: " + scores.toString
        }
        showPaused.setText("Game Over!")
        Platform.runLater(alert.showAndWait())
      }
      for (a <- 0 until currentPiece.size) {
        // rectangles(x)(y)
        // if y + 1, move right
        // if y - 1, move left
        // if x + 1, move down
        // if x - 1, move up
        rectangles(currentPiece(a)(1)+currentY)(currentPiece(a)(0)+currentX).fill = "blue"
      }
    }

    if(leftPressed) {
      move(-1,0)
      leftPressed = false
    }
    if(rightPressed) {
      // go right
      move(1,0)
      rightPressed = false
    }
    if(upPressed) {
      // rotate
      if (isRotatable()) {
        sound(0).stop
        sound(0).play
        clearPieceFromBoard(currentPiece,currentX,currentY)
        currentPiece = rotate()
        paintPieceToBoard(currentPiece,currentX,currentY)
      } else {
        sound(3).stop
        sound(3).play
      }
      upPressed = false
    }
    if(downPressed) {
      move(0,1)
      downPressed = false
    }
    if(enterPressed) {
      do {
        move(0,1)
      }
      while (!currentPiece.isEmpty)
      enterPressed = false
    }

    // make the body of this if statement to run every second
    if ((t - time) > 1e+9) {
      move(0,1)

      time = t
    }

  })

  timer.start

  // rotate the piece
  def rotate(): List[Array[Int]] = {
    if (currentZ + 1 >= currenttetrad.size) {
      currentZ = 0
      currenttetrad(currentZ)
    } else {
      currentZ += 1
      currenttetrad(currentZ)
    }
  }

  def isRotatable(): Boolean = {
    var piece = currenttetrad((currentZ + 1) % currenttetrad.size)
    for (a <- 0 until piece.size) {
      // check whether it hits the side
      if ((piece(a)(0) + currentX) >= 10 || (piece(a)(0) + currentX) < 0) {
        return false
      }

      // check whether it hits the bottom
      if ((piece(a)(1) + currentY) >= 20) {
        return false
      }

      // check whether it is occupied
      if ((board((piece(a)(1) + currentY))(piece(a)(0) + currentX) == 1)) {
        false
      }
      else if (board(piece(a)(1) + currentY)(piece(a)(0) + currentX) == 2){
        false
      }
    }
    return true
  }

  def checkGameOver(): Boolean = {
    for (a <- 0 until currentPiece.size) {
      if (collisionDetection(currentPiece(a),currentX,currentY)) {
        return true
      }
    }

    for (row <- 0 until 20) { //TODO
      for (col <- 0 until 10) {
        if (col == 0 || col == 9){
          if (board(row)(col) == 1) {
            return true
          }
        } else if (row == 0){
          if (board(row)(col) == 1) {
            return true
          }
        }
        else if (row == 19){
          if (col < 3 || col > 6){
            if (board(row)(col) == 1) {
              return true
            }
          }
        }
      }
    }

    return false
  }
  //TODO
  // paint the virtual board
  def paintBoard(currentX: Int, currentY: Int) {
    tetradLst.foreach{x=>
      if(x.block equals currenttetrad){
        for (a <- 0 until currentPiece.size) {
          var tmpCol = currentPiece(a)(0) + currentX
          var tmpRow = currentPiece(a)(1) + currentY
          board(tmpRow)(tmpCol) = 2
        }
      }
      else{
        for (a <- 0 until currentPiece.size) {
          var tmpCol = currentPiece(a)(0) + currentX
          var tmpRow = currentPiece(a)(1) + currentY
          board(tmpRow)(tmpCol) = 1
        }
      }

    }

    addScore(1)
    refreshBoard()
  }



  // add score
  def addScore(point: Int) {
    scores += point
    score.setText(scores.toString)
  }

  // check virtual board

  // if collide at row, add to board
  // if collide at col, dont let it move
  def collisionDetection(piece: Array[Int], currentX: Int, currentY: Int): Boolean = {
    if (board(piece(1)+currentY)(piece(0)+currentX) == 1)
      true
    else if (board(piece(1)+currentY)(piece(0)+currentX) == 2)
      true
    else
      false
  }

  // bind with virtual board
  def refreshBoard() = {
    // row
    for (row <- 0 until 20) {
      // column
      for (col <- 0 until 10) {


        // if it occupy, fill the right colour, otherwise make it white
        if (board(row)(col) == 1) {
          if (row >= 17 && row <= 19) {
            if (col >= 3 && col <= 6) {
              rectangles(row)(col).fill = "brown"
            }
            else if(col <3 || col >6){
              rectangles(row)(col).fill = "lightgreen"
            }
          } else if (row > 0){
            rectangles(row)(col).fill = "lightgreen"
          }
        } else if(board(row)(col)==2){
          //for branches
          rectangles(row)(col).fill = "brown"
        }
        else {
          if (row == 0) {                     // at the very top filled with pink
            rectangles(row)(col).fill = "pink"
          } else if (col == 0 || col == 9) {  // at both leftmost and rightmost filled with pink
            rectangles(row)(col).fill = "pink"
          } else if (row == 19){    // at the very bottom fill the row with pink except tree branch
            if(col <3 || col >6){
              rectangles(row)(col).fill = "pink"
            }
          }else {
            rectangles(row)(col).fill = "white"     //unoccupied
          }
        }
      }
    }
  }

  // return a random tetrad
  def randomPiece(): List[List[Array[Int]]] = {
    tetrad(Random.nextInt(tetrad.size))
  }

  // move method, pass the x and y to move left,right,up,down
  // pass 0 if you dont want to move the block
  def move(x: Int, y: Int): Unit = {

    for (a <- 0 until currentPiece.size) {
      // check if it hits the side of the board
      if (currentPiece(a)(0) + currentX + x >= 10 || currentPiece(a)(0) + currentX + x < 0) {
        sound(5).stop
        sound(5).play
        return
      }

      // check whether it collide with other pieces when pressed left or right
      if (collisionDetection(currentPiece(a),(currentX + x),(currentY))) {
        sound(3).stop
        sound(3).play
        return
      }

      //println(currentY+y)
      // check if it hits the bottom of the board
      if ((currentPiece(a)(1) + currentY + y) > 19) {
        sound(3).stop
        sound(3).play
        paintBoard(currentX + x,currentY)
        currentPiece = List()
        return
      }

      // check whether it collides the other pieces
      if (collisionDetection(currentPiece(a),(currentX + x),(currentY + y))) {
        sound(3).stop
        sound(3).play
        paintBoard(currentX + x,currentY)
        currentPiece = List()
        return
      }
    }
    sound(2).stop
    sound(2).play

    clearPieceFromBoard(currentPiece,currentX,currentY)
    currentX += x
    currentY += y
    paintPieceToBoard(currentPiece,currentX,currentY)
  }

  def clearPieceFromBoard(piece: List[Array[Int]], currentX: Int, currentY: Int) = {
    // paint the board back to white according to the piece
    for (a <- 0 until piece.size) {
      rectangles(piece(a)(1)+currentY)(piece(a)(0)+currentX).fill = "white"
      refreshBoard()
    }
  }


  def paintPieceToBoard(piece: List[Array[Int]], currentX: Int, currentY: Int) = {
    // paint the board back blue according to the piece
    for (a <- 0 until piece.size) {
      rectangles(piece(a)(1)+currentY)(piece(a)(0)+currentX).fill = "blue"
    }
  }

}